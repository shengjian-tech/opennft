import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import net.shengjian.frame.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class SendSms {
    public static void Test1() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "*", "*");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        Map templateParam = new HashMap();
        //templateParam.put("name", "a");
        //templateParam.put("policyName","b");
        templateParam.put("code","2348");
        request.putQueryParameter("PhoneNumbers", "18595378756");
        request.putQueryParameter("SignName", "盛见网络");
        request.putQueryParameter("TemplateCode", "SMS_206295071");
        request.putQueryParameter("TemplateParam", JsonUtils.writeValueAsString(templateParam));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}