package baza;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import modeli.Osoblje;
import modeli.Tip;

public class Helper {
	
public static ArrayList<Osoblje> getOsoblje() {
		
		ArrayList<Osoblje> l = new ArrayList<Osoblje>();
		ResultSet resultSet = null;
		try {
			
			Statement statement = Veza.vratiVezu().createStatement();
			String SQLQuery = "SELECT * FROM osoblje";
            resultSet = statement.executeQuery(SQLQuery);
            
            while (resultSet.next()) {
            	Osoblje o = new Osoblje(
            			resultSet.getInt("id"),
            			resultSet.getString("ime"),
            			resultSet.getString(3),
            			Integer.parseInt(resultSet.getString(4)));
            	l.add(o);
            }
            
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;
	}
	
	public static Osoblje getOsoblje(int id) {
		
		Osoblje o = null;
		ResultSet resultSet = null;
		try {
			Statement statement = Veza.vratiVezu().createStatement();
			String SQLQuery = "SELECT * FROM osoblje WHERE id="+id;
            resultSet = statement.executeQuery(SQLQuery);
            
            while (resultSet.next()) {
            	o = new Osoblje(
            			resultSet.getInt("id"),
            			resultSet.getString("ime"),
            			resultSet.getString(3),
            			Integer.parseInt(resultSet.getString(4)));
            			System.out.println(Integer.parseInt(resultSet.getString(4)) + "ajde majmune");
            }
            
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return o;
	}
	
	public static void addOsoblje(Osoblje o) {
		
		try {
			Statement statement = Veza.vratiVezu().createStatement();
			String SQLQuery = "INSERT INTO osoblje (ime, prezime, tip) VALUE ("
					+ "'" + o.getIme() + "', "
					+ "'" + o.getPrezime() + "', "
					+ o.getTip() + ")";
			System.out.println(o.getTip() +  "mali paradajz hopsla");
            statement.executeUpdate(SQLQuery);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public static void obrisiSve() {
	        try {
	            Statement statement = Veza.vratiVezu().createStatement();
	            statement.executeUpdate("DELETE FROM osoblje");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static String hashuj(String password) {
	        StringBuilder sb = new StringBuilder();

	        try {
	            MessageDigest md5 = MessageDigest.getInstance("MD5");

	            byte[] bytesOfPassword = password.getBytes(StandardCharsets.UTF_8);
	            byte[] hash = md5.digest(bytesOfPassword);

	            for (byte b : hash) {
	                sb.append(String.format("%02x", b));
	            }
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }

	        return sb.toString();
	    }
}
