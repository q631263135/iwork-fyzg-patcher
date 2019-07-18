package patchjar.oo.utils.handlerchain;

import patchjar.oo.utils.Config;

import java.io.*;

public class BatUtil extends BatHandler {
    private StringBuffer batCmd = new StringBuffer();;

    private static final BatUtil batUtil = new BatUtil();

    private BatUtil() {
        batCmd.append("@echo off\r\n");
        batCmd.append("set SourceFolder=%cd%\r\n");
        batCmd.append("set /p TargetFolder=请输入程序目录(例如E:\\apache-tomcat-6.0.37\\webapps\\fyzg):\r\n");
        batCmd.append("if not exist %TargetFolder% (\r\n");
        batCmd.append("	echo 目录输入错误，请重新运行！\r\n");
        batCmd.append("	pause \r\n");
        batCmd.append("	exit \r\n");
        batCmd.append(")\r\n");
//        this.setNextHandler(SHUtil.getInstance());
    }


    // 注意：这里不能用懒汉式单例
    public static BatUtil getInstance() {
        return batUtil;
    }

    public void appentCmd(String cmd) {
        batCmd.append(cmd + " \r\n");
    }

    public void completeCmd() {
        batCmd.append("echo 升级脚本执行完成\r\n");
        batCmd.append("pause");

        File batFile = new File(Config.patchFolder + File.separator + "patch.bat");
        PrintStream out = null;
        try {
            out = new PrintStream(new FileOutputStream(batFile), true, "GBK");
            System.setOut(out);
            System.out.println(batCmd.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.flush();
        out.close();
    }
}
