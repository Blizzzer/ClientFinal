package com;

import java.util.concurrent.TimeUnit;



public class Main {


    public static void main(String[] args) throws InterruptedException {

        UI app;
        LoginDialog loginDialog;

        loginDialog = new LoginDialog(); //TODO Add use of LoginDialog Data
        loginDialog.setVisible(true);
        while(!loginDialog.isReadyToFinish()) {
            TimeUnit.SECONDS.sleep(1);
        }
        if(loginDialog.isLogged()) {
            loginDialog.setVisible(false);
            loginDialog.setEnabled(false);
            app = new UI();
            app.setVisible(true);

            while (true) { //app.getrunning()
                TimeUnit.SECONDS.sleep(1);
            }
        }
            System.exit(0);

    }
}
