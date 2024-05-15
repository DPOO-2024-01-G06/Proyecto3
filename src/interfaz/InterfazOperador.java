package interfaz;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import galeria.Galeria;
import galeria.controller_galeria.ControladorOperador;
import galeria.controller_galeria.CoordinadorSesion;
import galeria.structurer_inventario.Artista;
import galeria.structurer_inventario.Oferta;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Venta;
import persistencia.PersistenciaNuevo;


public class InterfazOperador extends InterfazAbstracta{
	public static void main(String[] args) {
		System.out.println("Bienvenido a la interfaz de operador!");
		
		//TODO Cargar la galeria ya guardada
		PersistenciaNuevo persistenciaNuevo = new PersistenciaNuevo();
		Galeria galeria = persistenciaNuevo.nuevaGaleria();
		ControladorOperador cOperador = iniciarSesion(galeria);

		int s =-1;
		while(s != 6) {
			mostrarOpciones();
			s = inputInt("Seleccione una opcion:");
			if(s == 1) {
				uno(cOperador);
			}
			else if(s == 2) {
				dos(cOperador);
			}
			
			else if(s == 3) {
				tres(cOperador);
			}
			else if(s == 4) {
				cuatro(cOperador);
			}
			else if(s == 5) {
				cinco(cOperador);
			}

			else if(s == 6) {
				System.out.println("Saliendo...");
			}
			
			else {
				System.out.println("Opcion incorrecta, vuelva a intentarlo");
			}
		}
		
		}
	public static ControladorOperador iniciarSesion(Galeria galeria){
		ControladorOperador controladorOperador = null;
			while(controladorOperador == null) {
			String nombreUsuario = input("Ingrese su nombre de usuario (OPERADOR):");
			String contrasena = input("Ingrese su contraseña (0000):");
			CoordinadorSesion coordinadorSesion = new CoordinadorSesion(galeria, nombreUsuario, contrasena);
			coordinadorSesion.iniciarSesion();
			if(coordinadorSesion.getControladorActual().equals("ControladorOperador")) {
				controladorOperador = coordinadorSesion.getControladorOperador();
			}
			else System.out.println("Usuario y/o contraseña incorrectos, vuelvalo a intentar");
			}
		return controladorOperador;
	}
	public static void mostrarOpciones() {
		System.out.println("\n1- Agregar Oferta Pendiente");
		System.out.println("2- Planear Subasta");
		System.out.println("3- Actualizar datos");
		System.out.println("4- Ver historia de un artista");
		System.out.println("5- Ver una pieza de la galeria");
		System.out.println("6- Cerrar sesion");}
	
	public static void uno(ControladorOperador cOperador) {
		List<Oferta> ofertasPendientes = cOperador.getOfertasPendientes();
		if (ofertasPendientes.size()==0){
			System.out.println("No tiene ofertas pendientes.");
		}
		else {
			System.out.println("Indice/ Pieza/ Valor");
			int i=0;
			for(Oferta oferta:ofertasPendientes) {
				System.out.println(Integer.toString(i)+"/ "+oferta.getSubasta().getPieza().getTitulo() 
						+ "/ " + oferta.getValor());
				i++;
			}
		i=inputIndex("Seleccione una opción: ", ofertasPendientes.size());
		cOperador.agregarOfertaPendiente(i);
		}
		int indice = inputIndex("Ingrese el indice de la pieza a la : ",ofertasPendientes.size());
		cOperador.agregarOfertaPendiente(indice);
		System.out.println("Oferta agregada!");
	}
	public static void dos(ControladorOperador cOperador) {
		List<Venta> ventas = cOperador.getVentasPendientes();
		if(ventas.size()==0) {
			System.out.println("No hay piezas para subastar");
		}
		else {
			System.out.println("Indice/ Titulo");
			int i=0;
			for(Venta venta:ventas) {
				System.out.println(Integer.toString(i)+"/ " + venta.getPieza().getTitulo());
				i++;
			}
			i = inputIndex("Seleccione el índice de la pieza que desea subastar: ", i);
			Venta venta= ventas.get(i);
	        float valorMinimo = inputFloat("Ingrese el valor mínimo de la subasta: ");
	        String fecha= input("Ingrese la fecha de finalización de la subasta (formato: yyyy-MM-dd):");
	        LocalDateTime fechaFinal = LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	
	        float valorInicial = inputFloat("Ingrese el valor inicial de la subasta:");
	
	        cOperador.planearSubasta(venta, valorMinimo,new ArrayList<Oferta>(), fechaFinal, valorInicial);
		}
	
	}
	public static void tres(ControladorOperador cOperador) {
		String contrasena = input("Ingrese una nueva contraseña:");
		String nombre = input("Ingrese su nombre:");
		String correo =  input("Ingrese su correo:");
		String celular =  input("Ingrese su numero de celular:");
		cOperador.actualizarInfo(contrasena, nombre, celular, correo);
		System.out.println("Actualizacion exitosa!");
	}
	public static void cuatro(ControladorOperador cOperador) {
		List<Artista> artistas = cOperador.getArtistas();
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
	public static void cinco(ControladorOperador cOperador) {
		List<Pieza> piezas = cOperador.getListaPiezas();
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
			
		}}
	public static void seis(ControladorOperador cOperador) {
		//TODO cargar toda la informacion y actualizarla en los archivos de persistencia.
	}
	}
