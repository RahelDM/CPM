package igu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import log.Pedido;
import log.Regalo;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSeparator;

public class VentanaCarrito extends JDialog {

	// ATRIBUTOS
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Pedido pedido;
	private JLabel lblPuntosGastados;
	private JLabel lblPuntosSobrantes;
	private JTextField textPuntosSobrantes;
	private JTextField textPuntosTotales;
	private JButton btnCanjear;
	private JButton btnAtrás;
	private VentanaPremios vP;
	private JTable table;
	private Object[][] datosRegalos;
	private DefaultTableModel d;
	private String[] columnasRegalos;
	private JScrollPane scrollPane;
	private JLabel lblCarritoMensaje;
	private JLabel lblCarrito;
	private JSeparator separator;
	private ResourceBundle textos;
	private JButton btnAyuda;

	/**
	 * Constructor de la VentanaCarrito
	 * 
	 * @param pedido
	 * @param vP
	 */
	public VentanaCarrito(Pedido pedido, VentanaPremios vP) {
		setResizable(false);
		setModal(true);
		this.vP = vP;
		this.pedido = pedido;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 797, 464);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(new Color(19, 25, 33));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblPuntosGastados());
		contentPane.add(getLblPuntosSobrantes());
		contentPane.add(getTextPuntosSobrantes());
		contentPane.add(getTextPuntosTotales());
		contentPane.add(getBtnCanjear());
		contentPane.add(getBtnAtrás());
		contentPane.add(getLblCarritoMensaje());
		contentPane.add(getLblCarrito());
		contentPane.add(getSeparator());
		getRootPane().setDefaultButton(getBtnCanjear());
		localizar();
		// Tabla del Carrito -----
		String[] columnas = { textos.getString("seccion"), textos.getString("nombre"), textos.getString("valor"),
				textos.getString("unidades2") };
		this.columnasRegalos = columnas;
		Object[][] datos = { { "", "", "", "" }, };
		this.datosRegalos = datos;
		d = new DefaultTableModel(datosRegalos, columnasRegalos);
		contentPane.add(getScrollPane());
		contentPane.add(getBtnAyuda());
		// ----
		comprobarBotonCanejar();
		mostrarEnLista();
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
		hb.enableHelpKey(getRootPane(), "carrito", hs);
		hb.enableHelpOnButton(getBtnAyuda(), "carrito", hs);

	}

	/**
	 * Método para la localización.
	 */
	private void localizar() {
		ResourceBundle textos = vP.getVentanaDeJuego().getVentanaDeIdentificacion().getTextos();
		this.textos = textos;
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
		getBtnCanjear().setToolTipText(textos.getString("tooltipCanjear"));
		// Etiquetas:
		getLblCarrito().setText(textos.getString("lblCesta"));
		getLblPuntosGastados().setText(textos.getString("puntosGastados"));
		getLblPuntosSobrantes().setText(textos.getString("puntosSobrantes"));
		// Botones
		getBtnAtrás().setText(textos.getString("btnAtrás"));
		getBtnCanjear().setText(textos.getString("btnCanjearTodo"));
		// Título
		setTitle(vP.getSubVentanaDePremios().getTituloVentana() + ": " + textos.getString("lblCesta"));
		// Mnemonic
		getBtnAtrás().setMnemonic(textos.getString("mneAt").charAt(0));
		getBtnCanjear().setMnemonic(textos.getString("mneCanjear").charAt(0));

	}

	// BOTONES --------
	private JButton getBtnCanjear() { 
		if (btnCanjear == null) {
			btnCanjear = new JButton("Canjear Puntos");
			btnCanjear.setForeground(Color.WHITE);
			btnCanjear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					finalizar();
				}
			});
			btnCanjear.setBounds(289, 380, 178, 37);
			btnCanjear.setFont(new Font("SansSerif", Font.PLAIN, 19));
			btnCanjear.setBackground(new Color(0, 100, 0));
		}
		return btnCanjear;
	}

	private JButton getBtnAtrás() {
		if (btnAtrás == null) {
			btnAtrás = new JButton("Atr\u00E1s");
			btnAtrás.setBackground(Color.WHITE);
			btnAtrás.setFont(new Font("SansSerif", Font.PLAIN, 19));
			btnAtrás.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnAtrás.setFont(new Font("SansSerif", Font.PLAIN, 15));
			btnAtrás.setBounds(10, 380, 107, 37);
		}
		return btnAtrás;
	}

	private JButton getBtnAyuda() {
		if (btnAyuda == null) {
			btnAyuda = new JButton("?");
			btnAyuda.setToolTipText((String) null);
			btnAyuda.setFont(new Font("SansSerif", Font.PLAIN, 30));
			btnAyuda.setBorder(new LineBorder(new Color(204, 204, 204), 2, true));
			btnAyuda.setBackground(Color.WHITE);
			btnAyuda.setBounds(735, 380, 38, 37);
		}
		return btnAyuda;
	}

	private void comprobarBotonCanejar() {
		if (pedido.isVacio()) {
			getBtnCanjear().setEnabled(false);
		} else {
			getBtnCanjear().setEnabled(true);
		}
	}

	// ETIQUETAS -----
	
	private JLabel getLblPuntosGastados() {
		if (lblPuntosGastados == null) {
			lblPuntosGastados = new JLabel("Total de puntos gastados: ");
			lblPuntosGastados.setForeground(Color.WHITE);
			lblPuntosGastados.setFont(new Font("SansSerif", Font.PLAIN, 15));
			lblPuntosGastados.setBounds(429, 327, 198, 28);
		}
		return lblPuntosGastados;
	}

	private JLabel getLblPuntosSobrantes() {
		if (lblPuntosSobrantes == null) {
			lblPuntosSobrantes = new JLabel("Puntos sobrantes:");
			lblPuntosSobrantes.setForeground(Color.WHITE);
			lblPuntosSobrantes.setFont(new Font("SansSerif", Font.PLAIN, 15));
			lblPuntosSobrantes.setBounds(96, 327, 198, 28);
		}
		return lblPuntosSobrantes;
	}

	// CUADROS DE TEXTO------

	private JLabel getLblCarritoMensaje() {
		if (lblCarritoMensaje == null) {
			lblCarritoMensaje = new JLabel("");
			lblCarritoMensaje.setForeground(Color.WHITE);
			lblCarritoMensaje.setFont(new Font("SansSerif", Font.BOLD, 15));
			lblCarritoMensaje.setBounds(230, 352, 589, 28);
		}
		return lblCarritoMensaje;
	}

	private JLabel getLblCarrito() {
		if (lblCarrito == null) {
			lblCarrito = new JLabel("Carrito");
			lblCarrito.setFont(new Font("SansSerif", Font.BOLD, 30));
			lblCarrito.setForeground(Color.WHITE);
			lblCarrito.setHorizontalAlignment(SwingConstants.CENTER);
			lblCarrito.setBounds(289, 0, 161, 37);
		}
		return lblCarrito;
	}

	private JTextField getTextPuntosSobrantes() { 
		if (textPuntosSobrantes == null) {
			textPuntosSobrantes = new JTextField();
			textPuntosSobrantes.setEditable(false);
			textPuntosSobrantes.setHorizontalAlignment(SwingConstants.CENTER);
			textPuntosSobrantes.setFont(new Font("SansSerif", Font.PLAIN, 15));
			textPuntosSobrantes.setBounds(220, 327, 119, 25);
			textPuntosSobrantes.setColumns(10);
			textPuntosSobrantes.setText(vP.getVentanaDeJuego().getJuego().getPuntos() + "");
		}
		return textPuntosSobrantes;
	}

	private JTextField getTextPuntosTotales() {
		if (textPuntosTotales == null) {
			textPuntosTotales = new JTextField();
			textPuntosTotales.setEditable(false);
			textPuntosTotales.setHorizontalAlignment(SwingConstants.CENTER);
			textPuntosTotales.setFont(new Font("SansSerif", Font.PLAIN, 15));
			textPuntosTotales.setColumns(10);
			textPuntosTotales.setBounds(608, 327, 119, 25);
			textPuntosTotales.setText(pedido.getTotal() + "");
		}
		return textPuntosTotales;
	}

	// MÉTODOS --------

	private void mostrarEnLista() {
		if (pedido.getListaPedido().isEmpty()) {
			getLblCarritoMensaje().setText(textos.getString("nadaAñadido"));
		} else {
			getLblCarritoMensaje().setText("");
		}
		for (Regalo regalo : pedido.getListaPedido()) {
			Object[] nuevaFila = { regalo.getSeccion(), regalo.getDenominacion(), regalo.getPuntos(),
					regalo.getUnidades() };
			d.addRow(nuevaFila);
		}

	}

	private void mostrarMensaje() {
		textos.getString("mensajeFinal");
		int seleccion = JOptionPane.showOptionDialog(null, textos.getString("mensajeFinal"),
				vP.getSubVentanaDePremios().getTituloVentana() + ": " + textos.getString("lblCesta"),
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(VentanaDeIdentificacion.class.getResource("/imgDecorativas/bola.png")),
				new Object[] { textos.getString("finalizar") }, "opcion 1");
		System.out.println("Aplicación finalizada. Pedido finalizado.");
		reiniciarAplicacion(seleccion);	
	}

	private void reiniciarAplicacion(int seleccion) {
		if (seleccion == 0) {
			System.out.println("Reiniciando Aplicación");
			pedido.grabarPedido();
			pedido.inicializar();
			System.out.println("Se ha guardado el pedido");
			vP.getSubVentanaDePremios().dispose();
			vP.dispose();
			vP.getVentanaDeJuego().getVentanaDeIdentificacion().inicializar();
			dispose();
		}
	}

	private boolean preguntar() {
		int seleccion = JOptionPane.showOptionDialog(null, textos.getString("puntosSobrantesMensaje"),
				vP.getSubVentanaDePremios().getTituloVentana() + ": " + textos.getString("lblCesta"),
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { textos.getString("btnCanjear"), "No" }, "opcion 1");
		System.out.println("Aplicación finalizada. Pedido finalizado.");
		if (seleccion==0) {
			return true;
		}
		return false;
	}

	// TABLA --------

	private JTable getTable() {
		if (table == null) {
			table = new JTable(d);
			table.setEnabled(false);
			table.setSelectionBackground(Color.LIGHT_GRAY);
			table.setBorder(new LineBorder(SystemColor.desktop));
			table.setBackground(UIManager.getColor("Button.light"));
			table.setFont(new Font("SansSerif", Font.PLAIN, 12));
			table.setColumnSelectionAllowed(false);
			table.setModel(d);

		}
		return table;
	}

	// PANELES --------

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBackground(Color.LIGHT_GRAY);
			scrollPane.setFont(new Font("SansSerif", Font.PLAIN, 12));
			scrollPane.setBorder(new MatteBorder(1, 1, 3, 4, (Color) new Color(64, 64, 64)));
			scrollPane.setBounds(20, 49, 753, 263);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private void finalizar() {
		if (vP.getVentanaDeJuego().getJuego().getPuntos() != 0) {
			if (preguntar()) {
				mostrarMensaje();
			}
		} else {
			mostrarMensaje();
		}
	}

	public void actualizar() {
		mostrarEnLista();
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setForeground(Color.WHITE);
			separator.setBounds(0, 38, 795, 36);
		}
		return separator;
	}
}
