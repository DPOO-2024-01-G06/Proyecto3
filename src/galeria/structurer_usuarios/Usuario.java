package galeria.structurer_usuarios;

public abstract class Usuario {
	protected String nombreUsuario;
    protected String contrasena;
    protected String nombre;
    protected String celular;
    protected String correo;
    
    
    public Usuario (String nombreUsuario, String contrasena, String nombre, String celular, String correo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.celular = celular;
        this.correo = correo;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getCelular() {
        return celular;
    }

    public String getCorreo() {
        return correo;
    }
    public String getNombreUsuario() {
    	return nombreUsuario;
    }
    public String getContrasena() {
    	return contrasena;
    }
    public abstract String getTipoUsuario();

    
}
