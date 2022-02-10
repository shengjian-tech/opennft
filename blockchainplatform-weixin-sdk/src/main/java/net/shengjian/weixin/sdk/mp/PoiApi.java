/**
 * Copyright (c) 2011-2017, fuyong (859050943@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package net.shengjian.weixin.sdk.mp;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信门店接口
 * 门店poiid体系已做改造，poiid自创建后立刻分配，不再受审核影响发生变化。
 * <p>
 * https://developers.weixin.qq.com/doc/offiaccount/WeChat_Stores/WeChat_Store_Interface.html
 */
public class PoiApi {
    private static String addPoi = WxConsts.mpapiurl + "/cgi-bin/poi/addpoi?access_token=";
    private static String getPoi = WxConsts.mpapiurl + "/cgi-bin/poi/getpoi?access_token=";
    private static String getPoiList = WxConsts.mpapiurl + "/cgi-bin/poi/getpoilist?access_token=";
    private static String updatePoi = WxConsts.mpapiurl + "/cgi-bin/poi/updatepoi?access_token=";
    private static String delPoi = WxConsts.mpapiurl + "/cgi-bin/poi/delpoi?access_token=";
    private static String getWxCategory = WxConsts.mpapiurl + "/cgi-bin/poi/getwxcategory?access_token=";

    /**
     * 创建门店
     *
     * @param jsonStr json字符串
     * @return ApiResult
     * 发送json数据示例:
     * {
     * "business" : {
     * "base_info" : {
     * "address" : "门店所在的详细街道地址（不要填写省市信息）：不超过80个字",
     * "avg_price" : 35,
     * "branch_name" : "不超过10个字，不能含有括号和特殊字符",
     * "business_name" : "15个汉字或30个英文字符内",
     * "categories" : [ "美食,小吃快餐" ],
     * "city" : "不超过30个字",
     * "district" : "不超过10个字",
     * "introduction" : "不超过300字。麦当劳是全球大型跨国连锁餐厅，1940 年创立于美国，在世界上大约拥有3 万间分店。\n\n主要售卖汉堡包，以及薯条、炸鸡、汽水、冰品、沙拉、 水果等快餐食品",
     * "latitude" : 25.0974860,
     * "longitude" : 115.323750,
     * "offset_type" : 1,
     * "open_time" : "8:00-20:00",
     * "photo_list" : [
     * {
     * "photo_url" : "https:// 不超过20张.com"
     * },
     * {
     * "photo_url" : "https://XXX.com"
     * }
     * ],
     * "province" : "不超过10个字",
     * "recommend" : "不超过200字。麦辣鸡腿堡套餐，麦乐鸡，全家桶",
     * "sid" : "33788392",
     * "special" : "不超过200字。免费wifi，外卖服务",
     * "telephone" : "不超53个字符（不可以出现文字）"
     * }
     * }
     * }
     */
    public static ApiResult addPoi(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(addPoi + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 创建门店
     *
     * @param poi 门店数据实体
     * @return ApiResult
     */
    public static ApiResult addPoi(IWxMpConfig wxmpconfig, Poi poi) {
        Map<String, Map<String, Poi>> business = new HashMap<String, Map<String, Poi>>();
        Map<String, Poi> base_info = new HashMap<String, Poi>();

        base_info.put("base_info", poi);
        business.put("business", base_info);

        return addPoi(wxmpconfig, JsonUtils.writeValueAsString(business));
    }

    /**
     * 获取单个门店
     *
     * @param poiId 门店poi_id
     * @return ApiResult
     */
    public static ApiResult getPoi(IWxMpConfig wxmpconfig, String poiId) {
        String url = getPoi + wxmpconfig.getAccessToken();

        Map<String, String> poi_id = new HashMap<String, String>();
        poi_id.put("poi_id", poiId);

        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(poi_id));
        return new ApiResult(jsonResult);
    }

    /**
     * 获取门店列表
     *
     * @param begin 开始位置，0 即为从第一条开始查询, 必填
     * @param limit 返回数据条数，最大允许50，默认为20, 必填
     * @return ApiResult
     * 返回数据示例:
     * {
     * "business_list" : [
     * {
     * "base_info" : {
     * "address" : "艺苑路11号",
     * "available_state" : 3,
     * "avg_price" : 35,
     * "branch_name" : "艺苑路店",
     * "business_name" : "麦当劳",
     * "categories" : [ "美食,快餐小吃" ],
     * "city" : "广州市",
     * "district" : "海珠区",
     * "introduction" : "麦当劳是全球大型跨国连锁餐厅，1940 年创立于美国，在世界上大约拥有3 万间分店。主要售卖汉堡包，以及薯条、炸鸡、汽水、冰品、沙拉、水果等快餐食品",
     * "latitude" : 25.0974860,
     * "longitude" : 115.323750,
     * "offset_type" : 1,
     * "open_time" : "8:00-20:00",
     * "photo_list" : [
     * {
     * "photo_url" : "http: ...."
     * }
     * ],
     * "poi_id" : "285633617",
     * "province" : "广东省",
     * "recommend" : "麦辣鸡腿堡套餐，麦乐鸡，全家桶",
     * "sid" : "101",
     * "special" : "免费wifi，外卖服务",
     * "telephone" : "020-12345678",
     * "update_status" : 0
     * }
     * },
     * {
     * "base_info" : {
     * "address" : "北京路12号",
     * "available_state" : 4,
     * "avg_price" : 35,
     * "branch_name" : "北京路店",
     * "business_name" : "麦当劳",
     * "categories" : [ "美食,快餐小吃" ],
     * "city" : "广州市",
     * "district" : "越秀区",
     * "introduction" : "麦当劳是全球大型跨国连锁餐厅，1940 年创立于美国，在世界上大约拥有3 万间分店。主要售卖汉堡包，以及薯条、炸鸡、汽水、冰品、沙拉、水果等快餐食品",
     * "latitude" : 25.0923860,
     * "longitude" : 115.32350,
     * "offset_type" : 1,
     * "open_time" : "8:00-20:00",
     * "photo_list" : [
     * {
     * "photo_url" : "http: ...."
     * }
     * ],
     * "poi_id" : "285633618",
     * "province" : "广东省",
     * "recommend" : "麦辣鸡腿堡套餐，麦乐鸡，全家桶",
     * "sid" : "101",
     * "special" : "免费wifi，外卖服务",
     * "telephone" : "020-12345689",
     * "update_status" : 0
     * }
     * },
     * {
     * "base_info" : {
     * "address" : "迎龙路122号",
     * "available_state" : 2,
     * "avg_price" : 35,
     * "branch_name" : "龙洞店",
     * "business_name" : "麦当劳",
     * "categories" : [ "美食,快餐小吃" ],
     * "city" : "广州市",
     * "district" : "天河区",
     * "introduction" : "麦当劳是全球大型跨国连锁餐厅，1940 年创立于美国，在世界上大约拥有3 万间分店。主要售卖汉堡包，以及薯条、炸鸡、汽水、冰品、沙拉、水果等快餐食品",
     * "latitude" : 25.0566860,
     * "longitude" : 115.323450,
     * "offset_type" : 1,
     * "open_time" : "8:00-20:00",
     * "photo_list" : [
     * {
     * "photo_url" : "http: ...."
     * }
     * ],
     * "poi_id" : "285633619",
     * "province" : "广东省",
     * "recommend" : "麦辣鸡腿堡套餐，麦乐鸡，全家桶",
     * "sid" : "101",
     * "special" : "免费wifi，外卖服务",
     * "telephone" : "020-12345659",
     * "update_status" : 0
     * }
     * }
     * ],
     * "errcode" : 0,
     * "errmsg" : "ok",
     * "total_count" : "3"
     * }
     */
    public static ApiResult getPoiList(IWxMpConfig wxmpconfig, int begin, int limit) {
        String url = getPoiList + wxmpconfig.getAccessToken();

        Map<String, Integer> poiListPara = new HashMap<String, Integer>();
        poiListPara.put("begin", begin);
        poiListPara.put("limit", limit);

        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(poiListPara));
        return new ApiResult(jsonResult);
    }

