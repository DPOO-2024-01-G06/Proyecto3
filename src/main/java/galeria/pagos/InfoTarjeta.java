package galeria.pagos;

public class InfoTarjeta {
	private String titular;
	private String numeroTarjeta;
	private String fechaExpiracion;
	private double dineroDisponible;
	private String cvv;
	

	public InfoTarjeta(String titular, String numeroTarjeta, String fechaExpiracion, double dineroDisponible,
			String cvv) {
		this.titular = titular;
		this.numeroTarjeta = numeroTarjeta;
		this.fechaExpiracion = fechaExpiracion;
		this.dineroDisponible = 50000 + Math.random();
		this.cvv = cvv;
	}

	public double getDineroDisponible() {
		return dineroDisponible;
	}

	public void setDineroDisponible(double dineroDisponible) {
		this.dineroDisponible = dineroDisponible;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getFechaExpiracion() {
		return fechaExpiracion;
	}
	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
}
