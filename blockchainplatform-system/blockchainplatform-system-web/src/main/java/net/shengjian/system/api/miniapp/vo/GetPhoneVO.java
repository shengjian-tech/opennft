package net.shengjian.system.api.miniapp.vo;

/**
 * @descriptions: 参数对象
 * @author: YSK
 * @date: 2021/4/21 13:59
 * @version: 1.0
 */
public class GetPhoneVO {
    /**
     * 加密数据
     *
     * @required
     */
    private String encryptedData;
    /**
     * @required
     */
    private String iv;

    /**
     * 加密数据
     */
    public String getEncryptedData() {
        return encryptedData;
    }

    /**
     * 加密数据
     */
    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}
