package com;

import com.resources.TableInfo;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.sql.*;
import java.util.List;
import java.util.Vector;

public final class DatabaseLibrary {
    public void playSound() { //TODO Button and filename correction
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("D:/MusicPlayer/fml.mp3").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

    }

    public static void DeleteRecord(Connection conn, Statement stmt, String tablename, Integer id) {
        String sql = "";
        try {
            System.out.println("Creating delete statement...");
            switch (tablename) {
                case "uganda.person":
                    sql = "DELETE FROM " + tablename + " WHERE Person_ID = " + id;
                    break;
                case "uganda.soldier":
                    sql = "DELETE FROM " + tablename + " WHERE Soldier_ID = " + id;
                    break;
                case "uganda.commander":
                    sql = "DELETE FROM " + tablename + " WHERE Commander_ID = " + id;
                    break;
                case "uganda.engineer":
                    sql = "DELETE FROM " + tablename + " WHERE Engineer_ID = " + id;
                    break;
                case "uganda.marksman":
                    sql = "DELETE FROM " + tablename + " WHERE Marksman_ID = " + id;
                    break;
                case "uganda.tanker":
                    sql = "DELETE FROM " + tablename + " WHERE Tanker_ID = " + id;
                    break;
                case "uganda.medic":
                    sql = "DELETE FROM " + tablename + " WHERE Medic_ID = " + id;
                    break;
                case "uganda.equipment":
                    sql = "DELETE FROM " + tablename + " WHERE Equipment_ID = " + id;
                    break;
                case "uganda.weapon":
                    sql = "DELETE FROM " + tablename + " WHERE Weapon_ID = " + id;
                    break;
                case "uganda.vehicle":
                    sql = "DELETE FROM " + tablename + " WHERE Vehicle_ID = " + id;
                    break;
            }
            // String sql = "DELETE FROM "+tablename+" WHERE "+tablename+"_ID = "+id;
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            System.out.println("Czapek na peronie zwalił delete"); //Gdy połączenie nie udane.}
            se.printStackTrace();
        }

       /* try {
            //conn.commit();
        } catch (SQLException se) {
            System.out.println("Czapek na peronie nie pchnął commita na delete");
            se.printStackTrace();
        }*/
    }

