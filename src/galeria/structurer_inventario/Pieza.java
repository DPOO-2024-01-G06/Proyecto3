  package galeria.structurer_inventario;

import java.util.Objects;

import galeria.structurer_usuarios.Externo;

public class Pieza {
	@Override
	public int hashCode() {
		return Objects.hash(titulo, autor);
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
		return Objects.equals(titulo, other.titulo) && Objects.equals(autor, other.autor);
	}
	protected final String titulo;
	protected final int anio;
	protected final String lugarCreacion;
	protected final boolean electricidad;
	protected boolean exhibicion;
	protected String tiempoDisponible;
	protected boolean bloqueado;
	protected final String autor;
	private Externo externo;

	public Pieza(String titulo, int anio, String lugarCreacion, boolean electricidad,String tiempoDisponible, String autor, Externo externo) {
		this.titulo =titulo;
		this.anio= anio;
		this.lugarCreacion=lugarCreacion;
		this.electricidad = electricidad;
		this.exhibicion = true;
		this.tiempoDisponible = tiempoDisponible;
		this.bloqueado = false;
		this.externo = externo;
		this.autor = autor;
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
			return autor;
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
		
		
}
