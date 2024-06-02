package interfaz_grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class VentanaHistoriaPieza extends JFrame{
	
	public VentanaHistoriaPieza() {
		this.setLocationRelativeTo(null);
		setSize(500, 350);
		setResizable(true);
		setTitle("Historial de piezas");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		//Panel titulo
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		add(panelTitulo, BorderLayout.NORTH);
		
		JLabel titulo = new JLabel("HISTORIAL DE PIEZAS");
		titulo.setFont(new Font("Garamond", Font.BOLD, 24));
		panelTitulo.add(titulo);
		
		
		//Panel mostrar indices y nombres de piezas
		JPanel panelIndices = new JPanel(new BorderLayout());
		panelIndices.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
		add(panelIndices, BorderLayout.CENTER);
	
		JTextArea texto = new JTextArea("Indice - Nombre de la pieza \n"
										+ "1 - Pieza 1 \n"
										+ "2 - Pieza 2 \n"
										+ "3 - Pieza 3 \n"
										+ "4 - Pieza 4 \n"
										+ "5 - Pieza 5 \n"
										+ "6 - Pieza 6 \n"
										+ "7 - Pieza 7 \n"
										+ "8 - Pieza 8 \n");
		texto.setLineWrap(true);
		texto.setWrapStyleWord(true);
		texto.setFocusable(false);
		texto.getCaret().setSelectionVisible(false);
		
		JScrollPane scrollPane = new JScrollPane(texto);
		panelIndices.add(scrollPane);
		
		//Panel boton busqueda
		JPanel panelInputs = new JPanel();
		panelInputs.setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)),"   Indice a buscar:  "),
				BorderFactory.createEmptyBorder(5, 60, 5, 60)
				));
		panelInputs.setLayout(new GridLayout(2,1, 10, 10));
		panelInputs.setPreferredSize(new Dimension(400, 100));
		add(panelInputs, BorderLayout.SOUTH);
		
		JTextField indice = new JTextField(40);
		panelInputs.add(indice);
		
		JButton btBuscar = new JButton("<html><center> Buscar </center></html>");
		btBuscar.setForeground(Color.WHITE);
		btBuscar.setBackground(new Color(52, 73, 94));
		btBuscar.setBorder(BorderFactory.createLineBorder(new Color(33, 47, 60), 3));
		panelInputs.add(btBuscar);
				
		
	}
	
	public static void main(String[] args) {
		VentanaHistoriaPieza ventana = new VentanaHistoriaPieza();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

}
