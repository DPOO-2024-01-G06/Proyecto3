package prueba;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import galeria.Galeria;
import galeria.controller_galeria.ControladorComprador;
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
import galeria.structurer_usuarios.UsuariosGaleria;


public class CompradorTest {
	private Galeria galeria;
	private ControladorComprador contComprador;
	
	@BeforeEach
	public void setUp() {
		galeria = sampleGaleria();
		List<Externo> listaExternos = galeria.getUsuariosGaleria().getExternos();
		Externo comprador1 = null;
		comprador1 = listaExternos.get(0);
		
		contComprador = new ControladorComprador(galeria, comprador1);
		assertNotNull(contComprador);
    }


    @Test
    void testOfertarNotVerified() {
    	Pieza pieza = galeria.getUsuariosGaleria().getAdministrador().getPiezasPorAgregar().get(0);
    	Subasta subasta = new Subasta(1000, 1000, new ArrayList<Oferta>(), "", pieza, null);
    	galeria.getInventarioGaleria().getSubastasPendientes().add(subasta);
    	contComprador.ofertar(0, 999.0, "metodoPago");
    	assertEquals(0, galeria.getUsuariosGaleria().getOperador().getOfertasPendientes().size(), "La oferta NO se agregó correctamente." );
    	assertEquals(0, contComprador.getComprador().getSubastasPendientes().size(), "La oferta NO se agregó correctamente." );
    }
    
    @Test
    void testOfertarVerified() {
    	contComprador.getComprador().setVerificado(true);
    	Pieza pieza = galeria.getUsuariosGaleria().getAdministrador().getPiezasPorAgregar().get(0);
    	Subasta subasta = new Subasta(1000, 1000, new ArrayList<Oferta>(),"", pieza, null);
    	galeria.getInventarioGaleria().getSubastasPendientes().add(subasta);
    	contComprador.ofertar(0, 999.0, "metodoPago");
    	assertEquals(1, galeria.getUsuariosGaleria().getOperador().getOfertasPendientes().size(), "La oferta NO se agregó correctamente." );
    	assertEquals(1, contComprador.getComprador().getSubastasPendientes().size(), "La oferta NO se agregó correctamente." );
    }
    
    @Test
    void testIntentoComprarNotVerified() {
    	Comprador comprador = galeria.getUsuariosGaleria().getExternos().get(0).getComprador();
    	Pieza pieza = galeria.getUsuariosGaleria().getAdministrador().getPiezasPorAgregar().get(0);
    	Venta venta = new Venta(1000, false, false, pieza, null, "");
    	galeria.getInventarioGaleria().agregarPieza(venta);
    	contComprador.intentoComprar(0);
        assertEquals(1, comprador.getVentasPendientes().size(), "No se está agregando a las ventas pendientes");
        assertEquals(1, galeria.getUsuariosGaleria().getAdministrador().getPendientesVerificar().size(), "No se está agregando a pendientes verificar");
    }
    
    @Test
    void testIntentoComprarVerified1() {
    	Comprador comprador = galeria.getUsuariosGaleria().getExternos().get(0).getComprador();
    	comprador.setVerificado(true);
    	Pieza pieza = galeria.getUsuariosGaleria().getAdministrador().getPiezasPorAgregar().get(0);
    	Venta venta = new Venta(1000, false, false, pieza, null, "");
    	galeria.getInventarioGaleria().agregarPieza(venta);
    	contComprador.intentoComprar(0);
    	assertEquals(1, comprador.getVentasPendientes().size(), "No se está agregando a las ventas pendientes");
        assertEquals(0, galeria.getUsuariosGaleria().getAdministrador().getPendientesVerificar().size(), "No se está agregando a pendientes verificar");
        assertEquals(1, galeria.getUsuariosGaleria().getAdministrador().getSuperaronLimite().size(), "No se agregó el comprador a superaron límite");
    }
    
    @Test
    void testIntentoComprarVerified2() {
    	Comprador comprador = galeria.getUsuariosGaleria().getExternos().get(0).getComprador();
    	comprador.setVerificado(true);
    	comprador.setValorMaximo(1001);
    	Pieza pieza = galeria.getUsuariosGaleria().getAdministrador().getPiezasPorAgregar().get(0);
    	Venta venta = new Venta(1000, false, false, pieza, null, "");
    	galeria.getInventarioGaleria().agregarPieza(venta);
    	contComprador.intentoComprar(0);
    	assertEquals(1, comprador.getVentasPendientes().size(), "No se está agregando a las ventas pendientes");
        assertEquals(0, galeria.getUsuariosGaleria().getAdministrador().getPendientesVerificar().size(), "No se está agregando a pendientes verificar");
        assertEquals(1, galeria.getUsuariosGaleria().getAdministrador().getPendientesAceptar().size(), "No se esta agregando a pendientes aceptar");
    }
    
    public static Galeria sampleGaleria() {
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
		Video video2 = new Video("La captive", "2000-09-27","Ontario",false,
				null,(float) 201, "DVD","Ejempo",null, akerman, new HashMap<String, List<String>>(),null);
		akerman.addPieza(video);
		akerman.addPieza(video2);
		List<Artista> artistas= new ArrayList<Artista>();		
		artistas.add(akerman);artistas.add(munch);artistas.add(michelangelo);
		Externo externo1 = new Externo("Juan123","0000","Juan Ramirez", "a","b",null,null);
		Externo externo2 = new Externo("miguel123","0000","Miguel Corcho", "a","b",null,null);
		externo1.crearComprador((float)10000);
		externo1.getPropietario().addPiezaPropiedad(video);;
		externo2.getPropietario().addPiezaPropiedad(video2);
		video.setPropietario(externo1.getPropietario());
		video2.setPropietario(externo2.getPropietario());
		List<Externo> externos = new ArrayList<Externo>();externos.add(externo1);externos.add(externo2);
		InventarioGaleria inventarioGaleria = new InventarioGaleria(new ArrayList<Subasta>(), new ArrayList<Subasta>(), new  ArrayList<Venta>(), new  ArrayList<Venta>(), new ArrayList<Pieza>(),artistas);
		Administrador administrador = new Administrador("ADMIN", "0000", "a","b","c",new ArrayList<Venta>(),new ArrayList<Pieza>(), new ArrayList<Comprador>(), new ArrayList<Comprador>());
		administrador.getPiezasPorAgregar().add(video);administrador.getPiezasPorAgregar().add(pintura);administrador.getPiezasPorAgregar().add(escultura);administrador.getPiezasPorAgregar().add(video2); 
		Cajero cajero = new Cajero("CAJERO", "0000", "nombre", "correo@gmail.com", "0000000000", new ArrayList<Venta>());
		Operador operador = new Operador("OPERADOR", "0000", "nombre", "0000000000", "correo@gmail.com", new ArrayList<Oferta>());
		Galeria galeria = new Galeria( new UsuariosGaleria(administrador, cajero, operador, externos), inventarioGaleria);
		return galeria;
	}
    
}
