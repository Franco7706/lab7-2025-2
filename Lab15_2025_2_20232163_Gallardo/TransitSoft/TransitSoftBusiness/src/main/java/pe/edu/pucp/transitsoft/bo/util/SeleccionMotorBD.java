package pe.edu.pucp.transitsoft.bo.util;

import pe.edu.pucp.transitsoft.util.MotorDeBaseDeDatos;

public class SeleccionMotorBD {
    public static MotorDeBaseDeDatos obtenerMotor(String stringMotor){
        switch(stringMotor){
            case "MySQL":
                return MotorDeBaseDeDatos.MYSQL;
            case "MSSQL":
                return MotorDeBaseDeDatos.MSSQL;
            default:
                return MotorDeBaseDeDatos.MYSQL;
        }
    }
}
