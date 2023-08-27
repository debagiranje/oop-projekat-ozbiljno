package baza.pomocnici;
import java.sql.*;
import java.util.ArrayList;

import baza.*;
import modeli.Osoblje;

public class OsobljeDAO implements BazaCRUD<Osoblje>{

	@Override
    public Osoblje vratiPoId(int id) {
		Osoblje o = null;
        try {
        	
            PreparedStatement preparedStatement = Veza.vratiVezu().prepareStatement("SELECT * FROM osoblje WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            //System.out.println("pozvan iz osoblje dao");
            if(resultSet!=null) {
            	System.out.println("nije null");
            if(resultSet.next()) {
                o = new Osoblje(
                        resultSet.getInt("id"),
                        resultSet.getString("ime"),
                        resultSet.getString("prezime"),
                        (resultSet.getInt("tip"))
                );
               //System.out.println(o.toString() + "pozvan iz osoblje dao");
               
            }}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public ArrayList<Osoblje> vratiSve() {
        ArrayList<Osoblje> osobljeList = new ArrayList<>();
        try {
            Statement statement = Veza.vratiVezu().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM osoblje");
            while (resultSet.next()) {
                osobljeList.add(new Osoblje(
                        resultSet.getInt("id"),
                        resultSet.getString("ime"),
                        resultSet.getString("prezime"),
                        (resultSet.getInt("tip"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return osobljeList;
    }

    @Override
    public void dodaj(Osoblje obj) {
        try {
        	System.out.println("gospode evo me i ovdje");
            PreparedStatement preparedStatement = Veza.vratiVezu().prepareStatement("INSERT INTO osoblje (ime, prezime, tip) VALUES (?, ?, ?)");
            preparedStatement.setString(1, obj.getIme());
            preparedStatement.setString(2, obj.getPrezime());
            preparedStatement.setInt(3, obj.getTip());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void azuriraj(Osoblje obj) {
        try {
            PreparedStatement preparedStatement = Veza.vratiVezu().prepareStatement("UPDATE osoblje SET ime=?, prezime=?, tip=? WHERE id=?");
            preparedStatement.setString(1, obj.getIme());
            preparedStatement.setString(2, obj.getPrezime());
            preparedStatement.setInt(3, obj.getTip());
            preparedStatement.setInt(4, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void obrisiPoId(int id) {
        try {
            PreparedStatement preparedStatement = Veza.vratiVezu().prepareStatement("DELETE FROM osoblje WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void obrisiSve() {
        try {
            Statement statement = Veza.vratiVezu().createStatement();
            statement.executeUpdate("DELETE FROM osoblje");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


