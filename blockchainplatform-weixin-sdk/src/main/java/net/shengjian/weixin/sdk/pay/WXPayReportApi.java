package net.shengjian.weixin.sdk.pay;


import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxPayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;


public class WXPayReportApi {

    private static Logger logger = LoggerFactory.getLogger(WXPayReportApi.class);

    private static LinkedBlockingQueue<String> reportMsgQueue = null;
    private static ExecutorService executorService = null;

    // 健康上报缓存消息的最大数量。会有线程去独立上报
    // 粗略计算：加入一条消息200B，10000消息占用空间 2000 KB，约为2MB，可以接受
    private static Integer reportQueueMaxSize = 10000;

    // 进行健康上报的线程的数量
    private static Integer reportWorkerNum = 6;

    // 批量上报，一次最多上报多个数据
    private static Integer reportBatchSize = 10;

    private static String REPORT_URL = WxConsts.reporturl + "/wxpay/report/default";

    // 初始化变量,设置线程池.
    static {
        reportMsgQueue = new LinkedBlockingQueue<>(reportQueueMaxSize);

        // 添加处理线程
        executorService = Executors.newFixedThreadPool(reportWorkerNum, new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            }
        });

        logger.info("report worker num: {}", reportWorkerNum);
        for (int i = 0; i < reportWorkerNum; ++i) {
            executorService.execute(new Runnable() {
                public void run() {
                    while (true) {
                        // 先用 take 获取数据
                        try {
                            StringBuffer sb = new StringBuffer();
                            String firstMsg = reportMsgQueue.take();
                            logger.info("get first report msg: {}", firstMsg);
                            String msg = null;
                            sb.append(firstMsg); //会阻塞至有消息
                            int remainNum = reportBatchSize - 1;
                            for (int j = 0; j < remainNum; ++j) {
                                logger.info("try get remain report msg");
                                // msg = reportMsgQueue.poll();  // 不阻塞了
                                msg = reportMsgQueue.take();
                                logger.info("get remain report msg: {}", msg);
                                if (msg == null) {
                                    break;
                                } else {
                                    sb.append("\n");
                                    sb.append(msg);
                                }
                            }
                            // 上报
                            Map<String, String> header = new HashMap<>();
                            header.put("Content-Type", "text/xml");
                            header.put("User-Agent", WXPayConstants.USER_AGENT);
                            String httpHeaderPost = HttpClientUtils.sendHttpHeaderPost(REPORT_URL, header, sb.toString(), null);
                            //WXPayReport.httpRequest(sb.toString(), DEFAULT_CONNECT_TIMEOUT_MS, DEFAULT_READ_TIMEOUT_MS);
                        } catch (Exception ex) {
                            logger.error("report fail. reason: {}", ex.getMessage());
                        }
                    }
                }
            });
        }

    }

    private WXPayReportApi() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    /**
     * @param config
     * @param reportInfo
     */
    public static void report(IWxPayConfig config, WXPayReport reportInfo) {
        String data = reportInfo.toLineString(config.getKey());
        logger.info("report {}", data);
        if (data != null) {
            reportMsgQueue.offer(data);
        }
    }


}
