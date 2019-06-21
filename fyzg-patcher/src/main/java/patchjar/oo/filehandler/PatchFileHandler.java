package patchjar.oo.filehandler;

import patchjar.oo.utils.BatUtil;

import java.io.File;

public abstract class PatchFileHandler {
    protected BatUtil batUtil = BatUtil.getInstance();

    public abstract void handle(File file);
}
