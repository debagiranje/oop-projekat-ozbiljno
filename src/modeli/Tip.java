package modeli;

public enum Tip {
	NEDEF(0),
	GLUMAC(1),
	REZISER(2),
	AUTOR(3);
	
	private int broj;
	
	Tip(int broj){
		this.broj = broj;
	}
	
	public static Tip getEnum(int broj){
		switch(broj) {
			case 1: return Tip.GLUMAC;
			case 2: return Tip.REZISER;
			case 3: return Tip.AUTOR;
			default: return NEDEF;
		}
	}
	public static String getString(int broj){
		switch(broj) {
			case 1: return "Glumac";
			case 2: return "Reziser";
			case 3: return "Autor";
			default: return "Tip nedefinisan";
		}
	}
	
	public int getTip() {
		return broj;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}

}
