package persistencia;

import java.util.ArrayList;
import java.util.HashMap;

import galeria.Galeria;
import galeria.structurer_inventario.Artista;
import galeria.structurer_inventario.InventarioGaleria;
import galeria.structurer_inventario.Oferta;
import galeria.structurer_inventario.Pieza;
import galeria.structurer_inventario.Subasta;
import galeria.structurer_inventario.Venta;
import galeria.structurer_usuarios.Administrador;
import galeria.structurer_usuarios.Cajero;
import galeria.structurer_usuarios.Comprador;
import galeria.structurer_usuarios.Externo;
import galeria.structurer_usuarios.Operador;
import galeria.structurer_usuarios.UsuariosGaleria;

public class PersistenciaNuevo {

	public Galeria nuevaGaleria() {
		return new Galeria(nuevosUsuarios(), nuevoInventario());
	}
	
	public UsuariosGaleria nuevosUsuarios() {
		Administrador administrador = new Administrador("ADMIN", "0000", "nombre", "0000000000", "correo@gmail.com", 
									  new ArrayList<Venta>(), new ArrayList<Pieza>(), new ArrayList<Comprador>(), new ArrayList<Comprador>());
		Cajero cajero = new Cajero("CAJERO", "0000", "nombre", "correo@gmail.com", "0000000000", new ArrayList<Venta>());
		Operador operador = new Operador("OPERADOR", "0000", "nombre", "0000000000", "correo@gmail.com", new ArrayList<Oferta>());
		return new UsuariosGaleria(administrador, cajero, operador, new ArrayList<Externo>());
		
	}
	public InventarioGaleria nuevoInventario() {		
		return new InventarioGaleria(new HashMap<Integer, Subasta>(), new HashMap<Integer, Subasta>(), new HashMap<Integer, Venta>(), 
									new HashMap<Integer, Venta>(), new HashMap<Integer, Pieza>(), new ArrayList<Artista>());
	}
}
