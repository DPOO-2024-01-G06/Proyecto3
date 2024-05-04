package galeria.controller_galeria;

import galeria.Galeria;
import galeria.structurer_usuarios.Interno;
import galeria.structurer_usuarios.Operador;
import galeria.structurer_usuarios.Administrador;
import galeria.structurer_usuarios.Cajero;

public class Controlador_Internos {
	private String controladorActual;
	private Galeria galeria;
	private Controlador_Cajero controladorCajero;
	private Controlador_Administrador controladorAdministrador;
	private Controlador_Operador controladorOperador;
	private Interno interno;	
	
	public Controlador_Internos(Interno interno, Galeria galeria){
		this.interno = interno;
		this.galeria = galeria;
	}
	public void decidirControlador(){
		if(interno.getTipoInterno() == "administrador") {
			controladorAdministrador = new Controlador_Administrador(galeria, (Administrador) interno);
			controladorActual = "Controlador_Administrador";
		}
		else if(interno.getTipoInterno() == "operador") {
			controladorOperador = new Controlador_Operador(galeria, (Operador) interno);
			controladorActual = "Controlador_Operador";
		}
		else {
			controladorCajero = new Controlador_Cajero(galeria, (Cajero) interno);
			controladorActual = "Controlador_Cajero";
		}
	}
	public String getControladorActual() {
		return controladorActual;
	}
	public Controlador_Cajero getControladorCajero() {
		return controladorCajero;
	}
	public Controlador_Administrador getControladorAdministrador() {
		return controladorAdministrador;
	}
	public Controlador_Operador getControladorOperador() {
		return controladorOperador;
	}
}
