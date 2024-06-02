package interfaz_grafica.administrador;

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
	private JButton btVerificacion;
	private JButton btActualizarVMax;
	
	public panelDatosUsuario() {
		setBackground(new Color(153, 163, 164));
		setLayout(new GridLayout(1, 3, 30, 30));
		setBorder(BorderFactory.createCompoundBorder(
					new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)),"Datos de usuarios"),
					BorderFactory.createEmptyBorder(40, 40, 40, 40)
					));
				
		Border bordeBotones = BorderFactory.createLineBorder(new Color(33, 47, 60), 3);
		
		btActualizarDatos = new JButton("<html><center> Actualizar <br> Datos </center></html>");
		btActualizarDatos.setBackground(new Color(52, 73, 94));
		btActualizarDatos.setForeground(Color.WHITE);
		btActualizarDatos.setBorder(bordeBotones);
		add(btActualizarDatos);
		
		btVerificacion = new JButton("<html><center> Verificar / Invalidar <br> comprador </center></html>");
		btVerificacion.setBackground(new Color(52, 73, 94));
		btVerificacion.setForeground(Color.WHITE);
		btVerificacion.setBorder(bordeBotones);
		add(btVerificacion);
		
		btActualizarVMax = new JButton("<html><center> Actualizar valor <br> máximo de un comprador </center></html>");
		btActualizarVMax.setBackground(new Color(52, 73, 94));
		btActualizarVMax.setForeground(Color.WHITE);
		btActualizarVMax.setBorder(bordeBotones);
		add(btActualizarVMax);
	
	}

	public JButton getBtActualizarDatos() {
		return btActualizarDatos;
	}

	public JButton getBtVerificacion() {
		return btVerificacion;
	}

	public JButton getBtActualizarVMax() {
		return btActualizarVMax;
	}
	
	
	

}
