package interfaz_grafica.administrador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import galeria.controller_galeria.ControladorAdministrador;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Comprador;

@SuppressWarnings("serial")
public class VentanaHistoriaCompradores extends JFrame{
		@SuppressWarnings("rawtypes")
		private JComboBox lista;
		private JPanel panelOut;
		private JPanel fechaCompra;
		private JPanel costo;
		private JPanel nombre;
		
		public VentanaHistoriaCompradores(ControladorAdministrador controlador, JFrame ventana) {
			this.setLocationRelativeTo(ventana);
			setSize(600, 350);
			setResizable(true);
			setTitle("Historial de compradores");
			setLayout(new BorderLayout());
			
			//Panel titulo
			JPanel panelTitulo = new JPanel();
			panelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
			add(panelTitulo, BorderLayout.NORTH);
			
			JLabel titulo = new JLabel("HISTORIAL DE COMPRADORES");
			titulo.setFont(new Font("Garamond", Font.BOLD, 24));
			panelTitulo.add(titulo);
			
			
			//Panel mostrar indices y nombres de compradores
			ArrayList<String> despliegue = new ArrayList<String>();
			for(Comprador comprador: controlador.getCompradores()) {
				despliegue.add(comprador.getExterno().getNombre());
			}
			JLabel texto = new JLabel("Seleccione un comprador: ");
			JPanel panelIndices = new JPanel(new FlowLayout());
			panelIndices.add(texto);
			lista = new JComboBox<>(despliegue.toArray());	
			panelIndices.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
			add(panelIndices, BorderLayout.CENTER);
			addComboListener(controlador);
			panelIndices.add(lista);		
			//Panel info compradores
			panelOut = new JPanel();
			panelOut.setLayout(new GridLayout(1,3));
			panelOut.setBorder(BorderFactory.createCompoundBorder(
					new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)),"   Informacion Compras:  "),
					BorderFactory.createEmptyBorder(5, 60, 5, 60)
					));
			panelOut.setPreferredSize(new Dimension(600, 175));
			nombre = new JPanel();nombre.setLayout(new BoxLayout(nombre, BoxLayout.Y_AXIS));nombre.setMinimumSize(new Dimension(200,130));nombre.setBorder(new EmptyBorder
	                (0, 5, 0, 5));
			fechaCompra = new JPanel();fechaCompra.setLayout(new BoxLayout(fechaCompra, BoxLayout.Y_AXIS));fechaCompra.setPreferredSize(new Dimension(150,130));fechaCompra.setBorder(new EmptyBorder
	                (0, 5, 0, 5));
			costo = new JPanel();costo.setLayout(new BoxLayout(costo, BoxLayout.Y_AXIS));costo.setPreferredSize(new Dimension(150,130));costo.setBorder(new EmptyBorder
	                (0, 5, 0,5));
			panelOut.add(nombre);panelOut.add(fechaCompra);panelOut.add(costo);
			add(panelOut, BorderLayout.SOUTH);		
			setLocationRelativeTo(null);
		}
		
		public void addComboListener(ControladorAdministrador controlador) {
			ActionListener comboListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					fechaCompra.removeAll();nombre.removeAll();costo.removeAll();
					nombre.add(new JLabel("Titulo: "));
					fechaCompra.add(new JLabel("Fecha compra: "));
					costo.add(new JLabel("Costo: "));
					int i = lista.getSelectedIndex();
					for(Venta venta: controlador.getCompradores().get(i).getPiezasCompradas()) {
						Pieza pieza = venta.getPieza();
						nombre.add(new JLabel(pieza.getTitulo()));
						costo.add(new JLabel(Float.toString(pieza.getVenta().getPrecio())));
						fechaCompra.add(new JLabel(pieza.getVenta().getFecha()));
					}
					JLabel vCol = new JLabel("Valor de la colección: " + controlador.getCompradores().get(i).getValorColeccion());
					nombre.add(vCol);
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

