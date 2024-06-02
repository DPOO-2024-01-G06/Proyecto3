package galeria.pagos;

public class InfoPago {
	private double cantidadDinero;
	private String numeroCuenta;
	private String numeroTransaccion;
	private boolean exitoso;
	
	public InfoPago(double cantidadDinero, String numeroCuenta, String numeroTransaccion, boolean exitoso) {
		super();
		this.cantidadDinero = cantidadDinero;
		this.numeroCuenta = numeroCuenta;
		this.numeroTransaccion = numeroTransaccion;
		this.exitoso = exitoso;
	}

	public boolean isExitoso() {
		return exitoso;
	}
	public void setExitoso(boolean exitoso) {
		this.exitoso = exitoso;
	}

	public double getCantidadDinero() {
		return cantidadDinero;
	}
	public void setCantidadDinero(double cantidadDinero) {
		this.cantidadDinero	 = cantidadDinero;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getTransactionNumber() {
		return numeroTransaccion;
	}
	public void setTransactionNumber(String numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}
}
