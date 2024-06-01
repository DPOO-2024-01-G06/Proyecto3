package interfaz_grafica.operador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class interfaz_general extends JFrame {
	private panelDatosUsuario panelDU;
	private panelHistorial panelH;
	private panelAcciones panelA;
	private panelImagen panelI;
	
	public interfaz_general() {
		setSize(900, 600);
		setResizable(true);
		setTitle("Interfaz Operador");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		setLayout (new GridLayout(4, 1));
		
		panelI = new panelImagen();
        add(panelI);
		
		panelDU= new panelDatosUsuario();
		add(panelDU);
		
		panelH = new panelHistorial();
		add(panelH);
		
		panelA = new panelAcciones();
		add(panelA);
		
	}
	
	public static void main(String[] args) {
		interfaz_general principal = new interfaz_general();
		principal.setLocationRelativeTo(null);
		principal.setVisible(true);
	}
}
