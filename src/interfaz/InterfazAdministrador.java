package interfaz;
import java.util.List;
import java.util.Scanner;

import galeria.Galeria;
import galeria.controller_galeria.ControladorAdministrador;
import galeria.controller_galeria.ControladorInternos;
import galeria.controller_galeria.CoordinadorSesion;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Externo;
import persistencia.PersistenciaNuevo;

public class InterfazAdministrador { 
	
	public static void main(String[] args) {
		System.out.println("Bienvenido a la interfaz de administrador!");
		//TODO Cargar la galeria ya guardada
		PersistenciaNuevo persistenciaNuevo = new PersistenciaNuevo();
		Galeria galeria = persistenciaNuevo.nuevaGaleria();
		ControladorAdministrador cAdmin = iniciarSesion(galeria);
		int s = -1;
		while(s != 9) {
			
			mostrarOpciones();
			s = Integer.valueOf(input("Seleccione una opcion:"));
			if(s == 1) {
				uno(cAdmin);
			}
			else if(s == 2) {
				dos(cAdmin);
			}
			else if(s == 3) {
				tres(cAdmin);
			}
			else if(s == 4) {
				cuatro(cAdmin);
			}
			else if(s ==5) {
				cinco(cAdmin);
			}
			else if(s == 9) {
				System.out.println("Saliendo...");
			}
			
			else {
				System.out.println("Opcion incorrecta, vuelva a intentarlo");
			}
		}
	}
	public static String input(String mensaje) {
		 @SuppressWarnings("resource")
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		 System.out.print(mensaje + " ");
		 String resultado = myObj.next();
		return resultado;
	}
	public static ControladorAdministrador iniciarSesion(Galeria galeria){
		ControladorAdministrador controladorAdministrador = null;
			while(controladorAdministrador == null) {
			String nombreUsuario = input("Ingrese su nombre de usuario (ADMIN):");
			String contrasena = input("Ingrese su contraseña (0000):");
			CoordinadorSesion coordinadorSesion = new CoordinadorSesion(galeria, nombreUsuario, contrasena);
			coordinadorSesion.iniciarSesion();
			if(coordinadorSesion.getControladorActual().equals("ControladorInternos")) {
				ControladorInternos controladorInternos = coordinadorSesion.getControladorInternos();
				controladorInternos.decidirControlador();
				if(controladorInternos.getControladorActual().equals("ControladorAdministrador")) {
					controladorAdministrador = controladorInternos.getControladorAdministrador();
					System.out.println("Ingreso Exitoso!");
				}
			}
			if(controladorAdministrador == null) System.out.println("Usuario y/o contraseña incorrectos, vuelvalo a intentar");
			}
		return controladorAdministrador;
	}
	public static void mostrarOpciones() {
		System.out.println("\n1- Actualizar informacion");
		System.out.println("2- Verificar/Invalidar a un comprador");
		System.out.println("3- Aceptar/Rechazar una propuesta de compra");
		System.out.println("4- Ingresar una pieza cedida");
		System.out.println("5- Devolver una pieza cedida");
		System.out.println("6- Actualizar el valor maximo de un comprador");
		System.out.println("7- Ver la historia de un comprador");
		System.out.println("8- Ver la historia de una pieza");
		System.out.println("9- Cerrar sesion");
	}
	public static void uno(ControladorAdministrador cAdmin) {
		String contrasena = input("Ingrese una nueva contraseña:");
		String nombre = input("Ingrese su nombre:");
		String correo =  input("Ingrese su correo:");
		String celular =  input("Ingrese su numero de celular:");
		cAdmin.actualizarInfo(contrasena, nombre, celular, correo);
		System.out.println("Actualizacion exitosa!");
	}
	public static void dos(ControladorAdministrador cAdmin) {
		List<Externo> pendientes = cAdmin.getUsuariosPendientes();
		if(pendientes.size() == 0) System.out.println("No tiene usuarios por verificar");
		else {
			int i = 0;
			System.out.println("Indice- Nombre- Salario");
			for(Externo pendiente: pendientes){
				System.out.println(String.valueOf(i) + "- " + pendiente.getNombre() + "- " + pendiente.getComprador().getSalario());
				i++;
			}
			boolean continuar = true; 
			while(continuar) {
				i = Integer.valueOf(input("Seleccione un indice:"));
				if(i < 0 || i>= pendientes.size()){
					System.out.println("Indice incorrecto, ningun cambio efectuado");
				}
				else {
					String dec = input("Desea verficar al usuario[Y] o invalidarlo[N]:");
					if(dec.equals("Y")) {
						float valorMaximo = Float.valueOf(input("Ingrese el valor maximo de compras(sin puntos ni comas):"));
						cAdmin.verificarExterno(i, valorMaximo);
						continuar = false;
					} 
					else if(dec.equals("N")) { cAdmin.invalidarExterno(i); continuar = false;}
					else System.out.println("Entrada invalida, vuelva a intentarlo");
				}
			}
		}
	}
	public static void tres(ControladorAdministrador cAdmin) {
		List<Venta> pendientes = cAdmin.getVentasPendientes();
		if(pendientes.size() == 0) System.out.println("No tiene propuestas de compra pendientes por aceptar");
		else {
			int i = 0;
			System.out.println("Indice- Nombre- Salario- Pieza");
			for(Venta pendiente: pendientes){
				Externo externo = pendiente.getExterno();
				System.out.println(String.valueOf(i) + "- " + externo.getNombre() + "- " + externo.getComprador().getSalario() + "- " + pendiente.getPieza().getTitulo());
				i++;
			}
			boolean continuar = true; 
			while(continuar) {
				i = Integer.valueOf(input("Seleccione un indice"));
				if(i < 0 || i>= pendientes.size()){
					System.out.println("Indice incorrecto, ningun cambio efectuado");
				}
				else {
					String dec = input("Desea aceptar la oferta[Y] o rechazarla[N]:");
					if(dec.equals("Y")) {
						cAdmin.confirmarVenta(i, true);
						continuar = false;
					} 
					else if(dec.equals("N")) {cAdmin.confirmarVenta(i, false);; continuar = false;}
					else System.out.println("Entrada invalida, vuelva a intentarlo");
				}
			}
		}
	}
	