    /**
     * 修改门店信息
     * 商户可以通过该接口，修改门店的服务信息，包括：sid、图片列表、营业时间、推荐、特色服务、简介、人均价格、电话8个字段（名称、坐标、地址等不可修改）修改后需要人工审核。
     * <p>
     * 特别注意：以上8个字段，若有填写内容则为覆盖更新，若无内容则视为不修改，维持原有内容。
     * photo_list 字段为全列表覆盖，若需要增加图片，需将之前图片同样放入list 中，
     * 在其后增加新增图片。如：已有A、B、C 三张图片，又要增加D、E 两张图，则需要调用该接口，
     * photo_list 传入A、B、C、D、E 五张图片的链接。
     *
     * @param jsonStr json字符串
     * @return ApiResult
     * 发送json数据示例:
     * {
     * "business " : {
     * "base_info" : {
     * "avg_price" : 35,
     * "introduction" : "麦当劳是全球大型跨国连锁餐厅，1940 年创立于美国，在世界上大约拥有3 万间分店。主要售卖汉堡包，以及薯条、炸鸡、汽水、冰品、沙拉、水果等快餐食品",
     * "open_time" : "8:00-20:00",
     * "photo_list" : [
     * {
     * "photo_url" : "https:// XXX.com"
     * },
     * {
     * "photo_url" : "https://XXX.com"
     * }
     * ],
     * "poi_id " : "271864249",
     * "recommend" : "麦辣鸡腿堡套餐，麦乐鸡，全家桶",
     * "sid" : "A00001",
     * "special" : "免费wifi，外卖服务",
     * "telephone " : "020-12345678"
     * }
     * }
     * }
     */
    public static ApiResult updatePoi(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(updatePoi + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 修改门店信息
     * 商户可以通过该接口，修改门店的服务信息，包括：sid、图片列表、营业时间、推荐、特色服务、简介、人均价格、电话8个字段（名称、坐标、地址等不可修改）修改后需要人工审核。
     *
     * @param poi 门店数据实体
     * @return ApiResult
     */
    public static ApiResult updatePoi(IWxMpConfig wxmpconfig, Poi poi) {
        Map<String, Map<String, Poi>> business = new HashMap<String, Map<String, Poi>>();
        Map<String, Poi> base_info = new HashMap<String, Poi>();

        base_info.put("base_info", poi);
        business.put("business", base_info);

        return addPoi(wxmpconfig, JsonUtils.writeValueAsString(business));
    }

    /**
     * 删除单个门店
     *
     * @param poiId 门店poi_id
     * @return ApiResult
     */
    public static ApiResult delPoi(IWxMpConfig wxmpconfig, String poiId) {
        String url = delPoi + wxmpconfig.getAccessToken();

        Map<String, String> poi_id = new HashMap<String, String>();
        poi_id.put("poi_id", poiId);

        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(poi_id));
        return new ApiResult(jsonResult);
    }

    /**
     * 获取微信门店类目表
     * 类目名称接口是为商户提供自己门店类型信息的接口。门店类目定位的越规范，能够精准的吸引更多用户，提高曝光率。
     *
     * @return ApiResult
     */
    public static ApiResult getCategory(IWxMpConfig wxmpconfig) {
        String apiurl = getWxCategory + wxmpconfig.getAccessToken();
        return new ApiResult(HttpClientUtils.sendHttpGet(apiurl));
    }
}
