package net.shengjian.test;

import net.shengjian.frame.util.DateUtils;
import net.shengjian.makerone.entity.NftWorks;
import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.enums.EWorksState;
import net.shengjian.makerone.service.INftWorksService;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.rpc.sessionuser.UserVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @descriptions: 作品测试
 * @author: YSK
 * @date: 2021/12/27 10:53
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NftWorksTester {
    @Resource
    private INftWorksService nftWorksService;


    @Before
    public void classBefore(){
        UserVO userVO = new UserVO();
        userVO.setUserId("u_10001");
        SessionUser.sessionUserLocal.set(userVO);
    }

    /**
     * 更新作品状态
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        Boolean aBoolean = nftWorksService.updateWorksState("20211224113516054360679070", EWorksState.售卖中,new Date(),DateUtils.addDay(30),DateUtils.addDay(1),null);
        System.out.println("aBoolean = " + aBoolean);
    }
    /**
     * 更新作品状态2
     * @throws Exception
     */
    @Test
    public void testUpdate2() throws Exception {
        Boolean aBoolean = nftWorksService.updateWorksState("20220107140726335037286187", EWorksState.已售停,null,null,null,null);
        System.out.println("aBoolean = " + aBoolean);
    }

    /**
     * 根据合集id批量更新未上架的作品状态
     * @throws Exception
     */
    /*@Test
    public void testupdateBatchWorksStateByCollectionId() throws Exception {
        Boolean aBoolean = nftWorksService.updateBatchWorksStateByCollectionId("20211227182150514304267808",new Date(),DateUtils.addDay(30),DateUtils.addDay(1));
        System.out.println("aBoolean = " + aBoolean);
    }*/

    /**
     * 测试作品上架
     * @throws Exception
     */
    @Test
    public void testUpdateWorksIn() throws Exception {
        Boolean aBoolean = nftWorksService.updateWorksIn(null,"20211227182237551224237350",new Date(),DateUtils.addDay(30),DateUtils.addDay(1),null);
        System.out.println("aBoolean = " + aBoolean);
    }
    /**
     * 测试更新作品在链平台的上链认证信息
     * @throws Exception
     */
    @Test
    public void testUpdateChainPlatCert() throws Exception {
        Map<String,Object> args = new HashMap<>();
        args.put("a","b");
        Boolean aBoolean = nftWorksService.updateChainPlatCert(SessionUser.getUserId(),"20211227180533375858658136", EChainPlat.百度超级链,args);
        System.out.println("aBoolean = " + aBoolean);
    }
    /**
     * 测试上架合集内的作品
     * @throws Exception
     */
    @Test
    public void testUpdateInWorksByCollectionId() throws Exception {
        List<NftWorks> nftWorks = nftWorksService.updateInWorksByCollectionId("20211227182150514304267808", new Date(), DateUtils.addDay(10), null);
        System.out.println("aBoolean = " + nftWorks);
    }
}
