package galeria.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaOfertas {
	private String archivoOfertas;

	public PersistenciaOfertas(String archivoOfertas) {
		this.archivoOfertas = archivoOfertas;
	}
    public static <T> void guardarLista(List<T> lista, String archivoOfertas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoOfertas))) {
            for (T elemento : lista) {
                writer.write(elemento.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> cargarLista(String archivoOfertas) {
        List<String> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoOfertas))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lista.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
