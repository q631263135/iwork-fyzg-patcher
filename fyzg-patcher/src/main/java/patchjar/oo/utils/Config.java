package patchjar.oo.utils;

import java.util.Properties;

public class Config {

    // 项目部署部署路径
    public static String deployFolder = null;

    // 补丁生成的目录
    public static String patchFolder = null;

    static {
        Properties pro = new Properties();
        try {
            pro.load(Config.class.getResourceAsStream("/default.properties"));
            deployFolder = pro.getProperty("deployFolder");
            patchFolder = pro.getProperty("patchFolder");

            FileUtil.deleteFile(patchFolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
