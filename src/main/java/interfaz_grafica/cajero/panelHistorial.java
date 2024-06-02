package interfaz_grafica.cajero;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class panelHistorial extends JPanel{
	private JButton btHistoriaArtista;
	private JButton btPieza;
	
	
	public panelHistorial() {
		setBackground(new Color(153, 163, 164));
		setLayout(new GridLayout(1, 2, 30, 30));
		setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)),"Historiales"),
				BorderFactory.createEmptyBorder(40, 40, 40, 40)
				));
		
		Border bordeBotones = BorderFactory.createLineBorder(new Color(33, 47, 60), 3);
	
		btHistoriaArtista = new JButton("<html><center> Buscar historial <br> de un artista </center></html>");
		btHistoriaArtista.setBackground(new Color(52, 73, 94));
		btHistoriaArtista.setForeground(Color.WHITE);
		btHistoriaArtista.setBorder(bordeBotones);
		add(btHistoriaArtista);
		
		btPieza = new JButton("<html><center> Buscar pieza <br> en la galería </center></html>");
		btPieza.setBackground(new Color(52, 73, 94));
		btPieza.setForeground(Color.WHITE);
		btPieza.setBorder(bordeBotones);
		add(btPieza);
	}
	
}