package persistencia;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;


public class PersistenciaVentasPendientes {
	private String archivoVentasPendientes;

	public PersistenciaVentasPendientes(String archivoVentasPendientes) {
		this.archivoVentasPendientes = archivoVentasPendientes;
	}

	 public static <V> void guardarVentasPendientes(Map<?, V> mapa, String archivoVentasPendientes) {
	     try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoVentasPendientes))) {
	            for (V valor : mapa.values()) {
	                writer.write(valor.toString());
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static Map<Integer, String> cargarMapa(String archivoVentasPendientes) {
	        Map<Integer, String> mapa = new HashMap<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(archivoVentasPendientes))) {
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

	 