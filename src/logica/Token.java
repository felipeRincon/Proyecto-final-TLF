package logica;

public class Token {

	// Categoria correspondiente al token
	private Categoria categoria;

	// EXpresion regular del automata
	private String expresion;

	// Cadena de carcteres que forman el token
	private String palabra;

	// Posicion donde se encuentra el tokens
	private int fila, columna;

	public Token(Categoria categoria, String palabra, int fila, int columna, String expresion) {
		super();

		this.expresion = expresion;
		this.categoria = categoria;
		this.palabra = palabra;
		this.fila = fila;
		this.columna = columna;
	}

	@Override
	public String toString() {
		return "Token [categoria: " + categoria + ", palabra: " + palabra + ", fila: " + fila + ", columna: " + columna
				+  ", EXPRESION: "+ expresion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getExpresion() {
		return expresion;
	}

	public void setExpresion(String expresion) {
		this.expresion = expresion;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

}
