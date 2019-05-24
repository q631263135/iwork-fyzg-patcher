package patchoo;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Patcher extends JFrame {

    private Source2CompileHandler source2CompileHandler = new Source2CompileHandler();

    private StringBuffer fileListTxt =  new StringBuffer();

    public Patcher() {
        initComponents();
    }

    private void initComponents() {
        JScrollPane jScrollPane = new JScrollPane();
        JTextArea jTextArea = new MyTextArea();

        jTextArea.setColumns(20);
        jTextArea.setRows(20);
        jScrollPane.setViewportView(jTextArea);
        getContentPane().add(jScrollPane, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        final JFrame jframeSelf = this;

        JButton gen = new JButton("Generate..");
        buttonPanel.add(gen, BorderLayout.EAST);
        gen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (source2CompileHandler.gen()) {
                        JOptionPane.showMessageDialog(jframeSelf, "OK");
                        jframeSelf.dispose();

                        Runtime.getRuntime().exec("explorer.exe /e, " + source2CompileHandler.getPatchOutputFolder());
                    }
                } catch (IOException | IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
        });

        this.add(buttonPanel);

        this.setTitle("请拖动源码文件到光标处..");
        this.setAlwaysOnTop(true);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class MyTextArea extends JTextArea implements DropTargetListener {
        private MyTextArea() {
            this.setLineWrap(true);
            new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this);

            JTextArea jTextArea = this;

            this.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    System.out.println("insertUpdate");
                    try {
                        System.out.println(jTextArea.getText(e.getOffset(), e.getLength()));
                        String jTextAreaText = jTextArea.getText(e.getOffset(), e.getLength());

                        String[] split = jTextAreaText.split("\\u000A");

                        for (String str : split) {
                            SourceFile sourceFile = new SourceFile(str);
                            source2CompileHandler.addSourceFile(sourceFile);
                        }
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    System.out.println("removeUpdate");
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    System.out.println("changedUpdate");

                }
            });
        }

        public void drop(DropTargetDropEvent dtde) {
            try {
                Transferable tr = dtde.getTransferable();
                if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);

                    List list = (List) (dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
                    Iterator iterator = list.iterator();

                    while (iterator.hasNext()) {
                        File f = (File) iterator.next();
                        fileListTxt.append(f.getAbsolutePath());
                        fileListTxt.append("\n");
                        this.setText(fileListTxt.toString());

                        SourceFile sourceFile = new SourceFile(f.getAbsolutePath());
                        source2CompileHandler.addSourceFile(sourceFile);
                    }
                    dtde.dropComplete(true);
                    this.updateUI();
                } else {
                    dtde.rejectDrop();
                }
            } catch (IOException | UnsupportedFlavorException ioe) {
                ioe.printStackTrace();
            }
        }

        public void dragEnter(DropTargetDragEvent dtde) {
        }
        public void dragOver(DropTargetDragEvent dtde) {
        }
        public void dropActionChanged(DropTargetDragEvent dtde) {
        }
        public void dragExit(DropTargetEvent dte) {
        }
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> new Patcher().setVisible(true));
    }
}