	public static void cuatro(ControladorAdministrador cAdmin) {
		List<Pieza> pendientes = cAdmin.getPendientesPorAgregar();
		if(pendientes.size() == 0) System.out.println("No tiene piezas pendientes por ingresar");
		else {
			int i = 0;
			System.out.println("Indice- Nombre Propietario- Titulo- Tiempo Disponible");
			for(Pieza pendiente: pendientes){
				Externo externo = pendiente.getExterno();
				System.out.println(String.valueOf(i) + "- " + externo.getNombre() + "- " + pendiente.getTitulo() + "- " + pendiente.getTiempoDisponible());
				i++;
			}
			boolean continuar = true; 
			while(continuar) {
				i = Integer.valueOf(input("Seleccione un indice"));
				if(i < 0 || i>= pendientes.size()){
					System.out.println("Indice incorrecto, ningun cambio efectuado");
				}
				else {
					String dec = input("Desea ingresar la pieza[Y] o rechazarla[N]:");
					if(dec.equals("Y")) {
						float precio = Float.valueOf(input("Ingrese el precio que le pondra a la pieza"));
						cAdmin.ingresarPiezaCedida(i, precio);;
						continuar = false;
					} 
					else if(dec.equals("N")) {cAdmin.ingresarPiezaCedida(i, -1); continuar = false;}
					else System.out.println("Entrada invalida, vuelva a intentarlo");
				}
			}
		}
	}
	
	public static void cinco(ControladorAdministrador cAdmin) {
		List<Pieza> cedidas = cAdmin.getPiezasCedidas();
		if(cedidas.size() == 0) System.out.println("La galeria no tiene piezas cedidas");
		else {
			int i = 0;
			System.out.println("Indice- Nombre Propietario- Titulo- Tiempo Disponible");
			for(Pieza cedida: cedidas){
				Externo externo = cedida.getExterno();
				System.out.println(String.valueOf(i) + "- " + externo.getNombre() + "- " + cedida.getTitulo() + "- " + cedida.getTiempoDisponible());
				i++;
			}
			boolean continuar = true; 
			while(continuar) {
				i = Integer.valueOf(input("Seleccione el indice de la pieza que quiere devolver a su propietario"));
				if(i < 0 || i>= cedidas.size()){
					System.out.println("Indice incorrecto, ningun cambio efectuado");
				}
				else {
					cAdmin.devolverPieza(i);
				}
			}
		}
	}
	public static void seis(ControladorAdministrador cAdmin) {
		
	}
	
	
	
	
	public static void nueve() {
		//TODO cargar toda la informacion y actualizarla en los archivos de persistencia.
	}
	
	
}
