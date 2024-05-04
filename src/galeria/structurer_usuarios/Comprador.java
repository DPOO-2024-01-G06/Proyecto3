package galeria.structurer_usuarios;

import java.util.ArrayList;
import java.util.List;

import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;

public class Comprador {
	private float valorMaximo;
	private boolean verificado;
	private float salario;
	private List<Venta> ventas;
	private List<Subasta> subastasGanadas;
	
	
	public Comprador(float valorMaximo, boolean verificado, float salario, List<Venta> compras,
			List<Subasta> subastasGanadas) {
		this.valorMaximo = valorMaximo;
		this.verificado = verificado;
		this.salario = salario;
		this.ventas = compras;
		this.subastasGanadas = subastasGanadas;
	}

	public void setValorMaximo(float valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

	public void setVerificado(boolean verificado) {
	    this.verificado = verificado;
	}

	public float getSalario() {
		return salario;
	}

    public List<Venta> getVentas() {
    	return ventas;
    }

	public List<Subasta> getSubastasGanadas() {
		return subastasGanadas;
    }

    public void agregarSubasta(Subasta subasta) {
        this.subastasGanadas.add(subasta);
    }

	public void agregarCompras(Venta venta) {
		this.ventas.add(venta);
	}
	public boolean getVerficiado() {
		return verificado;
	}
	public float getValorMaximo() {
		return valorMaximo;
	}

		
}
