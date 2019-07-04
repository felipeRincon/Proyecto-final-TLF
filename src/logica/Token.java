package logica;

public class Token {

	//Categoria correspondiente al token
	private Categoria categoria;
	
	
	//Cadena de carcteres que forman el token
	private String palabra;
	
	//Posicion donde se encuentra el tokens
	private int fila, columna;

	public Token(Categoria categoria, String palabra, int fila, int columna) {
		super();
		this.categoria = categoria;
		this.palabra = palabra;
		this.fila = fila;
		this.columna = columna;
	}

	@Override
	public String toString() {
		return "Token [categoria: " + categoria + ", palabra: " + palabra + ", fila: " + fila + ", columna: " + columna
				+ "]";
	}

}
