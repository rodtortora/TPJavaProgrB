package controller;

import java.io.*;
import java.util.ArrayList;

import model.TarjetaATM;

public class SerializeController {
	
	public static void escribir(TarjetaATM tarjeta, String fichero) throws IOException {
		FileOutputStream fout = new FileOutputStream(fichero);
		ObjectOutputStream outStream = new ObjectOutputStream(fout);
		outStream.writeObject(tarjeta);
		outStream.flush();
		outStream.close();		
	}
	
	public static ArrayList<TarjetaATM> leerTarjetas(String fichero) throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream(fichero);
		ObjectInputStream inStream = new ObjectInputStream(fin);
		TarjetaATM card = (TarjetaATM) inStream.readObject();
		ArrayList<TarjetaATM> tarjetas = new ArrayList<>();
		while (card!=null) {
			tarjetas.add(card);
			card = (TarjetaATM) inStream.readObject();
		}
		inStream.close();
		return tarjetas;
	}
}
