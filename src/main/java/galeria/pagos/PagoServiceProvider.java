package galeria.pagos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PagoServiceProvider {
    private List<String> pasarelasDePago;

    public void cargarArchivo() {
        pasarelasDePago = new ArrayList<>();

        try {
            File file = new File("dataPersistencia/pasarelas.txt");
            if (!file.exists()) {
                throw new IllegalArgumentException("Archivo 'pasarelas.txt' no encontrado en el directorio actual");
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                pasarelasDePago.add(line);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public IPasarelaPago getPasarela(String nombrePasarela) throws Exception {
        if (pasarelasDePago.contains(nombrePasarela)) {
            Class<?> clazz = Class.forName(nombrePasarela);
            return (IPasarelaPago) clazz.getDeclaredConstructor().newInstance();
        } else {
            throw new IllegalArgumentException("La pasarela de pago " + nombrePasarela + " no está disponible.");
        }
    }
}