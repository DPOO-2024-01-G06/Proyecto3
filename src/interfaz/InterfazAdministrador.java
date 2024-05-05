package interfaz;
import java.util.List;
import java.util.Scanner;

import galeria.Galeria;
import galeria.controller_galeria.ControladorAdministrador;
import galeria.controller_galeria.ControladorInternos;
import galeria.controller_galeria.CoordinadorSesion;
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
			
			else {
				System.out.println("Opcion incorrecta, vuelva a intentarlo");
			}
		}
	}
	public static String input(String mensaje) {
		 Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		 System.out.print(mensaje + " ");
		return myObj.next();
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
		System.out.println("1- Actualizar informacion");
		System.out.println("2- Verificar/Invalidar a un comprador");
		System.out.println("3- Aceptar una propuesta de compra");
		System.out.println("4- Ingresar una pieza cedida");
		System.out.println("5- Devolver una pieza cedida");
		System.out.println("6- Actualizar el salario de un comprador");
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
	}
	public static void dos(ControladorAdministrador cAdmin) {
		List<Externo> pendientes = cAdmin.getUsuariosPendientes();
		int i = 0;
		System.out.println("Indice- Nombre- Salario");
		for(Externo pendiente: pendientes){
			System.out.println(String.valueOf(i) + "- " + pendiente.getNombre() + "- " + pendiente.getComprador().getSalario());
			i++;
		}
		i = Integer.valueOf(input("Seleccione un indice:"));
		boolean continuar = true; 
		while(continuar) {
			if(i < 0 || i>= pendientes.size()){
				System.out.println("Indice incorrecto, ningun cambio efectuado");
			}
			else {
				String dec = input("Desea verficar al usuario[Y] o invalidarlo[N]:");
				if(dec.equals("Y")) cAdmin.verificarExterno(i);
				else if(dec.equals("N")) cAdmin.invalidarExterno(i);
				else System.out.println("Entrada invalida, vuelva a intentarlo");
			}
			
		}
	}
	public static void tres(ControladorAdministrador cAdmin) {
		List<Venta> pendientes = cAdmin.getVentasPendientes();
		
	}
	
	
	
	public static void nueve() {
		//TODO cargar toda la informacion y actualizarla en los archivos de persistencia.
	}
	
	
}
