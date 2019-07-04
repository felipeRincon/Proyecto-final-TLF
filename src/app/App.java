package app;

import logica.AnalizadorLexico;
import logica.Token;

public class App {

	public static void main(String arg[]) {

		String codigoFuente = "hola {hola}[hola](hola)";

		AnalizadorLexico al = new AnalizadorLexico(codigoFuente);
		al.analizar();

		for (Token a : al.getListaTokens()) {

			System.out.println(a.toString());

			System.out.println("          -------------");
		}

	}
}
