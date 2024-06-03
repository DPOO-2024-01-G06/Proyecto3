package interfaz_grafica.comprador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import galeria.controller_galeria.ControladorComprador;
import galeria.controller_galeria.ControladorGenerico;
import galeria.structurer_inventario.Artista;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Venta;

public class VentanaPiezasCompradas extends JFrame {
	private JComboBox lista;
	private JPanel panelOut;
	private JPanel fechaCompra;
	private JPanel costo;
	private JPanel nombre;
		
	public VentanaPiezasCompradas(ControladorComprador controlador) {
		this.setLocationRelativeTo(null);
		setSize(600, 350);
		setResizable(false);
		setTitle("Piezas compradas");
		setLayout(new BorderLayout());
		
		//Panel titulo
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		add(panelTitulo, BorderLayout.NORTH);
		
		JLabel titulo = new JLabel("PIEZAS COMPRADAS");
		titulo.setFont(new Font("Garamond", Font.BOLD, 24));
		panelTitulo.add(titulo);
		
		
		//Panel mostrar indices y nombres de artistas
		ArrayList<String> despliegue = new ArrayList<String>();
		despliegue.add("-----------");
		for(Venta venta: controlador.getComprador().getPiezasCompradas()) {
			despliegue.add(venta.getPieza().getTitulo());
		}
		
		JLabel texto = new JLabel("Seleccione una pieza: ");
		JPanel panelIndices = new JPanel(new FlowLayout());
		panelIndices.add(texto);
		
		lista = new JComboBox<>(despliegue.toArray());	
		panelIndices.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
		add(panelIndices, BorderLayout.CENTER);
		addComboListener(controlador);
		panelIndices.add(lista);
		
		//Panel informacion de piezas
		panelOut = new JPanel();
		panelOut.setLayout(new GridLayout(1,3));
		panelOut.setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)),"   Información de la pieza :  "),
				BorderFactory.createEmptyBorder(5, 60, 5, 60)
				));
		panelOut.setPreferredSize(new Dimension(600, 175));
		
		nombre = new JPanel();
		nombre.setLayout(new BoxLayout(nombre, BoxLayout.Y_AXIS));
		nombre.setMinimumSize(new Dimension(200,130));
		nombre.setBorder(new EmptyBorder(0, 5, 0, 5));
		
		fechaCompra = new JPanel();
		fechaCompra.setLayout(new BoxLayout(fechaCompra, BoxLayout.Y_AXIS));
		fechaCompra.setPreferredSize(new Dimension(150,130));
		fechaCompra.setBorder(new EmptyBorder(0, 5, 0, 5));
		
		costo = new JPanel();
		costo.setLayout(new BoxLayout(costo, BoxLayout.Y_AXIS));
		costo.setPreferredSize(new Dimension(150,130));
		costo.setBorder(new EmptyBorder(0, 5, 0,5));
		
		panelOut.add(nombre);
		panelOut.add(fechaCompra);
		panelOut.add(costo);
		
		add(panelOut, BorderLayout.SOUTH);		
		setLocationRelativeTo(null);
	}
	
	public void addComboListener(ControladorComprador controlador) {
		ActionListener comboListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fechaCompra.removeAll();
				nombre.removeAll();
				costo.removeAll();
				
				nombre.add(new JLabel("Titulo: "));
				fechaCompra.add(new JLabel("Fecha de compra: "));
				costo.add(new JLabel("Costo: "));
				
				for(Venta venta: controlador.getComprador().getPiezasCompradas()) {
					
					nombre.add(new JLabel(venta.getPieza().getTitulo()));
					costo.add(new JLabel(Float.toString(venta.getPrecio())));
					fechaCompra.add(new JLabel(venta.getFecha()));
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


}
