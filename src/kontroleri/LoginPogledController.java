package kontroleri;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

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

	// Event Listener on Button[#btnUloguj].onAction
	@FXML
	public void uloguj(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnRegistruj].onAction
	@FXML
	public void registruj(ActionEvent event) {
		// TODO Autogenerated
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
