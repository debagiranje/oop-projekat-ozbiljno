package modeli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import baza.pomocnici.OsobljeDAO;
import baza.pomocnici.OsobljePredstaveDAO;
import baza.pomocnici.PredstavaDAO;

public class OsobljePredstave {
	private int id;
	private Osoblje OsobljeID;
	private Predstava PredstavaID;
	
	
    private OsobljeDAO osobljeDAO = new OsobljeDAO();
    private PredstavaDAO predstavaDAO = new PredstavaDAO();
    
    private static OsobljePredstaveDAO DAO = new OsobljePredstaveDAO();
    
	private static ArrayList<String> listaOsoblja = new ArrayList<>();
	private static ArrayList<OsobljePredstave> sve = new ArrayList<>();
	
	private static ArrayList<String> predstave = new ArrayList<>();
	
	public static Map<String, Integer> autori = new HashMap<String, Integer>();
	public static Map<String, Integer> reziseri = new HashMap<String, Integer>();
	public static Map<String, Integer> glumci = new HashMap<String, Integer>();
	
	private static ArrayList<OsobljePredstave> lista = new ArrayList<>();
	
	public static OsobljePredstave trenutno = null;
	
	
	public OsobljePredstave(int id, int osoblje, int predstava) {
		this.id = id;
		OsobljeID = osobljeDAO.vratiPoId(osoblje);
		PredstavaID = predstavaDAO.vratiPoId(predstava);
	}
	public OsobljePredstave(int osoblje, int predstava) {
		super();
		OsobljeID = osobljeDAO.vratiPoId(osoblje);
		PredstavaID = predstavaDAO.vratiPoId(predstava);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Osoblje getOsobljeID() {
		return OsobljeID;
	}
	public void setOsobljeID(Osoblje osobljeID) {
		OsobljeID = osobljeID;
	}
	public Predstava getPredstavaID() {
		return PredstavaID;
	}
	public void setPredstavaID(Predstava predstavaID) {
		PredstavaID = predstavaID;
	}
	@Override
	public String toString() {
		return "OsobljePredstave [id=" + id + ", OsobljeID="+ OsobljeID.toString() + ", PredstavaID="+PredstavaID.toString() + "]";
	}
	
	public String imePrezimeTip()
	{
		return OsobljeID.getIme() + " " + OsobljeID.getPrezime() + ", " + Tip.getString(OsobljeID.getTip());
	}
	
	
	public static ArrayList<String> vratiGlumce(int pid)
	{
		sve = DAO.vratiSve();
		listaOsoblja.clear();
		
		System.out.println(sve.isEmpty());
		
		for(OsobljePredstave o: sve)
		{
			if(o.getPredstavaID().getId() == pid)
			{
				if(o.getOsobljeID().getTip2()==1)
					listaOsoblja.add(o.getOsobljeID().ime + " " + o.getOsobljeID().prezime);
				
			}
		}
		
		return listaOsoblja;
		
	}
	
	public static String vratiAutora(int pid)
	{
		sve = DAO.vratiSve();
		
		
		for(OsobljePredstave o: sve)
		{
			if(o.getPredstavaID().getId() == pid)
			{
				if(o.getOsobljeID().getTip2()==3)
					return o.getOsobljeID().ime + " " + o.getOsobljeID().prezime;
				
			}
		}
		
		return null;
		
	}
	
	public static String vratiRezisera(int pid)
	{
		sve = DAO.vratiSve();
		
		
		for(OsobljePredstave o: sve)
		{
			if(o.getPredstavaID().getId() == pid)
			{
				if(o.getOsobljeID().getTip2()==2)
					return o.getOsobljeID().ime + " " + o.getOsobljeID().prezime;
				
			}
		}
		
		return null;
		
	}
	
	private boolean vecPostojiDodjela(int tip)
	{
		sve = DAO.vratiSve();
		
		for(OsobljePredstave o: sve)
		{
			if(o.PredstavaID.getId()==this.getPredstavaID().getId() && o.OsobljeID.getTip()==tip)
				return true;
			
		}
		
		return false;
		
	}
	
	private boolean vecPostojiGlumac()
	{
		sve = DAO.vratiSve();
		
		for(OsobljePredstave o: sve)
		{
			if(o.PredstavaID.getId()==this.getPredstavaID().getId() && o.OsobljeID.equals(this.OsobljeID))
				return true;
			
		}
		
		return false;
		
	}
	
	/*
	 * Ne razumijem sta sam htjela sa ovim azuriranjem da postignem... :) 
	 * Hajde, recimo, da napravimo zasebnu metodu koja ce cijelu listu izabranih glumaca da azurira
	 * 
	 * A za ove singletone, provjeri da li to pozoriste vec ima autora/rezisera
	 * ukoliko ima, azuriraj(?)
	 * 
	 * Treba jos nekakve sicusne parametre da dodamo... 
	*/
	
	public static void dodajAutora(int o, int pID)
	{
		OsobljePredstave op = new OsobljePredstave(o, pID);
		
		if(op.vecPostojiDodjela(3))
			DAO.azuriraj(op);
		else
			DAO.dodaj(op);
	}
	
	public static void dodajRezisera(int o, int pID)
	{
		OsobljePredstave op = new OsobljePredstave(o, pID);
		
		if(op.vecPostojiDodjela(2))
			DAO.azuriraj(op);
		else
			DAO.dodaj(op);
	}
	
	/*
	 * Ovdje bismo mogli, recimo, da za niz glumaca izgenerisemo po objekat osoblje
	 * Gledamo predstavu - ukoliko nema nijednog glumca, dodaj sve izabrane - DODAJ
	 * Inace
	 * Poredi sve glumce iz predstave (u bazi) sa listom glumaca: - AZURIRAJ (dodaj/brisi? -- tehnicki nemamo update, nemamo neku trajnost indeksa kao za autora/rezisera)
	 * 	Ukoliko postoji i u bazi, i u listi - continue
	 * 	Ukoliko postoji u bazi, ali ne i u listi, izbrisi iz baze
	 * 	Ukoliko postoji u listi, ali ne i u bazi, dodaj u bazu
	 * 
	 * ili... Pazi sad ovo
	 * Mozda mi je cak jeftnije da obrisem sve glumce iz predstave, i nanovo dodam
	 * Joj gospode, najbolje je da ja spavam.
	 * 
	 */
	
	
	
	public static void dodajGlumce(ArrayList<String> glumci, int pID)
	{
		//DAO.obrisiPoPredstavi(pID);
		
		for(String g : glumci)
		{
			OsobljePredstave op = new OsobljePredstave(Osoblje.vratiPoStringu(g, 1), pID);
			if(op.vecPostojiGlumac())
				DAO.azuriraj(op);
			else
				DAO.dodaj(op);
		}
		
	}
	
	////////
	
	public static ArrayList<OsobljePredstave> vratiOsoblje(int tip)
	{
		sve = DAO.vratiSve();
		lista.clear();
		
		for(OsobljePredstave o: sve)
		{
			if(o.getOsobljeID().getTip2()==tip)
			{
				//System.out.println(o.imePrezime());
				lista.add(o);
			}
		}
		
		return lista;
	}
	
	
	public static void brojPojavljivanjaOsoblja(int tip)
	{
		sve = DAO.vratiSve();
		
		lista = OsobljePredstave.vratiOsoblje(tip);
		System.out.print(lista.isEmpty() + " lista glumaca iz pojavljivanja");
		
		for(OsobljePredstave o: lista)
		{
			String naziv = o.imePrezimeTip();
			System.out.print(naziv + "iz broj pojavljivanja");
				if(tip == 1)
				{
					if(glumci.containsKey(naziv))
						glumci.merge(naziv, 1, (a, b) -> a + b);
					glumci.putIfAbsent(naziv, 1);
				}
				else if(tip == 2)
				{
					if(reziseri.containsKey(naziv))
						reziseri.merge(naziv, 1, (a, b) -> a + b);
					reziseri.putIfAbsent(naziv, 1);
					
				}
				else if(tip == 3)
				{
					if(autori.containsKey(naziv))
						autori.merge(naziv, 1, (a, b) -> a + b);
					autori.putIfAbsent(naziv, 1);
					
				}
				else
					System.out.println("greskica, nemamo taj tip");
			}
				
				
		}
	
	public static void izStringa(String str)
	{
		sve = DAO.vratiSve();
	
		
		for(OsobljePredstave o: sve)
		{
			if(o.imePrezimeTip().equals(str))
			{
				trenutno = o;
			}
		}
		
		
	}
	
	public static void izStringa(String str, int br)
	{
		sve = DAO.vratiSve();
	
		String s = str +  ", " + Tip.getString(br);
		for(OsobljePredstave o: sve)
		{
			if(o.imePrezimeTip().equals(s))
			{
				trenutno = o;
			}
		}
		
		
	}
	
	public static ArrayList<String> vratiPredstave(OsobljePredstave oo)
	{
		sve = DAO.vratiSve();
		predstave.clear();
		
		for(OsobljePredstave o: sve)
		{
			if(o.OsobljeID.getId() == oo.OsobljeID.getId())
			{
				predstave.add(o.getPredstavaID().getNaziv());
			}
		}
		return predstave;
		
	}

}
