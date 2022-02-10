package net.shengjian.weixin.sdk.pay;

public class WXPayReport {

    /**
     * 布尔变量使用int。0为false， 1为true。
     */

    // 基本信息
    private String version = "v1";
    private String sdk = WXPayConstants.WXPAYSDK_VERSION;

    private String uuid;  // 交易的标识
    private long timestamp;   // 上报时的时间戳，单位秒
    private long elapsedTimeMillis; // 耗时，单位 毫秒

    // 针对主域名
    private String firstDomain = "api.mch.weixin.qq.com";  // 第1次请求的域名
    private boolean primaryDomain = true; //是否主域名
    private int firstConnectTimeoutMillis = 5000;  // 第1次请求设置的连接超时时间，单位 毫秒
    private int firstReadTimeoutMillis = 5000;  // 第1次请求设置的读写超时时间，单位 毫秒
    private int firstHasDnsError = 0;  // 第1次请求是否出现dns问题
    private int firstHasConnectTimeout = 0; // 第1次请求是否出现连接超时
    private int firstHasReadTimeout = 0; // 第1次请求是否出现连接超时

    public WXPayReport() {
        this.timestamp = WXPayUtil.getCurrentTimestamp();
    }

    public WXPayReport(String uuid, long timestamp, long elapsedTimeMillis, String firstDomain, boolean primaryDomain, int firstConnectTimeoutMillis, int firstReadTimeoutMillis, boolean firstHasDnsError, boolean firstHasConnectTimeout, boolean firstHasReadTimeout) {
        this.uuid = uuid;
        this.timestamp = timestamp;
        this.elapsedTimeMillis = elapsedTimeMillis;
        this.firstDomain = firstDomain;
        this.primaryDomain = primaryDomain;
        this.firstConnectTimeoutMillis = firstConnectTimeoutMillis;
        this.firstReadTimeoutMillis = firstReadTimeoutMillis;
        this.firstHasDnsError = firstHasDnsError ? 1 : 0;
        this.firstHasConnectTimeout = firstHasConnectTimeout ? 1 : 0;
        this.firstHasReadTimeout = firstHasReadTimeout ? 1 : 0;
    }


    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setElapsedTimeMillis(long elapsedTimeMillis) {
        this.elapsedTimeMillis = elapsedTimeMillis;
    }

    public void setFirstHasDnsError(boolean firstHasDnsError) {
        this.firstHasDnsError = firstHasDnsError ? 1 : 0;
    }

    public void setFirstHasConnectTimeout(boolean firstHasConnectTimeout) {
        this.firstHasConnectTimeout = firstHasConnectTimeout ? 1 : 0;
    }

    public void setFirstHasReadTimeout(boolean firstHasReadTimeout) {
        this.firstHasReadTimeout = firstHasReadTimeout ? 1 : 0;
    }


    @Override
    public String toString() {
        return "ReportInfo{" +
                "version='" + version + '\'' +
                ", sdk='" + sdk + '\'' +
                ", uuid='" + uuid + '\'' +
                ", timestamp=" + timestamp +
                ", elapsedTimeMillis=" + elapsedTimeMillis +
                ", firstDomain='" + firstDomain + '\'' +
                ", primaryDomain=" + primaryDomain +
                ", firstConnectTimeoutMillis=" + firstConnectTimeoutMillis +
                ", firstReadTimeoutMillis=" + firstReadTimeoutMillis +
                ", firstHasDnsError=" + firstHasDnsError +
                ", firstHasConnectTimeout=" + firstHasConnectTimeout +
                ", firstHasReadTimeout=" + firstHasReadTimeout +
                '}';
    }

    /**
     * 转换成 csv 格式
     *
     * @return
     */
    public String toLineString(String key) {
        String separator = ",";
        Object[] objects = new Object[]{
                version, sdk, uuid, timestamp, elapsedTimeMillis,
                firstDomain, primaryDomain, firstConnectTimeoutMillis, firstReadTimeoutMillis,
                firstHasDnsError, firstHasConnectTimeout, firstHasReadTimeout
        };
        StringBuffer sb = new StringBuffer();
        for (Object obj : objects) {
            sb.append(obj).append(separator);
        }
        try {
            String sign = WXPayUtil.HMACSHA256(sb.toString(), key);
            sb.append(sign);
            return sb.toString();
        } catch (Exception ex) {
            return null;
        }

    }

}
