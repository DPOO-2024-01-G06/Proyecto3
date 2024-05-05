package galeria.structurer_usuarios;

import java.util.List;

import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;

public class Comprador {
	private float valorMaximo;
	private boolean verificado;
	private List<Venta> ventasGanadas;
	private List<Venta> ventasPendientes;
	private List<Subasta> subastasGanadas;
	private float salario;
	
	
	
	public Comprador(float valorMaximo, boolean verificado, List<Venta> compras,
			List<Subasta> subastasGanadas, List<Venta> ventasPendientes, float salario) {
		this.valorMaximo = valorMaximo;
		this.verificado = verificado;
		this.ventasGanadas = compras;
		this.subastasGanadas = subastasGanadas;
		this.ventasPendientes = ventasPendientes;
		this.salario = salario;
	}

	public void setValorMaximo(float valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

	public void setVerificado(boolean verificado) {
	    this.verificado = verificado;
	}

    public List<Venta> getVentasGanadas() {
    	return ventasGanadas;
    }
	public List<Venta> getVentasPendientes(){
		return ventasPendientes;
	}
	public List<Subasta> getSubastasGanadas() {
		return subastasGanadas;
    }
	public boolean getVerficado() {
		return verificado;
	}
	public float getValorMaximo() {
		return valorMaximo;
	}
	public float getSalario() {
		return salario;
	}
}
