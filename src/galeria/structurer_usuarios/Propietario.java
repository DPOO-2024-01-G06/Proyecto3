package galeria.structurer_usuarios;

import java.util.List;

import galeria.structurer_inventario.Pieza;

public class Propietario {
	private List<Pieza> piezasPropiedad;
	private List<Pieza> piezasCedidas;
	private List<Pieza> piezasPasadas;
	private Externo externo;
	
    public Propietario(List<Pieza> piezasPropiedad, List<Pieza> piezasCedidas, List<Pieza> piezasPasadas, Externo externo) {
		this.piezasPropiedad = piezasPropiedad;
		this.piezasCedidas = piezasCedidas;
		this.piezasPasadas = piezasPasadas;
		this.externo = externo;
	}

	public List<Pieza> getPiezasPropiedad() {
        return piezasPropiedad;
    }

    public List<Pieza> getPiezasCedidas() {
        return piezasCedidas;
    }
    
    public List<Pieza> getPiezasPasadas(){
    	return piezasPasadas;
    }
    
    public Externo getExterno() {
    	return externo;
    }
    public void addPiezaPropiedad(Pieza pieza) {
    	piezasPropiedad.add(pieza);
    }
	

}
