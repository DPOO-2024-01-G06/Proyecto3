package galeria.controller_galeria;

import java.util.List;

import galeria.Galeria;
import galeria.structurer_usuarios.Externo;
import galeria.structurer_usuarios.Interno;
import galeria.structurer_usuarios.Usuario;
import galeria.structurer_usuarios.Usuarios_Galeria;

public class Coordinador_Sesion {
	private String nombreUsuario;
	private String contrasena;
	private Controlador_Internos controladorInternos;
	private Controlador_Externos controladorExternos;
	private String controladorActual;
	private Usuario usuario;
	private Galeria galeria;
	
	
	public Coordinador_Sesion(Galeria galeria,String nombreUsuario, String contrasena) {
		this.galeria = galeria;
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
	}
	public String iniciarSesion() {
		String resultado = ""; 
		Usuarios_Galeria usuariosGaleria = galeria.getUsuariosGaleria();
		Usuario buscado = buscarUsuario(usuariosGaleria);
		if(buscado == null) {resultado = "Usuario no encontrado";}
		else if(buscado.getContrasena() != contrasena) {resultado = "Contraseña incorrecta";}
		else {
			usuario = buscado;
			if(usuario.getTipoUsuario() == "interno") {
				controladorInternos = new Controlador_Internos( (Interno) usuario, galeria);
				controladorActual = "Controlador_Internos";
				resultado = "Bienvenido, interno!";
			}
			else {
				controladorExternos = new Controlador_Externos((Externo) usuario, galeria);
				controladorActual = "Controlador_Externos";
				resultado = "Bienvenido, externo!";
			}
		}
		return resultado;
	}
	public String crearNuevoExterno(String nombreUsuario, String contraseña, String nombre, String celular, String correo) {
		String resultado = "";
		Usuarios_Galeria usuariosGaleria = galeria.getUsuariosGaleria();
		Usuario buscado = buscarUsuario(usuariosGaleria);
		if(buscado !=null) {resultado = "Usuario ya existente";}
		else {
			usuariosGaleria.agregarExterno(nombreUsuario, contraseña, nombre, celular, correo);
			resultado = "Usuario creado exitosamente!";
		}
		return resultado; 
	}
	public Usuario buscarUsuario(Usuarios_Galeria usuariosGaleria) {
		Usuario buscado = null;
		if(usuariosGaleria.getAdministrador().getNombreUsuario() == nombreUsuario) {
			buscado = usuariosGaleria.getAdministrador();
			}
		else if(usuariosGaleria.getOperador().getNombreUsuario() == nombreUsuario) {
			buscado = usuariosGaleria.getOperador();
			}
		else if(usuariosGaleria.getCajero().getNombreUsuario() == nombreUsuario) {
			buscado= usuariosGaleria.getCajero();
			}
		else {
		List<Externo> externos= usuariosGaleria.getExternos();
		for(int i = 0; i < externos.size() && buscado ==null; i++) {
			Externo externo = externos.get(i);
			if(externo.getNombreUsuario() == nombreUsuario) {buscado = externo;}
		}
		}
		return buscado;
	}
	public String getControladorActual() {
		return controladorActual;
	}
	public Controlador_Internos getControladorInternos() {
		return controladorInternos;
	}
	public Controlador_Externos getControladorExternos() {
		return controladorExternos;
	}
}