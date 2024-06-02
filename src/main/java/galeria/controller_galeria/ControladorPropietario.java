package galeria.controller_galeria;

import java.util.List;

import galeria.Galeria;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_usuarios.Externo;

public class ControladorPropietario extends ControladorGenerico {
	private Externo externo;
	private Galeria galeria;
	
	public ControladorPropietario(Galeria galeria, Externo externo){
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
		galeria.getUsuariosGaleria().getAdministrador().getPiezasPorAgregar().add(pieza);
	}

	@Override
	public void actualizarInfo(String contrasena, String nombre, String celular, String correo) {
		// TODO Auto-generated method stub
	}
}
