package patchjar.oo.filehandler;

import java.io.File;

public class PatchFileHandlerFactory {

    public PatchFileHandler newFileHandler(File file) {

        if (file.getAbsolutePath().contains("\\src\\main\\java")) {
            return new JavaPatchFileHandler();
        } else if (file.getAbsolutePath().contains("\\src\\main\\resources")) {
            return new ResoucesPatchFileHandler();
        } else if (file.getAbsolutePath().contains("\\src\\main\\webapp")) {
            return new WebappPatchFileHandler();
        }
        return new WebappPatchFileHandler();
    }
}
