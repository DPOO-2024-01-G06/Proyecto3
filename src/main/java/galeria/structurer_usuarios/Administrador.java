package galeria.structurer_usuarios;

import java.util.List;


import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Venta;

public class Administrador extends Interno{
	private List<Pieza> piezasPorAgregar;
    private List<Comprador> pendientesVerificar;	
    private List<Venta> pendientesAceptar;
    private List<Comprador> superaronLimite;
	
    public Administrador(String nombreUsuario, String contraseña, String nombre, String celular, String correo,
			List<Venta> pendientesAceptar, List<Pieza> piezasPorAgregar, List<Comprador> pendientesVerificar, List<Comprador> superaronLimite) {
		super(nombreUsuario, contraseña, nombre, celular, correo);
		this.piezasPorAgregar = piezasPorAgregar;
		this.pendientesVerificar = pendientesVerificar;
		this.pendientesAceptar = pendientesAceptar;
		this.superaronLimite = superaronLimite;
	}
	


	public List<Pieza> getPiezasPorAgregar() {
        return piezasPorAgregar;
    }
	@Override
	public String getTipoInterno() {
		return "administrador";
	}
	public List<Venta> getPendientesAceptar(){
		return pendientesAceptar;
	}
	public List<Comprador> getPendientesVerificar(){
		return pendientesVerificar;
	}
	public List<Comprador> getSuperaronLimite(){
		return superaronLimite;
	}
}

