package com.server.process;

import com.server.gui.ServerGUI;

import javax.swing.SwingUtilities;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ServerProcess extends ServerGUI {

    ReentrantLock lock = new ReentrantLock();
    private ServerSocket serverSocket;
    Vector<ClientHandler> clientHandlerVector = new Vector<>();

    private ServerProcess(int port) {
        try {
            serverSocket = new ServerSocket(port);
            if (serverSocket.isClosed())
                throw new RuntimeException("cannot connect to port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    new ServerProcess(5536).createWindowFrame();
                }
            });
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
                displayServerFeed.append("connection accepted...\n");
                multiClientHandler(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void multiClientHandler(Socket socket) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ClientHandler clientHandler = new ClientHandler(socket);
        System.out.println("here method");
        executorService.execute(clientHandler::execute);
        clientHandlerVector.addElement(clientHandler);
        System.out.println("created method");
    }
}
