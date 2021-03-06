package net.shengjian.frame.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtils {

    /**
     * 文件操作工具类
     */
    private FileUtils() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    /**
     * 根据路径和后缀,获取文件列表
     *
     * @param path
     * @param ext
     * @return
     */
    public static List<File> getPathAllFileExt(String path, String ext) {
        List<File> list = new ArrayList<>();

        getPathAllFileExt(list, path, ext);

        return list;

    }

    /**
     * 根据文件后缀,递归获取路径下的文件
     *
     * @param list
     * @param path
     * @param ext
     * @return
     */
    private static List<File> getPathAllFileExt(List<File> list, String path, String ext) {

        File dir = new File(path);
        if (dir.isDirectory() == false)
            return list;

        File[] files = dir.listFiles();

        if (files == null || files.length < 1)
            return list;
        for (File f : files) {
            if (f.isDirectory()) {
                String dirPath = f.getAbsolutePath();
                getPathAllFileExt(list, dirPath, ext);
            } else {
                if (f.getName().endsWith(ext)) {

                    list.add(f);
                }
            }
        }

        return list;
    }

    /**
     * 获取文件后缀 例如 .png
     *
     * @param originalFilename
     * @return
     */
    public static String getSuffix(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
    }

    /**
     * 获取文件格式 例如 png
     *
     * @param originalFilename
     * @return
     */
    public static String getFileFormat(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
    }

    /**
     * 获取文件名
     *
     * @param suffix
     * @return
     */
    public static String reSetFileName(String suffix) {
        return System.currentTimeMillis() + suffix;
    }

    /**
     * 把文件读到写入流
     *
     * @param writer
     * @param file
     * @throws Exception
     */

    public static void readIOFromFile(Writer writer, File file) throws IOException {
        readIOFromFile(writer, file, true);
    }

    /**
     * 把文件读到写入流
     *
     * @param writer
     * @param file
     * @throws Exception
     */

    public static void readIOFromFile(Writer writer, File file, boolean closeWriter) throws IOException {
        // 读出文件到response
        // 这里是先需要把要把文件内容先读到缓冲区
        // 再把缓冲区的内容写到response的输出流供用户下载
        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(bufferedInputStream, GlobalStatic.defaultCharset));) {

            char[] data = new char[1024];
            while (reader.read(data) != -1) {
                writer.write(data);
            }

        }

    }
    /**
     * 压缩成zip 方法
     * @param srcFiles 需要压缩的文件列表
     * @param outPath           压缩文件输出路径
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(List<File> srcFiles , String outPath) throws RuntimeException {
        long start = System.currentTimeMillis();
        try (OutputStream os = new FileOutputStream(outPath);
             ZipOutputStream zos = new ZipOutputStream(os)){
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[1024];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        }
    }
}
