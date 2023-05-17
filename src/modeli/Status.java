package modeli;

public enum Status {
	NEDEF(0),
	KUPLJENA_NPA(1),
	REZERVISANA_NP(2),
	REZERVISANA_P(3);
	
	private int broj;
	
	Status(int broj){
		this.broj = broj;
	}
	
	public static Status getEnum(int broj){
		switch(broj) {
			case 1: return Status.KUPLJENA_NPA;
			case 2: return Status.REZERVISANA_NP;
			case 3: return Status.REZERVISANA_P;
			default: return NEDEF;
		}
	}
	public static String getString(int broj){
		switch(broj) {
			case 1: return "Karta kupljena nekoristeci ovu aplikaciju";
			case 2: return "Karta rezervisana, nije preuzeta";
			case 3: return "Karta rezervisana, preuzeta";
			default: return "Status nedefinisan";
		}
	}
	
	public int getBroj() {
		return broj;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}
	

}
