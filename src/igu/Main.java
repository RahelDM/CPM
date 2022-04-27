package igu;

import java.awt.EventQueue;

import log.Clientela;

public class Main {
	
	/**
	 * Clase Main
	 * Panel de Regalos.
	 * 
	 * @author Raquel Suárez Álvarez UO284104.
	 */

	public static void main(String[] args) {
		Clientela listaDeJugadores = new Clientela();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDeIdentificacion frame = new VentanaDeIdentificacion(listaDeJugadores);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
