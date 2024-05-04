package persistencia;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PersistenciaSubastasPasadas {

	private String archivoSubastasPasadas;

	public PersistenciaSubastasPasadas(String archivoSubastasPasadas) {
		this.archivoSubastasPasadas = archivoSubastasPasadas;
	}

	 public static <V> void guardarVentasPendientes(Map<?, V> mapa, String archivoSubastasPasadas) {
	     try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSubastasPasadas))) {
	            for (V valor : mapa.values()) {
	                writer.write(valor.toString());
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static Map<Integer, String> cargarMapa(String archivoSubastasPasadas) {
	        Map<Integer, String> mapa = new HashMap<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(archivoSubastasPasadas))) {
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

