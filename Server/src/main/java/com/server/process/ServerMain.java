package com.server.process;

import javax.swing.SwingUtilities;

public class ServerMain   {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerProcess();
            }
        });
    }
}
