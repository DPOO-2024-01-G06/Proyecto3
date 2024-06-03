package galeria.controller_galeria;
import galeria.pagos.*;

import java.util.ArrayList;
import java.util.List;

import galeria.Galeria;
import galeria.structurer_inventario.Artista;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Cajero;
import galeria.structurer_usuarios.Comprador;
import galeria.structurer_usuarios.Propietario;

public class ControladorCajero extends ControladorGenerico{
	private Galeria galeria;
	private Cajero cajero;
	public ControladorCajero(Galeria galeria, Cajero cajero){
		this.galeria = galeria;
		this.cajero = cajero;
	}
	public List<Venta> getVentasPendientes() {
		return cajero.getVentasPendientes();
	}
	
	public void registrarPago(int indice, String fecha) {
		Venta venta = cajero.getVentasPendientes().get(indice);
		boolean exito=false;
		InfoTarjeta infoTarjeta=venta.getInfoTarjeta();
		InfoPago infoPago=venta.getInfoPago();
		IPasarelaPago pasarela= getPasarela(venta.getMetodoPago);
		exito= pasarela.procesarPago(infoTarjeta,infoPago);
		venta.setFacturada(exito, fecha);
		cajero.getVentasPendientes().remove(indice);
		Comprador comprador =  venta.getComprador();
		if(exito) {
			Pieza pieza = venta.getPieza();
			pieza.setVenta(venta);
			Propietario propietarioActual = comprador.getExterno().getPropietario();
			agregarComprador(venta, comprador, pieza.getPropietario().getExterno().getComprador());
			setNuevoPropietario(pieza, propietarioActual);
			agregarHistorial(pieza, venta.getPrecio(), comprador.getExterno().getNombre());
			galeria.getInventarioGaleria().setVentaFacturada(venta);
		}
		else {
			venta.setComprador(null);
			comprador.getVentasPendientes().remove(venta);
			venta.setAceptada(false);		
		}
	}
	
	public void agregarHistorial(Pieza pieza, float precio, String nombre) {
		List<String> precioYFecha = new ArrayList<String>();
		precioYFecha.add(String.valueOf(precio));
		precioYFecha.add(java.time.LocalDateTime.now().toString());
		pieza.getHistorialDuenos().put(nombre, precioYFecha);
	}
	public void setNuevoPropietario(Pieza pieza, Propietario propietarioActual) {
		Propietario propietarioAntes = pieza.getPropietario();
		propietarioAntes.getPiezasCedidas().remove(pieza);
		propietarioAntes.getPiezasPasadas().add(pieza);
		pieza.setPropietario(propietarioActual);
		propietarioActual.getPiezasPropiedad().add(pieza);
		
	}
	public void agregarComprador(Venta venta, Comprador compradorActual, Comprador compradorAntiguo) {
		compradorActual.getVentasPendientes().remove(venta);
		compradorActual.getPiezasCompradas().add(venta);
		compradorActual.setValorColeccion(compradorActual.getValorColeccion() + venta.getPrecio());
		if(compradorAntiguo != null) {
			boolean encontrado = false;
			for(int i=0;!encontrado;i++) {
				if(compradorAntiguo.getPiezasCompradas().get(i).getPieza().equals(venta.getPieza())) {
					encontrado = true;
					compradorAntiguo.setValorColeccion(compradorAntiguo.getValorColeccion()-compradorAntiguo.getPiezasCompradas().get(i).getPrecio());
					compradorAntiguo.getPiezasCompradas().remove(i);
					
				}
	
					
			}
			
			
		}
	}
	@Override
	public void actualizarInfo(String contrasena, String nombre, String celular, String correo) {
		cajero.actualizarDatos(contrasena, nombre, celular, correo);
	}
	
	public List<Artista> getArtistas(){
		return galeria.getInventarioGaleria().getArtistas();
	}
	public List<Pieza> getListaPiezas(){
		List<Pieza> piezas = galeria.getInventarioGaleria().getInventario();
		return piezas;
	}
	public Cajero getCajero() {
		return cajero;
	}
	public Galeria getGaleria() {
		return galeria;
	}
}
