package com.application.gui;

import javax.swing.*;

public abstract class GUI {
    protected JFrame frame;
    protected JPanel panel;
    protected javax.swing.JTextField inputTextField;
    protected JButton submitButton;
    protected JTextArea displayServerFeed;
    protected JScrollPane scrollPane;
    protected JLabel displayLabel;

    protected void instantiateComponents(){
        frame = new JFrame();
        panel = new JPanel();
        inputTextField = new JTextField();
        submitButton = new JButton();
        displayLabel = new JLabel();
        displayServerFeed = new JTextArea();
        scrollPane = new JScrollPane();
    }
    public void createWindowFrame(){
        //calls method that is used to instantiate the components to be used
        instantiateComponents();

        frame.setTitle("Chat Client");
        frame.setSize(350,350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        attachComponentsToFrame();
        frame.pack();
        frame.setVisible(true);
    }
    protected abstract void attachComponentsToFrame();
    //protected abstract void handlerForClosingFrame();

}
