package com.application.server;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.server.ServerNotActiveException;

public class ServerProcess extends ServerGUI implements Runnable {

    private ServerSocket serverSocket;

    ServerProcess() {
        createWindowFrame();
    }

    @Override
    public void run() {
            super.submitButton.addActionListener(e -> {
                if (e.getSource() == super.submitButton){
                    if (!textFieldInputHandler())
                        serverSocketHandler();
                }
            });
    }

    private void serverSocketHandler() {
        short PORT = Short.parseShort(inputTextField.getText());
        try  {//instantiate serverSocket object - param 'PORT'
            serverSocket = new ServerSocket(PORT);
            if (serverSocket.isClosed())//checks is the serverSocket is available
                throw new ServerNotActiveException(PORT + " cannot be accessed");
            System.out.println("starting");
            submitButton.setEnabled(false);
            displayServerFeed.append("start...");
            Runnable runnable = new Runnable() {
                public void run() {
                    incomingConnectionHandler(serverSocket);
                }
            };
            new Thread(runnable).start();
        } catch (IOException | ServerNotActiveException ioException) {
            ioException.printStackTrace();
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void incomingConnectionHandler(@NotNull ServerSocket serverSocket) {
        System.out.println("waiting...");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                if (socket.isClosed())
                    throw new SocketException("cannot connect to client socket");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean textFieldInputHandler() {
        if (super.inputTextField.getText().isEmpty()) {//checks if the text field is empty
            JOptionPane.showMessageDialog(frame, "Empty input");
            return true;
        } else if (super.inputTextField.getText().matches("^[a-zA-Z]+$") ||//checks if input is string
                !(Short.parseShort(super.inputTextField.getText()) >= 1)) {//checks if input is a positive int
            JOptionPane.showMessageDialog(frame, "Enter positive Integer");
            return true;
        }
        return false;
    }


}
