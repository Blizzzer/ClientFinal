package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableDialog extends JFrame {
    TableDialog(JScrollPane scrollPane) {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(300, 160));
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        this.getContentPane().add(mainPanel);

        mainPanel.add(scrollPane);

        JButton okButton = new JButton("Leave");
        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }

        });
        mainPanel.add(okButton);
        this.pack();
        this.setVisible(true);

        }
}
