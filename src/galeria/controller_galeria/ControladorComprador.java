package galeria.controller_galeria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import galeria.Galeria;
import galeria.structurer_inventario.Oferta;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Administrador;
import galeria.structurer_usuarios.Externo;

public class ControladorComprador {
	private Galeria galeria;
	private Externo externo;
	
	ControladorComprador(Galeria galeria, Externo externo){
		this.galeria = galeria; 
		this.externo = externo;
	}
	
	public List<Venta> getVentasDisponibles(){
		List<Venta> resultado = new ArrayList<Venta>();
		Collection<Venta> ventasPendientes = galeria.getInventarioGaleria().getVentasPendientes().values();
		for(Venta venta: ventasPendientes) {
			if(venta.getPieza().isBloqueado()) {}
			else {
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
		galeria.getInventarioGaleria().intentoVender(venta, externo);
		externo.getComprador().getVentasPendientes().add(venta);
		Administrador administrador = galeria.getUsuariosGaleria().getAdministrador();
		if(! externo.getComprador().getVerficado()) {
			administrador.getPendientesVerificar().add(externo);
			}
		if(externo.getComprador().getValorMaximo()< venta.getPrecio()) {
			administrador.getSuperaronLimite().add(externo);
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
	
}
