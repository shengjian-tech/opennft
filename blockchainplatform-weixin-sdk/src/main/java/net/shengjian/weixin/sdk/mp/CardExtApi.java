package net.shengjian.weixin.sdk.mp;

import net.shengjian.frame.util.HttpClientUtils;
import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.WxConsts;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;

/**
 * 特殊卡券接口-特殊票券
 * <p>
 * https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Special_ticket.html
 */
public class CardExtApi {
    private static String meetingTicketUpdateUserUrl = WxConsts.mpapiurl + "/card/meetingticket/updateuser?access_token=";
    private static String movieTicketUpdateUserUrl = WxConsts.mpapiurl + "/card/movieticket/updateuser?access_token=";
    private static String checkinBoardingpassUrl = WxConsts.mpapiurl + "/card/boardingpass/checkin?access_token=";

    /**
     * 更新会议门票
     *
     * @param wxmpconfig
     * @param jsonStr    JSON数据
     * @return {ApiResult}
     */
    public static ApiResult UpdateMeetingTicketUser(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(meetingTicketUpdateUserUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 更新电影票
     *
     * @param wxmpconfig
     * @param jsonStr    JSON数据
     * @return {ApiResult}
     */
    public static ApiResult UpdateMovieTicketUser(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(movieTicketUpdateUserUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 更新飞机票信息接口
     *
     * @param wxmpconfig
     * @param jsonStr    JSON数据
     * @return {ApiResult}
     */
    public static ApiResult checkinBoardingpass(IWxMpConfig wxmpconfig, String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(checkinBoardingpassUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }
}
