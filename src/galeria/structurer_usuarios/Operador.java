package galeria.structurer_usuarios;

import java.util.List;

import galeria.structurer_inventario.Oferta;
import java.util.ArrayList;

public class Operador extends Interno {
	public List<Oferta> ofertasPendientes;
	
	public Operador(String nombreUsuario, String contraseña, String nombre, String celular, String correo, List<Oferta> ofertasPendientes) {
        super(nombreUsuario, contraseña, nombre, celular, correo);
		this.ofertasPendientes = new ArrayList<>();
	}

	public void agregarOfertaPendiente (Oferta oferta){
		this.ofertasPendientes.add(oferta);
	}
	public List<Oferta> getOfertasPendientes(){
		return ofertasPendientes;
	}
	

	@Override
	public String getTipoInterno() {
		return "operador";
	}
	
}
