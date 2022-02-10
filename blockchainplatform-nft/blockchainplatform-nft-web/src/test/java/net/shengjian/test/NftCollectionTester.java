package net.shengjian.test;

import net.shengjian.frame.util.DateUtils;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.service.INftCollectionService;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.rpc.sessionuser.UserVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @descriptions: 合集测试
 * @author: YSK
 * @date: 2021/12/27 10:53
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NftCollectionTester {
    @Resource
    private INftCollectionService collectionService;


    @Before
    public void classBefore(){
        UserVO userVO = new UserVO();
        userVO.setUserId("u_10001");
        SessionUser.sessionUserLocal.set(userVO);
    }

    @Test
    public void testUpdate() throws Exception {
        Boolean aBoolean = collectionService.updateIsIn("20211224101348044146049021", CommonConst.FALSE,new Date(),DateUtils.addDay(30));
        System.out.println("aBoolean = " + aBoolean);
    }

    /**
     * 测试合集上架
     * @throws Exception
     */
    @Test
    public void testUpdateCollectionOnShelves() throws Exception {
        Boolean aBoolean = collectionService.updateCollectionOnShelves("20211227183415482378812780",new Date(), DateUtils.addDay(10),null);
        System.out.println("aBoolean = " + aBoolean);
    }

}
