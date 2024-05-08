package galeria.structurer_usuarios;

import java.util.List;

import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;

public class Comprador {
	private float valorMaximo;
	private boolean verificado;
	private List<Venta> ventasPendientes;
	private float salario;
	private Externo externo;
	private List<Subasta> subastasPendientes;
	private float valorColeccion;
	private List<Venta> piezasCompradas; 
	
	
	public Comprador(float valorMaximo, boolean verificado, List<Venta> ventasPendientes, float salario, Externo externo, 
					 List<Subasta> subastasPendientes, float valorColeccion, List<Venta> piezasCompradas) {
		this.valorMaximo = valorMaximo;
		this.verificado = verificado;
		this.ventasPendientes = ventasPendientes;
		this.salario = salario;
		this.externo = externo;
		this.subastasPendientes = subastasPendientes;
		this.valorColeccion = valorColeccion;
		this.piezasCompradas = piezasCompradas;
	}

	public void setValorMaximo(float valorMaximo) {
        this.valorMaximo = valorMaximo;
    }
	public void setVerificado(boolean verificado) {
	    this.verificado = verificado;
	}
	public void setValorColeccion(float valorColeccion) {
		this.valorColeccion = valorColeccion;
	}
	public float getValorMaximo() {
		return valorMaximo;
	}
	public boolean getVerficado() {
		return verificado;
	}
	public List<Venta> getVentasPendientes(){
		return ventasPendientes;
	}
	
	public float getSalario() {
		return salario;
	}
	public Externo getExterno() {
		return externo;
	}
	public List<Subasta> getSubastasPendientes(){
		return subastasPendientes;
	}
	public float getValorColeccion() {
		return valorColeccion;
	}
	public List<Venta> getPiezasCompradas(){
		return piezasCompradas;
	}
}
