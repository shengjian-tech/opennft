package net.shengjian.test;

import net.shengjian.makerone.enums.EAssetsSellState;
import net.shengjian.makerone.enums.EAssetsType;
import net.shengjian.makerone.service.INftUserAssetsService;
import net.shengjian.makerone.service.INftWorksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @descriptions:
 * @author: YSK
 * @date: 2021/12/24 15:13
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WorksTester {
    @Resource
    private INftWorksService nftWorksService;

    @Resource
    private INftUserAssetsService nftUserAssetsService;
    /**
     * 测试减少作品余量
     * @throws Exception
     */
    @Test
    public void testUpdateReduceInQuantity() throws Exception {
        Boolean aBoolean = nftWorksService.updateReduceInQuantity("20211228105933427769314787", 7);
        System.out.println("aBoolean = " + aBoolean);
    }
    /**
     * 测试更新购买者
     * @throws Exception
     */
    @Test
    public void testUpdateBuyers() throws Exception {
        Integer updateAssetsUser = nftUserAssetsService.updateAssetsUser(EAssetsSellState.已生成订单,EAssetsSellState.不出售,"20211229113700958853606512", "456","123", 1);
        System.out.println("updateAssetsUser = " + updateAssetsUser);
        Boolean ok = nftWorksService.updateBuyers("20211229113700958853606512","456","123");
        System.out.println("ok = " + ok);
    }

    @Test
    public void testUserAssetsNum() throws Exception {
        Integer u_10001 = nftUserAssetsService.findUserAssetsNum("20210521175017791641757545",null, EAssetsType.作品);
        System.out.println("u_10001 = " + u_10001);
    }
    @Test
    public void testFindUserCreateWorksCount() throws Exception {
        Integer u_10001 = nftWorksService.findUserCreateWorksCount("u_10001", null,null,null);
        System.out.println("u_10001 = " + u_10001);
    }
}
