package galeria.structurer_inventario;
import java.io.Serializable;
import java.util.List;



public class InventarioGaleria implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5575720429421210612L;
	private List<Subasta> subastasPendientes;
    private List<Subasta> subastasPasadas;
    private List<Venta> ventasPendientes;
    private List<Venta> ventasAceptadas;
    private List<Pieza> inventario; 
    private List<Artista> artistas;
     
    public InventarioGaleria( List<Subasta> subastasPendientes,  List<Subasta> subastasPasadas,
    		 List<Venta> ventasPendientes,  List<Venta> ventasAceptadas,  List<Pieza> inventario, List<Artista> artistas){
		this.subastasPendientes = subastasPendientes;
		this.subastasPasadas = subastasPasadas;
		this.ventasPendientes = ventasPendientes;
		this.ventasAceptadas = ventasAceptadas;
		this.inventario = inventario;
		this.artistas = artistas;
	}
    


    public void agregarPieza(Venta venta) {
    	inventario.add(venta.getPieza());
    	ventasPendientes.add(venta);
    }

    public void setVentaFacturada(Venta venta) {
    	ventasAceptadas.add(venta);
    	inventario.remove(venta.getPieza());
    }

    public void agregarSubasta(Subasta subasta, Venta venta) {
    	ventasPendientes.remove(venta);
    	subastasPendientes.add(subasta);
    }

    public void moverSubastaAPasadas(Subasta subasta) {
    	subastasPendientes.remove(subasta);
    	subastasPasadas.add(subasta);
    	inventario.remove(subasta.getPieza());
    }
    
    public void devolverPieza(Pieza pieza) {
    	int i= 0;
    	Boolean si = false;
    	while(!si) {
    		if(ventasPendientes.get(i).getPieza().equals(pieza)) {
    			si = true;
    			ventasPendientes.remove(i);
    		}
    		i++;
    	}
    	inventario.remove(pieza);
    }
    
    public List<Pieza> getInventario(){
    	return inventario;
    }
    public List<Venta> getVentasPendientes(){
    	return ventasPendientes;
    }
    public List<Venta> getVentasAceptadas(){
    	return ventasAceptadas;
    }
    public List<Subasta> getSubastasPendientes(){
    	return subastasPendientes;
    }
    public List<Subasta> getSubastasPasadas(){
    	return subastasPasadas;
    }
    public Venta getVentaPendiente(int i){
    	return ventasPendientes.get(i);
    }
    public List<Artista> getArtistas(){
    	return artistas;
    }
    public void addArtista(Artista artista) {
    	artistas.add(artista);
    }   
}
