package modeli;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import baza.pomocnici.IzvodjenjePredstaveDAO;
//import baza.pomocnici.KartaDAO;
import baza.pomocnici.PozoristeDAO;
import baza.pomocnici.PredstavaDAO;

public class IzvodjenjePredstave {
	
    private PozoristeDAO pozoristeDAO = new PozoristeDAO();
    private PredstavaDAO predstavaDAO = new PredstavaDAO();
    //private static KartaDAO kartaDAO = new KartaDAO();
	
	private int id;
	private Predstava predstava;
	private Pozoriste pozoriste;
	private double cijena;
	private LocalDateTime datumVrijeme;
	
	
	private static IzvodjenjePredstaveDAO DAO = new IzvodjenjePredstaveDAO();
	
	private static ArrayList<String> listaIzvodjenja = new ArrayList<>();
	private static ArrayList<String> listaTekucihIzvodjenja = new ArrayList<>();
	private static ArrayList<String> listaProteklihIzvodjenja = new ArrayList<>();
	
	private static ArrayList<IzvodjenjePredstave> listaObjekata = new ArrayList<>();
	private static ArrayList<IzvodjenjePredstave> listaObjekataPredstojece = new ArrayList<>();
	private static ArrayList<IzvodjenjePredstave> listaObjekataPrethodne = new ArrayList<>();
	
	private static ArrayList<IzvodjenjePredstave> sve = new ArrayList<>();
	
	public static IzvodjenjePredstave trenutno;
	
	public int nezauzetaSjedista;
	
	//private static Dictionary<Integer, Integer> dict= new Hashtable<>();
	
	public IzvodjenjePredstave(int id, int predstavaID, int pozoristeID, double cijena,
			LocalDateTime datumVrijeme) {
		this.id = id;
		this.predstava = predstavaDAO.vratiPoId(predstavaID);
		this.pozoriste = pozoristeDAO.vratiPoId(pozoristeID);
		this.cijena = cijena;
		this.datumVrijeme = datumVrijeme;
	}
	
	

	public IzvodjenjePredstave(int predstavaID, int pozoristeID, double cijena,
			LocalDateTime datumVrijeme) {
		this.predstava = predstavaDAO.vratiPoId(predstavaID);
		this.pozoriste = pozoristeDAO.vratiPoId(pozoristeID);
		this.cijena = cijena;
		this.datumVrijeme = datumVrijeme;
		
		this.nezauzetaSjedista = pozoriste.getBrojSjedista();
	}



	public IzvodjenjePredstave(int int1) {
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Predstava getPredstava() {
		return predstava;
	}

	public void setPredstava(Predstava predstavaID) {
		this.predstava = predstavaID;
	}

	public Pozoriste getPozoriste() {
		return pozoriste;
	}

	public void setPozoriste(Pozoriste pozoristeID) {
		this.pozoriste = pozoristeID;
	}

	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}

	public LocalDateTime getDatumVrijeme() {
		return datumVrijeme;
	}
	
	public LocalDate getDatum() {
		return datumVrijeme.toLocalDate();
	}
	
	public LocalTime getVrijeme() {
		return datumVrijeme.toLocalTime();
	}

	public void setDatumVrijeme(LocalDateTime datumVrijeme) {
		this.datumVrijeme = datumVrijeme;
	}
	
	public void povecajBrojNezauzetih(int brKarata) {
		this.nezauzetaSjedista += brKarata;
	}
	
	public void umanjiBrojNezauzetih(int brKarata) {
		this.nezauzetaSjedista -= brKarata;
	}
	
	public boolean datumIstekao(IzvodjenjePredstave ip)
	{
		LocalDateTime danas = LocalDateTime.now();
		if(ip.getDatumVrijeme().isBefore(danas))
		{
			System.out.println("Datum izvodjenja je istekao, molimo vas odaberite drugu...");
			return true;
		}
		return false;
	}
	
	
	public static ArrayList<String> vratiTekucaIzvodjenja(int pozoristeID)
	{
		int ctr = 1;
		listaTekucihIzvodjenja.clear();
		listaObjekata.clear();
		sve = DAO.vratiSve();
		
		LocalDate danas = LocalDate.now();
		
		
		if(sve.isEmpty() == false)
		{
			for(IzvodjenjePredstave i: sve)
			{
				if(i.pozoriste.getId() == pozoristeID)
				{
					if((i.getDatum().compareTo(danas))>=0) {
						listaTekucihIzvodjenja.add(i.toRepertoarString());
						listaObjekataPredstojece.add(i);
						//System.out.println(i.toRadnikRepertoar()+ "ajmo mali");
						ctr++;
					}
				}
			}
		}
		
		return listaTekucihIzvodjenja;
		
	}
	
