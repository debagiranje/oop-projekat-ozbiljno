package baza.pomocnici;

import java.sql.*;
import java.util.ArrayList;

import modeli.IzvodjenjePredstave;
import baza.*;

public class IzvodjenjePredstaveDAO implements BazaCRUD<IzvodjenjePredstave> {



    @Override
    public IzvodjenjePredstave vratiPoId(int id) {
        IzvodjenjePredstave izvodjenje = null;
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("SELECT * FROM izvodjenje_predstave WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                izvodjenje = new IzvodjenjePredstave(
                        rs.getInt("id"),
                        (rs.getInt("predstava_id")),
                        (rs.getInt("pozoriste_id")),
                        rs.getDouble("cijena"),
                        rs.getTimestamp("datum_i_vrijeme").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjenje;
    }

    @Override
    public ArrayList<IzvodjenjePredstave> vratiSve() {
        ArrayList<IzvodjenjePredstave> izvodjenja = new ArrayList<>();
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("SELECT * FROM izvodjenje_predstave");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IzvodjenjePredstave izvodjenje =  new IzvodjenjePredstave(
                        rs.getInt("id"),
                        (rs.getInt("predstava_id")),
                        (rs.getInt("pozoriste_id")),
                        rs.getDouble("cijena"),
                        rs.getTimestamp("datum_i_vrijeme").toLocalDateTime());
                izvodjenja.add(izvodjenje);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjenja;
    }

    @Override
    public void azuriraj(IzvodjenjePredstave izvodjenje) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("UPDATE izvodjenje_predstave SET predstava_id = ?, pozoriste_id = ?, cijena = ?, datum_i_vrijeme = ? WHERE id = ?");
            ps.setInt(1, izvodjenje.getPredstava().getId());
            ps.setInt(2, izvodjenje.getPozoriste().getId());
            ps.setDouble(3, izvodjenje.getCijena());
            ps.setTimestamp(4, Timestamp.valueOf(izvodjenje.getDatumVrijeme()));
            ps.setInt(5, izvodjenje.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void obrisiPoId(int id) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("DELETE FROM izvodjenje_predstave WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dodaj(IzvodjenjePredstave izvodjenje) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("INSERT INTO izvodjenje_predstave (predstava_id, pozoriste_id, cijena, datum_i_vrijeme) VALUES (?, ?, ?, ?)");
            ps.setInt(1, izvodjenje.getPredstava().getId());
            ps.setInt(2, izvodjenje.getPozoriste().getId());
            ps.setDouble(3, izvodjenje.getCijena());
            ps.setTimestamp(4, Timestamp.valueOf(izvodjenje.getDatumVrijeme()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
    public void obrisiSve() {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("DELETE FROM izvodjenje_predstave");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}