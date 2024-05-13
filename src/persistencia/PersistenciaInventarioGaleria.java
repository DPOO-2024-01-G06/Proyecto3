package persistencia;

import galeria.structurer_inventario.Artista;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;

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

public class PersistenciaInventarioGaleria {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void guardarInventarioGaleria(Map<Integer, Pieza> inventario) throws PersistenciaException {
        try (FileWriter writer = new FileWriter("InventarioGaleria.json")) {
            String json = gson.toJson(inventario);
            writer.write(json);
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar el inventario", e);
        }
    }

    public static Map<Integer, Pieza> cargarInventarioGaleria() throws PersistenciaException {
        try (FileReader reader = new FileReader("InventarioGaleria.json")) {
            Type type = new TypeToken<Map<Integer, Pieza>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (JsonSyntaxException | IOException e) {
            throw new PersistenciaException("Error al cargar el inventario", e);
        }
    }

    public static void guardarSubastasPendientes(Map<Integer, Subasta> inventory) throws PersistenciaException {
        try (FileWriter writer = new FileWriter("SubastasPendientes.json")) {
            String json = gson.toJson(inventory);
            writer.write(json);
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar las subastas", e);
        }
    }

    public static Map<Integer, Subasta> cargarSubastasPendientes() throws PersistenciaException {
        try (FileReader reader = new FileReader("SubastasPendientes.json")) {
            Type type = new TypeToken<Map<Integer, Subasta>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (JsonSyntaxException | IOException e) {
            throw new PersistenciaException("Error al cargar las subastas", e);
        }
    }

    public static void guardarSubastasPasadas(Map<Integer, Subasta> inventory) throws PersistenciaException {
        try (FileWriter writer = new FileWriter("Subastas pasadas.json")) {
            String json = gson.toJson(inventory);
            writer.write(json);
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar las subastas pasadas", e);
        }
    }

    public static Map<Integer, Subasta> cargarSubastasPasadas() throws PersistenciaException {
        try (FileReader reader = new FileReader("Subastas pasadas.json")) {
            Type type = new TypeToken<Map<Integer, Subasta>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (JsonSyntaxException | IOException e) {
            throw new PersistenciaException("Error al cargar las subastas pasadas", e);
        }
    }

    public static void guardarVentasPendientes(Map<Integer, Venta> inventory) throws PersistenciaException {
        try (FileWriter writer = new FileWriter("VentasPendientes.json")) {
            String json = gson.toJson(inventory);
            writer.write(json);
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar las ventas pendientes", e);
        }
    }

    public static Map<Integer, Venta> cargarVentasPendientes() throws PersistenciaException {
        try (FileReader reader = new FileReader("VentasPendientes.json")) {
            Type type = new TypeToken<Map<Integer, Venta>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (JsonSyntaxException | IOException e) {
            throw new PersistenciaException("Error al cargar las ventas pendientes", e);
        }
    }

    public static void guardarVentasAceptadas(Map<Integer, Venta> inventory) throws PersistenciaException {
        try (FileWriter writer = new FileWriter("VentasAceptadas.json")) {
            String json = gson.toJson(inventory);
            writer.write(json);
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar las ventas aceptadas", e);
        }
    }

    public static Map<Integer, Venta> cargarVentasAceptadas() throws PersistenciaException {
        try (FileReader reader = new FileReader("VentasAceptadas.json")) {
            Type type = new TypeToken<Map<Integer, Venta>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (JsonSyntaxException | IOException e) {
            throw new PersistenciaException("Error al cargar las ventas aceptadas", e);
        }
    }

    public static void guardarArtistas(List<Artista> artistas) throws PersistenciaException {
        try (FileWriter writer = new FileWriter("Artistas.json")) {
            String json = gson.toJson(artistas);
            writer.write(json);
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar los artistas", e);
        }
    }

    public static List<Artista> cargarArtistas() throws PersistenciaException {
        try (FileReader reader = new FileReader("Artistas.json")) {
            Type type = new TypeToken<List<Artista>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (JsonSyntaxException | IOException e) {
            throw new PersistenciaException("Error al cargar los artistas", e);
        }
    }

}
