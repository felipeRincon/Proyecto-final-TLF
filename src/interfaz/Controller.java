package interfaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import logica.AnalizadorLexico;
import logica.Token;

public class Controller  {


    @FXML
    private TableView<TokensView> tableTokens;

    @FXML
    private TableColumn<TokensView, String> colPalabra;

    @FXML
    private TableColumn<TokensView, String> colCategoria;

    @FXML
    private TableColumn<TokensView, String> colFila;

    @FXML
    private TableColumn<TokensView, String> colColumna;

    @FXML
    private TableColumn<TokensView, String> colAutomata;
    
    ObservableList<TokensView> tokens;

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
    void initialize() {
    

    	tokens = FXCollections.observableArrayList();
    	colPalabra.setCellValueFactory(
    			 new PropertyValueFactory<TokensView,String>("palabra")
    			);
    			colCategoria.setCellValueFactory(
    			 new PropertyValueFactory<TokensView,String>("categoria")
    			);
    			colFila.setCellValueFactory(
    			 new PropertyValueFactory<TokensView,String>("fila")
    			);
    			colColumna.setCellValueFactory(
    			 new PropertyValueFactory<TokensView,String>("columna")
    			);
    			colAutomata.setCellValueFactory(
    			 new PropertyValueFactory<TokensView,String>("automata")
    			);
    	
    }
	
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




}
