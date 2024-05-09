package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import galeria.Galeria;
import galeria.controller_galeria.ControladorAdministrador;
import galeria.structurer_inventario.Artista;
import galeria.structurer_inventario.Escultura;
import galeria.structurer_inventario.InventarioGaleria;
import galeria.structurer_inventario.Oferta;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Pintura;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;
import galeria.structurer_inventario.Video;
import galeria.structurer_usuarios.Administrador;
import galeria.structurer_usuarios.Cajero;
import galeria.structurer_usuarios.Comprador;
import galeria.structurer_usuarios.Externo;
import galeria.structurer_usuarios.Operador;
import galeria.structurer_usuarios.Propietario;
import galeria.structurer_usuarios.UsuariosGaleria;

class AdminTest {
	private Galeria galeria;
	private ControladorAdministrador cAdmin;
	@BeforeEach
	public void setUp() {
		galeria = SampleGaleria();
		cAdmin = new ControladorAdministrador(galeria, galeria.getUsuariosGaleria().getAdministrador());
	}

	
	@Test
	void testAgregarPiezaAInventario() {
		Propietario propietario =galeria.getUsuariosGaleria().getExternos().get(0).getPropietario();
		cAdmin.ingresarPiezaCedida(0, 1000);
		assertEquals(2,cAdmin.getPendientesPorAgregar().size(),"No se estan reduciendo las piezas en el inventario del ADMIN");
		assertEquals(1,cAdmin.getListaPiezas().size(),"No se estan colocando las piezas en el inventario");
		assertEquals(1,propietario.getPiezasCedidas().size(), "No se esta actualizando las piezas que se ceden");
		assertEquals(0,propietario.getPiezasPropiedad().size(), "No se esta actualizando las piezas propiedad");
	}
	@Test
	void testDevolverPiezaCedida() {
		Propietario propietario =galeria.getUsuariosGaleria().getExternos().get(0).getPropietario();
		cAdmin.ingresarPiezaCedida(0, 1000);
		cAdmin.devolverPieza(0);
		assertEquals(0,cAdmin.getListaPiezas().size(), "No se esta eliminando la pieza del inventario");
		assertEquals(1,propietario.getPiezasPropiedad().size(), "No se esta actualizando las piezas propiedad");
		assertEquals(0,propietario.getPiezasCedidas().size(), "No se estan actualizando las pieza cedidas");
	}
	@Test
	void testVerificarUsuario() {
		cAdmin.ingresarPiezaCedida(0, 1000);
		Comprador comprador = cAdmin.getCompradores().get(0);
		Venta  venta = galeria.getInventarioGaleria().getVentaPendiente(0);
		venta.setComprador(comprador);
		comprador.getVentasPendientes().add(venta);
		galeria.getUsuariosGaleria().getAdministrador().getPendientesVerificar().add(comprador);
		cAdmin.verificarExterno(0, 100);
		assertEquals(true,comprador.getVerficado(), "No se esta verificando al usuario");
		assertEquals(0,cAdmin.getUsuariosPendientes().size(), "No se esta eliminando al usuario de la lista de pendientes");
		assertEquals(0,cAdmin.getVentasPendientes().size(), "No se esta utilizando el criterio del valor máximo");
		assertEquals(null,venta.getComprador(), "No se esta bloqueando al usuario con valor mínimo");
		assertEquals(0,comprador.getVentasPendientes().size(), "No se esta eliminando la venta de la lista de pendientes del comprador");
		}

	@Test
	void testVerificarYAceptar() {
		cAdmin.ingresarPiezaCedida(0, 1000);
		Comprador comprador = cAdmin.getCompradores().get(0);
		Venta  venta = galeria.getInventarioGaleria().getVentaPendiente(0);
		venta.setComprador(comprador);
		comprador.getVentasPendientes().add(venta);
		galeria.getUsuariosGaleria().getAdministrador().getPendientesVerificar().add(comprador);
		cAdmin.verificarExterno(0, 1200);
		assertTrue(comprador.getVerficado(), "No se esta verificando al usuario");
		assertEquals(0,cAdmin.getUsuariosPendientes().size(), "No se esta eliminando al usuario de la lista de pendientes");
		assertEquals(1,cAdmin.getVentasPendientes().size(), "No se esta utilizando el criterio del valor máximo");
		
		cAdmin.confirmarVenta(0, true);
		assertTrue(venta.isAceptada(), "No se esta aceptando la venta");
		assertEquals(1,galeria.getUsuariosGaleria().getCajero().getVentasPendientes().size(),"No se esta llevando la venta al cajero");
	}
	@Test
	void testRechazarVenta() {
		cAdmin.ingresarPiezaCedida(0, 1000);
		Comprador comprador = cAdmin.getCompradores().get(0);
		Venta  venta = galeria.getInventarioGaleria().getVentaPendiente(0);
		venta.setComprador(comprador);
		comprador.getVentasPendientes().add(venta);
		galeria.getUsuariosGaleria().getAdministrador().getPendientesVerificar().add(comprador);
		cAdmin.verificarExterno(0, 1200);
		cAdmin.confirmarVenta(0, false);
		assertFalse(venta.isAceptada(), "No se esta rechazando la venta");
		assertEquals(0,galeria.getUsuariosGaleria().getCajero().getVentasPendientes().size(),"Se esta llevando la venta al cajero");
		assertEquals(null,venta.getComprador(),"No se esta actualizando el comprador");
		assertEquals(0,comprador.getVentasPendientes().size(),"No se esta actualizando la lista de pendientes del comprador");
	}
	
