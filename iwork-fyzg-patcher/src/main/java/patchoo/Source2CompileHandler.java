package patchoo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Source2CompileHandler {

    private final String WEB_INF_CLASSES = "\\WEB-INF\\classes\\";

    private String patchOutputFolder = "D:\\temp\\patch";
    private String programFolder = "D:\\workspaces\\IDEA_fxzb\\joyin.product-fyzg-public-parent\\joyin" +
            ".product-fyzg-public-page\\target\\joyin.product-fyzg-public-page";

    List<SourceFile> sourceFiles = new ArrayList<>();

    public String getPatchOutputFolder() {
        return patchOutputFolder;
    }

    public Source2CompileHandler() {
        try {
//            Properties pro = new Properties();
//            pro.load(Source2CompileHandler.class.getResourceAsStream("default.properties"));
//            patchOutputFolder = pro.getProperty("patchOutputFolder");
//            programFolder = pro.getProperty("programFolder");

            File patchFoldre = new File(patchOutputFolder);
            FileUtils.deletefile(patchFoldre.getAbsolutePath());
            patchFoldre.mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CompileFile findCompileFiles(SourceFile sourceFile) {
        String patchPath = "";
        String path = sourceFile.getPath();
        FileType fileType = sourceFile.getFileType();
        CompileFile compileFile = new CompileFile();

        if (fileType.equals(FileType.JAVA)) {
            String flagStr = "src\\main\\java";
            int startPosition = path.indexOf(flagStr) + flagStr.length();
            int endPosition = path.length();

            String _patchOutputFolder = patchOutputFolder + WEB_INF_CLASSES;
            patchPath = _patchOutputFolder + path.substring(startPosition, endPosition);
            patchPath = patchPath.replace("java", "class");

            String _programFolder = programFolder + WEB_INF_CLASSES;
            path = _programFolder + path.substring(startPosition, endPosition);
            path = path.replace("java", "class");
        } else if (fileType.equals(FileType.RESOURCE)) {
            String flagStr = "src\\main\\resources";
            int startPosition = path.indexOf(flagStr) + flagStr.length();
            int endPosition = path.length();

            String _patchOutputFolder = patchOutputFolder + WEB_INF_CLASSES;
            patchPath = _patchOutputFolder + path.substring(startPosition, endPosition);

            String _programFolder = programFolder + WEB_INF_CLASSES;
            path = _programFolder + path.substring(startPosition, endPosition);
        } else if (fileType.equals(FileType.WEBAPP)) {
            String flagStr = "src\\main\\webapp";
            int startPosition = path.indexOf(flagStr) + flagStr.length();
            int endPosition = path.length();

            patchPath = patchOutputFolder + path.substring(startPosition, endPosition);
            path = programFolder + path.substring(startPosition, endPosition);
        }
        compileFile.setFileType(fileType);
        compileFile.setPath(path);
        compileFile.setPatchPath(patchPath);

        return compileFile;
    }

    public boolean gen() throws IOException, IllegalAccessException {

        for(Iterator<SourceFile> ite = sourceFiles.iterator(); ite.hasNext();) {
            SourceFile sourceFile = ite.next();
            CompileFile compileFile = this.findCompileFiles(sourceFile);
            FileUtils.copyFile(compileFile);
        }
        return true;
    }

    public void setPatchOutputFolder(String patchOutputFolder) {
        this.patchOutputFolder = patchOutputFolder;
    }

    public void addSourceFile(SourceFile sourceFile) {
        sourceFiles.add(sourceFile);
    }
}
