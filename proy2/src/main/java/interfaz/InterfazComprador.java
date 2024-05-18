package interfaz;

import java.util.List;
import java.util.Map;

import galeria.Galeria;
import galeria.controller_galeria.ControladorComprador;
import galeria.controller_galeria.CoordinadorSesion;

import galeria.structurer_inventario.Venta;
import galeria.structurer_inventario.Artista;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_usuarios.Comprador;

public class InterfazComprador extends InterfazAbstracta{

	public static void main(String[] args) {
		System.out.println("Bienvenido a la interfaz de comprador!");
		Galeria galeria = cargarGaleria();
		ControladorComprador contComprador = iniciarSesion(galeria);
		int opcion =-1;
		while(opcion != 10) {
			opciones();
			opcion = inputInt("Seleccione una opcion:");
			if(opcion == 1) {
				uno(contComprador);
			}
			else if(opcion == 2) {
				dos(contComprador);
			}
			else if(opcion == 3) {
				tres(contComprador);
			}
			else if(opcion == 4) {
				cuatro(contComprador);
			}
			else if(opcion == 5) {
				cinco(contComprador);
			}
			else if(opcion == 6) {
				seis(contComprador);
			}
			else if(opcion == 7) {
				siete(contComprador);
			}
			else if(opcion == 8) {
				ocho(contComprador);
			}
			else if(opcion == 9) {
				nueve(contComprador);
			}
			else if(opcion == 10) {
				diez(galeria);
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
				coordinadorSesion.decidirExterno(true);
				if(coordinadorSesion.getControladorComprador().getComprador() != null){
					contComprador = coordinadorSesion.getControladorComprador();
				}
				else System.out.println("El usuario no tiene ningún comprador asociado a esta cuenta");
			}
			else System.out.println("El usuario o la contraseña ingresados no son válidos.");
			}
		return contComprador;
	}
	public static void opciones() {
		System.out.println("\n1 - Actualizar informacion");
		System.out.println("2 - Comprobar valor máximo");
		System.out.println("3 - Comprobar estado de verificación");
		System.out.println("4 - Ver piezas compradas");
		System.out.println("5 - Ver subastas pendientes");
		System.out.println("6 - Ver historia de una pieza");
		System.out.println("7 - Ver historia de una artista");
		System.out.println("8 - Ofertar en una subasta");
		System.out.println("9 - Realizar una compra");
		System.out.println("10 - Cerrar sesion");
	}
	
	public static void uno(ControladorComprador cComp) {
		String contrasena = input("Ingrese una nueva contraseña:");
		String nombre = input("Ingrese su nombre:");
		String correo =  input("Ingrese su correo:");
		String celular =  input("Ingrese su numero de celular:");
		cComp.actualizarInfo(contrasena, nombre, celular, correo);
		System.out.println("Actualizacion exitosa!");
	}
	
	public static void dos(ControladorComprador contComprador) {
		Comprador comprador = contComprador.getComprador();
		float vMax = comprador.getValorMaximo();
		System.out.println("Su valor máximo autorizado es de: " + vMax);
	}
	
	public static void tres(ControladorComprador contComprador) {
		Comprador comprador = contComprador.getComprador();
		boolean isVerificado = comprador.getVerificado();
		if (isVerificado == true) {
			System.out.println("Usted se encuentra verificado");
		}
		else
			System.out.println("Usted no se encuentra verificado");
	}
	
	public static void cuatro(ControladorComprador contComprador) {
		Comprador comprador = contComprador.getComprador();
		List<Venta> ventas = comprador.getPiezasCompradas();
		if(ventas.size() == 0) 
			System.out.println("No ha comprado ninguna pieza aún.");
		else {
			int i = 0;
			System.out.println("Indice - Titulo - Artista - Fecha de Creación");
			
			for (Venta venta: ventas) {
				 System.out.println(String.valueOf(i) + " - " + 
						venta.getPieza().getTitulo() + " - " + 
						venta.getPieza().getAutor()  + " - " +
						venta.getPieza().getFecha());
				i++;
			}
				
		}
	}
	
	public static void cinco (ControladorComprador contComprador) {
		Comprador comprador = contComprador.getComprador();
		List<Subasta> subastas = comprador.getSubastasPendientes();
		
		if(subastas.size() == 0) 
			System.out.println("No hay ninguna subasta.");
		else {
			int i = 0;
			System.out.println("Indice - Titulo - Oferta Máxima - Fecha límite");
			
			for (Subasta subasta: subastas) {
				 System.out.println(String.valueOf(i) + " - " +
									subasta.getPieza().getTitulo() + " - " +
									subasta.getOfertaMaxima() + " - " +
									subasta.getLimiteTiempo());
				i++;
			}
				
		}
	}
	
	public static void seis(ControladorComprador contComprador) {
		List<Pieza> piezas = contComprador.getListaPiezas();
		if(piezas.size() == 0) System.out.println("No hay piezas para mostrar.");
		else {
			int i=0;
			System.out.println("Indice - Titulo de la pieza");
			
			for(Pieza pieza: piezas){
				System.out.println(String.valueOf(i) + " - " + 
								   pieza.getTitulo());
				i++;
			} 
			i = inputIndex("\nSeleccione un indice:", piezas.size());
			Pieza pieza = piezas.get(i);
			System.out.println(pieza.getInfoPieza());
			Map<String, List<String>> historialPieza = pieza.getHistorialDuenos();
			System.out.println("Nombre Propietario - Costo - Fecha");
			
			for(String comprador : historialPieza.keySet()) {
				String costo =  historialPieza.get(comprador).get(0);
				String fecha = historialPieza.get(comprador).get(1);
				System.out.println(comprador + " - " + 
								   costo + " - " + 
								   fecha);
			}
			
		}
	}
	
	public static void siete(ControladorComprador contComprador) {
		List<Artista> artistas = contComprador.getArtistas();
		if(artistas.size() ==0) System.out.println("No hay compradores para revisar");
		else {
			int i=0;
			System.out.println("Indice- Nombre artista");
			
			for(Artista artista: artistas){
				System.out.println(String.valueOf(i) + " - " + 
								   artista.getNombre());
				i++;
			} 
			i = inputIndex("Seleccione un indice:", artistas.size());
			Artista artista= artistas.get(i); List<Pieza> piezas = artista.getPiezas();
			System.out.println("Titulo - Fecha realización - Fecha de compra - precio");
			
			for(Pieza pieza: piezas) {
				System.out.println(pieza.getTitulo()+ " - " + 
								   pieza.getFecha() + " - " +
								   pieza.getVenta().getFecha() + " - " + 
								   pieza.getVenta().getPrecio());
			}
		}
	}
	
	public static void ocho(ControladorComprador contComprador) {
		Comprador comprador = contComprador.getComprador();
		if (!comprador.getVerificado()) {
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
	
	public static void nueve(ControladorComprador contComprador) {
		List<Venta> ventasDisponibles = contComprador.getVentasDisponibles();
		if(ventasDisponibles.size() ==0) {
			System.out.println("No hay ventas disponibles");
		}
		else {
			int i =0;
			System.out.println("Indice- " + "Titulo- " + "Precio");
			for(Venta venta: ventasDisponibles) {
				System.out.println(i + "- " + venta.getPieza().getTitulo()+ "- "+venta.getPrecio());
				i++;
			}
			int indice = inputIndex("Seleccione el índice de la pieza que desea comprar: ",  ventasDisponibles.size());
			contComprador.intentoComprar(indice);
		}
		
	}
	public static void diez(Galeria galeria) {
		System.out.println("Saliendo...");
		salvarGaleria(galeria);
	}
	
}
