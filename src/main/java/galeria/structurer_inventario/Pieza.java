package galeria.structurer_inventario;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import java.io.Serializable;
import galeria.structurer_usuarios.Propietario;

@SuppressWarnings("serial")
public abstract class Pieza implements Serializable {

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pieza other = (Pieza) obj;
		return Objects.equals(titulo, other.getTitulo()) && Objects.equals(artista.getNombre(), other.getAutor());
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
	public Pieza() {}
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
		public Artista getArtista() {
			return artista;
		}
		public void setArtista(Artista artista) {
			this.artista = artista;
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
