package galeria.structurer_usuarios;

import java.util.ArrayList;
import java.util.List;

import galeria.structurer_inventario.Pieza;


public class Propietario {
	private List<Pieza> piezasPropiedad;
	private List<Pieza> piezasCedidas;
	
	

    public Propietario(List<Pieza> piezasPropiedad, List<Pieza> piezasCedidas) {
		this.piezasPropiedad = piezasPropiedad;
		this.piezasCedidas = piezasCedidas;
	}

	public List<Pieza> getPiezasPropiedad() {
        return piezasPropiedad;
    }

    public List<Pieza> getPiezasCedidas() {
        return piezasCedidas;
    }
	

}
