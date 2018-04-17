package com;

import java.util.concurrent.TimeUnit;



public class Main {


    public static void main(String[] args) throws InterruptedException {
        //String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        //private String username = "JAVA";
        //private String password = "papanurgle";
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
            app = new UI(/*loginDialog.getUrl(),loginDialog.getUsername(),loginDialog.getPassword()*/);
            app.setVisible(true);

            while (app.getrunning()) { //app.getrunning()
                TimeUnit.SECONDS.sleep(1);
            }
        }
            System.exit(0);

    }
}
