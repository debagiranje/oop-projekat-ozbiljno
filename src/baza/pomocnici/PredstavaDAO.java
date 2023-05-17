package baza.pomocnici;

import java.util.ArrayList;
import java.sql.*;


import baza.*;
import modeli.Predstava;
import modeli.Zanr;


public class PredstavaDAO implements BazaCRUD<Predstava> {

    @Override
    public Predstava vratiPoId(int id) {
        Predstava predstava = null;
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("SELECT * FROM predstava WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                predstava = new Predstava(
                        rs.getInt("id"),
                        rs.getString("naziv"),
                        Zanr.valueOf(rs.getString("zanr")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return predstava;
    }

    @Override
    public ArrayList<Predstava> vratiSve() {
        ArrayList<Predstava> predstave = new ArrayList<>();

        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("SELECT * FROM predstava");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Predstava predstava = new Predstava(
                        rs.getInt("id"),
                        rs.getString("naziv"),
                        Zanr.getEnum(rs.getInt("zanr")));
                predstave.add(predstava);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return predstave;
    }

    @Override
    public void azuriraj(Predstava predstava) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("UPDATE predstava SET naziv = ?, zanr = ? WHERE id = ?");
            ps.setString(1, predstava.getNaziv());
            ps.setInt(2, predstava.getZanr().getBroj());
            ps.setInt(3, predstava.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void obrisiPoId(int id) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("DELETE FROM predstava WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dodaj(Predstava predstava) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("INSERT INTO predstava (naziv, zanr) VALUES (?, ?)");
            ps.setString(1, predstava.getNaziv());
            ps.setInt(2, predstava.getZanr().getBroj());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void obrisiSve() {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("DELETE FROM predstava");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
