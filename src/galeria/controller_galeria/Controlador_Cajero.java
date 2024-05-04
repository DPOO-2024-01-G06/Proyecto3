package galeria.controller_galeria;

import java.util.List;

import galeria.Galeria;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Cajero;

public class Controlador_Cajero {
	private Galeria galeria;
	private Cajero cajero;
	Controlador_Cajero(Galeria galeria, Cajero cajero){
		this.galeria = galeria;
		this.cajero = cajero;
	}
	public List<Venta> getVentasPendientes() {
		return cajero.getVentasPendientes();
	}
	
	public void registrarPago(int indice, boolean exito) {
		Venta venta = cajero.getVentasPendientes().get(indice);
		galeria.getInventarioGaleria().facturada(venta, exito);
	}
	
	
	
}
