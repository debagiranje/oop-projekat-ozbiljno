package modeli;

import java.time.LocalDateTime;

public class IzvodjenjePredstave {
	
	private int id;
	private Predstava predstavaID;
	private Pozoriste pozoristeID;
	private double cijena;
	private LocalDateTime datumVrijeme;
	
	public IzvodjenjePredstave(int id, Predstava predstavaID, Pozoriste pozoristeID, double cijena,
			LocalDateTime datumVrijeme) {
		this.id = id;
		this.predstavaID = predstavaID;
		this.pozoristeID = pozoristeID;
		this.cijena = cijena;
		this.datumVrijeme = datumVrijeme;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Predstava getPredstavaID() {
		return predstavaID;
	}

	public void setPredstavaID(Predstava predstavaID) {
		this.predstavaID = predstavaID;
	}

	public Pozoriste getPozoristeID() {
		return pozoristeID;
	}

	public void setPozoristeID(Pozoriste pozoristeID) {
		this.pozoristeID = pozoristeID;
	}

	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}

	public LocalDateTime getDatumVrijeme() {
		return datumVrijeme;
	}

	public void setDatumVrijeme(LocalDateTime datumVrijeme) {
		this.datumVrijeme = datumVrijeme;
	}

	@Override
	public String toString() {
		return "IzvodjenjePredstave [id=" + id + ", predstavaID=" + predstavaID + ", pozoristeID=" + pozoristeID
				+ ", cijena=" + cijena + ", datumVrijeme=" + datumVrijeme + "]";
	}

}
