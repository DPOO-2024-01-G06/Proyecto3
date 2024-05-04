package galeria.structurer_inventario;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Subasta {
    private double valorMinimo;
    private double valorInicial;
    private Oferta ofertaMaxima;
    private LocalDateTime limiteTiempo;
    private ArrayList<Oferta> ofertas;
    private Pieza pieza;

    public Subasta(double valorMinimo, double valorInicial, LocalDateTime limiteTiempo, Pieza pieza ) {
        this.valorMinimo = valorMinimo;
        this.valorInicial = valorInicial;
        this.ofertaMaxima = null;
        this.limiteTiempo = limiteTiempo;
        new ArrayList<Oferta>();
        this.pieza = pieza;
    }

    public void agregarOferta(Oferta oferta) {
        if (this.ofertaMaxima == null || oferta.getValor() > this.ofertaMaxima.getValor()) {
            this.ofertaMaxima = oferta;}
        this.ofertas.add(oferta);
    }
    

    public static boolean verificarTiempo(Subasta subasta) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime tiempoLimite = subasta.getLimiteTiempo();
        return ahora.isAfter(tiempoLimite);
    }
    
    
    public Pieza pieza() {
        return pieza;
    }
    
    public double getValorMinimo() {
        return valorMinimo;
    }

    public double getValorInicial() {
        return valorInicial;
    }

    public Oferta getOfertaMaxima() {
        return ofertaMaxima;
    }

    public void setOfertaMaxima(Oferta ofertaMaxima) {
        this.ofertaMaxima = ofertaMaxima;
    }

    public LocalDateTime getLimiteTiempo() {
        return limiteTiempo;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
        
    }

	public Pieza getPieza() {
		return pieza;
	}

}
