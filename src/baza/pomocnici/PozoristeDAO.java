package baza.pomocnici;

import java.util.ArrayList;
import java.sql.*;


import baza.*;
import modeli.Pozoriste;

public class PozoristeDAO implements BazaCRUD<Pozoriste> {

    @Override
    public Pozoriste vratiPoId(int id) {
        Pozoriste pozoriste = null;
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("SELECT * FROM pozoriste WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pozoriste = new Pozoriste(
                        rs.getInt("id"),
                        rs.getString("naziv"),
                        rs.getString("grad"),
                        rs.getInt("broj_sjedista"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pozoriste;
    }

    @Override
    public ArrayList<Pozoriste> vratiSve() {
        ArrayList<Pozoriste> pozorista = new ArrayList<Pozoriste>();

        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("SELECT * FROM pozoriste");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pozoriste pozoriste = new Pozoriste(
                        rs.getInt("id"),
                        rs.getString("naziv"),
                        rs.getString("grad"),
                        rs.getInt("broj_sjedista"));
                pozorista.add(pozoriste);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pozorista;
    }

    @Override
    public void azuriraj(Pozoriste pozoriste) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("UPDATE pozoriste SET naziv = ?, grad = ?, broj_sjedista = ? WHERE id = ?");
            ps.setString(1, pozoriste.getNaziv());
            ps.setString(2, pozoriste.getGrad());
            ps.setInt(3, pozoriste.getBrojSjedista());
            ps.setInt(4, pozoriste.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void obrisiPoId(int id) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("DELETE FROM pozoriste WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dodaj(Pozoriste pozoriste) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("INSERT INTO pozoriste (naziv, grad, broj_sjedista) VALUES (?, ?, ?)");
            ps.setString(1, pozoriste.getNaziv());
            ps.setString(2, pozoriste.getGrad());
            ps.setInt(3, pozoriste.getBrojSjedista());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void obrisiSve() {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("DELETE FROM pozoriste");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
