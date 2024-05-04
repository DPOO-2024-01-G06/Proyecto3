package galeria.structurer_usuarios;

import java.util.ArrayList;
import java.util.List;

import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Venta;

public class Administrador extends Interno {
    private List<Pieza> piezasPorAgregar;
    private List<Externo> pendientesVerificar;	
    private List<Venta> pendientesAceptar;
    private List<Externo> superaronLimite;
	
    public Administrador(String nombreUsuario, String contraseña, String nombre, String celular, String correo,
			List<Venta> pendientesAceptar, List<Pieza> piezasPorAgregar, List<Externo> pendientesVerificar, List<Externo> superaronLimite) {
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
	public List<Externo> getPendientesVerificar(){
		return pendientesVerificar;
	}
	public List<Externo> getSuperaronLimite(){
		return superaronLimite;
	}
}

