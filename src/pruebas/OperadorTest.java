package pruebas;

import org.junit.jupiter.api.Test;

import galeria.structurer_inventario.Oferta;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_usuarios.Operador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

public class OperadorTest {

    @Test
    void testAgregarOfertaPendiente() {

        Operador operador = new Operador("username", "password", "name", "celular", "correo", new ArrayList<>());
        Subasta subasta=mock(Subasta.class);
        Oferta oferta = new Oferta(515, "efectivo", subasta); 

        operador.agregarOfertaPendiente(oferta);

        assertEquals(1, operador.getOfertasPendientes().size());
        assertEquals(oferta, operador.getOfertasPendientes().get(0));
    }

    @Test
    void testGetOfertasPendientes() {
        Operador operador = new Operador("username", "password", "name", "celular", "correo", new ArrayList<>());
        Subasta subasta=mock(Subasta.class);
        List<Oferta> ofertasPendientes = new ArrayList<>();
        ofertasPendientes.add(new Oferta(515, "efectivo", subasta)); 
        ofertasPendientes.add(new Oferta(4515, "efectivo", subasta));

        operador.ofertasPendientes = ofertasPendientes;

        List<Oferta> result = operador.getOfertasPendientes();

        assertEquals(ofertasPendientes, result);
    }

    @Test
    void testGetTipoInterno() {
        Operador operador = new Operador("username", "password", "name", "celular", "correo", new ArrayList<>());
        String tipoInterno = operador.getTipoInterno();

        assertEquals("operador", tipoInterno);
    }

}