
package galeria.structurer_inventario;

import galeria.structurer_usuarios.Externo;

public class Escultura extends Pieza{
	private double alto;
	private double ancho;
	private double profundidad;
	private double peso;
	private String material;
	private String instalacion;
	
	public Escultura(String titulo, int anio, String lugarCreacion, boolean electricidad, String tiempoDisponible,
			String autor, double alto, double ancho, double profundidad, double peso, String material,
			String instalacion, Externo externo) {
		super(titulo, anio, lugarCreacion, electricidad, tiempoDisponible, autor, externo);
		this.alto = alto;
		this.ancho = ancho;
		this.profundidad = profundidad;
		this.peso = peso;
		this.material = material;
		this.instalacion = instalacion;
	}
	
	
}
