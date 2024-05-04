package persistencia;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PersistenciaSubastasActivas {

	private String archivoSubastasActivas;

	public PersistenciaSubastasActivas(String archivoSubastasActivas) {
		this.archivoSubastasActivas = archivoSubastasActivas;
	}

	 public static <V> void guardarVentasPendientes(Map<?, V> mapa, String archivoSubastasActivas) {
	     try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSubastasActivas))) {
	            for (V valor : mapa.values()) {
	                writer.write(valor.toString());
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static Map<Integer, String> cargarMapa(String archivoSubastasActivas) {
	        Map<Integer, String> mapa = new HashMap<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(archivoSubastasActivas))) {
	            String linea;
	            int contador = 0;
	            while ((linea = reader.readLine()) != null) {
	                mapa.put(contador++, linea);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return mapa;
	    }
}


