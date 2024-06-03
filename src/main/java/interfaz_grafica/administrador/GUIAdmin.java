package interfaz_grafica.administrador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import galeria.controller_galeria.ControladorAdministrador;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Comprador;
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
		setActionHistoriaPieza(panelH.getBtHistoriaPieza(),this,cAdmin);
		setActionHistoriaArtista(panelH.getBtHistoriaArtista(),this,cAdmin);
		setActionVerificarUsuario(cAdmin,this);
		setActionActualizarMonto(cAdmin,this);
		setActionHistoriaComprador(cAdmin,this);
		setActionIngresarPieza(cAdmin,this);
		setActionDevolverPieza(cAdmin,this);
		setActionAdministrarCompra(cAdmin,this);
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
	
	@SuppressWarnings("rawtypes")
	public void setActionVerificarUsuario(ControladorAdministrador cAdmin, JFrame ventana1) {	
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame ventana = new JFrame();
				ventana.setLocationRelativeTo(ventana1);
				ventana.setTitle("Verificar/Invalidar usuario");
				ventana.setSize(new Dimension(350,100));
				ventana.setLayout(new FlowLayout());
				ventana.add(new JLabel("Seleccione un usuario: "));
				ventana.setVisible(true);
				ArrayList<String> comp = new ArrayList<String>();
				for(Comprador comprador: cAdmin.getUsuariosPendientes()) {
					comp.add(comprador.getExterno().getNombre());
				}
				JComboBox lista = new JComboBox<>(comp.toArray());
				ventana.add(lista);
				ActionListener comboBoxListener = new ActionListener() {
					@Override
				public void actionPerformed(ActionEvent e) {
					int i = lista.getSelectedIndex();
					int resultado = JOptionPane.showConfirmDialog(ventana, "Desea verificar al usuario?", "Verificacion",JOptionPane.YES_NO_OPTION);
					if(resultado == 0) {
						lista.remove(i);
						boolean parse = true ;
						while(parse) {
							String monto = JOptionPane.showInputDialog(ventana, "Ingrese el monto máximo de este comprador: ", "");
							if(monto!= null) {
								try {
									cAdmin.verificarExterno(i, Float.valueOf(monto));
									parse = false;
								}
								catch(Exception e1){
									JOptionPane.showMessageDialog(ventana, "Ingrese un monto válido", "Error",JOptionPane.ERROR_MESSAGE);
								}
							}
							else {
								parse = false;
							}
						}	
					}
					else if(resultado == 1){
						cAdmin.invalidarExterno(i);
						lista.remove(i);
					}
				ventana.removeAll();
				ventana.dispose();	
				}
			};
				lista.addActionListener(comboBoxListener);
			}
		};
		panelDU.getBtVerificacion().addActionListener(buttonListener);
	}
	
	public void setActionActualizarMonto(ControladorAdministrador cAdmin, JFrame ventana1) {		
		ActionListener buttonListener = new ActionListener() {
			@SuppressWarnings("rawtypes")
			@Override
			public void actionPerformed(ActionEvent e) {
			JFrame ventana = new JFrame();
			ventana.setLocationRelativeTo(ventana1);
			ventana.setTitle("Actualizar Monto");
			ventana.setSize(new Dimension(350,100));
			ventana.setLayout(new FlowLayout());
			ventana.add(new JLabel("Seleccione un usuario: "));
			ventana.setVisible(true);
			System.out.println(cAdmin.getSuperaronLimite().size());
			ArrayList<String> comp = new ArrayList<String>();
			for(Comprador comprador: cAdmin.getSuperaronLimite()) {
				comp.add(comprador.getExterno().getNombre());
			}
			JComboBox lista = new JComboBox<>(comp.toArray());
			ventana.add(lista);
			ActionListener comboBoxListener = new ActionListener() {
				@Override
			public void actionPerformed(ActionEvent e) {
				int i = lista.getSelectedIndex();
				boolean parse = true;
				while(parse) {
					String monto = JOptionPane.showInputDialog(ventana, "Ingrese el nuevo monto: " ,"");
					if(monto!= null) {
						try {
							cAdmin.reestablecerMaximo(i, Float.valueOf(monto));
							parse = false;
							ventana.dispose();
						}
						catch(Exception e1){
							JOptionPane.showMessageDialog(ventana, "Ingrese un monto válido", "Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						parse = false;
						ventana.dispose();
					}
				}
			}
				};
			lista.addActionListener(comboBoxListener);
			}
				
		};
		panelDU.getBtActualizarVMax().addActionListener(buttonListener);
	}
	
	public void setActionHistoriaComprador(ControladorAdministrador cAdmin, JFrame ventana) {	
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaHistoriaCompradores nuevo = new VentanaHistoriaCompradores(cAdmin, ventana);
				nuevo.mostrar();
			}
		};
		panelH.getBtHistoriaComprador().addActionListener(buttonListener);
		
		
	}
	
	@SuppressWarnings("rawtypes")
	public void setActionIngresarPieza(ControladorAdministrador cAdmin, JFrame ventana1) {	
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame ventana = new JFrame();
				ventana.setLocationRelativeTo(ventana1);
				ventana.setTitle("Ingresar Pieza Cedida");
				ventana.setSize(new Dimension(400,120));
				ventana.setLayout(new FlowLayout());
				ventana.add(new JLabel("Seleccione una pieza: "));
				ventana.setVisible(true);
				ArrayList<String> piezas = new ArrayList<String>();
				for(Pieza pieza: cAdmin.getPendientesPorAgregar()) {
					
					piezas.add(pieza.getTitulo());
				}
				JComboBox lista = new JComboBox<>(piezas.toArray());
				ventana.add(lista);
				ActionListener comboBoxListener = new ActionListener() {
					@Override
				public void actionPerformed(ActionEvent e) {
					int i = lista.getSelectedIndex();
					int resultado = JOptionPane.showConfirmDialog(ventana, "Desea ingresar la pieza?", "Verificacion",JOptionPane.YES_NO_OPTION);
					if(resultado == 0) {
						lista.remove(i);
						boolean parse = true ;
						while(parse) {
							String monto = JOptionPane.showInputDialog(ventana, "Ingrese el precio de la venta: ", "");
							if(monto!= null) {
								try {
									cAdmin.ingresarPiezaCedida(i, Float.valueOf(monto));
									parse = false;
								}
								catch(Exception e1){
									JOptionPane.showMessageDialog(ventana, "Ingrese un monto válido", "Error",JOptionPane.ERROR_MESSAGE);
								}
							}
							else {
								parse = false;
							}
						}	
					}
					else if(resultado == 1){
						cAdmin.ingresarPiezaCedida(i,-1);
						lista.remove(i);
					}
				ventana.removeAll();
				ventana.dispose();	
				}
			};
				lista.addActionListener(comboBoxListener);
			}
		};
		panelA.getBtIngresar().addActionListener(buttonListener);
	}
	
	@SuppressWarnings("rawtypes")
	public void setActionDevolverPieza(ControladorAdministrador cAdmin, JFrame ventana1) {	
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame ventana = new JFrame();
				ventana.setLocationRelativeTo(ventana1);
				ventana.setTitle("Devolver Pieza Cedida");
				ventana.setSize(new Dimension(400,120));
				ventana.setLayout(new FlowLayout());
				ventana.add(new JLabel("Seleccione una pieza: "));
				ventana.setVisible(true);
				ArrayList<String> piezas = new ArrayList<String>();
				for(Pieza pieza: cAdmin.getPiezasCedidas()) {
					
					piezas.add(pieza.getTitulo());
				}
				JComboBox lista = new JComboBox<>(piezas.toArray());
				ventana.add(lista);
				ActionListener comboBoxListener = new ActionListener() {
					@Override
				public void actionPerformed(ActionEvent e) {
					int i = lista.getSelectedIndex();
					int resultado = JOptionPane.showConfirmDialog(ventana, "Desea devolver la pieza?", "Verificacion",JOptionPane.YES_NO_OPTION);
					if(resultado == 0) {
						lista.remove(i);
						cAdmin.devolverPieza(i);
					}
				ventana.removeAll();
				ventana.dispose();	
				}
			};
				lista.addActionListener(comboBoxListener);
			}
		};
		panelA.getBtDevolver().addActionListener(buttonListener);
	}
	
	public void setActionAdministrarCompra(ControladorAdministrador cAdmin, JFrame ventana1) {	
		ActionListener buttonListener = new ActionListener() {
			@SuppressWarnings("rawtypes")
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame ventana = new JFrame();
				ventana.setLocationRelativeTo(ventana1);
				ventana.setTitle("Administrar compra");
				JPanel panel1 = new JPanel();
				JPanel panel2 = new JPanel();
				ventana.setSize(new Dimension(400,300));
				panel1.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
				panel2.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
				panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
				panel1.add(new JLabel("Seleccione una compra: "));
				ArrayList<String> piezas = new ArrayList<String>();
				for(Venta venta: cAdmin.getVentasPendientes()) {
					
					piezas.add(venta.getPieza().getTitulo());
				}
				JComboBox lista = new JComboBox<>(piezas.toArray());
				panel1.add(lista);
				ventana.add(panel1,BorderLayout.NORTH);
				ventana.add(panel2,BorderLayout.SOUTH);
				ventana.setVisible(true);
				ActionListener comboBoxListener = new ActionListener() {
					@Override
				public void actionPerformed(ActionEvent e) {
					int i = lista.getSelectedIndex();
					Venta venta = cAdmin.getVentasPendientes().get(i);
					panel2.removeAll();
					JLabel comprador = new JLabel("Comprador: " + venta.getComprador().getExterno().getNombre());
					JLabel precio = new JLabel(", Precio: " + Float.toString(venta.getPrecio()));
					JButton boton1 = new JButton("Aceptar");
					JButton boton2 = new JButton("Rechazar");
					panel2.add(comprador);panel2.add(precio);panel2.add(boton1);panel2.add(boton2);
					ventana.setVisible(false);
					ventana.setVisible(true);
					ActionListener boton1Listener = new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							cAdmin.confirmarVenta(i,true);
							ventana.dispose();
						}	
					};
					ActionListener boton2Listener = new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							cAdmin.confirmarVenta(i,false);
							ventana.dispose();
						}
					};
					boton1.addActionListener(boton1Listener);
					boton2.addActionListener(boton2Listener);
				}
			};
				lista.addActionListener(comboBoxListener);
			}
		};
		panelA.getBtPropCompra().addActionListener(buttonListener);
	}
	
}
