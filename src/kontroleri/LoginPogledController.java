package kontroleri;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kontroleri.posjetilac.PosjetilacPogledController;
import kontroleri.radnik.RadnikPogledController;
import modeli.PosjetilacPozorista;
import modeli.RadnikPozorista;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.PasswordField;

public class LoginPogledController implements Initializable{
	@FXML
	private TextField tfKorisnickoIme;
	@FXML
	private Button btnUloguj;
	@FXML
	private Button btnRegistruj;
	@FXML
	private PasswordField pfLozinka;
	
	private Parent root;
	private Stage stage;
	private Scene scene;


	// Event Listener on Button[#btnUloguj].onAction
	@FXML
	public void uloguj(ActionEvent event) throws IOException {
		String korisnickoIme = tfKorisnickoIme.getText();
		String passw = pfLozinka.getText();
		
		System.out.println(korisnickoIme);
		
		if(korisnickoIme.isEmpty() || passw.isEmpty())
			PomocniKontroler.upozorenjeAlert("Unesite sve podatke!", "");
		else
		{
		
		passw = PomocniKontroler.getMd5(passw);
		System.out.println(passw);
		
		PosjetilacPozorista pp = PosjetilacPozorista.pronadjiPosjetioca(korisnickoIme, passw);
		RadnikPozorista rp = RadnikPozorista.pronadjiRadnika(korisnickoIme, passw);
		
		
		if(rp == null)
		{	
			if(pp != null)
			{
				FXMLLoader PosjetilacLoader = new FXMLLoader();
				PosjetilacLoader.setLocation(getClass().getResource("/pogled/posjetilac/PosjetilacPogled.fxml"));
				root =  PosjetilacLoader.load();
				
				PosjetilacPozorista.trenutni = pp;
				PosjetilacPogledController ppc = PosjetilacLoader.getController();
				ppc.initPosjetilac();
				
				stage = (Stage)((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			else
			{
				tfKorisnickoIme.clear();
				pfLozinka.clear();
				System.out.println("nema tog korisnika -- dodaj alert");
				PomocniKontroler.errorAlert("Nepostojeći korisnik!", "Provjerite korisničko ime i šifru.");
			}
		}
		if(rp!=null)
		{
			FXMLLoader RadnikLoader = new FXMLLoader(getClass().getResource("/pogled/radnik/RadnikPogled.fxml"));		
			root = RadnikLoader.load();
			
			System.out.println(rp.toString());
			RadnikPozorista.trenutni = rp;
			RadnikPogledController rpc = RadnikLoader.getController();
			rpc.initRadnik();
			
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		}
		}
		
		}
	// Event Listener on Button[#btnRegistruj].onAction
	@FXML
	public void registruj(ActionEvent event) throws IOException {
		FXMLLoader RegistrujLoader = new FXMLLoader(getClass().getResource("/pogled/RegistracijaPogled.fxml"));		
		root = RegistrujLoader.load();
		
		//PosjetilacPogledController ppc = PosjetilacLoader.getController();
		
		
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
