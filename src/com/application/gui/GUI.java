package com.application.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public abstract class GUI {
    protected JFrame frame;
    protected JPanel panel;
    protected JTextField inputTextField;
    protected JButton submitButton;
    protected JTextArea displayServerFeed;
    protected JScrollPane scrollPane;
    protected JLabel displayLabel;

    protected synchronized void instantiateComponents(){
        frame = new JFrame();
        panel = new JPanel();
        inputTextField = new JTextField();
        submitButton = new JButton();
        displayLabel = new JLabel();
        displayServerFeed = new JTextArea();
        scrollPane = new JScrollPane();
    }
    public synchronized void createWindowFrame(){
        //calls method that is used to instantiate the components to be used
        instantiateComponents();

        frame.setTitle("Client");
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
