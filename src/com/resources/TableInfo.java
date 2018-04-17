package com.resources;

import java.util.Arrays;
import java.util.Vector;

public class TableInfo {
    public static Vector<String> personTable = new Vector<>(Arrays.asList("Person_ID", "Name", "Surname", "Height", "Weight"));
    public static Vector<String> soldierTable = new Vector<>(Arrays.asList("Soldier_ID", "Kills", "Rank", "SupervisingUnit_ID", "SupervisingSoldier_ID"));
    public static Vector<String> engineerTable = new Vector<>(Arrays.asList("Engineer_ID", "FavouriteEnergyDrink", "EngineerType"));
    public static Vector<String> marksmanTable = new Vector<>(Arrays.asList("Marksman_ID", "HeadshotPercent"));
    public static Vector<String> tankerTable = new Vector<>(Arrays.asList("Tanker_ID", "Position"));
    public static Vector<String> medicTable = new Vector<>(Arrays.asList("Medic_ID", "Revives"));
    public static Vector<String> commanderTable = new Vector<>(Arrays.asList("Commander_ID", "SupervisedUnit_ID"));
    public static Vector<String> equipmentTable = new Vector<>(Arrays.asList("Equipment_ID", "Type", "ProductionDate", "Owner_ID"));
    public static Vector<String> weaponTable = new Vector<>(Arrays.asList("Weapon_ID", "Caliber", "MagazineCapacity", "ModelName"));
    public static Vector<String> vehicleTable = new Vector<>(Arrays.asList("Vehicle_ID", "TypeOfFuel", "NumberOfSeats", "ModelName"));
    public static Vector<String> organisationUnitTable = new Vector<>(Arrays.asList("OrganisationUnit_ID", "Name", "Type", "Supervisor_ID"));
}
