package com.application.server;

import com.application.gui.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class ServerGUI extends GUI {


    @Override
    protected void attachComponentsToFrame() {

        super.panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //display label
        super.displayLabel.setText("Server Feed");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,10, 0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        super.panel.add(super.displayLabel, gbc);
        //textarea to displayed server feed messages
        super.displayServerFeed.setRows(20);
        super.displayServerFeed.setColumns(30);
        super.scrollPane.setViewportView(super.displayServerFeed);
        super.scrollPane.setWheelScrollingEnabled(true);
        super.displayServerFeed.setEditable(false);
        super.displayServerFeed.setText(null);
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.BOTH;
        super.panel.add(super.displayServerFeed, gbc);
        //text field to input IP the server should try to connect to
        super.inputTextField.setColumns(30);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0,10,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        super.panel.add(super.inputTextField, gbc);
        //submit button for text field data
        super.submitButton.setText("Connect");
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(5,0,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        super.panel.add(super.submitButton, gbc);
        submitButton.addActionListener((ActionListener) this);
        super.frame.add(super.panel, null);
    }
}
