package galeria.controller_galeria;

import galeria.Galeria;
import galeria.structurer_inventario.Oferta;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Administrador;
import galeria.structurer_usuarios.Cajero;
import galeria.structurer_usuarios.Externo;

public class Coordinador_Usuarios {
	
	private Galeria galeria;
	Coordinador_Usuarios(Galeria galeria){
		this.galeria = galeria;
	}
	
	public void rechazarVenta(Venta venta) {
		Externo externo = venta.getExterno();
		venta.setExterno(null);
		externo.getComprador().getVentas().remove(venta);
	}
	
	public void aceptarVenta(Venta venta) {
		Cajero cajero = galeria.getUsuariosGaleria().getCajero();
		cajero.getVentasPendientes().add(venta);
	}
	public void devolverPieza(Pieza pieza) {
		Externo externo = pieza.getExterno();
		externo.getPropietario().getPiezasPropiedad().add(pieza);
		externo.getPropietario().getPiezasCedidas().remove(pieza);
	}
	public void reestablecerMaximo(Externo externo, float nLimite) {
		externo.getComprador().setValorMaximo(nLimite);
	}
	public void intentoComprar(Externo externo, Venta venta) {
		Administrador administrador = galeria.getUsuariosGaleria().getAdministrador();
	if(! externo.getComprador().getVerficiado()) {
		administrador.getPendientesVerificar().add(externo);
		}
	if(externo.getComprador().getValorMaximo()< venta.getPrecio()) {
		administrador.getSuperaronLimite().add(externo);
	}
	administrador.getPendientesAceptar().add(venta);
	}
	public void Ofertar(Externo externo, Oferta oferta) {
		galeria.getUsuariosGaleria().getOperador().agregarOfertaPendiente(oferta);
		
	}
	public void cederPieza(Pieza pieza) {
		galeria.getUsuariosGaleria().getAdministrador().getPiezasPorAgregar().add(pieza);
	}
	

}
