package galeria.structurer_usuarios;

import java.util.ArrayList;
import java.util.List;

import galeria.structurer_inventario.Venta;

public class Cajero extends Interno {
	private List<Venta> ventasPendientes;
	
	public Cajero (String nombreUsuario, String contraseña, String nombre, String celular, String correo) {
        super(nombreUsuario, contraseña, nombre, celular, correo);
		this.ventasPendientes = new ArrayList<>();
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
