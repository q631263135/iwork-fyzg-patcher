package patchoo;


import static patchoo.FileType.*;

public class SourceFile {

    private FileType fileType;

    private String path;

    public SourceFile() {
    }

    public SourceFile(String path) {
        this.path = path;
        if (path.contains("src\\main\\java")) {
            this.setFileType(JAVA);
        }
        else if (path.contains("src\\main\\resources")) {
            this.setFileType(RESOURCE);
        }
        else if (path.contains("src\\main\\webapp")) {
            this.setFileType(WEBAPP);
        }
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

}
