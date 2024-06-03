package interfaz_grafica.comprador;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import galeria.controller_galeria.ControladorComprador;
import galeria.controller_galeria.ControladorGenerico;
import interfaz_grafica.GUIUsuario;
import interfaz_grafica.VentanaActualizacion;
import interfaz_grafica.VentanaHistoriaPieza;

@SuppressWarnings("serial")
public class GUIComprador extends GUIUsuario {
	private panelDatosUsuario panelDU;
	private panelHistorial panelH;
	private panelAcciones panelA;
	private JPanel panelI;
	
	public GUIComprador(ControladorComprador cComprador) {
		setSize(900, 600);
		setResizable(true);
		setTitle("Interfaz Comprador");
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
		
		//Acciones botones panel datos usuario
		setActionActualizarInfo(cComprador,panelDU.getBtActualizarDatos());
		setActionValorMax(cComprador, panelDU.getBtValorMax());
		setActionVerificacio(cComprador, panelDU.getBtVerificacion());
		setActionPiezasCompradas(panelDU.getBtPiezasCompradas(), this, cComprador);
		setActionSubastasPendientes(panelDU.getBtSubastasPendientes(), this, cComprador);
		
		//Acciones botones panel historiales
		setActionHistoriaPieza(panelH.getBtHistoriaPieza(),this,cComprador);
		setActionHistoriaArtista(panelH.getBtHistoriaArtista(),this,cComprador);
		
		//Acciones botones panel acciones
		setActionCerrarSesion(panelA.getBtCerrarSesion(), this, cComprador.getGaleria());

		
	}
	
	public void setActionValorMax(ControladorComprador cComp, JButton boton){
		float vMax = cComp.getComprador().getValorMaximo();
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Su valor máximo para ofertar es:  " + vMax + " $");
			}
		};
		
		boton.addActionListener(buttonListener);
	}
	
	public void setActionVerificacio(ControladorComprador cComp, JButton boton){
		boolean verificado = cComp.getComprador().getVerificado();
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String estado = "";
				if (verificado == false) {estado = "No verificado";}
				else {estado = "Verificado";}
				
				JOptionPane.showMessageDialog(null, "Estado:  " + estado);
			} 
		};
		
		boton.addActionListener(buttonListener);
	}
	
	public void setActionPiezasCompradas(JButton boton, JFrame ventana, ControladorComprador controlador) {
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPiezasCompradas ventana = new VentanaPiezasCompradas(controlador);
				ventana.setLocationRelativeTo(null);
				ventana.mostrar();
			}
		};
		boton.addActionListener(buttonListener);
	}
	
	public void setActionSubastasPendientes(JButton boton, JFrame ventana, ControladorComprador controlador) {
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSubastasPendientes ventana = new VentanaSubastasPendientes(controlador);
				ventana.setLocationRelativeTo(null);
				ventana.mostrar();
			}
		};
		boton.addActionListener(buttonListener);
	}
	
	
	public void ejecutar() {
		this.setVisible(true);
	}
	
	public void setPanelImagen() {
		panelI = new JPanel();
		panelI.setBackground(new Color(153, 163, 164));
		panelI.setLayout(new GridLayout(1, 1, 30, 30));
		panelI.setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60))),
				BorderFactory.createEmptyBorder(8, 8, 8, 8)
				));
		
		ImageIcon imagenIcon = new ImageIcon("Imagenes/BannerComprador.png");
        JLabel imagenLabel = new JLabel(imagenIcon);
        panelI.add(imagenLabel);
	}
}
