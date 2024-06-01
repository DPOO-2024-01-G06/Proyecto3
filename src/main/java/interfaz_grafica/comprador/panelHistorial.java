package interfaz_grafica.comprador;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class panelHistorial extends JPanel{
	private JButton btHistoriaPieza;
	private JButton btHistoriaArtista;
	
	
	public panelHistorial() {
		setBackground(new Color(153, 163, 164));
		setLayout(new GridLayout(1, 2, 30, 30));
		setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)),"Historiales"),
				BorderFactory.createEmptyBorder(40, 40, 40, 40)
				));
		
		Border bordeBotones = BorderFactory.createLineBorder(new Color(33, 47, 60), 3);
		
		btHistoriaPieza = new JButton("<html><center> Buscar historial <br> de una pieza </center></html>");
		btHistoriaPieza.setBackground(new Color(52, 73, 94));
		btHistoriaPieza.setForeground(Color.WHITE);
		btHistoriaPieza.setBorder(bordeBotones);
		add(btHistoriaPieza);
	
		btHistoriaArtista = new JButton("<html><center> Buscar historial <br> de un artista </center></html>");
		btHistoriaArtista.setBackground(new Color(52, 73, 94));
		btHistoriaArtista.setForeground(Color.WHITE);
		btHistoriaArtista.setBorder(bordeBotones);
		add(btHistoriaArtista);
	}
	
}
