package kontroleri;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import kontroleri.posjetilac.InfoPredstavaController;
import kontroleri.posjetilac.PosjetilacPogledController;
import kontroleri.posjetilac.RezervisiController;
import kontroleri.radnik.RadnikPogledController;
import kontroleri.radnik.RadnikPozoristaController;
import modeli.IzvodjenjePredstave;
import modeli.PosjetilacPozorista;


public class PomocniKontroler {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	public void izlogujScena(ActionEvent event) throws IOException {
		FXMLLoader LoginLoader = new FXMLLoader(getClass().getResource("/pogled/LoginPogled.fxml"));		
		root =  LoginLoader.load();
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void predstavePogledScena(ActionEvent event) throws IOException {
		FXMLLoader PosjetilacPLoader = new FXMLLoader();
		PosjetilacPLoader.setLocation(getClass().getResource("/pogled/posjetilac/PozoristaPogled.fxml"));
		root =  PosjetilacPLoader.load();
		
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void kartePogledScena(ActionEvent event) throws IOException {
		FXMLLoader PosjetilacKLoader = new FXMLLoader();
		PosjetilacKLoader.setLocation(getClass().getResource("/pogled/posjetilac/PosjetilacPogled.fxml"));
		root =  PosjetilacKLoader.load();
		
		//System.out.println(PosjetilacPozorista.trenutni.toString());
		PosjetilacPogledController rc = PosjetilacKLoader.getController();
		rc.initPosjetilac();
		
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void informacijeScena(ActionEvent event) throws IOException {
		FXMLLoader InfoLoader = new FXMLLoader(getClass().getResource("/pogled/posjetilac/InfoPredstava.fxml"));		
		root =  InfoLoader.load();
		
		System.out.println(PosjetilacPozorista.trenutni.toString() + " trenutni iz info" );
		
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void rezervisiScena(ActionEvent event) throws IOException {
		FXMLLoader rezervisiLoader = new FXMLLoader(getClass().getResource("/pogled/posjetilac/Rezervisi.fxml"));		
		root =  rezervisiLoader.load();
		
		//System.out.println(IzvodjenjePredstave.trenutno.toString() + " trenutni iz rezervisi" );
		RezervisiController rc = rezervisiLoader.getController();
		rc.initIzvodjenje(null);
		
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void prebaciOsoblje(ActionEvent event) throws IOException {
		FXMLLoader OLoader = new FXMLLoader();
		OLoader.setLocation(getClass().getResource("/pogled/posjetilac/OsobljePogled.fxml"));
		root =  OLoader.load();
		
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	/*
	 * Radnik scene
	 */
	
	
	public void kreirajScena(ActionEvent event) throws IOException {
		FXMLLoader LoginLoader = new FXMLLoader(getClass().getResource("/pogled/radnik/RadnikKreiraj.fxml"));		
		root =  LoginLoader.load();
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// Event Listener on Button[#btnPredstave].onAction
		@FXML
		public void predstaveScena(ActionEvent event) throws IOException {
			FXMLLoader RadnikLoader = new FXMLLoader(getClass().getResource("/pogled/radnik/RadnikPozorista.fxml"));		
			root = RadnikLoader.load();
			
			RadnikPozoristaController rpc = RadnikLoader.getController();
			rpc.init();
			
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		// Event Listener on Button[#btnIzvodjenja].onAction
		@FXML
		public void izvodjenjaScena(ActionEvent event) throws IOException {
			FXMLLoader RadnikLoader = new FXMLLoader(getClass().getResource("/pogled/radnik/RadnikPogled.fxml"));		
			root = RadnikLoader.load();
			
			RadnikPogledController rpc = RadnikLoader.getController();
			rpc.initRadnik();
			
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
		public void oMeniScena(ActionEvent event) throws IOException {
			FXMLLoader jaLoader = new FXMLLoader(getClass().getResource("/pogled/radnik/OMeni.fxml"));		
			root = jaLoader.load();
			
			//RadnikPogledController rpc = RadnikLoader.getController();
			//rpc.initRadnik();
			
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
		public void rezervacijaScena(ActionEvent event) throws IOException {
			FXMLLoader RezLoader = new FXMLLoader(getClass().getResource("/pogled/radnik/RadnikRezervisi.fxml"));		
			root = RezLoader.load();
			
			//RadnikPogledController rpc = RadnikLoader.getController();
			//rpc.initRadnik();
			
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
		public void osobljeScena(ActionEvent event) throws IOException {
			FXMLLoader OLoader = new FXMLLoader();
			OLoader.setLocation(getClass().getResource("/pogled/radnik/RadnikOsoblje.fxml"));
			root =  OLoader.load();
			
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
		/*
		 * Alertovi raznorazni
		 */
		
		public static void errorAlert(String header, String content)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Ojoj!");
			alert.setHeaderText(header);
			alert.setContentText(content);

			alert.showAndWait();
		}
		
		public static void upozorenjeAlert(String header, String content)
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ojo-joj!");
			alert.setHeaderText(header);
			alert.setContentText(content);

			alert.showAndWait();
		}
		
		public static void juhuAlert(String content)
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Jupi!");
			alert.setHeaderText("Uspjeh!");
			alert.setContentText(content);

			alert.showAndWait();
		}
		
		
		/*Pomocne funkcije*/
		
		public static boolean validna(String lozinka)
	    {
	 
	        // Regex to check valid password - besramno pokupljeno sa GFG
			
	        String regex = "^(?=.*[0-9])"
	                       + "(?=.*[a-z])(?=.*[A-Z])"
	                       + "(?=.*[@#$%^&+=])"
	                       + "(?=\\S+$).{8,20}$";
	 

	        Pattern p = Pattern.compile(regex);
	 
	        if (lozinka == null) {
	            return false;
	        }
	 
	        Matcher m = p.matcher(lozinka);
	 
	        return m.matches();
	    }
}
