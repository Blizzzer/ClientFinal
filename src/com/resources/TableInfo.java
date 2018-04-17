package com.resources;

import java.util.Arrays;
import java.util.List;

public class TableInfo {
    public static List<String> personTable = Arrays.asList("Person_ID", "Name", "Surname", "Height", "Weight");
    public static List<String> soldierTable = Arrays.asList("Soldier_ID", "Kills", "Rank", "SupervisingUnit_ID", "SupervisingSoldier_ID");
    public static List<String> engineerTable = Arrays.asList("Engineer_ID", "FavouriteEnergyDrink", "EngineerType");
    public static List<String> marksmanTable = Arrays.asList("Marksman_ID", "HeadshotPercent");
    public static List<String> tankerTable = Arrays.asList("Tanker_ID", "Position");
    public static List<String> medicTable = Arrays.asList("Medic_ID", "Revives");
    public static List<String> commanderTable = Arrays.asList("Commander_ID", "SupervisedUnit_ID");
    public static List<String> equipmentTable = Arrays.asList("Equipment_ID", "Type", "ProductionDate", "Owner_ID");
    public static List<String> weaponTable = Arrays.asList("Weapon_ID", "Caliber", "MagazineCapacity", "ModelName");
    public static List<String> vehicleTable = Arrays.asList("Vehicle_ID", "TypeOfFuel", "NumberOfSeats", "ModelName");
    public static List<String> organisationUnitTable = Arrays.asList("OrganisationUnit_ID", "Name", "Type", "Supervisor_ID");
}
