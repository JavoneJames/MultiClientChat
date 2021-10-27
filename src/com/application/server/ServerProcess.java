package com.application.server;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.server.ServerNotActiveException;

public class ServerProcess extends ServerGUI implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == super.submitButton) {
            if (textFieldInputHandler()) {//calls method that handles input validation
                short PORT = Short.parseShort(inputTextField.getText());
                try {//calls method that begins with handling serverSocket instantiation
                    serverSocketHandler(new ServerSocket(PORT));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }}
    }

    private void serverSocketHandler(ServerSocket serverSocket) {
        try {//instantiate serverSocket object - param 'PORT'
            if (serverSocket.isClosed())//checks is the serverSocket is available
                throw new ServerNotActiveException(serverSocket.getLocalPort() + " cannot be accessed");
            System.out.println("starting");
            submitButton.setEnabled(false);
            displayServerFeed.append("start...\n");
            Runnable runnable = () -> this.incomingConnectionHandler(serverSocket);
            new Thread(runnable).start();//execute while loop for connections to minimize thread blocking
        } catch (ServerNotActiveException ioException) {
            ioException.printStackTrace();
        }
    }
    @SuppressWarnings("InfiniteLoopStatement")
    private void incomingConnectionHandler(ServerSocket serverSocket) {
        System.out.println("waiting...");
        displayServerFeed.append("waiting...");
        while (true) {
            try {//accepts client connections and binds it to a socket
                Socket socket = serverSocket.accept();
                if (socket.isClosed())//checks if socket is available
                    throw new SocketException("cannot connect to client socket");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean textFieldInputHandler() {
        if (super.inputTextField.getText().isEmpty()) {//checks if the text field is empty
            JOptionPane.showMessageDialog(frame, "Empty input");
            return false;
        } else if (super.inputTextField.getText().matches("^[a-zA-Z]+$") ||//checks if input is string
                !(Integer.parseInt(super.inputTextField.getText()) >= 1)) {//checks if input is a positive int
            JOptionPane.showMessageDialog(frame, "Enter positive Integer");
            return false;
        } else if (Integer.parseInt(super.inputTextField.getText()) > 65535) {//checks if input is within range of ports
            JOptionPane.showMessageDialog(frame, "Input out of range of possible port numbers");
            return false;
        }
        return true;
    }
}
