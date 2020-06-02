package patchjar.oo.filehandler;

import patchjar.oo.utils.handlerchain.BatHandler;
import patchjar.oo.utils.handlerchain.BatUtil;
import patchjar.oo.utils.handlerchain.SHUtil;

import java.io.File;

public abstract class PatchFileHandler {
    protected BatUtil batUtil = BatUtil.getInstance();
    protected SHUtil shUtil = SHUtil.getInstance();

    public abstract void handle(File file);
}
