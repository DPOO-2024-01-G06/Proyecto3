package galeria.structurer_inventario;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;



public class InventarioGaleria {
    private Map<Integer, Subasta> subastasPendientes;
    private Map<Integer, Subasta> subastasPasadas;
    private Map<Integer, Venta> ventasPendientes;
    private Map<Integer, Venta> ventasAceptadas;
    private Map<Integer, Pieza> inventario; 
    private List<Artista> artistas;
     
    public InventarioGaleria(Map<Integer, Subasta> subastasPendientes, Map<Integer, Subasta> subastasPasadas,
			Map<Integer, Venta> ventasPendientes, Map<Integer, Venta> ventasAceptadas, Map<Integer, Pieza> inventario, List<Artista> artistas){
		this.subastasPendientes = subastasPendientes;
		this.subastasPasadas = subastasPasadas;
		this.ventasPendientes = ventasPendientes;
		this.ventasAceptadas = ventasAceptadas;
		this.inventario = inventario;
		this.artistas = artistas;
	}
    


    public void agregarPieza(Venta venta) {
        int hashCode = Objects.hash(venta.getPieza().getTitulo(), venta.getPieza().getAutor());
        ventasPendientes.put(hashCode, venta);
        inventario.put(hashCode, venta.getPieza());
    }

    public void setVentaFacturada(Venta venta) {
    	int hashCode = Objects.hash(venta.getPieza().getTitulo(), venta.getPieza().getAutor());
        ventasAceptadas.put(hashCode, venta);
        ventasPendientes.remove(hashCode);
        inventario.remove(hashCode);
    }

    public void agregarSubasta(Subasta subasta, Venta venta) {
        int hashCode = Objects.hash(subasta.getPieza().getTitulo(), subasta.getPieza().getAutor());
        ventasPendientes.remove(hashCode);
        subastasPendientes.put(hashCode, subasta);
    }

    public void moverSubastaAPasadas(Subasta subasta) {
        int hashCode = Objects.hash(subasta.getPieza().getTitulo(), subasta.getPieza().getAutor());
        subastasPendientes.remove(hashCode);
        subastasPasadas.put(hashCode, subasta);
        inventario.remove(hashCode);
    }
    
    public void devolverPieza(Pieza pieza) {
    	int hashCode = Objects.hash(pieza.getTitulo(), pieza.getAutor());
    	ventasPendientes.remove(hashCode);
    	inventario.remove(hashCode);
    }
    
    public Map<Integer, Pieza> getInventario(){
    	return inventario;
    }
    public Map<Integer, Venta> getVentasPendientes(){
    	return ventasPendientes;
    }
    public Map<Integer, Venta> getVentasAceptadas(){
    	return ventasAceptadas;
    }
    public Map<Integer, Subasta> getSubastasPendientes(){
    	return subastasPendientes;
    }
    public Map<Integer, Subasta> getSubastasPasadas(){
    	return subastasPasadas;
    }
    public Venta getVentaPendiente(int i){
    	List<Venta> ventas = new ArrayList<Venta>(ventasPendientes.values());
    	return ventas.get(i);
    }
    public List<Artista> getArtistas(){
    	return artistas;
    }
    public void addArtista(Artista artista) {
    	artistas.add(artista);
    }   
}
