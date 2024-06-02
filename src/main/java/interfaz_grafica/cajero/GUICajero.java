package interfaz_grafica.cajero;

import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import galeria.controller_galeria.ControladorCajero;
import galeria.controller_galeria.ControladorGenerico;
import interfaz_grafica.GUIUsuario;
import interfaz_grafica.cajero.VentanaRegistrarPago;


@SuppressWarnings("serial")
public class GUICajero extends GUIUsuario {
	private panelDatosUsuario panelDU;
	private panelHistorial panelH;
	private panelAcciones panelA;
	private JPanel panelI;
	
	public GUICajero(ControladorCajero cCajero) {
		setSize(900, 600);
		setResizable(true);
		setTitle("Interfaz Cajero");
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
		
		this.setLocationRelativeTo(null);
		
		//Acciones de los botones
		setActionActualizarInfo(cCajero,panelDU.getBtActualizarDatos());
		setActionCerrarSesion(panelA.getBtCerrarSesion(), this, cCajero.getGaleria());
		setActionHistoriaPieza(panelH.getBtHistoriaPieza(),this,cCajero);
		setActionHistoriaArtista(panelH.getBtHistoriaArtista(),this,cCajero);
		setActionRegistrarPago(panelA.getBtPago(),this,cCajero);
	}
	
	public void ejecutar() {
		this.setVisible(true);
	}
	
	public void setPanelImagen(){
		panelI = new JPanel();
    	panelI.setBackground(new Color(153, 163, 164));
		panelI.setLayout(new GridLayout(1, 1, 30, 30));
		panelI.setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60))),
				BorderFactory.createEmptyBorder(8, 8, 8, 8)
				));
		
		ImageIcon imagenIcon = new ImageIcon("Imagenes/BannerCajero.png");
        JLabel imagenLabel = new JLabel(imagenIcon);
        panelI.add(imagenLabel);
    }
	public void setActionRegistrarPago(JButton boton, JFrame ventana, ControladorCajero cCajero) {
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaRegistrarPago nuevo = new VentanaRegistrarPago(cCajero);
				nuevo.setLocationRelativeTo(null);
				nuevo.mostrar();
			}
		};
		boton.addActionListener(buttonListener);
	}
}
