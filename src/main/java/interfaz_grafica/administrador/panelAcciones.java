package interfaz_grafica.administrador;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class panelAcciones extends JPanel {
	private JButton btPropCompra;
	private JButton btIngresar;
	private JButton btDevolver;

	private JButton btCerrarSesion;
	
	panelAcciones() {
		setBackground(new Color(153, 163, 164));
		setLayout(new GridLayout(1, 4, 30, 30));
		setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)),"Acciones"),
				BorderFactory.createEmptyBorder(40, 40, 40, 40)
				));
		
		Border bordeBotones = BorderFactory.createLineBorder(new Color(33, 47, 60), 3);
		
		btPropCompra = new JButton("<html><center> Administrar propuestas <br> de compra </center></html>");
		btPropCompra.setBackground(new Color(52, 73, 94));
		btPropCompra.setForeground(Color.WHITE);
		btPropCompra.setBorder(bordeBotones);
		add(btPropCompra);
	
		btIngresar = new JButton("<html><center> Ingresar/Rechazar una <br> pieza cedida </center></html>");
		btIngresar.setBackground(new Color(52, 73, 94));
		btIngresar.setForeground(Color.WHITE);
		btIngresar.setBorder(bordeBotones);
		add(btIngresar);
		
		btDevolver = new JButton("<html><center> Devolver una <br> pieza cedida </center></html>");
		btDevolver.setBackground(new Color(52, 73, 94));
		btDevolver.setForeground(Color.WHITE);
		btDevolver.setBorder(bordeBotones);
		add(btDevolver);
		
		
		btCerrarSesion = new JButton("<html><center> Cerrar <br> Sesión </center></html>");
		btCerrarSesion.setBackground(new Color(52, 73, 94));
		btCerrarSesion.setForeground(Color.WHITE);
		btCerrarSesion.setBorder(bordeBotones);
		add(btCerrarSesion);
	}

	public JButton getBtPropCompra() {
		return btPropCompra;
	}

	public JButton getBtIngresar() {
		return btIngresar;
	}

	public JButton getBtDevolver() {
		return btDevolver;
	}

	public JButton getBtCerrarSesion() {
		return btCerrarSesion;
	}

	
	
}
