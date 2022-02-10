/**
 * Copyright (c) 2011-2017, fuyong (859050943@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package net.shengjian.weixin.sdk.mp;

import java.io.Serializable;
import java.util.List;

/**
 * 门店数据对象
 *
 * @author fuyong
 * json数据示例:
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
public class Poi implements Serializable {
    private static final long serialVersionUID = 4899154126408515026L;

    //base_info
    //门店基础信息字段（重要）---------------------------------------------------------------------
    //商户自己的id，用于后续审核通过收到poi_id 的通知时，做对应关系。请商户自己保证唯一识别性, 非必填
    private String sid;
    //门店名称(仅为商户名，如：国美、麦当劳，不应包含地区、地址、分店名等信息，错误示例：北京国美)不能为空，15个汉字或30个英文字符内, 必填
    private String business_name;
    //分店名称（不应包含地区信息，不应与门店名有重复，错误示例：北京王府井店）20个字以内, 必填
    private String branch_name;
    //门店所在的省份（直辖市填城市名,如：北京市）10个字以内, 必填
    private String province;
    //门店所在的城市10个字以内, 必填
    private String city;
    //门店所在地区10个字以内（东莞等没有“区”行政区划的城市，该字段可不必填写。其余城市必填。）, 必填
    private String district;
    //门店所在的详细街道地址（不要填写省市信息）, 必填
    private String address;
    //门店的电话（纯数字，区号、分机号均由“-”隔开）, 必填
    private String telephone;
    //门店的类型（不同级分类用“,”隔开，如：美食，川菜，火锅。详细分类参见附件：微信门店类目表）, 必填
    private List<String> categories;
    /**
     * 坐标类型：必填
     * 1 为火星坐标
     * 2 为sogou经纬度
     * 3 为百度经纬度
     * 4 为mapbar经纬度
     * 5 为GPS坐标
     * 6 为sogou墨卡托坐标
     */
    private Integer offset_type;
    //门店所在地理位置的经度, 必填
    private String longitude;
    //门店所在地理位置的纬度（经纬度均为火星坐标，最好选用腾讯地图标记的坐标）, 必填
    private String latitude;

    //门店服务信息字段, 均为非必填--------------------------------------------------------------------------------
    /**
     * 图片列表，url 形式，可以有多张图片，尺寸为640*340px。
     * 必须为上一接口生成的url。图片内容不允许与门店不相关，
     * 不允许为二维码、员工合照（或模特肖像）、营业执照、无门店正门的街景、地图截图、公交地铁站牌、菜单截图等
     */
    private List<PhotoUrl> photo_list;
    //推荐品，餐厅可为推荐菜；酒店为推荐套房；景点为推荐游玩景点等，针对自己行业的推荐内容200字以内
    private String recommend;
    //特色服务，如免费wifi，免费停车，送货上门等商户能提供的特色功能或服务
    private String special;
    //商户简介，主要介绍商户信息等300字以内
    private String introduction;
    //营业时间，24 小时制表示，用“-”连接，如8:00-20:00
    private String open_time;
    //人均价格，大于0 的整数
    private Integer avg_price;

    //门店获取返回数据字段-----------------------------------------------------------
    /**
     * 门店poi_id, 唯一
     */
    private String poi_id;
    /**
     * 门店是否可用状态。1 表示系统错误、2 表示审核中、3 审核通过、4 审核驳回。当该字段为1、2、4 状态时，poi_id 为空
     */
    private Integer available_state;
    /**
     * 扩展字段是否正在更新中。1 表示扩展字段正在更新中，尚未生效，不允许再次更新； 0 表示扩展字段没有在更新中或更新已生效，可以再次更新
     */
    private Integer update_status;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Integer getOffset_type() {
        return offset_type;
    }

    public void setOffset_type(Integer offset_type) {
        this.offset_type = offset_type;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public List<PhotoUrl> getPhoto_list() {
        return photo_list;
    }

    public void setPhoto_list(List<PhotoUrl> photo_list) {
        this.photo_list = photo_list;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public Integer getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(Integer avg_price) {
        this.avg_price = avg_price;
    }

    public String getPoi_id() {
        return poi_id;
    }

    public void setPoi_id(String poi_id) {
        this.poi_id = poi_id;
    }

    public Integer getAvailable_state() {
        return available_state;
    }

    public void setAvailable_state(Integer available_state) {
        this.available_state = available_state;
    }

    public Integer getUpdate_status() {
        return update_status;
    }

    public void setUpdate_status(Integer update_status) {
        this.update_status = update_status;
    }

    /**
     * 图片列表
     *
     * @author fuyong
     */
    public static class PhotoUrl {
        private String photo_url;

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }
    }

}
