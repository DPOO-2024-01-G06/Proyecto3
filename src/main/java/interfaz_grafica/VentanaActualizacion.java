package interfaz_grafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import galeria.controller_galeria.ControladorGenerico;


@SuppressWarnings("serial")
public class VentanaActualizacion extends JFrame {
	
	public VentanaActualizacion(ControladorGenerico controlador) {
		this.setLocationRelativeTo(null);
        setSize(500, 400);
        setResizable(false);
		setTitle("Formato de actualización de datos");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
        
		setLayout(new GridLayout(6, 1));
		
		//Panel Título
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
		add(panelTitulo);
		
		JLabel titulo = new JLabel("ACTUALIZACION DE DATOS");
		titulo.setFont(new Font("Garamond", Font.BOLD, 24));
		panelTitulo.add(titulo);
		
        //Panel Nombre de usuario
        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createTitledBorder("  Nuevo nombre de usuario:"));
        add(panel1);

        JTextField campo1 = new JTextField(20);
        panel1.add(campo1);

        //Panel Correo
        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createTitledBorder("  Nuevo correo:"));
        add(panel2);
        
        JTextField campo2 = new JTextField(20);
        panel2.add(campo2);

        
        //Panel Celular
        JPanel panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createTitledBorder("  Nuevo celular:"));
        add(panel3);
        
        JTextField campo3 = new JTextField(20);
        panel3.add(campo3);

        
        //Panel Contraseña
        JPanel panel4 = new JPanel();
        panel4.setBorder(BorderFactory.createTitledBorder("  Nueva contraseña:"));
        add(panel4);

        JPasswordField campo4 = new JPasswordField(20);
        panel4.add(campo4);
        
        //Panel Boton confirmar
        JPanel confirmar = new JPanel();
        add(confirmar);
        
        JButton btConfirmar = new JButton("Confirmar");
        btConfirmar.setBackground(new Color(52, 73, 94));
        btConfirmar.setForeground(Color.WHITE);
        btConfirmar.setBorder(BorderFactory.createLineBorder(new Color(33, 47, 60), 3));
        btConfirmar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	String nuevoNombreUsuario = campo1.getText();
				String nuevoCorreo = campo2.getText();
				String nuevoCelular = campo3.getText();
				String nuevaContrasena = new String(campo4.getPassword());
				
				if (nuevoNombreUsuario.length() < 5) {
					JOptionPane.showMessageDialog(null, "Ingrese un nombre de usuario válido (Mínimo cinco caracteres).");
				}
				else if (nuevoCorreo.length() < 8) {
					JOptionPane.showMessageDialog(null, "Ingrese un correo válido (Mínimo 8 caracteres).");
				}
				else if (!nuevoCelular.matches("\\d+")) {
					JOptionPane.showMessageDialog(null, "Ingrese un número telefónico válido.");
				}
				else if (nuevaContrasena.length() < 8) {
					JOptionPane.showMessageDialog(null, "Su contraseña es muy corta (Mínimo 8 caracteres).");
				}
				else {
					
					//Llamar controlador de usuario y funcion para actualizar datos
					//Usuario.actualizarInfo(nuevaContrasena, nuevoNombreUsuario, nuevoCelular, nuevoCorreo);
					controlador.actualizarInfo(nuevaContrasena, nuevoNombreUsuario, nuevoCelular, nuevoCorreo);
					JOptionPane.showMessageDialog(null, "Sus datos han sido correctamente actualizados.");
					dispose();
				}
		    }
		});
        confirmar.add(btConfirmar);
        
        
    }
	
	public void mostrar() {
		this.setVisible(true);
		
	}	
}