package process;

import gui.ClientGUI;

import javax.swing.SwingUtilities;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientProcess extends ClientGUI implements Runnable {
    private final InetAddress localhost;
    int port;

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
        try {
            Socket socket = new Socket(this.localhost, this.port);
            if (!socket.isConnected()) {
                displayServerFeed.append("cannot connect to server\n");
                throw new ConnectException("cannot connect to server");
            }
            displayServerFeed.append("connect to server\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
