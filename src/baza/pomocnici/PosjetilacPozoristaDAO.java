package baza.pomocnici;

import java.util.ArrayList;
import java.sql.*;

import baza.*;
import modeli.PosjetilacPozorista;


public class PosjetilacPozoristaDAO implements BazaCRUD<PosjetilacPozorista> {
    
    @Override
    public PosjetilacPozorista vratiPoId(int id) {
        PosjetilacPozorista posjetilac = null;
        
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("SELECT * FROM posjetilac_pozorista WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                posjetilac = new PosjetilacPozorista(
                        rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("korisincko_ime"),
                        rs.getString("lozinka"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posjetilac;
    }

    @Override
    public ArrayList<PosjetilacPozorista> vratiSve() {
        ArrayList<PosjetilacPozorista> posjetioci = new ArrayList<PosjetilacPozorista>();
        
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("SELECT * FROM posjetilac_pozorista");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                PosjetilacPozorista posjetilac = new PosjetilacPozorista(
                        rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("korisincko_ime"),
                        rs.getString("lozinka"));
                posjetioci.add(posjetilac);
                System.out.println(posjetilac.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posjetioci;
    }

    @Override
    public void azuriraj(PosjetilacPozorista posjetilac) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("UPDATE posjetilac_pozorista SET ime = ?, prezime = ?, korisnicko_ime = ?, lozinka = ? WHERE id = ?");
            ps.setString(1, posjetilac.getIme());
            ps.setString(2, posjetilac.getPrezime());
            ps.setString(3, posjetilac.getKorisnickoIme());
            ps.setString(4, posjetilac.getLozinka());
            ps.setInt(5, posjetilac.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void obrisiPoId(int id) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("DELETE FROM posjetilac_pozorista WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dodaj(PosjetilacPozorista posjetilac) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("INSERT INTO posjetilac_pozorista (ime, prezime, korisincko_ime, lozinka) VALUES (?,?,?,?)");
            ps.setString(1, posjetilac.getIme());
            ps.setString(2, posjetilac.getPrezime());
            ps.setString(3, posjetilac.getKorisnickoIme());
            ps.setString(4, posjetilac.getLozinka());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void obrisiSve() {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("DELETE FROM posjetilac_pozorista");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
