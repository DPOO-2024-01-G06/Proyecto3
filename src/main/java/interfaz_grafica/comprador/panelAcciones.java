package interfaz_grafica.comprador;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class panelAcciones extends JPanel {
	private JButton btOfertar;
	private JButton btComprar;
	private JButton btCerrarSesion;
	
	panelAcciones() {
		setBackground(new Color(153, 163, 164));
		setLayout(new GridLayout(1, 3, 30, 30));
		setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)),"Acciones"),
				BorderFactory.createEmptyBorder(40, 40, 40, 40)
				));
		
		Border bordeBotones = BorderFactory.createLineBorder(new Color(33, 47, 60), 3);
		
		btOfertar = new JButton("<html><center> Realizar oferta <br> en subasta </center></html>");
		btOfertar.setBackground(new Color(52, 73, 94));
		btOfertar.setForeground(Color.WHITE);
		btOfertar.setBorder(bordeBotones);
		add(btOfertar);
	
		btComprar = new JButton("<html><center> Relizar una <br> compra </center></html>");
		btComprar.setBackground(new Color(52, 73, 94));
		btComprar.setForeground(Color.WHITE);
		btComprar.setBorder(bordeBotones);
		add(btComprar);
		
		btCerrarSesion = new JButton("<html><center> Cerrar <br> Sesión </center></html>");
		btCerrarSesion.setBackground(new Color(52, 73, 94));
		btCerrarSesion.setForeground(Color.WHITE);
		btCerrarSesion.setBorder(bordeBotones);
		add(btCerrarSesion);
	}

	public JButton getBtOfertar() {
		return btOfertar;
	}

	public JButton getBtComprar() {
		return btComprar;
	}

	public JButton getBtCerrarSesion() {
		return btCerrarSesion;
	}
	
	
	
}
