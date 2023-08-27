package modeli;

import java.util.ArrayList;

import baza.pomocnici.PozoristeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Pozoriste {
	private int id;
	private String naziv;
	private String grad;
	private int brojSjedista;
	
	
	private static PozoristeDAO pDAO = new PozoristeDAO();
	private static ArrayList<String> listaPozorista = new ArrayList<>();
	private static ArrayList<Pozoriste> sve = new ArrayList<>();
	private static ObservableList<String> sviZanrovi = FXCollections.observableArrayList();
	private static ObservableList<String> pozorista = FXCollections.observableArrayList();
	
	public static Pozoriste trenutno;
	public static int IDtrenutnog;
	
	public Pozoriste(int id, String naziv, String grad, int brojSjedista) {
		this.id = id;
		this.naziv = naziv;
		this.grad = grad;
		this.brojSjedista = brojSjedista;
	}
	public int getId() {
		return id;
	}
	public Pozoriste(String naziv, String grad, int brojSjedista) {
		super();
		this.naziv = naziv;
		this.grad = grad;
		this.brojSjedista = brojSjedista;
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
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public int getBrojSjedista() {
		return brojSjedista;
	}
	public void setBrojSjedista(int brojSjedista) {
		this.brojSjedista = brojSjedista;
	}
	
	
	
	public static ArrayList<String> vratiPozorista()
	{
		listaPozorista.clear();
		sve = pDAO.vratiSve();
		for(Pozoriste p: sve)
		{
			listaPozorista.add(p.naziv);
		}
		return listaPozorista;
		
	}
	
	public static ObservableList<String> vratiPozoristaRadniku()
	{
		pozorista.clear();
		sve = pDAO.vratiSve();
		
		pozorista.add(RadnikPozorista.trenutni.getPozoristeID().naziv);
		for(Pozoriste p: sve)
		{
			if(RadnikPozorista.radniciPozorista(p.getId())==false)
				pozorista.add(p.naziv);
		}
		return pozorista;
		
	}
	
	
	public static ObservableList<String> vratiZanrove()
	{
		sviZanrovi.clear();
	
		for (int i = 1; i < 8; i++) 
		{
			sviZanrovi.add(Zanr.getString(i));
		}
		return sviZanrovi;
		
	}
	
	public static int strVratiID(String strPozoriste)
	{
		sve = pDAO.vratiSve();
		for(Pozoriste p: sve)
		{
			if(p.naziv.equals(strPozoriste))
				return p.id;
		}
		return 0;
		
	}
	
	public boolean brKarataValidan(int br)
	{
		trenutno = pDAO.vratiPoId(IDtrenutnog);
		System.out.println(trenutno.getBrojSjedista());
		if(trenutno.getBrojSjedista()>=br)
			return true;
		else
		{
			System.out.println("Ups! Nemamo toliko mjesta! Maksimalan broj karata koji mozete rezervisati je: "+trenutno.getBrojSjedista());
			return false;
		}
	}
	
	
	
	@Override
	public String toString() {
		return "Pozoriste " + naziv + ", grad " + grad + ", broj sjedista: " + brojSjedista + "]";
	}
	
	public static void dodajPozoriste(String naziv, String grad, int brojSjedista)
	{
		Pozoriste p = new Pozoriste(naziv, grad, brojSjedista);
		pDAO.dodaj(p);
	}
	
	public static boolean vecPostoji(String naziv, String grad)
	{
		sve = pDAO.vratiSve();
		for(Pozoriste p: sve)
		{
			if(p.getNaziv().equals(naziv) && p.getGrad().equals(grad))
				return true;
		}
		return false;
		
	}
	

}
