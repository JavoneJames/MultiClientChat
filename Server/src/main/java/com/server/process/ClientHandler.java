package com.server.process;

import com.server.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler {
  private final Socket socket;
  User user;
  private int clientID;

  public ClientHandler(Socket socket) {
    this.socket = socket;
  }

  public void execute() {
    System.out.println("here");
    createUser();
    notificationsHandler(clientID);
    try(ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
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

  private void notificationsHandler(int clientID){
    Vector<User> users = ServerProcess.getListOfUsers();
    if (users.size() == 0) return;
    for (User user : users)
      if (clientID == user.getID())
        System.out.println("client " + user.getID() + " has joined");
      if (clientID != user.getID())
        System.out.println("client " + user.getID() + " has joined");
  }
}
