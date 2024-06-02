package interfaz_grafica.operador;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class panelAcciones extends JPanel {
	private JButton btAgregarOferta;
	private JButton btPlanearSubasta;
	private JButton btCerrarSesion;
	
	panelAcciones() {
		setBackground(new Color(153, 163, 164));
		setLayout(new GridLayout(1, 3, 30, 30));
		setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)),"Acciones"),
				BorderFactory.createEmptyBorder(40, 40, 40, 40)
				));
		
		Border bordeBotones = BorderFactory.createLineBorder(new Color(33, 47, 60), 3);
		
		btAgregarOferta = new JButton("<html><center> Agregar oferta <br> pendiente </center></html>");
		btAgregarOferta.setBackground(new Color(52, 73, 94));
		btAgregarOferta.setForeground(Color.WHITE);
		btAgregarOferta.setBorder(bordeBotones);
		add(btAgregarOferta);
	
		btPlanearSubasta = new JButton("<html><center> Planear <br> subasta </center></html>");
		btPlanearSubasta.setBackground(new Color(52, 73, 94));
		btPlanearSubasta.setForeground(Color.WHITE);
		btPlanearSubasta.setBorder(bordeBotones);
		add(btPlanearSubasta);
		
		btCerrarSesion = new JButton("<html><center> Cerrar <br> Sesión </center></html>");
		btCerrarSesion.setBackground(new Color(52, 73, 94));
		btCerrarSesion.setForeground(Color.WHITE);
		btCerrarSesion.setBorder(bordeBotones);
		add(btCerrarSesion);
	}

	public JButton getBtAgregarOferta() {
		return btAgregarOferta;
	}

	public JButton getBtPlanearSubasta() {
		return btPlanearSubasta;
	}

	public JButton getBtCerrarSesion() {
		return btCerrarSesion;
	}
	
	
}
