package galeria.structurer_usuarios;

import java.util.List;


import galeria.structurer_inventario.Venta;

public class Cajero extends Interno {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9026241343904381467L;
	private List<Venta> ventasPendientes;
	
	public Cajero() {
		
	}
	public Cajero (String nombreUsuario, String contraseña, String nombre, String celular, String correo, List<Venta> ventasPendientes) {
        super(nombreUsuario, contraseña, nombre, celular, correo);
		this.ventasPendientes = ventasPendientes;
	}
	
	public void registrarPago(Venta venta) {
    }

	public void agregarVenta(Venta venta) {
		this.ventasPendientes.add(venta);
	}
	@Override
	public String getTipoInterno() {
		return "cajero";
	}
	public List<Venta> getVentasPendientes(){
		return ventasPendientes;
	}
}
