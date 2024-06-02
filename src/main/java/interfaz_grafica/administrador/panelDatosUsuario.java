package interfaz_grafica.administrador;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import interfaz_grafica.ventana_actualizarDatos;

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
		btActualizarDatos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        ventana_actualizarDatos ventana_actualizarDatos = new ventana_actualizarDatos();
		        ventana_actualizarDatos.setLocationRelativeTo(null);
				ventana_actualizarDatos.setVisible(true);
		    }
		});
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

}
