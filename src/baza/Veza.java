package baza;
import java.sql.*;

//https://www.geeksforgeeks.org/jdbc-using-model-object-and-singleton-class/


public class Veza {
	
	private static String DBuser = "root";
	private static String DBpassword = "Burescic1#";
	private static String connectionUrl;
	private static int port = 3306;
	private static String DBname = "projekat_juhu";
	private static Connection veza = null;

    public static Connection vratiVezu() {
        if (veza != null) {
            return veza;
        } else {
            try {
            	connectionUrl = "jdbc:mysql://localhost" + ":" + port + "/" + DBname;
                veza = DriverManager.getConnection(connectionUrl, DBuser, DBpassword);
                System.out.println("Juhu! Konekcija uspjesna.");
            } catch (SQLException e) {
                System.out.println("O ne! Greska pri povezivanju sa bazom: " + e.getMessage());
            }
            return veza;
        }
    }
    
    public static void zatvoriVezu() {
        if (veza != null) {
            try {
                veza.close();
            } catch (SQLException e) {
            	System.out.println("O ne! Greska pri zatvaranju baze: " + e.getMessage());
            }
        }
    }

}
