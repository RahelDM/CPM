package igu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.CardLayout;
import java.awt.Color;

import log.Carta;
import log.Pedido;
import log.Regalo;

import java.awt.Font;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import igu.VentanaPremios.Seccion;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.event.MouseAdapter;

import javax.swing.JCheckBox;

import java.awt.FlowLayout;
import java.awt.Toolkit;

public class SubVentanaPremios extends JFrame {

	// ATRIBUTOS
	private static final long serialVersionUID = 1L;
	private AccionBotonA aB;
	private JPanel contentPane;
	private VentanaPremios ventanaPremios;
	private JPanel pnAlimentacion;
	private JPanel pnElectronica;
	private JPanel pnDeportes;
	private JPanel pnViajes;
	private Carta carta;
	private JPanel pnJuguetes;
	private JPanel panelCard;
	private JPanel subPanelInferior;
	private JPanel subPanelSuperior;
	private JLabel lblSeccion;
	private JButton btnCarrito;
	private JButton btnAtrás;
	private Pedido pedido;
	private JLabel lblPuntosRestantes;
	private JTextField txtPuntosTotales;
	private JButton btnAñadir;
	private JButton btnEliminar;
	private Seccion seccion;
	private JPanel panelTodos;
	private JPanel panel;
	private JTextArea textDescripciones;
	private JButton componente;
	private JCheckBox chckbxFiltro;
	private JLabel lblCabecera;
	private JLabel lblProductosTotales;
	private JLabel lblCesta;
	private ResourceBundle textos;
	private JButton btnAyuda;
	private String titulo;
	private JLabel lblFondoSubPremios;

