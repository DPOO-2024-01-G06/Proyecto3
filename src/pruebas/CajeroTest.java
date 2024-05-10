package pruebas;

import org.junit.Test;


import galeria.Galeria;
import galeria.controller_galeria.ControladorCajero;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Cajero;
import galeria.structurer_usuarios.Comprador;
import galeria.structurer_usuarios.Externo;
import galeria.structurer_usuarios.Propietario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CajeroTest{

    @Test
    public void testGetVentasPendientes() {
        Galeria galeria = mock(Galeria.class);
        Cajero cajero = mock(Cajero.class);
        Venta venta= mock(Venta.class);
        
        List<Venta> ventasPendientes = new ArrayList<Venta>();
        ventasPendientes.add(venta);
        when(cajero.getVentasPendientes()).thenReturn(ventasPendientes);

        ControladorCajero controlador = new ControladorCajero(galeria, cajero);

        List<Venta> ventas = controlador.getVentasPendientes();
        assertEquals(1, ventas.size());
    }

    @Test
    public void testRegistrarPago() {
        Galeria galeria = mock(Galeria.class);
        Cajero cajero = mock(Cajero.class);
        Venta venta = mock(Venta.class);
        List<Venta> ventasPendientes = new ArrayList<>();
        ventasPendientes.add(venta);
        when(cajero.getVentasPendientes()).thenReturn(ventasPendientes);

        Pieza pieza = mock(Pieza.class);
        Comprador comprador = mock(Comprador.class);
        Externo externo = mock(Externo.class);
        when(comprador.getExterno()).thenReturn(externo);
        when(pieza.getPropietario()).thenReturn(comprador.getExterno().getPropietario());
        when(pieza.getHistorialDuenos()).thenReturn(new HashMap<>());

        ControladorCajero controlador = new ControladorCajero(galeria, cajero);

        controlador.registrarPago(0, true);

        verify(cajero, times(1)).getVentasPendientes();
        verify(cajero, times(1)).getVentasPendientes().remove(0);

        verify(pieza, times(1)).setVenta(venta);
    }
   @Test
    public void testAgregarHistorial() {
        Pieza pieza = mock(Pieza.class);

        Map<String, Externo> historialDuenos = new HashMap<>();
        when(pieza.getHistorialDuenos());

        ControladorCajero controlador = new ControladorCajero(null, null);

        controlador.agregarHistorial(pieza, 100, "Comprador 1");


        verify(pieza, times(1)).getHistorialDuenos();
        assertEquals(1, historialDuenos.size());
        assertTrue(historialDuenos.containsKey("Comprador 1"));
    }
    @Test
    public void testSetNuevoPropietario() {
        Pieza pieza = mock(Pieza.class);
        Propietario propietario1=mock(Propietario.class);
        Propietario propietario2=mock(Propietario.class);
        when(pieza.getPropietario()).thenReturn(propietario1);
        ControladorCajero controladorCajero = new ControladorCajero(null, null);
		controladorCajero.setNuevoPropietario(pieza, propietario2);

            verify(propietario1).getPiezasCedidas();
            verify(propietario1.getPiezasCedidas()).remove(pieza);
            verify(propietario1).getPiezasPasadas();
            verify(propietario1.getPiezasPasadas()).add(pieza);
            verify(pieza).setPropietario(propietario2);
            verify(propietario1).getPiezasPropiedad();
            verify(propietario1.getPiezasPropiedad()).add(pieza);
        }
    
    
    @SuppressWarnings("deprecation")
	@Test
    public void testAgregarComprador() {
        ControladorCajero ControladorCajero= mock(ControladorCajero.class);
        Venta venta = mock(Venta.class);
        Comprador comprador1 = mock(Comprador.class);
        Comprador comprador2 = mock(Comprador.class);
        when(comprador1.getVentasPendientes()).thenReturn(new ArrayList<>());
        when(comprador1.getPiezasCompradas()).thenReturn(new ArrayList<>());
        when(comprador1.getValorColeccion()).thenReturn((float) 0.0);

        ControladorCajero.agregarComprador(venta, comprador2, null);

            verify(comprador2.getVentasPendientes()).remove(venta);
            verify(comprador2.getPiezasCompradas()).add(venta);
            assertEquals(comprador2.getValorColeccion(), venta.getPrecio());
        }
}

