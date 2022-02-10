package net.shengjian.test;

import net.shengjian.frame.util.JsonUtils;
import net.shengjian.makerone.dto.PayRequestParamDTO;
import net.shengjian.makerone.utils.PayUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @descriptions:
 * @author: YSK
 * @date: 2022/1/11 15:13
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayUtilTest {

    @Resource
    private PayUtil payUtil;

    @Test
    public void testPlaceAnOrderByJsApi() throws Exception {
        String prepay_id = payUtil.placeAnOrderByJsApi("1"
                , "test-测试商品"
                , "o84I85qQTBOFUHkN68PV-VnB0Xfs"
                , "20220110103328358697891526");
        System.out.println("prepay_id = " + prepay_id);
        PayRequestParamDTO payRequestParam = payUtil.getPayRequestParam(prepay_id);
        System.out.println(JsonUtils.writeValueAsString(payRequestParam));
    }
}
