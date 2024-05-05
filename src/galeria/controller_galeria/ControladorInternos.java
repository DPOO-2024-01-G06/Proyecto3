package galeria.controller_galeria;

import galeria.Galeria;
import galeria.structurer_usuarios.Interno;
import galeria.structurer_usuarios.Operador;
import galeria.structurer_usuarios.Administrador;
import galeria.structurer_usuarios.Cajero;

public class ControladorInternos {
	private String controladorActual;
	private Galeria galeria;
	private Controlador_Cajero controladorCajero;
	private ControladorAdministrador controladorAdministrador;
	private Controlador_Operador controladorOperador;
	private Interno interno;	
	
	public ControladorInternos(Interno interno, Galeria galeria){
		this.interno = interno;
		this.galeria = galeria;
	}
	public void decidirControlador(){
		if(interno.getTipoInterno() == "administrador") {
			controladorAdministrador = new ControladorAdministrador(galeria, (Administrador) interno);
			controladorActual = "ControladorAdministrador";
		}
		else if(interno.getTipoInterno() == "operador") {
			controladorOperador = new Controlador_Operador(galeria, (Operador) interno);
			controladorActual = "ControladorOperador";
		}
		else {
			controladorCajero = new Controlador_Cajero(galeria, (Cajero) interno);
			controladorActual = "ControladorCajero";
		}
	}
	public String getControladorActual() {
		return controladorActual;
	}
	public Controlador_Cajero getControladorCajero() {
		return controladorCajero;
	}
	public ControladorAdministrador getControladorAdministrador() {
		return controladorAdministrador;
	}
	public Controlador_Operador getControladorOperador() {
		return controladorOperador;
	}
}
