package process;

import gui.ClientGUI;

import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ClientProcess extends ClientGUI implements Runnable, ActionListener {
  private final InetAddress localhost;
  int port;
  private ObjectOutputStream outputStream;
  private ObjectInputStream inputStream;

  private ClientProcess(InetAddress localhost, int port) {
    this.localhost = localhost;
    this.port = port;
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      try {
        var ClientProcess = new ClientProcess(InetAddress.getByName("localhost"), 5536);
        ClientProcess.createWindowFrame();
        ClientProcess.run();
      } catch (UnknownHostException e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public void run() {
    connectToServer();
  }

  private void connectToServer() {
    try {
      Socket socket = new Socket(localhost, port);
      if (!socket.isConnected()) {
        displayServerFeed.append("cannot connect to server\n");
        throw new ConnectException("cannot connect to server");
      }
      displayServerFeed.append("connect to server\n");
      outputStream = new ObjectOutputStream(socket.getOutputStream());
      inputStream = new ObjectInputStream(socket.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
    readMessagesFromServer();
  }

  private void readMessagesFromServer() {
    Executor executorService = Executors.newSingleThreadExecutor();
    executorService.execute(() -> {
      while (true) {
        try {
          String line = inputStream.readUTF();
          System.out.println(line);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  @Override
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
