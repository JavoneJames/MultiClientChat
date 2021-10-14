package com.application.server;

public class ServerProcess extends ServerGUI implements Runnable{

    ServerProcess(){
        createWindowFrame();
    }

    @Override
    public void run() {
        submitButton.addActionListener(e ->{
            if (e.getSource() == super.submitButton)
               if (super.inputTextField.getText().isEmpty())
                   System.out.println("empty");
        });
    }
}
