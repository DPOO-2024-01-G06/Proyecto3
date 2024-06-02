package interfaz_grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import galeria.Galeria;
import galeria.controller_galeria.CoordinadorSesion;
import interfaz_grafica.administrador.GUIAdmin;
import interfaz_grafica.cajero.GUICajero;
import interfaz_grafica.comprador.GUIComprador;
import interfaz_grafica.operador.GUIOp;

public class GUISesion {
	private VentanaSesion ventana = new VentanaSesion();
	private String controlador;
	private CoordinadorSesion coord;
	private Galeria galeria;
	
	public void ejecutar(Galeria galeria) {
		this.galeria = galeria;
		addButtonListener();
		ventana.mostrar();
	}
	
	public void addButtonListener() {
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = ventana.getCampo1().getText();
				String cont = ventana.getCampo2().getText();
				coord = new CoordinadorSesion(galeria,usuario,cont);
				coord.iniciarSesion();
				elegirControlador();
			}
			
		};
		ventana.getBoton().addActionListener(buttonListener);	
		}
	public void elegirControlador() {
		controlador = coord.getControladorActual();
		if(controlador.equals("ControladorAdministrador")) {
			JOptionPane.showMessageDialog(ventana, "Bienvenido, Administrador!", "Exito",JOptionPane.INFORMATION_MESSAGE);
			ventana.dispose();
			GUIAdmin nuevo = new GUIAdmin(coord.getControladorAdministrador());
			nuevo.ejecutar();
		}
		
		else if(controlador.equals("ControladorCajero")) {
			JOptionPane.showMessageDialog(ventana, "Bienvenido, Cajero!", "Exito",JOptionPane.INFORMATION_MESSAGE);
			ventana.dispose();
			GUICajero nuevo = new GUICajero(coord.getControladorCajero());
			nuevo.ejecutar();
		}
		
		else if(controlador.equals("ControladorOperador")) {
			JOptionPane.showMessageDialog(ventana, "Bienvenido, Operador!", "Exito",JOptionPane.INFORMATION_MESSAGE);
			ventana.dispose();
			GUIOp nuevo = new GUIOp(coord.getControladorOperador());
			nuevo.ejecutar();
		}
		
		else if(controlador.equals("ControladorExterno")) {
			JOptionPane.showMessageDialog(ventana, "Bienvenido, Comprador!", "Exito",JOptionPane.INFORMATION_MESSAGE);
			coord.decidirExterno(true);
			ventana.dispose();
			GUIComprador nuevo = new GUIComprador(coord.getControladorComprador());
			nuevo.ejecutar();
		}
		
		else {
			JOptionPane.showMessageDialog(ventana, "Usuario y/o contraseña incorrecto", "Error",JOptionPane.ERROR_MESSAGE);
		}

	}
	
}
