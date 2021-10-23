package com.application.server;

import java.net.Socket;

public class ClientHandler implements Runnable {
    public ClientHandler(Socket socket) {
    }

    @Override
    public void run() {
        System.out.println("client handler");
    }
}
