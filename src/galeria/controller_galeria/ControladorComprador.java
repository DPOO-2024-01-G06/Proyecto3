package galeria.controller_galeria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import galeria.Galeria;
import galeria.structurer_inventario.Oferta;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Administrador;
import galeria.structurer_usuarios.Comprador;
import galeria.structurer_usuarios.Externo;

public class ControladorComprador {
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
		Collection<Venta> ventasPendientes = galeria.getInventarioGaleria().getVentasPendientes().values();
		for(Venta venta: ventasPendientes) {
			if(venta.getComprador() == null) {
				resultado.add(venta);
			}
		}
		return resultado;
	}
	
	public List<Subasta> getSubastasDisponibles(){
		return (List<Subasta>)galeria.getInventarioGaleria().getSubastasPendientes().values();
	}
	
	public void intentoComprar(int indice) {
		Venta venta = getVentasDisponibles().get(indice);
		venta.setComprador(comprador);
		comprador.getVentasPendientes().add(venta);
		Administrador administrador = galeria.getUsuariosGaleria().getAdministrador();
		if(! comprador.getVerficado()) {
			administrador.getPendientesVerificar().add(comprador);
			}
		if(externo.getComprador().getValorMaximo()< venta.getPrecio()) {
			administrador.getSuperaronLimite().add(comprador);
		}
		else administrador.getPendientesAceptar().add(venta);
		}
	
	public void ofertar(int indice, double precio, String metodoPago) {
		List<Subasta> subastasPendientes = (List<Subasta>) galeria.getInventarioGaleria().getSubastasPendientes().values();
		Subasta subasta = subastasPendientes.get(indice);
		Oferta oferta = new Oferta(precio, metodoPago, subasta);
		if(externo.getComprador().getVerficado()) {
			galeria.getUsuariosGaleria().getOperador().agregarOfertaPendiente(oferta);
			
		}
	}
	
	
	public void actualizarContraseña(String contrasena) {
		externo.actualizarContraseña(contrasena);
	}
	
	public void actualizarCorreo(String correo) {
		externo.actualizarCorreo(correo);
	}
	
	public void actualizarCelular(String celular) {
		externo.actualizarCelular(celular);
	}
	
	public Comprador getComprador() {
		return this.comprador;
	}
}
