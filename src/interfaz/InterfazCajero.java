package interfaz;
import java.util.List;

import java.util.Map;

import galeria.Galeria;
import galeria.controller_galeria.ControladorAdministrador;
import galeria.controller_galeria.ControladorCajero;
import galeria.controller_galeria.ControladorOperador;
import galeria.controller_galeria.CoordinadorSesion;
import galeria.structurer_inventario.Artista;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Comprador;
import galeria.structurer_usuarios.Propietario;
import persistencia.PersistenciaNuevo;


public class InterfazCajero extends InterfazAbstracta{
	public static void main(String[] args) {
		System.out.println("Bienvenido a la interfaz de cajero!");
		PersistenciaNuevo persistenciaNuevo = new PersistenciaNuevo();
		Galeria galeria = persistenciaNuevo.nuevaGaleria();
		ControladorCajero cCajero = iniciarSesion(galeria);
		int s =-1;
		while(s != 5) {
			mostrarOpciones();
			s = inputInt("Seleccione una opcion:");
			if(s == 1) {
				uno(cCajero);
			}
			else if(s == 2) {
				dos(cCajero);
			}
			else if(s == 3) {
				tres(cCajero);
			}else if(s == 4) {
				cuatro(cCajero);
			}
			else if(s == 5) {
				System.out.println("Saliendo...");
			}
			
			
			else {
				System.out.println("Opcion incorrecta, vuelva a intentarlo");
			}
		}
	}
	public static ControladorCajero iniciarSesion(Galeria galeria){
		ControladorCajero controladorCajero = null;
			while(controladorCajero == null) {
			String nombreUsuario = input("Ingrese su nombre de usuario (CAJERO):");
			String contrasena = input("Ingrese su contraseña (0000):");
			CoordinadorSesion coordinadorSesion = new CoordinadorSesion(galeria, nombreUsuario, contrasena);
			coordinadorSesion.iniciarSesion();
			if(coordinadorSesion.getControladorActual().equals("ControladorCajero")) {
				controladorCajero = coordinadorSesion.getControladorCajero();
			}
			else System.out.println("Usuario y/o contraseña incorrectos, vuelvalo a intentar");
			}
		return controladorCajero;
	}
	public static void mostrarOpciones() {
		System.out.println("\n1- Actualizar informacion");
		System.out.println("2- Registrar/Denegar Pago");
		System.out.println("3- Ver historia de un artista");
		System.out.println("4- Ver una pieza de la galeria");
		System.out.println("5- Cerrar sesion");
	}
	public static void uno(ControladorCajero cCajero) {
		String contrasena = input("Ingrese una nueva contraseña:");
		String nombre = input("Ingrese su nombre:");
		String correo =  input("Ingrese su correo:");
		String celular =  input("Ingrese su numero de celular:");
		cCajero.actualizarInfo(contrasena, nombre, celular, correo);
		System.out.println("Actualizacion exitosa!");
	}
	public static void dos(ControladorCajero cCajero) {
		List<Venta> pendientes = cCajero.getVentasPendientes();
		if(pendientes.size() == 0) System.out.println("No tiene pagos por registrar");
		else {
			System.out.println("Indice/ Titulo");
			int i=0;
			for(Venta venta:pendientes) {
				System.out.println(Integer.toString(i) + "/ " + venta.getPieza().getTitulo());
				i++;
			}
			i=inputIndex("Seleccione un indice:",i);
			boolean decision = inputBoolean("Desea aceptarla{Y} o rechazarla{N}? ", "Y", "N");
			cCajero.registrarPago(i, decision);
		}

		}
	
	public static void tres(ControladorCajero cCajero) {
		List<Artista> artistas = cCajero.getArtistas();
		if(artistas.size() ==0) System.out.println("No hay artistas para revisar");
		else {
			int i=0;
			System.out.println("Indice- Nombre artista");
			for(Artista artista: artistas){
				System.out.println(String.valueOf(i) + "- " + artista.getNombre());
				i++;
			} 
			i = inputIndex("Seleccione un indice:", artistas.size());
			Artista artista= artistas.get(i); List<Pieza> piezas = artista.getPiezas();
			System.out.println("Titulo- Fecha realización- Fecha compra- precio");
			for(Pieza pieza: piezas) {
				System.out.println(pieza.getTitulo()+"- " +pieza.getFecha() + "- "+ pieza.getVenta().getFecha() +"- "+ pieza.getVenta().getPrecio());
			}
		}
	}
	public static void cuatro(ControladorCajero cCajero) {
		List<Pieza> piezas = cCajero.getListaPiezas();
		if(piezas.size() == 0) System.out.println("No hay piezas para mostrar");
		else {
			int i=0;
			System.out.println("Indice- Titulo de la pieza");
			for(Pieza pieza: piezas){
				System.out.println(String.valueOf(i) + "- " + pieza.getTitulo());
				i++;
			} 
			i = inputIndex("Seleccione un indice:", piezas.size());
			Pieza pieza = piezas.get(i);
			System.out.println(pieza.getInfoPieza());
			Map<String, List<String>> historialPieza = pieza.getHistorialDuenos();
			System.out.println("Nombre Propietario- Costo- Fecha");
			for(String comprador : historialPieza.keySet()) {
				String costo =  historialPieza.get(comprador).get(0);
				String fecha = historialPieza.get(comprador).get(1);
				System.out.println(comprador + "- " +costo + "- "+ fecha);
			}
		}
		}
	
	public static void cinco() {
		//TODO cargar toda la informacion y actualizarla en los archivos de persistencia.
	}
}
