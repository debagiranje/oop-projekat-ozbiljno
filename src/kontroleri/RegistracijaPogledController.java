package kontroleri;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import modeli.PosjetilacPozorista;

import java.io.IOException;

import baza.pomocnici.PosjetilacPozoristaDAO;
import javafx.event.ActionEvent;

import javafx.scene.control.PasswordField;

public class RegistracijaPogledController {
	@FXML
	private TextField tfKorisnickoIme;
	@FXML
	private Button btnRegistruj;
	@FXML
	private PasswordField pfLozinka;
	@FXML
	private TextField tfPrezime;
	@FXML
	private TextField tfIme;
	
	private Parent root;
	private Stage stage;
	private Scene scene;

	
	private PosjetilacPozoristaDAO dao = new PosjetilacPozoristaDAO();
	private PosjetilacPozorista pp;

	// Event Listener on Button[#btnRegistruj].onAction
	@FXML
	public void registruj(ActionEvent event) throws IOException {
		String username = tfKorisnickoIme.getText().strip();
		String passw = pfLozinka.getText().strip();
		String ime = tfIme.getText().strip();
		String prezime = tfPrezime.getText().strip();
		
		if(username.isEmpty() || passw.isEmpty() || ime.isEmpty() || prezime.isEmpty())
			PomocniKontroler.upozorenjeAlert("Unesite sve podatke!", "");
		else
		{
		if(PomocniKontroler.validna(passw) == false)
		{
			PomocniKontroler.upozorenjeAlert("unesite validnu lozinku", "");
			pfLozinka.clear();
		}
		else {
		
		pp = new PosjetilacPozorista(ime, prezime, username, PomocniKontroler.getMd5(passw));
		if(pp.unikanoIme(username) == true)
		{
			dao.dodaj(pp);
			
			System.out.println(pp.toString());
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Jupi!");
			alert.setHeaderText("Uspjesno ste kreirali profil!");
			alert.setContentText("");

			alert.showAndWait().ifPresent(response -> {
			    if (response == ButtonType.OK) {
			    	FXMLLoader LoginLoader = new FXMLLoader();
					LoginLoader.setLocation(getClass().getResource("/pogled/LoginPogled.fxml"));
					try {
						root =  LoginLoader.load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
					
					stage = (Stage)((Node) event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
			    }
			});
			
		}
		else
		{
			tfKorisnickoIme.clear();
			//pfLozinka.clear();
			//tfIme.clear();
			//tfPrezime.clear();
			
			System.out.println("molimo Vas unesite unikatno ime -- dodaj popup");
			PomocniKontroler.upozorenjeAlert("Unesite unikatno ime!", "");
			
			
		}
		}
		}
		
	}
}
