package modeli;

public class OsobljePredstave {
	private int id;
	private Osoblje OsobljeID;
	private Predstava PredstavaID;
	public OsobljePredstave(int id, Osoblje osobljeID, Predstava predstavaID) {
		this.id = id;
		this.OsobljeID = osobljeID;
		this.PredstavaID = predstavaID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Osoblje getOsobljeID() {
		return OsobljeID;
	}
	public void setOsobljeID(Osoblje osobljeID) {
		OsobljeID = osobljeID;
	}
	public Predstava getPredstavaID() {
		return PredstavaID;
	}
	public void setPredstavaID(Predstava predstavaID) {
		PredstavaID = predstavaID;
	}
	@Override
	public String toString() {
		return "OsobljePredstave [id=" + id + ", OsobljeID=" + OsobljeID + ", PredstavaID=" + PredstavaID + "]";
	}
	

}
