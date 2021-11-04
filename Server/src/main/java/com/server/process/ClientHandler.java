package com.server.process;

import com.server.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler {
  private final Socket socket;
  User user;
  private int clientID;
  private ObjectOutputStream outputStream;

  public ClientHandler(Socket socket) {
    this.socket = socket;
    try {
      this.outputStream = new ObjectOutputStream(socket.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void execute() {
    createUser();
    notificationsHandler(clientID);
    ServerProcess.notifyOFNewClients(clientID);
    try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
      while (true) {
        String line = inputStream.readUTF();
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void createUser() {
    clientID = ServerProcess.getCounter();
    user = new User(clientID);
  }

  private void notificationsHandler(int clientID) {
    Vector<User> users = ServerProcess.getListOfUsers();
    if (users.size() == 0) return;
    for (User user : users)
      if (clientID == user.getID())
        sendOutputToClient("client " + user.getID() + " has joined");
    if (clientID != user.getID())
      sendOutputToClient("client " + user.getID() + " has joined");
  }

  void sendOutputToClient(String message) {
    try {
      outputStream.writeUTF(message);
      outputStream.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
