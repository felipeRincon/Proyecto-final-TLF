package app;

import logica.AnalizadorLexico;
import logica.Token;

public class App {

	public static void main(String arg[]) {

		//Validar en la interfaz que el codigo fuente no sea vacio
		String codigoFuente = "2.223 2323s 44.34";

		AnalizadorLexico al = new AnalizadorLexico(codigoFuente);
		al.analizar();

		for (Token a : al.getListaTokens()) {

			System.out.println(a.toString());

			System.out.println("          -------------");
		}

	}
}
