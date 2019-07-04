package logica;

import java.util.ArrayList;

public class AnalizadorLexico {

	// Codigo fuente que va ser analizado
	private String codigoFuente;

	// Lista de tokens que reconoce el analizador lexico
	private ArrayList<Token> listaTokens;

	// Palabras reservadas del lenguaje
	private ArrayList<String> palabrasReservadas;

	// Caracter actual
	private char caracterActual;

	//
	private char finCodigo;

	// Posiciones para guardar los tokens y reconocer la posicion actual
	private int posActual, colActual, filaActual;

	/**
	 * Inicializamos
	 * 
	 * @param codigoFuente
	 */
	public AnalizadorLexico(String codigoFuente) {
		this.codigoFuente = codigoFuente;
		this.listaTokens = new ArrayList<Token>();
		this.caracterActual = codigoFuente.charAt(posActual);
		this.filaActual = 0;
		this.colActual = 0;
		this.finCodigo = 0;

		this.palabrasReservadas = new ArrayList<String>();

		palabrasReservadas.add("dInt");
		palabrasReservadas.add("dInt");
		palabrasReservadas.add("dInt");
		palabrasReservadas.add("dInt");
		palabrasReservadas.add("dInt");
		palabrasReservadas.add("dInt");
		palabrasReservadas.add("dInt");

	}

	/**
	 * Metodo que oermite obtener el siguiente caracter ademas permite ir
	 * actualizando la fila cada vez que detected un salto de linea
	 */

