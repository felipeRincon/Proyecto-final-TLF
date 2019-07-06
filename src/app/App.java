package app;

import logica.AnalizadorLexico;
import logica.Token;

public class App {

	public static void main(String arg[]) {

		
		// Validar en la interfaz que el codigo fuente no sea vacio
		String codigoFuente = "+d+m+++>=>>¿¿?@&====22.33g44([})#F223{$5LVV$";

		
		String codigoFuente2="+++++";
		AnalizadorLexico al = new AnalizadorLexico(codigoFuente);
		al.analizar();

		for (Token a : al.getListaTokens()) {

			System.out.println(a.toString());

			System.out.println("          -------------");
		}

	}
}
