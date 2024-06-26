package galeria.pagos;

import java.io.FileWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;

public class Stripe implements IPasarelaPago{
	private static final int LONGITUD_NUMERO_TARJETA = 16;
    private static final int LONGITUD_CVV = 3;
  


    @Override
    public boolean procesarPago(InfoTarjeta infoTarjeta, InfoPago infoPago) {
    	boolean exito =true;
    	infoPago.setNumeroCuenta("Stripe12345677890");
    	infoPago.setTransactionNumber(randomString(15, "NUMBERS"));
    	try {
			if(comprobarDatosTarjeta( infoTarjeta.getNumeroTarjeta() , infoTarjeta.getCvv())){
				if(estaExpirado(infoTarjeta)) {
					exito=false;
					procesarTransaccion("PayU", infoTarjeta, infoPago, exito, "Tarjeta expirada");
				}
			    if (infoTarjeta.getDineroDisponible() < infoPago.getCantidadDinero()) {
			    	exito=false;
			        procesarTransaccion("PayU", infoTarjeta, infoPago, exito, "Fondos insuficientes");
			    }
			    else{
			    	procesarTransaccion("PayU", infoTarjeta, infoPago, exito, "Transaccion exitosa");
			    }}
			else {
				exito=false;
				procesarTransaccion("PayU", infoTarjeta, infoPago, exito, "Los datos de la tarjeta son incorrectos"); 	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return exito;
    	}
   
    
    public boolean comprobarDatosTarjeta(String numeroTarjeta, String cvv) throws Exception {
    	 boolean exito = true;
        if (numeroTarjeta.length() != LONGITUD_NUMERO_TARJETA) {
            exito=false;  
        }
        if (cvv.length() != LONGITUD_CVV) {
            exito=false;
        }
        return exito;
    	}
    
    private synchronized void procesarTransaccion(String pasarela, InfoTarjeta infoTarjeta, InfoPago infoPago, boolean exito, String mensaje) {
        try (FileWriter writer = new FileWriter("dataPersistencia/movimientos.txt", true)) {
            writer.write("Pasarela: " + pasarela + ", Tarjeta Numero: " + infoTarjeta.getNumeroTarjeta() +
                         ", Cantidad: " + infoPago.getCantidadDinero() + ", Exito: " + exito + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean estaExpirado(InfoTarjeta infoTarjeta) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            LocalDate fechaVencimientoLD = LocalDate.parse(infoTarjeta.getFechaExpiracion(), formatter);
            fechaVencimientoLD = fechaVencimientoLD.withDayOfMonth(1); 

            LocalDate fechaActual = LocalDate.now();

            if (fechaVencimientoLD.isBefore(fechaActual)) {
                return true;
            } else {
                return false;
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha de vencimiento inválida. Debe ser en formato MM/AA", e);
        }
    }
    public String randomString(int length, String tipo) {
        final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
        final String NUMEROS = "1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        if (tipo.equals("NUMEROS")) {
            for (int i = 0; i < length; i++) {
                sb.append(NUMEROS.charAt(random.nextInt(NUMEROS.length())));
            }
        } else if (tipo.equals("CHARACTERS")) {
            for (int i = 0; i < length; i++) {
                sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
        }
        return sb.toString();
    }
	
}
