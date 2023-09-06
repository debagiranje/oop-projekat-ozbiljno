package kontroleri.radnik;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import kontroleri.PomocniKontroler;
import modeli.IzvodjenjePredstave;
import modeli.Karta;
import modeli.PosjetilacPozorista;
import modeli.RadnikPozorista;
import modeli.Status;


public class ProdajController implements Initializable{
	@FXML
	private Button btnIzloguj;
	@FXML
	private Label nazivPozorista;
	@FXML
	private Button btnOmeni;
	@FXML
	private Button btnKreiraj;
	@FXML
	private Button btnOsoblje;
	@FXML
	private Button btnRezervacije;
	@FXML
	private Button btnPredstave;
	@FXML
	private Button btnIzvodjenja;
	@FXML
	private Label statusSjedista;
	@FXML
	private Label brojSlobodnih;
	@FXML
	private Label ipString;
	@FXML
	private Spinner<Integer> brKarataKupac;
	@FXML
	private Button prodajKupac;
	@FXML
	private ListView<String> lvKorisnici;
	@FXML
	private Spinner<Integer> brKarataKorisnici;
	@FXML
	private Button prodajKorisnici;

	public IzvodjenjePredstave ip;
	
	// Event Listener on Button[#btnOmeni].onAction
		@FXML
		public void oMeni(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.oMeniScena(event);
		}
		// Event Listener on Button[#btnKreiraj].onAction
		@FXML
		public void KreirajScena(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.kreirajScena(event);
		}
		// Event Listener on Button[#btnOsoblje].onAction
		@FXML
		public void osobljeScena(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.osobljeScena(event);
		}
		// Event Listener on Button[#btnRezervacije].onAction
		@FXML
		public void rezervacijeScena(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.rezervacijaScena(event);
		}
		// Event Listener on Button[#btnPredstave].onAction
		@FXML
		public void predstaveScena(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.predstaveScena(event);
		}
		// Event Listener on Button[#btnIzvodjenja].onAction
		@FXML
		public void izvodjenjaScena(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.izvodjenjaScena(event);
		}
		@FXML
		public void izloguj(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.izlogujScena(event);
		}
		
	
	// Event Listener on Button[#prodajKupac].onAction
	@FXML
	public void prodajKupcima(ActionEvent event) {
		
		int br = brKarataKorisnici.getValue();
		
		if(ip.getPozoriste().getBrojSjedista()-Karta.vratiBrojKarata(ip)-br <0)
			PomocniKontroler.upozorenjeAlert("Nazalost, nemamo toliko karata na raspolaganju.", "");
		else
		{
			Karta k = new Karta(ip.getId(), Status.KUPLJENA_NPA, 1, br);
			Karta.ubaciMe(k);
		
			statusSjedista.setText(Karta.vratiBrojKarata(ip)+ " / " + ip.getPozoriste().getBrojSjedista());
			brojSlobodnih.setText(ip.getPozoriste().getBrojSjedista()-Karta.vratiBrojKarata(ip) + "");
		}
		
	}
	// Event Listener on Button[#prodajKorisnici].onAction
	@FXML
	public void prodajKorisnicima(ActionEvent event) {
		String izabrani = lvKorisnici.selectionModelProperty().get().getSelectedItem();
		int br = brKarataKorisnici.getValue();
		
		String[] korisnicko = izabrani.split(" - ", 2);
		 
		PosjetilacPozorista pp = PosjetilacPozorista.vratiPoKorisnickom(korisnicko[1]);
		
		if(ip.getPozoriste().getBrojSjedista()-Karta.vratiBrojKarata(ip)-br <0)
			PomocniKontroler.upozorenjeAlert("Nazalost, nemamo toliko karata na raspolaganju.", "");
		else
		{
			Karta k = new Karta(ip.getId(), Status.REZERVISANA_P, pp.getId(), br);
			Karta.ubaciMe(k);
		
			lvKorisnici.getItems().clear();
			lvKorisnici.getItems().addAll(Karta.vratiKorisnikeZaRez(ip));
			statusSjedista.setText(Karta.vratiBrojKarata(ip)+ " / " + ip.getPozoriste().getBrojSjedista());
			brojSlobodnih.setText(ip.getPozoriste().getBrojSjedista()-Karta.vratiBrojKarata(ip) + "");
		}
		
	}
	public void initData(IzvodjenjePredstave ip) {
		this.ip = ip;
		
		System.out.println(ip.toRepertoarString() + "ip iz info ucitaj");
		
		
		lvKorisnici.getItems().addAll(Karta.vratiKorisnikeZaRez(ip));
		
		statusSjedista.setText(Karta.vratiBrojKarata(ip)+ " / " + ip.getPozoriste().getBrojSjedista());
		brojSlobodnih.setText(ip.getPozoriste().getBrojSjedista()-Karta.vratiBrojKarata(ip) + "");
		
		ipString.setText(ip.toRepertoarString());
			
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

		SpinnerValueFactory<Integer> valueFactory = 

				new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);

		valueFactory.setValue(1);
		brKarataKupac.setValueFactory(valueFactory);
		brKarataKorisnici.setValueFactory(valueFactory);
		
		nazivPozorista.setText(RadnikPozorista.trenutni.getPozoristeID().getNaziv());
		
		
	}
}
