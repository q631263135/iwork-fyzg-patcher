package patchjar.oo.filehandler;

import patchjar.oo.utils.Config;
import patchjar.oo.utils.FileUtil;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebappPatchFileHandler extends PatchFileHandler {
    @Override
    public void handle(File file) {
        Pattern compile = Pattern.compile("\\\\src\\\\main\\\\webapp(.*)\\\\(.*)");
        Matcher matcher = compile.matcher(file.getAbsolutePath());
        matcher.find();

        String jspFilePath = matcher.group(1) + "\\" + matcher.group(2);

        File patchFile = FileUtil.createFile(Config.patchFolder + jspFilePath);
        FileUtil.copyFile(file, patchFile);

        if (matcher.group(1).indexOf("\\") > -1) {
            batUtil.appent("cd %TargetFolder%");
            batUtil.appent("md " + matcher.group(1) + " 2>nul");
        }
        batUtil.appent("xcopy \"%SourceFolder%" + jspFilePath + "\" \"%TargetFolder%" + jspFilePath + "\" /y");
    }
}
