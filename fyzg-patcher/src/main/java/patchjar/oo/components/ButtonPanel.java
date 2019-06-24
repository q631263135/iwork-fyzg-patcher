package patchjar.oo.components;

import patchjar.oo.BootStrap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ButtonPanel extends JPanel {
    public ButtonPanel(BootStrap bootStrap) {
        JButton gen = new JButton("生成补丁...");
        this.add(gen, BorderLayout.EAST);
        gen.addActionListener(e -> bootStrap.generatePathFiles());
    }
}
