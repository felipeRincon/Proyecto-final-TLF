package app;

import logica.AnalizadorLexico;
import logica.Token;

public class App {

	public static void main(String arg[]) {

		String codigoFuente = "123 1234.12 $ola k ace$ nombre + / * >=<= ? @ #F2B5C #FC123 [}{](yt),;";

		AnalizadorLexico al = new AnalizadorLexico(codigoFuente);
		al.analizar();

		for (Token a : al.getListaTokens()) {

			System.out.println(a.toString());

			System.out.println("          -------------");
		}

	}
}
