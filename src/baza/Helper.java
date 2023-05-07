package baza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modeli.Osoblje;

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
			System.out.println(SQLQuery);
            statement.executeUpdate(SQLQuery);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
