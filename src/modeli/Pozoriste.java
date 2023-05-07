package modeli;

public class Pozoriste {
	private int id;
	private String naziv;
	private String grad;
	private int brojSjedista;
	
	public Pozoriste(int id, String naziv, String grad, int brojSjedista) {
		this.id = id;
		this.naziv = naziv;
		this.grad = grad;
		this.brojSjedista = brojSjedista;
	}
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
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public int getBrojSjedista() {
		return brojSjedista;
	}
	public void setBrojSjedista(int brojSjedista) {
		this.brojSjedista = brojSjedista;
	}
	@Override
	public String toString() {
		return "Pozoriste " + naziv + ", grad " + grad + ", broj sjedista: " + brojSjedista + "]";
	}
	
	

}
