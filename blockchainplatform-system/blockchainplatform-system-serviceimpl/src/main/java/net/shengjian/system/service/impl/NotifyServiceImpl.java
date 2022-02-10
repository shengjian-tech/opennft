package net.shengjian.system.service.impl;

import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.frame.util.Finder;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.system.entity.Notify;
import net.shengjian.system.entity.User;
import net.shengjian.system.service.INotifyService;
import net.shengjian.system.vo.NotifyVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 消息通知
 * @author springrain<Auto generate>
 * @version  2021-09-14 13:36:14
 */

@Service("notifyService")
public class NotifyServiceImpl extends BaseSpringrainServiceImpl implements INotifyService {

   
    @Override
	public String  save(IBaseEntity entity ) throws Exception{
	    Notify notify=(Notify) entity;
	    return super.save(notify).toString();
	}


	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
		Notify notify=(Notify) entity;
		return super.update(notify);
    }
	
    @Override
	public Notify findNotifyById(String id) throws Exception{
		return super.findById(id,Notify.class);
	}

    @Override
    public List<NotifyVO> message() throws Exception {
		String userId = SessionUser.getUserId();
		List<NotifyVO> objects = new ArrayList<>();


		//TODO 查询表中所有信息
		Finder allFinder = Finder.getSelectFinder(Notify.class,"*")
                .append("where acceptId=:acceptId AND status=:status ")
                .setParam("acceptId",userId)
                .setParam("status","0");

		//TODO 返回表中所有信息
		objects = queryForList(allFinder, NotifyVO.class);

		return objects;
    }

	@Override
	public NotifyVO updatemessagestatus(String id) throws Exception {

        Finder f_update = Finder.getUpdateFinder(Notify.class,"status=:status").setParam("status",1).append(" WHERE id=:id").setParam("id",id);
        super.update(f_update);
		return null;
	}

    @Override
    public void saveNotify(Notify notify) throws Exception {
        super.save(notify);
    }

    @Override
    public void saveSendSysMessage(String userId, String title, String content) throws Exception {
        Notify notify = new Notify(
                SecUtils.getTimeNO(),
                Thread.currentThread().getName(),
                new Date(),
                userId, null,
                title,
                content, 0, 1, null);
        this.save(notify);
    }

    @Override
    public void saveSendSysMessage(String notifyId, String userId, String title, String content) throws Exception {
        Notify notify = new Notify(
                SecUtils.getTimeNO(),
                notifyId,
                new Date(),
                userId, null,
                title,
                content, 0, 1, null);
        this.save(notify);
    }

}
