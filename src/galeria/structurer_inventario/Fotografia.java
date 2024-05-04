package galeria.structurer_inventario;

import galeria.structurer_usuarios.Externo;

public class Fotografia extends Pieza{
    private double ancho;
    private double alto;
    private String tipoPapel;

    public Fotografia(String titulo, int anio, String lugarCreacion, boolean electricidad,
            String tiempoDisponible, String autor, double ancho, double alto, String tipoPapel, Externo externo) {
        super(titulo, anio, lugarCreacion, electricidad, tiempoDisponible, autor, externo);
        this.ancho = ancho;
        this.alto = alto;
        this.tipoPapel = tipoPapel;
    }
}