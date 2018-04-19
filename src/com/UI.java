package com;

import com.resources.TableInfo;

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
    private String serverName = "localhost";
    private String mydatabase = "uganda";
    private String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

    private String username = "JAVA";
    private String password = "papanurgle";
    private boolean running = true;
    private java.util.List<String> paramlist = null;
    private java.util.List<String> paramUpdateList = null;
    JList<String> selectColumnsList;
    JScrollPane selectScrollPane;


    UI(/*String url, String username, String password*/) {
        //this.url = url;
        //this.username = username;
        //this.password = password;
        paramlist = new ArrayList<>();
        paramUpdateList = new ArrayList<>();
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1200, 600));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        this.getContentPane().add(mainPanel);
        addWindowListener(this);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(this);
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(300, 600));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        mainPanel.add(leftPanel);

        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(300, 600));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        mainPanel.add(middlePanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(300, 600));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        mainPanel.add(rightPanel);

        /*JButton testButton = new JButton("Test Select");
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
        mainPanel.add(testButton);*/



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
                        tableNameToDelete = "uganda.soldier";
                        break;
                    case "Commander":
                        tableNameToDelete = "uganda.commander";
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
        deletePanel.setPreferredSize(new Dimension(300, 140));
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
        leftPanel.add(deletePanel);







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
        String[] tableNamesForSelectStrings = {"uganda.soldier", "uganda.person","uganda.medic", "uganda.marksman", "uganda.tanker", "uganda.engineer",
                "uganda.weapon", "uganda.equipment", "uganda.vehicle", "uganda.commander","uganda.`organisation unit`"};
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
                paramlist.add(firstAddTextField.getText());
                paramlist.add(secondAddTextField.getText());
                paramlist.add(thirdAddTextField.getText());
                paramlist.add(fourthAddTextField.getText());
                paramlist.add(fifthAddTextField.getText());
                DatabaseLibrary.AddRecord(conn,stmt,(String)tableNamesAddList.getSelectedItem(),paramlist);
                System.out.println("OK, Add");
                String value = (String)tableNamesAddList.getSelectedItem();
                System.out.println(value);
            }

        });
        JPanel addPanel = new JPanel();
        addPanel.setBorder(BorderFactory.createTitledBorder("Add record"));
        addPanel.setPreferredSize(new Dimension(400, 450));
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        middlePanel.add(addPanel);

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
        selectPanel.setPreferredSize(new Dimension(400, 300));
        selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.Y_AXIS));
        leftPanel.add(selectPanel);

        JComboBox tableNamesSelectList = new JComboBox(tableNamesForSelectStrings);

        selectPanel.add(tableNamesSelectList);
        //TODO JButton
        selectColumnsList = new JList<>();
        selectColumnsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        selectColumnsList.setListData(TableInfo.soldierTable);
        selectScrollPane = new JScrollPane(selectColumnsList);
        tableNamesSelectList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String selectValue = (String) tableNamesSelectList.getSelectedItem();

                switch (selectValue) {
                    case "uganda.person":
                        selectColumnsList.setListData(TableInfo.personTable);
                        break;
                    case "uganda.soldier":
                        selectColumnsList.setListData(TableInfo.soldierTable);
                        break;
                    case "uganda.medic":
                        selectColumnsList.setListData(TableInfo.medicTable);
                        break;
                    case "uganda.engineer":
                        selectColumnsList.setListData(TableInfo.engineerTable);
                        break;
                    case "uganda.tanker":
                        selectColumnsList.setListData(TableInfo.tankerTable);
                        break;
                    case "uganda.marksman":
                        selectColumnsList.setListData(TableInfo.marksmanTable);
                        break;
                    case "uganda.equipment":
                        selectColumnsList.setListData(TableInfo.equipmentTable);
                        break;
                    case "uganda.vehicle":
                        selectColumnsList.setListData(TableInfo.vehicleTable);
                        break;
                    case "uganda.weapon":
                        selectColumnsList.setListData(TableInfo.weaponTable);
                        break;
                    case "uganda.commander":
                        selectColumnsList.setListData(TableInfo.commanderTable);
                        break;
                }

            }
        });
        selectPanel.add(selectScrollPane);
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseLibrary.Select(selectColumnsList.getSelectedValuesList(),stmt,(String)tableNamesSelectList.getSelectedItem());
            }

        });
        selectPanel.add(selectButton);




        JLabel tableUpdateLabel = new JLabel( "Table name:" );
        JLabel firstUpdateLabel = new JLabel( "Soldier_ID:" );
        JLabel secondUpdateLabel = new JLabel( "Kills:" );
        JLabel thirdUpdateLabel = new JLabel( "Rank:" );
        JLabel fourthUpdateLabel = new JLabel( "Supervising Unit ID:" );
        JLabel fifthUpdateLabel = new JLabel( "Supervising Soldier ID:" );
        JTextField firstUpdateTextField = new JTextField();
        JTextField secondUpdateTextField = new JTextField();
        JTextField thirdUpdateTextField = new JTextField();
        JTextField fourthUpdateTextField = new JTextField();
        JTextField fifthUpdateTextField = new JTextField();
        firstUpdateTextField.setText("");
        secondUpdateTextField.setText("");
        thirdUpdateTextField.setText("");
        fourthUpdateTextField.setText("");
        fifthUpdateTextField.setText("");
        JComboBox tableNamesUpdateList = new JComboBox(tableNamesStrings);
        tableNamesUpdateList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String value = (String)tableNamesUpdateList.getSelectedItem();
                firstUpdateTextField.setText("");
                secondUpdateTextField.setText("");
                thirdUpdateTextField.setText("");
                fourthUpdateTextField.setText("");
                fifthUpdateTextField.setText("");
                System.out.println(value);
                switch (value) {
                    case "uganda.person":
                        firstUpdateLabel.setText("Person_ID");
                        secondUpdateLabel.setText("Name: ");thirdUpdateLabel.setText("Surname: ");
                        fourthUpdateLabel.setText("Height: ");fifthUpdateLabel.setText("Weight: ");
                        firstUpdateLabel.setVisible(true);firstUpdateTextField.setVisible(true);
                        secondUpdateLabel.setVisible(true);secondUpdateTextField.setVisible(true);
                        thirdUpdateLabel.setVisible(true);thirdUpdateTextField.setVisible(true);
                        fourthUpdateLabel.setVisible(true);fourthUpdateTextField.setVisible(true);
                        fifthUpdateLabel.setVisible(true);fifthUpdateTextField.setVisible(true);

                        break;
                    case "uganda.soldier":
                        firstUpdateLabel.setText("Soldier_ID: ");secondUpdateLabel.setText("Kills: ");
                        thirdUpdateLabel.setText("Rank: ");fourthUpdateLabel.setText("SupervisingUnit_ID: ");
                        fifthUpdateLabel.setText("SupervisingSoldier_ID");
                        firstUpdateLabel.setVisible(true);firstUpdateTextField.setVisible(true);
                        secondUpdateLabel.setVisible(true);secondUpdateTextField.setVisible(true);
                        thirdUpdateLabel.setVisible(true);thirdUpdateTextField.setVisible(true);
                        fourthUpdateLabel.setVisible(true);fourthUpdateTextField.setVisible(true);
                        fifthUpdateLabel.setVisible(true);fifthUpdateTextField.setVisible(true);

                        break;
                    case "uganda.medic":
                        firstUpdateLabel.setText("Medic_ID: "); secondUpdateLabel.setText("Revives: ");
                        firstUpdateLabel.setVisible(true);firstUpdateTextField.setVisible(true);
                        secondUpdateLabel.setVisible(true);secondUpdateTextField.setVisible(true);
                        thirdUpdateLabel.setVisible(false);thirdUpdateTextField.setVisible(false);
                        fourthUpdateLabel.setVisible(false);fourthUpdateTextField.setVisible(false);
                        fifthUpdateLabel.setVisible(false);fifthUpdateTextField.setVisible(false);
                        break;
                    case "uganda.engineer":
                        firstUpdateLabel.setText("Engineer_ID: ");secondUpdateLabel.setText("Favourite energy drink: ");
                        thirdUpdateLabel.setText("Engineer Type");
                        firstUpdateLabel.setVisible(true);firstUpdateTextField.setVisible(true);
                        secondUpdateLabel.setVisible(true);secondUpdateTextField.setVisible(true);
                        thirdUpdateLabel.setVisible(true);thirdUpdateTextField.setVisible(true);
                        fourthUpdateLabel.setVisible(false);fourthUpdateTextField.setVisible(false);
                        fifthUpdateLabel.setVisible(false);fifthUpdateTextField.setVisible(false);
                        break;
                    case "uganda.tanker":
                        firstUpdateLabel.setText("Tanker_ID: "); secondUpdateLabel.setText("Position: ");
                        firstUpdateLabel.setVisible(true);firstUpdateTextField.setVisible(true);
                        secondUpdateLabel.setVisible(true);secondUpdateTextField.setVisible(true);
                        thirdUpdateLabel.setVisible(false);thirdUpdateTextField.setVisible(false);
                        fourthUpdateLabel.setVisible(false);fourthUpdateTextField.setVisible(false);
                        fifthUpdateLabel.setVisible(false);fifthUpdateTextField.setVisible(false);
                        break;
                    case "uganda.marksman":
                        firstUpdateLabel.setText("Marksman_ID: "); secondUpdateLabel.setText("HeadshotPercent: ");
                        firstUpdateLabel.setVisible(true);firstUpdateTextField.setVisible(true);
                        secondUpdateLabel.setVisible(true);secondUpdateTextField.setVisible(true);
                        thirdUpdateLabel.setVisible(false);thirdUpdateTextField.setVisible(false);
                        fourthUpdateLabel.setVisible(false);fourthUpdateTextField.setVisible(false);
                        fifthUpdateLabel.setVisible(false);fifthUpdateTextField.setVisible(false);
                        break;
                    case "uganda.equipment":
                        firstUpdateLabel.setText("Equipment_ID");
                        secondUpdateLabel.setText("Type: ");fourthUpdateLabel.setText("ProductionDate: ");
                        fourthUpdateLabel.setText("Owner_ID: ");
                        firstUpdateLabel.setVisible(true);firstUpdateTextField.setVisible(true);
                        secondUpdateLabel.setVisible(true);secondUpdateTextField.setVisible(true);
                        thirdUpdateLabel.setVisible(true);thirdUpdateTextField.setVisible(true);
                        fourthUpdateLabel.setVisible(true);fourthUpdateTextField.setVisible(true);
                        fifthUpdateLabel.setVisible(false);fifthUpdateTextField.setVisible(false);
                        break;
                    case "uganda.vehicle":
                        firstUpdateLabel.setText("Weapon_ID: ");secondUpdateLabel.setText("Type of fuel: ");
                        thirdUpdateLabel.setText("Number of seats: "); fourthUpdateLabel.setText("ModelName");
                        firstUpdateLabel.setVisible(true);firstUpdateTextField.setVisible(true);
                        secondUpdateLabel.setVisible(true);secondUpdateTextField.setVisible(true);
                        thirdUpdateLabel.setVisible(true);thirdUpdateTextField.setVisible(true);
                        fourthUpdateLabel.setVisible(true);fourthUpdateTextField.setVisible(true);
                        fifthUpdateLabel.setVisible(false);fifthUpdateTextField.setVisible(false);
                        break;
                    case "uganda.weapon":
                        firstUpdateLabel.setText("Weapon_ID: ");secondUpdateLabel.setText("Caliber: ");
                        thirdUpdateLabel.setText("MagazineCapacity: "); fourthUpdateLabel.setText("ModelName");
                        firstUpdateLabel.setVisible(true);firstUpdateTextField.setVisible(true);
                        secondUpdateLabel.setVisible(true);secondUpdateTextField.setVisible(true);
                        thirdUpdateLabel.setVisible(true);thirdUpdateTextField.setVisible(true);
                        fourthUpdateLabel.setVisible(true);fourthUpdateTextField.setVisible(true);
                        fifthUpdateLabel.setVisible(false);fifthUpdateTextField.setVisible(false);
                        break;
                    case "uganda.commander":
                        firstUpdateLabel.setText("Commander_ID");; secondUpdateLabel.setText("Supervised Unit ID: ");
                        firstUpdateLabel.setVisible(true);firstUpdateTextField.setVisible(true);
                        secondUpdateLabel.setVisible(true);secondUpdateTextField.setVisible(true);
                        thirdUpdateLabel.setVisible(false);thirdUpdateTextField.setVisible(false);
                        fourthUpdateLabel.setVisible(false);fourthUpdateTextField.setVisible(false);
                        fifthUpdateLabel.setVisible(false);fifthUpdateTextField.setVisible(false);
                        break;
                }
            }

        });

        JButton okUpdateButton = new JButton("OK");
        okUpdateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                paramUpdateList.clear();
                paramUpdateList.add(firstUpdateTextField.getText());
                paramUpdateList.add(secondUpdateTextField.getText());
                paramUpdateList.add(thirdUpdateTextField.getText());
                paramUpdateList.add(fourthUpdateTextField.getText());
                paramUpdateList.add(fifthUpdateTextField.getText());
                DatabaseLibrary.Update(stmt,(String)tableNamesUpdateList.getSelectedItem(),paramUpdateList);
            }
        });
        JPanel updatePanel = new JPanel();
        updatePanel.setBorder(BorderFactory.createTitledBorder("Update record"));
        updatePanel.setPreferredSize(new Dimension(300, 600));
        updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.Y_AXIS));
        rightPanel.add(updatePanel);

        JPanel updateValuesPanel = new JPanel();
        updateValuesPanel.setPreferredSize(new Dimension(400, 400));
        GroupLayout updateLayout = new GroupLayout(updateValuesPanel);
        updateLayout.setAutoCreateGaps(true);
        updateLayout.setAutoCreateContainerGaps(true);


        GroupLayout.SequentialGroup updateHGroup = updateLayout.createSequentialGroup();
        updateHGroup.addGroup(updateLayout.createParallelGroup().
                addComponent(tableUpdateLabel).addComponent(firstUpdateLabel).addComponent(secondUpdateLabel).addComponent(thirdUpdateLabel)
                .addComponent(fourthUpdateLabel).addComponent(fifthUpdateLabel).addComponent(okUpdateButton));
        updateHGroup.addGroup(updateLayout.createParallelGroup().
                addComponent(tableNamesUpdateList).addComponent(firstUpdateTextField).addComponent(secondUpdateTextField)
                .addComponent(thirdUpdateTextField).addComponent(fourthUpdateTextField).addComponent(fifthUpdateTextField));
        updateLayout.setHorizontalGroup(updateHGroup);

        GroupLayout.SequentialGroup updateVGroup = updateLayout.createSequentialGroup();
        updateVGroup.addGroup(updateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(tableUpdateLabel).addComponent(tableNamesUpdateList));
        updateVGroup.addGroup(updateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(firstUpdateLabel).addComponent(firstUpdateTextField));
        updateVGroup.addGroup(updateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(secondUpdateLabel).addComponent(secondUpdateTextField));
        updateVGroup.addGroup(updateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(thirdUpdateLabel).addComponent(thirdUpdateTextField));
        updateVGroup.addGroup(updateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(fourthUpdateLabel).addComponent(fourthUpdateTextField));
        updateVGroup.addGroup(updateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(fifthUpdateLabel).addComponent(fifthUpdateTextField));
        updateVGroup.addGroup(updateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(okUpdateButton));


        updateLayout.setVerticalGroup(updateVGroup);
        updateValuesPanel.setLayout(updateLayout);

        updatePanel.add(updateValuesPanel);





        this.setTitle("Uganda Client App");
        this.pack();
        this.setVisible(true);

        DatabaseLibrary.playSound();
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
