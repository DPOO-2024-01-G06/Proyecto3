package galeria;

import galeria.controller_galeria.Coordinador_Sesion;
import galeria.structurer_inventario.Inventario_Galeria;
import galeria.structurer_usuarios.Usuarios_Galeria;

public class Galeria {
	private Usuarios_Galeria usuariosGaleria;
	private Coordinador_Sesion coordinadorSesion;
	private Inventario_Galeria inventarioGaleria;
	public Galeria(Usuarios_Galeria usuariosGaleria, Coordinador_Sesion coordinadorSesion, Inventario_Galeria inventarioGaleria) {
		this.usuariosGaleria = usuariosGaleria;
		this.coordinadorSesion = coordinadorSesion;
		this.inventarioGaleria = inventarioGaleria;
	}
	public Usuarios_Galeria getUsuariosGaleria() {
		return usuariosGaleria;
	}
	public Coordinador_Sesion getCoordinadorSesion() {
		return coordinadorSesion;
	}
	public Inventario_Galeria getInventarioGaleria() {
		return inventarioGaleria;
	}
}
