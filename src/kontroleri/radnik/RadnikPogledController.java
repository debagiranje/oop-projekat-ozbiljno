package kontroleri.radnik;

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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.stage.Stage;
import kontroleri.PomocniKontroler;
import kontroleri.posjetilac.InfoPredstavaController;
import modeli.IzvodjenjePredstave;
import modeli.Karta;
import modeli.Pozoriste;
import modeli.RadnikPozorista;


public class RadnikPogledController implements Initializable{
	@FXML
	private Button btnIzloguj;
	@FXML
	private Label nazivPozorista;
	@FXML
	private Button btnOsoblje11;
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
	private Label imeRadnika;
	@FXML
	private ListView<String> lvPredstojece;
	@FXML
	private ListView<String> lvPrethodne;
	@FXML
	private Button btnInfo;
	@FXML
	private ListView<String> lvRezervacije;
	@FXML
	private Button btnProdaj;

	private RadnikPozorista rp;
	
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	protected String strIzvodjenje;

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
	
	
	// Event Listener on Button[#btnProdaj].onAction
	@FXML
	public void prodaj(ActionEvent event) throws IOException {
		FXMLLoader ProdajLoader = new FXMLLoader(getClass().getResource("/pogled/radnik/Prodaj.fxml"));		
		root =  ProdajLoader.load();
		
		//System.out.println("data init stuff aaaaaa" + IzvodjenjePredstave.vratiIzStringa(lvPredstave.getSelectionModel().getSelectedItem()) + " trenutni iz info" );
		
		ProdajController pc = ProdajLoader.getController();
		
		//System.out.println(lvPrethodne.getSelectionModel().getSelectedItem().toString()+ "ovo je za radnika info info blabla");
		
		if(lvPredstojece.getSelectionModel().getSelectedItem()!=null)
		{
			IzvodjenjePredstave.vratiIzStringaPredstojece(lvPredstojece.getSelectionModel().getSelectedItem());
			pc.initData(IzvodjenjePredstave.trenutno);
			
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		}
		else
		{
			PomocniKontroler.errorAlert("Molimo Vas, izaberite izvodjenje", null);
		}
	}
	
	// Event Listener on Button[#btnDodajPredstavu].onAction
	@FXML
	public void dodajPredstavu(ActionEvent event) {
		// TODO Autogenerated
	}
	
	public void initRadnik() {
		rp = RadnikPozorista.trenutni;
		//System.out.println(rp.toString());
		imeRadnika.setText(rp.getIme() + " " + rp.getPrezime());
		nazivPozorista.setText(rp.getPozoristeID().getNaziv());
		lvPredstojece.getItems().setAll(IzvodjenjePredstave.vratiTekucaIzvodjenja(rp.getPozoristeID().getId()));
		lvPrethodne.getItems().setAll(IzvodjenjePredstave.vratiProteklaIzvodjenja(rp.getPozoristeID().getId()));
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		lvPredstojece.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {


				IzvodjenjePredstave.vratiIzStringaPredstojece(lvPredstojece.getSelectionModel().getSelectedItem());

				lvRezervacije.getItems().setAll(Karta.vratiRezervacijeIzvodjenja(IzvodjenjePredstave.trenutno));
			}	

			});
		
		lvPrethodne.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {


				IzvodjenjePredstave.vratiIzStringaPrethodne(lvPrethodne.getSelectionModel().getSelectedItem());
				System.out.println(IzvodjenjePredstave.trenutno.toRadnikRepertoar() + " ja sam sad izabran");
				lvRezervacije.getItems().setAll(Karta.vratiRezervacijeIzvodjenja(IzvodjenjePredstave.trenutno));
			}	

			});
		
		
	}
	// Event Listener on Button[#btnInfo].onAction
	@FXML
	public void informacije(ActionEvent event) throws IOException {
		FXMLLoader InfoLoader = new FXMLLoader(getClass().getResource("/pogled/radnik/RadnikInfoPredstava.fxml"));		
		root =  InfoLoader.load();
		
		//System.out.println("data init stuff aaaaaa" + IzvodjenjePredstave.vratiIzStringa(lvPredstave.getSelectionModel().getSelectedItem()) + " trenutni iz info" );
		
		RadnikInfoPredstavaController ipc = InfoLoader.getController();
		
		//System.out.println(lvPrethodne.getSelectionModel().getSelectedItem().toString()+ "ovo je za radnika info info blabla");
		
		if(lvPredstojece.getSelectionModel().getSelectedItem()!=null)
		{
			IzvodjenjePredstave.vratiIzStringaPredstojece(lvPredstojece.getSelectionModel().getSelectedItem());
			ipc.initData(IzvodjenjePredstave.trenutno);
			
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		}
		else if(lvPrethodne.getSelectionModel().getSelectedItem()!=null)
		{
			IzvodjenjePredstave.vratiIzStringaPrethodne(lvPrethodne.getSelectionModel().getSelectedItem());
			ipc.initData(IzvodjenjePredstave.trenutno);
			
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		}
		else
		{
			PomocniKontroler.errorAlert("Molimo Vas, izaberite izvodjenje", null);
		}
		
	}
	
	

}
