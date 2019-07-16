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
        // 如：\com\joyin\fyzg\domain\batch
        String classFileClassPath = matcher.group(2);
        // 如：Constants
        String classFileName = matcher.group(3);
        // 如：\com\joyin\fyzg\domain\batch\Constants.class
//        String classFilePath = classFileClassPath + "\\" + classFileName + ".class";

        // 找到部署文件中的class文件
//        File classFile = new File(Config.deployFolder + classFilePath);

        File innerClassParentFolder = new File(Config.deployFolder + classFileClassPath);
        File[] innerClasses = FileUtil.listFilesByInnerClassName(innerClassParentFolder, classFileName);

        for (File classFile : innerClasses) {
            String classFilePath = classFileClassPath + "\\" + classFile.getName();
            // 新建补丁文件
            File patchFile = FileUtil.createFile(Config.patchFolder + classFilePath);
            // 复制文件内容
            FileUtil.copyFile(classFile, patchFile);

            batUtil.appent("cd %SourceFolder%");
            batUtil.appent("jar vuf %TargetFolder%\\WEB-INF\\lib\\");
            batUtil.appent(projectName + "-0.0.1-SNAPSHOT.jar " );
            batUtil.appent(classFilePath.substring(1) );
        }
    }
}
