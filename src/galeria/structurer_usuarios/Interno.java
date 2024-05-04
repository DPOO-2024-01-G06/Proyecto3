package galeria.structurer_usuarios;

public abstract class Interno extends Usuario {
	public Interno (String nombreUsuario, String contraseña, String nombre, String celular, String correo) {
		super(nombreUsuario, contraseña, nombre, celular, correo);
	}
	@Override
	public String getTipoUsuario() {
		return "interno";
	}
	
	public abstract String getTipoInterno();
}
