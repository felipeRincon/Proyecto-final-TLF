package logica;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import sun.security.x509.CertificateIssuerName;

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

		if (posActual < codigoFuente.length()) {

			if (caracterActual != '\n') {

				colActual++;

				filaActual = 0;

			} else {

				filaActual++;
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

		
			
			if (esOperadorDeIncremento_esOperadorDeDecremento())
				continue;

		
			
			//arreglar 
			if (esNumeroReal())
				continue;

			
			// Numeros naturales y reales listo
			if (esNatural())
				continue;

			// Metodo por probar
			if (esOperadorAritmetico())
				continue;
			// Metodo

			if (esOperadorDeAsignacion())
				continue;

			// Metodo por probar
			if (esOperadorRelacional())
				continue;

			// Metodo por probar
			if (esOperadorLogico())
				continue;

			// metodo probado
			if (esComentario())
				continue;

			// RESOLVER ESTE ERROR
			if (esPalabraReservada())
				continue;

			if (esIdentificador())
				continue;

			if (esHexadecimal())
				continue;

			if (esLlave_esCorchete())
				continue;

			// Metodo por probar
			if (esParentesis())
				continue;

			// Metodo probado
			if (esCadenaCaracteres())
				continue;

			if (esTerminal())
				continue;

			listaTokens.add(new Token(Categoria.DESCONOCIDO, "" + caracterActual, filaActual, colActual));
			obtenerSgteCaracter();
		}

	}

	/**
	 * Metodo que reconoce los nuemro naturales
	 * 
	 * @return
	 */
	public boolean esNatural() {

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

			listaTokens.add(new Token(Categoria.NUMERO_NATURAL, palabra, fila, columna));
			
			return true;

		}

		return false;

	}

	/**
	 * Metodo que reconoce los nuemros reales
	 * 
	 * @return
	 */
	public boolean esNumeroReal() {

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

				while (Character.isDigit(caracterActual)) {

					palabra += caracterActual;
					obtenerSgteCaracter();
				}

				listaTokens.add(new Token(Categoria.NUMERO_REAL, palabra, fila, columna));
				return true;

			}else {
				
				vueltaAtras(fila, columna);
				return false;
			}

		}

		return false;

	}

	/**
	 * Metodo que reconoce los numero naturales y reales
	 * 
	 * @return
	 */
//	public boolean esNatural_esReal() {
//
//		if (Character.isDigit(caracterActual)) {
//
//			int fila = filaActual;
//
//			int columna = colActual;
//
//			String palabra = "";
//
//			palabra += caracterActual;
//
//			obtenerSgteCaracter();
//
//			while (Character.isDigit(caracterActual)) {
//
//				palabra += caracterActual;
//				obtenerSgteCaracter();
//			}
//
//			if (caracterActual == '.') {
//
//				palabra += caracterActual;
//				obtenerSgteCaracter();
//
//				if (Character.isDigit(caracterActual)) {
//
//					while (Character.isDigit(caracterActual)) {
//
//						palabra += caracterActual;
//
//						obtenerSgteCaracter();
//
//					}
//
//					listaTokens.add(new Token(Categoria.NUMERO_REAL, palabra, fila, columna));
//					obtenerSgteCaracter();
//					return true;
//
//				}
//
//			} else {
//
//				listaTokens.add(new Token(Categoria.NUMERO_NATURAL, palabra, fila, columna));
//				obtenerSgteCaracter();
//				return true;
//
//				// GUARDAR EL NUMERO
//
//			}
//
//		}
//
//		return false;
//	}

	/**
	 * Metodo que detectad los numerosReales
	 * 
	 * @return
	 */

