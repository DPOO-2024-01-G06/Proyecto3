package interfaz;

import java.util.Scanner;

public abstract class InterfazAbstracta {
	
	
	@SuppressWarnings("resource")
	public static String input(String mensaje) {
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		 System.out.print(mensaje + " ");
		 String resultado = myObj.next();
		return resultado;
	}
	
	@SuppressWarnings("resource")
	public static float inputFloat(String mensaje) {
		float resultado = -1;
		boolean continuar = true;
		System.out.print(mensaje + " ");
		while(continuar) {
			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
			String cadena = myObj.next();
			try {
				resultado = Float.valueOf(cadena);
				continuar = false;
			}
			catch(Exception e){
				System.out.print("Por favor ingrese un número válido: ");
			}
		}
		return resultado;
	}
	
	@SuppressWarnings("resource")
	public static int inputInt(String mensaje) {
		int resultado = -1;
		boolean continuar = true;
		System.out.print(mensaje + " ");
		while(continuar) {
			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
			String cadena = myObj.next();
			try {
				resultado = Integer.valueOf(cadena);
				continuar = false;
			}
			catch(Exception e){
				System.out.print("Por favor ingrese un número válido: ");
			}
		}
		return resultado;
	}
	
	@SuppressWarnings("resource")
	public static boolean inputBoolean(String mensaje, String si, String no) {
		boolean resultado = false;
		boolean continuar = true;
		System.out.print(mensaje + " ");
		while(continuar) {
			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
			String cadena = myObj.next();
			if(cadena.equals(si)) {resultado = true; continuar = false;}
			else if(cadena.equals(no)) {resultado = false;continuar = false;}
			else System.out.print("Por favor ingrese una entrada válida: "); 
		}
		return resultado;
	}
	
	@SuppressWarnings("resource")
	public static int inputIndex(String mensaje, int tamano) {
		int indice = -1;
		boolean continuar = true;
		System.out.print(mensaje + " ");
		while(continuar) {
			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
			String cadena = myObj.next();
			try {
				indice = Integer.valueOf(cadena);
				if(indice < 0 || indice >= tamano) {
					System.out.print("Por favor ingrese un indice válido: ");
				}
				else {
				continuar = false;
				}
			}
			catch(Exception e){
				System.out.print("Por favor ingrese un indice válido");
			}
		}
		return indice;
	}
}
