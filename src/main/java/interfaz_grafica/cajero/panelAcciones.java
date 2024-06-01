package interfaz_grafica.cajero;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class panelAcciones extends JPanel {
	private JButton btPago;
	private JButton btCerrarSesion;
	
	public panelAcciones() {
		setBackground(new Color(153, 163, 164));
		setLayout(new GridLayout(1, 2, 30, 30));
		setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)),"Acciones"),
				BorderFactory.createEmptyBorder(40, 40, 40, 40)
				));
		
		Border bordeBotones = BorderFactory.createLineBorder(new Color(33, 47, 60), 3);
		
		btPago = new JButton("<html><center> Registrar / Denegar <br> un pago </center></html>");
		btPago.setBackground(new Color(52, 73, 94));
		btPago.setForeground(Color.WHITE);
		btPago.setBorder(bordeBotones);
		add(btPago);
		
		btCerrarSesion = new JButton("<html><center> Cerrar <br> Sesión </center></html>");
		btCerrarSesion.setBackground(new Color(52, 73, 94));
		btCerrarSesion.setForeground(Color.WHITE);
		btCerrarSesion.setBorder(bordeBotones);
		add(btCerrarSesion);
	}
}
