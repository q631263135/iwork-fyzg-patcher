package patchjar.oo.components;

import patchjar.oo.BootStrap;
import patchoo.Patcher;
import patchoo.SourceFile;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTextArea extends JTextArea {

    public FileTextArea(BootStrap bootStrap) {

        this.setColumns(20);
        this.setRows(10);
        this.setLineWrap(true);

        JTextArea jTextArea = this;
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    System.out.println(jTextArea.getText(e.getOffset(), e.getLength()));
                    String jTextAreaText = jTextArea.getText(e.getOffset(), e.getLength());
                    String[] split = jTextAreaText.split("\\u000A");

                    for (String str : split) {
                        File file = new File(str);
                        bootStrap.getFilesChanged().add(file);
                    }
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }
}
