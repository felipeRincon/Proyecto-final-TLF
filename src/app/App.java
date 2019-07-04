package app;

import logica.AnalizadorLexico;
import logica.Token;

public class App {

	public static void main(String arg[]) {

		String codigoFuente = "$cvff d**d$dsds %>>=--sasaddff+++ddfdf ! , () {{ddfd{df  .1213  1212zaddf   1212.1212 ";

		AnalizadorLexico al = new AnalizadorLexico(codigoFuente);
		al.analizar();

		for (Token a : al.getListaTokens()) {

			System.out.println(a.toString());

			System.out.println("          -------------");
		}

	}
}
