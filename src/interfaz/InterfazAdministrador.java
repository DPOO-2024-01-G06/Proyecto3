package interfaz;
import java.util.List;

import galeria.Galeria;
import galeria.controller_galeria.ControladorAdministrador;
import galeria.controller_galeria.CoordinadorSesion;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Comprador;
import galeria.structurer_usuarios.Externo;
import persistencia.PersistenciaNuevo;

public class InterfazAdministrador extends InterfazAbstracta{ 
	
	public static void main(String[] args) {
		System.out.println("Bienvenido a la interfaz de administrador!");
		//TODO Cargar la galeria ya guardada
		PersistenciaNuevo persistenciaNuevo = new PersistenciaNuevo();
		Galeria galeria = persistenciaNuevo.nuevaGaleria();
		ControladorAdministrador cAdmin = iniciarSesion(galeria);
		int s =-1;
		while(s != 10) {
			mostrarOpciones();
			s = inputInt("Seleccione una opcion:");
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
			else if(s == 10) {
				System.out.println("Saliendo...");
			}
			
			else {
				System.out.println("Opcion incorrecta, vuelva a intentarlo");
			}
		}
	}
	public static ControladorAdministrador iniciarSesion(Galeria galeria){
		ControladorAdministrador controladorAdministrador = null;
			while(controladorAdministrador == null) {
			String nombreUsuario = input("Ingrese su nombre de usuario (ADMIN):");
			String contrasena = input("Ingrese su contraseña (0000):");
			CoordinadorSesion coordinadorSesion = new CoordinadorSesion(galeria, nombreUsuario, contrasena);
			coordinadorSesion.iniciarSesion();
			if(coordinadorSesion.getControladorActual().equals("ControladorAdministrador")) {
				controladorAdministrador = coordinadorSesion.getControladorAdministrador();
			}
			else System.out.println("Usuario y/o contraseña incorrectos, vuelvalo a intentar");
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
		System.out.println("9- Ver la historia de un artista");
		System.out.println("10- Cerrar sesion");
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
			i = inputIndex("Seleccione un indice:", pendientes.size());
			Boolean dec = inputBoolean("Desea verficar al usuario[Y] o invalidarlo[N]:","Y","N");
			if(dec) {
				float valorMaximo = inputFloat("Ingrese el valor maximo de compras(con puntos para decimales):");
				cAdmin.verificarExterno(i, valorMaximo);
			} 
			else{cAdmin.invalidarExterno(i);}
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
				i = inputIndex("Seleccione un indice", pendientes.size());
				boolean dec = inputBoolean("Desea aceptar la oferta[Y] o rechazarla[N]:","Y","N");
				if(dec) cAdmin.confirmarVenta(i, true);
				else cAdmin.confirmarVenta(i, false);
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
				i = inputIndex("Seleccione un indice", pendientes.size());
				boolean dec = inputBoolean("Desea ingresar la pieza[Y] o rechazarla[N]:","Y","N");
				if(dec) {
					float precio = inputFloat("Ingrese el precio que le pondra a la pieza");
					cAdmin.ingresarPiezaCedida(i, precio);;
				} 
				else cAdmin.ingresarPiezaCedida(i, -1);
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
			
			i = inputIndex("Seleccione el indice de la pieza que quiere devolver a su propietario", cedidas.size());
			cAdmin.devolverPieza(i);
		}
	}
	public static void seis(ControladorAdministrador cAdmin) {
		List<Externo> pendientes = cAdmin.getUsuariosPendientes();
		if(pendientes.size() == 0) System.out.println("No hay usuarios que hayan superado su valor máximo");
		else {
			int i = 0;
			System.out.println("Indice- Nombre- Salario- Valor máximo");
			for(Externo pendiente: pendientes){
				Comprador comprador = pendiente.getComprador();
				System.out.println(String.valueOf(i) + "- " + pendiente.getNombre() + "- " + comprador.getSalario() + "- " + comprador.getValorMaximo());
				i++;
			} 
			i = inputIndex("Seleccione un indice:", pendientes.size());
			float valorMaximo = inputFloat("Ingrese el nuevo valor maximo(Con puntos para decimales):");
			cAdmin.reestablecerMaximo(i, valorMaximo);
		}
	}
	
	
	
	public static void diez() {
		//TODO cargar toda la informacion y actualizarla en los archivos de persistencia.
	}
	
	
}
