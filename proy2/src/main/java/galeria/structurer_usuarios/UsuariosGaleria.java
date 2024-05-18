package galeria.structurer_usuarios;

import java.io.Serializable;
import java.util.List;

public class UsuariosGaleria implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7339765893643918599L;
	private Administrador administrador;
    private Cajero cajero;
    private Operador operador;
    private List<Externo> externos;

    public UsuariosGaleria(Administrador administrador, Cajero cajero, Operador operador, List<Externo> externos){
        this.administrador = administrador;
        this.cajero =  cajero;
        this.operador = operador;
        this.externos = externos;
    }

    public void agregarExterno(String nombreUsuario, String contraseña, String nombre, String celular, String correo) {
    	Externo externo = new Externo(nombreUsuario, contraseña, nombre, celular, correo, null, null);
        this.externos.add(externo);
    }

    public List<Externo> getExternos() {
        return externos;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public Cajero getCajero() {
        return cajero;
    }

    public Operador getOperador() {
        return operador;
    }
}
