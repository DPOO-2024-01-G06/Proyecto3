package interfaz_grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import galeria.controller_galeria.ControladorGenerico;

@SuppressWarnings("serial")
public class GUIUsuario extends JFrame {
	
	public void setActionActualizarInfo(ControladorGenerico controlador, JButton boton){
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaActualizacion nuevo = new VentanaActualizacion(controlador);
				nuevo.setLocationRelativeTo(null);
				nuevo.mostrar();
			}
		};
		boton.addActionListener(buttonListener);
	}
}
