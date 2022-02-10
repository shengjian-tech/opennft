package net.shengjian.system.service;

import net.shengjian.rpc.annotation.RpcServiceAnnotation;
import net.shengjian.system.entity.Notify;
import net.shengjian.system.vo.NotifyVO;

import java.util.List;

/**
 * 消息通知
 * @author springrain<Auto generate>
 * @version  2021-09-14 13:36:14
 */
@RpcServiceAnnotation
public interface INotifyService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Notify findNotifyById(String id) throws Exception;


    /**
     * 消息通知
     * @return
     * @throws Exception
     */
    List<NotifyVO> message() throws Exception;

	/**
	 * 消息状态的改变
	 * @throws Exception
	 */
	NotifyVO updatemessagestatus(String id) throws Exception;

    /**
     * 新增一个消息通知
     * @throws Exception
     */
	void saveNotify(Notify notify) throws Exception;
    /**
     * 发送系统运行通知
     * @param userId
     * @param title
     * @param content
     * @return
     */
    void saveSendSysMessage(String userId,String title,String content) throws Exception;
    /**
     * 发送系统运行通知
     * @param notifyId
     * @param userId
     * @param title
     * @param content
     * @return
     */
    void saveSendSysMessage(String notifyId,String userId,String title,String content) throws Exception;
}
