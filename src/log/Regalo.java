package log;

import igu.VentanaPremios.Seccion;
import log.Regalo;

public class Regalo {

	/**
	 * Clase Regalo.
	 * 
	 * @author Raquel Suárez Álvarez UO284104.
	 */

	// --------------------------- ATRIBUTOS -----------------------------

	private String codigo;
	private String seccion;
	private String denominacion;
	private int puntos;
	private String descripcion;
	private int unidades;
	private String observaciones;
	private String fecha;

	// --------------------------- CONSTRUCTOR -----------------------------

	/**
	 * Constructo de la clase Regalo
	 * 
	 * @param codigo
	 * @param seccion
	 * @param denominacion
	 * @param descripcion
	 * @param puntos
	 * @param unidades
	 */
	public Regalo(String codigo, String seccion, String denominacion, String descripcion, int puntos, int unidades) {
		this.codigo = codigo;
		this.seccion = seccion;
		this.denominacion = denominacion;
		this.puntos = puntos;
		this.descripcion = descripcion;
		this.unidades = unidades;
		this.observaciones = "No hay observaciones";
	}

	public Regalo(Regalo otroArticulo) {
		this(otroArticulo.codigo, otroArticulo.seccion, otroArticulo.denominacion, otroArticulo.descripcion,
				otroArticulo.puntos, otroArticulo.unidades);
		setObservaciones(otroArticulo.getObservaciones());
		setFecha(otroArticulo.getFecha());
		setUnidades(otroArticulo.getUnidades());
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	String getFecha() {
		return fecha;
	}

	/**
	 * Devuelve la denominación de un artículo.
	 * 
	 * @return una cadena de texto.
	 */
	public String getDenominacion() {
		return denominacion;
	}

	/**
	 * Modifica la descripción de un artículo.
	 * 
	 * @param la nueva cadena de texto
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Devuelve la descripción de un artículo.
	 * 
	 * @return una cadena de texto.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Modifica la denominación de un artículo.
	 * 
	 * @param la nueva cadena de texto
	 */
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	/**
	 * Devuelve el precio de un artículo.
	 * 
	 * @return un float.
	 */
	public int getPuntos() {
		return puntos;
	}

	/**
	 * Modifica el precio de un artículo.
	 * 
	 * @param un float.
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	/**
	 * Devuelve el código de un artículo.
	 * 
	 * @return una cadena de texto.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Modifica el código de un artículo.
	 * 
	 * @param la nueva cadena de texto
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Devuelve las unidades pedidas de un artículo.
	 * 
	 * @return una entero.
	 */
	public String getSeccion() {
		return seccion;
	}

	/**
	 * Modifica las unidades pedidas de un artículo.
	 * 
	 * @param las nuevas unidades.
	 */
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	/**
	 * Devuelve las unidades pedidas de un artículo.
	 * 
	 * @return una entero.
	 */
	public int getUnidades() {
		return unidades;
	}

	/**
	 * Modifica las unidades pedidas de un artículo.
	 * 
	 * @param las nuevas unidades.
	 */
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	/**
	 * Método toString de un artículo.
	 * 
	 * @return una cadena de texto con toda la información de un artículo.
	 */
	public String toString() {
		String strArticulo;
		strArticulo = this.seccion + "-" + this.denominacion + "\n" + this.descripcion + "\n";
		return strArticulo;
	}

	public void setObservaciones(String obser) {
		this.observaciones = obser;
	}

	public Seccion establecerSeccion(String str) {
		switch (str) {
		case "A", "F":
			return Seccion.ALIMENTACION;
		case "D", "S":
			return Seccion.DEPORTES;
		case "E":
			return Seccion.ELECTRONICA;
		case "J", "T":
			return Seccion.JUGUETES;
		case "V":
			return Seccion.VIAJES;
		default:
			return null;
		}
	}

	public String getObservaciones() {
		return observaciones;
	}

	public String getFechaObservaciones() {
		String str;
		if (getObservaciones().isBlank() || getObservaciones().isEmpty()) {
			str = getFecha();
		} else {
			str = getFecha() + "@" + getObservaciones();
		}
		return str;
	}

}
