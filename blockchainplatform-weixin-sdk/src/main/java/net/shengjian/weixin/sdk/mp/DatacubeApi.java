package net.shengjian.weixin.sdk.mp;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.frame.util.JsonUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据统计接口
 * <p>
 * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shake_Nearby/Analytics/Using_devices_as_a_dimension_for_the_data_statistics_interface.html
 */
public class DatacubeApi {

    private static String getUserSummaryUrl = WxConsts.mpapiurl + "/datacube/getusersummary?access_token=";

    private static String getUserCumulateUrl = WxConsts.mpapiurl + "/datacube/getusercumulate?access_token=";
    private static String getArticleSummaryUrl = WxConsts.mpapiurl + "/datacube/getarticlesummary?access_token=";
    private static String getArticlEtotalURL = WxConsts.mpapiurl + "/datacube/getarticletotal?access_token=";
    private static String getUserReadURL = WxConsts.mpapiurl + "/datacube/getuserread?access_token=";
    private static String getUserReadHourURL = WxConsts.mpapiurl + "/datacube/getuserreadhour?access_token=";
    private static String getUserShareURL = WxConsts.mpapiurl + "/datacube/getusershare?access_token=";
    private static String getUserShareHourURL = WxConsts.mpapiurl + "/datacube/getusersharehour?access_token=";
    private static String getUpStreamMsgURL = WxConsts.mpapiurl + "/datacube/getupstreammsg?access_token=";
    private static String getUpStreamMsgHourURL = WxConsts.mpapiurl + "/datacube/getupstreammsghour?access_token=";
    private static String getUpStreamMsgWeekMsgURL = WxConsts.mpapiurl + "/datacube/getupstreammsgweek?access_token=";
    private static String getUpStreamMsgMonthURL = WxConsts.mpapiurl + "/datacube/getupstreammsgmonth?access_token=";
    private static String getUpStreamMsgDistURL = WxConsts.mpapiurl + "/datacube/getupstreammsgdist?access_token=";
    private static String getUpStreamMsgDistWeekURL = WxConsts.mpapiurl + "/datacube/getupstreammsgdistweek?access_token=";
    private static String getUpStreamMsgDistMonthURL = WxConsts.mpapiurl + "/datacube/getupstreammsgdistmonth?access_token=";
    private static String getInterFaceSummaryURL = WxConsts.mpapiurl + "/datacube/getinterfacesummary?access_token=";
    private static String getInterFaceSummaryHourURL = WxConsts.mpapiurl + "/datacube/getinterfacesummaryhour?access_token=";
    private static String getCardBizuinInfo = WxConsts.mpapiurl + "/datacube/getcardbizuininfo?access_token=";
    private static String getCardInfo = WxConsts.mpapiurl + "/datacube/getcardcardinfo?access_token=";
    private static String getMemberCardInfo = WxConsts.mpapiurl + "/datacube/getcardmembercardinfo?access_token=";
    private static String getMemberCardDetail = WxConsts.mpapiurl + "/datacube/getcardmembercarddetail?access_token=";

    /**
     * 获取统计信息
     *
     * @param url        url链接
     * @param begin_date 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
     * @param end_date   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @return ApiResult
     */
    private static ApiResult getData(IWxMpConfig wxmpconfig, String url, String begin_date, String end_date) {
        Map<String, String> mapData = new HashMap<>();
        mapData.put("begin_date", begin_date);
        mapData.put("end_date", end_date);

        String jsonResult = HttpClientUtils.sendHttpPost(url, mapData);
        return new ApiResult(jsonResult);
    }

