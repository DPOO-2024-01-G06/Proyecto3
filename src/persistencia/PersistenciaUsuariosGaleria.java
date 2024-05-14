package persistencia;

import galeria.structurer_usuarios.Administrador;
import galeria.structurer_inventario.Artista;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;
import galeria.structurer_inventario.Oferta;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import errores.PersistenciaException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;

public class PersistenciaUsuariosGaleria {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void guardarUsuariosGaleria(Map<Integer, Pieza> usuarios) throws PersistenciaException {
        try (FileWriter writer = new FileWriter("UsuariosGaleria.json")) {
            String json = gson.toJson(usuarios);
            writer.write(json);
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar usuarios", e);
        }
    }
    
    public static Map<Integer, Pieza> cargarUsuariosGaleria() throws PersistenciaException {
        try (FileReader reader = new FileReader("UsuariosGaleria.json")) {
            Type type = new TypeToken<Map<Integer, Pieza>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (JsonSyntaxException | IOException e) {
            throw new PersistenciaException("Error al cargar usuarios", e);
        }
    }
    
    public static void guardarPendientesAceptar(Map<Integer, Venta> usuarios) throws PersistenciaException {
        try (FileWriter writer = new FileWriter("PendientesAceptar.json")) {
            String json = gson.toJson(usuarios);
            writer.write(json);
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar las ventas pendientes por aceptar", e);
        }
    }

    public static Map<Integer, Venta> cargarPendientesAceptar() throws PersistenciaException {
        try (FileReader reader = new FileReader("PendientesAceptar.json")) {
            Type type = new TypeToken<Map<Integer, Venta>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (JsonSyntaxException | IOException e) {
            throw new PersistenciaException("Error al cargar las ventas pendientes por aceptar", e);
        }
    }
    
    public static void guardarPiezasPorAgregar(Map<Integer, Pieza> usuarios) throws PersistenciaException {
        try (FileWriter writer = new FileWriter("PiezasPorAgregar.json")) {
            String json = gson.toJson(usuarios);
            writer.write(json);
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar las piezas por agregar", e);
        }
    }

    public static Map<Integer, Venta> cargarPiezasPorAgregar() throws PersistenciaException {
        try (FileReader reader = new FileReader("PiezasPorAgregar.json")) {
            Type type = new TypeToken<Map<Integer, Pieza>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (JsonSyntaxException | IOException e) {
            throw new PersistenciaException("Error al cargar las piezas por agregar", e);
        }
    }
    
    public static void guardarPendientesVerificar(Map<Integer, Externo> usuarios) throws PersistenciaException {
        try (FileWriter writer = new FileWriter("PendientesVerificar.json")) {
            String json = gson.toJson(usuarios);
            writer.write(json);
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar los usuarios pendientes por verificar", e);
        }
    }

    public static Map<Integer, Venta> cargarPendientesVerificar() throws PersistenciaException {
        try (FileReader reader = new FileReader("PendientesVerificar.json")) {
            Type type = new TypeToken<Map<Integer, Externo>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (JsonSyntaxException | IOException e) {
            throw new PersistenciaException("Error al cargar los usuarios pendientes por verificar", e);
        }
    }
    
    public static void guardarPiezasCompradas(Map<Integer, Venta> usuarios) throws PersistenciaException {
        try (FileWriter writer = new FileWriter("PiezasCompradas.json")) {
            String json = gson.toJson(usuarios);
            writer.write(json);
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar las piezas compradas", e);
        }
    }

    public static Map<Integer, Venta> cargarPiezasCompradas() throws PersistenciaException {
        try (FileReader reader = new FileReader("PiezasCompradas.json")) {
            Type type = new TypeToken<Map<Integer, Venta>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (JsonSyntaxException | IOException e) {
            throw new PersistenciaException("Error al cargar las piezas compradas", e);
        }
    }
    
    public static void guardarOfertasPendientes(Map<Integer, Oferta> usuarios) throws PersistenciaException {
        try (FileWriter writer = new FileWriter("OfertasPendientes.json")) {
            String json = gson.toJson(usuarios);
            writer.write(json);
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar las ofertas pendientes", e);
        }
    }

    public static Map<Integer, Venta> cargarPiezasCompradas() throws PersistenciaException {
        try (FileReader reader = new FileReader("OfertasPendientes.json")) {
            Type type = new TypeToken<Map<Integer, Oferta>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (JsonSyntaxException | IOException e) {
            throw new PersistenciaException("Error al cargar las ofertas pendientes", e);
        }
    }
}