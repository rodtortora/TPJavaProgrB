package controller;

import java.io.*;
import java.util.ArrayList;

import model.TarjetaATM;

public class SerializeController {
	
	public static void escribir(ArrayList<TarjetaATM> tarjetas) throws IOException {
		FileOutputStream fout = new FileOutputStream("tarjetas.dat");
		ObjectOutputStream outStream = new ObjectOutputStream(fout);
		outStream.writeObject(tarjetas);
		outStream.flush();
		outStream.close();		
	}
	
	public static ArrayList<TarjetaATM> leerTarjetas() throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream("tarjetas.dat");
		ObjectInputStream inStream = new ObjectInputStream(fin);
		ArrayList<TarjetaATM> tarjetas = (ArrayList<TarjetaATM>)inStream.readObject();
		inStream.close();
		return tarjetas;
	}

}
