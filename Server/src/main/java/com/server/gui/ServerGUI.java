package com.server.gui;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public abstract class ServerGUI {

    protected volatile JTextArea displayServerFeed;
    private final JFrame frame;
    private final JPanel panel;
    private final JLabel displayLabel;
    private final JButton submitButton;
    private final JScrollPane scrollPane;
    private final JTextField inputTextField;

    public ServerGUI() {
        frame = new JFrame();
        panel = new JPanel();
        inputTextField = new JTextField();
        submitButton = new JButton();
        displayLabel = new JLabel();
        displayServerFeed = new JTextArea();
        scrollPane = new JScrollPane();
    }
 /*   protected JFrame frame;
    protected JPanel panel;
    protected JTextField inputTextField;
    protected JButton submitButton;
    public JTextArea displayServerFeed;
    protected JScrollPane scrollPane;
    protected JLabel displayLabel;

    public Runnable instantiateComponents(){
        frame = new JFrame();
        panel = new JPanel();
        inputTextField = new JTextField();
        submitButton = new JButton();
        displayLabel = new JLabel();
        displayServerFeed = new JTextArea();
        scrollPane = new JScrollPane();
        createWindowFrame();
        return this::attachComponentsToFrame;
    }*/

    public void createWindowFrame() {
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

    protected void attachComponentsToFrame(){
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //display label
        displayLabel.setText("Server Feed");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,10, 0,0);
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
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(displayServerFeed, gbc);
        //text field to input IP the server should try to connect to
        inputTextField.setColumns(30);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0,10,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(inputTextField, gbc);
        //submit button for text field data
        submitButton.setText("Connect");
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(5,0,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(submitButton, gbc);
        //super.submitButton.addActionListener((ActionListener) this);
        frame.add(panel, null);
    }

    protected abstract void serverSocketHandler();
}
