package net.shengjian.frame.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 加密的工具类
 *
 * @author caomei
 */
public class SecUtils {

    private static final Logger logger = LoggerFactory.getLogger(SecUtils.class);
    /**
     * RSA最大加密明文大小,有11位被默认占用
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    private SecUtils() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    /**
     * MD5加密(32位)
     *
     * @param instr 要加密的字符串
     * @return 返回加密后的字符串
     */
    public static String encoderByMd5With32Bit(String instr) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            if (instr != null && !"".equals(instr)) {
                byte[] strTemp = instr.getBytes();
                // MD5计算方法
                MessageDigest mdTemp = MessageDigest.getInstance("MD5");
                mdTemp.update(strTemp);
                byte[] md = mdTemp.digest();
                int j = md.length;
                char[] str = new char[j * 2];
                int k = 0;
                for (int i = 0; i < j; i++) {
                    byte byte0 = md[i];
                    str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                    str[k++] = hexDigits[byte0 & 0xf];
                }
                return new String(str);
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取随机的UUID字符串
     *
     * @return String
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Base64加密
     *
     * @param str 要加密的字符串
     * @return 返回加密后的字符串
     */
    public static String encoderByBase64(String str) {
        return encoderByBase64(str.getBytes());
    }

    /**
     * Base64加密
     *
     * @param data 要加密的字符串
     * @return 返回加密后的字符串
     */
    public static String encoderByBase64(byte[] data) {
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(data);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Base64解密
     *
     * @param str 要解密的字符串
     * @return 返回解密后的字符串
     */
    public static byte[] decoderByteByBase64(String str) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            return decoder.decode(str);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Base64解密
     *
     * @param str 要解密的字符串
     * @return 返回解密后的字符串
     */
    public static String decoderByBase64(String str) {
        try {
            byte[] decoderByBase64 = decoderByteByBase64(str);
            if (decoderByBase64 == null) {
                return null;
            }
            return new String(decoderByBase64);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * RSA私钥加密,公钥解密.---加密,返回BASE64的字符串
     *
     * @param message 加密的字符串
     * @return 返回BASE64的字符串
     */
    public static String encoderByRSAPrivateKey(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        try {

            if (privateKey == null) {
                initRSA();
            }

            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance(GlobalStatic.provider);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(GlobalStatic.provider);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            byte[] data = message.getBytes();
            byte[] result = rsaByteResult(data, MAX_ENCRYPT_BLOCK, cipher);
            if (result == null) {
                return null;
            }
            return encoderByBase64(result);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * RSA私钥加密,公钥解密.---解密,返回原始字符串,message是BASE64的字符串
     *
     * @param message BASE64的字符串
     * @return 解密好的字符串
     */
    public static String decoderByRSAPublicKey(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }

        try {

            if (publicKey == null) {
                initRSA();
            }

            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance(GlobalStatic.provider);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(GlobalStatic.provider);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            byte[] data = decoderByteByBase64(message);
            byte[] result = rsaByteResult(data, MAX_DECRYPT_BLOCK, cipher);

            if (result == null) {
                return null;
            }
            return new String(result);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * RSA分段处理byte数组
     *
     * @param data
     * @param block
     * @param cipher
     * @return
     */
    private static byte[] rsaByteResult(byte[] data, int block, Cipher cipher) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            int inputLen = data.length;

            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段处理
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > block) {
                    cache = cipher.doFinal(data, offSet, block);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * block;
            }
            byte[] result = out.toByteArray();
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 初始化RSA证书
     *
     * @throws Exception
     */
    public static void initRSA() throws Exception {

        KeyFactory keyFactory = KeyFactory.getInstance(GlobalStatic.provider);
        String rsaPrivateKeyStr = (GlobalStatic.rsaPrivateKeyPem);
        if (StringUtils.isNotBlank(rsaPrivateKeyStr)) {
            byte[] decode = Base64.getDecoder().decode(rsaPrivateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decode);
            privateKey = keyFactory.generatePrivate(keySpec);
        }

        String rsaPublicKeyStr = (GlobalStatic.rsaPublicKeyPem);
        if (StringUtils.isNotBlank(rsaPublicKeyStr)) {
            byte[] decode = Base64.getDecoder().decode(rsaPublicKeyStr);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decode);
            publicKey = keyFactory.generatePublic(keySpec);
        }


    }


    /**
     * 读取 pem file的 base64内容
     *
     * @param path
     * @return
     * @throws Exception
     */


    private static String readRSAPEMFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path));) {
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    //sb.append('\r');
                }
            }

            return sb.toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }

    /**
     * 产生随机数
     *
     * @param bound
     * @return
     */
    public static Integer randomInteger(Integer bound) {
        Random r = new Random();
        int random = r.nextInt(bound);
        return Integer.valueOf(random);
    }


    /**
     * 产生 yyyyMMddHHmmssSSS+四位位随机数的字符串
     *
     * @return
     */
    public static String getTimeNO() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String result = format.format(new Date());
        return result + String.format("%09d", randomInteger(1000000000));
    }


    public static String String2Hash256(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest object = MessageDigest.getInstance("SHA-256");
        byte[] encrypted = object.digest(s.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte b : encrypted) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }



/*
	public static void main(String[] args) {
		String abc = encoderByRSAPrivateKey("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
		System.out.println(abc);
		System.out.println(decoderByRSAPublicKey(abc));
	}
*/


}