	@Test
	void testInvalidarExterno() {
		cAdmin.ingresarPiezaCedida(0, 1000);
		Comprador comprador = cAdmin.getCompradores().get(0);
		Venta  venta = galeria.getInventarioGaleria().getVentaPendiente(0);
		venta.setComprador(comprador);
		comprador.getVentasPendientes().add(venta);
		galeria.getUsuariosGaleria().getAdministrador().getPendientesVerificar().add(comprador);
		cAdmin.invalidarExterno(0);
		assertEquals(0,cAdmin.getUsuariosPendientes().size(), "No se esta eliminando al usuario de la lista de pendientes");
		assertEquals(0,comprador.getVentasPendientes().size(), "No se esta eliminando las ventas pendientes del usuario");
		assertEquals(null,venta.getComprador(), "No se esta eliminando al usuario de la venta");
	}
	@Test
	void testReestablecerMaximo1() {
		cAdmin.ingresarPiezaCedida(0, 1000);
		Comprador comprador = cAdmin.getCompradores().get(0);
		galeria.getUsuariosGaleria().getAdministrador().getPendientesVerificar().add(comprador);
		cAdmin.verificarExterno(0, 100);
		Venta  venta = galeria.getInventarioGaleria().getVentaPendiente(0);
		venta.setComprador(comprador);
		comprador.getVentasPendientes().add(venta);
		galeria.getUsuariosGaleria().getAdministrador().getSuperaronLimite().add(comprador);
		cAdmin.reestablecerMaximo(0, 1001);
		assertEquals(0,cAdmin.getSuperaronLimite().size(), "No se esta eliminando al usuario de la lista de superaron limite");
		assertEquals(1,cAdmin.getVentasPendientes().size(), "No se esta agregando la venta al tener un limite superior");
	}
	@Test
	void testReestablecerMaximo2() {
		cAdmin.ingresarPiezaCedida(0, 1000);
		Comprador comprador = cAdmin.getCompradores().get(0);
		galeria.getUsuariosGaleria().getAdministrador().getPendientesVerificar().add(comprador);
		cAdmin.verificarExterno(0, 100);
		Venta  venta = galeria.getInventarioGaleria().getVentaPendiente(0);
		venta.setComprador(comprador);
		comprador.getVentasPendientes().add(venta);
		galeria.getUsuariosGaleria().getAdministrador().getSuperaronLimite().add(comprador);
		cAdmin.reestablecerMaximo(0, 999);
		assertEquals(0,cAdmin.getSuperaronLimite().size(), "No se esta eliminando al usuario de la lista de superaron limite");
		assertEquals(0,cAdmin.getVentasPendientes().size(), "No se esta evaluando correctamente el límite de maximo precio");
		assertEquals(null,venta.getComprador(), "No se reestablece el comprador");
		assertEquals(0,comprador.getVentasPendientes().size(), "No se reestablece la lista de ventas pendientes del comprador");
	}
	
	
	
	
	
	
	
	public Galeria SampleGaleria() {
		//Crear artistas y asociarles una obra
		Artista michelangelo = new Artista("Michelangelo", new ArrayList<Pieza>());
		Escultura escultura = new Escultura("David","1504-06-08", "Florencia", false, 
											null, (float)517, (float)199,(float)50,(float)5660,"marmol",null, null, michelangelo,
											new HashMap<String, List<String>>(), null);
		michelangelo.addPieza(escultura);
		Artista  munch= new Artista("Edvard Munch", new ArrayList<Pieza>());
		Pintura pintura = new Pintura("The scream", "1893", "Berlin", false, null, (float) 73.5,(float)91,"oleo",
									  null, munch, new HashMap<String, List<String>>(), null);
		munch.addPieza(pintura);
		Artista akerman = new Artista("Chantal Akerman", new ArrayList<Pieza>());
		
		Video video = new Video("Jeanne Dielman, 23 quai du Commerce, 1080 Bruxelles", "1975-01-01","Bruselas",false,
								null,(float) 201, "DVD","Ejempo",null, akerman, new HashMap<String, List<String>>(),null);
		akerman.addPieza(video);
		List<Artista> artistas= new ArrayList<Artista>();		
		artistas.add(akerman);artistas.add(munch);artistas.add(michelangelo);
		//Crear un externo y asociarle una obra
		Externo externo1 = new Externo("Juan123","0000","Juan Ramirez", "a","b",null,null);
		externo1.crearComprador((float)10000);
		externo1.getPropietario().addPiezaPropiedad(video);
		video.setPropietario(externo1.getPropietario());
		List<Externo> externos = new ArrayList<Externo>();externos.add(externo1);
		//Creacion Galeria
		InventarioGaleria inventarioGaleria = new InventarioGaleria(new HashMap<Integer, Subasta>(), new HashMap<Integer, Subasta>(), new  HashMap<Integer, Venta>(), new  HashMap<Integer, Venta>(), new HashMap<Integer, Pieza>(),artistas);
		Administrador administrador = new Administrador("ADMIN", "0000", "a","b","c",new ArrayList<Venta>(),new ArrayList<Pieza>(), new ArrayList<Comprador>(), new ArrayList<Comprador>());
		administrador.getPiezasPorAgregar().add(video);administrador.getPiezasPorAgregar().add(pintura);administrador.getPiezasPorAgregar().add(escultura); 
		Cajero cajero = new Cajero("CAJERO", "0000", "nombre", "correo@gmail.com", "0000000000", new ArrayList<Venta>());
		Operador operador = new Operador("OPERADOR", "0000", "nombre", "0000000000", "correo@gmail.com", new ArrayList<Oferta>());
		return new Galeria( new UsuariosGaleria(administrador, cajero, operador, externos), 
						inventarioGaleria);
	}
	
	
	

}
