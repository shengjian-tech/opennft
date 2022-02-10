package net.shengjian.weixin.sdk.miniapp;

import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMiniappConfig;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 小程序码的接口
 * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/qr-code/wxacode.getUnlimited.html
 */

public class MiniappQrcodeApi {
    private static final Logger logger = LoggerFactory.getLogger(MiniappQrcodeApi.class);
    private static String getUnlimitedUrl = WxConsts.mpapiurl + "/wxa/getwxacodeunlimit?access_token=";

    private MiniappQrcodeApi() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    public static ApiResult getUnlimited(IWxMiniappConfig config, MiniappQrcode miniappQrcode) throws Exception {

        String apiurl = getUnlimitedUrl + config.getAccessToken();


        OutputStream os = null;
        HttpEntity entity = null;
        CloseableHttpResponse response = null;

        try {

            HttpPost httpPost = new HttpPost(apiurl);
            httpPost.setEntity(new StringEntity(JsonUtils.writeValueAsString(miniappQrcode.getQrCodeMap())));
            response = HttpClientUtils.getHttpClient().execute(httpPost);
            entity = response.getEntity();
            Header[] contentTypeHeader = response.getHeaders("Content-Type");
            if (contentTypeHeader != null && contentTypeHeader.length > 0
                    && ContentType.APPLICATION_JSON.getMimeType()
                    .equals(ContentType.parse(contentTypeHeader[0].getValue()).getMimeType())) { //如果是json格式
                String responseContent = EntityUtils.toString(entity, "UTF-8");
                logger.error("getUnlimited 返回错误:" + responseContent + ",config是:" + JsonUtils.writeValueAsString(config));
                return new ApiResult(responseContent);
            } else {
                byte[] byteArray = EntityUtils.toByteArray(entity);
                String dirpath = GlobalStatic.rootDir + "/upload/miniappqrcode";
                File dir = new File(GlobalStatic.rootDir + "/upload/miniappqrcode");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String filePath = dirpath + "/" + UUID.randomUUID().toString() + ".jpg";
                File file = new File(filePath);
                os = new BufferedOutputStream(new FileOutputStream(file));
                os.write(byteArray);
                os.flush();
                ApiResult apiResult = new ApiResult();
                apiResult.setFile(file);
                return apiResult;
            }


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //return new ApiResult();
            return null;
        } finally {

            if (os != null) {
                os.close();
            }

            // 关闭连接,释放资源
            if (entity != null) {
                EntityUtils.consumeQuietly(entity); // 会自动释放连接
            }

            if (response != null) {
                response.close();
            }

        }

    }


}
