package galeria.controller_galeria;

import galeria.Galeria;
import galeria.structurer_usuarios.Externo;

public class Controlador_Externos {
	private ControladorPropietario controladorPropietario;
	private ControladorComprador controladorComprador;
	private String controladorActual;
	private Galeria galeria;
	private Externo externo;

	Controlador_Externos(Externo externo, Galeria galeria){
		this.galeria = galeria;
		this.externo = externo;
		}
	public void Decidir(boolean decision) {
		if(decision) {
			controladorComprador = new ControladorComprador(galeria, externo);
			controladorActual = "Controlador_Comprador";
		}
		else {
			controladorPropietario = new ControladorPropietario(galeria, externo);
			controladorActual = "Controlador_Propietario";
		}
	
	}
	public ControladorPropietario getControladorPropietario(){
		return controladorPropietario;
	}
	public ControladorComprador getControladorComprador() {
		return controladorComprador;
	}
	public String getControladorActual() {
		return controladorActual;
	}
}
