package interfaz_grafica;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VentanaSesion extends JFrame{
	private JLabel tit;
	private JPanel panel;
	private JTextField campo1;
	private JTextField campo2;
	private JButton boton;
	
	VentanaSesion(){
		this.setTitle("Inicio de Sesion");
		this.setSize(300, 200);
		panel = new JPanel();
		tit = new JLabel("Inicio de sesión Galeria");
		tit.setAlignmentX(CENTER_ALIGNMENT);
		panel.setPreferredSize(new Dimension(125,125));
		campo1 = new JTextField("Ingrese su usuario",10);
		campo2 = new JTextField("Ingrese su contraseña",10);
		campo1.requestFocusInWindow();
		ajusteCampo(campo1);ajusteCampo(campo2);
		setFocusField(campo1);setFocusField(campo2);
		boton = new JButton("Iniciar Sesion");
		boton.setAlignmentX(Component.CENTER_ALIGNMENT);
		BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(box);
		panel.add(tit);
		panel.add(Box.createRigidArea(new Dimension(10, 10)));
		panel.add(campo1);panel.add(Box.createRigidArea(new Dimension(10, 10)));
		panel.add(campo2); panel.add(Box.createRigidArea(new Dimension(10, 10)));
		panel.add(boton);
		this.add(panel);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	}
	
	public void ajusteCampo(JTextField campo) {
		campo.setMinimumSize(new Dimension(100, 30));
		campo.setPreferredSize(new Dimension(125, 30));
		campo.setMaximumSize(new Dimension(150,30));
	}
	
	public void setFocusField(JTextField campo) {
		
		String str = campo.getText();
		campo.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(campo.getText().equals(str)) {
					campo.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(campo.getText().equals("")) {
					campo.setText(str);
				}
				
			}
			
		});
	}
		
	public JTextField getCampo1() {
		return campo1;
	}

	public JTextField getCampo2() {
		return campo2;
	}

	public JButton getBoton() {
		return boton;
	}

	public void mostrar() {
		this.setVisible(true);
		setFocusButton();
	}
	public void setFocusButton() {
		boton.requestFocusInWindow();
	}
	
	
	
}
