package interfaz_grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import galeria.Galeria;
import galeria.controller_galeria.ControladorGenerico;
import persistencia.PersistenciaGaleria;

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
	
	public void setActionCerrarSesion(JButton boton, JFrame ventana, Galeria galeria) {
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(ventana, "Galeria guardada exitosamente", "Salir",JOptionPane.INFORMATION_MESSAGE);
				ventana.dispose();
				new PersistenciaGaleria().guardarGaleria(galeria);
			}
		};
		boton.addActionListener(buttonListener);
	}
	
	public void setActionHistoriaPieza(JButton boton, JFrame ventana, ControladorGenerico controlador) {
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaHistoriaPieza nuevo = new VentanaHistoriaPieza(controlador);
				nuevo.setLocationRelativeTo(null);
				nuevo.mostrar();
			}
		};
		boton.addActionListener(buttonListener);
	}
	
	public void setActionHistoriaArtista(JButton boton, JFrame ventana, ControladorGenerico controlador) {
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaHistoriaArtista nuevo = new VentanaHistoriaArtista(controlador);
				nuevo.setLocationRelativeTo(null);
				nuevo.mostrar();
			}
		};
		boton.addActionListener(buttonListener);
	}
}
