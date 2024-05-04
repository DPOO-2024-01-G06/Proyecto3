package galeria.controller_galeria;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.List;

import galeria.Galeria;
import galeria.structurer_inventario.Oferta;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Interno;
import galeria.structurer_usuarios.Operador;

public class Controlador_Operador {
	private Operador operador; 
	private Galeria galeria;

	public Controlador_Operador(Galeria galeria, Operador operador){
		this.operador = operador;
		this.galeria = galeria;
	}
	public void agregarOfertaPendiente(int indice) {
		Oferta oferta = operador.getOfertasPendientes().get(indice);
		oferta.getSubasta().agregarOferta(oferta);
	}
	
	public void planearSubasta(Venta venta, double valorMinimo,LocalDateTime tiempo, double valorInicial) {
		Subasta subasta = new Subasta(valorMinimo, valorInicial, tiempo, venta.getPieza());
		galeria.getInventarioGaleria().agregarSubasta(subasta, venta);
	}
	
	public List<Oferta> getOfertasPendientes(){
		return operador.getOfertasPendientes();
	}
	
}
