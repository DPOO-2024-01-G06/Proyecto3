package prueba;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import galeria.pagos.*;

public class TarjetaTest {
    private InfoTarjeta tarjetaValida;
    private InfoTarjeta tarjetaInvalida;
    private InfoPago infoPago;

    @BeforeEach
    public void setUp() {
        tarjetaValida = new InfoTarjeta("John Doe", "1234567812345678", "12/24", 0, "123");
        tarjetaValida.setDineroDisponible(60000);
        tarjetaInvalida = new InfoTarjeta("John Doe", "1234567812345", "12/23", 0, "12");
        tarjetaInvalida.setDineroDisponible(0);
        infoPago = new InfoPago(100.0, "987654321", "txn12345", false);
    }

    @Test
    public void testCargarArchivo() {
        PagoServiceProvider psp = new PagoServiceProvider();
        psp.cargarArchivo();
        IPasarelaPago pasarela = null;
		try {
			pasarela = psp.getPasarela("PayU");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertNotNull(pasarela, "No debe ser Null");
        assertTrue(pasarela instanceof PayU, "La pasarela de pago debería ser una instancia de PayU");
        }
 

    @Test
    public void testPayUInvalido() {
        IPasarelaPago pasarela = new PayU();
        boolean result = pasarela.procesarPago(tarjetaInvalida, infoPago);
        assertFalse(result);
    }

    @Test
    public void testPayUValido() {
        IPasarelaPago pasarela = new PayU();
        boolean result = pasarela.procesarPago(tarjetaValida, infoPago);
        assertTrue(result);
    }

    @Test
    public void testPayPalValido() {
        IPasarelaPago pasarela = new PayPal();
        boolean result = pasarela.procesarPago(tarjetaValida, infoPago);
        assertTrue(result);
    }

    @Test
    public void testPayPalInvalido() {
        IPasarelaPago pasarela = new PayPal();
        boolean result = pasarela.procesarPago(tarjetaInvalida, infoPago);
        assertFalse(result);
    }
}