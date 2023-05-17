package modeli;

public abstract class Osoba {
	protected int id;
	protected String ime;
	protected String prezime;
	
	public Osoba(int id, String ime, String prezime) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
	}
	public int getId() {
		return id;
	}
	public Osoba(String ime, String prezime) {
		super();
		this.ime = ime;
		this.prezime = prezime;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	@Override
	public String toString() {
		return "Osoba id=" + id + ", ime=" + ime + ", prezime=" + prezime;
	}
	

}
