package interfaz_grafica.comprador;

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
	private JButton btValorMax;
	private JButton btVerificacion;
	private JButton btPiezasCompradas;
	private JButton btSubastasPendientes;
	
	panelDatosUsuario() {
		setBackground(new Color(153, 163, 164));
		setLayout(new GridLayout(1, 5, 30, 30));
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
		
		btValorMax = new JButton("<html><center> Ver valor <br> máximo </center></html>");
		btValorMax.setBackground(new Color(52, 73, 94));
		btValorMax.setForeground(Color.WHITE);
		btValorMax.setBorder(bordeBotones);
		add(btValorMax);
		
		btVerificacion = new JButton("<html><center> Estado de <br> verificación </center></html>");
		btVerificacion.setBackground(new Color(52, 73, 94));
		btVerificacion.setForeground(Color.WHITE);
		btVerificacion.setBorder(bordeBotones);
		add(btVerificacion);
		
		btPiezasCompradas = new JButton("<html><center> Ver piezas <br> compradas </center></html>");
		btPiezasCompradas.setBackground(new Color(52, 73, 94));
		btPiezasCompradas.setForeground(Color.WHITE);
		btPiezasCompradas.setBorder(bordeBotones);
		add(btPiezasCompradas);
		
		btSubastasPendientes = new JButton("<html><center>Ver subastas pendientes</center></html>");
		btSubastasPendientes.setHorizontalAlignment(JButton.CENTER);
		btSubastasPendientes.setBackground(new Color(52, 73, 94));
		btSubastasPendientes.setForeground(Color.WHITE);
		btSubastasPendientes.setBorder(bordeBotones);
		add(btSubastasPendientes);
		
	}

	public JButton getBtActualizarDatos() {
		return btActualizarDatos;
	}

	public JButton getBtValorMax() {
		return btValorMax;
	}

	public JButton getBtVerificacion() {
		return btVerificacion;
	}

	public JButton getBtPiezasCompradas() {
		return btPiezasCompradas;
	}

	public JButton getBtSubastasPendientes() {
		return btSubastasPendientes;
	}
	
	

}
