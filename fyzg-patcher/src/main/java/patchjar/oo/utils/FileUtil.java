package patchjar.oo.utils;

import java.io.*;

public class FileUtil {
    public static File createFile(String filePath) {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
                return file;
            } catch (IOException e) {
                System.out.println("FileUtil.createFile() Exception:" + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return file;
    }

    public static boolean deleteFile(String depPath) throws Exception {
        try {
            File file = new File(depPath);
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] fileList = file.list();
                for (int i = 0; i < fileList.length; i++) {
                    File fileToDelete = new File(depPath + "\\" + fileList[i]);
                    if (!fileToDelete.isDirectory()) {
                        fileToDelete.delete();
                    } else if (fileToDelete.isDirectory()) {
                        deleteFile(depPath + "\\" + fileList[i]);
                    }
                }
                System.out.println(file.getAbsolutePath() + "删除成功");
                file.delete();
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileUtil.deleteFile() Exception:" + e.getMessage());
            throw new RuntimeException(e);
        }
        return true;
    }

    public static void copyFile(File source, File dest) {

        try (FileInputStream fis = new FileInputStream(source)) {
            FileOutputStream fos = new FileOutputStream(dest);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fos.write(buffer);
            fos.flush();
            fis.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("FileUtil.copyFile() Exception:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static File[] listFilesByInnerClassName(File folder, String innerClassNamePrefix) {
        return folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                // 内部类
                if (pathname.getName().startsWith(innerClassNamePrefix) && pathname.getName().contains("$")) {
                    return true;
                }
                // 外部类
                if (pathname.getName().split("\\.")[0].equals(innerClassNamePrefix)) {
                    return true;
                }
                return false;
            }
        });
    }
}
