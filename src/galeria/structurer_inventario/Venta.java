package galeria.structurer_inventario;

import galeria.structurer_usuarios.Comprador;

public class Venta {
	private float precio;
	private boolean aceptada;
	private Comprador comprador;
	private boolean facturada;
	private Pieza pieza;
	private String fecha;
	
	public Venta(float precio, boolean aceptada, boolean facturada, Pieza pieza, Comprador comprador, String fecha) {
		this.precio = precio;
		this.aceptada = aceptada;
		this.facturada = facturada;
		this.pieza = pieza;
		this.comprador = comprador;
		this.fecha = fecha;
	}
	
	public void setAceptada(boolean aceptada){
		this.aceptada =aceptada;
	}
	public void setFacturada(boolean aceptada, String fecha){
		this.facturada = aceptada;
		if(aceptada) {
			this.fecha = fecha;
		}
	}

	public float getPrecio() {
		return precio;
	}

	public boolean isAceptada() {
		return aceptada;
	}

	public boolean isFacturada() {
		return facturada;
	}

	public Pieza getPieza() {
		return pieza;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public Comprador getComprador() {
		return comprador;
	}
	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}
	
}
