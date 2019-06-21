package patchjar.oo.filehandler;

import patchjar.oo.utils.Config;
import patchjar.oo.utils.FileUtil;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaPatchFileHandler extends PatchFileHandler {
    @Override
    public void handle(File file) {
        Pattern compile = Pattern.compile(".*\\\\(.*)\\\\src\\\\main\\\\java(.*)\\\\(.*).java");
        Matcher matcher = compile.matcher(file.getAbsolutePath());
        matcher.find();

        // 如：joyin.product-fyzg-public-domain
        String projectName = matcher.group(1);

        // 如：\com\joyin\fyzg\domain\batch\XXX.class
        // matcher.group(2)：\com\joyin\fyzg\domain\batch
        // matcher.group(3)：XXX
        String classFilePath = matcher.group(2) + "\\" + matcher.group(3) + ".class";

        // 找到部署文件中的class文件
        File classFile = new File(Config.deployFolder + classFilePath);

        // 新建补丁文件
        File patchFile = FileUtil.createFile(Config.patchFolder + classFilePath);

        // 复制文件内容
        FileUtil.copyFile(classFile, patchFile);

        batUtil.appentCmd("cd %SourceFolder%");
        batUtil.appentCmd("jar vuf %TargetFolder%\\WEB-INF\\lib\\" + projectName + "-0.0.1-SNAPSHOT.jar " + classFilePath.substring(1));
    }
}
