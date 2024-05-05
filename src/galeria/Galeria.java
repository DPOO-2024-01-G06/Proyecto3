package galeria;

import galeria.structurer_inventario.InventarioGaleria;
import galeria.structurer_usuarios.UsuariosGaleria;

public class Galeria {
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
