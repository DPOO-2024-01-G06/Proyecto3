package interfaz_grafica.operador;

import javax.swing.*;


import galeria.controller_galeria.ControladorOperador;
import galeria.structurer_inventario.Venta;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class VentanaPlanearSubasta extends JFrame {
    private JComboBox<String> lista;
    private JTextField valorMinimoField;
    private JTextField fechaField;
    private JTextField valorInicialField;
    private ControladorOperador cOperador;

    public VentanaPlanearSubasta(ControladorOperador cOperador) {
        this.cOperador = cOperador;
        setSize(600, 350);
        setResizable(true);
        setTitle("Subasta de piezas");
        setLayout(new BorderLayout());

        // Panel de selección de pieza
        JPanel panelSeleccion = new JPanel(new FlowLayout());
        panelSeleccion.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        add(panelSeleccion, BorderLayout.NORTH);

        List<Venta> ventas = cOperador.getVentasPendientes();
        List<String> titulos = new ArrayList<>();
        for (Venta venta : ventas) {
            titulos.add(venta.getPieza().getArtista().getNombre() + " - " + venta.getPieza().getTitulo());
        }
        lista = new JComboBox<>(titulos.toArray(new String[0]));
        panelSeleccion.add(new JLabel("Seleccione una pieza para subastar: "));
        panelSeleccion.add(lista);

        // Panel de entrada de datos
        JPanel panelDatos = new JPanel(new GridLayout(3, 2));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        add(panelDatos, BorderLayout.CENTER);

        valorMinimoField = new JTextField();
        fechaField = new JTextField();
        valorInicialField = new JTextField();

        panelDatos.add(new JLabel("Valor mínimo de la subasta: "));
        panelDatos.add(valorMinimoField);
        panelDatos.add(new JLabel("<html>Fecha de finalización de la subasta<br/>(formato: yyyy-MM-dd):</html>"));
        panelDatos.add(fechaField);
        panelDatos.add(new JLabel("Valor inicial de la subasta: "));
        panelDatos.add(valorInicialField);

        // Botón de agregar oferta pendiente
        JButton botonAgregar = new JButton("Agregar oferta pendiente");
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice = lista.getSelectedIndex();
                Venta venta = ventas.get(indice);
                float valorMinimo = Float.parseFloat(valorMinimoField.getText());
                String fecha = fechaField.getText();
                float valorInicial = Float.parseFloat(valorInicialField.getText());

                cOperador.planearSubasta(venta, valorMinimo, new ArrayList<>(), fecha, valorInicial);

                // Borra la información ingresada en las casillas
                valorMinimoField.setText("");
                fechaField.setText("");
                valorInicialField.setText("");
            }
        });
        add(botonAgregar, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public void mostrar() {
        setVisible(true);
    }
}

