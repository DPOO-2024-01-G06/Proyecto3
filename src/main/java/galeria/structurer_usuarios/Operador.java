package galeria.structurer_usuarios;

import java.util.List;


import galeria.structurer_inventario.Oferta;

public class Operador extends Interno {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4368913631583280004L;
	public List<Oferta> ofertasPendientes;
	public Operador() {
		
	}
	public Operador(String nombreUsuario, String contraseña, String nombre, String celular, String correo, List<Oferta> ofertasPendientes) {
        super(nombreUsuario, contraseña, nombre, celular, correo);
		this.ofertasPendientes = ofertasPendientes;
	}

	public void agregarOfertaPendiente (Oferta oferta){
		this.ofertasPendientes.add(oferta);
	}
	public List<Oferta> getOfertasPendientes(){
		return ofertasPendientes;
	}
	
	public String getTipoInterno() {
		return "operador";
	}
	
}
