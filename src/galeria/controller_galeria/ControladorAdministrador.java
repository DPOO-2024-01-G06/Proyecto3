package galeria.controller_galeria;

import java.util.List;
import java.util.Map;

import galeria.Galeria;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Administrador;
import galeria.structurer_usuarios.Comprador;
import galeria.structurer_usuarios.Externo;

public class ControladorAdministrador {
	private Administrador administrador;
	private Galeria galeria;
	
	public ControladorAdministrador(Galeria galeria, Administrador administrador) {
		this.administrador = administrador;
		this.galeria = galeria;
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
		Externo externo = venta.getExterno();
		administrador.getPendientesAceptar().remove(indice);
		galeria.getInventarioGaleria().venderPieza(venta, aceptada);
		if(aceptada) {
			galeria.getUsuariosGaleria().getCajero().getVentasPendientes().add(venta);
		}
		else {
			externo.getComprador().getVentasPendientes().remove(venta);
		}
	}
	public void verificarExterno(int indice, float valorMaximo) {
		Externo externo = administrador.getPendientesVerificar().get(indice);
		Comprador comprador = externo.getComprador();
		comprador.setVerificado(true);
		comprador.setValorMaximo(valorMaximo);
		administrador.getPendientesVerificar().remove(indice);
		for(Venta venta: comprador.getVentasPendientes()) {
			if(comprador.getValorMaximo()>= venta.getPrecio()) {
				administrador.getPendientesAceptar().add(venta);
			}
			else{
				galeria.getInventarioGaleria().venderPieza(venta, false);
				comprador.getVentasPendientes().remove(venta);
			}
		}
	}
	
	public void invalidarExterno(int indice) {
		Externo externo = administrador.getPendientesVerificar().get(indice);
		Comprador comprador = externo.getComprador();
		comprador.setVerificado(false);
		administrador.getPendientesVerificar().remove(indice);
		for(Venta venta: comprador.getVentasPendientes()) {
			galeria.getInventarioGaleria().venderPieza(venta, false);
			comprador.getVentasPendientes().remove(venta);
		}
	}
	public void ingresarPiezaCedida(int indice, double precio) {
		Pieza pieza = administrador.getPiezasPorAgregar().get(indice);
		Venta venta = new Venta(precio, false, false, pieza);
		galeria.getInventarioGaleria().agregarPieza(venta);
	}
	public void devolverPieza(Venta venta) {
		galeria.getInventarioGaleria().devolverPieza(venta);
		Externo externo = venta.getExterno();
		externo.getPropietario().getPiezasPropiedad().add(venta.getPieza());
		externo.getPropietario().getPiezasCedidas().remove(venta.getPieza());
	}
	public void reestablecerMaximo(int indice, float nLimite) {
		Externo externo = administrador.getSuperaronLimite().get(indice);
		Comprador comprador = externo.getComprador();
		comprador.setValorMaximo(nLimite);
		for(Venta venta: comprador.getVentasPendientes()) {
			if(nLimite>= venta.getPrecio()) {
				administrador.getPendientesAceptar().add(venta);
			}
			else{
				galeria.getInventarioGaleria().venderPieza(venta, false);
				comprador.getVentasPendientes().remove(venta);
			}
		}
	}
	
	public void actualizarInfo(String contrasena, String nombre, String celular, String correo) {
		administrador.actualizarDatos(contrasena, nombre, celular, correo);
	}
	
}
