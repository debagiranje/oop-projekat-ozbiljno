package kontroleri.posjetilac;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import kontroleri.PomocniKontroler;
import modeli.IzvodjenjePredstave;
import modeli.Karta;
import modeli.PosjetilacPozorista;
import modeli.Status;

public class RezervisiController implements Initializable{
	@FXML
	private Button btnKarte;
	@FXML
	private Button btnPozorista;
	@FXML
	private Button btnOsoblje;
	@FXML
	private Button btnIzloguj;
	@FXML
	private Button btnPotvrdiIzmjenu;
	@FXML
	private Spinner<Integer> spnBrojKarata;
	@FXML
	private Label lblDatum;
	@FXML
	private Label lblVrijeme;
	@FXML
	private Label lblCijena;
	@FXML
	private Label lblNaziv;
	@FXML
	private Label lblDatum11;
	private IzvodjenjePredstave ip;

	// Event Listener on Button[#btnKarte].onAction
		@FXML
		public void prebaciKarte(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.kartePogledScena(event);
		}
		// Event Listener on Button[#btnPozorista].onAction
		@FXML
		public void prebaciPozorista(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.predstavePogledScena(event);
		}
		// Event Listener on Button[#btnOsoblje].onAction
		@FXML
		public void prebaciOsoblje(ActionEvent event) throws IOException {
			
			PomocniKontroler help = new PomocniKontroler();
			help.prebaciOsoblje(event);
		}
		// Event Listener on Button[#btnIzloguj].onAction
		@FXML
		public void izloguj(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.izlogujScena(event);
		}
		// Event Listener on Button[#btnPotvrdiIzmjenu].onAction
		@FXML
		public void potvrdiIzmjenu(ActionEvent event) {
			
			if(PosjetilacPozorista.trenutni!=null && ip.getPozoriste().brKarataValidan(spnBrojKarata.getValue())==true && ip.datumIstekao(ip)==false)
			{
				
				int br_sjedista = ip.getPozoriste().getBrojSjedista();
				if(ip.getPozoriste().getBrojSjedista()-Karta.vratiBrojKarata(ip)-spnBrojKarata.getValue() <0)
					PomocniKontroler.upozorenjeAlert("Nazalost, nemamo toliko karata na raspolaganju.", "");
				else {
					Karta k = new Karta(ip.getId(), Status.REZERVISANA_NP, PosjetilacPozorista.trenutni.getId(), spnBrojKarata.getValue());
					Karta.ubaciMe(k);
					ip.umanjiBrojNezauzetih(spnBrojKarata.getValue());
					PomocniKontroler.juhuAlert("jupi!  rezervisano");
				}
			}
			else
			{
				if(ip.datumIstekao(ip))
					PomocniKontroler.errorAlert("datum istekao, odaberite drugu predstavu", null);
			}
		}
		
		public void initIzvodjenje(IzvodjenjePredstave 	ip) {
			this.ip = ip;
			
			lblNaziv.setText(ip.getPredstava().getNaziv());
			lblCijena.setText(ip.getCijena() + "");
			lblDatum.setText(ip.getDatum().toString());
			lblVrijeme.setText(ip.getVrijeme().toString());
		}
		
		
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			SpinnerValueFactory<Integer> valueFactory = 

					new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);

			valueFactory.setValue(1);
			spnBrojKarata.setValueFactory(valueFactory);
			
	

		}
}
