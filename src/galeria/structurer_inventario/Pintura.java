package galeria.structurer_inventario;

import java.util.List;
import java.util.Map;
import galeria.structurer_usuarios.Propietario;

public class Pintura extends Pieza {
	private double ancho;
	private double alto;
	private String tipoLienzo;
	
	public Pintura(String titulo, String fecha, String lugarCreacion, boolean electricidad, String tiempoDisponible,
			String autor, double ancho, double alto, String tipoLienzo, Propietario propietario, Artista artista, Map<String, List<String>> historial, Venta venta) {
		super(titulo, fecha, lugarCreacion, electricidad, tiempoDisponible, propietario, artista, historial, venta);
		this.ancho = ancho;
		this.alto = alto;
		this.tipoLienzo = tipoLienzo;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}

	public String getTipoLienzo() {
		return tipoLienzo;
	}

}
