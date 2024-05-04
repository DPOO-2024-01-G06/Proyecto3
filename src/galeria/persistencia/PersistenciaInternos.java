package galeria.persistencia;

import java.util.ArrayList;
import java.util.List;

import galeria.structurer_usuarios.*;
import galeria.structurer_inventario.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaInternos {
    private String archivoAdministrador;
    private String archivoCajero;
    private String archivoOperador;

    public PersistenciaInternos(String archivoAdministrador, String archivoCajero, String archivoOperador) {
        this.archivoAdministrador = archivoAdministrador;
        this.archivoCajero = archivoCajero;
        this.archivoOperador = archivoOperador;
    }

    public void guardarAdministrador(Administrador administrador) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoAdministrador))) {
            writer.write(administrador.getNombreUsuario() + ";" +
                         administrador.getContrasena() + ";" +
                         administrador.getNombre() + ";" +
                         administrador.getCorreo() + ";" +
                         administrador.getCelular() + "\n");
            for (Venta venta : administrador.getPendientesAceptar()) {
                writer.write(venta.getPrecio() + "," + 
                        venta.isAceptada() + "," + 
                        venta.isFacturada() + "," + 
                        venta.getPieza().getTitulo() + "," + 
                        venta.getPieza().getAutor() + "\n");
            }
            
            for (Pieza pieza : administrador.getPiezasPorAgregar()) {
                writer.write(pieza.getTitulo() + "," + 
                        pieza.getAutor() + "," + 
                        pieza.isExhibicion() + "," + 
                        pieza.getTiempoDisponible() + "\n");
            }
            
            for (Externo externo : administrador.getPendientesVerificar()) {
                writer.write(externo.getNombreUsuario() +  "," +
                		externo.getContrasena() + "," +
                		externo.getNombre() + "," +
                		externo.getCelular() + "," +
                		externo.getCorreo() + "," );
            }
        }
            catch (IOException e) {
        	System.out.println("No fue posible guardar la informacion del Administrador");
        }
    }

    public void guardarCajero(Cajero cajero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCajero))) {
            writer.write(cajero.getNombreUsuario() + ";" +
                         cajero.getContrasena() + ";" +
                         cajero.getNombre() + ";" +
                         cajero.getCorreo() + ";" +
                         cajero.getCelular() + "\n");
            
            for (Venta venta : cajero.getVentasPendientes()) {
                writer.write(venta.getPrecio() + "," + 
                        venta.isAceptada() + "," + 
                        venta.isFacturada() + "," + 
                        venta.getPieza().getTitulo() + "\n");
            }
            
        } catch (IOException e) {
            System.out.println("No fue posible guardar la informacion del Cajero");
        }
    }

    public void guardarOperador(Operador operador) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoOperador))) {
            writer.write(operador.getNombreUsuario() + ";" +
                         operador.getContrasena() + ";" +
                         operador.getNombre() + ";" +
                         operador.getCorreo() + ";" +
                         operador.getCelular() + "\n");
            
            for (Oferta oferta : operador.getOfertasPendientes()) {
            	Subasta subasta = oferta.getSubasta();
                writer.write(subasta.getValorMinimo() + "," + 
                        subasta.getValorInicial() + "," + 
                        subasta.getOfertaMaxima() + "," + 
                        subasta.getLimiteTiempo() + "," + 
                        subasta.getOfertas() + "\n");
            }  
        } catch (IOException e) {
        	System.out.println("No fue posible guardar la informacion del Operador");
        }
    }

    public Administrador cargarAdministrador() {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoAdministrador))) {
            String linea = reader.readLine();
            String[] campos = linea.split(";");
            String nombreUsuario = campos[0];
            String contrasena = campos[1];
            String nombre = campos[2];
            String correo = campos[3];
            String celular = campos[4];

            //Administrador administrador = new Administrador(nombreUsuario, contrasena, nombre, correo, celular, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            String ventaLine;
            while ((ventaLine = reader.readLine())!= null) {
                String[] ventaCampos = ventaLine.split(",");
                double precio = Double.parseDouble(ventaCampos[0]);
                boolean aceptada = Boolean.parseBoolean(ventaCampos[1]);
                boolean facturada = Boolean.parseBoolean(ventaCampos[2]);
                String tituloPieza = ventaCampos[3];
                String autorPieza = ventaCampos[4];
                Pieza pieza = new Pieza(tituloPieza, 0, "", false, "", autorPieza, null);
                Venta venta = new Venta(precio, aceptada, facturada, pieza);
                //venta.setPieza(pieza);
                //administrador.getComprasPorAceptar().add(venta);
            }

            String piezaLine;
            while ((piezaLine = reader.readLine())!= null) {
                String[] piezaCampos = piezaLine.split(",");
                String titulo = piezaCampos[0];
                String autor = piezaCampos[1];
                boolean exhibicion = Boolean.parseBoolean(piezaCampos[2]);
                String tiempoDisponible = piezaCampos[3];
                //Pieza pieza = new Pieza(titulo, 0, "", false, tiempoDisponible, autor);
                //pieza.setExhibicion(exhibicion);
                //administrador.getPiezasPorAgregar().add(pieza);
            }

            String externoLine;
            while ((externoLine = reader.readLine())!= null) {
                String[] externoCampos = externoLine.split(",");
                String nombreUsuarioExterno = externoCampos[0];
                String contrasenaExterno = externoCampos[1];
                String nombreExterno = externoCampos[2];
                String correoExterno = externoCampos[3];
                String celularExterno = externoCampos[4];
                //Externo externo = new Externo(nombreUsuarioExterno, contrasenaExterno, nombreExterno, correoExterno, celularExterno);
                //administrador.getPendientesVerificar().add(externo);
            }

            //return administrador;
            return null;
        } catch (IOException e) {
            System.out.println("No fue posible cargar la informacion del Administrador");
            return null;
        }
    }

    
    public Cajero cargarCajero() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("cajero.txt"));
            String[] cajeroCampos = reader.readLine().split(";");
            String nombreUsuario = cajeroCampos[0];
            String contrasena = cajeroCampos[1];
            String nombre = cajeroCampos[2];
            String correo = cajeroCampos[3];
            String celular = cajeroCampos[4];

            Cajero cajero = new Cajero(nombreUsuario, contrasena, nombre, correo, celular);

            String ventaLine;
            while ((ventaLine = reader.readLine()) != null) {
                String[] ventaCampos = ventaLine.split(",");
                double precio = Double.parseDouble(ventaCampos[0]);
                boolean aceptada = Boolean.parseBoolean(ventaCampos[1]);
                boolean facturada = Boolean.parseBoolean(ventaCampos[2]);
                String[] piezaCampos = ventaCampos[3].split("\\|");
                String titulo = piezaCampos[0];
                int anio = Integer.parseInt(piezaCampos[1]);
                String lugarCreacion = piezaCampos[2];
                boolean electricidad = Boolean.parseBoolean(piezaCampos[3]);
                String tiempoDisponible = piezaCampos[4];
                String autor = piezaCampos[5];
                //Pieza pieza = new Pieza(titulo, anio, lugarCreacion, electricidad, tiempoDisponible, autor);
                //Venta venta = new Venta(precio, aceptada, facturada);
                //cajero.getVentasPendientes().add(venta);
            }

            return cajero;
        } catch (IOException e) {
            System.out.println("No fue posible cargar la informacion del Cajero");
            return null;
        }
    }
    
    
    public Operador cargarOperador() {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoOperador))) {
            String linea = reader.readLine();
            String[] campos = linea.split(";");
            String nombreUsuario = campos[0];
            String contrasena = campos[1];
            String nombre = campos[2];
            String correo = campos[3];
            String celular = campos[4];

            //Operador operador = new Operador(nombreUsuario, contrasena, nombre, correo, celular);

            String subastaLine;
            while ((subastaLine = reader.readLine())!= null) {
                String[] subastaCampos = subastaLine.split(",");
                double valorMinimo = Double.parseDouble(subastaCampos[0]);
                double valorInicial = Double.parseDouble(subastaCampos[1]);
                double ofertaMaxima = Double.parseDouble(subastaCampos[2]);
                long limiteTiempo = Long.parseLong(subastaCampos[3]);
                
                List<Oferta> ofertas = new ArrayList<>();
                String[] ofertasCampos = subastaCampos[4].split(",");
                for (String ofertaCampo : ofertasCampos) {
                    String[] ofertaDatos = ofertaCampo.split(",");
                    double valor = Double.parseDouble(ofertaDatos[1]);
                    String metodoPago = ofertaDatos[0];
                   // Oferta oferta = new Oferta(valor, metodoPago);
                   // ofertas.add(oferta);
                }
                //Subasta subasta = new Subasta(valorMinimo, valorInicial, limiteTiempo, oferta);
                //operador.getOfertasPendientes().add(oferta);
            }

            //return operador;
            return null;
        } catch (IOException e) {
            System.out.println("No fue posible cargar la informacion del Operador");
            return null;
        }
    }
}