	/**
	 * Constructor de la ventana SubPremios
	 * 
	 * @param ventanaPremios
	 * @param pedido
	 * @param secion
	 */
	public SubVentanaPremios(VentanaPremios ventanaPremios, Pedido pedido, Seccion secion) {
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(SubVentanaPremios.class.getResource("/imgDecorativas/logo.png")));
		this.seccion = secion;
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				asociarImagenes();
			}
		});
		this.pedido = pedido;
		aB = new AccionBotonA();
		this.ventanaPremios = ventanaPremios;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 824);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getSubPanelInferior());
		contentPane.add(getSubPanelSuperior());
		contentPane.add(getBtnAñadir());
		contentPane.add(getBtnEliminar());
		contentPane.add(getPanelCard());
		contentPane.add(getTextDescripciones());
		contentPane.add(getBtnAyuda());
		contentPane.add(getLblFondoSubPremios());
		comprobarBotones();
		localizar();
		cargaAyuda();
	}

	/**
	 * Método para cargar la ayuda.
	 */
	private void cargaAyuda() {
		URL hsURL;
		HelpSet hs;
		try {
			File fichero = new File("help/ayuda.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		} catch (Exception e) {
			System.out.println("Ayuda no encontrada");
			return;
		}
		HelpBroker hb = hs.createHelpBroker();
		hb.initPresentation();
		hb.enableHelpKey(getRootPane(), "productos", hs);
		hb.enableHelpOnButton(getBtnAyuda(), "productos", hs);

	}

	/**
	 * Método para la localización.
	 */
	private void localizar() {
		ResourceBundle textos = ventanaPremios.getVentanaDeJuego().getVentanaDeIdentificacion().getTextos();
		this.textos = textos;
		carta = new Carta(textos);
		traductor(textos);
	}

	/**
	 * Método de traducción.
	 * 
	 * @param textos
	 */
	private void traductor(ResourceBundle textos) {
		// ToolTipTexts:
		getBtnAtrás().setToolTipText(textos.getString("tooltipAtras"));
		getBtnAñadir().setToolTipText((textos.getString("tooltipAñadir")));
		getBtnEliminar().setToolTipText((textos.getString("tooltipEliminar")));
		getBtnCarrito().setToolTipText((textos.getString("tooltipCarrito")));
		getBtnAyuda().setToolTipText(textos.getString("tooltipAyuda"));

		// Etiquetas:
		getLblPuntosRestantes().setText(textos.getString("lblPuntos"));
		getLblCesta().setText(textos.getString("lblCesta"));

		// Botones
		getBtnAtrás().setText(textos.getString("btnAtrás"));
		getBtnAñadir().setText(textos.getString("btnAñadir"));
		getBtnEliminar().setText(textos.getString("btnEliminar"));

		// CheckBox
		getChckbxFiltro().setText(textos.getString("checkBox"));

		// Mnemonic
		getBtnAtrás().setMnemonic(textos.getString("mneAt").charAt(0));
		getBtnAñadir().setMnemonic(textos.getString("mneAñadir").charAt(0));
		getBtnEliminar().setMnemonic(textos.getString("mneEliminar").charAt(0));
		getBtnCarrito().setMnemonic(textos.getString("mneCesta").charAt(0));
		getChckbxFiltro().setMnemonic(textos.getString("mneCheck").charAt(0));
	}

	// PANELES -------------
	private JPanel getSubPanelSuperior() {
		if (subPanelSuperior == null) {
			subPanelSuperior = new JPanel();
			subPanelSuperior.setBackground(Color.BLACK);
			subPanelSuperior.setBounds(0, 0, 695, 68);
			subPanelSuperior.setLayout(null);
			subPanelSuperior.add(getLblProductosTotales());
			subPanelSuperior.add(getLblCesta());
			subPanelSuperior.add(getBtnCarrito());
			subPanelSuperior.add(getLblSeccion());
			subPanelSuperior.add(getLblCabecera());
		}
		return subPanelSuperior;
	}

	private JPanel getSubPanelInferior() {
		if (subPanelInferior == null) {
			subPanelInferior = new JPanel();
			subPanelInferior.setBackground(new Color(0, 0, 0));
			subPanelInferior.setBounds(0, 744, 671, 43);
			subPanelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			subPanelInferior.add(getBtnAtrás());
			subPanelInferior.add(getLblPuntosRestantes());
			subPanelInferior.add(getTxtPuntosTotales());
			subPanelInferior.add(getChckbxFiltro());
		}
		return subPanelInferior;
	}

	public JPanel getPanelCard() {
		if (panelCard == null) {
			panelCard = new JPanel();
			panelCard.setBorder(new LineBorder(new Color(227, 128, 11), 3, true));
			panelCard.setBackground(Color.DARK_GRAY);
			panelCard.setBounds(63, 78, 548, 500);
			panelCard.setLayout(new CardLayout(0, 0));
			panelCard.add(getPnDeportes(), "pnDeportes");
			panelCard.add(getPnElectronica(), "pnElectronica");
			panelCard.add(getPnJuguetes(), "pnJuguetes");
			panelCard.add(getPnViajes(), "pnViajes");
			panelCard.add(getPanelTodos(), "pnTodos");
			panelCard.add(getPnAlimentacion(), "pnAlimentacion");
		}
		return panelCard;
	}

	private JPanel getPnAlimentacion() {
		if (pnAlimentacion == null) {
			pnAlimentacion = new JPanel();
			pnAlimentacion.setBounds(52, 144, 500, 100);
			pnAlimentacion.setBackground(Color.DARK_GRAY);
			pnAlimentacion.setLayout(new GridLayout(0, 4, 0, 0));

		}
		return pnAlimentacion;
	}

	private JPanel getPnElectronica() {
		if (pnElectronica == null) {
			pnElectronica = new JPanel();
			pnElectronica.setBackground(Color.DARK_GRAY);
			pnElectronica.setBounds(0, 57, 682, 676);
			pnElectronica.setLayout(new GridLayout(0, 4, 0, 0));

		}
		return pnElectronica;
	}

	private JPanel getPnDeportes() {
		if (pnDeportes == null) {
			pnDeportes = new JPanel();
			pnDeportes.setBorder(new LineBorder(new Color(227, 128, 11), 2, true));
			pnDeportes.setBackground(Color.DARK_GRAY);
			pnDeportes.setLayout(new GridLayout(0, 4, 0, 0));

		}
		return pnDeportes;
	}

	private JPanel getPnViajes() {
		if (pnViajes == null) {
			pnViajes = new JPanel();
			pnViajes.setBackground(Color.DARK_GRAY);
			pnViajes.setLayout(new GridLayout(0, 4, 0, 0));

		}
		return pnViajes;
	}

	private JPanel getPnJuguetes() {
		if (pnJuguetes == null) {
			pnJuguetes = new JPanel();
			pnJuguetes.setBackground(Color.DARK_GRAY);
			pnJuguetes.setLayout(new GridLayout(0, 4, 0, 0));

		}
		return pnJuguetes;
	}

	private JPanel getPanelTodos() {
		if (panelTodos == null) {
			panelTodos = new JPanel();
			panelTodos.setBackground(Color.DARK_GRAY);
			panelTodos.setLayout(new GridLayout(0, 4, 0, 0));
		}
		return panelTodos;
	}

	// ETIQUETAS -------------

	private JLabel getLblSeccion() {
		if (lblSeccion == null) {
			lblSeccion = new JLabel("          Alimentacion");
			lblSeccion.setHorizontalAlignment(SwingConstants.CENTER);
			lblSeccion.setForeground(Color.WHITE);
			lblSeccion.setBounds(187, 10, 360, 60);
			lblSeccion.setBackground(Color.BLACK);
			lblSeccion.setFont(new Font("SansSerif", Font.PLAIN, 30));
		}
		return lblSeccion;
	}

	private JLabel getLblCabecera() {
		if (lblCabecera == null) {
			lblCabecera = new JLabel("");
			lblCabecera.setIcon(new ImageIcon(SubVentanaPremios.class.getResource("/imgDecorativas/cabecera.png")));
			lblCabecera.setBounds(10, 7, 644, 58);
		}
		return lblCabecera;
	}

	private JLabel getLblProductosTotales() {
		if (lblProductosTotales == null) {
			lblProductosTotales = new JLabel("0");
			lblProductosTotales.setLabelFor(getBtnCarrito());
			lblProductosTotales.setHorizontalAlignment(SwingConstants.CENTER);
			lblProductosTotales.setBackground(Color.BLACK);
			lblProductosTotales.setForeground(new Color(227, 128, 11));
			lblProductosTotales.setFont(new Font("Serif", Font.PLAIN, 20));
			lblProductosTotales.setBounds(537, -19, 60, 77);
		}
		return lblProductosTotales;
	}

	private JLabel getLblCesta() {
		if (lblCesta == null) {
			lblCesta = new JLabel("Cesta");
			lblCesta.setLabelFor(getBtnCarrito());
			lblCesta.setBackground(Color.BLACK);
			lblCesta.setForeground(Color.WHITE);
			lblCesta.setFont(new Font("SansSerif", Font.PLAIN, 16));
			lblCesta.setBounds(598, 26, 76, 38);
		}
		return lblCesta;
	}

	private JLabel getLblPuntosRestantes() {
		if (lblPuntosRestantes == null) {
			lblPuntosRestantes = new JLabel("       Puntos:");
			lblPuntosRestantes.setForeground(Color.WHITE);
			lblPuntosRestantes.setBackground(Color.BLACK);
			lblPuntosRestantes.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPuntosRestantes.setFont(new Font("SansSerif", Font.PLAIN, 19));
		}
		return lblPuntosRestantes;
	}

	// CUADROS DE TEXTO -------------

	private JTextArea getTextDescripciones() {
		if (textDescripciones == null) {
			textDescripciones = new JTextArea();
			textDescripciones.setBorder(new LineBorder(new Color(227, 128, 11), 3, true));
			textDescripciones.setBounds(12, 585, 635, 106);
			textDescripciones.setLineWrap(true);
			textDescripciones.setWrapStyleWord(true);
			textDescripciones.setEditable(false);
			textDescripciones.setFont(new Font("SansSerif", Font.PLAIN, 12));
			textDescripciones.setBackground(Color.WHITE);
			textDescripciones.setColumns(10);
		}
		return textDescripciones;
	}

	private JTextField getTxtPuntosTotales() {
		if (txtPuntosTotales == null) {
			txtPuntosTotales = new JTextField();
			txtPuntosTotales.setBackground(new Color(192, 192, 192));
			txtPuntosTotales.setFont(new Font("SansSerif", Font.PLAIN, 19));
			txtPuntosTotales.setText(ventanaPremios.getVentanaDeJuego().getJuego().getPuntos() + "");
			txtPuntosTotales.setHorizontalAlignment(SwingConstants.CENTER);
			txtPuntosTotales.setEditable(false);
			txtPuntosTotales.setColumns(10);
		}
		return txtPuntosTotales;
	}

	// ETIQUETAS --------

	public void cambiarPanel(String string, Seccion seccion) {
		((CardLayout) getPanelCard().getLayout()).show(getPanelCard(), string);
		setSeccion(seccion);
		JPanel panel = creaBotonesPnArticulos(seccion);
		setPanel(panel);
		actualizarUnidadesTotales();
	}

	// BOTONES -------------

	private JButton getBtnAñadir() {
		if (btnAñadir == null) {
			btnAñadir = new JButton("A\u00F1adir");
			btnAñadir.setForeground(Color.WHITE);
			btnAñadir.setEnabled(false);
			btnAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirAPedido();
				}
			});
			btnAñadir.setFont(new Font("SansSerif", Font.PLAIN, 19));
			btnAñadir.setBackground(new Color(0, 100, 0));
			btnAñadir.setBounds(157, 701, 160, 33);
		}
		return btnAñadir;
	}

	private void añadirAPedido() {
		int posArticuloEnCarta = Integer.parseInt(componente.getActionCommand());
		Regalo r = carta.getRegalos()[posArticuloEnCarta];
		// Comprobar si es un viaje o no:
		esViajesOTodos(r);
		// Actualizar lógica:
		pedido.add(r, 1);
		int puntos = (int) (ventanaPremios.getVentanaDeJuego().getJuego().getPuntos() - r.getPuntos());
		ventanaPremios.getVentanaDeJuego().getJuego().setPuntos(puntos);
		filtroPrecio();
		// Actualizar interfaz:
		mostrarDescripciones(r);
		actualizarUnidadesTotales();
		comprobarBotones();
		comprobarBotonesAlSeleccionar(componente, r);
		getTxtPuntosTotales().setText(puntos + "");
		ventanaPremios.getTxtPuntosTotales().setText(puntos + "");
		// Consola:
		System.out.println("Se ha añadido al pedido un/a: " + r.getDenominacion() + " por valor de " + r.getPuntos());
	}

	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setForeground(Color.WHITE);
			btnEliminar.setEnabled(false);
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarDePedido();
				}
			});
			btnEliminar.setFont(new Font("SansSerif", Font.PLAIN, 19));
			btnEliminar.setBackground(new Color(178, 34, 34));
			btnEliminar.setBounds(350, 701, 160, 33);
		}
		return btnEliminar;
	}

	private void eliminarDePedido() {
		int posArticuloEnCarta = Integer.parseInt(componente.getActionCommand());
		Regalo r = carta.getRegalos()[posArticuloEnCarta];
		// Actualizar lógica:
		pedido.remove(r, 1);
		int puntos = (int) (ventanaPremios.getVentanaDeJuego().getJuego().getPuntos() + r.getPuntos());
		ventanaPremios.getVentanaDeJuego().getJuego().setPuntos(puntos);
		filtroPrecio();
		// Actualizar interfaz:
		mostrarDescripciones(r);
		actualizarUnidadesTotales();
		comprobarBotones();
		comprobarBotonEliminar();
		comprobarBotonesAlSeleccionar(componente, r);
		getTxtPuntosTotales().setText(puntos + "");
		ventanaPremios.getTxtPuntosTotales().setText(puntos + "");
		// Consola:
		System.out
				.println("Se ha eliminado del pedido un/a: " + r.getDenominacion() + " por valor de " + r.getPuntos());

	}

	private JButton getBtnCarrito() {
		if (btnCarrito == null) {
			btnCarrito =new JButton("");
			btnCarrito.setVerticalAlignment(SwingConstants.BOTTOM);
			btnCarrito.setHorizontalAlignment(SwingConstants.LEFT);
			btnCarrito.setIcon(new ImageIcon(SubVentanaPremios.class.getResource("/imgDecorativas/carrito.png")));
			btnCarrito.setForeground(Color.WHITE);
			btnCarrito.setBorder(new LineBorder(new Color(19, 25, 33), 2, true));
			btnCarrito.setFont(new Font("Arial", Font.PLAIN, 10));
			btnCarrito.setBounds(521, 32, 76, 38);
			btnCarrito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					crearCarrito();
				}
			});
			btnCarrito.setBackground(Color.BLACK);
		}
		return btnCarrito;
	}

	private void crearCarrito() {
		VentanaCarrito vCarrito = new VentanaCarrito(pedido, ventanaPremios);
		vCarrito.setLocationRelativeTo(null);
		vCarrito.setVisible(true);
		vCarrito.actualizar();
	}

	private JButton getBtnAtrás() {
		if (btnAtrás == null) {
			btnAtrás = new JButton("Atr\u00E1s");
			btnAtrás.setBackground(Color.WHITE);
			btnAtrás.setFont(new Font("SansSerif", Font.PLAIN, 19));
			btnAtrás.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaPremios();
				}
			});
		}
		return btnAtrás;
	}

	private void mostrarVentanaPremios() {
		ventanaPremios.setVisible(true);
		setVisible(false);
	}

	private JButton getBtnAyuda() {
		if (btnAyuda == null) {
			btnAyuda = new JButton("?");
			btnAyuda.setFont(new Font("SansSerif", Font.PLAIN, 30));
			btnAyuda.setBorder(new LineBorder(new Color(204, 204, 204), 2, true));
			btnAyuda.setBackground(Color.WHITE);
			btnAyuda.setBounds(10, 702, 36, 33);
		}
		return btnAyuda;
	}

	private JCheckBox getChckbxFiltro() {
		if (chckbxFiltro == null) {
			chckbxFiltro = new JCheckBox("Mostrar articulos canjeables");
			chckbxFiltro.setBackground(new Color(192, 192, 192));
			chckbxFiltro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filtroPrecio();
				}
			});
			chckbxFiltro.setFont(new Font("SansSerif", Font.PLAIN, 19));
		}
		return chckbxFiltro;
	}

	/**
	 * Método que filtra, a partir de los puntos totales ganados
	 * y desabilita la oportunidad de añadir los productos con precios superiores al
	 * pedido.
	 */
	private void filtroPrecio() {
		if (getChckbxFiltro().isSelected()) {
			for (int i = 0; i < panel.getComponents().length; i++) {
				if (seccion == carta.getListaRegalos().get(i)
						.establecerSeccion(carta.getListaRegalos().get(i).getSeccion()) || seccion == Seccion.TODAS) {
					if (carta.getListaRegalos().get(i).getPuntos() > ventanaPremios.getVentanaDeJuego().getJuego()
							.getPuntos()) {
						panel.getComponents()[i].setEnabled(false);
					} else {
						panel.getComponents()[i].setEnabled(true);
					}
				}
			}
		} else {
			filtro();
		}
	System.out.println("Aplicando filtro.");

	}

	// MÉTODOS ----------

	/**
	 * Método para la creación de las distintas categorías.
	 * 
	 * @param seccion
	 * @return el panel indicador de la sección.
	 */
	private JPanel creaBotonesPnArticulos(Seccion seccion) {
		JPanel panel = null;
		getBtnEliminar().setEnabled(false);
		if (seccion.equals(Seccion.ALIMENTACION)) {
			for (int i = 0; i < carta.getRegalos().length; i++) {
				getPnAlimentacion().add(nuevoBoton(i));
			}
			titulos(textos.getString("tituloSubVentanaPremios"), textos.getString("seccionA"));
			panel = getPnAlimentacion();
			return panel;
		} else if (seccion.equals(Seccion.DEPORTES)) {
			for (int i = 0; i < carta.getRegalos().length; i++) {
				getPnDeportes().add(nuevoBoton(i));
			}
			titulos(textos.getString("tituloSubVentanaPremios"), textos.getString("seccionD"));
			panel = getPnDeportes();
			return panel;

		} else if (seccion.equals(Seccion.ELECTRONICA)) {
			for (int i = 0; i < carta.getRegalos().length; i++) {
				getPnElectronica().add(nuevoBoton(i));
			}
			titulos(textos.getString("tituloSubVentanaPremios"), textos.getString("seccionE"));
			panel = getPnElectronica();
			return panel;

		} else if (seccion.equals(Seccion.JUGUETES)) {
			for (int i = 0; i < carta.getRegalos().length; i++) {
				getPnJuguetes().add(nuevoBoton(i));
			}
			titulos(textos.getString("tituloSubVentanaPremios"), textos.getString("seccionJ"));
			panel = getPnJuguetes();
			return panel;

		} else if (seccion.equals(Seccion.VIAJES)) {
			for (int i = 0; i < carta.getRegalos().length; i++) {
				getPnViajes().add(nuevoBoton(i));
			}
			titulos(textos.getString("tituloSubVentanaPremios"), textos.getString("seccionV"));
			panel = getPnViajes();
			return panel;

		} else {
			for (int i = 0; i < carta.getRegalos().length; i++) {
				getPanelTodos().add(nuevoBoton(i));
			}
			titulos(textos.getString("tituloSubVentanaPremios"), textos.getString("seccionT"));
			seccion = Seccion.TODAS;
			panel = getPanelTodos();
			return panel;
		}
	}

	private JButton nuevoBoton(Integer posicion) {
		JButton boton = new JButton("");
		boton.setBackground(Color.white);
		boton.setBorder(new LineBorder(Color.BLACK, 2, true));
		boton.setToolTipText(carta.getListaRegalos().get(posicion).getDenominacion());
		boton.setActionCommand(posicion.toString());
		boton.addMouseListener(aB);
		return boton;
	}

	private void comprobarBotones() {
		if (ventanaPremios.getVentanaDeJuego().getJuego().getPuntos() == 0) {
			getBtnAñadir().setEnabled(false);
		}
	}

	private void comprobarBotonesAlSeleccionar(JButton bt, Regalo r) {
		if (r.getPuntos() <= ventanaPremios.getVentanaDeJuego().getJuego().getPuntos() && bt.isEnabled()) {
			getBtnAñadir().setEnabled(true);
		} else {
			getBtnAñadir().setEnabled(false);
		}
		for (Regalo regalo : pedido.getListaPedido()) {
			if (regalo.getCodigo().equals(r.getCodigo())) {
				if (regalo.getUnidades() > 0) {
					getBtnEliminar().setEnabled(true);
					break;
				}
			} else {
				getBtnEliminar().setEnabled(false);
			}
		}
	}

	private void comprobarBotonEliminar() {
		if (pedido.getTotal() == 0) {
			getBtnEliminar().setEnabled(false);
		} else {
			getBtnEliminar().setEnabled(true);
		}
	}

	private void habilitarBotones(boolean e) {
		for (int i = 0; i < carta.getRegalos().length; i++) {
			panel.getComponents()[i].setEnabled(e);
		}
	}

	private void filtro() {
		habilitarBotones(false);
		for (int i = 0; i < carta.getRegalos().length; i++) {
			Regalo regalo = carta.getRegalos()[i];
			if (seccion == Seccion.TODAS) {
				habilitarBotones(true);
				break;
			} else if ((regalo.getSeccion().equals("A") || regalo.getSeccion().equals("F"))
					&& seccion == Seccion.ALIMENTACION) {
				panel.getComponent(i).setEnabled(true);
			} else if ((regalo.getSeccion().equals("D") || regalo.getSeccion().equals("S"))
					&& seccion == Seccion.DEPORTES) {
				panel.getComponent(i).setEnabled(true);
			} else if (regalo.getSeccion().equals("E") && seccion == Seccion.ELECTRONICA) {
				panel.getComponent(i).setEnabled(true);
			} else if (regalo.getSeccion().equals("V") && seccion == Seccion.VIAJES) {
				panel.getComponent(i).setEnabled(true);
			} else if ((regalo.getSeccion().equals("J") || regalo.getSeccion().equals("T"))
					&& seccion == Seccion.JUGUETES) {
				panel.getComponent(i).setEnabled(true);
			}
		}
	}

	private void actualizarUnidadesTotales() {
		int unidades = pedido.getListaPedido().size();
		getLblProductosTotales().setText(unidades + "");

	}

	private void mostrarDescripciones(Regalo regalo) {
		getTextDescripciones().setText(regalo.toString()+textos.getString("precioEs") +regalo.getPuntos()+ textos.getString("lblUnidades2") + " "
				+ pedido.buscarUnidades(regalo) + " " + textos.getString("lblUnidades"));

	}
	

	private void marcarBoton(Regalo regalo) {
		for (int i = 0; i < carta.getRegalos().length; i++) {
			((JButton) panel.getComponents()[i]).setBorder(new LineBorder(Color.GRAY, 3));
			if (regalo.getCodigo().equals(carta.getListaRegalos().get(i).getCodigo())) {
				((JButton) panel.getComponents()[i]).setBorder(new LineBorder(Color.YELLOW, 3));
			}
		}

	}

	private void setImagenAdaptada(JButton boton, String rutaImagen) {
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_FAST);
		ImageIcon icon = new ImageIcon(imgEscalada);
		boton.setIcon(icon);
	}

	private void asociarImagenes() {
		for (int i = 0; i < panel.getComponents().length; i++) {
			JButton boton = (JButton) (panel.getComponents()[i]);
			setImagenAdaptada(boton, "/img/" + carta.getListaRegalos().get(i).getCodigo() + ".png");
		}
		filtro();
		filtroPrecio();
	}

	private void esViajesOTodos(Regalo r) {
		if (Seccion.VIAJES.equals(r.establecerSeccion(r.getSeccion()))) {
			if (preguntar()) {
				VentanaObservaciones vO = new VentanaObservaciones(r, this);
				vO.setLocationRelativeTo(null);
				vO.setVisible(true);
			}
		}
	}

	private boolean preguntar() {
		JOptionPane.showMessageDialog(null, textos.getString("mensajeObservaciones"), null,
				JOptionPane.INFORMATION_MESSAGE);
		return true;
	}

	private void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public void titulos(String ventana, String seccion) {
		titulo = ventana + " " + seccion;
		this.setTitle(titulo);
		this.getLblSeccion().setText(seccion);
	}

	public VentanaPremios getVentanaPremios() {
		return ventanaPremios;
	}

	public String getTituloVentana() {
		return titulo;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;

	}

	class AccionBotonA extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			JButton bt = (JButton) e.getSource();
			componente = bt;
			int posicion = Integer.parseInt(bt.getActionCommand());
			Regalo r = carta.getRegalos()[posicion];
			mostrarDescripciones(r);
			marcarBoton(r);
			// Habilitar la posibilidad de añadir el regalo:
			comprobarBotonesAlSeleccionar(bt, r);
			// Consola:
			System.out.println("Seleccionado el regalo: " + r.getDenominacion() + " por valor de " + r.getPuntos());
		}

	}
	private JLabel getLblFondoSubPremios() {
		if (lblFondoSubPremios == null) {
			lblFondoSubPremios = new JLabel("");
			lblFondoSubPremios.setIcon(new ImageIcon(SubVentanaPremios.class.getResource("/imgDecorativas/fondoSubPremios.png")));
			lblFondoSubPremios.setBounds(0, 69, 671, 674);
		}
		return lblFondoSubPremios;
	}
}
