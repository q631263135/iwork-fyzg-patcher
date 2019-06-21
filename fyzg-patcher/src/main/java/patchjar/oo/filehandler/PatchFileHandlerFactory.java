package patchjar.oo.filehandler;

import java.io.File;

public class PatchFileHandlerFactory {

    public PatchFileHandler newFileHandler(File file) {
        if (file.getName().endsWith("java")) {
            return new JavaPatchFileHandler();
        } else if (file.getName().endsWith("jsp")) {
            return new JspPatchFileHandler();
        } else {
            return new ResoucesPatchFileHandler();
        }
    }
}
