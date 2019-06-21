package patchjar.oo.utils;

import java.io.IOException;
import java.util.Properties;

public class Config {


//    final String deployFolder = "D:\\workspaces\\IDEA_fxzb\\joyin.product-fyzg-public-parent\\joyin.product-fyzg-public-page\\target\\joyin.product-fyzg-public-page\\WEB-INF\\classes";
//    final String patchFolder = "D:\\temp\\patch";

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
