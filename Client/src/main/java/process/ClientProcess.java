package process;

import gui.ClientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ClientProcess extends ClientGUI implements ActionListener {
  private final InetAddress localhost;
  int port;
  private ObjectOutputStream outputStream;
  private ObjectInputStream inputStream;
  private Socket socket;

  ClientProcess(InetAddress localhost, int port) {
    this.localhost = localhost;
    this.port = port;
  }

  //creates a new socket object that takes in ip/port and connect to server
  void connectToServer() {
    try {
      socket = new Socket(localhost, port);
      if (!socket.isConnected()) {
        displayServerFeed.append("cannot connect to server\n");
        throw new ConnectException("cannot connect to server");
      }
      displayServerFeed.append("connect to server\n");
      outputStream = new ObjectOutputStream(socket.getOutputStream());

    } catch (IOException e) {
      e.printStackTrace();
    }
    readMessagesFromServer();
  }
  //runs in separate executor thread - reads messages from the server
  private void readMessagesFromServer() {
    Executor executorService = Executors.newSingleThreadExecutor();
    executorService.execute(() -> {
      try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())){
        while (true) {
          String line = inputStream.readUTF();
          System.out.println(line);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  @Override//Send messages from the text field to the server
  public synchronized void actionPerformed(ActionEvent e) {
    if (e.getSource() == submitButton) {
      if (!inputTextField.getText().isEmpty()) {
        try {
          outputStream.writeUTF(inputTextField.getText());
          outputStream.flush();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    }
  }
}
