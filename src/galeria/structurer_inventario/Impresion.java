package galeria.structurer_inventario;

import java.util.List;
import java.util.Map;

import galeria.structurer_usuarios.Propietario;

public class Impresion extends Pieza {
	private double alto;
	private double ancho;
	private String tipoPapel;
	
	public Impresion(String titulo, String fecha, String lugarCreacion, boolean electricidad, String tiempoDisponible,
		String autor, double alto, double ancho, String tipoPapel, Propietario propietario, Artista artista, Map<String, List<String>> historial, Venta venta) {
		super(titulo, fecha, lugarCreacion, electricidad, tiempoDisponible, propietario, artista, historial, venta);
		this.alto = alto;
		this.ancho = ancho;
		this.tipoPapel= tipoPapel;
	}

	public double getAlto() {
		return alto;
	}

	public double getAncho() {
		return ancho;
	}

	public String getTipoPapel() {
		return tipoPapel;
	}
	
	
	
}
