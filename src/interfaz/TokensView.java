package interfaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TokensView {

	private final StringProperty palabra;
	private final StringProperty categoria;
	private final StringProperty fila;
	private final StringProperty columna;
	private final StringProperty automata;

	public TokensView(String strPalabra, String strCategoria, String strFila, String strColumna, String strAutomata) {
		this.palabra = new SimpleStringProperty(strPalabra);
		this.categoria = new SimpleStringProperty(strCategoria);
		this.fila = new SimpleStringProperty(strFila);
		this.columna = new SimpleStringProperty(strColumna);
		this.automata = new SimpleStringProperty(strAutomata);
	}

	public String getPalabra() {
		return palabra.get();
	}

	public String getCategoria() {
		return categoria.get();
	}

	public String getColumna() {
		return columna.get();
	}

	public String getFila() {
		return fila.get();
	}

	public String getAutomata() {
		return automata.get();
	}

	public void setPalabra(String strPalabra) {
		palabra.set(strPalabra);
	}

	public void setCategoria(String strCategoria) {
		categoria.set(strCategoria);
	}

	public void setColumna(String strColumna) {
		columna.set(strColumna);
	}

	public void setFila(String strFila) {
		fila.set(strFila);
	}

	public void setAutomata(String strAutomata) {
		fila.set(strAutomata);
	}
}
