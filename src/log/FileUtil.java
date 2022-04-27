package log;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public abstract class FileUtil {

	/**
	 * Clase FileUtil.
	 * 
	 * @author Raquel Suárez Álvarez UO284104.
	 */

	public static void loadFileJugadores(String nombreFicheroEntrada, List<Cliente> listaClientes) {
		String linea;
		String[] datosCliente = null;

		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
			while (fichero.ready()) {
				linea = fichero.readLine();
				datosCliente = linea.split("@");
				listaClientes.add(new Cliente(datosCliente[0], datosCliente[1], Integer.parseInt(datosCliente[2])));
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}

	public static void loadFileRegalos(String nombreFicheroEntrada, List<Regalo> listaRegalos) {
		String linea;
		String[] datosRegalo = null;

		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
			while (fichero.ready()) {
				linea = fichero.readLine();
				datosRegalo = linea.split("@");
				listaRegalos.add(new Regalo(datosRegalo[0], datosRegalo[1], datosRegalo[2], datosRegalo[3],
						Integer.parseInt(datosRegalo[4]), 0));
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}

	public static void saveToFile(String nombreFicheroSalida, String txPedido) {
		try {
			String filePedido = "files/" + nombreFicheroSalida + ".dat";
			Path path = Paths.get(filePedido);
			if (!Files.exists(path.getParent())) {
				Files.createDirectory(path.getParent());
			}
			BufferedWriter fichero = new BufferedWriter(new FileWriter(filePedido,true));
			fichero.append(txPedido);
			fichero.close();
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha podido guardar");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida");
		}
	}


	public static void saveToFileClientes(String nombreFicheroSalida, String txPedido) {
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter(nombreFicheroSalida));
			fichero.write(txPedido);
			fichero.close();
		}

		catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha podido guardar");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida");
		}
	}

}
