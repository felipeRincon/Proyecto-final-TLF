package logica;

public class Expresion {

	public static String NUMERO_NATURAL = "(D)(D)*";

	public static String NUMERO_REAL = "(D)(D)*(.)(D)(D)*";

	public static String IDENTIFICADORES = "[ (L) ] [ ((L) U (D))* ]";

	public static String HEXADECIMALES = "(#)(H)(H)*";

	public static String TERMINAL = "( ; )";

	public static String esCadenaCaracteres = "($)(L)*($)";
	public static String OPERADORES_ASIGNACION = ("[ (=) ] U [ (+) (=) ] U [ (-) (=) ] U [ (t) (=) ]");

	public static String OPERADORES_INCREMENTO = " [ (+) (+) ] U [ (-) (-) ]";

	public static String PARENTESIS = " [ ( ] U [ ) ]";

	public static String CORCHETES = " [ { ] U [ } ]";

	public static String LLAVES = "{ ( [ ) U ( ] ) }";

	public static String COMENTARIO = "(_)(L)*(D)*(_)";

	public static String SEPARADOR = "( | )";

	public static String OPERADORES_ARITMETICOS = "[ ( + ) ] U [ ( - ) ] U [ ( * ) ] U [ ( / ) ] U [ ( % ) ]";

	public static String OPERADORES_LOGICOS = "[ ( ? ) ]  U [ ( @ ) ]  U [ ( & ) ]";

	public static String OPERADORES_RELACIONALES = "[ ( ? ) ] U [  { ( < ) U ( > ) } ( = ) ] U [ ( = ) ( = ) ]";

	public static String CADENA_CARACTERES = "[ ($) [ ()  ] ] ";
}
