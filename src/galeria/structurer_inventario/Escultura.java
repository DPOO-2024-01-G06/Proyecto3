
package galeria.structurer_inventario;

import java.util.List;
import java.util.Map;

import galeria.structurer_usuarios.Externo;

public class Escultura extends Pieza{
	private double alto;
	private double ancho;
	private double profundidad;
	private double peso;
	private String material;
	private String instalacion;
	
	public Escultura(String titulo, String fecha, String lugarCreacion, boolean electricidad, String tiempoDisponible,
			String autor, double alto, double ancho, double profundidad, double peso, String material,
			String instalacion, Externo externo, Artista artista, Map<String, List<String>> historial) {
		super(titulo, fecha, lugarCreacion, electricidad, tiempoDisponible, externo, artista, historial);
		this.alto = alto;
		this.ancho = ancho;
		this.profundidad = profundidad;
		this.peso = peso;
		this.material = material;
		this.instalacion = instalacion;
	}
	
	
}
