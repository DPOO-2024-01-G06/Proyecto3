package interfaz_grafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import galeria.controller_galeria.ControladorGenerico;
import galeria.structurer_inventario.Artista;
import galeria.structurer_inventario.Pieza;

@SuppressWarnings("serial")
public class VentanaHistoriaArtista extends JFrame {
	@SuppressWarnings("rawtypes")
	private JComboBox lista;
	private JPanel panelOut;
	private JPanel fechaCompra;
	private JPanel fechaRealizacion;
	private JPanel costo;
	private JPanel nombre;
	
	public VentanaHistoriaArtista(ControladorGenerico controlador) {
		this.setLocationRelativeTo(null);
		setSize(600, 350);
		setResizable(true);
		setTitle("Historial de artistas");
		setLayout(new BorderLayout());
		
		//Panel titulo
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		add(panelTitulo, BorderLayout.NORTH);
		
		JLabel titulo = new JLabel("HISTORIAL DE ARTISTAS");
		titulo.setFont(new Font("Garamond", Font.BOLD, 24));
		panelTitulo.add(titulo);
		
		
		//Panel mostrar indices y nombres de artistas
		ArrayList<String> despliegue = new ArrayList<String>();
		for(Artista artista: controlador.getArtistas()) {
			despliegue.add(artista.getNombre());
		}
		JLabel texto = new JLabel("Seleccione un artista: ");
		JPanel panelIndices = new JPanel(new FlowLayout());
		panelIndices.add(texto);
		lista = new JComboBox<>(despliegue.toArray());	
		panelIndices.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
		add(panelIndices, BorderLayout.CENTER);
		addComboListener(controlador);
		panelIndices.add(lista);		
		//Panel info artistas
		panelOut = new JPanel();
		panelOut.setLayout(new GridLayout(1,4));
		panelOut.setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)),"   Piezas realizadas:  "),
				BorderFactory.createEmptyBorder(5, 60, 5, 60)
				));
		panelOut.setPreferredSize(new Dimension(600, 175));
		nombre = new JPanel();nombre.setLayout(new BoxLayout(nombre, BoxLayout.Y_AXIS));nombre.setMinimumSize(new Dimension(200,130));nombre.setBorder(new EmptyBorder
                (0, 5, 0, 5));
		fechaCompra = new JPanel();fechaCompra.setLayout(new BoxLayout(fechaCompra, BoxLayout.Y_AXIS));fechaCompra.setPreferredSize(new Dimension(150,130));fechaCompra.setBorder(new EmptyBorder
                (0, 5, 0, 5));
		fechaRealizacion = new JPanel();fechaRealizacion.setLayout(new BoxLayout(fechaRealizacion, BoxLayout.Y_AXIS));fechaRealizacion.setPreferredSize(new Dimension(150,130));fechaRealizacion.setBorder(new EmptyBorder
                (0, 5, 0,5));
		costo = new JPanel();costo.setLayout(new BoxLayout(costo, BoxLayout.Y_AXIS));costo.setPreferredSize(new Dimension(150,130));costo.setBorder(new EmptyBorder
                (0, 5, 0,5));
		panelOut.add(nombre);panelOut.add(fechaCompra);panelOut.add(fechaRealizacion);panelOut.add(costo);
		add(panelOut, BorderLayout.SOUTH);		
		setLocationRelativeTo(null);
	}
	
	public void addComboListener(ControladorGenerico controlador) {
		ActionListener comboListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fechaRealizacion.removeAll();fechaCompra.removeAll();nombre.removeAll();costo.removeAll();
				nombre.add(new JLabel("Titulo: "));
				fechaCompra.add(new JLabel("Fecha compra: "));
				costo.add(new JLabel("Costo: "));
				fechaRealizacion.add(new JLabel("Fecha: "));
				int i = lista.getSelectedIndex();
				for(Pieza pieza: controlador.getArtistas().get(i).getPiezas()) {
					nombre.add(new JLabel(pieza.getTitulo()));
					costo.add(new JLabel(Float.toString(pieza.getVenta().getPrecio())));
					fechaRealizacion.add(new JLabel(pieza.getFecha()));
					fechaCompra.add(new JLabel(pieza.getVenta().getFecha()));
				}
				panelOut.setVisible(false);
				panelOut.setVisible(true);
			}
		};
		
		lista.addActionListener(comboListener);	
		}
	
	public void mostrar() {
		setVisible(true);
	}
	
