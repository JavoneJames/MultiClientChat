package com.application.client;

import com.application.gui.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class ClientGUI extends GUI {



    @Override
    protected synchronized void attachComponentsToFrame() {
        super.panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

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
        super.submitButton.setText("Send Message");
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(5,0,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        super.panel.add(super.submitButton, gbc);
        submitButton.addActionListener(new ClientProcess());
        super.frame.add(super.panel, null);
    }

}
