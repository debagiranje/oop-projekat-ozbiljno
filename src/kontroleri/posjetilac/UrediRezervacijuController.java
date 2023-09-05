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

public class UrediRezervacijuController implements Initializable{
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
	@FXML
	private Label lblBrKarata;
	@FXML
	private Label lblDatum111;

	private Karta k;

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
			if(spnBrojKarata.getValue()>k.getBrojKarta())
				PomocniKontroler.errorAlert("Unesite ispravan broj karata za otkazati", null);
			else if(spnBrojKarata.getValue()==k.getBrojKarta())
			{
				Karta.promijeniBrojKarata2(k, spnBrojKarata.getValue());
				lblBrKarata.setText(k.getBrojKarta() + "");
				PomocniKontroler.juhuAlert("Uspjesno obrisana rezervacija");
			}
			else
			{
				Karta.promijeniBrojKarata2(k, spnBrojKarata.getValue());
				lblBrKarata.setText(k.getBrojKarta() + "");
				PomocniKontroler.juhuAlert("Uspjesno izmijenjena rezervacija");
			}
			
		}
		
		public void initIzvodjenje(Karta k) {
			this.k = k;
			
			System.out.println(k.KartaInfo());
			
			lblNaziv.setText(k.getIp().getPredstava().getNaziv());
			lblCijena.setText(k.getIp().getCijena() + "");
			lblDatum.setText(k.getIp().getDatum().toString());
			lblVrijeme.setText(k.getIp().getVrijeme().toString());
			lblBrKarata.setText(k.getBrojKarta() + "");
		}
		
		
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			SpinnerValueFactory<Integer> valueFactory = 

					new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);

			valueFactory.setValue(1);
			spnBrojKarata.setValueFactory(valueFactory);
			
	

		}
}
