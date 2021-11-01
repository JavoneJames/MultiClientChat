package com.server.process;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class ClientHandler {
  private final Socket socket;

  public ClientHandler(Socket socket) {
    this.socket = socket;
  }

  public void execute() {
    System.out.println("here");
    try(ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
      while (true) {
        String line = inputStream.readUTF();
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
