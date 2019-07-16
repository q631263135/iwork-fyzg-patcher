package patchjar.oo.utils.handlerchain;

import patchjar.oo.utils.Config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class SHUtil extends BatHandler {
    private StringBuffer batCmd = new StringBuffer();;

    private static final SHUtil shUtil = new SHUtil();

    private SHUtil() {
        batCmd.append("@echo off\r\n");
        batCmd.append("set SourceFolder=%cd%\r\n");
        batCmd.append("set /p TargetFolder=请输入程序目录(例如E:\\apache-tomcat-6.0.37\\webapps\\fyzg):\r\n");
        batCmd.append("if not exist %TargetFolder% (\r\n");
        batCmd.append("	echo 目录输入错误，请重新运行！\r\n");
        batCmd.append("	pause \r\n");
        batCmd.append("	exit \r\n");
        batCmd.append(")\r\n");
    }

    public static SHUtil getInstance() {
        return shUtil;
    }

    @Override
    public void completeCmd() {
        batCmd.append("echo 升级脚本执行完成\r\n");
        batCmd.append("pause");

        File batFile = new File(Config.patchFolder + File.separator + "patch2.bat");
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

    @Override
    public void appentCmd(String cmd) {
        batCmd.append(cmd + " \r\n");
    }
}
