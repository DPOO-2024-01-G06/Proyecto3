  package galeria.structurer_inventario;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import galeria.structurer_usuarios.Externo;

public class Pieza {
	@Override
	public int hashCode() {
		return Objects.hash(titulo, artista.getNombre());
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pieza other = (Pieza) obj;
		return Objects.equals(titulo, other.titulo) && Objects.equals(artista.getNombre(), other.artista.getNombre());
	}
	protected final String titulo;
	protected final String fecha;
	protected final String lugarCreacion;
	protected final boolean electricidad;
	protected boolean exhibicion;
	protected String tiempoDisponible;
	protected boolean bloqueado;
	private Externo externo;
	private final Artista artista;
	private Map<String, List<String>> historial;

	public Pieza(String titulo, String fecha, String lugarCreacion, boolean electricidad,String tiempoDisponible, Externo externo, Artista artista, Map<String, List<String>> historial) {
		this.titulo =titulo;
		this.fecha= fecha;
		this.lugarCreacion=lugarCreacion;
		this.electricidad = electricidad;
		this.exhibicion = true;
		this.tiempoDisponible = tiempoDisponible;
		this.bloqueado = false;
		this.externo = externo;
		this.artista = artista;
		this.historial = historial;
	}
		public void setExhibicion(boolean exhibicion) {
			this.exhibicion = exhibicion;
		}
		public void setBloqueado(boolean bloqueado) {
			this.bloqueado = bloqueado;
		}
	
		public String getTitulo() {
			return titulo;
		}
		public String getAutor() {
			return artista.getNombre();
		}
		public boolean isExhibicion() {
			return exhibicion;
		}
		public String getTiempoDisponible() {
			return tiempoDisponible;
		}
		public boolean isBloqueado() {
			return bloqueado;
		}
		public Externo getExterno() {
			return externo;
		}
		public Map<String, List<String>> getHistorial(){
			return historial;
		}
		
		
}
