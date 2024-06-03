package galeria.structurer_inventario;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;


import galeria.structurer_usuarios.Comprador;

public class Subasta implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1745584192861234428L;
	private float valorMinimo;
    private float valorInicial;
    private Oferta ofertaMaxima;
    private String limiteTiempo;
    private ArrayList<Oferta> ofertas;
    private Pieza pieza;
    
    public Subasta() {
    	
    }
    public Subasta(float valorMinimo, float valorInicial, ArrayList<Oferta> ofertas, String limiteTiempo, Pieza pieza, Comprador ganador) {
        this.valorMinimo = valorMinimo;
        this.valorInicial = valorInicial;
        this.ofertaMaxima = null;
        this.limiteTiempo = limiteTiempo;
        this.pieza = pieza;
        this.ofertas=ofertas;
    }


    public void agregarOferta(Oferta oferta) {
        if (this.ofertaMaxima == null || oferta.getValor() > this.ofertaMaxima.getValor()) {
            this.ofertaMaxima = oferta;}
        this.ofertas.add(oferta);
    }
    

    public static boolean verificarTiempo(Subasta subasta) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime tiempoLimite = LocalDateTime.parse(subasta.getLimiteTiempo());   
        return ahora.isAfter(tiempoLimite);
    }
    
    
    public Pieza getPieza() {
        return pieza;
    }
    
    public float getValorMinimo() {
        return valorMinimo;
    }

    public float getValorInicial() {
        return valorInicial;
    }

    public Oferta getOfertaMaxima() {
        return ofertaMaxima;
    }

    public void setOfertaMaxima(Oferta ofertaMaxima) {
        this.ofertaMaxima = ofertaMaxima;
    }

    public String getLimiteTiempo() {
        return limiteTiempo;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
        
    }
    

}
