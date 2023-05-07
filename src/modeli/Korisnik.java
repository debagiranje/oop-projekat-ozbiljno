package modeli;

public abstract class Korisnik extends Osoba{
	
	protected String korisnickoIme;
	protected String lozinka;
	
	
	public Korisnik(int id, String ime, String prezime, String korisnickoIme, String lozinka) {
		super(id, ime, prezime);
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	@Override
	public String getIme() {
		// TODO Auto-generated method stub
		return super.getIme();
	}

	@Override
	public void setIme(String ime) {
		// TODO Auto-generated method stub
		super.setIme(ime);
	}

	@Override
	public String getPrezime() {
		// TODO Auto-generated method stub
		return super.getPrezime();
	}

	@Override
	public void setPrezime(String prezime) {
		// TODO Auto-generated method stub
		super.setPrezime(prezime);
	}

	@Override
	public String toString() {
		return "Korisnik [korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", id=" + id + ", ime=" + ime
				+ ", prezime=" + prezime + "]";
	}
	

}
