package galeria.structurer_inventario;
import java.time.LocalDateTime;
import java.util.ArrayList;

import galeria.structurer_usuarios.Comprador;

public class Subasta {
    private double valorMinimo;
    private double valorInicial;
    private Oferta ofertaMaxima;
    private LocalDateTime limiteTiempo;
    private ArrayList<Oferta> ofertas;
    private Pieza pieza;

    public Subasta(double valorMinimo, double valorInicial,ArrayList<Oferta> ofertas, LocalDateTime limiteTiempo, Pieza pieza, Comprador ganador) {
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
