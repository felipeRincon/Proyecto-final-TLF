package logica;

import java.util.ArrayList;

public class AnalizadorLexico {

	private String codigoFuente;
	private ArrayList<Token> listaTokens;
	private char caracterActual, finCodigo;
	private int posActual, colActual, filaActual;

	public AnalizadorLexico(String codigoFuente) {
		this.codigoFuente = codigoFuente;
		this.listaTokens = new ArrayList<Token>();
		this.caracterActual = codigoFuente.charAt(posActual);
		this.finCodigo = 0;
	}

	/**
	 * Metodo para obtener el siguente caracter
	 */
	public void obtenerSgteCaracter() {

		posActual++;

		if (posActual < codigoFuente.length()) {

			if (caracterActual == '\n') {
				filaActual++;
				colActual = 0;
			} else {		
				
				colActual++;
			}

			caracterActual = codigoFuente.charAt(posActual);
			
		} else {
			caracterActual = finCodigo;
		}

	}

	public ArrayList<Token> getListaTokens() {
		return listaTokens;
	}

	
	/**
	 * Metodo principal del analizador lexico
	 * 
	 */
	public void analizar() {

		while (caracterActual != finCodigo) {

			if (caracterActual == ' ' || caracterActual == '\n' || caracterActual == '\t') {
				obtenerSgteCaracter();
				continue;
			}

			if (isNumeronatural())
				continue;
			if (esIdentificador())
				continue;

			if (esOperadorRelacional())
				continue;

			if (isOperadorLogico())
				continue;

			if (isHexadecimal())
				continue;

			if (isCadenaDeCaracteres())
				continue;
			listaTokens.add(new Token(Categoria.DESCONOCIDO, "" + caracterActual, filaActual, colActual));
			obtenerSgteCaracter();
		}

	}

	/**
	 * Detecta los caracteres correspondientes a los enteros
	 * 
	 * @return
	 */
	public boolean isNumeronatural() {

		if (Character.isDigit(caracterActual)) {
			String palabra = "";
			int fila = filaActual;
			int columna = colActual;

			// Transición
			palabra += caracterActual;
			obtenerSgteCaracter();

			while (Character.isDigit(caracterActual)) {
				palabra += caracterActual;
				obtenerSgteCaracter();
			}

			listaTokens.add(new Token(Categoria.NUMERO_NATURAL, palabra, fila, columna));
			return true;

		}

		// RI
		return false;
	}
	
	
	
	
	//public boolean isNumeroReal()

	/**
	 * 
	 * @return
	 */
	public boolean esIdentificador() {

		if (Character.isLetter(caracterActual) || caracterActual == '_'
				|| caracterActual == '$' && caracterActual != 'd') {
			String palabra = "";
			int fila = filaActual;
			int columna = colActual;

			// Transición
			palabra += caracterActual;
			obtenerSgteCaracter();

			while (Character.isLetter(caracterActual) || caracterActual == '_' || caracterActual == '$'
					|| Character.isDigit(caracterActual)) {
				palabra += caracterActual;
				obtenerSgteCaracter();
			}

			listaTokens.add(new Token(Categoria.IDENTIFICADOR, palabra, fila, columna));
			return true;

		}

		// RI
		return false;
	}

	/**
	 * Metodo que valida si el caracter pertenece a los relacionales
	 * 
	 * @return
	 */
	public boolean esRelacional(char a) {

		return a == '>' || a == '<' || a == '=' || a == '!';

	}

