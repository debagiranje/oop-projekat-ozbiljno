package modeli;

public class Predstava {
	
	private int id;
	private String naziv;
	private Zanr zanr;
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
	public Predstava(int id, String naziv, Zanr zanr) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.zanr = zanr;
	}
	@Override
	public String toString() {
		return "Predstava [id=" + id + ", naziv=" + naziv + ", zanr=" + zanr + "]";
	}
	
	

}
