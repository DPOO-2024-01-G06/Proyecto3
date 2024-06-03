package galeria.pagos;

import java.io.FileWriter;
import java.io.IOException;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;

public class PayU implements IPasarelaPago {
    private static final int LONGITUD_NUMERO_TARJETA = 16;
    private static final int LONGITUD_CVV = 3;

    @Override
    public boolean procesarPago(InfoTarjeta infoTarjeta, InfoPago infoPago) {
        boolean exito = true;
        infoPago.setNumeroCuenta("PayU12345677890");
        infoPago.setTransactionNumber(randomString(15, "NUMBERS"));
        try {
            if (comprobarDatosTarjeta(infoTarjeta.getNumeroTarjeta(), infoTarjeta.getCvv())) {
                if (estaExpirado(infoTarjeta)) {
                    exito = false;
                    procesarTransaccion("PayU", infoTarjeta, infoPago, exito, "Tarjeta expirada");
                } else if (infoTarjeta.getDineroDisponible() < infoPago.getCantidadDinero()) {
                    exito = false;
                    procesarTransaccion("PayU", infoTarjeta, infoPago, exito, "Fondos insuficientes");
                } else {
                    procesarTransaccion("PayU", infoTarjeta, infoPago, exito, "Transacción exitosa");
                }
            } else {
                exito = false;
                procesarTransaccion("PayU", infoTarjeta, infoPago, exito, "Los datos de la tarjeta son incorrectos");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exito;
    }

    public boolean comprobarDatosTarjeta(String numeroTarjeta, String cvv) {
        return numeroTarjeta.length() == LONGITUD_NUMERO_TARJETA && cvv.length() == LONGITUD_CVV;
    }

    private synchronized void procesarTransaccion(String pasarela, InfoTarjeta infoTarjeta, InfoPago infoPago, boolean exito, String mensaje) {
        try (FileWriter writer = new FileWriter("dataPersistencia/movimientos.txt", true)) {
            writer.write("Pasarela: " + pasarela + ", Tarjeta Numero: " + infoTarjeta.getNumeroTarjeta() +
                    ", Cantidad: " + infoPago.getCantidadDinero() + ", Exito: " + exito + ", Mensaje: " + mensaje + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean estaExpirado(InfoTarjeta infoTarjeta) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth fechaVencimientoYM = YearMonth.parse(infoTarjeta.getFechaExpiracion(), formatter);
            YearMonth fechaActual = YearMonth.now();

            return fechaVencimientoYM.isBefore(fechaActual);
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
        return sb.toString();}
}

