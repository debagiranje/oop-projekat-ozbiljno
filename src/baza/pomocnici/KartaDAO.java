package baza.pomocnici;

import java.sql.*;
import java.util.ArrayList;


import baza.*;
import modeli.Karta;
import modeli.Status;


public class KartaDAO implements BazaCRUD<Karta>{
	
	private IzvodjenjePredstaveDAO izvodjenjeDAO;
	private PosjetilacPozoristaDAO posjetilacDAO;

	@Override
    public Karta vratiPoId(int id) {
		
        Karta karta = null;
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("SELECT * FROM karta WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                karta = new Karta(
                		rs.getInt("id"),
                		izvodjenjeDAO.vratiPoId(rs.getInt("izvodjenje_predstave_id")),
                		Status.getEnum(rs.getInt("status")),
                		posjetilacDAO.vratiPoId(rs.getInt("posjetilac_id")),
            			rs.getInt("broj_karta"));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return karta;
    }

    @Override
    public ArrayList<Karta> vratiSve() {
        ArrayList<Karta> karte = new ArrayList<Karta>();
     
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("SELECT * FROM karta");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Karta karta = new Karta(
                		rs.getInt("id"),
                		izvodjenjeDAO.vratiPoId(rs.getInt("izvodjenje_predstave_id")),
                		Status.getEnum(rs.getInt("status")),
                		posjetilacDAO.vratiPoId(rs.getInt("posjetilac_id")),
            			rs.getInt("broj_karta"));
                karte.add(karta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return karte;
    }

    @Override
    public void azuriraj(Karta karta) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("UPDATE karta SET izvodjenje_predstave_id = ?, status = ?, posjetilac_id = ?, broj_karta = ? WHERE id = ?");
            ps.setInt(1, karta.getIpID().getId());
            ps.setInt(2, karta.getStatus().getBroj());
            ps.setInt(3, karta.getPpID().getId());
            ps.setInt(4, karta.getBrojKarta());
            ps.setInt(5, karta.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void obrisiPoId(int id) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("DELETE FROM karta WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dodaj(Karta karta) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("INSERT INTO karta (izvodjenje_predstave_id, status, posjetilac_id, broj_karta) VALUES (?, ?, ?, ?)");
            ps.setInt(1, karta.getIpID().getId());
            ps.setInt(2, karta.getStatus().getBroj());
            ps.setInt(3, karta.getPpID().getId());
            ps.setInt(4, karta.getBrojKarta());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void obrisiSve() {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("DELETE FROM karta");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
