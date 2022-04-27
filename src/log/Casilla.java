package log;

public class Casilla {

	/**
	 *  Clase Casilla.
	 *  
	 *  @author Raquel Suárez Álvarez UO284104.
	 */
	
	// --------------------------- ATRIBUTOS -----------------------------

	private int puntos;
	private boolean bonus;
	private boolean por2;

	// --------------------------- CONSTRUCTOR -----------------------------

	/**
	 * Constructor de la clase Casilla
	 * @param puntos
	 * @param bonus
	 * @param por2
	 */
	public Casilla(int puntos, boolean bonus, boolean por2) {
		setPuntos(puntos);
		setBonus(bonus);
		setPorDos(por2);
	}
	

	private void setPorDos(boolean por2) {
		this.por2 = por2;
	}

	public int getPuntos() {
		return puntos;
	}

	private void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	private void setBonus(boolean bonus) {
		this.bonus = bonus;
	}

	/**
	 * Método que devuelve true si la casilla tiene bonus.
	 * @return bonus.
	 */
	public boolean tieneBonus() {
		return bonus;
	}
	
	/**
	 * Método que devuelve true si la casilla
	 * permite duplicar puntos.
	 * @return
	 */
	public boolean duplicaPuntos() {
		return por2;
	}
}
