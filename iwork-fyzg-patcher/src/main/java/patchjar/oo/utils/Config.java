package patchjar.oo.utils;

import java.io.InputStreamReader;
import java.util.Properties;

public class Config {

    // 项目部署部署路径
    public static String deployFolder = null;

    // 补丁生成的目录
    public static String patchFolder = null;
    public static String repalceFlag = null;
    public static String replaceStr = null;
    public static String replaceTo = null;

    static {
        Properties pro = new Properties();
        try {
            pro.load(new InputStreamReader(Config.class.getResourceAsStream("/default.properties"), "utf-8"));
            deployFolder = pro.getProperty("deployFolder");
            patchFolder = pro.getProperty("patchFolder");
            repalceFlag = pro.getProperty("repalceFlag");
            replaceStr = pro.getProperty("replaceStr");
            replaceTo = pro.getProperty("replaceTo");

            FileUtil.deleteFile(patchFolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
