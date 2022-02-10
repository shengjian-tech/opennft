package net.shengjian.frame.mq;

import net.shengjian.frame.config.RedisCacheConfig;
import net.shengjian.frame.util.ClassUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Closeable;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 因为接口不能注入springBean,使用抽象类实现,主要用于隔离了Redis Stream API,方便后期更换MQ的实现.
 * 如果未确认消息消费,Redis Stream 暂时没有重试的API,项目启动时使用 retryFailMessage() 重试一次,业务代码可以自行调度retryFailMessage()方法
 * 使用生产消费的group模式,用于多个消费者并行消费,只有group模式才有ack应答.如果要订阅发布,每个客户端创建一个group变通实现.
 * 订阅发布模式,使用 $ 符号订阅最新的消息,目前监听器存在问题,不能正常消费,原因待查
 * 子类继承之后注入,需要使用IMessageProducerConsumerListener接口,例如
 * <code>
 *
 * @param <T> 需要放入队列的对象
 * @Component("userMessageProducerConsumerListener") public class UserMessageProducerConsumerListener extends AbstractMessageProducerConsumerListener<User>
 *
 * //sendProducerMessage 方法实现 return super.sendProducerMessage();
 *
 * </code>
 *
 * <code>
 * @Resource IMessageProducerConsumerListener<User> userMessageProducerConsumerListener;
 * </code>
 */
public abstract class AbstractMessageProducerConsumerListener<T> implements StreamListener<String, ObjectRecord<String, T>>, IMessageProducerConsumerListener<T>, Closeable {
    private Logger logger = LoggerFactory.getLogger(getClass());
    //默认的线程池
    //private final Executor defaultExecutor = new SimpleAsyncTaskExecutor();

    // 默认batchSize
    private final int defaultBatchSize = 100;


    // 默认XTRIM的MAXLEN,如果是<0,则不限制
    private  int defaultMaxLen = -1;

    //泛型的类型
    private final Class<T> genericClass = ClassUtils.getActualTypeGenericSuperclass(getClass());

    //监听的容器
    private StreamMessageListenerContainer<String, ObjectRecord<String, T>> container = null;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 消息队列的名称,redis里就是stream的名称
     *
     * @return
     */
    public abstract String getQueueName();

    /**
     * 批量消费的数量
     *
     * @return
     */
    public int getBatchSize() {
        return defaultBatchSize;
    }

    /**
     * 默认XTRIM的MAXLEN,如果是<0,则不限制
     * @return
     */
    public int getDefaultMaxLen() {
        return defaultMaxLen;
    }

    /**
     * 默认XTRIM的MAXLEN,如果是<0,则不限制
     * @param defaultMaxLen
     */
    public void setDefaultMaxLen(int defaultMaxLen) {
        this.defaultMaxLen = defaultMaxLen;
    }


    /**
     * 消费者的名称
     *
     * @return
     */
    public abstract String getConsumerName();

    /**
     * group的名称,如果为空,默认是 getQueueName()+"_defaultGroupName"
     *
     * @return
     */
    public String getGroupName() {
        return getQueueName() + "_defaultGroupName";
    }

    /**
     * 指定监听器的线程池
     *
     * @return
     */
    public Executor getExecutor() {
        return null;
    }

