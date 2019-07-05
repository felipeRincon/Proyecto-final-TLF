package interfaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import logica.AnalizadorLexico;
import logica.Token;

public class Controller {

	@FXML
	private MenuItem btnAnalizar;

	@FXML
	private TextArea txtArea;

	@FXML
	private MenuItem menuNoNaturales;

	@FXML
	private MenuItem menuNoReales;

	@FXML
	private MenuItem menuIdentificadores;

	@FXML
	private MenuItem menuOpAritmeticos;

	@FXML
	private MenuItem menuOpRelacionales;

	@FXML
	private MenuItem menuOpLogicos;

	@FXML
	private MenuItem menuOpAsignacion;

	@FXML
	private MenuItem menuOpIncremento;

	@FXML
	private MenuItem menuParentesis;

	@FXML
	private MenuItem menuLlaves;

	@FXML
	private MenuItem menuTerminal;

	@FXML
	private MenuItem menuSeparador;

	@FXML
	private MenuItem menuHexa;

	@FXML
	private MenuItem menuCadena;

	@FXML
	private MenuItem menuComentarios;

	@FXML
	void onButtonAnalizar(ActionEvent event) {

		String codigoFuente = "" + txtArea.getText();

		AnalizadorLexico al = new AnalizadorLexico(codigoFuente);
		al.analizar();

		for (Token a : al.getListaTokens()) {

			System.out.println(a.toString());

			System.out.println(" -------------");
		}

	}

	@FXML
	void onButtonCadena(ActionEvent event) {

	}

	@FXML
	void onButtonComentarios(ActionEvent event) {

	}

	@FXML
	void onButtonHexa(ActionEvent event) {

	}

	@FXML
	void onButtonIdentificadores(ActionEvent event) {

	}

	@FXML
	void onButtonLlaves(ActionEvent event) {

	}

	@FXML
	void onButtonNoNaturales(ActionEvent event) {

	}

	@FXML
	void onButtonNoReales(ActionEvent event) {

	}

	@FXML
	void onButtonOpAisgnacion(ActionEvent event) {

	}

	@FXML
	void onButtonOpAritmeticos(ActionEvent event) {

	}

	@FXML
	void onButtonOpIncremento(ActionEvent event) {

	}

	@FXML
	void onButtonOpLogicos(ActionEvent event) {

	}

	@FXML
	void onButtonOpRelacionales(ActionEvent event) {

	}

	@FXML
	void onButtonParentesis(ActionEvent event) {

	}

	@FXML
	void onButtonSeparador(ActionEvent event) {

	}

	@FXML
	void onButtonTerminal(ActionEvent event) {

	}

}
