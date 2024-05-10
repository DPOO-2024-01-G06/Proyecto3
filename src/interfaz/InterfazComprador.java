package interfaz;

import java.util.List;

import galeria.Galeria;
import galeria.controller_galeria.ControladorComprador;
import galeria.controller_galeria.CoordinadorSesion;

import galeria.structurer_inventario.Venta;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_usuarios.Comprador;
import persistencia.PersistenciaNuevo;

public class InterfazComprador extends InterfazAbstracta{

	public static void main(String[] args) {
		System.out.println("INTERFAZ DE COMPRADOR:");
		PersistenciaNuevo persistenciaNuevo = new PersistenciaNuevo();
		Galeria galeria = persistenciaNuevo.nuevaGaleria();
		ControladorComprador contComprador = iniciarSesion(galeria);
		boolean working = true;
		while(working == true) {
			opciones();
			String entradaOpcion = input("Seleccione una opcion:");
			int opcion = Integer.parseInt(entradaOpcion);
			if(opcion == 1) {
				actualizarContraseña(contComprador);
			}
			else if(opcion == 2) {
				actualizarCorreo(contComprador);
			}
			else if(opcion == 3) {
				actualizarCelular(contComprador);
			}
			else if(opcion == 4) {
				comprobarValorMaximo(contComprador);
			}
			else if(opcion == 5) {
				comprobarVerificado(contComprador);
			}
			else if(opcion == 6) {
				verPiezasCompradas(contComprador);
			}
			else if(opcion == 7) {
				verSubastasPendientes(contComprador);
			}
			else if(opcion == 8) {
				ofertarEnSubasta(contComprador);
			}
			else if(opcion == 9) {
				realizarCompra(contComprador);
			}
			else if(opcion == 0) {
				System.out.println("Sesión terminada.");
				working = false;
			}
			
			else {
				System.out.println("La opción seleccionada no es válida");
			}
		}
	}
	public static ControladorComprador iniciarSesion(Galeria galeria){
		ControladorComprador contComprador = null;
			while(contComprador == null) {
			String usuario = input("Ingrese su usuario: ");
			String contrasena = input("Ingrese su contraseña: ");
			CoordinadorSesion coordinadorSesion = new CoordinadorSesion(galeria, usuario, contrasena);
			coordinadorSesion.iniciarSesion();
			if(coordinadorSesion.getControladorActual().equals("ControladorExterno")) {
				boolean desicion = true;
				coordinadorSesion.decidirExterno(desicion);
				contComprador = coordinadorSesion.getControladorComprador();
			}
			else System.out.println("El usuario o la contraseña ingresados no son válidos.");
			}
		return contComprador;
	}
	public static void opciones() {
		System.out.println("\n1 - Actualización de contraseña");
		System.out.println("2 - Actualizacion de correo electronico");
		System.out.println("3 - Actualizacion de número de celular");
		System.out.println("4 - Comprobar valor máximo");
		System.out.println("5 - Comprobar estado de verificación");
		System.out.println("6 - Ver piezas compradas");
		System.out.println("7 - Ver subastas pendientes");
		System.out.println("8 - Ofertar en una subasta");
		System.out.println("9 - Realizar una compra");
		System.out.println("0 - Cerrar sesion");
	}
	
	public static void actualizarContraseña (ControladorComprador contComprador) {
		String contrasena = input("Ingrese su nueva contraseña: ");
		String confirmar = input("Confrime su nueva contraseña: ");
		if (contrasena == confirmar) {
			contComprador.actualizarContraseña(contrasena);
			System.out.println("Contraseña actualizada exitosamente.");
		}
		else
			System.out.println("Las contraseñas no coinciden, intentelo de nuevo.");
	}
	
	public static void actualizarCorreo(ControladorComprador contComprador) {
		String correo = input("Ingrese su nuevo correo: ");
		contComprador.actualizarCorreo(correo);
		System.out.println("Correo actualizado exitosamente.");
	}
		
	public static void actualizarCelular(ControladorComprador contComprador) {
		String celular = input("Ingrese su nuevo número de celular: ");
		contComprador.actualizarCorreo(celular);
		System.out.println("Celular actualizado exitosamente.");
	}
	
	public static void comprobarValorMaximo(ControladorComprador contComprador) {
		Comprador comprador = contComprador.getComprador();
		float vMax = comprador.getValorMaximo();
		System.out.println("Su valor máximo autorizado es de: " + vMax);
	}
	
	public static void comprobarVerificado(ControladorComprador contComprador) {
		Comprador comprador = contComprador.getComprador();
		boolean isVerificado = comprador.getVerficado();
		if (isVerificado == true) {
			System.out.println("Usted se encuentra verificado");
		}
		else
			System.out.println("Usted NO se encuentra verificado");
	}
	
	public static void verPiezasCompradas(ControladorComprador contComprador) {
		Comprador comprador = contComprador.getComprador();
		List<Venta> ventas = comprador.getPiezasCompradas();
		if(ventas.size() == 0) 
			System.out.println("No ha comprado ninguna pieza aún.");
		else {
			for (Venta venta: ventas) {
				System.out.println(venta);
			}
				
		}
	}
	
	public static void verSubastasPendientes (ControladorComprador contComprador) {
		Comprador comprador = contComprador.getComprador();
		List<Subasta> subastas = comprador.getSubastasPendientes();
		
		if(subastas.size() == 0) 
			System.out.println("No hay ninguna subasta.");
		else {
			for (Subasta subasta: subastas) {
				System.out.println(subasta);
			}
				
		}
	}
	
	public static void ofertarEnSubasta(ControladorComprador contComprador) {
		Comprador comprador = contComprador.getComprador();
		if (!comprador.getVerficado()) {
			System.out.println("El usuario no se encuentra verificado");
		}
		else {
			List<Subasta> subastasDisponibles = contComprador.getSubastasDisponibles();
			int tamano = subastasDisponibles.size();
			int indice = inputIndex("Seleccione el índice de la subasta para la que desea ofetar: ", tamano);
			
			double precio = inputFloat("¿Que precio desea ofertar en la subasta?: ");
			if (precio > comprador.getValorMaximo()) {
				System.out.println("Se ha excedido el precio máximo establecido");
			}
			else {
				String metodoPago = input("¿Qué método de pago desea utilizar?: ");
				contComprador.ofertar(indice, precio, metodoPago);
			}
		}
	}
	
	public static void realizarCompra(ControladorComprador contComprador) {
		List<Venta> ventasDisponibles = contComprador.getVentasDisponibles();
		int tamano = ventasDisponibles.size();
		int indice = inputIndex("Seleccione el índice de la subasta para la que desea ofetar: ", tamano);
		contComprador.intentoComprar(indice);
	}
	
}
