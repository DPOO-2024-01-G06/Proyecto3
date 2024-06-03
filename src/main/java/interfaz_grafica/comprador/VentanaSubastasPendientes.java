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
import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;

public class VentanaSubastasPendientes extends JFrame{
	private JComboBox lista;
	private JPanel panelOut;
	private JPanel nombre;
	private JPanel valorInicial;
	private JPanel ofertaMax;
	private JPanel fechaLimite;
		
	public VentanaSubastasPendientes(ControladorComprador controlador) {
		this.setLocationRelativeTo(null);
		setSize(600, 350);
		setResizable(false);
		setTitle("Piezas compradas");
		setLayout(new BorderLayout());
		
		//Panel titulo
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		add(panelTitulo, BorderLayout.NORTH);
		
		JLabel titulo = new JLabel("SUBASTAS PENDIENTES");
		titulo.setFont(new Font("Garamond", Font.BOLD, 24));
		panelTitulo.add(titulo);
		
		
		//Panel mostrar indices y nombres de artistas
		ArrayList<String> despliegue = new ArrayList<String>();
		despliegue.add("-----------");
		for(Subasta subasta: controlador.getComprador().getSubastasPendientes()) {
			despliegue.add(subasta.getPieza().getTitulo());
		}
		
		JLabel texto = new JLabel("Seleccione la pieza en subasta: ");
		JPanel panelIndices = new JPanel(new FlowLayout());
		panelIndices.add(texto);
		
		lista = new JComboBox<>(despliegue.toArray());	
		panelIndices.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
		add(panelIndices, BorderLayout.CENTER);
		addComboListener(controlador);
		panelIndices.add(lista);
		
		//Panel informacion de piezas
		panelOut = new JPanel();
		panelOut.setLayout(new GridLayout(1,4));
		panelOut.setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)),"   Información de subasta:  "),
				BorderFactory.createEmptyBorder(5, 60, 5, 60)
				));
		panelOut.setPreferredSize(new Dimension(600, 175));
		
		nombre = new JPanel();
		nombre.setLayout(new BoxLayout(nombre, BoxLayout.Y_AXIS));
		nombre.setMinimumSize(new Dimension(200,130));
		nombre.setBorder(new EmptyBorder(0, 5, 0, 5));
		
		valorInicial = new JPanel();
		valorInicial.setLayout(new BoxLayout(valorInicial, BoxLayout.Y_AXIS));
		valorInicial.setPreferredSize(new Dimension(150,130));
		valorInicial.setBorder(new EmptyBorder(0, 5, 0,5));
		
		ofertaMax = new JPanel();
		ofertaMax.setLayout(new BoxLayout(ofertaMax, BoxLayout.Y_AXIS));
		ofertaMax.setPreferredSize(new Dimension(150,130));
		ofertaMax.setBorder(new EmptyBorder(0, 5, 0,5));
		
		fechaLimite = new JPanel();
		fechaLimite.setLayout(new BoxLayout(fechaLimite, BoxLayout.Y_AXIS));
		fechaLimite.setPreferredSize(new Dimension(150,130));
		fechaLimite.setBorder(new EmptyBorder(0, 5, 0, 5));
		
		panelOut.add(nombre);
		panelOut.add(valorInicial);
		panelOut.add(ofertaMax);
		panelOut.add(fechaLimite);
		
		add(panelOut, BorderLayout.SOUTH);		
		setLocationRelativeTo(null);
	}
	
	public void addComboListener(ControladorComprador controlador) {
		ActionListener comboListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nombre.removeAll();
				valorInicial.removeAll();
				ofertaMax.removeAll();
				fechaLimite.removeAll();
				
				nombre.add(new JLabel("Titulo: "));
				ofertaMax.add(new JLabel("Oferta máxima: "));
				valorInicial.add(new JLabel("Valor inicial: "));
				fechaLimite.add(new JLabel("Fecha límite: "));
				
				for(Subasta subasta: controlador.getComprador().getSubastasPendientes()) {
					nombre.add(new JLabel(subasta.getPieza().getTitulo()));
					ofertaMax.add(new JLabel(Double.toString(subasta.getOfertaMaxima().getValor())));
					valorInicial.add(new JLabel(Float.toString(subasta.getValorInicial())));
					fechaLimite.add(new JLabel(subasta.getLimiteTiempo()));
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

