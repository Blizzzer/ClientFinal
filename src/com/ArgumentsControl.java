package com;


import java.sql.Statement;
import java.util.List;

public final class ArgumentsControl {
    public static boolean isPersonOK(List<String> paramlist){
        boolean[] c = new boolean[6];
        c[0]=DataControl.LengthControl(paramlist.get(0), 20);
        c[1]=DataControl.LengthControl(paramlist.get(1), 20);
        c[2]=DataControl.CheckIfNumeric(paramlist.get(2));
        c[3]=DataControl.CheckIfNumeric(paramlist.get(3));
        c[4]=DataControl.isHeightRange(paramlist.get(2));
        c[5]=DataControl.isWeightRange(paramlist.get(3));
        for(int i=0; i<c.length; i++){
            System.out.println(c[i]);
        }
        System.out.println(c[0]&&c[1]&&c[2]&&c[3]&&c[4]&&c[5]);
        if(c[0]&&c[1]&&c[2]&&c[3]&&c[4]&&c[5]){
            return true;
        }
        WarningDialogGenerator.personWarning(c);
        return false;
    }

    public static boolean isSoldierOK(Statement stmt, List<String> paramlist){
        boolean[] c = new boolean[9];
        c[0]=DataControl.CheckIfNumeric(paramlist.get(0));
        c[1]=DataControl.CheckIfIDExists(stmt, "uganda.person", Integer.parseInt(paramlist.get(0))); //czy soldier
        c[2]=DataControl.CheckIfNumeric(paramlist.get(1));
        c[3]=DataControl.isSoldierRank(paramlist.get(2));
        c[4]=DataControl.isOrgUnitRange(paramlist.get(3));
        c[5]=DataControl.CheckIfNumeric(paramlist.get(4));
        c[6]=DataControl.CheckIfIDExists(stmt, "uganda.soldier", Integer.parseInt(paramlist.get(4)));
        c[7]=!DataControl.CheckIfIDExists(stmt, "uganda.soldier", Integer.parseInt(paramlist.get(0)));
        c[8]=!DataControl.CheckIfIDExists(stmt, "uganda.commander", Integer.parseInt(paramlist.get(0)));
        for(int i=0; i<c.length; i++){
            System.out.println(c[i]);
        }
        if(c[0]&&c[1]&&c[2]&&c[3]&&c[4]&&c[5]&&c[6]&&c[7]&&c[8])
            return true;
        WarningDialogGenerator.soldierWarning(c);
        return false;
    }

    public static boolean isCommanderOK(Statement stmt, List<String> paramlist){
        boolean[] c = new boolean[5];
        c[0]=DataControl.CheckIfNumeric(paramlist.get(0));
        c[1]=DataControl.CheckIfIDExists(stmt, "uganda.person", Integer.parseInt(paramlist.get(0))); //Czy soldier
        c[2]=DataControl.isOrgUnitRange(paramlist.get(1));
        c[3]=!DataControl.CheckIfIDExists(stmt, "uganda.soldier", Integer.parseInt(paramlist.get(0)));
        c[4]=!DataControl.CheckIfIDExists(stmt, "uganda.commander", Integer.parseInt(paramlist.get(0)));
        if(c[0]&&c[1]&&c[2]&&c[3]&&c[4])
            return true;
        WarningDialogGenerator.commanderWarning(c);
        return false;
    }

    public static boolean isEngineerOK(Statement stmt, List<String> paramlist){
        boolean[] c = new boolean[8];
        c[0]=DataControl.CheckIfNumeric(paramlist.get(0));
        c[1]=DataControl.CheckIfIDExists(stmt, "uganda.soldier", Integer.parseInt(paramlist.get(0)));
        c[2]=DataControl.isEngineerDrink(paramlist.get(1));
        c[3]=DataControl.isEngineerType(paramlist.get(2));
        c[4]=!DataControl.CheckIfIDExists(stmt, "uganda.engineer", Integer.parseInt(paramlist.get(0)));
        c[5]=!DataControl.CheckIfIDExists(stmt, "uganda.marksman", Integer.parseInt(paramlist.get(0)));
        c[6]=!DataControl.CheckIfIDExists(stmt, "uganda.tanker", Integer.parseInt(paramlist.get(0)));
        c[7]=!DataControl.CheckIfIDExists(stmt, "uganda.medic", Integer.parseInt(paramlist.get(0)));
        if(c[0]&&c[1]&&c[2]&&c[3]&&c[4]&&c[5]&&c[6]&&c[7])
            return true;
        WarningDialogGenerator.engineerWarning(c);
        return false;
    }

    public static boolean isMarksmanOK(Statement stmt, List<String> paramlist){
        boolean[] c = new boolean[8];
        c[0]=DataControl.CheckIfNumeric(paramlist.get(0));
        c[1]=DataControl.CheckIfIDExists(stmt, "uganda.soldier", Integer.parseInt(paramlist.get(0)));
        c[2]=DataControl.CheckIfNumeric(paramlist.get(1));
        c[3]=DataControl.isHeadshotPercentRange(paramlist.get(1));
        c[4]=!DataControl.CheckIfIDExists(stmt, "uganda.engineer", Integer.parseInt(paramlist.get(0)));
        c[5]=!DataControl.CheckIfIDExists(stmt, "uganda.marksman", Integer.parseInt(paramlist.get(0)));
        c[6]=!DataControl.CheckIfIDExists(stmt, "uganda.tanker", Integer.parseInt(paramlist.get(0)));
        c[7]=!DataControl.CheckIfIDExists(stmt, "uganda.medic", Integer.parseInt(paramlist.get(0)));
        if(c[0]&&c[1]&&c[2]&&c[3]&&c[4]&&c[5]&&c[6]&&c[7])
            return true;
        WarningDialogGenerator.marksmanWarning(c);
        return false;
    }

