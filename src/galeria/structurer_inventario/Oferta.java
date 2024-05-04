 package galeria.structurer_inventario;

public class Oferta {
	private double valor;
	private String metodoPago;
	private Subasta subasta;
	
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
