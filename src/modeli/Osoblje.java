package modeli;

import java.util.ArrayList;
import java.util.List;

import baza.BazaCRUD;
import baza.pomocnici.OsobljeDAO;
import javafx.scene.control.CheckMenuItem;


public class Osoblje extends Osoba{
	
	private Tip tip;
	private int tip2;
	
	private int cnt =  0;
	
	private int flag_najgluplji_na_svijetu = 0;
	
	private static BazaCRUD<Osoblje> dao = new OsobljeDAO(); // ovo bi trebalo da je pravilan poziv...
	
	public static ArrayList<Osoblje> osoblje = new ArrayList<>();
	
	public static ArrayList<String> sviAutori = new ArrayList<>();
	public static List<CheckMenuItem> sviGlumci = new ArrayList<>();
	public static ArrayList<String> sviReziseri = new ArrayList<>();

	
	public Osoblje(String ime, String prezime, int tip2) {
		super(ime, prezime);
		this.setTip2(tip2);
		flag_najgluplji_na_svijetu = 3;
	}

	public Osoblje(int id, String ime, String prezime, int tip2) {
		super(id, ime, prezime);
		this.tip2 = tip2;
		flag_najgluplji_na_svijetu = 4;
	}

	public int getTip() {
		if (flag_najgluplji_na_svijetu == 2)
			return tip.getTip();
		else {
		//System.out.println(tip.getTip() + "Juhuhuhuhuhu");
		return tip2;
		}
	}

	public void setTip(Tip tip) {
		this.tip = tip;
	}

	@Override
	public String toString() {
			return "Osoblje [tip=" + Tip.getString(tip2) + ", " + super.toString() + "]";

	}
	

	public int getTip2() {
		return tip2;
	}

	public void setTip2(int tip2) {
		this.tip2 = tip2;
	}
	
	
	public void povecaj()
	{
		setCnt(getCnt() + 1);
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
	public static ArrayList<String> vratiAutore()
	{
		sviAutori.clear();
		osoblje = dao.vratiSve();
		
		for(Osoblje o : osoblje)
		{
			if(o.tip2 == 3)
			{
				sviAutori.add(o.ime + " " + o.prezime);
			}
		}
		
		sviAutori.add("");
		return sviAutori;	
	}
	
	
	public static ArrayList<String> vratiRezisere()
	{
		sviReziseri.clear();
		osoblje = dao.vratiSve();
		
		for(Osoblje o : osoblje)
		{
			if(o.tip2 == 2)
			{
				sviReziseri.add(o.ime + " " + o.prezime);
			}
		}
		
		sviReziseri.add("");
		
		return sviReziseri;	
	}
	
	public static List<CheckMenuItem> vratiGlumce()
	{
		sviGlumci.clear();
		osoblje = dao.vratiSve();
		
		for(Osoblje o : osoblje)
		{
			if(o.tip2 == 1)
			{
				sviGlumci.add(new CheckMenuItem(o.ime + " " + o.prezime));
			}
		}
		
		//CheckMenuItem mirko = new CheckMenuItem("Dodaj Glumca...");
		
		//sviGlumci.add(mirko);
		return sviGlumci;	
	}
	
	public static void dodajOsoblje(String imePrezime, int tip)
	{
		String[] podijeli = imePrezime.split(" ", 2);
		Osoblje o = new Osoblje(podijeli[0], podijeli[1], tip);
		dao.dodaj(o);
	}
	
	public static int vratiPoStringu(String imePrezime, int tip)
	{
		osoblje = dao.vratiSve();
		
		String[] podijeli = imePrezime.split(" ", 2);
		
		for(Osoblje os:osoblje)
		{
			if(os.ime.equals(podijeli[0])&&os.prezime.equals(podijeli[1]))
				return os.id;
		}
		
		return -1;
		
	}
}
