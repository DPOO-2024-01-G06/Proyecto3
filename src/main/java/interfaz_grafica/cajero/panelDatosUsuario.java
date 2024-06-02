package interfaz_grafica.cajero;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class panelDatosUsuario extends JPanel {
	private JButton btActualizarDatos;
	
	panelDatosUsuario() {
		setBackground(new Color(153, 163, 164));
		setLayout(new GridLayout(1, 1, 30, 30));
		setBorder(BorderFactory.createCompoundBorder(
					new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)),"Datos de usuario"),
					BorderFactory.createEmptyBorder(40, 40, 40, 40)
					));
				
		Border bordeBotones = BorderFactory.createLineBorder(new Color(33, 47, 60), 3);
		
		btActualizarDatos = new JButton("<html><center> Actualizar <br> Datos </center></html>");
		btActualizarDatos.setBackground(new Color(52, 73, 94));
		btActualizarDatos.setForeground(Color.WHITE);
		btActualizarDatos.setBorder(bordeBotones);
		add(btActualizarDatos);
		
	}

	public JButton getBtActualizarDatos() {
		return btActualizarDatos;
	}
	


}
