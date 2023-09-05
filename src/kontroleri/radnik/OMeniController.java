package kontroleri.radnik;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;
import kontroleri.PomocniKontroler;
import modeli.RadnikPozorista;

public class OMeniController implements Initializable{
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
	private Label nazivPozorista1;
	@FXML
	private Label nazivPozorista11;
	@FXML
	private Label nazivPozorista12;
	@FXML
	private Label tfIme;
	@FXML
	private Label tfPrezime;
	@FXML
	private Label tfKorisnicko;
	@FXML
	private Label nazivPozorista13;
	@FXML
	private Label tfNazivPozorista;
	@FXML
	private Label nazivPozorista131;
	@FXML
	private Label tfGrad;
	@FXML
	private Label nazivPozorista2;
	@FXML
	private Label nazivPozorista1113;
	@FXML
	private Label nazivPozorista112;
	@FXML
	private Label nazivPozorista1121;
	@FXML
	private Label nazivPozorista11211;
	@FXML
	private PasswordField pfStara;
	@FXML
	private PasswordField pfNova;
	@FXML
	private PasswordField pfPotvrdiNovu;
	@FXML
	private Button btnPotvrdi;

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
		
	// Event Listener on Button[#btnPotvrdi].onAction
	@FXML
	public void potvrdi(ActionEvent event) {
		
		if(pfStara.getText().isEmpty() || pfNova.getText().isEmpty() || pfPotvrdiNovu.getText().isEmpty())
		{
			PomocniKontroler.upozorenjeAlert("Unesite sve sifre!", null);
		}
		else if(!pfStara.getText().equals(RadnikPozorista.trenutni.getLozinka()))
		{
			PomocniKontroler.upozorenjeAlert("Stara lozinka nije u redu!", null);
			pfStara.clear();
		}
		else if(!pfNova.getText().equals(pfPotvrdiNovu.getText()))
		{
			PomocniKontroler.upozorenjeAlert("Nove lozinke se ne poklapaju!", null);
			pfNova.clear();
			pfPotvrdiNovu.clear();
		}
		else if(pfNova.getText().equals(pfStara.getText()))
		{
			PomocniKontroler.upozorenjeAlert("Nova lozinka ne smije biti ista kao stara...", null);
			pfNova.clear();
			pfPotvrdiNovu.clear();
		}
		else if(!PomocniKontroler.validna(pfNova.getText()))
		{
			PomocniKontroler.upozorenjeAlert("Lozinka mora imati barem 8 karaktera, veliko i malo slovo, simbol...", null);
			pfNova.clear();
			pfPotvrdiNovu.clear();
		}
		else
		{
			RadnikPozorista.azurirajLozinku(pfNova.getText());
			PomocniKontroler.juhuAlert("Promijenili ste lozinku :)");
			pfStara.clear();
			pfNova.clear();
			pfPotvrdiNovu.clear();
			
		}
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		nazivPozorista.setText(RadnikPozorista.trenutni.getPozoristeID().getNaziv());
		
		tfIme.setText(RadnikPozorista.trenutni.getIme());
		tfPrezime.setText(RadnikPozorista.trenutni.getPrezime());
		tfKorisnicko.setText(RadnikPozorista.trenutni.getKorisnickoIme());
		tfNazivPozorista.setText(RadnikPozorista.trenutni.getPozoristeID().getNaziv());
		tfGrad.setText(RadnikPozorista.trenutni.getPozoristeID().getGrad());
		
		
	}
}
