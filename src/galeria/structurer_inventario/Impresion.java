package galeria.structurer_inventario;

import java.util.List;
import java.util.Map;

import galeria.structurer_usuarios.Externo;

public class Impresion extends Pieza {
	private double alto;
	private double ancho;
	private String tipoPapel;
	
	public Impresion(String titulo, String fecha, String lugarCreacion, boolean electricidad, String tiempoDisponible,
		String autor, double alto, double ancho, String tipoPapel, Externo externo, Artista artista, Map<String, List<String>> historial) {
		super(titulo, fecha, lugarCreacion, electricidad, tiempoDisponible, externo, artista, historial);
		this.alto = alto;
		this.ancho = ancho;
		this.tipoPapel= tipoPapel;
	}
	
}
