package com.application.server;

import javax.swing.*;

public class ServerMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ServerProcess());
    }
}
