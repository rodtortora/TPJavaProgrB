/*package controller;

import java.io.*;
import java.util.ArrayList;

import model.TarjetaATM;

public class SerializeController {
	
	public static void escribir(TarjetaATM tarjeta, String fichero) throws IOException {
		FileOutputStream fout = new FileOutputStream(fichero + ".dat");
		ObjectOutputStream outStream = new ObjectOutputStream(fout);
		outStream.writeObject(tarjeta);
		outStream.flush();
		outStream.close();		
	}
	
	public static ArrayList<TarjetaATM> leerTarjetas(String fichero) throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream(fichero + ".dat");
		ObjectInputStream inStream = new ObjectInputStream(fin);
		TarjetaATM card;
		ArrayList<TarjetaATM> tarjetas = new ArrayList<>();
		Object aux = inStream.readObject();
		while (aux!=null) {
			card = (TarjetaATM) inStream.readObject();
			tarjetas.add(card);
			
		}
		inStream.close();
		return tarjetas;
	}
}
*/