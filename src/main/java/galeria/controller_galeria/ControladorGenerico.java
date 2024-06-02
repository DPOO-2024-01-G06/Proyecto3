package galeria.controller_galeria;

import java.util.List;

import galeria.structurer_inventario.Artista;
import galeria.structurer_inventario.Pieza;

public abstract class ControladorGenerico {
	public abstract void actualizarInfo(String contrasena, String nombre, String celular, String correo);
	public abstract List<Artista> getArtistas();
	public abstract List<Pieza> getListaPiezas();
}