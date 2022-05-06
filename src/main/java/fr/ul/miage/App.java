package fr.ul.miage;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Simulation baignoire");
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("baignoire.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		   
		} catch (IOException e) {
			e.printStackTrace();
		}
       
	}

	public static void main(String[] args) {
		launch(args);

	}
}
