package galeria.controller_galeria;

import java.util.List;

import galeria.Galeria;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_usuarios.Externo;

public class Controlador_Propietario {
	private Externo externo;
	private Galeria galeria;
	
	Controlador_Propietario(Galeria galeria, Externo externo){
		this.externo = externo;
		this.galeria = galeria;
	}
	
	public List<Pieza> getPiezasPropiedad(){
		return externo.getPropietario().getPiezasPropiedad();
	}
	public List<Pieza> getPiezasCedidas(){
		return externo.getPropietario().getPiezasCedidas();
	}
	public void cederPieza(int indice) {
		Pieza pieza = externo.getPropietario().getPiezasPropiedad().get(indice);
		externo.getPropietario().getPiezasCedidas().add(pieza);
		externo.getPropietario().getPiezasPropiedad().remove(pieza);
	}
	public void agregarPieza(String titulo, int anio, String lugarCreacion, boolean electricidad,String tiempoDisponible, String autor) {
	Pieza pieza = new Pieza(titulo, anio, lugarCreacion, electricidad, autor, autor, externo);
	getPiezasPropiedad().add(pieza);
	}
	

}
