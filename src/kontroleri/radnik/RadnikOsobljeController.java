package kontroleri.radnik;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.Comparator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import modeli.OsobljePredstave;

public class RadnikOsobljeController implements Initializable{
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
	private ListView<String> lvOsoblje;

	// Event Listener on Button[#btnPredstave].onAction
	@FXML
	public void predstaveScena(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnIzvodjenja].onAction
	@FXML
	public void izvodjenjaScena(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnOsoblje].onAction
	@FXML
	public void osobljeScena(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnIzloguj].onAction
	@FXML
	public void izloguj(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnRezervacije].onAction
	@FXML
	public void rezervacijeScena(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnKreiraj].onAction
	@FXML
	public void KreirajScena(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnOmeni].onAction
	@FXML
	public void oMeni(ActionEvent event) {
		// TODO Autogenerated
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		OsobljePredstave.brojPojavljivanjaOsoblja(3);
		lvOsoblje.getItems().setAll(OsobljePredstave.autori.entrySet().stream()
			    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			    .map(Map.Entry::getKey)
			    .collect(Collectors.toList()));
		
		OsobljePredstave.brojPojavljivanjaOsoblja(2);
		lvOsoblje.getItems().addAll(OsobljePredstave.reziseri.entrySet().stream()
			    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			    .map(Map.Entry::getKey)
			    .collect(Collectors.toList()));
		
		OsobljePredstave.brojPojavljivanjaOsoblja(1);
		lvOsoblje.getItems().addAll(OsobljePredstave.glumci.entrySet().stream()
			    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			    .map(Map.Entry::getKey)
			    .collect(Collectors.toList()));
		
	}
}
