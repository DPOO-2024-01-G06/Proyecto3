 package galeria.structurer_inventario;

import java.io.Serializable;

public class Oferta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3341102729069403793L;
	private double valor;
	private String metodoPago;
	private Subasta subasta;
	
	public Oferta() {
		
	}
	public Oferta(double valor, String metodoPago, Subasta subasta) {
		this.valor = valor;
		this.metodoPago = metodoPago;
		this.subasta = subasta;
	
	}

	public double getValor() {
		return valor;
	}

	public String getMetodoPago() {
		return metodoPago;
	}
	public Subasta getSubasta() {
		return subasta;
	}
}
