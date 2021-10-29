package com.server.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public abstract class ServerGUI {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel displayLabel = new JLabel();
    JButton submitButton = new JButton();
    JScrollPane scrollPane = new JScrollPane();
    protected JTextArea displayServerFeed = new JTextArea();
    JTextField inputTextField = new JTextField();

    public ServerGUI() {
        createWindowFrame();
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

}
