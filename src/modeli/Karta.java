package modeli;

import java.util.ArrayList;

import baza.pomocnici.IzvodjenjePredstaveDAO;
import baza.pomocnici.KartaDAO;
import baza.pomocnici.PosjetilacPozoristaDAO;

public class Karta {
	private int id;
	private IzvodjenjePredstave ip;
	private Status status;
	private PosjetilacPozorista pp;
	private int brojKarta;
	
	
	IzvodjenjePredstaveDAO ipDAO = new IzvodjenjePredstaveDAO();
	static PosjetilacPozoristaDAO ppDAO = new PosjetilacPozoristaDAO();
	static KartaDAO kDAO = new KartaDAO();
	
	public static ArrayList<Karta> sveKarte = new ArrayList<>();
	public static ArrayList<String> sveRezervacije = new ArrayList<>();
	
	public static ArrayList<PosjetilacPozorista> sviPosjetioci= new ArrayList<>();
	public static ArrayList<String> korisniciZaRez = new ArrayList<>();
	
	
	public static ArrayList<String> posjetiociRezervacije = new ArrayList<>();
	public static ArrayList<String> posjetiociRezervacijeNjihove = new ArrayList<>();
	public static ArrayList<Karta> rezervacijeNjihoveLista = new ArrayList<>();
	
	public static Karta trenutno = null;
	
