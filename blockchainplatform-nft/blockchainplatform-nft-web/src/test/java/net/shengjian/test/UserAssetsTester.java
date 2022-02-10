package net.shengjian.test;

import net.shengjian.makerone.enums.EChainPlat;
import net.shengjian.makerone.service.INftUserAssetsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @descriptions:
 * @author: YSK
 * @date: 2022/1/23 14:25
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAssetsTester {

    @Resource
    private INftUserAssetsService nftUserAssetsService;
    @Test
    public void test1() throws Exception {
        Boolean ok = nftUserAssetsService.updateTransferAssets(EChainPlat.百度超级链, "20220110163739394089049276", "cRsoDDnDX1NjhzJtNKLS3GHudBsgyRouQ", "20220121154737521804541326", "");
        System.out.println("ok = " + ok);
    }
}
