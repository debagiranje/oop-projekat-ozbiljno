package application;
	
import java.util.ArrayList;

import baza.*;
import baza.pomocnici.OsobljeDAO;
import modeli.Osoblje;
import modeli.Tip;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/pogled/PosjetilacPogled.fxml"));
			//FXMLLoader loader = new FXMLLoader(Main.class.getResource("pogled/LoginPogled.fxml"));
			//loader.setRoot(new AnchorPane());			
			Parent root =  loader.load();
			Scene scene = new Scene(root,900,600);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		OsobljeDAO vezica = new OsobljeDAO();
		
	}
}
