package kontroleri.posjetilac;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.beans.value.ChangeListener;

import javafx.beans.value.ObservableValue;

import javafx.scene.control.ListView;
import javafx.stage.Stage;
import kontroleri.PomocniKontroler;
import modeli.IzvodjenjePredstave;
import modeli.Pozoriste;

public class PozoristaPogledController implements Initializable{
	@FXML
	private Button btnKarte;
	@FXML
	private Button btnPozorista;
	@FXML
	private Button btnOsoblje;
	@FXML
	private Button btnIzloguj;
	@FXML
	private ListView<String> lvPozorista;
	@FXML
	private ListView<String> lvPredstave;
	@FXML
	private Button btnRezervisi;
	@FXML
	private Button btnInformacije;
	
	private String strPozoriste;
	
	
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
	// Event Listener on Button[#btnRezervisi].onAction
	@FXML
	public void rezervisi(ActionEvent event) throws IOException {
		FXMLLoader RLoader = new FXMLLoader();
		RLoader.setLocation(getClass().getResource("/pogled/posjetilac/Rezervisi.fxml"));
		root =  RLoader.load();
		
		RezervisiController rc = RLoader.getController();
		//System.out.println(lvPredstave.getSelectionModel().getSelectedItem());
		IzvodjenjePredstave.vratiIzStringa(lvPredstave.getSelectionModel().getSelectedItem());
		rc.initIzvodjenje(IzvodjenjePredstave.trenutno);
		
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	// Event Listener on Button[#btnInformacije].onAction
	@FXML
	public void informacije(ActionEvent event) throws IOException {
		FXMLLoader InfoLoader = new FXMLLoader(getClass().getResource("/pogled/posjetilac/InfoPredstava.fxml"));		
		root =  InfoLoader.load();
		
		//System.out.println("data init stuff aaaaaa" + IzvodjenjePredstave.vratiIzStringa(lvPredstave.getSelectionModel().getSelectedItem()) + " trenutni iz info" );
		
		InfoPredstavaController ipc = InfoLoader.getController();
		IzvodjenjePredstave.vratiIzStringa(lvPredstave.getSelectionModel().getSelectedItem());
		ipc.initData(IzvodjenjePredstave.trenutno);
		
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lvPozorista.getItems().setAll(Pozoriste.vratiPozorista());
		
		lvPozorista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {


				strPozoriste = lvPozorista.getSelectionModel().getSelectedItem();
				
				Pozoriste.IDtrenutnog = Pozoriste.strVratiID(strPozoriste);

				lvPredstave.getItems().setAll(IzvodjenjePredstave.vratiIzvodjenja(Pozoriste.IDtrenutnog));
			}	

			});
		
	}
}
