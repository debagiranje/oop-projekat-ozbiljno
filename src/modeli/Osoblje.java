package modeli;

public class Osoblje extends Osoba{
	
	private Tip tip;
	private int tip2;
	
	private int flag_najgluplji_na_svijetu = 0;

	public Osoblje(int id, String ime, String prezime, Tip tip) {
		super(id, ime, prezime);
		this.tip = tip;
		flag_najgluplji_na_svijetu = 1;
	}

	public Osoblje(String ime, String prezime, Tip tip) {
		super(ime, prezime);
		this.tip = tip;
		flag_najgluplji_na_svijetu = 2;
	}
	
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
		if (flag_najgluplji_na_svijetu == 2)
			return "Osoblje [tip=" + Tip.getString(tip.getTip()) + ", " + super.toString() + "]";
		else
			return "Osoblje [tip=" + Tip.getString(tip2) + ", " + super.toString() + "]";

			
	}
	

	public int getTip2() {
		System.out.println(tip2 + "Juhuhuhuhuhu");
		return tip2;
	}

	public void setTip2(int tip2) {
		this.tip2 = tip2;
	}

}