//	/**
//	 * 
//	 * METODO QUE DETECTA QUE ES IDENTIFICADOR
//	 * 
//	 * @return
//	 */
//	public boolean _esIdentificador_esPalabraReservada() {
//
//		if (Character.isLetter(caracterActual)) {
//			String palabra = "";
//			int fila = filaActual;
//			int columna = colActual;
//
//			palabra += caracterActual;
//
//			obtenerSgteCaracter();
//
//			while (Character.isLetter(caracterActual) || Character.isDigit(caracterActual)) {
//
//				palabra += caracterActual;
//				obtenerSgteCaracter();
//			}
//
//			if (!correspondeReservada(palabra)) {
//				listaTokens.add(new Token(Categoria.IDENTIFICADOR, palabra, fila, columna));
//				obtenerSgteCaracter();
//				return true;
//
//			} else {
//
//				listaTokens.add(new Token(Categoria.PALABRA_RESERVADA, palabra, fila, columna));
//				obtenerSgteCaracter();
//				return true;
//
//			}
//		}
//
//		return false;
//	}

	/**
	 * 
	 */
	public boolean esPalabraReservada() {

		if (Character.isLetter(caracterActual)) {

			int fila = filaActual;

			int columna = colActual;

			String palabra = "";

			palabra += caracterActual;

			obtenerSgteCaracter();

			while (Character.isLetter(caracterActual)) {
				palabra += caracterActual;
				obtenerSgteCaracter();
			}

			if (Character.isDigit(caracterActual)) {

				vueltaAtras(fila, columna);
				return false;
			}

			if (correspondeReservada(palabra)) {

				listaTokens.add(new Token(Categoria.PALABRA_RESERVADA, palabra, fila, columna));
				return true;

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

	public boolean esIdentificador() {

		if (Character.isLetter(caracterActual)) {

			int fila = filaActual;

			int columna = colActual;

			String palabra = "";

			palabra += caracterActual;

			obtenerSgteCaracter();

			while (Character.isLetter(caracterActual) || Character.isDigit(caracterActual)) {

				palabra += caracterActual;

				obtenerSgteCaracter();

			}

			listaTokens.add(new Token(Categoria.IDENTIFICADOR, palabra, fila, columna));

			return true;

//			if(!correspondeReservada(palabra)) {
//				
//				
//				listaTokens.add(new Token(Categoria.IDENTIFICADOR, palabra, fila, columna));
//				
//				return true;
//				
//			}

		}

		return false;
	}

	/**
	 * Metodo que detectad los operadores aritmeticos LISTO
	 * 
	 * @return
	 */
	public boolean esOperadorAritmetico() {

		if (caracterActual == '+') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			palabra += caracterActual;

			obtenerSgteCaracter();

			if (caracterActual == '+' || caracterActual == '=') {

				vueltaAtras(fila, columna);

				return false;
			} else {

				listaTokens.add(new Token(Categoria.OPERADOR_ARITMETICO, palabra, fila, columna));
				// no se obtiene el siguiente caracter por que este hay que revisarlo con otro
				// automata
				return true;
			}

		}

		if (caracterActual == '-') {

			int fila = filaActual;

			int columna = colActual;

			String palabra = "";

			palabra += caracterActual;

			obtenerSgteCaracter();

			if (caracterActual == '-' || caracterActual == '=') {

				vueltaAtras(fila, columna);

				esOperadorDeIncremento_esOperadorDeDecremento();
				return false;
			} else {

				listaTokens.add(new Token(Categoria.OPERADOR_ARITMETICO, palabra, fila, columna));

				// no se obtiene el siguiente caracter por que este hay que revisarlo con otro
				// automata
				return true;

			}

		}

		if (caracterActual == '*' || caracterActual == '/' || caracterActual == '%') {

			int fila = filaActual;

			int columna = colActual;

			String palabra = "";

			palabra += caracterActual;

			obtenerSgteCaracter();

			if (caracterActual == '=') {

				vueltaAtras(fila, columna);
				return false;
			} else {

				listaTokens.add(new Token(Categoria.OPERADOR_ARITMETICO, palabra, fila, columna));

				// no se obtiene el siguiente caracter por que este hay que revisarlo con otro
				// automata
				return true;
			}

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

		if (caracterActual == '?') {

			String palabra = "";
			int fila = filaActual;
			int columna = colActual;

			palabra += caracterActual;

			obtenerSgteCaracter();
			listaTokens.add(new Token(Categoria.OPERADOR_RELACIONAL, palabra, fila, columna));
			return true;

		}

		// Si es operador que obligtoriamente debe de tener dos operadores juntos
		if (caracterActual == '=') {

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

			} else {

				vueltaAtras(fila, columna);

				return false;
			}

		}

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

				// aca es cualquier otra cosa por lo tanto guardamos el > o el <
				// no se optiene el siguiente caracter ni se concatena por que lo debe revisar
				// otro automata
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

		if (caracterActual == '&' || caracterActual == '@' || caracterActual == '¿') {

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

		if (caracterActual == '=') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;

			obtenerSgteCaracter();

			if (caracterActual == '=') {

				vueltaAtras(fila, columna);

				return false;

			} else {

				listaTokens.add(new Token(Categoria.OPERADORES_DE_ASIGNACION, palabra, fila, columna));
				// NO SE CONCATENA POR QUE ESTE DEBE SER REVISADO POR OTRO AUTOMATA
				return true;
			}

		}

		if (caracterActual == '+') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;

			obtenerSgteCaracter();

			if (caracterActual == '+') {
				vueltaAtras(fila, columna);

				return false;
			}

			if (caracterActual == '=') {

				palabra += caracterActual;
				listaTokens.add(new Token(Categoria.OPERADORES_DE_ASIGNACION, palabra, fila, columna));

				obtenerSgteCaracter();
				return true;

			}

		}

		if (caracterActual == '-') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;

			obtenerSgteCaracter();

			if (caracterActual == '-') {
				vueltaAtras(fila, columna);

				return false;
			}

			if (caracterActual == '=') {

				palabra += caracterActual;
				listaTokens.add(new Token(Categoria.OPERADORES_DE_ASIGNACION, palabra, fila, columna));

				obtenerSgteCaracter();
				return true;

			}

		}
		// SI no necesita bactraking
		if (caracterActual == '*' || caracterActual == '/' || caracterActual == '%') {

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

				obtenerSgteCaracter();
				return true;
			}

		}

		return false;

	}

	/**
	 * Guarda los operadores de incremento
	 * 
	 * @return
	 */
	public boolean esOperadorDeIncremento_esOperadorDeDecremento() {

		if (caracterActual == '+') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			palabra += caracterActual;
			obtenerSgteCaracter();

			if (caracterActual == '+') {

				palabra += caracterActual;

				listaTokens.add(new Token(Categoria.OPERADOR_DE_INCREMENTO, palabra, fila, columna));
				obtenerSgteCaracter();
				return true;

			} else {

				vueltaAtras(fila, columna);
				return false;
			}
		}

		if (caracterActual == '-') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			palabra += caracterActual;
			obtenerSgteCaracter();

			if (caracterActual == '-') {

				palabra += caracterActual;
				obtenerSgteCaracter();
				listaTokens.add(new Token(Categoria.OPERADOR_DECREMENTO, palabra, fila, columna));
				return true;

			} else {

				vueltaAtras(fila, columna);
				return false;
			}
		}

		return false;

	}

	/**
	 * Metodo para detectar los parentesis CUADRAR PARA APERTURA Y CIERRE
	 * 
	 * @return
	 */
	public boolean esParentesis() {

		if (caracterActual == '(') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;

			listaTokens.add(new Token(Categoria.PARENTESIS_APERTURA, palabra, fila, columna));

			obtenerSgteCaracter();

			return true;
		}

		if (caracterActual == ')') {

			int fila = filaActual;
			int columna = colActual;

			String palabra = "";

			// Transición
			palabra += caracterActual;

			listaTokens.add(new Token(Categoria.PARENTESIS_CIERRE, palabra, fila, columna));

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
	public boolean esLlave_esCorchete() {

		if (caracterActual == '{') {

			int fila = filaActual;
			int columna = colActual;
			String palabra = "";
			// Transición
			palabra += caracterActual;
			listaTokens.add(new Token(Categoria.CORCHETES_APERTURA, palabra, fila, columna));

			obtenerSgteCaracter();

			return true;
		}

		if (caracterActual == '}') {
			int fila = filaActual;
			int columna = colActual;
			String palabra = "";
			// Transición
			palabra += caracterActual;
			listaTokens.add(new Token(Categoria.CORCHETES_CIERRE, palabra, fila, columna));

			obtenerSgteCaracter();

			return true;
		}

		if (caracterActual == '[') {

			int fila = filaActual;
			int columna = colActual;
			String palabra = "";
			// Transición
			palabra += caracterActual;
			listaTokens.add(new Token(Categoria.LLAVES_APERTURA, palabra, fila, columna));

			obtenerSgteCaracter();

			return true;

		}

		if (caracterActual == ']') {

			int fila = filaActual;
			int columna = colActual;
			String palabra = "";
			// Transición
			palabra += caracterActual;
			listaTokens.add(new Token(Categoria.LLAVES_CIERRE, palabra, fila, columna));

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
	public boolean esCadenaCaracteres() {

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
					obtenerSgteCaracter();
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
						obtenerSgteCaracter();

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

			palabra += caracterActual;
			if (caracterActual == '_') {

				listaTokens.add(new Token(Categoria.COMENTARIO, palabra, fila, columna));
				obtenerSgteCaracter();
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

		colActual = columna;

		caracterActual = codigoFuente.charAt(columna);

	}

}
