package rmihelper;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Saltwater
 * @version 1.0
 * The Quick Start Window For Server
 */
public class QuickStart extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.initStyle(StageStyle.UNDECORATED);
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(QuickStart.class.getResource("Visualization.fxml"));
		AnchorPane pane = null;
		try {
			pane = (AnchorPane)loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Scene scene = new Scene(pane);
		scene.setFill(Color.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
