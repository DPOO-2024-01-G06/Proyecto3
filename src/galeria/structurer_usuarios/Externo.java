package galeria.structurer_usuarios;

import java.util.ArrayList;
import java.util.List;

import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;


public class Externo extends Usuario {
    private Comprador comprador;
    private Propietario propietario;
    
    public Externo (String nombreUsuario, String contraseña, String nombre, String celular, String correo, Comprador comprador, Propietario propietario) {
		super(nombreUsuario, contraseña, nombre, celular, correo);
		this.propietario = propietario;
		this.comprador = comprador;
    }
    
    public Comprador crearComprador(String nombreUsuario, String contraseña, String nombre, String celular, String correo) {
        Comprador comprador = new Comprador(0, false, 0, new ArrayList<Venta>(), new ArrayList<Subasta>());
        return comprador;
    }
    
    public Propietario crearPropietario(String nombreUsuario, String contraseña, String nombre, String celular, String correo) {
        Propietario propietario = new Propietario(new ArrayList<Pieza>(),new ArrayList<Pieza>());
        return propietario;
    }
    
    public Comprador getComprador() {
        return comprador;
    }

    public Propietario getPropietario() {
        return propietario;
    }

	@Override
	public String getTipoUsuario() {
		return "externo";
	}
}
