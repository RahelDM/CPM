package log;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	
	/**
	 * Clase Clientela.
	 * 
	 * @author Raquel Suárez Álvarez UO284104.
	 */

	// --------------------------- ATRIBUTOS -----------------------------
	
	private static final String FICHERO_SALIDA = "entregas";
	private List<Regalo> listaPedido;
	private String idCliente;

	// --------------------------- CONSTRUCTOR -----------------------------

	/**
	 * Constructor de la clase Pedido
	 * 
	 * @param idCliente del cliente al que pertenece el pedido.
	 */
	public Pedido(String idCliente) {
		this.idCliente = idCliente;
		listaPedido = new ArrayList<Regalo>();
		inicializar();
	}

	public void inicializar() {
		listaPedido.clear();
	}

	public boolean isVacio() {
		return listaPedido.size() == 0;
	}

	/**
	 * Método para añadir al pedido un regalo.
	 * 
	 * @param regaloDelCatalogo
	 * @param unidades
	 */
	public void add(Regalo regaloDelCatalogo, int unidades) {
		Regalo regaloEnPedido = null;

		for (Regalo a : listaPedido) {
			if (a.getCodigo().equals(regaloDelCatalogo.getCodigo())) {
				regaloEnPedido = a;
				regaloEnPedido.setUnidades(regaloEnPedido.getUnidades() + unidades);
				break;
			}
		}
		if (regaloEnPedido == null) {
			Regalo regaloAPedido = new Regalo(regaloDelCatalogo);
			regaloAPedido.setUnidades(unidades);
			listaPedido.add(regaloAPedido);
		}

	}

	/**
	 * Método para eliminar un regalo del pedido.
	 * @param regalo
	 * @param unidades
	 */
	public void remove(Regalo regalo, int unidades) {
		Regalo regaloEnPedido = null;
		for (Regalo r : listaPedido) {
			if (r.getCodigo().equals(regalo.getCodigo()))
				regaloEnPedido = r;
		}
		if (regaloEnPedido != null) {
			int totalUnidades = regaloEnPedido.getUnidades() - unidades;
			if (totalUnidades <= 0) {
				listaPedido.remove(regaloEnPedido);
			} else {
				regaloEnPedido.setUnidades(totalUnidades);
			}
		}
	}

	/**
	 * Devuelve los puntos totales gastados.
	 * 
	 * @return los puntos gastados.
	 */
	public float getTotal() {
		int precio = 0;
		for (Regalo r : listaPedido) {
			precio += r.getPuntos() * r.getUnidades();
		}
		return precio;
	}

	public void grabarPedido() {
		FileUtil.saveToFile(FICHERO_SALIDA, this.toString());
	}

	public int buscarUnidades(Regalo regaloSeleccionado) {
		for (Regalo a : listaPedido) {
			if (a.getCodigo().equals(regaloSeleccionado.getCodigo()))
				return (a.getUnidades());
		}
		return 0;
	}

	public String toString() {
		String strPedido = "";
		for (Regalo a : listaPedido) {
			for (int i = 0; i < a.getUnidades(); i++) {
				if (a.getSeccion().equals("V")) {
					strPedido = strPedido + "\n" + idCliente + "@" + a.getCodigo() + "@"+a.getFechaObservaciones();
				} else {
					strPedido = strPedido + "\n" + idCliente + "@" + a.getCodigo();
				}
			}
		}
		return strPedido;
	}

	public List<Regalo> getListaPedido() {
		return listaPedido;
	}
	

}