	public int getId() {
		return id;
	}
	public Karta(int id, int ipID, Status status, int ppID, int brojKarta) {
		this.id = id;
		this.ip = ipDAO.vratiPoId(ipID);
		this.status = status;
		this.pp = ppDAO.vratiPoId(ppID);
		this.brojKarta = brojKarta;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Karta(int ipID, Status status, int ppID, int brojKarta) {
		super();
		this.ip = ipDAO.vratiPoId(ipID);
		this.status = status;
		this.pp = ppDAO.vratiPoId(ppID);
		this.brojKarta = brojKarta;
	}
	public IzvodjenjePredstave getIp() {
		return ip;
	}
	public void setIpID(IzvodjenjePredstave ipID) {
		this.ip = ipID;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getPpID() {
		return pp.id;
	}
	public void setPpID(PosjetilacPozorista ppID) {
		this.pp = ppID;
	}
	public int getBrojKarta() {
		return brojKarta;
	}
	@Override
	public String toString() {
		return pp.toString() + " Broj rezervisanih karata je: "+ brojKarta + ". ";
	}
	
	public String KartaInfo() {
		return ip.toString() + " Broj rezervisanih karata je: "+ brojKarta + ". ";
	}
	public void setBrojKarta(int brojKarta) {
		this.brojKarta = brojKarta;
	}
	
	public static void ubaciMe(Karta k)
	{
		kDAO.dodaj(k);
		System.out.println("Juhu! Dodate karte!");
	}
	
	public static ArrayList<String> vratiRezervacijePozoriste(int pozoristeID)
	{
		sveRezervacije.clear();
		sveKarte = kDAO.vratiSve();
		
		
		
		if(sveKarte.isEmpty() == false)
		{
			for(Karta i: sveKarte)
			{
				if(i.ip.getPozoriste().getId()==pozoristeID)
					sveRezervacije.add(i.toString());
				
			}
		}
		return sveRezervacije;
		
	}
	
	public static ArrayList<String> vratiRezervacijeIzvodjenja(IzvodjenjePredstave izvodjenje)
	{
		sveRezervacije.clear();
		sveKarte = kDAO.vratiSve();
		
		int brBezRacuna = 0;
		
		if(sveKarte.isEmpty() == false)
		{
			for(Karta i: sveKarte)
			{
				if(i.ip.getId()==izvodjenje.getId() && i.getStatus()!=Status.REZERVISANA_NP)  // samo kupljene i preuzete
				{
					if(i.pp.getId()!=1)
						sveRezervacije.add(i.toString());
					else
					{
						brBezRacuna += i.brojKarta;
					}
				}
				
			}
		}
		sveRezervacije.add("Neregistrovani posjetioci - broj karata ukupno: " + brBezRacuna);
		return sveRezervacije;
		
	}
	
	public static ArrayList<String> vratiKorisnikeZaRez(IzvodjenjePredstave izvodjenje)
	{
		korisniciZaRez.clear();
		sveKarte = kDAO.vratiSve();
		sviPosjetioci = ppDAO.vratiSve();
		
		int flag = 0;
		for(PosjetilacPozorista pp: sviPosjetioci)
		{
			for(Karta k: sveKarte)
			{
				if(k.pp.getId() == pp.getId())
				{
					if(k.getIp().getId() == izvodjenje.getId())
					{
						flag = 1;
					}
				}
			}
			if(flag == 0)
			{
				if(pp.getId()!=1)
					korisniciZaRez.add(pp.toString());
			}
			flag = 0;
		}
		
		
		return korisniciZaRez;
		
	}
	
	
	public static int vratiBrojKarata(IzvodjenjePredstave izvodjenje)
	{
		sveKarte = kDAO.vratiSve();
		
		int brKarata = 0;
		
		if(sveKarte.isEmpty() == false)
		{
			for(Karta i: sveKarte)
			{
				if(i.ip.getId()==izvodjenje.getId())
				{
					brKarata += i.brojKarta;
				}
				
			}
		}
		
		return brKarata;
	}
	
	
	public static ArrayList<String> vratiPosjetioceRezervacija()
	{
		posjetiociRezervacije.clear();
		sveKarte = kDAO.vratiSve();
		sviPosjetioci = ppDAO.vratiSve();
		
		int flag = 0;
		
		for(PosjetilacPozorista pp: sviPosjetioci)
		{
			for(Karta k: sveKarte)
			{
				if(k.pp.getId() == pp.getId())
				{
					if(k.getStatus()==Status.REZERVISANA_NP && flag==0)
					{
						posjetiociRezervacije.add(pp.toString());
						flag = 1;
					}
				}
			}
			flag = 0;
		}
		
		return posjetiociRezervacije;
		
	}
	
	public static ArrayList<String> vratiNepreuzeteRezervacijePosjetioca(PosjetilacPozorista pp)
	{
		posjetiociRezervacijeNjihove.clear();
		rezervacijeNjihoveLista.clear();
		sveKarte = kDAO.vratiSve();
		sviPosjetioci = ppDAO.vratiSve();
	
			
		for(Karta k: sveKarte)
		{
			if(k.pp.getId() == pp.getId())
			{
				if(k.getStatus()==Status.REZERVISANA_NP)
				{
					posjetiociRezervacijeNjihove.add(k.getIp().toRepertoarString()+ " Broj karata: " + k.getBrojKarta());
					rezervacijeNjihoveLista.add(k);
					
				}
			}
		}
		
		
		return posjetiociRezervacijeNjihove;
		
	}
	
	public static void promijeniStatusPreuzeta(String izabrana)
	{
		for(Karta k: rezervacijeNjihoveLista)
		{
			if((k.getIp().toRepertoarString()+ " Broj karata: " + k.getBrojKarta()).equals(izabrana))
			{
				System.out.println("jupiiiiiiiiiiiiiiii");
				k.status = Status.REZERVISANA_P;
				kDAO.azuriraj(k);
				
			}
		}
	}
	
	public static void promijeniBrojKarata(String izabrana, int broj)
	{
		for(Karta k: rezervacijeNjihoveLista)
		{
			if((k.getIp().toRepertoarString()+ " Broj karata: " + k.getBrojKarta()).equals(izabrana))
			{
				if(k.brojKarta==broj)
					kDAO.obrisiPoId(k.getId());
				else
				{
					k.brojKarta -= broj;
					kDAO.azuriraj(k);
				}
				
			}
		}
	}
	
	public static void promijeniBrojKarata2(Karta kk, int broj)
	{

				if(kk.brojKarta==broj)
					kDAO.obrisiPoId(kk.getId());
				else
				{
					kk.brojKarta -= broj;
					kDAO.azuriraj(kk);
				}
				
	
	}
	
	public static void vratiIzStringa(String Naziv)
	{
		sveKarte = kDAO.vratiSve();
		System.out.println(Naziv + "iz vrati iz stringa, yippee");
		for(Karta k : sveKarte)
		{
			System.out.println(Naziv + "--" + k.KartaInfo());
			if(k.KartaInfo().equals(Naziv))
			{
				//if(unikatnoIzvodjenje(i) == true)
					trenutno = k;
				//else
					//trenutno = null;
				
			}
		}
		//return trenutno;
		
	}

}
