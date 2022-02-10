package net.shengjian.weixin.sdk.common;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.Security;
import java.util.Arrays;

public class WxCryptUtils {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    /**
     * 串接arr参数，生成sha1 digest
     */
    public static String genSHA1(String... arr) {
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (String a : arr) {
            sb.append(a);
        }
        return DigestUtils.sha1Hex(sb.toString());
    }

    /**
     * 用&串接arr参数，生成sha1 digest
     */
    public static String genWithAmpleSHA1(String... arr) {
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            String a = arr[i];
            sb.append(a);
            if (i != arr.length - 1) {
                sb.append('&');
            }
        }
        return DigestUtils.sha1Hex(sb.toString());
    }

    /**
     * AES解密.
     *
     * @param sessionKey    session_key
     * @param encryptedData 消息密文
     * @param ivStr         iv字符串
     */
    public static String decrypt(String sessionKey, String encryptedData, String ivStr) {


        byte[] keyBytes = Base64.decodeBase64(sessionKey.getBytes(UTF_8));

        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }

        Security.addProvider(new BouncyCastleProvider());
        Key key = new SecretKeySpec(keyBytes, "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(Base64.decodeBase64(ivStr.getBytes(UTF_8))));
            return new String(cipher.doFinal(Base64.decodeBase64(encryptedData.getBytes(UTF_8))), UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("AES解密失败！", e);
        }
    }

    /**
     * 验证用户信息完整性.
     *
     * @param sessionKey 会话密钥
     * @param rawData    微信用户基本信息
     * @param signature  数据签名
     * @return .
     */
    public boolean checkUserInfo(String sessionKey, String rawData, String signature) {
        final String generatedSignature = DigestUtils.sha1Hex(rawData + sessionKey);
        return generatedSignature.equals(signature);
    }


}
