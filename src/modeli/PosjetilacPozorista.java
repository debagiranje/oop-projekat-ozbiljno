package modeli;

public class PosjetilacPozorista extends Korisnik{

	public PosjetilacPozorista(int id, String ime, String prezime, String korisnickoIme, String lozinka) {
		super(id, ime, prezime, korisnickoIme, lozinka);
	}

	@Override
	public String getKorisnickoIme() {
		// TODO Auto-generated method stub
		return super.getKorisnickoIme();
	}

	@Override
	public void setKorisnickoIme(String korisnickoIme) {
		// TODO Auto-generated method stub
		super.setKorisnickoIme(korisnickoIme);
	}

	@Override
	public String getLozinka() {
		// TODO Auto-generated method stub
		return super.getLozinka();
	}

	@Override
	public void setLozinka(String lozinka) {
		// TODO Auto-generated method stub
		super.setLozinka(lozinka);
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
		return "PosjetilacPozorista " + super.toString() + "]";
	}

}