//	public static void main(String[] args) {
//		Galeria galeria = sampleGaleria();
//		ControladorAdministrador cAdmin= new ControladorAdministrador(galeria, galeria.getUsuariosGaleria().getAdministrador());
//		VentanaHistoriaArtista ventana = new VentanaHistoriaArtista(cAdmin);
//		ventana.setVisible(true);
//	}
//	
//	public static Galeria sampleGaleria() {
//		//Crear artistas y asociarles una obra
//		Artista michelangelo = new Artista("Michelangelo", new ArrayList<Pieza>());
//		Escultura escultura = new Escultura("David","1504-06-08", "Florencia", false, 
//											null, (float)517, (float)199,(float)50,(float)5660,"marmol",null, null, michelangelo,
//											new HashMap<String, List<String>>(), null);
//		michelangelo.addPieza(escultura);
//		Artista  munch= new Artista("Edvard Munch", new ArrayList<Pieza>());
//		Pintura pintura = new Pintura("The scream", "1893", "Berlin", false, null, (float) 73.5,(float)91,"oleo",
//									  null, munch, new HashMap<String, List<String>>(), null);
//		munch.addPieza(pintura);
//		Artista akerman = new Artista("Chantal Akerman", new ArrayList<Pieza>());
//		
//		Video video = new Video("Jeanne Dielman, 23 quai du Commerce, 1080 Bruxelles", "1975-01-01","Bruselas",false,
//								null,(float) 201, "DVD","Ejempo",null, akerman, new HashMap<String, List<String>>(),null);
//		Video video2 = new Video("La captive", "2000-09-27","Ontario",false,
//				null,(float) 201, "DVD","Ejempo",null, akerman, new HashMap<String, List<String>>(),null);
//		akerman.addPieza(video);
//		akerman.addPieza(video2);
//		List<Artista> artistas= new ArrayList<Artista>();		
//		artistas.add(akerman);artistas.add(munch);artistas.add(michelangelo);
//		Externo externo1 = new Externo("Juan123","0000","Juan Ramirez", "a","b",null,null);
//		Externo externo2 = new Externo("miguel123","0000","Miguel Corcho", "a","b",null,null);
//		externo1.crearComprador((float)10000);
//		externo2.getPropietario().addPiezaPropiedad(video);;
//		externo2.getPropietario().addPiezaPropiedad(video2);
//		video.setPropietario(externo2.getPropietario());
//		video2.setPropietario(externo2.getPropietario());
//		List<Externo> externos = new ArrayList<Externo>();externos.add(externo1);externos.add(externo2);
//		InventarioGaleria inventarioGaleria = new InventarioGaleria(new ArrayList<Subasta>(), new ArrayList<Subasta>(), new  ArrayList<Venta>(), new  ArrayList<Venta>(), new ArrayList<Pieza>(),artistas);
//		Administrador administrador = new Administrador("ADMIN", "0000", "a","b","c",new ArrayList<Venta>(),new ArrayList<Pieza>(), new ArrayList<Comprador>(), new ArrayList<Comprador>());
//		administrador.getPiezasPorAgregar().add(video);administrador.getPiezasPorAgregar().add(video2);administrador.getPiezasPorAgregar().add(pintura);administrador.getPiezasPorAgregar().add(escultura); 
//		Cajero cajero = new Cajero("CAJERO", "0000", "nombre", "correo@gmail.com", "0000000000", new ArrayList<Venta>());
//		Operador operador = new Operador("OPERADOR", "0000", "nombre", "0000000000", "correo@gmail.com", new ArrayList<Oferta>());
//		Galeria galeria = new Galeria( new UsuariosGaleria(administrador, cajero, operador, externos), inventarioGaleria);
//		ControladorAdministrador cAdmin = new ControladorAdministrador(galeria, administrador);
//		cAdmin.ingresarPiezaCedida(0, 1000);
//		cAdmin.ingresarPiezaCedida(0, 20000);
//		cAdmin.ingresarPiezaCedida(0, 30000);
//		cAdmin.ingresarPiezaCedida(0, 40000);	
//		return galeria;
//	}
}
