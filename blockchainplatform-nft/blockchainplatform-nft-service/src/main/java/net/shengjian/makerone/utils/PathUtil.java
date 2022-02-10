package net.shengjian.makerone.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * @descriptions: 路径
 * @author: YSK
 * @date: 2021/12/23 18:05
 * @version: 1.0
 */
public class PathUtil {

    /**
     * 路径转义符替换 \ -> /
     * @return 替换后的路劲
     */
    public static String pathRightTOLeft(String path){
        if(StringUtils.isBlank(path)){
            return null;
        }
        return path.replace("\\","/");
    }

    /**
     * 读取文件的值
     * @param path 路径
     * @return value
     */
    public static String readPath(String path) {
        File file = new File(path);
        if(file.exists()) {
            try {
                return readPath(new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static String readPath(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[(int) inputStream.available()];
        try {
            IOUtils.read(inputStream,buffer);
            return new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
        return null;
    }
}
