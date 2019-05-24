package patchoo;

public class CompileFile {

    private FileType fileType;

    private String path;

    private String patchPath;

    public CompileFile(String path) {
        this.path = path;
    }

    public CompileFile() {

    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPatchPath() {
        return patchPath;
    }

    public void setPatchPath(String patchPath) {
        this.patchPath = patchPath;
    }
}
