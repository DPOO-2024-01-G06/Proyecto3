package interfaz;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import errores.PersistenciaException;
import galeria.Galeria;
import galeria.structurer_inventario.Artista;
import galeria.structurer_inventario.InventarioGaleria;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.UsuariosGaleria;
import persistencia.PersistenciaInventarioGaleria;

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
	public static InventarioGaleria cargarInventario(PersistenciaInventarioGaleria pInventario) {
		InventarioGaleria inventario = null; 
		try {
			Map<Integer, Pieza> inventarioC = pInventario.cargarInventarioGaleria();
			Map<Integer, Subasta> subastasPendientes = pInventario.cargarSubastasPendientes();
			Map<Integer, Subasta> subastasPasadas = pInventario.cargarSubastasPasadas();
			Map<Integer, Venta> ventasPendientes = pInventario.cargarVentasPendientes();
			Map<Integer, Venta> ventasAceptadas = pInventario.cargarVentasAceptadas();
			List<Artista> artistas = pInventario.cargarArtistas();
			inventario = new InventarioGaleria(subastasPendientes, subastasPasadas, ventasPendientes, ventasAceptadas, inventarioC, artistas);
		} catch (PersistenciaException e) {
			System.out.println("Error al cargar el inventario");
			e.printStackTrace();
		}
		return inventario;
	}
	
	public static UsuariosGaleria cargarUsuarios() {
		UsuariosGaleria usuarios = null;
		return usuarios;
	}
	public static void salvarInventario(Galeria galeria, PersistenciaInventarioGaleria pInventario ) {
		InventarioGaleria inventario = galeria.getInventarioGaleria();
		try {
			pInventario.guardarArtistas(inventario.getArtistas());
			pInventario.guardarInventarioGaleria(inventario.getInventario());
			pInventario.guardarSubastasPasadas(inventario.getSubastasPasadas());
			pInventario.guardarVentasPendientes(inventario.getVentasPendientes());
			pInventario.guardarSubastasPendientes(inventario.getSubastasPendientes());
			pInventario.guardarVentasAceptadas(inventario.getVentasAceptadas());
			
		} catch (PersistenciaException e) {
			e.printStackTrace();
			System.out.println("Error al guardar el inventario");
		}
	}
	
	
}
