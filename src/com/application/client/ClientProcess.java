package com.application.client;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientProcess extends ClientGUI implements ActionListener {
    ClientProcess(){
        try {
            InetAddress serverName = InetAddress.getByName("localhost");
            if (!serverName.isReachable(10000))
                throw new RuntimeException("server address is not reachable");
            final int PORT = 5536;
            connectToServer(serverName, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectToServer(InetAddress serverName, int port) {
        try {
            Socket socket = new Socket(serverName, port);
            if (!socket.isConnected())
                throw new ConnectException("cannot connect to server");
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            displayServerFeed.append("connected to the server" + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton)
            if (inputTextField.getText().isEmpty())
                JOptionPane.showConfirmDialog(frame,"ok");
    }
}
