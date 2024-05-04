package galeria.controller_galeria;

import galeria.Galeria;
import galeria.structurer_usuarios.Externo;

public class Controlador_Externos {
	private Controlador_Propietario controladorPropietario;
	private Controlador_Comprador controladorComprador;
	private String controladorActual;
	private Galeria galeria;
	private Externo externo;
	private boolean decision;

	Controlador_Externos(Externo externo, Galeria galeria){
		this.galeria = galeria;
		this.externo = externo;
		}
	public void setDecision(boolean decision) {
		this.decision = decision;
		if(decision) {
			controladorComprador = new Controlador_Comprador(galeria, externo);
			controladorActual = "Controlador_Comprador";
		}
		else {
			controladorPropietario = new Controlador_Propietario(galeria, externo);
			controladorActual = "Controlador_Propietario";
		}
	
	}
	public Controlador_Propietario getControladorPropietario(){
		return controladorPropietario;
	}
	public Controlador_Comprador getControladorComprador() {
		return controladorComprador;
	}
}