    /**
     * 用户分析数据接口，最大时间跨度：7天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getUserSummary(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getUserSummaryUrl + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 用户分析数据接口，最大时间跨度：7天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getUserCumulate(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getUserCumulateUrl + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 获取图文群发每日数据，最大跨度1天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getArticleSummary(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getArticleSummaryUrl + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 获取图文群发总数据，最大跨度1天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getArticlEtotal(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getArticlEtotalURL + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 获取图文统计数据，最大跨度3天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getUserRead(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getUserReadURL + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 获取图文统计分时数据，最大跨度1天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getUserReadHour(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getUserReadHourURL + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 获取图文分享转发数据，最大跨度7天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getUserShare(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getUserShareURL + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 获取图文分享转发分时数据，最大跨度1天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getUserShareHour(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getUserShareHourURL + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 获取消息发送概况数据，最大跨度7天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getUpStreamMsg(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getUpStreamMsgURL + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 获取消息分送分时数据，最大跨度1天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getUpStreamMsgHour(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getUpStreamMsgHourURL + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 获取消息发送周数据，最大跨度30天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getUpStreamMsgWeekMsg(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getUpStreamMsgWeekMsgURL + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 获取消息发送月数据，最大跨度30天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getUpStreamMsgMonth(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getUpStreamMsgMonthURL + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 获取消息发送分布数据，最大跨度15天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getUpStreamMsgDist(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getUpStreamMsgDistURL + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 获取消息发送分布周数据，最大跨度30天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getUpStreamMsgDistWeek(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getUpStreamMsgDistWeekURL + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 获取消息发送分布月数据，最大跨度30天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getUpStreamMsgDistMonth(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getUpStreamMsgDistMonthURL + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 获取接口分析数据，最大跨度30天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getInterFaceSummary(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getInterFaceSummaryURL + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 获取接口分析分时数据，最大跨度1天
     *
     * @param begin_date 获取数据的起始日期
     * @param end_date   获取数据的结束日期
     * @return ApiResult
     */
    public static ApiResult getInterFaceSummaryHour(IWxMpConfig wxmpconfig, String begin_date, String end_date) {
        String url = getInterFaceSummaryHourURL + wxmpconfig.getAccessToken();
        return getData(wxmpconfig, url, begin_date, end_date);
    }

    /**
     * 拉取卡券概况数据接口
     *
     * @param beginDate  获取数据的起始日期
     * @param endDate    获取数据的结束日期
     * @param condSource 卡券来源，0为公众平台创建的卡券数据、1是API创建的卡券数据
     * @return ApiResult
     */
    public static ApiResult getCardBizuinInfo(IWxMpConfig wxmpconfig, String beginDate, String endDate, int condSource) {
        String url = getCardBizuinInfo + wxmpconfig.getAccessToken();
        Map<String, Object> data = new HashMap<>();
        data.put("begin_date", beginDate);
        data.put("end_date", endDate);
        data.put("cond_source", condSource);
        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 获取免费券数据接口
     *
     * @param beginDate  获取数据的起始日期
     * @param endDate    获取数据的结束日期
     * @param condSource 卡券来源，0为公众平台创建的卡券数据、1是API创建的卡券数据
     * @return ApiResult
     */
    public static ApiResult getCardInfo(IWxMpConfig wxmpconfig, String beginDate, String endDate, int condSource) {
        return getCardInfo(wxmpconfig, beginDate, endDate, condSource, null);
    }

    /**
     * 获取免费券数据接口
     *
     * @param beginDate  获取数据的起始日期
     * @param endDate    获取数据的结束日期
     * @param condSource 卡券来源，0为公众平台创建的卡券数据、1是API创建的卡券数据
     * @param cardId     卡券ID。填写后，指定拉出该卡券的相关数据。
     * @return ApiResult
     */
    public static ApiResult getCardInfo(IWxMpConfig wxmpconfig, String beginDate, String endDate, int condSource, String cardId) {
        String url = getCardInfo + wxmpconfig.getAccessToken();
        Map<String, Object> data = new HashMap<>();
        data.put("begin_date", beginDate);
        data.put("end_date", endDate)
        ;
        data.put("cond_source", condSource);
        if (StringUtils.isNotBlank(cardId)) {
            data.put("card_id", cardId);
        }
        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 拉取会员卡概况数据接口
     *
     * @param beginDate  获取数据的起始日期
     * @param endDate    获取数据的结束日期
     * @param condSource 卡券来源，0为公众平台创建的卡券数据、1是API创建的卡券数据
     * @return ApiResult
     */
    public static ApiResult getMemberCardInfo(IWxMpConfig wxmpconfig, String beginDate, String endDate, int condSource) {
        String url = getMemberCardInfo + wxmpconfig.getAccessToken();
        Map<String, Object> data = new HashMap<>();
        data.put("begin_date", beginDate);
        data.put("end_date", endDate)
        ;
        data.put("cond_source", condSource);
        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 拉取单张会员卡数据接口
     *
     * @param beginDate 获取数据的起始日期
     * @param endDate   获取数据的结束日期
     * @param cardId    "card_id":"xxxxxxxxxxxxxxxx" 卡券id
     * @return ApiResult
     */
    public static ApiResult getMemberCardDetail(IWxMpConfig wxmpconfig, String beginDate, String endDate, String cardId) {
        String url = getMemberCardDetail + wxmpconfig.getAccessToken();
        Map<String, Object> data = new HashMap<>();
        data.put("begin_date", beginDate);
        data.put("end_date", endDate)
        ;
        data.put("card_id", cardId);
        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }
}
