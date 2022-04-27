package log;

public class Tablero {

	/**
	 * Clase Tablero.
	 * 
	 * @author Raquel Suárez Álvarez UO284104.
	 */

	// --------------------------- ATRIBUTOS -----------------------------

	public static final int DIM = 25;
	Casilla[] casillas;
	private int dim;

	// --------------------------- CONSTRUCTOR -----------------------------

	/**
	 * Constructor de un tablero.
	 * 
	 * @param dim
	 */
	public Tablero(int dim) {
		this.dim = dim;
		casillas = new Casilla[dim];
		colocar0Puntos();
		colocarPor2();
		colocarUnaCasillaMas();
		colocar50puntos();
		colocar250puntos();
		colocar1000puntos();
	}

	// Métodos para la colocación de las distintas casillas: 
	
	private void colocar0Puntos() {
		int posicion;
		for (int i = 0; i < 8; i++) {
			do {
				posicion = (int) (Math.random() * dim);
			} while (!hayEspacio(posicion));
			casillas[posicion] = new Casilla(0, false, false);
			System.out.println("Casilla de 0 en " + posicion);
		}
	}

	private void colocarPor2() {
		int posicion;
		do {
			posicion = (int) (Math.random() * dim);
		} while (!hayEspacio(posicion));
		casillas[posicion] = new Casilla(0, false, true);
		System.out.println("Casilla duplicadora en " + posicion);
	}

	private void colocarUnaCasillaMas() {
		int posicion;
		for (int i = 0; i < 2; i++) {
			do {
				posicion = (int) (Math.random() * dim);
			} while (!hayEspacio(posicion));
			casillas[posicion] = new Casilla(0, true, false);
			System.out.println("Casilla de bonus en " + posicion);
		}
	}

	private void colocar50puntos() {
		int posicion;
		for (int i = 0; i < 8; i++) {
			do {
				posicion = (int) (Math.random() * dim);
			} while (!hayEspacio(posicion));
			casillas[posicion] = new Casilla(50, false, false);
			System.out.println("Casilla de 50 en " + posicion);
		}
	}

	private void colocar250puntos() {
		int posicion;
		for (int i = 0; i < 5; i++) {
			do {
				posicion = (int) (Math.random() * dim);
			} while (!hayEspacio(posicion));
			casillas[posicion] = new Casilla(250, false, false);
			System.out.println("Casilla de 250 en " + posicion);
		}
	}

	private void colocar1000puntos() {
		int posicion;
		do {
			posicion = (int) (Math.random() * dim);
		} while (!hayEspacio(posicion));
		casillas[posicion] = new Casilla(1000, false, false);
		System.out.println("Casilla de 1000 en " + posicion);

	}

	/**
	 * Método que busca espacio libre.
	 * @param i
	 * @return
	 */
	private boolean hayEspacio(int i) {
		if (getCasillas()[i] == null)
			return true;
		return false;
	}

	public Casilla[] getCasillas() {
		return casillas;
	}
}
