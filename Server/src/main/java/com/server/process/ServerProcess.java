package com.server.process;

import com.server.User;
import com.server.gui.ServerGUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerProcess extends ServerGUI {

  private static final Vector<ClientHandler> clientHandlerVector = new Vector<>();
  protected static AtomicInteger atomicInteger = new AtomicInteger(1);
  private ServerSocket serverSocket;

  ServerProcess(int port) {
    try {
      serverSocket = new ServerSocket(port);
      if (serverSocket.isClosed())
        throw new RuntimeException("cannot connect to port: " + port);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //gets clients ID for joining and incremeent counter by 1 for the next client
  static int getCounter() {
    return atomicInteger.getAndIncrement();
  }

  //returns a vector of all current clients connected to the server
  protected static Vector<User> getListOfUsers() {
    Vector<User> listOfUsers = new Vector<>();
    for (ClientHandler clientHandler : clientHandlerVector)
      if (clientHandler.user != null)
        listOfUsers.addElement(clientHandler.user);
    return listOfUsers;
  }

  //notifies existing clients of newer clients joining
  static void notifyOfNewClients(int clientID) {
    for (ClientHandler clientHandler : clientHandlerVector)
      if (clientHandler.user != null && clientHandler.user.getID() != clientID)
        clientHandler.sendOutputToClient("client " + clientID + " has joined");
  }

  @Override//override method used to start server
  protected void serverSocketHandler() {
    System.out.println("starting....");
    displayServerFeed.append("starting...\n");
    new Thread(() -> incomingConnectionsHandler(serverSocket)).start();
  }

  //accepts connections via serversocket and binds to socket object
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

  //hands off socket connection to another thread to deal with processing info
  private void multiClientHandler(Socket socket) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    ClientHandler clientHandler = new ClientHandler(socket);
    executorService.execute(clientHandler::execute);
    clientHandlerVector.addElement(clientHandler);
  }
}
