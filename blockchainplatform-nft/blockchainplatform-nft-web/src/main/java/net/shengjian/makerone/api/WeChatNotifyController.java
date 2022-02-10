package net.shengjian.makerone.api;

import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.SecUtils;
import net.shengjian.makerone.constant.CommonConst;
import net.shengjian.makerone.constant.MessageConst;
import net.shengjian.makerone.enums.EPayPlat;
import net.shengjian.makerone.enums.EPayState;
import net.shengjian.makerone.service.INftOrderService;
import net.shengjian.system.base.BaseController;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;
import net.shengjian.weixin.sdk.common.wxconfig.IWxPayConfig;
import net.shengjian.weixin.service.IWxMpConfigService;
import net.shengjian.weixin.service.IWxPayConfigService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 微信回调接口
 * @descriptions: 微信回调接口
 * @author: YSK
 * @date: 2022/1/5 11:42
 * @version: 1.0
 */
@RestController
@RequestMapping(value="/api/nft/wechat", method = RequestMethod.POST)
public class WeChatNotifyController extends BaseController {

    @Resource
    private IWxPayConfigService wxPayConfigService;

    @Resource
    private IWxMpConfigService wxMpConfigService;

    @Resource
    private INftOrderService nftOrderService;
    /**
     * 微信支付后的回调地址
     * @param request 请求体
     * @param response 响应体
     */
    @PostMapping(value="pay/notify")
    public void payNotify(HttpServletRequest request, HttpServletResponse response){
        System.out.println(" -----微信回调请求----- ");
        try {
            ServletInputStream inputStream = request.getInputStream();
            Map map = JsonUtils.readValue(inputStream, Map.class);
            IWxPayConfig wxPayConfig = wxPayConfigService.findWxPayConfigById(CommonConst.SITE);
            if(wxPayConfig==null){
                throw new RuntimeException("未找到微信支付配置! wxPayConfig is null!");
            }
            Object resource = map.get("resource");
            if(resource==null){
                throw new RuntimeException("resource is null!");
            }
            Map resourceMap = (Map)resource;
            String nonce = resourceMap.get("nonce").toString();
            String associated_data = resourceMap.get("associated_data").toString();
            String ciphertext = resourceMap.get("ciphertext").toString();
            //解密数据
            AesUtil aesUtil = new AesUtil(wxPayConfig.getApiV3Key().getBytes(StandardCharsets.UTF_8));
            String decryptToString = aesUtil.decryptToString(associated_data.getBytes(StandardCharsets.UTF_8)
                    , nonce.getBytes(StandardCharsets.UTF_8), ciphertext);

            Map<String,String> dataMap = JsonUtils.readValue(decryptToString, Map.class);
            if(dataMap==null){
                throw new RuntimeException("解密数据转换map异常!");
            }
            System.out.println(dataMap);
            String tradeState = dataMap.get("trade_state");//交易状态
            String orderId = dataMap.get("out_trade_no");//商户订单号
            String transactionId = dataMap.get("transaction_id");//微信支付订单号
            String successTime = dataMap.get("success_time");//支付完成时间 yyyyMMddHHmmss
            String attach = dataMap.get("attach");//附加的数据
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
            Date txTime = df.parse(successTime);
            if(txTime==null){
                throw new RuntimeException("success_time format err!");
            }
            if("SUCCESS".equals(tradeState)){
                Boolean ok = nftOrderService.updatePayAfter(orderId, EPayPlat.微信支付, txTime, EPayState.已支付, transactionId,attach);
                if(!ok){
                    throw new RuntimeException("订单状态更新异常!");
                }
            }else{
                throw new RuntimeException("tradeState not is SUCCESS!");
            }
            response.setStatus(200);
        } catch (Exception e){
            response.setStatus(500);
            try {
                response.getWriter().write(e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            logger.error(e.getMessage(),e);
        }
    }

    /**
     * 签名链接
     * @param url 链接
     * @return 签名后的信息
     */
    @PostMapping(value="sign/url")
    public ReturnDatas<Map<String,String>> signUrl(String url){
        ReturnDatas<Map<String,String>> rd = ReturnDatas.getSuccessReturnDatas();
        try {
            IWxMpConfig wxMpConfigById = wxMpConfigService.findWxMpConfigById(CommonConst.SITE);
            Map<String, String> mpJsApiParam = wxMpConfigService.findMpJsApiParam(wxMpConfigById, url);
            if(mpJsApiParam==null){
                rd.setMessage("签名失败!");
            }else{
                rd.setResult(mpJsApiParam);
                rd.setMessage("签名成功!");
            }
        }catch (Exception e){
            rd.setMessage(MessageConst.UNKNOWN_ERR);
            rd.setStatus(ReturnDatas.ERROR);
        }
        return rd;
    }
}
