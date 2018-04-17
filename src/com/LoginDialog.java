package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDialog extends JFrame implements WindowListener {
    private boolean Logged = false;
    private boolean readyToFinish = false;
    private Connection conn = null;
    private Statement stmt = null;
    private String driverName = "com.mysql.jdbc.Driver";
    private String serverName = null;
    private String mydatabase = null;

    private String url = null; //zwrócić
    private String username = null; //zwrócić
    private String password = null; //zwrócić

    LoginDialog() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(300, 160));
        GroupLayout mainLayout = new GroupLayout(mainPanel);
        mainLayout.setAutoCreateGaps(true);
        mainLayout.setAutoCreateContainerGaps(true);

        JLabel loginLabel = new JLabel( "Login:" );
        JLabel passwordLabel = new JLabel( "Password:" );
        JLabel serverLabel = new JLabel( "Server IP:" );
        JLabel databaseLabel = new JLabel( "Database:" );
        JTextField loginTextField = new JTextField();
        JTextField passwordTextField = new JTextField();
        JTextField serverTextField = new JTextField();
        JTextField databaseTextField = new JTextField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                username = loginTextField.getText();
                password = passwordTextField.getText();
                serverName = serverTextField.getText();
                mydatabase = databaseTextField.getText();
                try {
                    Class.forName(driverName); // here is the ClassNotFoundException
                } catch (ClassNotFoundException ce) {
                    System.out.println("Class not found");
                }

                String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
                try {
                    conn = DriverManager.getConnection(url, username, password);
                    System.out.println("Creating statement...");
                    stmt = conn.createStatement();
                    Logged = true;
                }catch(SQLException se){
                    Logged = false;
                    JOptionPane.showMessageDialog(null,
                            "Login failed",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                }finally {
                    //finally block used to close resources
                    try {
                        if (stmt != null)
                            stmt.close();
                    } catch (SQLException se) {
                    }// do nothing
                    try {
                        if (conn != null)
                            conn.close();
                    } catch (SQLException se) {
                        se.printStackTrace();
                    }
                }
                if(Logged)
                    readyToFinish = true;
            }

        });

        JButton leaveButton = new JButton("Leave");
        leaveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                readyToFinish = true;
                Logged = true; //TODO change to false
            }

        });


        GroupLayout.SequentialGroup mainHGroup = mainLayout.createSequentialGroup();
        mainHGroup.addGroup(mainLayout.createParallelGroup().
                addComponent(loginLabel).addComponent(passwordLabel).addComponent(serverLabel).addComponent(databaseLabel).addComponent(loginButton));
        mainHGroup.addGroup(mainLayout.createParallelGroup().
                addComponent(loginTextField).addComponent(passwordTextField).addComponent(serverTextField).addComponent(databaseTextField).addComponent(leaveButton));
        mainLayout.setHorizontalGroup(mainHGroup);

        GroupLayout.SequentialGroup mainVGroup = mainLayout.createSequentialGroup();
        mainVGroup.addGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(loginLabel).addComponent(loginTextField));
        mainVGroup.addGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(passwordLabel).addComponent(passwordTextField));
        mainVGroup.addGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(serverLabel).addComponent(serverTextField));
        mainVGroup.addGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(databaseLabel).addComponent(databaseTextField));
        mainVGroup.addGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(loginButton).addComponent(leaveButton));
        mainLayout.setVerticalGroup(mainVGroup);
        mainPanel.setLayout(mainLayout);

        this.getContentPane().add(mainPanel);
        addWindowListener(this);
        this.setTitle("Uganda Login Dialog");
        this.pack();
    }
    public void windowClosing(WindowEvent e) {
        System.out.println("Switching running dialog");
        readyToFinish = true;
        Logged = false;
    }

    public boolean isLogged(){
        return Logged;
    }
    public boolean isReadyToFinish(){
        return readyToFinish;
    }
    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}

}
