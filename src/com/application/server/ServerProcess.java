package com.application.server;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.server.ServerNotActiveException;

public class ServerProcess extends ServerGUI implements Runnable {

    ServerProcess() {
        createWindowFrame();
    }

    @Override
    public void run() {
        submitButton.addActionListener(e -> {
            if (e.getSource() == submitButton)
                textFieldInputHandler();
            short PORT = Short.parseShort(inputTextField.getText());
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                if (serverSocket.isClosed())
                    throw new ServerNotActiveException(PORT + " cannot be accessed");
            } catch (IOException | ServerNotActiveException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    private void textFieldInputHandler() {
        if (inputTextField.getText().isEmpty())//checks if the text field is empty
            JOptionPane.showMessageDialog(frame, "Empty input");
        else if (inputTextField.getText().matches("^[a-zA-Z]+$") ||//checks if input is string
                !(Short.parseShort(inputTextField.getText()) >= 1))//checks if input is a positive int
            JOptionPane.showMessageDialog(frame, "Enter positive Integer");
    }
}