	public static ArrayList<String> vratiProteklaIzvodjenja(int pozoristeID)
	{
		int ctr = 1;
		listaProteklihIzvodjenja.clear();
		listaObjekata.clear();
		sve = DAO.vratiSve();
		
		LocalDate danas = LocalDate.now();
		
		
		if(sve.isEmpty() == false)
		{
			for(IzvodjenjePredstave i: sve)
			{
				if(i.pozoriste.getId() == pozoristeID)
				{
					if((i.getDatum().compareTo(danas))<0) {
						listaProteklihIzvodjenja.add(i.toRepertoarString());
						listaObjekataPrethodne.add(i);
						//System.out.println(i.toRadnikRepertoar()+ "ajmo mali");
						ctr++;
					}
				}
			}
		}
		
		return listaProteklihIzvodjenja;
		
	}
	
	
	
	
	public static ArrayList<String> vratiIzvodjenja(int pozoristeID)
	{
		listaIzvodjenja.clear();
		listaObjekata.clear();
		sve = DAO.vratiSve();
		for(IzvodjenjePredstave i: sve)
		{
			if(i.pozoriste.getId() == pozoristeID)
			{
				listaIzvodjenja.add(i.toRepertoarString());
				listaObjekata.add(i);
				//dict.put(ctr, i.getId());
			}
		}
		return listaIzvodjenja;
		
	}
	
	public static ArrayList<String> vratiPredstave(int pozoristeID)
	{
		listaIzvodjenja.clear();
		listaObjekata.clear();
		sve = DAO.vratiSve();
		for(IzvodjenjePredstave i: sve)
		{
			if(i.pozoriste.getId() == pozoristeID)
			{
				if(!listaIzvodjenja.contains(i.predstava.getNaziv()))
						listaIzvodjenja.add(i.toRepertoarString());
				//listaObjekata.add(i);
				//dict.put(ctr, i.getId());
			}
		}
		return listaIzvodjenja;
		
	}
	
	
	public static void vratiIzStringa(String Naziv)
	{
		//sve = DAO.vratiSve();
		//System.out.println(Naziv + "iz vrati iz stringa, yippee");
		for(IzvodjenjePredstave i: listaObjekata)
		{
			//System.out.println(Naziv + " ovo je naziv, a ovo sta je u listi: " + i.toRepertoarString());
			if(i.toRepertoarString().equals(Naziv))
			{
				//if(unikatnoIzvodjenje(i) == true)
					trenutno = i;
				//else
					//trenutno = null;
				
			}
		}
		//return trenutno;
		
	}
	
	/*private static boolean unikatnoIzvodjenje (IzvodjenjePredstave ip)
	{
		ArrayList<Karta> sveKarte = kartaDAO.vratiSve();
		for(Karta k: sveKarte)
		{
			if(k.getIp().getId() == ip.getId())
				return false;
		}
		return true;
	}*/
	
	private static boolean vecPostoji(IzvodjenjePredstave ipp) {
		sve = DAO.vratiSve();
        for (IzvodjenjePredstave ip : sve) {
            if (ip.getDatumVrijeme() == ipp.getDatumVrijeme() &&
                    ip.getPozoriste().getId() == ipp.pozoriste.getId()) {
                return true;
            }
        }
        return false;
    }
	
	public static void dodaj(int predstavaID, int pozoristeID,int cijena, LocalDateTime datum)
	{
		IzvodjenjePredstave ipp = new IzvodjenjePredstave(predstavaID, pozoristeID, cijena, datum);
		if(!vecPostoji(ipp))
			DAO.dodaj(ipp);
		System.out.println("Yippee! Dodat termincek");
	}

	@Override
	public String toString() {
		return "Predstava " + predstava.getNaziv()+ ", pozorište " + pozoriste.getNaziv()
				+ ". Cijena: " + cijena + " KM. Termin: " + getDatum() + ", u " + getVrijeme() + " h. ";
	}
	
	public String toRepertoarString() {
		return "Naziv: " + predstava.getNaziv()+ ". Cijena: " + cijena + " KM. Termin: " + getDatum() + ", u " + getVrijeme() + " h. ";
	}
	
	public String toRadnikRepertoar() {
		return  predstava.getNaziv()+ ", Termin: " + getDatum() + ", u " + getVrijeme() + " h. ";
	}



	public static void vratiIzStringaPredstojece(String Naziv) {
		for(IzvodjenjePredstave i: listaObjekataPredstojece)
		{
			System.out.println(Naziv + " ovo je naziv, a ovo sta je u listi: " + i.toRepertoarString());
			if(i.toRepertoarString().equals(Naziv))
			{
				//if(unikatnoIzvodjenje(i) == true)
					trenutno = i;
				//else
					//trenutno = null;
				
			}
		}
		
	}



	public static void vratiIzStringaPrethodne(String Naziv) {
		for(IzvodjenjePredstave i: listaObjekataPrethodne)
		{
			System.out.println(Naziv + " ovo je naziv, a ovo sta je u listi: " + i.toRepertoarString());
			if(i.toRepertoarString().equals(Naziv))
			{
				//if(unikatnoIzvodjenje(i) == true)
					trenutno = i;
				//else
					//trenutno = null;
				
			}
		}
		
	}
	
	

}
