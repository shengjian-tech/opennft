package net.shengjian.makerone.utils;


import java.io.*;
import java.util.Base64;

/**
 * @descriptions: 图片工具类
 * @author: YSK
 * @date: 2022/1/6 10:36
 * @version: 1.0
 */
public class NFTImageUtil {
    private NFTImageUtil(){}
    /**
     * 图片转base64字符串
     * @param imgFile 图片路径
     * @return base64字符串
     */
    public static String imageToBase64Str(String imgFile) {
        byte[] data = null;
        try (InputStream  inputStream = new FileInputStream(imgFile)){
            data = new byte[inputStream.available()];
            inputStream.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        return Base64.getEncoder().encodeToString(data);
    }
    /**
     * base64编码字符串转换为图片
     * @param imgStr base64编码字符串
     * @param path 输出图片路径
     * @return 是否转换成功
     */
    public static boolean base64StrToImage(String imgStr, String path) {
        if (imgStr == null){
            return false;
        }
        try {
            // 解密
            byte[] b = Base64.getDecoder().decode(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //文件夹不存在则自动创建
            File tempFile = new File(path);
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(tempFile);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String base64Str = NFTImageUtil.imageToBase64Str("C:\\Users\\Admin\\Pictures\\2b94b22a8dc6807fda58eb9f47c29f04.jpg");
        System.out.println(base64Str);
        NFTImageUtil.base64StrToImage(base64Str,"C:\\Users\\Admin\\Pictures\\b.jpg");
    }
}
