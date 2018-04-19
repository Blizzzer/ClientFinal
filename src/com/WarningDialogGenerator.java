package com;

import javax.swing.*;

public final class WarningDialogGenerator { //TODO Nie sklejanie tekstów
    private static String[] personString = {"Name to long","Surname to long","Height not numeric","Weight not numeric","Height not in range","Weight not in range"};
    public static void personWarning(boolean[] c){
        String problemString = "";
        for(int i =0; i< c.length; i++){
            if(!c[i])
                problemString = problemString +", "+ personString[i];
        }
        JOptionPane.showMessageDialog(null,
                "Wrong data input \nPlease correct entered data\nProblem occurred in: " + problemString,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }
    private static String[] soldierString = {"Soldier ID not numeric","Soldier ID not correct ID","Kills not numeric","Incorrect Rank","Incorrect Organisation unit","Supervising Soldier ID not numeric","Supervising Soldier ID not correct ID", "ID is already taken by another soldier", "ID is already taken by commander"};
    public static void soldierWarning(boolean[] c){
        String problemString = "";
        for(int i =0; i< c.length; i++){
            if(!c[i])
                problemString = problemString +", "+ soldierString[i];
        }
        JOptionPane.showMessageDialog(null,
                "Wrong data input \nPlease correct entered data\nProblems: " + problemString,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }
    private static String[] commanderString = {"Commander ID not numeric" , "Commander ID not correct ID", "Incorrect Supervised Organisation Unit", "ID is already taken by soldier", "ID is already taken by another commander"};
    public static void commanderWarning(boolean[] c){
        String problemString = "";
        for(int i =0; i< c.length; i++){
            if(!c[i])
                problemString = problemString +", "+ commanderString[i];
        }
        JOptionPane.showMessageDialog(null,
                "Wrong data input \nPlease correct entered data\nProblems: " + problemString,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }
    private static String[] engineerString = {"Engineer ID not numeric" , "Engineer ID not correct ID", "Incorrect Energy Drink", "Incorrect Engineer Type", "ID is already taken by another engineer", "ID is already taken by marksman", "ID is already taken by tanker", "ID is already taken by medic"};
    public static void engineerWarning(boolean[] c){
        String problemString = "";
        for(int i =0; i< c.length; i++){
            if(!c[i])
                problemString = problemString +", "+ engineerString[i];
        }
        JOptionPane.showMessageDialog(null,
                "Wrong data input \nPlease correct entered data\nProblems: " + problemString,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }
    private static String[] marksmanString = {"Marksman ID not numeric" , "Marksman ID not correct ID", "Headshot percent not nuemric","Headshot percent not in range", "ID is already taken by engineer", "ID is already taken by another marksman", "ID is already taken by tanker", "ID is already taken by medic"};
    public static void marksmanWarning(boolean[] c){
        String problemString = "";
        for(int i =0; i< c.length; i++){
            if(!c[i])
                problemString = problemString +", "+ marksmanString[i];
        }
        JOptionPane.showMessageDialog(null,
                "Wrong data input \nPlease correct entered data\nProblems: " + problemString,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }
    private static String[] tankerString = {"Tanker ID not numeric" , "Tanker ID not correct ID", "Incorrect tanker position", "ID is already taken by engineer", "ID is already taken by marksman", "ID is already taken by another tanker", "ID is already taken by medic"};
    public static void tankerWarning(boolean[] c){
        String problemString = "";
        for(int i =0; i< c.length; i++){
            if(!c[i])
                problemString = problemString +", "+ tankerString[i];
        }
        JOptionPane.showMessageDialog(null,
                "Wrong data input \nPlease correct entered data\nProblems: " + problemString,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    private static String[] medicString = {"Medic ID not numeric" , "Medic ID not correct ID", "Revives not numeric", "ID is already taken by engineer", "ID is already taken by marksman", "ID is already taken by tanker", "ID is already taken by another medic"};
    public static void medicWarning(boolean[] c){
        String problemString = "";
        for(int i =0; i< c.length; i++){
            if(!c[i])
                problemString = problemString +", "+ medicString[i];
        }
        JOptionPane.showMessageDialog(null,
                "Wrong data input \nPlease correct entered data\nProblems: " + problemString,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }
    private static String[] equipmentString = {"Incorrect Equipment type","Owner ID not numeric" , "Owner ID not correct ID"};
    public static void equipmentWarning(boolean[] c){
        String problemString = "";
        for(int i =0; i< c.length; i++){
            if(!c[i])
                problemString = problemString +", "+ equipmentString[i];
        }
        JOptionPane.showMessageDialog(null,
                "Wrong data input \nPlease correct entered data\nProblems: " + problemString,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }
    private static String[] vehicleString = {"Vehicle ID not numeric" , "Vehicle ID not correct ID", "Incorrect fuel type", "Number of seats not numeric","ModelName to long", "ID is already taken by weapon", "ID is already taken by another vehicle"};
    public static void vehicleWarning(boolean[] c){
        String problemString = "";
        for(int i =0; i< c.length; i++){
            if(!c[i])
                problemString = problemString +", "+ vehicleString[i];
        }
        JOptionPane.showMessageDialog(null,
                "Wrong data input \nPlease correct entered data\nProblems: " + problemString,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }
    private static String[] weaponString = {"Weapon ID not numeric" , "Weapon ID not correct ID", "Incorrect Caliber", "Magazine capacity not numeric","ModelName to long", "ID is already taken by another weapon", "ID is already taken by vehicle"};
    public static void weaponWarning(boolean[] c){
        String problemString = "";
        for(int i =0; i< c.length; i++){
            if(!c[i])
                problemString = problemString +", "+ weaponString[i] + " ";
        }
        JOptionPane.showMessageDialog(null,
                "Wrong data input \nPlease correct entered data\nProblems: " + problemString,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }
}
