package com.server.process;

import com.server.gui.ServerGUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProcess extends ServerGUI {

    ServerProcess() {
        super();
        try {
            ServerSocket serverSocket = new ServerSocket(5536);
            serverSocketHandler(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void serverSocketHandler(ServerSocket serverSocket) {
        System.out.println("starting....");
        displayServerFeed.append("starting...");
        new Thread(() -> incomingConnectionsHandler(serverSocket)).start();
    }

    private void incomingConnectionsHandler(ServerSocket serverSocket) {
        while (true) {
            System.out.println("waiting...");
            try {
                Socket socket = serverSocket.accept();
                System.out.println("connection accepted...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
