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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// 过程化编程要解决的问题

// 1. 哪些文件要生成补丁：这里拟从JTextArea中获取，在“客户端”加载
// 2. 生成补丁文件的过程中，需要创建bat文件信息，可以由单一操作实例执行
// 3. 生成补丁文件的过程需要抽象出来，方便将来扩展；
// 4. 将面向过程的代码片段，放到对象的方法区，利用对象的继承多态等来增强可扩展性（一定程度上来说，继承等就是为了解决把面向过程编程的扩展方式即分支语句）

/**第二次编程 面向对象**/
public class BootStrap extends MyFrame {

    private List<File> filesChanged = new ArrayList<>();

    private JTextArea textArea = null;

    private JPanel buttonPanel = null;

    public List<File> getFilesChanged() {
        return filesChanged;
    }

    public BootStrap(String title) throws HeadlessException {
        super(title);
        initComponent();
    }

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

    public void generatePathFiles() throws IOException {
        // 工厂模式不要仅局限于创建对象，当需要创建各种行为时，也相当于创建包装行为的对象；
        // 相当于说需要创建不同的行为时，也可以使用工厂模式
        try {
            PatchFileHandlerFactory patchFileHandlerFactory = new PatchFileHandlerFactory();

            for (int i = 0; i < filesChanged.size(); i++) {
                File file = filesChanged.get(i);
                PatchFileHandler fileHandler = patchFileHandlerFactory.newFileHandler(file);
                fileHandler.handle(file);
            }

            BatUtil.getInstance().completeCmd();
            this.dispose();
            Runtime.getRuntime().exec("explorer.exe /e, " + Config.patchFolder);
        } catch (Exception e) {
            textArea.setText("程序执行发生异常：" + ExceptionUtil.getStackTrace(e));
            textArea.setForeground(Color.red);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new BootStrap("拷贝文件路径到下方区域...").setVisible(true));
    }
}
