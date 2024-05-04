package galeria.controller_galeria;

import java.util.List;
import java.util.Map;

import galeria.Galeria;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Administrador;
import galeria.structurer_usuarios.Externo;

public class Controlador_Administrador {
	private Administrador administrador;
	private Galeria galeria;
	private Coordinador_Usuarios coordinadorUsuarios;
	
	public Controlador_Administrador(Galeria galeria, Administrador administrador) {
		this.administrador = administrador;
		this.galeria = galeria;
		coordinadorUsuarios = new Coordinador_Usuarios(galeria);
	}
	public Map<Integer, Pieza> getInventario(){
		return galeria.getInventarioGaleria().getInventario();
	}
	public List<Venta> getVentasPendientes(){
		return administrador.getPendientesAceptar();
	}
	public List<Externo> getUsuariosPendientes(){
		return administrador.getPendientesVerificar();
	}
	public List<Externo> getSuperaronLimite(){
		return administrador.getSuperaronLimite();
	}
	public void confirmarVenta(int indice, boolean aceptada) {
		Venta venta = administrador.getPendientesAceptar().get(indice);
		administrador.getPendientesAceptar().remove(indice);
		galeria.getInventarioGaleria().venderPieza(venta, aceptada);
		if(aceptada) {
		coordinadorUsuarios.aceptarVenta(venta);
		}
	}
	public void verificarExterno(int indice) {
		Externo externo = administrador.getPendientesVerificar().get(indice);
		externo.getComprador().setVerificado(true);
		administrador.getPendientesVerificar().remove(indice);
	}
	
	public void invalidarExterno(int indice) {
		Externo externo = administrador.getPendientesVerificar().get(indice);
		externo.getComprador().setVerificado(false);
		administrador.getPendientesVerificar().remove(indice);
	}
	public void ingresarPiezaCedida(int indice, double precio) {
		Pieza pieza = administrador.getPiezasPorAgregar().get(indice);
		Venta venta = new Venta(precio, false, false, pieza);
		galeria.getInventarioGaleria().agregarPieza(venta);
	}
	public void devolverPieza(int hashCode) {
		Pieza pieza = galeria.getInventarioGaleria().getInventario().get(hashCode);
		galeria.getInventarioGaleria().getInventario().remove(pieza);
		coordinadorUsuarios.devolverPieza(pieza);
	}
	public void reestablecerMaximo(int indice, float nLimite) {
		Externo externo = administrador.getSuperaronLimite().get(indice);
		coordinadorUsuarios.reestablecerMaximo(externo, nLimite);
	}
	
}
