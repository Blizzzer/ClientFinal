package com.resources;

public final class AddInformationStrings {

    public static String soldierInformationText = "Person to soldier upgrade \nRestrictions: \nSoldier ID: Must be numeric value belonging to " +
            "IDs in Person table which is not already an soldier or commander \n" +
            "Kills: Must be numeric \n Rank: Must be: P (Private), C (Corporal), L (Lieutant) " +
            "\nSupervising Unit ID: Must be numeric value of ID belonging to Organisation Unit table \n" +
            "Supervising Soldier ID: Must be numeric value belonging to ne of IDs in Soldier table";

    public static String commanderInformationText = "Person to commander upgrade \nRestrictions: \nCommander ID: Must be numeric value belonging to " +
            "\nSupervised Unit ID: Must be numeric value of ID belonging to Organisation Unit table";


    public static String personInformationText = "New person creation \nRestrictions: \n" +
            "Name: 20 or less letters \n Surname: 20 or less letters " +
            "\nHeight: Must be numeric value within 100 to 300 range  \n" +
            "Weight: Must be numeric value within 30 to 250";

    public static String equipmentInformationText = "New equipment creation \nRestrictions: \n" +
            "Type: Must be one of: AR (Assault rifle),SG (Submachine Pistol)" +
            ",SR (Sniper Rifle) or TK (Tank)\n" +
            "Production Date: Must be date written like XX-XX-XXXX\n" +
            "Owner_ID: Must be numeric value belonging to IDs in Soldier table";

    public static String marksmanInformationText = "Soldier to marksman upgrade \nRestrictions: \n" +
            "Marksman ID: Must be numeric value belonging to " +
            "IDs in Soldier table which is not already an marksman, engineer, tanker or medic \n" +
            "HeadshotPercent: Must be numeric value within 0 to 100 range";

    public static String engineerInformationText = "Soldier to engineer upgrade \nRestrictions: \n" +
            "Engineer ID: Must be numeric value belonging to " +
            "IDs in Soldier table which is not already an marksman, engineer, tanker or medic \n" +
            "Favourite Energy Drink: Must be one of: M (Monster), RB (Red bull), RS (Rockstar), O (Oshee)\n" +
            "Engineer Type: Must be one of: BC (Bridge Constructor), ED (Explosive Disposal)\n" +
            ", BC (Bridge Constructor), ME (Mechanical Engineer)\n" +
            "EE (Electrical Engineer)";

    public static String tankerInformationText = "Soldier to tanker upgrade \nRestrictions: \nTanker ID: Must be numeric value belonging to " +
            "IDs in Soldier table which is not already an marksman, engineer, tanker or medic \n" +
            "Positiont: Must be one of: G (Gunner), L (Loader), C (Commander), D (Driver)";

    public static String medicInformationText = "Soldier to medic upgrade \nRestrictions: \nMedic ID: Must be numeric value belonging to " +
            "IDs in Soldier table which is not already an marksman, engineer, tanker or medic \n" +
            "Revives: Must be numeric value";

    public static String vehicleInformationText = "Equipment to vehicle upgrade \nRestrictions: \nVehicle ID: Must be numeric value belonging to " +
            "IDs in Equipment table which is not already an vehicle or weapon \n" +
            "Type of fueal: Must be one of: D (Diesel) or G (Gasoline) \nNumber of seats: Must be numeric value " +
            "\nModel Name: Must be 20 or less letters";

    public static String weaponInformationText = "Equipment to weapon upgrade \nRestrictions: \nWeapon ID: Must be numeric value belonging to " +
            "IDs in Equipment table which is not already an vehicle or weapon \n" +
            "Caliber: Must be one of following: 5.56, 7.62, .50M, .45, .44M, 12g, 9mm, .357M \nMagazine Caliber: Must be numeric value " +
            "\nModel Name: Must be 20 or less letters";



}
