package igu;

import javax.swing.ImageIcon;

import log.Casilla;

public class ImagenFactoria {

	//ATRIBUTOS
	private static final String IMAGEN_DUPLICAPUNTOS = "/imgCasillas/porDos.png";
	private static final String IMAGEN_BONUS = "/imgCasillas/+1.png";
	private static final String IMAGEN_1000 = "/imgCasillas/1000(2).png";
	private static final String IMAGEN_250 = "/imgCasillas/250.png";
	private static final String IMAGEN_50 = "/imgCasillas/50.png";
	private static final String IMAGEN_0 ="/imgCasillas/0.png";
	
	public static ImageIcon getImagen(Casilla casilla) {
		if (casilla.duplicaPuntos() && casilla.getPuntos()==0) {
			return cargaImagen(IMAGEN_DUPLICAPUNTOS);
		} else if (casilla.tieneBonus()) {
			return cargaImagen(IMAGEN_BONUS);
		} else if (casilla.getPuntos() == 1000) {
			return cargaImagen(IMAGEN_1000);
		} else if (casilla.getPuntos() == 250) {
			return cargaImagen(IMAGEN_250);
		} else if (casilla.getPuntos() == 50) {
			return cargaImagen(IMAGEN_50);
		} else if (casilla.getPuntos() == 0) {
			return cargaImagen(IMAGEN_0);
		}
		return null;
	}

	private static ImageIcon cargaImagen(String fichero) {
		return new ImageIcon(ImagenFactoria.class.getResource(fichero));
	}
}
