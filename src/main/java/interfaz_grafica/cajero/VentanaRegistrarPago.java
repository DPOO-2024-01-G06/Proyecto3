package interfaz_grafica.cajero;

import javax.swing.*;

import galeria.controller_galeria.ControladorCajero;
import galeria.structurer_inventario.Venta;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class VentanaRegistrarPago extends JFrame {
    private JComboBox<String> lista;
    private ControladorCajero cCajero;

    public VentanaRegistrarPago(ControladorCajero cCajero) {
        this.cCajero = cCajero;
        setSize(500, 350);
        setResizable(true);
        setTitle("Registro de Pagos");
        setLayout(new BorderLayout());

        // Panel de selección de venta
        JPanel panelSeleccion = new JPanel(new FlowLayout());
        panelSeleccion.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        add(panelSeleccion, BorderLayout.NORTH);

        List<Venta> pendientes = cCajero.getVentasPendientes();
        List<String> titulos = new ArrayList<>();
        for (Venta venta : pendientes) {
            titulos.add(venta.getPieza().getTitulo());
        }
        lista = new JComboBox<>(titulos.toArray(new String[0]));
        panelSeleccion.add(new JLabel("Seleccione una venta: "));
        panelSeleccion.add(lista);

        // Botón de registrar pago
        JButton botonRegistrar = new JButton("Registrar Pago");
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice = lista.getSelectedIndex();
                String fecha = JOptionPane.showInputDialog("Ingrese la fecha (formato: YYYY/MM/DD):");
                cCajero.registrarPago(indice, fecha);
                JOptionPane.showMessageDialog(null, "Pago registrado!");
                lista.setSelectedIndex(-1);
            }
        });

        add(botonRegistrar, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public void mostrar() {
        setVisible(true);
    }
}
