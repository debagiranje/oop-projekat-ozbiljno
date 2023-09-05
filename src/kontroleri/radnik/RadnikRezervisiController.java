package kontroleri.radnik;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import kontroleri.PomocniKontroler;
import modeli.Karta;
import modeli.PosjetilacPozorista;

public class RadnikRezervisiController implements Initializable{
	@FXML
	private Button btnPredstave;
	@FXML
	private Button btnIzvodjenja;
	@FXML
	private Button btnOsoblje;
	@FXML
	private Button btnIzloguj;
	@FXML
	private Label nazivPozorista;
	@FXML
	private Button btnRezervacije;
	@FXML
	private Button btnKreiraj;
	@FXML
	private Button btnOmeni;
	@FXML
	private Button btnOtkazi;
	@FXML
	private Spinner<Integer> spnOtkazi;
	@FXML
	private Button btnPredaj;
	@FXML
	private ListView<String> lvKorisnici;
	@FXML
	private ListView<String> lvRezervacije;
	@FXML
	private Label nazivPozorista1;
	
	private String[] korisnicko;

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
		
	// Event Listener on Button[#btnOtkazi].onAction
	@FXML
	public void otkazi(ActionEvent event) {
		String izabrana = lvRezervacije.getSelectionModel().getSelectedItem();
		Karta.promijeniBrojKarata(izabrana, spnOtkazi.getValue());
		
		lvKorisnici.getItems().clear();
		lvKorisnici.getItems().addAll(Karta.vratiPosjetioceRezervacija());
		
		//lvRezervacije.getItems().clear();
		
	}
	// Event Listener on Button[#btnPredaj].onAction
	@FXML
	public void predaj(ActionEvent event) {
		String izabrana = lvRezervacije.getSelectionModel().getSelectedItem();
		Karta.promijeniStatusPreuzeta(izabrana);
		
		lvKorisnici.getItems().clear();
		lvKorisnici.getItems().addAll(Karta.vratiPosjetioceRezervacija());
		
		lvRezervacije.getItems().clear();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lvKorisnici.getItems().addAll(Karta.vratiPosjetioceRezervacija());
		
		
		lvKorisnici.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				if(lvKorisnici.selectionModelProperty().get().getSelectedItem()!=null)
				{
					korisnicko = lvKorisnici.selectionModelProperty().get().getSelectedItem().split(" - ", 2);	
				}
				lvRezervacije.getItems().setAll(Karta.vratiNepreuzeteRezervacijePosjetioca(PosjetilacPozorista.vratiPoKorisnickom(korisnicko[1])));
			}	

			});
		
		SpinnerValueFactory<Integer> valueFactory = 

				new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);

		valueFactory.setValue(1);
		spnOtkazi.setValueFactory(valueFactory);
	}
}
