package galeria.controller_galeria;

import java.util.ArrayList;
import java.util.List;


import galeria.Galeria;
import galeria.structurer_inventario.Artista;
import galeria.structurer_inventario.Oferta;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Administrador;
import galeria.structurer_usuarios.Comprador;
import galeria.structurer_usuarios.Externo;

public class ControladorComprador extends ControladorGenerico {
	private Galeria galeria;
	private Externo externo;
	private Comprador comprador;
	
	public ControladorComprador(Galeria galeria, Externo externo){
		this.galeria = galeria; 
		this.externo = externo;
		comprador = externo.getComprador();
	}
	
	public List<Venta> getVentasDisponibles(){
		List<Venta> resultado = new ArrayList<Venta>();
		List<Venta> pendientes = galeria.getInventarioGaleria().getVentasPendientes();
		for(Venta venta: pendientes) {
			if(venta.getComprador() == null) {
				resultado.add(venta);
			}
		}
		return resultado;
	}
	
	public List<Subasta> getSubastasDisponibles(){
		return galeria.getInventarioGaleria().getSubastasPendientes();
	}
	
	public void intentoComprar(int indice) {
		Venta venta = getVentasDisponibles().get(indice);
		venta.setComprador(comprador);
		comprador.getVentasPendientes().add(venta);
		Administrador administrador = galeria.getUsuariosGaleria().getAdministrador();
		if(! comprador.getVerificado()) {
			administrador.getPendientesVerificar().add(comprador);
			}
		if(externo.getComprador().getValorMaximo()< venta.getPrecio()) {
			administrador.getSuperaronLimite().add(comprador);
		}
		else administrador.getPendientesAceptar().add(venta);
		}
	
	public void ofertar(int indice, double precio, String metodoPago) {
		List<Subasta> subastasPendientes = getSubastasDisponibles();
		Subasta subasta = subastasPendientes.get(indice);
		Oferta oferta = new Oferta(precio, metodoPago, subasta);
		if(externo.getComprador().getVerificado()) {
			galeria.getUsuariosGaleria().getOperador().agregarOfertaPendiente(oferta);
			comprador.getSubastasPendientes().add(subasta);
		}
	}
	
	
	public List<Pieza> getListaPiezas(){
		return galeria.getInventarioGaleria().getInventario();
	}
	
	public List<Artista> getArtistas(){
		return galeria.getInventarioGaleria().getArtistas();
	}
	@Override
	public void actualizarInfo(String contrasena, String nombre, String celular, String correo) {
		comprador.getExterno().actualizarDatos(contrasena, nombre, celular, correo);
	}
	
	
	
	public Comprador getComprador() {
		return this.comprador;
	}
}
