package app;

import interfaz.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logica.AnalizadorLexico;


public class App extends Application{
	
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/interfaz/Inicio.fxml"));
        primaryStage.setTitle("Anaizador Lexico");
        primaryStage.setScene(new Scene(root, 800, 450));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

	

}
