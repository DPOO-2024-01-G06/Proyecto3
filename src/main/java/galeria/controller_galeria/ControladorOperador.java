package galeria.controller_galeria;

import java.util.ArrayList;
import java.util.List;

import galeria.Galeria;
import galeria.structurer_inventario.Oferta;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Operador;
import galeria.structurer_inventario.Artista;

public class ControladorOperador extends ControladorGenerico {
	private Operador operador; 
	private Galeria galeria;

	public ControladorOperador(Galeria galeria, Operador operador){
		this.galeria = galeria;
		this.operador=operador;
	}
	public void agregarOfertaPendiente(int indice) {
		Oferta oferta = operador.getOfertasPendientes().get(indice);
		oferta.getSubasta().agregarOferta(oferta);
		operador.getOfertasPendientes().remove(indice);}
	
	public void planearSubasta(Venta venta, float valorMinimo , ArrayList<Oferta> ofertas,String tiempo, float valorInicial) {
		Subasta subasta = new Subasta(valorMinimo, valorInicial,ofertas, tiempo,venta.getPieza(), null);
		galeria.getInventarioGaleria().agregarSubasta(subasta, venta);
	}

	public List<Venta> getVentasPendientes(){
		List<Venta> resultado =new ArrayList<Venta>();
		for(Venta venta:galeria.getInventarioGaleria().getVentasPendientes()) {
			if (venta.getComprador()==null) {
				resultado.add(venta);
			}
		}
		return resultado;
	}
	
	@Override
	public void actualizarInfo(String contrasena, String nombre, String celular, String correo) {
		operador.actualizarDatos(contrasena, nombre, celular, correo);
	}
	
	public List<Oferta> getOfertasPendientes(){
		return operador.getOfertasPendientes();
	}
	
	public List<Artista> getArtistas(){
		return galeria.getInventarioGaleria().getArtistas();
	}
	public List<Pieza> getListaPiezas(){
		List<Pieza> piezas = galeria.getInventarioGaleria().getInventario();
		return piezas;
	}
	public Operador getOperador() {
		return operador;
	}
}
