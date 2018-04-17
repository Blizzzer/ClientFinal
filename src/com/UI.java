package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import java.util.ArrayList;

public class UI extends JFrame implements WindowListener {
    private TextArea informationAddLabel;
    private Connection conn = null;
    private Statement stmt = null;
    private String serverName = "192.168.0.17";
    private String mydatabase = "uganda";
    private String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

    private String username = "JAVA";
    private String password = "papanurgle";
    private boolean running = true;
    private java.util.List<String> paramlist = null;


    UI() {
        paramlist = new ArrayList<>();
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(600, 600));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new FlowLayout());
        this.getContentPane().add(mainPanel);
        addWindowListener(this);


        JButton testButton = new JButton("Test Select");
        testButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "SELECT Name, Surname, ModelName FROM uganda.weaponview";
                try {
                    ResultSet rs = stmt.executeQuery(sql);
                    JTable table = new JTable(DatabaseLibrary.buildTableModel(rs));
                    rs.close();
                    JScrollPane scrollPane = new JScrollPane(table);
                    Runnable thread=new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            TableDialog tableDialog = new TableDialog(scrollPane);
                        }
                    };
                    SwingUtilities.invokeLater(thread);


                } catch (SQLException se) {
                    System.out.println("Select failure ...");
                    se.printStackTrace();
                }
            }

        });
        mainPanel.add(testButton);



        JLabel idDelete = new JLabel( "ID:" );
        JLabel tableDelete = new JLabel( "Table:" );
        JTextField idDeleteTextField = new JTextField();
        String[] tableDeleteNamesStrings = { "Person","Equipment","Soldier","Commander","Marksman", "Medic","Engineer","Tanker","Weapon","Vehicle" };

        JComboBox tableDeleteList = new JComboBox(tableDeleteNamesStrings);
        JButton okDeleteButton = new JButton("OK");
        okDeleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String tableNameToDelete;
                System.out.println("OK, Delete");
                String id = idDeleteTextField.getText();
                String table = (String)tableDeleteList.getSelectedItem();
                System.out.println(table);

                switch (table) {
                    case "Person":
                        tableNameToDelete = "uganda.person";
                        break;
                    case "Equipment":
                        tableNameToDelete = "uganda.equipment";
                        break;
                    case "Weapon":
                        tableNameToDelete = "uganda.weapon";
                        break;
                    case "Vehicle":
                        tableNameToDelete = "uganda.vehicle";
                        break;
                    case "Soldier":
                        tableNameToDelete = "uganda.commander";
                        break;
                    case "Commander":
                        tableNameToDelete = "uganda.soldier";
                        break;
                    case "Marksman":
                        tableNameToDelete = "uganda.marksman";
                        break;
                    case "Medic":
                        tableNameToDelete = "uganda.medic";
                        break;
                    case "Engineer":
                        tableNameToDelete = "uganda.engineer";
                        break;
                    case "Tanker":
                        tableNameToDelete = "uganda.tanker";
                        break;

                    default:
                        tableNameToDelete = null;
                        break;
                }
                System.out.println(tableNameToDelete);
                System.out.println(id);
                DatabaseLibrary.DeleteRecord(conn,stmt,tableNameToDelete,Integer.parseInt(id));
            }

        });

        JPanel deletePanel = new JPanel();
        deletePanel.setBorder(BorderFactory.createTitledBorder("Delete record"));
        deletePanel.setPreferredSize(new Dimension(300, 120));
        GroupLayout deleteLayout = new GroupLayout(deletePanel);
        deleteLayout.setAutoCreateGaps(true);
        deleteLayout.setAutoCreateContainerGaps(true);
        GroupLayout.SequentialGroup deleteHGroup = deleteLayout.createSequentialGroup();
        deleteHGroup.addGroup(deleteLayout.createParallelGroup().
                addComponent(idDelete).addComponent(tableDelete).addComponent(okDeleteButton));
        deleteHGroup.addGroup(deleteLayout.createParallelGroup().
                addComponent(idDeleteTextField).addComponent(tableDeleteList));
        deleteLayout.setHorizontalGroup(deleteHGroup);

        GroupLayout.SequentialGroup deleteVGroup = deleteLayout.createSequentialGroup();
        deleteVGroup.addGroup(deleteLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(idDelete).addComponent(idDeleteTextField));
        deleteVGroup.addGroup(deleteLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(tableDelete).addComponent(tableDeleteList));
        deleteVGroup.addGroup(deleteLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(okDeleteButton));
        deleteLayout.setVerticalGroup(deleteVGroup);
        deletePanel.setLayout(deleteLayout);
        mainPanel.add(deletePanel);







        informationAddLabel = new TextArea( com.resources.AddInformationStrings.soldierInformationText );
        informationAddLabel.setEditable(false);
        JLabel tableAddLabel = new JLabel( "Table name:" );
        JLabel firstAddLabel = new JLabel( "Soldier_ID:" );
        JLabel secondAddLabel = new JLabel( "Kills:" );
        JLabel thirdAddLabel = new JLabel( "Rank:" );
        JLabel fourthAddLabel = new JLabel( "Supervising Unit ID:" );
        JLabel fifthAddLabel = new JLabel( "Supervising Soldier ID:" );
        JTextField firstAddTextField = new JTextField();
        JTextField secondAddTextField = new JTextField();
        JTextField thirdAddTextField = new JTextField();
        JTextField fourthAddTextField = new JTextField();
        JTextField fifthAddTextField = new JTextField();
        String[] tableNamesStrings = {"uganda.soldier", "uganda.person","uganda.medic", "uganda.marksman", "uganda.tanker", "uganda.engineer",
                "uganda.weapon", "uganda.equipment", "uganda.vehicle", "uganda.commander"};

        JComboBox tableNamesAddList = new JComboBox(tableNamesStrings);
        tableNamesAddList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String value = (String)tableNamesAddList.getSelectedItem();
                firstAddTextField.setText("");
                secondAddTextField.setText("");
                thirdAddTextField.setText("");
                fourthAddTextField.setText("");
                fifthAddTextField.setText("");
                System.out.println(value);
                switch (value) {
                    case "uganda.person":
                        informationAddLabel.setText(com.resources.AddInformationStrings.personInformationText);
                        firstAddLabel.setText("Name: ");secondAddLabel.setText("Surname: ");
                        thirdAddLabel.setText("Height: ");fourthAddLabel.setText("Weight: ");
                        firstAddLabel.setVisible(true);firstAddTextField.setVisible(true);
                        secondAddLabel.setVisible(true);secondAddTextField.setVisible(true);
                        thirdAddLabel.setVisible(true);thirdAddTextField.setVisible(true);
                        fourthAddLabel.setVisible(true);fourthAddTextField.setVisible(true);
                        fifthAddLabel.setVisible(false);fifthAddTextField.setVisible(false);

                        break;
                    case "uganda.soldier":
                        informationAddLabel.setText(com.resources.AddInformationStrings.soldierInformationText );
                        firstAddLabel.setText("Soldier_ID: ");secondAddLabel.setText("Kills: ");
                        thirdAddLabel.setText("Rank: ");fourthAddLabel.setText("SupervisingUnit_ID: ");
                        fifthAddLabel.setText("SupervisingSoldier_ID");
                        firstAddLabel.setVisible(true);firstAddTextField.setVisible(true);
                        secondAddLabel.setVisible(true);secondAddTextField.setVisible(true);
                        thirdAddLabel.setVisible(true);thirdAddTextField.setVisible(true);
                        fourthAddLabel.setVisible(true);fourthAddTextField.setVisible(true);
                        fifthAddLabel.setVisible(true);fifthAddTextField.setVisible(true);

                        break;
                    case "uganda.medic":
                        informationAddLabel.setText(com.resources.AddInformationStrings.medicInformationText );
                        firstAddLabel.setText("Medic_ID: "); secondAddLabel.setText("Revives: ");
                        firstAddLabel.setVisible(true);firstAddTextField.setVisible(true);
                        secondAddLabel.setVisible(true);secondAddTextField.setVisible(true);
                        thirdAddLabel.setVisible(false);thirdAddTextField.setVisible(false);
                        fourthAddLabel.setVisible(false);fourthAddTextField.setVisible(false);
                        fifthAddLabel.setVisible(false);fifthAddTextField.setVisible(false);
                        break;
                    case "uganda.engineer":
                        informationAddLabel.setText(com.resources.AddInformationStrings.engineerInformationText );
                        firstAddLabel.setText("Engineer_ID: ");secondAddLabel.setText("Favourite energy drink: ");
                        thirdAddLabel.setText("Engineer Type");
                        firstAddLabel.setVisible(true);firstAddTextField.setVisible(true);
                        secondAddLabel.setVisible(true);secondAddTextField.setVisible(true);
                        thirdAddLabel.setVisible(true);thirdAddTextField.setVisible(true);
                        fourthAddLabel.setVisible(false);fourthAddTextField.setVisible(false);
                        fifthAddLabel.setVisible(false);fifthAddTextField.setVisible(false);
                        break;
                    case "uganda.tanker":
                        informationAddLabel.setText(com.resources.AddInformationStrings.tankerInformationText );
                        firstAddLabel.setText("Tanker_ID: "); secondAddLabel.setText("Position: ");
                        firstAddLabel.setVisible(true);firstAddTextField.setVisible(true);
                        secondAddLabel.setVisible(true);secondAddTextField.setVisible(true);
                        thirdAddLabel.setVisible(false);thirdAddTextField.setVisible(false);
                        fourthAddLabel.setVisible(false);fourthAddTextField.setVisible(false);
                        fifthAddLabel.setVisible(false);fifthAddTextField.setVisible(false);
                        break;
                    case "uganda.marksman":
                        informationAddLabel.setText(com.resources.AddInformationStrings.marksmanInformationText );
                        firstAddLabel.setText("Marksman_ID: "); secondAddLabel.setText("HeadshotPercent: ");
                        firstAddLabel.setVisible(true);firstAddTextField.setVisible(true);
                        secondAddLabel.setVisible(true);secondAddTextField.setVisible(true);
                        thirdAddLabel.setVisible(false);thirdAddTextField.setVisible(false);
                        fourthAddLabel.setVisible(false);fourthAddTextField.setVisible(false);
                        fifthAddLabel.setVisible(false);fifthAddTextField.setVisible(false);
                        break;
                    case "uganda.equipment":
                        informationAddLabel.setText( com.resources.AddInformationStrings.equipmentInformationText );
                        firstAddLabel.setText("Type: ");secondAddLabel.setText("ProductionDate: ");
                        thirdAddLabel.setText("Owner_ID: ");
                        firstAddLabel.setVisible(true);firstAddTextField.setVisible(true);
                        secondAddLabel.setVisible(true);secondAddTextField.setVisible(true);
                        thirdAddLabel.setVisible(true);thirdAddTextField.setVisible(true);
                        fourthAddLabel.setVisible(false);fourthAddTextField.setVisible(false);
                        fifthAddLabel.setVisible(false);fifthAddTextField.setVisible(false);
                        break;
                    case "uganda.vehicle":
                        informationAddLabel.setText(com.resources.AddInformationStrings.vehicleInformationText );
                        firstAddLabel.setText("Weapon_ID: ");secondAddLabel.setText("Type of fuel: ");
                        thirdAddLabel.setText("Number of seats: "); fourthAddLabel.setText("ModelName");
                        firstAddLabel.setVisible(true);firstAddTextField.setVisible(true);
                        secondAddLabel.setVisible(true);secondAddTextField.setVisible(true);
                        thirdAddLabel.setVisible(true);thirdAddTextField.setVisible(true);
                        fourthAddLabel.setVisible(true);fourthAddTextField.setVisible(true);
                        fifthAddLabel.setVisible(false);fifthAddTextField.setVisible(false);
                        break;
                    case "uganda.weapon":
                        informationAddLabel.setText(com.resources.AddInformationStrings.weaponInformationText );
                        firstAddLabel.setText("Weapon_ID: ");secondAddLabel.setText("Caliber: ");
                        thirdAddLabel.setText("MagazineCapacity: "); fourthAddLabel.setText("ModelName");
                        firstAddLabel.setVisible(true);firstAddTextField.setVisible(true);
                        secondAddLabel.setVisible(true);secondAddTextField.setVisible(true);
                        thirdAddLabel.setVisible(true);thirdAddTextField.setVisible(true);
                        fourthAddLabel.setVisible(true);fourthAddTextField.setVisible(true);
                        fifthAddLabel.setVisible(false);fifthAddTextField.setVisible(false);
                        break;
                    case "uganda.commander":
                        informationAddLabel.setText(com.resources.AddInformationStrings.commanderInformationText );
                        firstAddLabel.setText("Commander_ID");; secondAddLabel.setText("Supervised Unit ID: ");
                        firstAddLabel.setVisible(true);firstAddTextField.setVisible(true);
                        secondAddLabel.setVisible(true);secondAddTextField.setVisible(true);
                        thirdAddLabel.setVisible(false);thirdAddTextField.setVisible(false);
                        fourthAddLabel.setVisible(false);fourthAddTextField.setVisible(false);
                        fifthAddLabel.setVisible(false);fifthAddTextField.setVisible(false);
                        break;
                }
            }

        });

        JButton okAddButton = new JButton("OK");
        okAddButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                paramlist.clear();
                for(int i =0; i<5; i++){

                    paramlist.add(firstAddTextField.getText());
                    paramlist.add(secondAddTextField.getText());
                    paramlist.add(thirdAddTextField.getText());
                    paramlist.add(fourthAddTextField.getText());
                    paramlist.add(fifthAddTextField.getText());
                }
                DatabaseLibrary.AddRecord(conn,stmt,(String)tableNamesAddList.getSelectedItem(),paramlist);
                System.out.println("OK, Add");
                String value = (String)tableNamesAddList.getSelectedItem();
                System.out.println(value);
            }

        });
        JPanel addPanel = new JPanel();
        addPanel.setBorder(BorderFactory.createTitledBorder("Add record"));
        addPanel.setPreferredSize(new Dimension(400, 400));
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        mainPanel.add(addPanel);

        JPanel addValuesPanel = new JPanel();
        addValuesPanel.setPreferredSize(new Dimension(400, 400));
        GroupLayout addLayout = new GroupLayout(addValuesPanel);
        addLayout.setAutoCreateGaps(true);
        addLayout.setAutoCreateContainerGaps(true);


        GroupLayout.SequentialGroup addHGroup = addLayout.createSequentialGroup();
        addHGroup.addGroup(addLayout.createParallelGroup().
                addComponent(tableAddLabel).addComponent(firstAddLabel).addComponent(secondAddLabel).addComponent(thirdAddLabel)
                .addComponent(fourthAddLabel).addComponent(fifthAddLabel).addComponent(okAddButton));
        addHGroup.addGroup(addLayout.createParallelGroup().
                addComponent(tableNamesAddList).addComponent(firstAddTextField).addComponent(secondAddTextField)
                .addComponent(thirdAddTextField).addComponent(fourthAddTextField).addComponent(fifthAddTextField));
        addLayout.setHorizontalGroup(addHGroup);

        GroupLayout.SequentialGroup addVGroup = addLayout.createSequentialGroup();
        addVGroup.addGroup(addLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(tableAddLabel).addComponent(tableNamesAddList));
        addVGroup.addGroup(addLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(firstAddLabel).addComponent(firstAddTextField));
        addVGroup.addGroup(addLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(secondAddLabel).addComponent(secondAddTextField));
        addVGroup.addGroup(addLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(thirdAddLabel).addComponent(thirdAddTextField));
        addVGroup.addGroup(addLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(fourthAddLabel).addComponent(fourthAddTextField));
        addVGroup.addGroup(addLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(fifthAddLabel).addComponent(fifthAddTextField));
        addVGroup.addGroup(addLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(okAddButton));


        addLayout.setVerticalGroup(addVGroup);
        addValuesPanel.setLayout(addLayout);

        addPanel.add(informationAddLabel);
        addPanel.add(addValuesPanel);


        JPanel selectPanel = new JPanel();
        selectPanel.setBorder(BorderFactory.createTitledBorder("Select record"));
        selectPanel.setPreferredSize(new Dimension(400, 400));
        selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.Y_AXIS));
        mainPanel.add(selectPanel);

        JComboBox tableNamesSelectList = new JComboBox(tableNamesStrings);
        mainPanel.add(tableNamesSelectList);
        //TODO JList + JButton






        this.setTitle("Uganda Client App");
        this.pack();
        this.setVisible(true);


        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
        } catch (SQLException se) {
            dispose();
            se.printStackTrace();
            //System.exit(0);
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException see) {
                //see.printStackTrace();
            }
        }

    }
    public void windowClosing(WindowEvent e) {
        running = false;
        System.out.println("Switching running");
    }

    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}

    public boolean getrunning(){
        return running;
    }

}
