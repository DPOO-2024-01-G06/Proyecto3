package galeria.structurer_usuarios;

public abstract class Interno extends Usuario {
	public Interno (String nombreUsuario, String contrasena, String nombre, String celular, String correo) {
		super(nombreUsuario, contrasena, nombre, celular, correo);
	}
	@Override
	public String getTipoUsuario() {
		return "interno";
	}
	
	public void actualizarDatos(String contrasena, String nombre, String celular, String correo) {
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.celular = celular;
		this.correo = correo; 
	}
	
	public abstract String getTipoInterno();
}
