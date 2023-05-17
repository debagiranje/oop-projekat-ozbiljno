package baza.pomocnici;

import java.util.ArrayList;
import java.sql.*;

import baza.*;
import modeli.OsobljePredstave;

public class OsobljePredstaveDAO implements BazaCRUD<OsobljePredstave> {

    private OsobljeDAO osobljeDAO;
    private PredstavaDAO predstavaDAO;

    public OsobljePredstaveDAO(OsobljeDAO osobljeDAO, PredstavaDAO predstavaDAO) {
        this.osobljeDAO = osobljeDAO;
        this.predstavaDAO = predstavaDAO;
    }

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
                        osobljeDAO.vratiPoId(rs.getInt("osoblje_id")),
                        predstavaDAO.vratiPoId(rs.getInt("predstava_id"))
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

            while (rs.next()) {
                OsobljePredstave osobljePredstave = new OsobljePredstave(
                        rs.getInt("id"),
                        osobljeDAO.vratiPoId(rs.getInt("osoblje_id")),
                        predstavaDAO.vratiPoId(rs.getInt("predstava_id"))
                );
                osobljePredstaveList.add(osobljePredstave);
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

    @Override
    public void dodaj(OsobljePredstave osobljePredstave) {
    	try {
            PreparedStatement ps = Veza.vratiVezu().prepareStatement("INSERT INTO osoblje_predstave (osoblje_id, predstava_id) VALUES (?,?,?,?)");
            ps.setInt(1, osobljePredstave.getOsobljeID().getId());
            ps.setInt(2, osobljePredstave.getPredstavaID().getId());
            ps.setInt(3, osobljePredstave.getId());
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