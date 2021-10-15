package com.application.server;

import javax.swing.*;

public class ServerProcess extends ServerGUI implements Runnable {

    ServerProcess() {
        createWindowFrame();
    }

    @Override
    public void run() {
        submitButton.addActionListener(e -> {
            if (e.getSource() == submitButton)
                if (inputTextField.getText().isEmpty())//checks if the text field is empty
                    JOptionPane.showMessageDialog(frame, "Empty input");
                else if (inputTextField.getText().matches("^[a-zA-Z]+$") ||//checks if input is string
                        !(Short.parseShort(inputTextField.getText()) >= 1))//checks if input is a positive int
                    JOptionPane.showMessageDialog(frame, "Enter positive Integer");

        });
    }
}
