package com.server.gui;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public abstract class ServerGUI {

    final JFrame frame = new JFrame();
    final JPanel panel = new JPanel();
    final JLabel displayLabel = new JLabel();
    final JScrollPane scrollPane = new JScrollPane();
    protected JTextArea displayServerFeed = new JTextArea();

    protected void createWindowFrame() {
        frame.setTitle("Server");
        frame.setSize(350, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        attachComponentsToFrame();
        frame.pack();
        frame.setVisible(true);
        serverSocketHandler();
    }

    protected void attachComponentsToFrame() {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //display label
        displayLabel.setText("Server Feed");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(displayLabel, gbc);
        //textarea to displayed server feed messages
        displayServerFeed.setRows(20);
        displayServerFeed.setColumns(30);
        scrollPane.setViewportView(displayServerFeed);
        scrollPane.setWheelScrollingEnabled(true);
        displayServerFeed.setEditable(false);
        displayServerFeed.setText(null);
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(displayServerFeed, gbc);

        frame.add(panel, null);
    }

    protected abstract void serverSocketHandler();
}
