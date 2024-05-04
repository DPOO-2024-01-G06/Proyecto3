package persistencia;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PersistenciaInventario{

	private String archivoInventario;

	public PersistenciaInventario(String archivoInventario) {
		this.archivoInventario = archivoInventario;
	}

	 public static <V> void guardarVentasPendientes(Map<?, V> mapa, String Inventario) {
	     try (BufferedWriter writer = new BufferedWriter(new FileWriter(Inventario))) {
	            for (V valor : mapa.values()) {
	                writer.write(valor.toString());
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static Map<Integer, String> cargarMapa(String Inventario) {
	        Map<Integer, String> mapa = new HashMap<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(Inventario))) {
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