    public static void AddRecord(Connection conn, Statement stmt, String tablename, List<String> paramlist) {
        Boolean isOK = false;
        String sql = "";
        switch (tablename) {
            case "uganda.person":
                isOK = ArgumentsControl.isPersonOK(paramlist);
                sql = "INSERT INTO " + tablename + " (Name, Surname, Height, Weight) VALUES (";
                break;
            case "uganda.soldier":
                isOK = ArgumentsControl.isSoldierOK(stmt, paramlist);
                sql = "INSERT INTO " + tablename + " (Soldier_ID, Kills, Rank, SupervisingUnit_ID, SupervisingSoldier_ID) VALUES (";
                break;
            case "uganda.commander":
                paramlist.subList(2, paramlist.size()).clear();
                isOK = ArgumentsControl.isCommanderOK(stmt, paramlist);
                sql = "INSERT INTO " + tablename + " (Commander_ID, SupervisedUnit_ID) VALUES (";
                break;
            case "uganda.engineer":
                paramlist.subList(3, paramlist.size()).clear();
                isOK = ArgumentsControl.isEngineerOK(stmt, paramlist);
                sql = "INSERT INTO " + tablename + " (Engineer_ID, FavouriteEnergyDrink, EngineerType) VALUES (";
                break;
            case "uganda.marksman":
                paramlist.subList(2, paramlist.size()).clear();
                isOK = ArgumentsControl.isMarksmanOK(stmt, paramlist);
                sql = "INSERT INTO " + tablename + " (Marksman_ID, HeadshotPercent) VALUES (";
                break;
            case "uganda.tanker":
                paramlist.subList(2, paramlist.size()).clear();
                isOK = ArgumentsControl.isTankerOK(stmt, paramlist);
                sql = "INSERT INTO " + tablename + " (Tanker_ID, Position) VALUES (";
                break;
            case "uganda.medic":
                paramlist.subList(2, paramlist.size()).clear();
                isOK = ArgumentsControl.isMedicOK(stmt, paramlist);
                sql = "INSERT INTO " + tablename + " (Medic_ID, Revives) VALUES (";
                break;
            case "uganda.equipment":
                paramlist.subList(3, paramlist.size()).clear();
                isOK = ArgumentsControl.isEquipmentOK(stmt, paramlist);
                sql = "INSERT INTO " + tablename + " (Type, ProductionDate, Owner_ID) VALUES (";
                break;
            case "uganda.weapon":
                paramlist.subList(4, paramlist.size()).clear();
                isOK = ArgumentsControl.isWeaponOK(stmt, paramlist);
                sql = "INSERT INTO " + tablename + " (Weapon_ID, Caliber, MagazineCapacity, ModelName) VALUES (";
                break;
            case "uganda.vehicle":
                paramlist.subList(4, paramlist.size()).clear();
                isOK = ArgumentsControl.isVehicleOK(stmt, paramlist);
                sql = "INSERT INTO " + tablename + " (Vehicle_ID, TypeofFuel, NumberOfSeats, ModelName) VALUES (";
                break;
            /*case "uganda.`organisation unit`":
                sql = "INSERT INTO " + tablename + " (Name, Type, Supervisor_ID) VALUES (";             //Do wyjebania
                paramlist.subList(3, paramlist.size()).clear();
                break;*/
        }
        if (isOK) {
            try {
                System.out.println("Creating add statement...");
                //sql = sql + "INSERT INTO "+ tablename + " (...) "+"VALUES ("; //!!!!!!!!!!!!!!!!!!!!!

                for (int i = 0; i < paramlist.size(); i++) {
                    if (i == 0) {
                        sql = sql + "'" + paramlist.get(i) + "'";
                    } else {
                        sql = sql + ", " + "'" + paramlist.get(i) + "'";
                    }
                }
                sql = sql + ")";

                stmt.executeUpdate(sql);

            } catch (SQLException se) {
                System.out.println("Czapek na peronie zwalił add"); //Gdy połączenie nie udane.}
                se.printStackTrace();
            }
        }
    }

    public static void Update(Statement stmt, String tablename, List<String> paramlist) {
        String sql = "";
        List<String> columnInfo = null;
        switch (tablename) {
            case "uganda.person":
                sql = "UPDATE " + tablename + " SET ";
                columnInfo = TableInfo.personTable;
                break;
            case "uganda.soldier":
                sql = "UPDATE " + tablename + " SET ";
                columnInfo = TableInfo.soldierTable;
                break;
            case "uganda.commander":
                sql = "UPDATE " + tablename + " SET ";
                columnInfo = TableInfo.commanderTable;
                break;
            case "uganda.marksman":
                sql = "UPDATE " + tablename + " SET ";
                columnInfo = TableInfo.marksmanTable;
                break;
            case "uganda.engineer":
                sql = "UPDATE " + tablename + " SET ";
                columnInfo = TableInfo.engineerTable;
                break;
            case "uganda.tanker":
                sql = "UPDATE " + tablename + " SET ";
                columnInfo = TableInfo.tankerTable;
                break;
            case "uganda.medic":
                sql = "UPDATE " + tablename + " SET ";
                columnInfo = TableInfo.medicTable;
                break;
            case "uganda.equipment":
                sql = "UPDATE " + tablename + " SET ";
                columnInfo = TableInfo.equipmentTable;
                break;
            case "uganda.weapon":
                sql = "UPDATE " + tablename + " SET ";
                columnInfo = TableInfo.weaponTable;
                break;
            case "uganda.vehicle":
                sql = "UPDATE " + tablename + " SET ";
                columnInfo = TableInfo.vehicleTable;
                break;
        }
        for (int i = 0; i < paramlist.size(); i++) {
            if (paramlist.get(i) != "") {   //Powiedzieć o tym Krzysztofowi!!!!!!!!!!!!!!!
                sql = sql + columnInfo.get(i) + " = " + "'" + paramlist.get(i) + "'";
                if (i < paramlist.size() - 1) {
                    sql = sql + ",";
                }
            }
        }
        sql = sql + " WHERE " + columnInfo.get(0) + " = " + paramlist.get(0);
    }
}
