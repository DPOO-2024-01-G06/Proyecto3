package galeria.controller_galeria;

import java.util.List;

import galeria.Galeria;
import galeria.structurer_usuarios.Externo;
import galeria.structurer_usuarios.Interno;
import galeria.structurer_usuarios.Usuario;
import galeria.structurer_usuarios.UsuariosGaleria;

public class CoordinadorSesion {
	private String nombreUsuario;
	private String contrasena;
	private ControladorInternos controladorInternos;
	private Controlador_Externos controladorExternos;
	private String controladorActual;
	private Usuario usuario;
	private Galeria galeria;
	
	
	public CoordinadorSesion(Galeria galeria,String nombreUsuario, String contrasena) {
		this.galeria = galeria;
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.controladorActual = "";
	}
	public void iniciarSesion() { 
		UsuariosGaleria usuariosGaleria = galeria.getUsuariosGaleria();
		Usuario buscado = buscarUsuario(usuariosGaleria);
		if(buscado !=null && buscado.getContrasena().equals(contrasena)){
			usuario = buscado;
			if(usuario.getTipoUsuario() == "interno") {
				controladorInternos = new ControladorInternos( (Interno) usuario, galeria);
				controladorActual = "ControladorInternos";
			}
			else {
				controladorExternos = new Controlador_Externos((Externo) usuario, galeria);
				controladorActual = "ControladorExternos";
			}
		}
	}
	public String crearNuevoExterno(String nombreUsuario, String contraseña, String nombre, String celular, String correo) {
		String resultado = "";
		UsuariosGaleria usuariosGaleria = galeria.getUsuariosGaleria();
		Usuario buscado = buscarUsuario(usuariosGaleria);
		if(buscado !=null) {resultado = "Usuario ya existente";}
		else {
			usuariosGaleria.agregarExterno(nombreUsuario, contraseña, nombre, celular, correo);
			resultado = "Usuario creado exitosamente!";
		}
		return resultado; 
	}
	public Usuario buscarUsuario(UsuariosGaleria usuariosGaleria) {
		Usuario buscado = null;
		if(usuariosGaleria.getAdministrador().getNombreUsuario().equals(nombreUsuario)) {
			buscado = usuariosGaleria.getAdministrador();
			}
		else if(usuariosGaleria.getOperador().getNombreUsuario().equals(nombreUsuario)) {
			buscado = usuariosGaleria.getOperador();
			}
		else if(usuariosGaleria.getCajero().getNombreUsuario().equals(nombreUsuario)) {
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
	public ControladorInternos getControladorInternos() {
		return controladorInternos;
	}
	public Controlador_Externos getControladorExternos() {
		return controladorExternos;
	}
}