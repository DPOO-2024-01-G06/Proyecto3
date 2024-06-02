package interfaz_grafica.operador;

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
	
	public panelDatosUsuario() {
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
		btActualizarDatos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        ventana_actualizarDatos ventana_actualizarDatos = new ventana_actualizarDatos();
		        ventana_actualizarDatos.setLocationRelativeTo(null);
				ventana_actualizarDatos.setVisible(true);
		    }
		});
		add(btActualizarDatos);
		
	}

}
