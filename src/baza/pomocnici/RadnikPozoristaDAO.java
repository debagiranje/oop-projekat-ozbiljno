package baza.pomocnici;

import java.util.ArrayList;
import java.sql.*;

import baza.*;
import modeli.Pozoriste;
import modeli.RadnikPozorista;

public class RadnikPozoristaDAO implements BazaCRUD<RadnikPozorista> {

    @Override
    public RadnikPozorista vratiPoId(int id) {
        RadnikPozorista radnik = null;
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("SELECT * FROM radnik_pozorista WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PozoristeDAO pozoristeDAO = new PozoristeDAO();
                Pozoriste pozoriste = pozoristeDAO.vratiPoId(rs.getInt("pozoriste_id"));

                radnik = new RadnikPozorista(
                        rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("korisnicko_ime"),
                        rs.getString("lozinka"),
                        pozoriste);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return radnik;
    }

    @Override
    public ArrayList<RadnikPozorista> vratiSve() {
        ArrayList<RadnikPozorista> radnici = new ArrayList<RadnikPozorista>();

        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("SELECT * FROM radnik_pozorista");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PozoristeDAO pozoristeDAO = new PozoristeDAO();
                Pozoriste pozoriste = pozoristeDAO.vratiPoId(rs.getInt("pozoriste_id"));

                RadnikPozorista radnik = new RadnikPozorista(
                        rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("korisnicko_ime"),
                        rs.getString("lozinka"),
                        pozoriste);
                radnici.add(radnik);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return radnici;
    }

    @Override
    public void azuriraj(RadnikPozorista radnik) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("UPDATE radnik_pozorista SET ime = ?, prezime = ?, korisnicko_ime = ?, lozinka = ?, pozoriste_id = ? WHERE id = ?");
            ps.setString(1, radnik.getIme());
            ps.setString(2, radnik.getPrezime());
            ps.setString(3, radnik.getKorisnickoIme());
            ps.setString(4, radnik.getLozinka());
            ps.setInt(5, radnik.getPozoristeID().getId());
            ps.setInt(6, radnik.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void obrisiPoId(int id) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("DELETE FROM radnik_pozorista WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dodaj(RadnikPozorista radnik) {
    	try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("INSERT INTO radnik_pozorista (ime, prezime, korisnicko_ime, lozinka, pozoriste_id) VALUES (?,?,?,?,?)");
            ps.setString(1, radnik.getIme());
            ps.setString(2, radnik.getPrezime());
            ps.setString(3, radnik.getKorisnickoIme());
            ps.setString(4, radnik.getLozinka());
            ps.setInt(5, radnik.getPozoristeID().getId());
            ps.setInt(6, radnik.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void obrisiSve() {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("DELETE FROM radnik_pozorista");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
            
  }