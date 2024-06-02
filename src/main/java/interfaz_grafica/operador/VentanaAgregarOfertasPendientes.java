package interfaz_grafica.operador;

import javax.swing.*;

import galeria.controller_galeria.ControladorOperador;
import galeria.structurer_inventario.Oferta;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class VentanaAgregarOfertasPendientes extends JFrame {
    private JComboBox<String> lista;
    private ControladorOperador cOperador;

    public VentanaAgregarOfertasPendientes(ControladorOperador cOperador) {
        this.cOperador = cOperador;
        setSize(600, 350);
        setResizable(true);
        setTitle("Ofertas Pendientes");
        setLayout(new BorderLayout());

        // Panel de selección de oferta
        JPanel panelSeleccion = new JPanel(new FlowLayout());
        panelSeleccion.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        add(panelSeleccion, BorderLayout.NORTH);

        List<Oferta> ofertasPendientes = cOperador.getOfertasPendientes();
        List<String> titulos = new ArrayList<>();
        for (Oferta oferta : ofertasPendientes) {
            titulos.add(oferta.getSubasta().getPieza().getTitulo() + " - " + oferta.getValor());
        }
        lista = new JComboBox<>(titulos.toArray(new String[0]));
        panelSeleccion.add(new JLabel("Seleccione una oferta pendiente: "));
        panelSeleccion.add(lista);

        // Botón de agregar oferta pendiente
        JButton botonAgregar = new JButton("Agregar oferta pendiente");
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice = lista.getSelectedIndex();
                cOperador.agregarOfertaPendiente(indice);
                JOptionPane.showMessageDialog(null, "Oferta agregada!");
                lista.setSelectedIndex(-1); // Línea agregada para limpiar la selección
            }
            
        });
        
        add(botonAgregar, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public void mostrar() {
        setVisible(true);
    }
}


  
