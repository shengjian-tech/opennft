package net.shengjian.test;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.frame.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientTest {


    @Test
    public void testSearch() {

        Map<String, String> header = new HashMap<>();
        header.put("Host", "public.creditchina.gov.cn");
        header.put("Connection", "keep-alive");
        header.put("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"90\", \"Google Chrome\";v=\"90\"");
        header.put("Accept", "application/json, text/javascript, */*; q=0.01");
        header.put("sec-ch-ua-mobile", "?0");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36");
        header.put("Origin", "https://www.creditchina.gov.cn");
        header.put("Sec-Fetch-Site", "same-site");
        header.put("Sec-Fetch-Mode", "cors");
        header.put("Sec-Fetch-Dest", "empty");
        header.put("Referer", "https://www.creditchina.gov.cn/");
        header.put("Accept-Encoding", "gzip, deflate, br");
        header.put("Accept-Language", "zh,zh-CN;q=0.9");


        //营业执照
        String keyword = "914101086905876455";
        String url = "https://public.creditchina.gov.cn/private-api/catalogSearchHome?keyword=" + keyword + "&scenes=defaultScenario&tableName=credit_xyzx_tyshxydm&searchState=2&entityType=1%2C2%2C4%2C5%2C6%2C7%2C8&templateId=&page=1&pageSize=10";

        String xyurl = "https://public.creditchina.gov.cn/private-api/getTyshxydmDetailsContent?keyword=%s&scenes=defaultscenario&entityType=1&searchState=1&uuid=%s&tyshxydm=%s";


        String content = HttpClientUtils.sendHttpHeaderGet(url, header, null);
        System.out.println(content);

        Map<String, Object> data = JsonUtils.readValue(content, HashMap.class);

        System.out.println(data);

        Integer status = (Integer) data.get("status");
        if (status == null || status == 0) {
            return;
        }
        List<Map<String, Object>> list = (List<Map<String, Object>>) ((Map<String, Object>) data.get("data")).get("list");
        if (list == null) {
            return;
        }
        for (Map<String, Object> map : list) {
            String uuid = (String) map.get("uuid");
            String accurate_entity_name = (String) map.get("accurate_entity_name");
            if (StringUtils.isBlank(uuid) || StringUtils.isBlank(accurate_entity_name)) {
                continue;
            }

            String u = String.format(xyurl, accurate_entity_name, uuid, keyword);

            // punishmentStatus 是否是惩戒对象,界面显示 失信惩戒对象
            String c = HttpClientUtils.sendHttpHeaderGet(u, header, null);
            System.out.println(c);
        }


    }
}
