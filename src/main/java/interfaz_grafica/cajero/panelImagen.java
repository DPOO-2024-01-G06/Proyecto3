package interfaz_grafica.cajero;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class panelImagen extends JPanel {

    public panelImagen() {
    	setBackground(new Color(153, 163, 164));
		setLayout(new GridLayout(1, 1, 30, 30));
		setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createLineBorder(new Color(33, 47, 60))),
				BorderFactory.createEmptyBorder(8, 8, 8, 8)
				));
		
		ImageIcon imagenIcon = new ImageIcon("Imagenes/BannerCajero.png");
        JLabel imagenLabel = new JLabel(imagenIcon);
        add(imagenLabel);
    }
}