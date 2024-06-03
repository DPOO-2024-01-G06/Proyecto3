package interfaz_grafica.administrador;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class VentanaHistograma extends JFrame{
	private PanelHistograma panel;
	
	VentanaHistograma(){	
		panel = new PanelHistograma();
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		  new VentanaHistograma().setVisible(true);; 
		 }
}
