package patchjar.oo.filehandler;

import patchjar.oo.utils.Config;
import patchjar.oo.utils.FileUtil;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResoucesPatchFileHandler extends PatchFileHandler {
    @Override
    public void handle(File file) {
        Pattern compile = Pattern.compile(".*\\\\(.*)\\\\src\\\\main\\\\resources(.*)\\\\(.*)");
        Matcher matcher = compile.matcher(file.getAbsolutePath());
        matcher.find();

        // 如：joyin.product-fyzg-public-dao
        String projectName = matcher.group(1);

        // 如：\sqlmapper\product\fyzg\salesplan\XXX.xml
        String resourceFilePath = matcher.group(2) + "\\" + matcher.group(3);

        // 创建补丁文件
        File patchFile = FileUtil.createFile(Config.patchFolder + resourceFilePath);

        // 复制文件内容
        FileUtil.copyFile(file, patchFile);


        if ("joyin.product-fyzg-public-page".equals(projectName)) {
            batUtil.appentCmd("xcopy \"%SourceFolder%" + resourceFilePath + "\" \"%TargetFolder%\\WEB-INF\\classes" + resourceFilePath + "\" /y");
        } else {
            batUtil.appentCmd("cd %SourceFolder%");
            batUtil.appentCmd("jar vuf %TargetFolder%\\WEB-INF\\lib\\" + projectName + "-0.0.1-SNAPSHOT.jar " + resourceFilePath.substring(1));
        }
    }
}
