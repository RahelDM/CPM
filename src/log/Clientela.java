package log;

import java.util.ArrayList;
import java.util.List;

public class Clientela {

	/**
	 * Clase Clientela.
	 * 
	 * @author Raquel Suárez Álvarez UO284104.
	 */

	// --------------------------- ATRIBUTOS -----------------------------

	private static final String FICHERO_CLIENTES = "files/clientes.dat";

	private List<Cliente> listaClientes;
	private List<Cliente> listaJugadores;

	// --------------------------- CONSTRUCTOR -----------------------------

	/**
	 * Constructor de la clase Clientela.
	 */
	public Clientela() {
		listaClientes = new ArrayList<Cliente>();
		listaJugadores = new ArrayList<Cliente>();

		inicializar();
	}

	public void inicializar() {
		listaClientes.clear();
		cargarClientes();
		cargarJugadores();
	}

	/**
	 * Método privado que se ocupa de cargar adecuadamente los clientes.
	 */
	private void cargarClientes() {
		FileUtil.loadFileJugadores(FICHERO_CLIENTES, listaClientes);

	}
	
	public void grabarClientes() {
		FileUtil.saveToFileClientes(FICHERO_CLIENTES, this.toString());
	}

	/**
	 * Método que devuelve un array de clientes.
	 * 
	 * @return
	 */
	public Cliente[] getClientes() {
		Cliente[] clientes = listaClientes.toArray(new Cliente[listaClientes.size()]);
		return clientes;
	}

	/**
	 * Método que devuelve un array de jugadores.
	 * 
	 * @return
	 */
	public Cliente[] getJugadores() {
		Cliente[] jugadores = listaJugadores.toArray(new Cliente[listaJugadores.size()]);
		return jugadores;
	}

	private void cargarJugadores() {
		for (Cliente cl : listaClientes) {
			if (cl.esJugador()) {
				listaJugadores.add(cl);
			}
		}
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	public String toString() {
		String str = "";
		for(Cliente cliente: listaClientes) {
			str=cliente.toString()+"\n"+str;
		}
		return str;
	}

	
	
}
