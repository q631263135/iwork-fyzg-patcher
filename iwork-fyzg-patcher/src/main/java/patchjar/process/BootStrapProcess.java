package patchjar.process;

import sun.misc.Regexp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 替换补丁文件，首个问题是哪里的文件替换到哪里，其次是哪些文件，最后是替换的过程
// 1. 哪里的文件：部署项目下的文件，按照其路径生成补丁
// 2. 哪些文件：发生变动的项目下的文件，这些文件如何读取
// 3. 替换过程：生成替换脚本，覆盖文件

// 以上：
// 1. 定义项目部署路径 -> 配置文件类
// 2. 根据发生变动文件及其路径，找到部署下的文件，并创建补丁
// 3. 创建替换过程 -> 工具类

// 针对第2步
// 1. 发生变动文件及其路径，找到部署下的文件
// 1.1 文件类型抽象类及其路径
// 1.2 封装创建补丁过程
// 2. 创建过程
// 2.1 文件工具类，供创建补丁、创建替换过程使用

/** 第一次 过程化编程 **/
public class BootStrapProcess {

    public static void main(String[] args) {

        // 项目部署部署路径
        final String deployFolder = "D:\\workspaces\\IDEA_fxzb\\joyin.product-fyzg-public-parent\\joyin.product-fyzg-public-page\\target\\joyin.product-fyzg-public-page\\WEB-INF\\classes";

        // 补丁生成的目录
        final String patchFolder = "D:\\temp\\patch";

        // 变动的文件列表
        List<File> filesChanged = new ArrayList<>();
        filesChanged.add(new File("D:\\workspaces\\IDEA_swhy\\joyin.product-fyzg-public-parent\\joyin.product-fyzg-public-domain\\src\\main\\java\\com\\joyin\\fyzg\\domain\\batch\\AssetScheduleGeneratedService.java"));
        filesChanged.add(new File("D:\\workspaces\\IDEA_swhy\\joyin.product-fyzg-public-parent\\joyin.product-fyzg-public-page\\src\\main\\webapp\\products\\fyzg\\salesPlan\\salesPlanManage.jsp"));
        filesChanged.add(new File("D:\\workspaces\\IDEA_swhy\\joyin.product-fyzg-public-parent\\joyin.product-fyzg-public-dao\\src\\main\\resources\\sqlmapper\\product\\fyzg\\salesplan\\SalesPlanMapper.xml"));

        // 遍历变动文件列表，生成补丁文件
        for(int i = 0; i < filesChanged.size(); i++) {
            File file = filesChanged.get(i);
            if (file.getName().endsWith("jsp")) {
                Pattern compile = Pattern.compile("\\\\src\\\\main\\\\webapp(.*)");
                Matcher matcher = compile.matcher(file.getAbsolutePath());
                matcher.find();

                try (FileInputStream fis = new FileInputStream(file)) {
                    File patchFile = new File(patchFolder + matcher.group(1));
                    patchFile.getParentFile().mkdirs();
                    patchFile.createNewFile();
                    FileOutputStream fos = new FileOutputStream(patchFile);
                    byte[] bytes = new byte[fis.available()];
                    fis.read(bytes);
                    fos.write(bytes);
                    fos.flush();
                    fos.close();
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (file.getName().endsWith("java")) {
                    Pattern compile = Pattern.compile("\\\\src\\\\main\\\\java(.*)\\.java");
                    Matcher matcher = compile.matcher(file.getAbsolutePath());
                    matcher.find();

                    file = new File(deployFolder + matcher.group(1) + ".class");
                    try (FileInputStream fis = new FileInputStream(file)) {
                        File patchFile = new File(patchFolder + matcher.group(1) + ".class");
                        patchFile.getParentFile().mkdirs();
                        patchFile.createNewFile();
                        FileOutputStream fos = new FileOutputStream(patchFile);
                        byte[] bytes = new byte[fis.available()];
                        fis.read(bytes);
                        fos.write(bytes);
                        fos.flush();
                        fos.close();
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Pattern compile = Pattern.compile("\\\\src\\\\main\\\\resources(.*)");
                    Matcher matcher = compile.matcher(file.getAbsolutePath());
                    matcher.find();

                    try (FileInputStream fis = new FileInputStream(file)) {
                        File patchFile = new File(patchFolder + matcher.group(1));
                        patchFile.getParentFile().mkdirs();
                        patchFile.createNewFile();
                        FileOutputStream fos = new FileOutputStream(patchFile);
                        byte[] bytes = new byte[fis.available()];
                        fis.read(bytes);
                        fos.write(bytes);
                        fos.flush();
                        fos.close();
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
