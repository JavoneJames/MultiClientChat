package com.application.server;

import com.application.gui.GUI;

import javax.swing.*;
import java.awt.*;

public class ServerGUI extends GUI {


    private JFrame frame;
    private JPanel panel;
    private JTextField inputTextField;
    private JButton submitButton;
    private JLabel displayLabel;
    private JTextArea displayServerFeed;
    private JScrollPane scrollPane;

    @Override
    protected void instantiateComponents() {

        frame = new JFrame();
        panel = new JPanel();
        displayLabel = new JLabel();
        inputTextField = new JTextField();
        submitButton = new JButton();
        displayServerFeed = new JTextArea(20,30);
        scrollPane = new JScrollPane();
    }

    @Override
    protected void createWindowFrame() {
        //calls method that is used to instantiate the components to be used
        instantiateComponents();

        frame.setTitle("Server Client");
        frame.setSize(350,350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        attachComponentsToFrame();
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    protected void attachComponentsToFrame() {

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

        frame.add(panel, null);
    }
}
