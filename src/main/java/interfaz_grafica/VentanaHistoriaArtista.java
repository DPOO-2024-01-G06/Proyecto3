package interfaz_grafica;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class VentanaHistoriaArtista extends JFrame {
	
	public VentanaHistoriaArtista() {
		this.setLocationRelativeTo(null);
		setSize(500, 350);
		setResizable(true);
		setTitle("Historial de artistas");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		//Panel titulo
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		add(panelTitulo, BorderLayout.NORTH);
		
		JLabel titulo = new JLabel("HISTORIAL DE ARTISTAS ");
		titulo.setFont(new Font("Garamond", Font.BOLD, 24));
		panelTitulo.add(titulo);
		
		
		//Panel mostrar indices y nombres de artistas
		JPanel panelIndices = new JPanel(new BorderLayout());
		panelIndices.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
		add(panelIndices, BorderLayout.CENTER);
	
		JTextArea texto = new JTextArea("Indice - Nombre del artista \n"
										+ "1 - Artista 1 \n"
										+ "2 - Artista 2 \n"
										+ "3 - Artista 3 \n"
										+ "4 - Artista 4 \n"
										+ "5 - Artista 5 \n"
										+ "6 - Artista 6 \n"
										+ "7 - Artista 7 \n"
										+ "8 - Artista 8 \n");
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
		VentanaHistoriaArtista ventana = new VentanaHistoriaArtista();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
}
