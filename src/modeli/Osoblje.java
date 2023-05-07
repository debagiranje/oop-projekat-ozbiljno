package modeli;

public class Osoblje extends Osoba{
	
	private int tip;

	public Osoblje(int id, String ime, String prezime, int tip) {
		super(id, ime, prezime);
		this.setTip(tip);
	}

	public Osoblje(int id, String ime, String prezime) {
		super(id, ime, prezime);
	}

	public int getTip() {
		return tip;
	}

	public void setTip(int tip) {
		this.tip = tip;
	}

}
