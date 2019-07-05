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

		palabrasReservadas.add("int");
		palabrasReservadas.add("integer");
		palabrasReservadas.add("string");

	}

	/**
	 * Metodo que permite obtener el siguiente caracter ademas permite ir
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

//			//Es palabra reservad
//			if(esPalabraReservada())
//				continue;

			// Numeros naturales y reales listo
			if (esNumero())
				continue;

			// Metodo listo
			if (esIdentificador())
				continue;

			// Metodo en prueba
			if (esHexadecimal())
				continue;
			
			 if(esComentario())
				 continue;

			listaTokens.add(new Token(Categoria.DESCONOCIDO, "" + caracterActual, filaActual, colActual));
			obtenerSgteCaracter();
		}

	}

	/**
	 * Detecta los caracteres o cadenas de caracteres que correspondientes a los
	 * enteros SE PROBO ESTA LISTO
	 * 
	 * @return true si el token se agrego con exito
	 */
//	public boolean esNumeroNatural() {
//
//		// Si el caracter corresponde a un digito
//		if (Character.isDigit(caracterActual)) {
//
//			// inciamos palabra
//			String palabra = "";
//
//			// obtenemos la posicon para guradar el token
//			int fila = filaActual;
//			int columna = colActual;
//
//			// Transición
//			palabra += caracterActual;
//
//			// Como ya añadimos el caracter obtenemos el siguiente
//			obtenerSgteCaracter();
//
//			if (Character.isDigit(caracterActual)) {
//
//				while (Character.isDigit(caracterActual)) {
//					palabra += caracterActual;
//					obtenerSgteCaracter();
//				}
//
//				// Por que si no seria un tipo real
//				if (caracterActual != '.') {
//
//					listaTokens.add(new Token(Categoria.NUMERO_NATURAL, palabra, fila, columna));
//					return true;
//
//				}
//			} else {
//
//				if (caracterActual != '.') {
//
//					listaTokens.add(new Token(Categoria.NUMERO_NATURAL, palabra, fila, columna));
//					return true;
//
//				} else {
//
//					posActual = fila;
//
//					filaActual = fila;
//
//					colActual = columna;
//
//				}
//
//			}
//
//		}
//
//		// RI
//		return false;
//	}

	/**
	 * Metodo que reconoce los numero naturales y reales
	 * 
	 * @return
	 */
	public boolean esNumero() {

		if (Character.isDigit(caracterActual)) {

			int fila = filaActual;

			int columna = colActual;

			String palabra = "";

			palabra += caracterActual;

			obtenerSgteCaracter();

			while (Character.isDigit(caracterActual)) {

				palabra += caracterActual;
				obtenerSgteCaracter();
			}

			if (caracterActual == '.') {

				palabra += caracterActual;
				obtenerSgteCaracter();

				if (Character.isDigit(caracterActual)) {

					while (Character.isDigit(caracterActual)) {

						palabra += caracterActual;

						obtenerSgteCaracter();

					}

					listaTokens.add(new Token(Categoria.NUMERO_REAL, palabra, fila, columna));
					return true;

				}

			} else {

				listaTokens.add(new Token(Categoria.NUMERO_NATURAL, palabra, fila, columna));
				return true;

				// GUARDAR EL NUMERO

			}

		}

		return false;
	}

	/**
	 * Metodo que detectad los numerosReales
	 * 
	 * @return
	 */
//	public boolean esNumeroReal() {
//
//		if (Character.isDigit(caracterActual)) {
//
//			// inciamos palabra
//			String palabra = "";
//
//			// obtenemos la posicon para guradar el token
//			int fila = filaActual;
//			int columna = colActual;
//
//			// Transición
//			palabra += caracterActual;
//
//			// Como ya añadimos el caracter obtenemos el siguiente
//			obtenerSgteCaracter();
//
//			while (Character.isDigit(caracterActual) && posActual < codigoFuente.length()) {
//
//				// Transición
//				palabra += caracterActual;
//
//				// Como ya añadimos el caracter obtenemos el siguiente
//				obtenerSgteCaracter();
//
//			}
//
//			if (caracterActual == '.') {
//
//				palabra += caracterActual;
//				obtenerSgteCaracter();
//
//				while (Character.isDigit(caracterActual) && posActual < codigoFuente.length()) {
//
//					palabra += caracterActual;
//
//					obtenerSgteCaracter();
//
//				}
//
//				listaTokens.add(new Token(Categoria.NUMERO_REAL, palabra, fila, columna));
//				return true;
//
//			} else {
//
//				vueltaAtras(fila, columna);
//
//				return false;
//			}
//
//		}
//
//		// Se puede iniciar directamente con punto
//		if (caracterActual == '.') {
//
//			// inciamos palabra
//			String palabra = "";
//
//			// obtenemos la posicon para guradar el token
//			int fila = filaActual;
//			int columna = colActual;
//
//			// Transición
//			palabra += caracterActual;
//
//			// Como ya añadimos el caracter obtenemos el siguiente
//			obtenerSgteCaracter();
//
//			// Si son digitos empezamos a concatenar
//			while (Character.isDigit(caracterActual)) {
//				palabra += caracterActual;
//
//				obtenerSgteCaracter();
//
//			}
//
//			listaTokens.add(new Token(Categoria.NUMERO_REAL, palabra, fila, columna));
//			return true;
//		}
//
//		return false;
//	}

	// public boolean isNumeroReal()

