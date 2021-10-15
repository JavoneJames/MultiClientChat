package com.application.server;

import org.jetbrains.annotations.NotNull;

import javax.swing.JOptionPane;
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
        submitButton.addActionListener(e -> {
            if (e.getSource() == submitButton)
                textFieldInputHandler();
            short PORT = Short.parseShort(inputTextField.getText());
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {//instantiate serverSocket object - param 'PORT'
                if (serverSocket.isClosed() || serverSocket.isBound())//checks is the serverSocket is available
                    throw new ServerNotActiveException(PORT + " cannot be accessed");
                displayServerFeed.append("Starting Server...");
                incomingConnectionsHandler(serverSocket);
            } catch (IOException | ServerNotActiveException ioException) {
                ioException.printStackTrace();
            }
        });
    }
    @SuppressWarnings("InfiniteLoopStatement")
    private void incomingConnectionsHandler(@NotNull ServerSocket serverSocket){
        while (true){
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

    private void textFieldInputHandler() {
        if (inputTextField.getText().isEmpty())//checks if the text field is empty
            JOptionPane.showMessageDialog(frame, "Empty input");
        else if (inputTextField.getText().matches("^[a-zA-Z]+$") ||//checks if input is string
                !(Short.parseShort(inputTextField.getText()) >= 1))//checks if input is a positive int
            JOptionPane.showMessageDialog(frame, "Enter positive Integer");
    }
}
