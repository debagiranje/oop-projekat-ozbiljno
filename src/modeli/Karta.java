package modeli;

public class Karta {
	private int id;
	private IzvodjenjePredstave ipID;
	private Status status;
	private PosjetilacPozorista ppID;
	private int brojKarta;
	public int getId() {
		return id;
	}
	public Karta(int id, IzvodjenjePredstave ipID, Status status, PosjetilacPozorista ppID, int brojKarta) {
		super();
		this.id = id;
		this.ipID = ipID;
		this.status = status;
		this.ppID = ppID;
		this.brojKarta = brojKarta;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Karta(IzvodjenjePredstave ipID, Status status, PosjetilacPozorista ppID, int brojKarta) {
		super();
		this.ipID = ipID;
		this.status = status;
		this.ppID = ppID;
		this.brojKarta = brojKarta;
	}
	public IzvodjenjePredstave getIpID() {
		return ipID;
	}
	public void setIpID(IzvodjenjePredstave ipID) {
		this.ipID = ipID;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public PosjetilacPozorista getPpID() {
		return ppID;
	}
	public void setPpID(PosjetilacPozorista ppID) {
		this.ppID = ppID;
	}
	public int getBrojKarta() {
		return brojKarta;
	}
	@Override
	public String toString() {
		return "Karta [id=" + id + ", ipID=" + ipID + ", status=" + status + ", ppID=" + ppID + ", brojKarta="
				+ brojKarta + "]";
	}
	public void setBrojKarta(int brojKarta) {
		this.brojKarta = brojKarta;
	}

}
