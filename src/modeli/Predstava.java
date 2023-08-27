package modeli;

import java.util.ArrayList;

import baza.pomocnici.PredstavaDAO;

public class Predstava {
	
	private int id;
	private String naziv;
	private Zanr zanr;
	
	public static ArrayList<String> svePredstave = new ArrayList<>();
	public static ArrayList<Predstava> sve = new ArrayList<>();
	
	private static PredstavaDAO pDAO = new PredstavaDAO();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Zanr getZanr() {
		return zanr;
	}
	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
	}
	public Predstava(String naziv, Zanr zanr2) {
		super();
		this.naziv = naziv;
		this.zanr = (zanr2);
	}
	public Predstava(int id, String naziv, Zanr zanr) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.zanr = zanr;
	}
	@Override
	public String toString() {
		return naziv + ", žanr " + Zanr.getString(this.getZanr().getBroj());
	}
	
	public static ArrayList<String> vratiPredstavu()
	{
		svePredstave.clear();
		sve = pDAO.vratiSve();
		for(Predstava p: sve)
		{
			svePredstave.add(p.toString());
		}
		return svePredstave;
		
		
	}
	
	public static Boolean unikatanNaziv (String naziv)
	{
		sve = pDAO.vratiSve();
		
		
		for(Predstava p : sve)
		{
			if (p.naziv.equals(naziv))
			{
				return false;
			}
		}
		
		
		return true;
	}
	
	public static void dodajPredstavu(String Naziv, String zanr)
	{
		Predstava p = new Predstava(Naziv, Zanr.getEnum(zanr));
		pDAO.dodaj(p);
	}

	
	public static Predstava vratiPoStringu(String naziv)
	{
		sve = pDAO.vratiSve();
		
		String[] podijeli = naziv.split(",", 2);
		
		for(Predstava p : sve)
		{
			if (p.naziv.equals(podijeli[0]))
			{
				return p;
			}
		}
		
		return null;
		
	}
	
	

}
