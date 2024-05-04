package galeria.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import galeria.structurer_usuarios.Externo;


public class PersistenciaExternos {
    private String archivo;

    public PersistenciaExternos(String archivo) {
        this.archivo = archivo;
    }

    public void guardarExternos(Externo externo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(externo.getNombreUsuario() + ";" +
                         externo.getContrasena() + ";" +
                         externo.getNombre() + ";" +
                         externo.getCorreo() + ";" +
                         externo.getCelular() + "\n");

        } catch (IOException e) {
            System.out.println("No fue posible guardar la informacion del Externo");
        }
    }
    
    public Externo cargarExternos() {
        Externo externo = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea = reader.readLine();
            if (linea != null) {
                String[] campos = linea.split(";");
 //               externo = new Externo(campos[0], campos[1], campos[2], campos[3], campos[4]);
            }
        } catch (IOException e) {
            System.out.println("No fue posible cargar la informacion del Externo");
        }
        return externo;
    }
}