    public static boolean isTankerOK(Statement stmt, List<String> paramlist){
        boolean[] c = new boolean[7];
        c[0]=DataControl.CheckIfNumeric(paramlist.get(0));
        c[1]=DataControl.CheckIfIDExists(stmt, "uganda.soldier", Integer.parseInt(paramlist.get(0)));
        c[2]=DataControl.isTankerPosition(paramlist.get(1));
        c[3]=!DataControl.CheckIfIDExists(stmt, "uganda.engineer", Integer.parseInt(paramlist.get(0)));
        c[4]=!DataControl.CheckIfIDExists(stmt, "uganda.marksman", Integer.parseInt(paramlist.get(0)));
        c[5]=!DataControl.CheckIfIDExists(stmt, "uganda.tanker", Integer.parseInt(paramlist.get(0)));
        c[6]=!DataControl.CheckIfIDExists(stmt, "uganda.medic", Integer.parseInt(paramlist.get(0)));
        if(c[0]&&c[1]&&c[2]&&c[3]&&c[4]&&c[5]&&c[6])
            return true;
        WarningDialogGenerator.tankerWarning(c);
        return false;
    }

    public static boolean isMedicOK(Statement stmt, List<String> paramlist){
        boolean[] c = new boolean[7];
        c[0]=DataControl.CheckIfNumeric(paramlist.get(0));
        c[1]=DataControl.CheckIfIDExists(stmt, "uganda.soldier", Integer.parseInt(paramlist.get(0)));
        c[2]=DataControl.CheckIfNumeric(paramlist.get(1));
        c[3]=!DataControl.CheckIfIDExists(stmt, "uganda.engineer", Integer.parseInt(paramlist.get(0)));
        c[4]=!DataControl.CheckIfIDExists(stmt, "uganda.marksman", Integer.parseInt(paramlist.get(0)));
        c[5]=!DataControl.CheckIfIDExists(stmt, "uganda.tanker", Integer.parseInt(paramlist.get(0)));
        c[6]=!DataControl.CheckIfIDExists(stmt, "uganda.medic", Integer.parseInt(paramlist.get(0)));
        if(c[0]&&c[1]&&c[2]&&c[3]&&c[4]&&c[5]&&c[6])
            return true;
        WarningDialogGenerator.medicWarning(c);
        return false;
    }

    public static boolean isEquipmentOK(Statement stmt, List<String> paramlist){
        boolean[] c = new boolean[3];
        c[0]=DataControl.isEquipmentType(paramlist.get(0));
        //TODO Sprawdzanie daty
        c[1]=DataControl.CheckIfNumeric(paramlist.get(2));
        c[2]=DataControl.CheckIfIDExists(stmt, "uganda.soldier",Integer.parseInt(paramlist.get(2)));
        if(c[0]&&c[1]&&c[2])
            return true;
        WarningDialogGenerator.equipmentWarning(c);
        return false;
    }

    public static boolean isWeaponOK(Statement stmt, List<String> paramlist){
        boolean[] c = new boolean[7];
        c[0]=DataControl.CheckIfNumeric(paramlist.get(0));
        c[1]=DataControl.CheckIfIDExists(stmt, "uganda.equipment", Integer.parseInt(paramlist.get(0)));
        c[2]=DataControl.isWeaponCaliber(paramlist.get(1));
        c[3]=DataControl.CheckIfNumeric(paramlist.get(2));
        c[4]=DataControl.LengthControl(paramlist.get(3), 20);
        c[5]=!DataControl.CheckIfIDExists(stmt, "uganda.weapon", Integer.parseInt(paramlist.get(0)));
        c[6]=!DataControl.CheckIfIDExists(stmt, "uganda.vehicle", Integer.parseInt(paramlist.get(0)));
        if(c[0]&&c[1]&&c[2]&&c[3]&&c[4])
            return true;
        WarningDialogGenerator.weaponWarning(c);
        return false;
    }

    public static boolean isVehicleOK(Statement stmt, List<String> paramlist){
        boolean[] c = new boolean[7];
        c[0]=DataControl.CheckIfNumeric(paramlist.get(0));
        c[1]=DataControl.CheckIfIDExists(stmt, "uganda.equipment", Integer.parseInt(paramlist.get(0)));
        c[2]=DataControl.isFuelType(paramlist.get(1));
        c[3]=DataControl.CheckIfNumeric(paramlist.get(2));
        c[4]=DataControl.LengthControl(paramlist.get(3), 20);
        c[5]=!DataControl.CheckIfIDExists(stmt, "uganda.weapon", Integer.parseInt(paramlist.get(0)));
        c[6]=!DataControl.CheckIfIDExists(stmt, "uganda.vehicle", Integer.parseInt(paramlist.get(0)));
        if(c[0]&&c[1]&&c[2]&&c[3]&&c[4])
            return true;
        WarningDialogGenerator.vehicleWarning(c);
        return false;
    }


}