package baza.pomocnici;

import java.util.ArrayList;
import java.sql.*;

import baza.*;
import modeli.OsobljePredstave;

public class OsobljePredstaveDAO implements BazaCRUD<OsobljePredstave> {

    @Override
    public OsobljePredstave vratiPoId(int id) {
        OsobljePredstave osobljePredstave = null;
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("SELECT * FROM osoblje_predstave WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                osobljePredstave = new OsobljePredstave(
                		rs.getInt("id"),
                        (rs.getInt("osoblje_id")),
                        (rs.getInt("predstava_id"))
                );
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return osobljePredstave;
    }

    @Override
    public ArrayList<OsobljePredstave> vratiSve() {
        ArrayList<OsobljePredstave> osobljePredstaveList = new ArrayList<>();

        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("SELECT * FROM osoblje_predstave");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {System.out.println(rs.getInt("osoblje_id"));
                OsobljePredstave osobljePredstave = new OsobljePredstave(
                        rs.getInt("id"),
                        (rs.getInt("osoblje_id")),
                        (rs.getInt("predstava_id"))
                );
                osobljePredstaveList.add(osobljePredstave);
                //System.out.println(osobljePredstave.getOsobljeID().getId() + "Napraviosam te mishu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return osobljePredstaveList;
    }

    @Override
    public void azuriraj(OsobljePredstave osobljePredstave) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("UPDATE osoblje_predstave SET osoblje_id = ?, predstava_id = ? WHERE id = ?");
            ps.setInt(1, osobljePredstave.getOsobljeID().getId());
            ps.setInt(2, osobljePredstave.getPredstavaID().getId());
            ps.setInt(3, osobljePredstave.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void obrisiPoId(int id) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("DELETE FROM osoblje_predstave WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void obrisiPoPredstavi(int id) {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("DELETE FROM osoblje_predstave WHERE predstava_id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dodaj(OsobljePredstave osobljePredstave) {
    	try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("INSERT INTO osoblje_predstave (osoblje_id, predstava_id) VALUES (?,?)");
            ps.setInt(1, osobljePredstave.getOsobljeID().getId());
            ps.setInt(2, osobljePredstave.getPredstavaID().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void obrisiSve() {
        try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("DELETE FROM osoblje_predstave");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}