package com.application.server;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.server.ServerNotActiveException;

public class ServerProcess extends ServerGUI implements Runnable {

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
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {//instantiate serverSocket object - param 'PORT'
            if (serverSocket.isClosed())//checks is the serverSocket is available
                throw new ServerNotActiveException(PORT + " cannot be accessed");
            System.out.println("starting");
            super.displayServerFeed.append("Starting Server... \n");
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    incomingConnectionsHandler(serverSocket);//calls method that deals with incoming connections
                }
            };
        } catch (IOException | ServerNotActiveException ioException) {
            ioException.printStackTrace();
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void incomingConnectionsHandler(@NotNull ServerSocket serverSocket) {
        while (true) {
            Socket socket;
            try {
                socket = serverSocket.accept();
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
