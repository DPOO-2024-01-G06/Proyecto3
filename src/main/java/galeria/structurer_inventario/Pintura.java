package galeria.structurer_inventario;


import java.util.List;
import java.util.Map;


import galeria.structurer_usuarios.Propietario;


public class Pintura extends Pieza{
	private float ancho;
	private float alto;
	private String type = "pintura";
	private String tipoLienzo;
	public Pintura() {}
	public Pintura(String titulo, String fecha, String lugarCreacion, boolean electricidad, String tiempoDisponible,
			float ancho, float alto, String tipoLienzo, Propietario propietario, Artista artista, Map<String, List<String>> historial, Venta venta) {
		super(titulo, fecha, lugarCreacion, electricidad, tiempoDisponible, propietario, artista, historial, venta);
		this.ancho = ancho;
		this.alto = alto;
		this.tipoLienzo = tipoLienzo;
	}

	public float getAncho() {
		return ancho;
	}

	public float getAlto() {
		return alto;
	}

	public String getTipoLienzo() {
		return tipoLienzo;
	}
	public String getType() {
		return type;
	}

}
