package modeli;

public class RadnikPozorista extends Korisnik{
	
	private Pozoriste pozoristeID;
	
	public RadnikPozorista(int id, String ime, String prezime, String korisnickoIme, String lozinka,
			Pozoriste pozoristeID) {
		super(id, ime, prezime, korisnickoIme, lozinka);
		this.pozoristeID = pozoristeID;
	}

	public Pozoriste getPozoristeID() {
		return pozoristeID;
	}
	public void setPozoristeID(Pozoriste pozoristeID) {
		this.pozoristeID = pozoristeID;
	}

	

}
