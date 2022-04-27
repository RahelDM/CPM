package log;

public class Cliente {

	/**
	 * Clase Cliente.
	 * 
	 * @author Raquel Suárez Álvarez UO284104.
	 */

	// --------------------------- ATRIBUTOS -----------------------------
	
	private String ID;
	private String nombre;
	private boolean esJugador;
	private int puedeJugar;
	
	// --------------------------- CONSTRUCTOR -----------------------------

	/**
	 * Constructor de la clase Cliente
	 * @param ID
	 * @param nombre
	 * @param puedeJugar
	 */
	public Cliente(String ID, String nombre, int puedeJugar) {
		this.puedeJugar=puedeJugar;
		comprobarJugador(puedeJugar);
		setNombre(nombre);
		setID(ID);
	}

	public String getID() {
		return ID;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean esJugador() {
		return esJugador;
	}

	// SET
	private void setID(String iD) {
		this.ID = iD;
	}
	
	public int getPuedeJugar() {
		return puedeJugar;
	}


	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método que hace que el jugador dejé de ser jugador.
	 */
	public void yaNoEsJugador() {
		esJugador = false;
		puedeJugar = 0;
	}

	private boolean comprobarJugador(int valor) {
		return esJugador = (valor == 1) ? true : false;
	}

	public String toString() {
		@SuppressWarnings("unused")
		String str;
		if(esJugador) {
			return str= getID()+"@"+getNombre()+"@"+1;
		}else {
			return str= getID()+"@"+getNombre()+"@"+0;
		}
	}
}
