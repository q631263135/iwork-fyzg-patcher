package patchoo;

import java.io.*;

public class FileUtils {

    public static boolean deletefile(String depPath) throws Exception {
        try {

            File file = new File(depPath);
            // 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(depPath + "\\" + filelist[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                        System.out.println(delfile.getAbsolutePath() + "删除文件成功");
                    } else if (delfile.isDirectory()) {
                        deletefile(depPath + "\\" + filelist[i]);
                    }
                }
                System.out.println(file.getAbsolutePath() + "删除成功");
                file.delete();
            }

        } catch (FileNotFoundException e) {
            System.out.println("deletefile() Exception:" + e.getMessage());
        }
        return true;
    }

    public static void copyFile(CompileFile compileFile) throws IOException {
        FileInputStream fis = new FileInputStream(compileFile.getPath());

        File file = new File(compileFile.getPatchPath());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fos.write(buffer);
        fos.flush();
        fis.close();
        fos.close();
    }
}
