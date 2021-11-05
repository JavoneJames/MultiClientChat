package process;

import javax.swing.SwingUtilities;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientMain {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      try {
        var ClientProcess = new ClientProcess(InetAddress.getByName("localhost"), 5536);
        ClientProcess.createWindowFrame();
        ClientProcess.connectToServer();
      } catch (UnknownHostException e) {
        e.printStackTrace();
      }
    });
  }
}
