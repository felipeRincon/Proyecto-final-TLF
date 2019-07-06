package app;

import logica.AnalizadorLexico;
import logica.Token;

public class App {

	public static void main(String arg[]) {

		
		/**
		 * APARENTEMENTE ESTA LISTA
		 */
		// Validar en la interfaz que el codigo fuente no sea vacio
		String codigoFuente = "#_++121.23 2323>=--#";

		AnalizadorLexico al = new AnalizadorLexico(codigoFuente);
		al.analizar();

		for (Token a : al.getListaTokens()) {

			System.out.println(a.toString());

			System.out.println("          -------------");
		}

	}
}
