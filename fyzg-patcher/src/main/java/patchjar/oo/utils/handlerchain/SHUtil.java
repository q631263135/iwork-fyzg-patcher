package patchjar.oo.utils.handlerchain;

import patchjar.oo.utils.Config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class SHUtil extends BatHandler {
    private StringBuffer shellCmd = new StringBuffer();;

    private static final SHUtil shUtil = new SHUtil();

    private SHUtil() {
        shellCmd.append("#!/bin/bash\n");
        shellCmd.append("SourceFolder=$(pwd)\n");
        shellCmd.append("echo -n \"请输入程序目录(例如 /usr/local/tomcat/apache-tomcat-8.5.23/webapps/fyzg):\"\n");
        shellCmd.append("read TargetFolder\n");
        shellCmd.append("if [ ! -d $TargetFolder ] ; then\n");
        shellCmd.append("	echo \"目录输入错误，请重新运行！\"\n");
        shellCmd.append("	exit 2\n");
        shellCmd.append("fi\n");
    }

    public static SHUtil getInstance() {
        return shUtil;
    }

    @Override
    public void completeCmd() {
        shellCmd.append("echo \"升级脚本执行完成\"\n");

        File batFile = new File(Config.patchFolder + File.separator + "patch.sh");
        PrintStream out = null;
        try {
            out = new PrintStream(new FileOutputStream(batFile), true, "GBK");
            System.setOut(out);
            System.out.println(shellCmd.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.flush();
        out.close();
    }

    @Override
    public void appentCmd(String cmd) {
        shellCmd.append(cmd + "\n");
    }
}