	/**
	 * Metodo que se encarga de guardar los token en la categoria de operadores
	 * relacionales
	 * 
	 * @return
	 */
	public boolean esOperadorRelacional() {

		// OPeradores que por si solos ya son un operador relacional
		if (caracterActual == '>' || caracterActual == '<') {

			String palabra = "";
			int fila = filaActual;
			int columna = colActual;

			// Transición
			palabra += caracterActual;

			listaTokens.add(new Token(Categoria.OPERADOR_RELACIONAL, palabra, fila, columna));

			obtenerSgteCaracter();

			if (caracterActual == '=') {

				fila = filaActual;
				columna = colActual;

				// Transición
				palabra += caracterActual;

				listaTokens.add(new Token(Categoria.OPERADOR_RELACIONAL, palabra, fila, columna));

				return true;

			}

			if (!esRelacional(caracterActual)) {

				return true;

			}

		}

		// Si es operador que obligtoriamente debe de tener dos operadores juntos
		if (caracterActual == '!' || caracterActual == '=') {

			String palabra = "";
			int fila = filaActual;
			int columna = colActual;

			palabra += caracterActual;

			obtenerSgteCaracter();

			if (caracterActual == '=') {

				palabra += caracterActual;

				listaTokens.add(new Token(Categoria.OPERADOR_RELACIONAL, palabra, fila, columna));

				return true;

			}
		}

		return false;
	}

	/**
	 * Operadores aritmeticos
	 * 
	 * @return
	 */
	public boolean operadoresAritmeticos() {

		if (caracterActual == '*' || caracterActual == '/' || caracterActual == '%' || caracterActual == '+'
				|| caracterActual == '-') {

			char caraterAnterio = caracterActual;

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;

			listaTokens.add(new Token(Categoria.OPERADOR_RELACIONAL, palabra, fila, columna));

			obtenerSgteCaracter();

			if (caraterAnterio != '+' || caraterAnterio == '-') {

				if (caracterActual == caraterAnterio) {

					fila = filaActual;
					columna = colActual;

					// Transición
					palabra += caracterActual;

					listaTokens.add(new Token(Categoria.OPERADOR_RELACIONAL, palabra, fila, columna));

					return true;

				} else {

					if (caracterActual == '=') {

						fila = filaActual;
						columna = colActual;

						// Transición
						palabra += caracterActual;

						listaTokens.add(new Token(Categoria.OPERADOR_RELACIONAL, palabra, fila, columna));

						return true;

					}
				}

			}

		}

		return false;

	}

	/**
	 * caracterActualcaracterActual Metodo que guarda los operadores logicos
	 */
	public boolean isOperadorLogico() {

		if (caracterActual == '&' || caracterActual == '@' || caracterActual == '?') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;

			listaTokens.add(new Token(Categoria.OPERADORES_LOGICOS, palabra, fila, columna));

			obtenerSgteCaracter();
			return true;
		}

		return false;
	}

	/**
	 * Caracteres del lenguaje hexadecimal
	 * 
	 * @return
	 */
	public boolean isCaracterHexadecimal() {

		return caracterActual == '0' || caracterActual == '1' || caracterActual == '2' || caracterActual == 'A'
				|| caracterActual == 'B';

	}

	/**
	 * Detecta si el caracter es hexadecimal
	 */
	public boolean isHexadecimal() {

		if (caracterActual == '#') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;

			obtenerSgteCaracter();

			if (isCaracterHexadecimal()) {

				while (isCaracterHexadecimal()) {

					palabra += caracterActual;
					obtenerSgteCaracter();
				}
				listaTokens.add(new Token(Categoria.HEXADECIMAL, palabra, fila, columna));

			}

		}

		return false;

	}

	/**
	 * Metood cara detectar las cadenas de caracteres
	 * 
	 * @return
	 */
	public boolean isCadenaDeCaracteres() {

		if (caracterActual == '_') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			palabra += caracterActual;
			obtenerSgteCaracter();

			if (caracterActual == '_') {

				palabra += caracterActual;

				listaTokens.add(new Token(Categoria.CADENA_CARACTERES, palabra, fila, columna));

				return true;

			} else {

				while (caracterActual != '_') {

					palabra += caracterActual;

					obtenerSgteCaracter();

				}

				palabra+=caracterActual;
				listaTokens.add(new Token(Categoria.CADENA_CARACTERES, palabra, fila, columna));

				return true;
				
				
				
			}

		}

		return false;
	}

}
