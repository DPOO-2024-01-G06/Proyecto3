package interfaz_grafica.operador;

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

import galeria.controller_galeria.ControladorOperador;
import interfaz_grafica.GUIUsuario;

@SuppressWarnings("serial")
public class GUIOp extends GUIUsuario {
	private panelDatosUsuario panelDU;
	private panelHistorial panelH;
	private panelAcciones panelA;
	private JPanel panelI;
	
	public GUIOp(ControladorOperador cOp) {
		setSize(900, 600);
		setResizable(true);
		setTitle("Interfaz Operador");
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
		setActionActualizarInfo(cOp,panelDU.getBtActualizarDatos());
		setActionCerrarSesion(panelA.getBtCerrarSesion(), this, cOp.getGaleria());
		setActionHistoriaPieza(panelH.getBtHistoriaPieza(),this,cOp);
		setActionHistoriaArtista(panelH.getBtHistoriaArtista(),this,cOp);
		setActionAgregarOferta(panelA.getBtAgregarOferta(),this,cOp);
		setActionPlanearSubasta(panelA.getBtPlanearSubasta(),this, cOp);
	}
	public void ejecutar() {
		this.setVisible(true);
	}
	public void setActionAgregarOferta(JButton boton, JFrame ventana, ControladorOperador cOp) {
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaAgregarOfertasPendientes nuevo = new VentanaAgregarOfertasPendientes(cOp);
				nuevo.setLocationRelativeTo(null);
				nuevo.mostrar();
			}
		};
		boton.addActionListener(buttonListener);
	}
	
	public void setActionPlanearSubasta(JButton boton, JFrame ventana, ControladorOperador cOp) {
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPlanearSubasta nuevo = new VentanaPlanearSubasta(cOp);
				nuevo.setLocationRelativeTo(null);
				nuevo.mostrar();
			}
		};
		boton.addActionListener(buttonListener);
	}
	
	public void setPanelImagen() {
		panelI = new JPanel();
		panelI.setBackground(new Color(153, 163, 164));
		panelI.setLayout(new GridLayout(1, 1, 30, 30));
		panelI.setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60))),
				BorderFactory.createEmptyBorder(8, 8, 8, 8)
				));
		
		ImageIcon imagenIcon = new ImageIcon("Imagenes/BannerOperador.png");
        JLabel imagenLabel = new JLabel(imagenIcon);
        panelI.add(imagenLabel);
		
		
	}
}
