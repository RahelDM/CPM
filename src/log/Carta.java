package log;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import igu.VentanaPremios.Seccion;

public class Carta {
	
	/**
	 * Clase Carta
	 * 
	 * @author Raquel Suárez Álvarez UO284104.
	 */

	// --------------------------- ATRIBUTOS -----------------------------

	private static final String FICHERO = "files/";
	private List<Regalo> listaRegalos = null;
	private ResourceBundle textos;

	// --------------------------- CONSTRUCTOR -----------------------------

	/**
	 * Constructor de la clase Carta. Inicializa un arrayList de artículos y los
	 * carga dentro de la carta.
	 * 
	 * @param textos
	 */
	public Carta(ResourceBundle textos) {
		this.textos = textos;
		listaRegalos = new ArrayList<Regalo>();
		cargarRegalos();
	}

	/**
	 * Método privado que se ocupa de cargar adecuadamente los artículos.
	 */
	private void cargarRegalos() {
		String str = cargarIdioma();
		FileUtil.loadFileRegalos(FICHERO + str, listaRegalos);
	}

	private String cargarIdioma() {
		return textos.getString("fichero");
	}

	/**
	 * Método que devuelve un array de artículos.
	 * 
	 * @return
	 */
	public Regalo[] getRegalos() {
		Regalo[] articulos = listaRegalos.toArray(new Regalo[listaRegalos.size()]);
		return articulos;
	}

	public List<Regalo> getListaRegalos() {
		return listaRegalos;
	}

	/**
	 * Método que devuelve un array solo con los productos que pertenezcan a la
	 * categoría indicada por el parámetro.
	 * 
	 * @param art
	 * @return un array.
	 */
	public Regalo[] getProductos(Seccion seccion) {
		List<Regalo> regalos = new ArrayList<Regalo>();
		for (Regalo regalo : listaRegalos) {
			if (regalo.getSeccion() == "A" && seccion == Seccion.ALIMENTACION) {
				regalos.add(regalo);
			} else if (regalo.getSeccion() == "D" && seccion == Seccion.DEPORTES) {
				regalos.add(regalo);
			} else if (regalo.getSeccion() == "E" && seccion.equals(Seccion.ELECTRONICA)) {
				regalos.add(regalo);
			} else if (regalo.getSeccion() == "J" && seccion.equals(Seccion.JUGUETES)) {
				regalos.add(regalo);
			} else if (regalo.getSeccion() == "V" && seccion.equals(Seccion.VIAJES)) {
				regalos.add(regalo);
			}
		}
		return regalos.toArray(new Regalo[listaRegalos.size()]);
	}

}
