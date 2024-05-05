package galeria.structurer_inventario;

import java.util.List;
import java.util.Map;

import galeria.structurer_usuarios.Externo;

public class Fotografia extends Pieza{
    private double ancho;
    private double alto;
    private String tipoPapel;

    public Fotografia(String titulo, String fecha, String lugarCreacion, boolean electricidad,
            String tiempoDisponible, String autor, double ancho, double alto, String tipoPapel, Externo externo, Artista artista, Map<String, List<String>> historial) {
        super(titulo, fecha, lugarCreacion, electricidad, tiempoDisponible, externo, artista, historial);
        this.ancho = ancho;
        this.alto = alto;
        this.tipoPapel = tipoPapel;
    }
}