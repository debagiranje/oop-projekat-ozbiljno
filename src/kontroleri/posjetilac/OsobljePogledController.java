package kontroleri.posjetilac;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kontroleri.PomocniKontroler;
import modeli.OsobljePredstave;

public class OsobljePogledController implements Initializable {
	@FXML
	private Button btnKarte;
	@FXML
	private Button btnPozorista;
	@FXML
	private Button btnOsoblje;
	@FXML
	private Button btnIzloguj;
	@FXML
	private ListView<String> lvOsoblje;
	@FXML
	private Button btnGlumac;
	@FXML
	private Button btnReziser;
	@FXML
	private Button btnAutor;
	@FXML
	private Button btnSvoOsoblje;
	

	private Parent root;
	private Stage stage;
	private Scene scene;

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
	
	// Event Listener on Button[#btnGlumac].onAction
	@FXML
	public void glumac(ActionEvent event) {
		lvOsoblje.getItems().clear();
		OsobljePredstave.brojPojavljivanjaOsoblja(1);
		lvOsoblje.getItems().addAll(OsobljePredstave.glumci.entrySet().stream()
			    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			    .map(Map.Entry::getKey)
			    .collect(Collectors.toList()));
	}
	// Event Listener on Button[#btnReziser].onAction
	@FXML
	public void reziser(ActionEvent event) {
		lvOsoblje.getItems().clear();
		OsobljePredstave.brojPojavljivanjaOsoblja(2);
		lvOsoblje.getItems().addAll(OsobljePredstave.reziseri.entrySet().stream()
			    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			    .map(Map.Entry::getKey)
			    .collect(Collectors.toList()));
	}
	// Event Listener on Button[#btnAutor].onAction
	@FXML
	public void autor(ActionEvent event) {
		lvOsoblje.getItems().clear();
		OsobljePredstave.brojPojavljivanjaOsoblja(3);
		lvOsoblje.getItems().setAll(OsobljePredstave.autori.entrySet().stream()
			    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			    .map(Map.Entry::getKey)
			    .collect(Collectors.toList()));
	}
	// Event Listener on Button[#btnSvoOsoblje].onAction
	@FXML
	public void svoOsoblje(ActionEvent event) {
		sve();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sve();
		
	}
	
	
	
	
	
	
	
	
	public void sve()
	{
		lvOsoblje.getItems().clear();
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
		
		lvOsoblje.setOnMouseClicked(new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent event) {
	        	FXMLLoader PosjetilacLoader = new FXMLLoader();
				PosjetilacLoader.setLocation(getClass().getResource("/pogled/posjetilac/InfoOsoblje.fxml"));
				try {
					root =  PosjetilacLoader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				OsobljePredstave.izStringa(lvOsoblje.getSelectionModel().getSelectedItem());
				InfoOsobljeController ppc = PosjetilacLoader.getController();
				ppc.initOsoblje();
				
				stage = (Stage)((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
	        }
	    });
	}
}
