package app;

import logica.AnalizadorLexico;
import logica.Token;

public class App {

	public static void main(String arg[]) {

		System.out.println(Character.isLetter('_'));
		
		
		
		String codigoFuente = "$cvffddd$dsds";

		AnalizadorLexico al = new AnalizadorLexico(codigoFuente);
		al.analizar();

		for (Token a : al.getListaTokens()) {

			System.out.println(a.toString());

			System.out.println("          -------------");
		}

	}
}
