package galeria.structurer_usuarios;

import java.util.ArrayList;


import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;

public class Externo extends Usuario{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1540625778280515643L;
	private Comprador comprador;
    private Propietario propietario;
    public Externo() {
    	
    }
    public Externo (String nombreUsuario, String contraseña, String nombre, String celular, String correo, Comprador comprador, Propietario propietario) {
		super(nombreUsuario, contraseña, nombre, celular, correo);
		if(propietario == null) {
			this.propietario = new Propietario(new ArrayList<Pieza>(), new ArrayList<Pieza>(),new ArrayList<Pieza>(),this);
		}
		else this.propietario = propietario;
		this.comprador = comprador;
    }
    public Comprador getComprador() {
        return comprador;
    }

    public Propietario getPropietario() {
        return propietario;
    }
    public void actualizarDatos(String contrasena, String nombre, String celular, String correo) {
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.celular = celular;
		this.correo = correo; 
    }
    public void crearComprador(float salario) {
    	comprador = new Comprador((float) 0.0,false, new ArrayList<Venta>(),salario, this, new ArrayList<Subasta>(),(float) 0.0, new ArrayList<Venta>());
    }
	@Override
	public String getTipoUsuario() {
		return "externo";
	}
	
	public void actualizarContraseña(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public void actualizarCorreo(String correo) {
		this.correo = correo;
	}
	
	public void actualizarCelular(String celular) {
		this.celular = celular;
	}
}
