package galeria.controller_galeria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import galeria.Galeria;
import galeria.structurer_inventario.Artista;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Administrador;
import galeria.structurer_usuarios.Comprador;
import galeria.structurer_usuarios.Externo;
import galeria.structurer_usuarios.Propietario;

public class ControladorAdministrador{
	private Administrador administrador;
	private Galeria galeria;
	
	public ControladorAdministrador(Galeria galeria, Administrador administrador) {
		this.administrador = administrador;
		this.galeria = galeria;
	}
	public Map<Integer, Pieza> getInventario(){
		return galeria.getInventarioGaleria().getInventario();
	}
	public List<Pieza> getPendientesPorAgregar(){
		return administrador.getPiezasPorAgregar();
	}
	public List<Venta> getVentasPendientes(){
		return administrador.getPendientesAceptar();
	}
	public List<Comprador> getUsuariosPendientes(){
		return administrador.getPendientesVerificar();
	}
	public List<Comprador> getSuperaronLimite(){
		return administrador.getSuperaronLimite();
	}
	public List<Pieza> getPiezasCedidas(){
		ArrayList<Pieza> resultado = new ArrayList<Pieza>();		
		for(Pieza pieza: getInventario().values()) {
			if(pieza.getPropietario() != null) {
				resultado.add(pieza);
			}
		}
		return resultado;
	}
	
	public void confirmarVenta(int indice, boolean aceptada) {
		Venta venta = administrador.getPendientesAceptar().get(indice);
		Comprador comprador = venta.getComprador();
		administrador.getPendientesAceptar().remove(indice);
		venta.setAceptada(aceptada);
		if(aceptada) {
			galeria.getUsuariosGaleria().getCajero().getVentasPendientes().add(venta);
		}
		else {
			venta.setComprador(null);
			comprador.getVentasPendientes().remove(venta);
			
		}
	}
	public void verificarExterno(int indice, float valorMaximo) {
		Comprador comprador = administrador.getPendientesVerificar().get(indice);
		comprador.setVerificado(true);
		comprador.setValorMaximo(valorMaximo);
		administrador.getPendientesVerificar().remove(indice);
		List<Venta> pendientes = new ArrayList<Venta>(comprador.getVentasPendientes()); 
		for(Venta venta: pendientes) {
			if(comprador.getValorMaximo()>= venta.getPrecio()) {
				administrador.getPendientesAceptar().add(venta);
			}
			else{
				venta.setAceptada(false);
				venta.setComprador(null);
				comprador.getVentasPendientes().remove(venta);
			}
		}
	}
	
	public void invalidarExterno(int indice) {
		Comprador comprador = administrador.getPendientesVerificar().get(indice);
		comprador.setVerificado(false);
		administrador.getPendientesVerificar().remove(indice);
		List<Venta> pendientes = new ArrayList<Venta>(comprador.getVentasPendientes());
		for(Venta venta: pendientes) {
			venta.setComprador(null);
			
			comprador.getVentasPendientes().remove(venta);
		}
	}
	public void ingresarPiezaCedida(int indice, float precio) {
		if(precio <= 0) {
			administrador.getPiezasPorAgregar().remove(indice);	
		}
		else {
			Pieza pieza = administrador.getPiezasPorAgregar().get(indice);
			Venta venta = new Venta(precio, false, false, pieza, null, null);
			galeria.getInventarioGaleria().	agregarPieza(venta);
			pieza.getPropietario().getPiezasPropiedad().remove(pieza);
			pieza.getPropietario().getPiezasCedidas().add(pieza);
		}
	}
	public void devolverPieza(int indice) {
		Pieza pieza = getPiezasCedidas().get(indice);
		galeria.getInventarioGaleria().devolverPieza(pieza);
		Propietario propietario = pieza.getPropietario();
		propietario.getPiezasPropiedad().add(pieza);
		propietario.getPiezasCedidas().remove(pieza);
	}
	public void reestablecerMaximo(int indice, float nLimite) {
		Comprador comprador= administrador.getSuperaronLimite().get(indice);
		comprador.setValorMaximo(nLimite);
		List<Venta> pendientes = new ArrayList<Venta>(comprador.getVentasPendientes());
		for(Venta venta: pendientes) {
			if(nLimite>= venta.getPrecio()) {
				administrador.getPendientesAceptar().add(venta);
			}
			else{
				venta.setComprador(null);
				comprador.getVentasPendientes().remove(venta);
			}
		}
	}
	
	public List<Pieza> getListaPiezas(){
		List<Pieza> piezas = new ArrayList<Pieza>(galeria.getInventarioGaleria().getInventario().values());
		return piezas;
	}
	
	public List<Comprador> getCompradores(){
		List<Comprador> resultado = new ArrayList<Comprador>();
		List<Externo> externos = galeria.getUsuariosGaleria().getExternos();
		for(Externo externo: externos) {
			if(externo.getComprador() != null) {
				resultado.add(externo.getComprador());
			}
		}
		return resultado;
	}
	
	public List<Artista> getArtistas(){
		return galeria.getInventarioGaleria().getArtistas();
	}
	
	public void actualizarInfo(String contrasena, String nombre, String celular, String correo) {
		administrador.actualizarDatos(contrasena, nombre, celular, correo);
	}
	
}
