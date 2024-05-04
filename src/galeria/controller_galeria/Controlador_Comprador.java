package galeria.controller_galeria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import galeria.Galeria;
import galeria.structurer_inventario.Oferta;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Externo;

public class Controlador_Comprador {
	private Galeria galeria;
	private Externo externo;
	private Coordinador_Usuarios coordinadorUsuarios;
	
	Controlador_Comprador(Galeria galeria, Externo externo){
		this.galeria = galeria; 
		this.externo = externo;
		coordinadorUsuarios = new Coordinador_Usuarios(galeria);
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
	
	public Collection<Subasta> getSubastasDisponibles(){
		return galeria.getInventarioGaleria().getSubastasPendientes().values();
		
	}
	
	public void intentoComprar(int indice) {
		Venta venta = getVentasDisponibles().get(indice);
		venta.setExterno(externo);
		galeria.getInventarioGaleria().intentoVender(venta);
	}
	
	public void ofertar(int indice, double precio, String metodoPago) {
		List<Subasta> subastasPendientes = (List<Subasta>) galeria.getInventarioGaleria().getSubastasPendientes().values();
		Subasta subasta = subastasPendientes.get(indice);
		Oferta oferta = new Oferta(precio, metodoPago, subasta);
		if(externo.getComprador().getVerficiado()) {
		coordinadorUsuarios.Ofertar(externo, oferta);
		}
	}
	
}