	public void obtenerSgteCaracter() {

		posActual++;

		// Verificamos que no se desborde
		if (posActual < codigoFuente.length()) {

			if (caracterActual != '\n') {
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

		// Analizamos todo la fuente de entrada
		while (caracterActual != finCodigo) {

			// Si son vacios o saltos de linea llamamos a obtener caracter
			if (caracterActual == ' ' || caracterActual == '\n' || caracterActual == '\t') {
				obtenerSgteCaracter();
				continue;
			}

			if (esNumeroReal())
				continue;

			if (esNumeronatural())
				continue;
			if (esIdentificador())
				continue;

			if (esOperadorAritmetico())
				continue;

			if (esOperadorRelacional())
				continue;

			if (isOperadorLogico())
				continue;

			if (isHexadecimal())
				continue;

			if (isCadenaDeCaracteres())
				continue;

			if (isLlave())
				continue;

			if (isParentesis())
				continue;

			if (isTerminal())
				continue;

			if (isSeparador())
				continue;
			listaTokens.add(new Token(Categoria.DESCONOCIDO, "" + caracterActual, filaActual, colActual));
			obtenerSgteCaracter();
		}

	}

	/**
	 * Detecta los caracteres o cadenas de caracteres que correspondientes a los
	 * enteros ESTA LISTO
	 * 
	 * @return true si el token se agrego con exito
	 */
	public boolean esNumeronatural() {

		// Si el caracter corresponde a un digito
		if (Character.isDigit(caracterActual)) {

			// inciamos palabra
			String palabra = "";

			// obtenemos la posicon para guradar el token
			int fila = filaActual;
			int columna = colActual;

			// Transición
			palabra += caracterActual;

			// Como ya añadimos el caracter obtenemos el siguiente
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

	/**
	 * Metodo que detectad los numerosReales
	 * 
	 * @return
	 */
	public boolean esNumeroReal() {

		if (Character.isDigit(caracterActual)) {

			// inciamos palabra
			String palabra = "";

			// obtenemos la posicon para guradar el token
			int fila = filaActual;
			int columna = colActual;

			// Transición
			palabra += caracterActual;

			// Como ya añadimos el caracter obtenemos el siguiente
			obtenerSgteCaracter();

			while (Character.isDigit(caracterActual) && posActual < codigoFuente.length()) {

				// Transición
				palabra += caracterActual;

				// Como ya añadimos el caracter obtenemos el siguiente
				obtenerSgteCaracter();

			}

			if (caracterActual == '.') {

				palabra += caracterActual;
				obtenerSgteCaracter();

				while (Character.isDigit(caracterActual) && posActual < codigoFuente.length()) {

					palabra += caracterActual;

					obtenerSgteCaracter();

				}

				listaTokens.add(new Token(Categoria.NUMERO_REAL, palabra, fila, columna));
				return true;

			}

		}

		// Se puede iniciar directamente con punto
		if (caracterActual == '.') {

			// inciamos palabra
			String palabra = "";

			// obtenemos la posicon para guradar el token
			int fila = filaActual;
			int columna = colActual;

			// Transición
			palabra += caracterActual;

			// Como ya añadimos el caracter obtenemos el siguiente
			obtenerSgteCaracter();

			// Si son digitos empezamos a concatenar
			while (Character.isDigit(caracterActual)) {
				palabra += caracterActual;

				obtenerSgteCaracter();

			}

			listaTokens.add(new Token(Categoria.NUMERO_REAL, palabra, fila, columna));
			return true;
		}

		return false;
	}

	// public boolean isNumeroReal()

	/**
	 * 
	 * @return
	 */
	public boolean esIdentificador() {

		if (Character.isLetter(caracterActual)) {
			String palabra = "";
			int fila = filaActual;
			int columna = colActual;

			palabra += caracterActual;

			obtenerSgteCaracter();

			while (Character.isLetter(caracterActual) || Character.isDigit(caracterActual) && caracterActual != '$') {

				palabra += caracterActual;
				obtenerSgteCaracter();
			}

			listaTokens.add(new Token(Categoria.IDENTIFICADOR, palabra, fila, columna));
			return true;

		}

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

			obtenerSgteCaracter();

			if (caracterActual == '=') {

				fila = filaActual;
				columna = colActual;

				// Transición
				palabra += caracterActual;

				listaTokens.add(new Token(Categoria.OPERADOR_RELACIONAL, palabra, fila, columna));

				obtenerSgteCaracter();
				return true;

			} else {
				listaTokens.add(new Token(Categoria.OPERADOR_RELACIONAL, palabra, fila, columna));
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
				obtenerSgteCaracter();

				return true;

			}

		}

		return false;
	}

	/**
	 * Metodo que detectad los operadores aritmeticos LISTO
	 * 
	 * @return
	 */
	public boolean esOperadorAritmetico() {

		if (caracterActual == '*' || caracterActual == '/' || caracterActual == '%' || caracterActual == '+'
				|| caracterActual == '-') {

			char caraterAnterio = caracterActual;

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;
			listaTokens.add(new Token(Categoria.OPERADOR_ARITMETICO, palabra, fila, columna));
			obtenerSgteCaracter();

			if (caraterAnterio == '-') {

				if (caracterActual == caraterAnterio) {

					fila = filaActual;
					columna = colActual;

					// Transición
					palabra += caracterActual;

					listaTokens.add(new Token(Categoria.OPERADOR_ARITMETICO, palabra, fila, columna));
					return true;

				}

//				else {
//
//					if (caracterActual == '=') {
//
//						fila = filaActual;
//						columna = colActual;
//
//						// Transición
//						palabra += caracterActual;
//
//						listaTokens.add(new Token(Categoria.OPERADOR_ARITMETICO, palabra, fila, columna));
//						return true;
//
//					}
//				}

			}
			if (caraterAnterio == '+') {

				if (caracterActual == caraterAnterio) {

					fila = filaActual;
					columna = colActual;

					// Transición
					palabra += caracterActual;

					listaTokens.add(new Token(Categoria.OPERADOR_ARITMETICO, palabra, fila, columna));
					return true;

				}

			}

			return true;

		}

		return false;

	}

	/**
	 * caracterActualcaracterActual Metodo que guarda los operadores logicos
	 * FUNCIONA & es equivalente a Y @ es equivalente a O ? es equivalente a
	 * diferente
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

				return true;
			}

		}

		return false;

	}

	/**
	 * Metodo para detectar las cadenas de caracteres
	 * 
	 * @return
	 */
	public boolean isCadenaDeCaracteres() {

		if (caracterActual == '$') {
			{

				int fila = filaActual;
				int columna = colActual;

				String palabra = "";

				// Transición
				palabra += caracterActual;

				obtenerSgteCaracter();

				if (caracterActual == '$') {

					palabra += caracterActual;

					listaTokens.add(new Token(Categoria.CADENA_CARACTERES, palabra, fila, columna));

					return true;
				} else {

					String faltante = codigoFuente.substring(posActual + 1);

					if (faltante.contains("$")) {

						while (caracterActual != '$' && posActual < codigoFuente.length()) {
							palabra += caracterActual;

							obtenerSgteCaracter();

						}
						palabra += caracterActual;

						listaTokens.add(new Token(Categoria.CADENA_CARACTERES, palabra, fila, columna));

						return true;
					}

				}
			}

		}

		return false;
	}

	/**
	 * Metodo para detectar las llaves
	 * 
	 * @return
	 */
	public boolean isLlave() {

		if (caracterActual == '{' || caracterActual == '}' || caracterActual == '[' || caracterActual == ']') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;

			listaTokens.add(new Token(Categoria.LLAVES, palabra, fila, columna));

			obtenerSgteCaracter();

			return true;
		}

		return false;
	}

	/**
	 * Metodo para detectar los parentesis
	 * 
	 * @return
	 */
	public boolean isParentesis() {

		if (caracterActual == '(' || caracterActual == ')') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;

			listaTokens.add(new Token(Categoria.PARENTESIS, palabra, fila, columna));

			obtenerSgteCaracter();

			return true;
		}

		return false;
	}

	/**
	 * Metodo para detectar el terminal ( ; )
	 * 
	 * @return
	 */
	public boolean isTerminal() {

		if (caracterActual == ';') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;

			listaTokens.add(new Token(Categoria.TERMINAL, palabra, fila, columna));

			obtenerSgteCaracter();

			return true;
		}

		return false;
	}

	/**
	 * Metodo para detectar el separador ( , )
	 * 
	 * @return
	 */
	public boolean isSeparador() {

		if (caracterActual == ',') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;

			listaTokens.add(new Token(Categoria.SEPARADOR, palabra, fila, columna));

			obtenerSgteCaracter();

			return true;
		}

		return false;
	}

}
