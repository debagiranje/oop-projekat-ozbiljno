package modeli;

public enum Zanr {
	NEDEF(0),
	KOMEDIJA(1),
	FARSA(2),
	SATIRA(3),
	KOMEDIJA_RESTAURACIJE(4),
	TRAGEDIJA(5),
	ISTORIJA(6),
	MJUZIKL(7);
	
	private int broj;
	
	Zanr(int broj){
		this.broj = broj;
	}
	public static Zanr getEnum(int broj){
		switch(broj) {
			case 1: return Zanr.KOMEDIJA;
			case 2: return Zanr.FARSA;
			case 3: return Zanr.SATIRA;
			case 4: return Zanr.KOMEDIJA_RESTAURACIJE;
			case 5: return Zanr.TRAGEDIJA;
			case 6: return Zanr.ISTORIJA;
			case 7: return Zanr.MJUZIKL;
			default: return NEDEF;
		}
	}
	
	public static String getString(int broj){
		switch(broj) {
			case 1: return "Komedija";
			case 2: return "Farsa";
			case 3: return "Satira";
			case 4: return "Komedija Restauracije";
			case 5: return "Tragedija";
			case 6: return "Istorija";
			case 7: return "Mjuzikl";
			default: return "Zanr nedefinisan";
		}
	}
	
	public int getBroj() {
		return broj;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}
	static Zanr getEnum(String zanr) {
		switch(zanr) {
		case "Komedija": return Zanr.KOMEDIJA;
		case "Farsa": return Zanr.FARSA;
		case "Satira": return Zanr.SATIRA;
		case "Komedija Restauracije": return Zanr.KOMEDIJA_RESTAURACIJE;
		case "Tragedija": return Zanr.TRAGEDIJA;
		case "Istorija": return Zanr.ISTORIJA;
		case "Mjuzikl": return Zanr.MJUZIKL;
		default: return NEDEF;
	}
	}

}
