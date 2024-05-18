package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import galeria.Galeria;

public class PersistenciaGaleria {
	public PersistenciaGaleria() {}
	
	public Galeria cargarGaleria() {
		Galeria galeria = null;
		try {
			FileInputStream fileIn  = new FileInputStream("dataPersistencia/galeria.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			galeria = (Galeria) in.readObject();
			in.close();
			fileIn.close();
		} 
		catch (Exception e) {
			System.out.println("Error al cargar la galeria");
			e.printStackTrace();
		}
		return galeria;
		
	}
	
	public void guardarGaleria(Galeria galeria) {
		try {
			FileOutputStream fileOut = new FileOutputStream("dataPersistencia/galeria.ser");
			 ObjectOutputStream out = new ObjectOutputStream(fileOut);
			 out.writeObject(galeria);
			 out.close();
			 fileOut.close();
		} catch (Exception e) {
			System.out.println("Error al guardar la galeria");
			e.printStackTrace();
		}
		
	}
}
