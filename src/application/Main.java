package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//loader za login
			FXMLLoader LoginLoader = new FXMLLoader(getClass().getResource("/pogled/LoginPogled.fxml"));		
			
			Parent root =  LoginLoader.load();
			Scene scene = new Scene(root,900,600);
			
			primaryStage.setTitle("Pozoristance");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
				
	}
}