//
//		if (Character.isDigit(caracterActual)) {
//
//			// inciamos palabra
//			String palabra = "";
//
//			// obtenemos la posicon para guradar el token
//			int fila = filaActual;
//			int columna = colActual;
//
//			// Transición
//			palabra += caracterActual;
//
//			// Como ya añadimos el caracter obtenemos el siguiente
//			obtenerSgteCaracter();
//
//			while (Character.isDigit(caracterActual) && posActual < codigoFuente.length()) {
//
//				// Transición
//				palabra += caracterActual;
//
//				// Como ya añadimos el caracter obtenemos el siguiente
//				obtenerSgteCaracter();
//
//			}
//
//			if (caracterActual == '.') {
//
//				palabra += caracterActual;
//				obtenerSgteCaracter();
//
//				while (Character.isDigit(caracterActual) && posActual < codigoFuente.length()) {
//
//					palabra += caracterActual;
//
//					obtenerSgteCaracter();
//
//				}
//
//				listaTokens.add(new Token(Categoria.NUMERO_REAL, palabra, fila, columna));
//				return true;
//
//			} else {
//
//				vueltaAtras(fila, columna);
//
//				return false;
//			}
//
//		}
//
//		// Se puede iniciar directamente con punto
//		if (caracterActual == '.') {
//
//			// inciamos palabra
//			String palabra = "";
//
//			// obtenemos la posicon para guradar el token
//			int fila = filaActual;
//			int columna = colActual;
//
//			// Transición
//			palabra += caracterActual;
//
//			// Como ya añadimos el caracter obtenemos el siguiente
//			obtenerSgteCaracter();
//
//			// Si son digitos empezamos a concatenar
//			while (Character.isDigit(caracterActual)) {
//				palabra += caracterActual;
//
//				obtenerSgteCaracter();
//
//			}
//
//			listaTokens.add(new Token(Categoria.NUMERO_REAL, palabra, fila, columna));
//			return true;
//		}
//
//		return false;
//	
	/**
	 * 
	 * METODO QUE DETECTA QUE ES IDENTIFICADOR
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

			while (Character.isLetter(caracterActual) || Character.isDigit(caracterActual) && caracterActual == '_') {

				palabra += caracterActual;
				obtenerSgteCaracter();
			}

			if (!correspondeReservada(palabra)) {
				listaTokens.add(new Token(Categoria.IDENTIFICADOR, palabra, fila, columna));
				return true;

			} else {

				listaTokens.add(new Token(Categoria.PALABRA_RESERVADA, palabra, fila, columna));
				return true;

			}
		}

		return false;
	}

	/**
	 * 
	 */
	public boolean esPalabraReservada() {

		if (Character.isLetter(caracterActual)) {

			int fila = filaActual;

			int columna = colActual;

			String palabra = "";

			while (Character.isLetter(caracterActual) && posActual < codigoFuente.length()) {

				palabra += caracterActual;

				if (correspondeReservada(palabra)) {

					// GUARDAMOS EL TOKENS

					obtenerSgteCaracter();
					if (caracterActual == ' ') {

						listaTokens.add(new Token(Categoria.PALABRA_RESERVADA, palabra, fila, columna));

						return true;

					}
				}

				obtenerSgteCaracter();
			}

		}

		return false;

	}

	/**
	 * 
	 * @return
	 */
	public boolean correspondeReservada(String palabra) {

		boolean centinela = false;

		for (int i = 0; i < palabrasReservadas.size() && !centinela; i++) {

			if (palabra.equals(palabrasReservadas.get(i))) {

				centinela = true;

			}
		}

		return centinela;

	}

	/**
	 * Metodo que detectad los operadores aritmeticos LISTO
	 * 
	 * @return
	 */
	public boolean esOperadorAritmetico() {

		if (caracterActual == '*' || caracterActual == '/' || caracterActual == '%' || caracterActual == '+'
				|| caracterActual == '-') {

			int fila = filaActual;
			int columna = colActual;

			System.out.println(caracterActual);
			String palabra = "";

			// Transición
			palabra += caracterActual;
			listaTokens.add(new Token(Categoria.OPERADOR_ARITMETICO, palabra, fila, columna));

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
	 * caracterActualcaracterActual Metodo que guarda los operadores logicos
	 * FUNCIONA & es equivalente a Y @ es equivalente a O ? es equivalente a
	 * diferente
	 */
	public boolean esOperadorLogico() {

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
	 * Registra los operadores de asignacion
	 * 
	 * @return true si conrresponde a la categoria
	 */
	public boolean esOperadorDeAsignacion() {

		// Si es uno de aumento y asignacion simultanea
		if (caracterActual == '+' || caracterActual == '-' || caracterActual == '*' || caracterActual == '/'
				|| caracterActual == '%') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;
			obtenerSgteCaracter();

			// para guardarse debe llegar
			if (caracterActual == '=') {
				// Transición
				palabra += caracterActual;
				listaTokens.add(new Token(Categoria.OPERADORES_DE_ASIGNACION, palabra, fila, columna));

				return true;
			}

		}

		// Si es uno de asignacion normal
		if (caracterActual == '=') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;
			listaTokens.add(new Token(Categoria.OPERADORES_DE_ASIGNACION, palabra, fila, columna));

			return true;

		}
		return false;

	}

	/**
	 * 
	 * @return
	 */
	public boolean esOperadorDeIncremento() {

		if (caracterActual == '+' || caracterActual == '-') {

			char caraterAnterior = caracterActual;

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;

			obtenerSgteCaracter();
			if (caracterActual == caraterAnterior) {

				palabra += caracterActual;
				listaTokens.add(new Token(Categoria.OPERADOR_DE_INCREMENTO, palabra, fila, columna));

				return true;

			}

		}

		return false;

	}

	/**
	 * Metodo para detectar los parentesis CUADRAR PARA APERTURA Y CIERRE
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
	 * Metodo para detectar las llaves CUADRAR PARA QUE SEA DE APERTURA Y CIERRE
	 * 
	 * @return
	 */
	public boolean esLlave() {

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
	 * Metodo para detectar el terminal ( ; )
	 * 
	 * @return
	 */
	public boolean esTerminal() {

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
	public boolean esSeparador() {

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

	/**
	 * Caracteres del lenguaje hexadecimal
	 * 
	 * @return
	 */
	public boolean esCaracterHexadecimal() {

		return caracterActual == '0' || caracterActual == '1' || caracterActual == '2' || caracterActual == '3'
				|| caracterActual == '4' || caracterActual == '5' || caracterActual == '6' || caracterActual == '7'
				|| caracterActual == '8' || caracterActual == '9' || caracterActual == 'A' || caracterActual == 'B'
				|| caracterActual == 'C' || caracterActual == 'D' || caracterActual == 'F'

		;

	}

	/**
	 * METODO QUE RECONOCE LOS TOKENS correspondientes a decimal
	 * 
	 */
	public boolean esHexadecimal() {

		if (caracterActual == '#') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;

			obtenerSgteCaracter();

			if (esCaracterHexadecimal()) {

				while (esCaracterHexadecimal()) {

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
	 * Metodo que detecta los comentarios
	 * 
	 * @return
	 */
	public boolean esComentario() {

		if (caracterActual == '_') {

			int fila = filaActual;

			String palabra = "";

			int columna = colActual;

			
			palabra += caracterActual;
			
			obtenerSgteCaracter();

			

			while (Character.isLetter(caracterActual) || Character.isDigit(caracterActual)) {

				palabra += caracterActual;

				obtenerSgteCaracter();

			}

			palabra+=caracterActual;
			if (caracterActual == '_') {

				listaTokens.add(new Token(Categoria.COMENTARIO, palabra, fila, columna));

				return true;

			}

		}

		return false;
	}

	/**
	 * Metodo para volver atras
	 * 
	 * @param fila
	 * @param columna
	 * @param posicion
	 */
	public void vueltaAtras(int fila, int columna) {

		filaActual = fila;

		posActual = columna;

		System.out.println(
				"FILA ACTUAL: " + filaActual + "  POS ACTUAL: " + posActual + "  COLUMNA ACTUAL: " + colActual);

	}

}
