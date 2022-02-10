package net.shengjian.frame.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行命令行的工具类,需要提前设定执行权限
 *
 * @author caomei
 */
public class CmdUtils {
    private static final Logger logger = LoggerFactory.getLogger(CmdUtils.class);

    private CmdUtils() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    /**
     * 执行命令行
     *
     * @param commandStr
     * @return
     */
    public static List<String> execCmd(String commandStr) {
        final String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            return execWindowsCmd(commandStr);
        } else if (os.contains("linux")) {
            return execLinuxCmd(commandStr);
        } else {
            return null;
        }
    }

    /**
     * 执行命令行
     *
     * @param pb
     * @return
     */
    public static List<String> execCmd(ProcessBuilder pb) {

        BufferedReader br = null;
        try {

            Process p = pb.start();
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            List<String> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                list.add(line);
            }

            return list;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 执行Windows命令
     *
     * @param commandStr
     * @return
     */
    private static List<String> execWindowsCmd(String commandStr) {
        List<String> cmds = new ArrayList<>();
        cmds.add("cmd.exe");
        cmds.add("/c");
        cmds.add(commandStr);
        ProcessBuilder pb = new ProcessBuilder(cmds);
        //正常信息和错误信息合并输出,有些情况信息会在错误信息里,例如ffmpeg
        pb.redirectErrorStream(true);
        return execCmd(pb);
    }

    /**
     * 执行Linux命令
     *
     * @param commandStr
     * @return
     */
    private static List<String> execLinuxCmd(String commandStr) {
        List<String> cmds = new ArrayList<>();
        cmds.add("sh");
        cmds.add("-c");
        cmds.add(commandStr);
        ProcessBuilder pb = new ProcessBuilder(cmds);
        //正常信息和错误信息合并输出,有些情况信息会在错误信息里,例如ffmpeg
        pb.redirectErrorStream(true);
        return execCmd(pb);
    }

    /**
     * public static void main(String[] args) { StringBuilder execCmd =
     * execCmd("ipconfig /all"); System.out.println(execCmd.toString()); }
     */
}
