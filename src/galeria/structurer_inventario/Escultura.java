
package galeria.structurer_inventario;

import java.util.List;
import java.util.Map;
import galeria.structurer_usuarios.Propietario;

public class Escultura extends Pieza{
	private float alto;
	private float ancho;
	private float profundidad;
	private float peso;
	private String material;
	private String instalacion;
	
	public Escultura(String titulo, String fecha, String lugarCreacion, boolean electricidad, String tiempoDisponible,
			float alto, float ancho, float profundidad, float peso, String material,
			String instalacion, Propietario propietario, Artista artista, Map<String, List<String>> historial, Venta venta) {
		super(titulo, fecha, lugarCreacion, electricidad, tiempoDisponible, propietario, artista, historial, venta);
		this.alto = alto;
		this.ancho = ancho;
		this.profundidad = profundidad;
		this.peso = peso;
		this.material = material;
		this.instalacion = instalacion;
	}

	public float getAlto() {
		return alto;
	}

	public float getAncho() {
		return ancho;
	}

	public float getProfundidad() {
		return profundidad;
	}

	public float getPeso() {
		return peso;
	}

	public String getMaterial() {
		return material;
	}

	public String getInstalacion() {
		return instalacion;
	}
}

