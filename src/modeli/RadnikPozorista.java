package modeli;

import java.util.ArrayList;

import baza.pomocnici.PozoristeDAO;
import baza.pomocnici.RadnikPozoristaDAO;

public class RadnikPozorista extends Korisnik{
	
	public static ArrayList<RadnikPozorista> sviRadnici = new ArrayList<>();
	public static RadnikPozoristaDAO dao = new RadnikPozoristaDAO();
	public static PozoristeDAO pdao = new PozoristeDAO();
	public static RadnikPozorista trenutni;

	
	private Pozoriste pozoristeID;
	
	public RadnikPozorista(int id, String ime, String prezime, String korisnickoIme, String lozinka,
			Pozoriste pozoristeID) {
		super(id, ime, prezime, korisnickoIme, lozinka);
		this.pozoristeID = pozoristeID;
	}
	
	public RadnikPozorista(String ime, String prezime, String korisnickoIme, String lozinka,
			int pozoristeID) {
		super(ime, prezime, korisnickoIme, lozinka);
		this.pozoristeID = pdao.vratiPoId(pozoristeID);
	}

	public Pozoriste getPozoristeID() {
		return pozoristeID;
	}
	public void setPozoristeID(Pozoriste pozoristeID) {
		this.pozoristeID = pozoristeID;
	}
	
	public static RadnikPozorista pronadjiRadnika (String korisnicko, String passw)
	{
		sviRadnici = dao.vratiSve();
		for(RadnikPozorista r : sviRadnici)
		{
			if (r.getKorisnickoIme().equals(korisnicko) && r.getLozinka().equals(passw))
			{
				return r;
			}
		}
		
		return null;
		
	}
	
	public static boolean radniciPozorista(int id)
	{
		sviRadnici = dao.vratiSve();
		for(RadnikPozorista r : sviRadnici)
		{
			if(r.pozoristeID.getId()==id)
				return true;
		}
		
		return false;
		
	}
	public static void azurirajLozinku (String nova)
	{
		trenutni.lozinka = nova;
		dao.azuriraj(trenutni);
		System.out.println("Juhu! azurirali smo te");
	}

	public static void dodajRadnika(String ime, String prezime, String korisnicko, String lozinka, int id)
	{
		RadnikPozorista rp = new RadnikPozorista(ime, prezime, korisnicko, lozinka, id);
		if(pronadjiRadnika(korisnicko, lozinka)==null)
			dao.dodaj(rp);
		
	}

}
