package igu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import log.Pedido;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class VentanaPremios extends JFrame {

	
	//ATRIBUTOS
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private VentanaDeJuego ventanaDeJuego;
	private JPanel pnSecciones;
	private JButton btnAyuda;
	private JButton btnAlimentacion;
	private JButton btnDeportes;
	private JButton btnElectronica;
	private JButton btnViajes;
	private JLabel lblPuntos;
	private JTextField txtPuntosTotales;
	private SubVentanaPremios ventanaEleccionPremios;
	private JButton btnJuguetes;
	private Pedido pedido;
	private JButton btnTodos;
	private SubVentanaPremios subVentanaPremios;
	private JLabel lblEncabezado;
	private JPanel panelEncabezado;
	private JLabel lblAnimacion;
	
	public enum Seccion {ALIMENTACION, JUGUETES, ELECTRONICA, DEPORTES, VIAJES, TODAS};
	private Seccion seccion;

	/**
	 * Constructor de la VentanaDePremios
	 * 
	 * @param ventanaDeJuego
	 */
	public VentanaPremios(VentanaDeJuego ventanaDeJuego) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPremios.class.getResource("/imgDecorativas/logo.png")));
		setTitle("Panel de Regalos: Categor\u00EDas");
		this.ventanaDeJuego = ventanaDeJuego;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 813, 478);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.BLACK);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		panelPrincipal.add(getLblPuntos());
		panelPrincipal.add(getTxtPuntosTotales());
		panelPrincipal.add(getPnSecciones());
		panelPrincipal.add(getBtnAyuda());
		panelPrincipal.add(getPanelEncabezado());
		pedido = new Pedido(ventanaDeJuego.getJuego().getJugador());
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
		hb.enableHelpKey(getRootPane(), "categorias", hs);
		hb.enableHelpOnButton(getBtnAyuda(), "categorias", hs);

	}
	
	/**
	 * Método para la localización.
	 */
	public void localizar() { // Traducción
		ResourceBundle textos = getVentanaDeJuego().getVentanaDeIdentificacion().getTextos();
		traductor(textos);
	}

	/**
	 * Método de traducción.
	 * 
	 * @param textos
	 */
	private void traductor(ResourceBundle textos) {
		// ToolTipTexts:
		getBtnAlimentacion().setToolTipText(textos.getString("seccionA"));
		getBtnDeportes().setToolTipText(textos.getString("seccionD"));
		getBtnElectronica().setToolTipText(textos.getString("seccionE"));
		getBtnJuguetes().setToolTipText(textos.getString("seccionJ"));
		getBtnTodos().setToolTipText(textos.getString("seccionT"));
		getBtnViajes().setToolTipText(textos.getString("seccionV"));
		getBtnAyuda().setToolTipText(textos.getString("tooltipAyuda"));

		// Etiquetas:
		getLblPuntos().setText(textos.getString("lblPuntos"));
		
		// Botones
		getBtnAlimentacion().setText(textos.getString("seccionA"));
		getBtnDeportes().setText(textos.getString("seccionD"));
		getBtnElectronica().setText(textos.getString("seccionE"));
		getBtnJuguetes().setText(textos.getString("seccionJ"));
		getBtnTodos().setText(textos.getString("seccionT"));
		getBtnViajes().setText(textos.getString("seccionV"));
		
		//Mnemonic
		getBtnAlimentacion().setMnemonic(textos.getString("mneA").charAt(0));
		getBtnDeportes().setMnemonic(textos.getString("mneD").charAt(0));
		getBtnElectronica().setMnemonic(textos.getString("mneE").charAt(0));
		getBtnJuguetes().setMnemonic(textos.getString("mneJ").charAt(0));
		getBtnTodos().setMnemonic(textos.getString("mneT").charAt(0));
		getBtnViajes().setMnemonic(textos.getString("mneV").charAt(0));


	}
	
	//BOTONES ------
	
	private JPanel getPanelEncabezado() {
		if (panelEncabezado == null) {
			panelEncabezado = new JPanel();
			panelEncabezado.setBackground(new Color(19,25,33));
			panelEncabezado.setBounds(0, 0, 799, 78);
			panelEncabezado.add(getLblEncabezado());
		}
		return panelEncabezado;
	}

	//PANELES ------
	private JPanel getPnSecciones() { 
		if (pnSecciones == null) {
			pnSecciones = new JPanel();
			pnSecciones.setBounds(138, 88, 661, 346);
			pnSecciones.setLayout(null);
			pnSecciones.setBorder(new EmptyBorder(5, 5, 5, 5));
			pnSecciones.setBackground(Color.BLACK);
			pnSecciones.add(getBtnAlimentacion());
			pnSecciones.add(getBtnDeportes());
			pnSecciones.add(getBtnElectronica());
			pnSecciones.add(getBtnViajes());
			pnSecciones.add(getBtnJuguetes());
			pnSecciones.add(getBtnTodos());
			pnSecciones.add(getLblAnimacion());
		}
		return pnSecciones;
	}

	//BOTONES ------

	private void mostrarVentanas(char c) {
		subVentanaPremios = crearVentanaDeEleccion();
		if (c == 'A') {
			subVentanaPremios.cambiarPanel("pnAlimentacion",Seccion.ALIMENTACION);
			System.out.println("Entrando en la sección de Alimentación");
		} else if (c == 'D') {
			subVentanaPremios.cambiarPanel("pnDeportes",Seccion.DEPORTES);
			System.out.println("Entrando en la sección de Deportes");
		} else if (c == 'E') {
			subVentanaPremios.cambiarPanel("pnElectronica",Seccion.ELECTRONICA);
			System.out.println("Entrando en la sección de Electrónica");
		} else if (c == 'V') {
			subVentanaPremios.cambiarPanel("pnViajes",Seccion.VIAJES);
			System.out.println("Entrando en la sección de Viajes");
		} else if(c=='J') {
			subVentanaPremios.cambiarPanel("pnJuguetes",Seccion.JUGUETES);
			System.out.println("Entrando en la sección de Juguetes");
		} else {
			subVentanaPremios.cambiarPanel("pnTodos",Seccion.TODAS);
			System.out.println("Entrando en la sección de Todos");
		}
	}

	private SubVentanaPremios crearVentanaDeEleccion() {
		this.ventanaEleccionPremios = new SubVentanaPremios(this,pedido,seccion);
		ventanaEleccionPremios.setVisible(true);
		ventanaEleccionPremios.setLocationRelativeTo(null); // Hace que se centre en la pantalla.
		this.setVisible(false);
		return ventanaEleccionPremios;
	}

	private JButton getBtnAlimentacion() { 
		if (btnAlimentacion == null) {
			btnAlimentacion = new JButton("Alimentaci\u00F3n");
			btnAlimentacion.setBorder(new LineBorder(new Color(227, 128, 11), 3));
			btnAlimentacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanas('A');
				}
			});
			btnAlimentacion.setFont(new Font("SansSerif", Font.PLAIN, 20));
			btnAlimentacion.setBackground(Color.WHITE);
			btnAlimentacion.setBounds(18, 98, 191, 55);
		}
		return btnAlimentacion;
	}

	private JButton getBtnDeportes() {
		if (btnDeportes == null) {
			btnDeportes = new JButton("Deportes");
			btnDeportes.setBorder(new LineBorder(new Color(227, 128, 11), 3));
			btnDeportes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanas('D');
				}
			});
			btnDeportes.setFont(new Font("SansSerif", Font.PLAIN, 20));
			btnDeportes.setBackground(Color.WHITE);
			btnDeportes.setBounds(299, 98, 191, 55);
		}
		return btnDeportes;
	}

	private JButton getBtnElectronica() {
		if (btnElectronica == null) {
			btnElectronica = new JButton("Electr\u00F3nica");
			btnElectronica.setBorder(new LineBorder(new Color(227, 128, 11), 3));
			btnElectronica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanas('E');
				}
			});
			btnElectronica.setFont(new Font("SansSerif", Font.PLAIN, 20));
			btnElectronica.setBackground(Color.WHITE);
			btnElectronica.setBounds(18, 184, 191, 55);
		}
		return btnElectronica;
	}

	private JButton getBtnViajes() {
		if (btnViajes == null) {
			btnViajes = new JButton("Viajes y Experiencias");
			btnViajes.setBorder(new LineBorder(new Color(227, 128, 11), 3));
			btnViajes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanas('V');
				}
			});
			btnViajes.setFont(new Font("SansSerif", Font.PLAIN, 20));
			btnViajes.setBackground(Color.WHITE);
			btnViajes.setBounds(140, 249, 237, 55);
		}
		return btnViajes;
	}

	private JButton getBtnJuguetes() {
		if (btnJuguetes == null) {
			btnJuguetes = new JButton("Juguetes");
			btnJuguetes.setBorder(new LineBorder(new Color(227, 128, 11), 3));
			btnJuguetes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanas('J');
				}
			});
			btnJuguetes.setFont(new Font("SansSerif", Font.PLAIN, 20));
			btnJuguetes.setBackground(Color.WHITE);
			btnJuguetes.setBounds(299, 184, 191, 55);
		}
		return btnJuguetes;
	}
	private JButton getBtnTodos() {
		if (btnTodos == null) {
			btnTodos = new JButton("Todos");
			btnTodos.setBorder(new LineBorder(new Color(227, 128, 11), 3));
			btnTodos.setBackground(Color.WHITE);
			btnTodos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanas('T');
				}
			});
			btnTodos.setFont(new Font("SansSerif", Font.BOLD, 20));
			btnTodos.setBounds(176, 33, 161, 55);
		}
		return btnTodos;
	}
	private JButton getBtnAyuda() {
		if (btnAyuda == null) {
			btnAyuda = new JButton("?");
			btnAyuda.setBorder(new LineBorder(new Color(204, 204, 204), 2, true));
			btnAyuda.setBounds(10, 358, 68, 63);
			btnAyuda.setFont(new Font("SansSerif", Font.PLAIN, 30));
			btnAyuda.setBackground(new Color(255, 255, 255));
		}
		return btnAyuda;
	}
	
	//ETIQUETAS ------

	private JLabel getLblPuntos() { 
		if (lblPuntos == null) {
			lblPuntos = new JLabel("Puntos: ");
			lblPuntos.setForeground(Color.WHITE);
			lblPuntos.setBounds(279, 23, 204, 41);
			lblPuntos.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lblPuntos;
	}

	//CUADROS DE TEXTO ------
	
	private JLabel getLblAnimacion() {
		if (lblAnimacion == null) {
			lblAnimacion = new JLabel("");
			lblAnimacion.setBounds(-43, 20, 704, 298);
			lblAnimacion.setIcon(new ImageIcon(VentanaPremios.class.getResource("/imgDecorativas/movimiento2.gif")));
		}
		return lblAnimacion;
	}

	private JLabel getLblEncabezado() {
		if (lblEncabezado == null) {
			lblEncabezado = new JLabel("");
			lblEncabezado.setBackground(Color.BLACK);
			lblEncabezado.setIcon(new ImageIcon(VentanaPremios.class.getResource("/imgDecorativas/cabecera.png")));
		}
		return lblEncabezado;
	}

	//CUADROS DE TEXTO ------

	public JTextField getTxtPuntosTotales() {
		if (txtPuntosTotales == null) {
			txtPuntosTotales = new JTextField();
			txtPuntosTotales.setBorder(new LineBorder(new Color(227, 128, 11), 3, true));
			txtPuntosTotales.setHorizontalAlignment(SwingConstants.CENTER);
			txtPuntosTotales.setBounds(368, 10, 115, 54);
			txtPuntosTotales.setText(ventanaDeJuego.getJuego().getPuntos() + "");
			txtPuntosTotales.setFont(new Font("Arial", Font.PLAIN, 20));
			txtPuntosTotales.setEditable(false);
		}
		return txtPuntosTotales;
	}

	//MÉTODOS ------

	public VentanaDeJuego getVentanaDeJuego() {
		return ventanaDeJuego;
	}
	public SubVentanaPremios getSubVentanaDePremios() {
		return subVentanaPremios;
	}

	public Seccion getSeccion(Seccion seccion) {
		return seccion;
	}
}
