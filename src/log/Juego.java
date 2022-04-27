package log;

public class Juego {

	/**
	 * Clase Juego.
	 * 
	 * @author Raquel Suárez Álvarez UO284104.
	 */

	// --------------------------- ATRIBUTOS -----------------------------

	int puntos;
	private int dim;
	private int intentos;
	private Tablero tablero;
	private boolean porDos;
	private boolean bonus;
	private String IDJugador;
	public static final int DIM=25;
	public static final int CASILLAS_A_LEVANTAR=3;

	// --------------------------- CONSTRUCTOR -----------------------------

	/**
	 * Constructor de la clase Juego.
	 * 
	 * @param ID
	 */
	public Juego(String ID) {
		this.IDJugador = ID;
		puntos = 0;
		porDos = false;
		bonus = false;
		setDim(DIM);
		setIntentos(CASILLAS_A_LEVANTAR);
		tablero = new Tablero(DIM);
	}

	/**
	 * Método para la selección de casillas.
	 * @param i
	 */
	public void selecciona(int i) {
		intentos--;
		if (tablero.getCasillas()[i].duplicaPuntos()) {
			setPuntos(getPuntos() * 2);
			porDos = true;
		} else if (tablero.getCasillas()[i].tieneBonus()) {
			setIntentos(getIntentos() + 1);
			bonus = true;
		}
		puntos += tablero.getCasillas()[i].getPuntos();
		System.out.println("Seleccionada la casilla: "+i+"\n Ganados: " +tablero.getCasillas()[i].getPuntos()+" puntos.");
	}

	public boolean bonusEncontrado() {
		return bonus;
	}

	public boolean porDosEncontrado() {
		return porDos;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getIntentos() {
		return intentos;
	}

	private void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public int getDim() {
		return dim;
	}

	private void setDim(int dim) {
		this.dim = dim;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public String getJugador() {
		return IDJugador;
	}

	public boolean isPartidaFinalizada() {
		return (intentos == 0);
	}
}
