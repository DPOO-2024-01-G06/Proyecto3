package pruebas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test

;

import galeria.Galeria;
import galeria.controller_galeria.ControladorOperador;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OperadorTest {
	private Galeria galeria;
	private ControladorOperador cOperador;
	@BeforeEach
	public void setUp() {
	galeria = SampleGaleria();
	cOperador = new ControladorOperador(galeria, galeria.getUsuariosGaleria().getOperador());
		}
    @Test
    void testAgregarOfertaPendiente() {
    	Pieza pieza=galeria.getUsuariosGaleria().getAdministrador().getPiezasPorAgregar().get(0);
        Subasta subasta=new Subasta(0, 0,new ArrayList<Oferta>(), null, pieza, null);
        galeria.getInventarioGaleria().getSubastasPendientes().put(1, subasta);
        Oferta oferta= new Oferta(10, null, subasta);
        cOperador.getOperador().getOfertasPendientes().add(oferta);
        cOperador.agregarOfertaPendiente(0);

        assertEquals(0, cOperador.getOfertasPendientes().size(),"No se actualiza la lista de operador");
        assertEquals(1, subasta.getOfertas().size(),"No se agrega la oferta a la subasta");
    }
    @Test
    void testPlanearSubasta() {
    	Pieza pieza=galeria.getUsuariosGaleria().getAdministrador().getPiezasPorAgregar().get(0);
        Venta venta=new Venta(5165, false, false, pieza, null, null);
        cOperador.planearSubasta(venta, 0, null, null, 0);
        assertEquals(1, galeria.getInventarioGaleria().getSubastasPendientes().values().size(),"No se actualiza la lista de operador");
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