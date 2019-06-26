package patchjar.oo;

import patchjar.oo.components.ButtonPanel;
import patchjar.oo.components.FileTextArea;
import patchjar.oo.components.MyFrame;
import patchjar.oo.filehandler.PatchFileHandler;
import patchjar.oo.filehandler.PatchFileHandlerFactory;
import patchjar.oo.utils.BatUtil;
import patchjar.oo.utils.Config;
import patchjar.oo.utils.ExceptionUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
// 过程化编程要解决的问题

// 1. 哪些文件要生成补丁：这里拟从JTextArea中获取，在“客户端”加载
// 2. 生成补丁文件的过程中，需要创建bat文件信息，可以由单一操作实例执行
// 3. 生成补丁文件的过程需要抽象出来，方便将来扩展；
// 4. 将面向过程的代码片段，放到对象的方法区，利用对象的继承多态等来增强可扩展性（一定程度上来说，继承等就是为了解决把面向过程编程的扩展方式即分支语句）

/**
 * 第二次编程 面向对象
 **/
public class BootStrap extends MyFrame {

    // 待生成补丁的文件（IDEA下的local changes）
    private List<File> filesChanged = new ArrayList<>();

    // 文本域，可以粘贴文件路径
    private JTextArea textArea = null;

    // 按钮容器
    private JPanel buttonPanel = null;

    public List<File> getFilesChanged() {
        return filesChanged;
    }

    private BootStrap(String title) throws HeadlessException {
        super(title);
        initComponent();
    }

    /**
     * 初始化界面
     */
    private void initComponent() {
        // FileTextArea->补丁文件路径
        textArea = new FileTextArea(this);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(textArea);
        this.add(jScrollPane, BorderLayout.NORTH);

        // ButtonPanel->定义按钮及其行为
        buttonPanel = new ButtonPanel(this);
        this.add(buttonPanel);
    }

    /**
     * 生成补丁文件
     */
    public void generatePathFiles() {
        // 工厂模式不要仅局限于创建对象，当需要创建各种行为时，也相当于创建包装行为的对象；
        // 相当于说需要创建不同的行为时，也可以使用工厂模式（一切皆对象此处适用）
        try {
            PatchFileHandlerFactory patchFileHandlerFactory = new PatchFileHandlerFactory();

            // 遍历变动的文件，交给相应的文件补丁处理器处理，生成补丁文件
            for (File file : filesChanged) {
                PatchFileHandler fileHandler = patchFileHandlerFactory.newFileHandler(file);
                fileHandler.handle(file);
            }

            BatUtil.getInstance().completeCmd();
            this.dispose();

            // 完成生成补丁，打开补丁文件所在目录
            Runtime.getRuntime().exec("explorer.exe /e, " + Config.patchFolder);
        } catch (Exception e) {
            textArea.setText("程序执行发生异常：" + ExceptionUtil.getStackTrace(e));
            textArea.setForeground(Color.red);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        // 设置界面显示主题
        UIManager.setLookAndFeel(com.sun.java.swing.plaf.windows.WindowsLookAndFeel.class.getName());

        EventQueue.invokeLater(() -> new BootStrap("拷贝文件路径到下方区域...").setVisible(true));
    }
}
