package kontroleri.radnik;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import kontroleri.PomocniKontroler;
import modeli.Pozoriste;
import modeli.RadnikPozorista;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ComboBox;

public class RadnikKreirajController implements Initializable{
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
	private Label nazivPozorista111;
	@FXML
	private TextField tbNaziv;
	@FXML
	private TextField tbGrad;
	@FXML
	private TextField tbSjedista;
	@FXML
	private Label nazivPozorista12;
	@FXML
	private Label nazivPozorista121;
	@FXML
	private Label nazivPozorista122;
	@FXML
	private Label nazivPozorista1221;
	@FXML
	private Label nazivPozorista12211;
	@FXML
	private ComboBox<String> cbPozoriste;
	@FXML
	private TextField tbIme;
	@FXML
	private TextField tbPrezime;
	@FXML
	private TextField tbKorisnicko;
	@FXML
	private PasswordField pfLozinka;
	@FXML
	private Button btnDodajPozoriste;
	@FXML
	private Button btnDodajRadnika;

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
		
	// Event Listener on Button[#btnDodajPozoriste].onAction
	@FXML
	public void dodajPozoriste(ActionEvent event) {
		String naziv = tbNaziv.getText();
		String grad = tbGrad.getText();
		int sjedista = Integer.parseInt(tbSjedista.getText());
		
		if(Integer.parseInt(tbSjedista.getText())<1)
		{
			PomocniKontroler.errorAlert("Ne možete dodati negativan broj sjedišta!", null);
			tbSjedista.clear();
		}
		
		else if(Pozoriste.vecPostoji(naziv, grad))
		{
			PomocniKontroler.errorAlert("Ojoj! Već postoji ovo pozorište!", null);
			tbNaziv.clear();
			tbGrad.clear();
			tbSjedista.clear();
		}	
		else
			{Pozoriste.dodajPozoriste(naziv, grad, sjedista);
		cbPozoriste.setItems(null);
		cbPozoriste.setItems(Pozoriste.vratiPozoristaRadniku());
		tbNaziv.clear();
		tbGrad.clear();
		tbSjedista.clear();
		PomocniKontroler.juhuAlert("Uspješno ste dodali novo Pozorištance!");
			}
	}
	// Event Listener on Button[#btnDodajRadnika].onAction
	@FXML
	public void dodajRadnika(ActionEvent event) {
		String ime = tbIme.getText();
		String prezime = tbPrezime.getText();
		String korisnicko = tbKorisnicko.getText();
		String lozinka = pfLozinka.getText();
		
		int id = Pozoriste.strVratiID(cbPozoriste.getSelectionModel().getSelectedItem());
		
		if(ime.isEmpty() || prezime.isEmpty() || korisnicko.isEmpty() || lozinka.isEmpty())
			PomocniKontroler.upozorenjeAlert("unesite sve podatke","");
		else
		{
			if(PomocniKontroler.validna(lozinka)==false)
			{
				PomocniKontroler.upozorenjeAlert("unesite validnu lozinku","");
				pfLozinka.clear();
			}
			else
			{
				RadnikPozorista.dodajRadnika(ime, prezime, korisnicko, PomocniKontroler.getMd5(lozinka), id);
				
				tbIme.clear();
				tbPrezime.clear();
				tbKorisnicko.clear();
				pfLozinka.clear();
				
				
				cbPozoriste.setItems(null);
				cbPozoriste.setItems(Pozoriste.vratiPozoristaRadniku());
				PomocniKontroler.juhuAlert("Uspješno ste dodali novog radnika!");
			}
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbPozoriste.setItems(Pozoriste.vratiPozoristaRadniku());
		nazivPozorista.setText(RadnikPozorista.trenutni.getPozoristeID().getNaziv());
		
		
	}
}
