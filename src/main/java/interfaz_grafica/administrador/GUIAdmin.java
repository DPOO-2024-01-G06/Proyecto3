package interfaz_grafica.administrador;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import galeria.controller_galeria.ControladorAdministrador;
import interfaz_grafica.GUIUsuario;

@SuppressWarnings("serial")
public class GUIAdmin extends GUIUsuario {
		private panelDatosUsuario panelDU;
		private panelHistorial panelH;
		private panelAcciones panelA;
		private JPanel panelI;
		
	
	public GUIAdmin(ControladorAdministrador cAdmin) {	
		setSize(900, 600);
		setResizable(true);
		setTitle("Interfaz Adminstrador");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		setLayout (new GridLayout(4, 1));
		
		setPanelImagen();
        add(panelI);
		
		panelDU= new panelDatosUsuario();
		add(panelDU);
		
		panelH = new panelHistorial();
		add(panelH);
		
		panelA = new panelAcciones();
		add(panelA);
		setLocationRelativeTo(null);
		
		//Acciones de los botones
		setActionActualizarInfo(cAdmin,panelDU.getBtActualizarDatos());
		setActionCerrarSesion(panelA.getBtCerrarSesion(), this, cAdmin.getGaleria());
	}
	public void ejecutar() {
		this.setVisible(true);
	}
	
	
	public void setPanelImagen() {
		panelI = new JPanel();
    	panelI.setBackground(new Color(153, 163, 164));
		panelI.setLayout(new GridLayout(1, 2, 30, 30));
		panelI.setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60))),
				BorderFactory.createEmptyBorder(8, 8, 8, 8)
				));
		
		ImageIcon imagenIcon = new ImageIcon("Imagenes/BannerAdmin.png");
        JLabel imagenLabel = new JLabel(imagenIcon);
        panelI.add(imagenLabel);
    }
	
	
	
	
	
	
}
