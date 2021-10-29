package com.server.process;

import com.server.gui.ServerGUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;

public class ServerProcess extends ServerGUI {

    private ServerSocket serverSocket;

    ServerProcess() {
        try {
            serverSocket = new ServerSocket(5536);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void serverSocketHandler() {
        System.out.println("starting....");
        displayServerFeed.append("starting...\n");
        new Thread(() -> incomingConnectionsHandler(serverSocket)).start();
    }

    private void incomingConnectionsHandler(ServerSocket serverSocket) {
        while (true) {
            System.out.println("waiting...");
            displayServerFeed.append("waiting...\n");
            try {
                Socket socket = serverSocket.accept();
                System.out.println("connection accepted...");
                //displayServerFeed.append("connection accepted...\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
