package interfaz_grafica.comprador;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import galeria.controller_galeria.ControladorComprador;
import galeria.pagos.InfoTarjeta;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Subasta;

public class VentanaComprar extends JFrame{
	private JComboBox lista;
	private JPanel panelOut;
	private JPanel panelMetodos;
	private JPanel nombre;
	private JPanel valorInicial;
	private JPanel ofertaMax;
	private JPanel fechaLimite;
	private String metodo;
	private Integer piezaSeleccionada;
		
	public VentanaComprar(ControladorComprador controlador) {
		this.setLocationRelativeTo(null);
		setSize(600, 600);
		setResizable(false);
		setTitle("Comprar pieza");
		setLayout(new GridLayout(4,1));
		
		// Panel título y selección de pieza
		JPanel panelTituloYSeleccion = new JPanel();
		panelTituloYSeleccion.setLayout(new BorderLayout());
		panelTituloYSeleccion.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		add(panelTituloYSeleccion);

		JPanel panelTitulo = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;

		JLabel titulo = new JLabel("COMPRAR PIEZA");
		titulo.setFont(new Font("Garamond", Font.BOLD, 24));
		panelTitulo.add(titulo, gbc);
		panelTituloYSeleccion.add(panelTitulo, BorderLayout.NORTH);

		JPanel panelSeleccion = new JPanel(new FlowLayout());
		panelSeleccion.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));

		JLabel texto = new JLabel("Seleccione la pieza que desea comprar: ");
		panelSeleccion.add(texto);

		ArrayList<String> despliegue = new ArrayList<String>();
		despliegue.add("-----------");
		for(Pieza pieza: controlador.getListaPiezas()) {
		    despliegue.add(pieza.getTitulo());
		}

		lista = new JComboBox<>(despliegue.toArray());	
		lista.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            String seleccionado = (String) lista.getSelectedItem();
		            System.out.println("Seleccionado: " + seleccionado);
		        }
		    }
		});
		
		lista.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            piezaSeleccionada = (Integer) lista.getSelectedItem();
		        }
		    }
		});

		panelSeleccion.add(lista);
		panelTituloYSeleccion.add(panelSeleccion, BorderLayout.CENTER);
		
		
		
		//Panel botones metodos de pago
		panelMetodos = new JPanel();
		panelMetodos.setLayout(new GridLayout(1,3, 40, 40));
		panelMetodos.setBorder(BorderFactory.createCompoundBorder(
		        new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)),"   Seleccione el método de pago:   "),
		        BorderFactory.createEmptyBorder(5, 60, 5, 60)
		        ));
		panelMetodos.setPreferredSize(new Dimension(200, 175));

		ButtonGroup grupoBotones = new ButtonGroup();

		ActionListener listener = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JButton boton = (JButton) e.getSource();
		        metodo = boton.getText();
		        System.out.println("Metodo seleccionado: " + metodo);
		    }
		};

		JRadioButton btMetodo1 = new JRadioButton("Paypal");
		btMetodo1.addActionListener(listener);
		grupoBotones.add(btMetodo1);
		panelMetodos.add(btMetodo1);

		JRadioButton btMetodo2 = new JRadioButton("PayU");
		btMetodo2.addActionListener(listener);
		grupoBotones.add(btMetodo2);
		panelMetodos.add(btMetodo2);

		JRadioButton btMetodo3 = new JRadioButton("Stripe");
		btMetodo3.addActionListener(listener);
		grupoBotones.add(btMetodo3);
		panelMetodos.add(btMetodo3);

		add(panelMetodos);
		
		// Panel datos de pago
		JPanel panelDatos = new JPanel(new CardLayout());
		panelDatos.setBorder(BorderFactory.createCompoundBorder(
		        new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)), "Completa los datos de pago:"),
		        BorderFactory.createEmptyBorder(5, 60, 5, 60)
		));
		
		// Panel datos de pago tarjetas
		JPanel panelDatosPago = new JPanel();
		panelDatosPago.setVisible(false);
		panelDatosPago.setLayout(new GridLayout(4, 1, 10, 10));
		panelDatosPago.setBorder(BorderFactory.createCompoundBorder(
		        new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)), "Completa los datos de pago:"),
		        BorderFactory.createEmptyBorder(5, 60, 5, 60)
		));

		JLabel labelTitular = new JLabel("Titular de la tarjeta:");
		JTextField fieldTitular = new JTextField(20);
		panelDatosPago.add(labelTitular);
		panelDatosPago.add(fieldTitular);

		JLabel labelNumeroTarjeta = new JLabel("Número de la tarjeta:");
		JTextField fieldNumeroTarjeta = new JTextField(20);
		panelDatosPago.add(labelNumeroTarjeta);
		panelDatosPago.add(fieldNumeroTarjeta);

		JLabel labelFechaExpiracion = new JLabel("Fecha de expiración:");
		JTextField fieldFechaExpiracion = new JTextField(20);
		panelDatosPago.add(labelFechaExpiracion);
		panelDatosPago.add(fieldFechaExpiracion);

		JLabel labelCVV = new JLabel("CVV:");
		JTextField fieldCVV = new JTextField(20);
		panelDatosPago.add(labelCVV);
		panelDatosPago.add(fieldCVV);

		// Panel para datos de PayPal
		JPanel panelPayPal = new JPanel();
		panelPayPal.setVisible(false);
		panelPayPal.setLayout(new GridLayout(2, 1, 10, 10));
		panelPayPal.setBorder(BorderFactory.createCompoundBorder(
		        new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60)), "Datos de PayPal:"),
		        BorderFactory.createEmptyBorder(5, 60, 5, 60)
		));

		JLabel labelContraseña = new JLabel("Contraseña:");
		JTextField fieldContraseña = new JTextField(20);
		panelPayPal.add(labelContraseña);
		panelPayPal.add(fieldContraseña);

		JLabel labelCorreo = new JLabel("Correo electrónico:");
		JTextField fieldCorreo = new JTextField(20);
		panelPayPal.add(labelCorreo);
		panelPayPal.add(fieldCorreo);

		btMetodo1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        CardLayout cl = (CardLayout) panelDatos.getLayout();
		        cl.show(panelDatos, "paypal");
		    }
		});

		btMetodo2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        CardLayout cl = (CardLayout) panelDatos.getLayout();
		        cl.show(panelDatos, "tarjeta");
		    }
		});

		btMetodo3.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        CardLayout cl = (CardLayout) panelDatos.getLayout();
		        cl.show(panelDatos, "tarjeta");
		    }
		});
		
		panelDatos.add(panelDatosPago, "tarjeta");
		panelDatos.add(panelPayPal, "paypal");
		add(panelDatos);
		
		// Panel botón Comprar
		JPanel panelBotonComprar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.CENTER;

        JButton botonComprar = new JButton("Comprar");
        botonComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btMetodo2.isSelected() || btMetodo3.isSelected()) {
                    String titular = fieldTitular.getText();
                    String numeroTarjeta = fieldNumeroTarjeta.getText();
                    String fechaExpiracion = fieldFechaExpiracion.getText();
                    String cvv = fieldCVV.getText();

                    InfoTarjeta tarjeta = new InfoTarjeta(titular, numeroTarjeta, fechaExpiracion, 0, cvv);
                    controlador.intentoComprar(piezaSeleccionada, tarjeta);
                }
            }
        });
        panelBotonComprar.add(botonComprar, gbc2);
        add(panelBotonComprar);
	}
	
	public void mostrar() {
		setVisible(true);
	}

}
