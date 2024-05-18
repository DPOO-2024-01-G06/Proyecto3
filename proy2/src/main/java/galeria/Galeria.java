package galeria;

import java.io.Serializable;

import galeria.structurer_inventario.InventarioGaleria;
import galeria.structurer_usuarios.UsuariosGaleria;

public class Galeria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8616154029593689949L;
	private UsuariosGaleria usuariosGaleria;
	private InventarioGaleria inventarioGaleria;
	public Galeria(UsuariosGaleria usuariosGaleria, InventarioGaleria inventarioGaleria) {
		this.usuariosGaleria = usuariosGaleria;
		this.inventarioGaleria = inventarioGaleria;
	}
	public UsuariosGaleria getUsuariosGaleria() {
		return usuariosGaleria;
	}
	public InventarioGaleria getInventarioGaleria() {
		return inventarioGaleria;
	}
	
}
