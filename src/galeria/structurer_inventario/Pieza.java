  package galeria.structurer_inventario;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import galeria.structurer_usuarios.Propietario;

public abstract class Pieza {
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
	private String titulo;
	private String fecha;
	private String lugarCreacion;
	private boolean electricidad;
	private boolean exhibicion;
	private String tiempoDisponible;
	private Propietario propietario;
	private Artista artista;
	private Venta venta;
	private Map<String, List<String>> historialDuenos;

	public Pieza(String titulo, String fecha, String lugarCreacion, boolean electricidad,String tiempoDisponible, Propietario propietario, Artista artista, 
			Map<String, List<String>> historialDuenos, Venta venta) {
		this.titulo =titulo;
		this.fecha= fecha;
		this.lugarCreacion=lugarCreacion;
		this.electricidad = electricidad;
		this.exhibicion = true;
		this.tiempoDisponible = tiempoDisponible;
		this.propietario = propietario;
		this.artista = artista;
		this.historialDuenos = historialDuenos;
		this.venta = venta;
		artista.addPieza(this);
	}
		public void setExhibicion(boolean exhibicion) {
			this.exhibicion = exhibicion;
		}
		public void setPropietario(Propietario propietario) {
			this.propietario = propietario;
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
		public Propietario getPropietario() {
			return propietario;
		}
		public boolean isElectricidad() {
			return electricidad;
		}
		public Map<String, List<String>> getHistorialDuenos(){
			return historialDuenos;
		}
		public String getInfoPieza() {
			String resultado = "Titulo: " + titulo + "\n"
							 + "Fecha de creación: " + fecha + "\n"
							 + "Lugar de creación: " + lugarCreacion + "\n"
							 + "Propietario: " + propietario.getExterno().getNombre() + "\n"
							 + "Autor: " + artista.getNombre();
			return resultado;
		}
		public Venta getVenta() {
			return venta;
		}
		public void setVenta(Venta venta) {
			this.venta = venta; 
		}
		public String getFecha() {
			return fecha;
		}
}
