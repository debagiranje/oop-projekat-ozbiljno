package modeli;

import java.time.LocalDate;
import java.util.ArrayList;

import baza.pomocnici.KartaDAO;
import baza.pomocnici.PosjetilacPozoristaDAO;

public class PosjetilacPozorista extends Korisnik{
	
	public static ArrayList<PosjetilacPozorista> sviPosjetioci = new ArrayList<>();
	public static ArrayList<Karta> sveKarte = new ArrayList<>();
	public static ArrayList<String> mojeKarte = new ArrayList<>();
	public static ArrayList<String> mojeKarteP = new ArrayList<>();
	public static ArrayList<String> lista48 = new ArrayList<>();
	public static PosjetilacPozoristaDAO dao = new PosjetilacPozoristaDAO();
	public static KartaDAO daoKarta = new KartaDAO();
	public static PosjetilacPozorista trenutni;

	public PosjetilacPozorista(int id, String ime, String prezime, String korisnickoIme, String lozinka) {
		super(id, ime, prezime, korisnickoIme, lozinka);
	}
	
	public PosjetilacPozorista(String ime, String prezime, String korisnickoIme, String lozinka) {
		super(ime, prezime, korisnickoIme, lozinka);
	}


	@Override
	public String getKorisnickoIme() {
		// TODO Auto-generated method stub
		return super.getKorisnickoIme();
	}

	@Override
	public void setKorisnickoIme(String korisnickoIme) {
		// TODO Auto-generated method stub
		super.setKorisnickoIme(korisnickoIme);
	}

	@Override
	public String getLozinka() {
		// TODO Auto-generated method stub
		return super.getLozinka();
	}

	@Override
	public void setLozinka(String lozinka) {
		// TODO Auto-generated method stub
		super.setLozinka(lozinka);
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	@Override
	public String getIme() {
		// TODO Auto-generated method stub
		return super.getIme();
	}

	@Override
	public void setIme(String ime) {
		// TODO Auto-generated method stub
		super.setIme(ime);
	}

	@Override
	public String getPrezime() {
		// TODO Auto-generated method stub
		return super.getPrezime();
	}

	@Override
	public void setPrezime(String prezime) {
		// TODO Auto-generated method stub
		super.setPrezime(prezime);
	}
	
	public static PosjetilacPozorista pronadjiPosjetioca (String korisnicko, String passw)
	{
		sviPosjetioci = dao.vratiSve();
		
		
		for(PosjetilacPozorista p : sviPosjetioci)
		{
			if (p.getKorisnickoIme().equals(korisnicko) && p.getLozinka().equals(passw))
			{
				return p;
			}
		}
		
		return null;
		
	}
	
	public Boolean unikanoIme (String korisnicko)
	{
		sviPosjetioci = dao.vratiSve();
		
		
		for(PosjetilacPozorista p : sviPosjetioci)
		{
			if (p.getKorisnickoIme().equals(korisnicko))
			{
				return false;
			}
		}
		
		
		return true;
	}
	
	public static PosjetilacPozorista vratiPoKorisnickom (String korisnicko)
	{
		sviPosjetioci = dao.vratiSve();
		
		
		for(PosjetilacPozorista p : sviPosjetioci)
		{
			if (p.getKorisnickoIme().equals(korisnicko))
			{
				return p;
			}
		}
		
		return null;
	}
	
	public static ArrayList<String> vratiMojeTekuceKarte()
	{
		int ctr = 1;
		
		sveKarte = daoKarta.vratiSve();
		
		LocalDate danas = LocalDate.now();
		
		mojeKarte.clear();
		
		if(sveKarte.isEmpty() == false)
		{
			for(Karta k : sveKarte)
			{
				if (k.getPpID() == trenutni.id)
				{
					if((k.getIp().getDatum().compareTo(danas))>=0) {
						mojeKarte.add(ctr + ": " + k.toString());
						ctr++;
					}
				}
			}
		}
		
		return mojeKarte;
		
		
	}
	
	public static ArrayList<String> vratiMojeProtekleKarte()
	{
		int ctr = 1;
		sveKarte = daoKarta.vratiSve();
		
		LocalDate danas = LocalDate.now();
		
		mojeKarteP.clear();
		
		if(sveKarte.isEmpty() == false)
		{
			for(Karta k : sveKarte)
			{
				if (k.getPpID() == trenutni.id)
				{
					if((k.getIp().getDatum().compareTo(danas))<0) {
						mojeKarteP.add(ctr + ": " + k.toString());
						ctr++;
					}
				}
			}
		}
		
		return mojeKarteP;	
	}
	
	public static ArrayList<String> vrati48()
	{
		int ctr = 0;
		lista48.clear();
		//listaObjekata.clear();
		sveKarte = daoKarta.vratiSve();
		
		LocalDate danas = LocalDate.now();
		
		
		
		if(sveKarte.isEmpty() == false)
		{
			for(Karta i: sveKarte)
			{
				
					if(i.getIp().getDatum().equals(danas.plusDays(2)) || i.getIp().getDatum().equals(danas.plusDays(1)) || i.getIp().getDatum().equals(danas)){
						lista48.add(i.getIp().toRadnikRepertoar());
						ctr++;
						System.out.println(i.getIp().toRadnikRepertoar()+ "ajmo mali" + ctr);
						
					}
				
			}
		}
		
		if (lista48.isEmpty())
			lista48.add("Nemate nepreuzetih rezervacija za predstave u narednih 48 h. :)");
		
		return lista48;
		
	}

	@Override
	public String toString() {
		return super.ime + " " + super.prezime + " - " + super.korisnickoIme;
	}

}
