package galeria.structurer_inventario;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Inventario_Galeria {
    private Map<Integer, Subasta> subastasPendientes;
    private Map<Integer, Subasta> subastasPasadas;
    private Map<Integer, Venta> ventasPendientes;
    private Map<Integer, Venta> ventasAceptadas;
    private Map<Integer, Pieza> inventario; 
     
    public Inventario_Galeria(Map<Integer, Subasta> subastasPendientes, Map<Integer, Subasta> subastasPasadas,
			Map<Integer, Venta> ventasPendientes, Map<Integer, Venta> ventasAceptadas, Map<Integer, Pieza> inventario) {
		this.subastasPendientes = subastasPendientes;
		this.subastasPasadas = subastasPasadas;
		this.ventasPendientes = ventasPendientes;
		this.ventasAceptadas = ventasAceptadas;
		this.inventario = inventario;
	}

	//ventas
    //se debe crear la venta antes de ingresar
    // ej el admin agrega la pieza y luego le pregunta los datos de venta
    //al crear una obra por defecto debe entrar a esta lista
    public void agregarPieza(Venta venta) {
        int hashCode = Objects.hash(venta.getPieza().getTitulo(), venta.getPieza().getAutor());
        ventasPendientes.put(hashCode, venta);
        inventario.put(hashCode, venta.getPieza());
    }

    //el primer intento de un cliente de comprar la pieza
    public void intentoVender(Venta venta) {
        int hashCode = Objects.hash(venta.getPieza().getTitulo(), venta.getPieza().getAutor());
        Venta ventaPendiente = ventasPendientes.get(hashCode);
        Pieza piezaVenta = ventaPendiente.getPieza();
        piezaVenta.setBloqueado(true);
        Pieza piezainvent = inventario.get(hashCode);
        piezainvent.setBloqueado(true);
    }

    //el cliente cumple los requisitos para venderla
    public void venderPieza(Venta venta, boolean aceptada) {
        int hashCode = Objects.hash(venta.getPieza().getTitulo(), venta.getPieza().getAutor());
        if (aceptada) {
            ventasPendientes.get(hashCode).setAceptada(true);;
        }
        else {
            Venta ventaPendiente = ventasPendientes.get(hashCode);
            Pieza piezaVenta = ventaPendiente.getPieza();
            piezaVenta.setBloqueado(true);
            Pieza piezainvent = inventario.get(hashCode);
            piezainvent.setBloqueado(true);
        }
    }

    //la facturacion es exitosa
    public void facturada(Venta venta, boolean exito) {
    	int hashCode = Objects.hash(venta.getPieza().getTitulo(), venta.getPieza().getAutor());
    	if (exito) {
        venta.setFacturada(true);
        ventasAceptadas.put(hashCode, venta);
        ventasPendientes.remove(hashCode);
    	}
    	else {
    		Venta ventaPendiente = ventasPendientes.get(hashCode);
            Pieza piezaVenta = ventaPendiente.getPieza();
            piezaVenta.setBloqueado(true);
            Pieza piezainvent = inventario.get(hashCode);
            piezainvent.setBloqueado(true);
    	}
    }

    //subastas
    //hay que crear la subasta antes
    public void agregarSubasta(Subasta subasta, Venta venta) {
        int hashCode = Objects.hash(subasta.getPieza().getTitulo(), subasta.getPieza().getAutor());
        ventasPendientes.remove(hashCode);
        subastasPendientes.put(hashCode, subasta);
    }

    public void moverSubastaAPasadas(Subasta subasta) {
        int hashCode = Objects.hash(subasta.getPieza().getTitulo(), subasta.getPieza().getAutor());
        if (Subasta.verificarTiempo(subasta)) {
            subastasPendientes.remove(hashCode);
            subastasPasadas.put(hashCode, subasta);
            Pieza pieza = inventario.get(hashCode);
            pieza.setBloqueado(true);
        } 
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
    
}
