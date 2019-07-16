package patchjar.oo.utils.handlerchain;

public abstract class BatHandler {
    private BatHandler nextHandler;

    public void setNextHandler(BatHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void completeCmd();
    public abstract void appentCmd(String cmd);

    public final void complete() {
        this.completeCmd();
        if (nextHandler != null) {
            nextHandler.complete();
        }
    }

    public final void appent(String cmd) {
        this.appentCmd(cmd);
        if (nextHandler != null) {
            nextHandler.appent(cmd);
        }
    }
}