    /**
     * spring-data-redis 实现的 stream 原生消费者回调方法,依赖Redis ObjectRecord API,业务中不要直接调用!!!!!!.
     * 使用自行实现的onMessage(T value, String queueName, String messageId, Long messageTime) 方法
     *
     * @param message 需要消费者处理的消息
     */
    @Override
    public void onMessage(ObjectRecord<String, T> message) {
        try {
            RecordId recordId = messageSuccessRecordId(message);
            if (recordId != null) {
                //消息确认ack
                redisTemplate.opsForStream().acknowledge(getQueueName(), getGroupName(), recordId);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }


    }


    /**
     * 消费消息,隔离Redis API,如果返回true则自动应答,如果返回false,认为消息处理失败
     *
     * @param messageObjectDto
     * @return
     */
    @Override
    public abstract boolean onMessage(MessageObjectDto<T> messageObjectDto) throws Exception;

    /**
     * 初始化监听器
     */
    @PostConstruct
    private void registerConsumerListener() {
        try {

            String className = getClass().toString();
            if (StringUtils.isBlank(getQueueName())) {
                logger.error(className + "的getQueueName()为空,registerConsumerListener()方法执行失败.");
                return;
            }
            if (StringUtils.isBlank(getGroupName())) {
                logger.error(className + "的getGroupName()为空,registerConsumerListener()方法执行失败.");
                return;
            }
            if (StringUtils.isBlank(getConsumerName())) {
                logger.error(className + "的getConsumerName()为空,registerConsumerListener()方法执行失败.");
                return;
            }


            int batchSize = getBatchSize();
            if (batchSize < 1) {
                batchSize = defaultBatchSize;
            }

            Executor executor = getExecutor();
            if (executor == null) {
                executor = new SimpleAsyncTaskExecutor();
            }

            // 增加自定义的 BytesToTimestampConverter 类型转换器.
            // spring jdbc 把 datetime 类型解析成了 java.sql.timestamp,spring-data-redis并没用提供BytesToTimestampConverter,造成无法转换类型
            CustomConversions customConversions = new RedisCustomConversions(Arrays.asList(new BytesToTimestampConverter()));
            // 使用 ObjectHashMapper 构造函数 注册自定义的转换器
            ObjectHashMapper objectHashMapper = new ObjectHashMapper(customConversions);


            //监听器的配置项
            StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, ObjectRecord<String, T>> options =
                    StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder()
                            .batchSize(batchSize) //一批次拉取的最大count数
                            .executor(executor)  //线程池
                            .pollTimeout(Duration.ZERO) //阻塞式轮询
                            //设置默认的序列化器,要和 redisTemplate 保持一致!!!!!!!!!!!!!!!!!!!!!
                            //默认 targetType 会设置序列化器是  RedisSerializer.byteArray,这里手动初始化objectMapper,并设置自定义转换器和序列化器.
                            .objectMapper(objectHashMapper)
                            .keySerializer(RedisCacheConfig.stringRedisSerializer)
                            .hashKeySerializer(RedisCacheConfig.stringRedisSerializer)
                            .hashValueSerializer(RedisCacheConfig.fstSerializer)
                            //.serializer(RedisCacheConfig.fstSerializer)
                            .targetType(genericClass) //目标类型(消息内容的类型),如果objectMapper为空,会设置默认的ObjectHashMapper
                            .build();
            container = StreamMessageListenerContainer.create(redisConnectionFactory, options);
            //检查创建group组
            prepareChannelAndGroup(redisTemplate.opsForStream(), getQueueName(), getGroupName());

            // 通过xread命令也就是非消费者组模式直接读取,或者使用xreadgroup命令在消费者组中命令一个消费者去消费一条记录,
            // 我们可以通过0、>、$分别表示第一条记录、最后一次未被消费的记录和最新一条记录,
            // 比如创建消费者组时不能使用>表示最后一次未被消费的记录,比如0表示从第一条开始并且包括第一条,
            // $表示从最新一条开始但并不是指当前Stream的最后一条记录,是表示下一个xadd添加的那一条记录,所以说$在非消费者组模式的阻塞读取下才有意义!


            // 消费者
            Consumer consumer = Consumer.from(getGroupName(), getConsumerName());

            // 需要手动回复应答 ACK
            // container.receive(consumer, StreamOffset.fromStart(getQueueName()), this);
            // container.receive(consumer, StreamOffset.create(getQueueName(),ReadOffset.latest()), this);
            container.receive(consumer, StreamOffset.create(getQueueName(), ReadOffset.lastConsumed()), this);
            container.start();


            //开启线程,重试异常的消息
            executor.execute(() -> {
                //重试失败的消息
                try {
                    retryFailMessage();
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            });


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }


    /**
     * 重试消息,项目启动时会重试一次,业务代码自行实现根据调度重试
     * 避免死循环,最多1000次.如果单次返回的所有消息都是异常的,终止重试.
     * 如果全部重试成功,返回null.如果还有部分失败,就返回失败的消息记录
     *
     * @return 返回重试失败的消息记录对象
     */
    @Override
    public List<MessageObjectDto<T>> retryFailMessage() throws Exception {

        int batchSize = getBatchSize();
        if (batchSize < 1) {
            batchSize = defaultBatchSize;
        }
        //消费者
        Consumer consumer = Consumer.from(getGroupName(), getConsumerName());
        //设置配置
        StreamReadOptions streamReadOptions = StreamReadOptions.empty().count(batchSize).block(Duration.ofSeconds(5));
        List<ObjectRecord<String, T>> retryFailMessageList = new ArrayList<>();
        //避免死循环,最多1000次.如果单次返回的所有消息都是异常的,退出循环
        for (int i = 0; i < 1000; i++) {
            List<ObjectRecord<String, T>> readList = redisTemplate.opsForStream().read(genericClass, consumer, streamReadOptions, StreamOffset.fromStart(getQueueName()));
            //如果已经没有异常的消息,退出循环
            if (CollectionUtils.isEmpty(readList)) {
                break;
            }
            //如果返回的消息全部都是异常的,退出循环
            if (retryFailMessageList.containsAll(readList)) {
                break;
            }

            // 遍历异常的消息
            for (ObjectRecord<String, T> message : readList) {
                RecordId recordId = messageSuccessRecordId(message);
                //处理成功
                if (recordId != null) {
                    //消息确认ack
                    redisTemplate.opsForStream().acknowledge(getQueueName(), getGroupName(), recordId);
                } else {//处理失败,记录下来
                    retryFailMessageList.add(message);
                }
            }
        }
        // 没有失败的消息记录
        if (CollectionUtils.isEmpty(retryFailMessageList)) {
            return null;
        }
        //返回处理异常的消息
        List<MessageObjectDto<T>> retryFailMessageObjectList = new ArrayList<>();
        for (ObjectRecord<String, T> message : retryFailMessageList) {
            retryFailMessageObjectList.add(objectRecord2MessageObject(message));
        }
        return retryFailMessageObjectList;
    }


    /**
     * 在初始化容器时,如果key对应的stream或者group不存在时会抛出异常,所以我们需要提前检查并且初始化.
     *
     * @param ops
     * @param queueName
     * @param group
     */
    private void prepareChannelAndGroup(StreamOperations<String, ?, ?> ops, String queueName, String group) {
        String status = "OK";
        try {
            //如果group已经存在
            StreamInfo.XInfoGroups groups = ops.groups(queueName);
            if (groups.stream().noneMatch(xInfoGroup -> group.equals(xInfoGroup.groupName()))) {
                //status = ops.createGroup(queueName, group);
                status = ops.createGroup(queueName, ReadOffset.from("0-0"), group);
            }
        } catch (Exception exception) {
            if (getDefaultMaxLen()>0){
                ops.trim(queueName,getDefaultMaxLen(),true);
            }
            T t = null;
            //初始化/创建队列
            RecordId initialRecord = ops.add(ObjectRecord.create(queueName, t));
            Assert.notNull(initialRecord, "Cannot initialize stream with key '" + queueName + "'");
            status = ops.createGroup(queueName, ReadOffset.from(initialRecord), group);
        } finally {
            Assert.isTrue("OK".equals(status), "Cannot create group with name '" + group + "'");
        }
    }


    /**
     * 生产者向消息队列发送消息
     *
     * @param message
     * @return
     */
    @Override
    public MessageObjectDto<T> sendProducerMessage(T message) throws Exception {
        if (message == null) {
            return null;
        }

        try {
            ObjectRecord<String, T> record = Record.of(message).withStreamKey(getQueueName());
            //StreamRecords.newRecord()
            //ObjectRecord record = Record.of(message).withStreamKey(queueName);
            RecordId recordId = redisTemplate.opsForStream().add(record);
            // return recordId.getValue();
            return new MessageObjectDto<T>(message, getQueueName(), recordId.getValue(), recordId.getTimestamp());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
            //return null;
        }
    }

    /**
     * 消息消费是否成功,成功返回RecordId,失败返回null
     *
     * @param message
     * @return
     */
    private RecordId messageSuccessRecordId(ObjectRecord<String, T> message) {
        RecordId recordId = message.getId();
        MessageObjectDto<T> messageObjectRecord = objectRecord2MessageObject(message);
        try {
            boolean ok = onMessage(messageObjectRecord);
            if (ok) {
                return recordId;
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }


    /**
     * ObjectRecord2MessageObject 类型转换
     *
     * @param message
     * @return
     */
    private MessageObjectDto<T> objectRecord2MessageObject(ObjectRecord<String, T> message) {
        RecordId recordId = message.getId();
        String messageId = recordId.getValue();
        Long messageTime = recordId.getTimestamp();
        String queueName = message.getStream();
        T messageObject = message.getValue();

        MessageObjectDto<T> messageObjectRecord = new MessageObjectDto<>(messageObject, queueName, messageId, messageTime);


        return messageObjectRecord;

    }


    @Override
    public void close() throws IOException {
        if (container != null) {
            container.stop();
        }

    }

}
