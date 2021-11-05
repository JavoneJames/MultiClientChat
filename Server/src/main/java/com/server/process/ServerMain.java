package com.server.process;

import javax.swing.SwingUtilities;

public class ServerMain {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new ServerProcess(5536).createWindowFrame());
  }
}
