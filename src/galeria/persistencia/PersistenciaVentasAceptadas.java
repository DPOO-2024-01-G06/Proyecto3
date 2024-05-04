package galeria.persistencia;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import galeria.structurer_inventario.Venta;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PersistenciaVentasAceptadas {

	private String archivoSubastasPasadas;
	private String archivoVentasAceptadas;

	public PersistenciaVentasAceptadas(String archivoVentasAceptadas) {
		this.archivoVentasAceptadas = archivoVentasAceptadas;
	}

	 public static <V> void guardarVentasAceptadas(Map<?, V> mapa, String archivoVentasAceptadas) {
	     try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoVentasAceptadas))) {
	            for (V valor : mapa.values()) {
	                writer.write(valor.toString());
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static Map<Integer, String> cargarMapa(String archivoVentasAceptadas) {
	        Map<Integer, String> mapa = new HashMap<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(archivoVentasAceptadas))) {
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
