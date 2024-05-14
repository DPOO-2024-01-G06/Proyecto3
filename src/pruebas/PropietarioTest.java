package pruebas;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import galeria.Galeria;
import galeria.controller_galeria.ControladorPropietario;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_usuarios.Externo;
import galeria.structurer_usuarios.Propietario;

public class PropietarioTest {

    @Test
    void testGetPiezasPropiedad() {
        Propietario propietario = mock(Propietario.class);
        List<Pieza> piezasPropiedad = new ArrayList<>();
        Pieza pieza1 = mock(Pieza.class);
        Pieza pieza2 = mock(Pieza.class);
        propietario.addPiezaPropiedad(pieza1);
        propietario.addPiezaPropiedad(pieza2);
        
        Externo externo = new Externo(null, null, null, null, null, null, propietario);

        Galeria galeria = new Galeria(null, null);

        ControladorPropietario controlador = new ControladorPropietario(galeria, externo);

        List<Pieza> result = controlador.getPiezasPropiedad();

        assertEquals(piezasPropiedad, result);
    }

    @Test
    void testGetPiezasCedidas() {
        Propietario propietario = new Propietario(null, null, null, null);
        List<Pieza> piezasCedidas = new ArrayList<>();
        Pieza pieza1 = mock(Pieza.class);
        Pieza pieza2 = mock(Pieza.class);
        propietario.addPiezaPropiedad(pieza1);
        propietario.addPiezaPropiedad(pieza2);

        Externo externo = new Externo(null, null, null, null, null, null, propietario);

        Galeria galeria = new Galeria(null, null);

        ControladorPropietario controlador = new ControladorPropietario(galeria, externo);

        List<Pieza> result = controlador.getPiezasCedidas();

        assertEquals(piezasCedidas, result);
    }

    @Test
    void testCederPieza() {
        Propietario propietario = new Propietario(null, null, null, null);
        Pieza pieza1 = mock(Pieza.class);
        Pieza pieza2 = mock(Pieza.class);
        propietario.addPiezaPropiedad(pieza1);
        propietario.addPiezaPropiedad(pieza2);

        Externo externo = new Externo(null, null, null, null, null, null, propietario);

        Galeria galeria = new Galeria(null, null);

        ControladorPropietario controlador = new ControladorPropietario(galeria, externo);


        controlador.cederPieza(0);

        assertEquals(1, propietario.getPiezasCedidas().size());
        assertEquals(pieza1, propietario.getPiezasCedidas().get(0));
        assertEquals(1, propietario.getPiezasPropiedad().size());
        assertEquals(pieza2, propietario.getPiezasPropiedad().get(0));

        assertEquals(1, galeria.getUsuariosGaleria().getAdministrador().getPiezasPorAgregar().size());
        assertEquals(pieza1, galeria.getUsuariosGaleria().getAdministrador().getPiezasPorAgregar().get(0));
    }}