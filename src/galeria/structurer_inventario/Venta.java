package galeria.structurer_inventario;

import galeria.structurer_usuarios.Externo;

public class Venta {
	private double precio;
	private boolean aceptada;
	private Externo externo;
	private boolean facturada;
	private Pieza pieza;
	
	public Venta(double precio, boolean aceptada, boolean facturada, Pieza pieza) {
		this.precio = precio;
		this.aceptada = aceptada;
		this.facturada = facturada;
		this.pieza = pieza;
	}
	
	public void setAceptada(boolean aceptada){
		this.aceptada =aceptada;
	}
	public void setFacturada(boolean aceptada){
		this.aceptada =aceptada;
	}

	public double getPrecio() {
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
	
	public Externo getExterno() {
		return externo;
	}
	public void setExterno(Externo externo) {
		this.externo = externo;
	}

	public void setPiezaz(Pieza pieza) {
		this.pieza = pieza;
	}
	
}
