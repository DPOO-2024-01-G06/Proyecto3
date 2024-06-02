package interfaz_grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import galeria.controller_galeria.ControladorGenerico;
import galeria.structurer_inventario.Pieza;

@SuppressWarnings("serial")
public class VentanaHistoriaPieza extends JFrame{
	@SuppressWarnings("rawtypes")
	private JComboBox lista;
	private JPanel panelOut;
	private JPanel nombre;
	private JPanel costo;
	private JPanel fecha;
	public VentanaHistoriaPieza(ControladorGenerico controlador) {
		this.setLocationRelativeTo(null);
		setSize(500, 350);
		setResizable(true);
		setTitle("Historial de piezas");
		setLayout(new BorderLayout());
		
		//Panel titulo
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		add(panelTitulo, BorderLayout.NORTH);
		
		JLabel titulo = new JLabel("HISTORIAL DE PIEZAS");
		titulo.setFont(new Font("Garamond", Font.BOLD, 24));
		panelTitulo.add(titulo);
		
		
		//Panel mostrar indices y nombres de piezas
		ArrayList<String> despliegue = new ArrayList<String>();
		for(Pieza pieza: controlador.getListaPiezas()) {
			despliegue.add(pieza.getTitulo());
		}
		JLabel texto = new JLabel("Seleccione una pieza: ");
		JPanel panelIndices = new JPanel(new FlowLayout());
		panelIndices.add(texto);
		lista = new JComboBox<>(despliegue.toArray());	
		panelIndices.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
		add(panelIndices, BorderLayout.CENTER);
		addComboListener(controlador);
		panelIndices.add(lista);		
		//Panel info artistas
		panelOut = new JPanel();
		panelOut.setLayout(new GridLayout(1,3));
		panelOut.setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)),"   Historial de propietarios:  "),
				BorderFactory.createEmptyBorder(5, 60, 5, 60)
				));
		panelOut.setPreferredSize(new Dimension(400, 175));
		nombre = new JPanel();nombre.setLayout(new BoxLayout(nombre, BoxLayout.Y_AXIS));nombre.setPreferredSize(new Dimension(150,130));nombre.setBorder(new EmptyBorder
                (0, 5, 0, 5));
		fecha = new JPanel();fecha.setLayout(new BoxLayout(fecha, BoxLayout.Y_AXIS));fecha.setPreferredSize(new Dimension(150,130));fecha.setBorder(new EmptyBorder
                (0, 5, 0, 5));
		costo = new JPanel();costo.setLayout(new BoxLayout(costo, BoxLayout.Y_AXIS));costo.setPreferredSize(new Dimension(150,130));costo.setBorder(new EmptyBorder
                (0, 5, 0,5));
		panelOut.add(nombre);panelOut.add(fecha);panelOut.add(costo);
		add(panelOut, BorderLayout.SOUTH);		
		setLocationRelativeTo(null);
	}
	
	public void mostrar() {
		setVisible(true);
	}
	
	public void addComboListener(ControladorGenerico controlador) {
		ActionListener comboListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fecha.removeAll();nombre.removeAll();costo.removeAll();
				nombre.add(new JLabel("Propietario: "));
				fecha.add(new JLabel("Fecha de compra: "));
				costo.add(new JLabel("Costo de compra: "));
				int i = lista.getSelectedIndex();
				Map<String, List<String>> historial = controlador.getListaPiezas().get(i).getHistorialDuenos();
				for(String prop: historial.keySet()) {
					nombre.add(new JLabel(prop));
					costo.add(new JLabel(historial.get(prop).get(0)));
					fecha.add(new JLabel(historial.get(prop).get(1)));
				}
				nombre.repaint();
				fecha.repaint();
				costo.repaint();
				panelOut.setVisible(false);
				panelOut.setVisible(true);
			}
		};
		
		lista.addActionListener(comboListener);	
		}
}
