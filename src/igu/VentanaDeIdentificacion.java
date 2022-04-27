package igu;

import javax.swing.JFrame;
import javax.swing.JPanel;

import log.Cliente;
import log.Clientela;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.help.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

import javax.swing.SwingConstants;

public class VentanaDeIdentificacion extends JFrame {

	// ATRIBUTOS
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textID;
	private JLabel lblBienvenida;
	private JButton btnContinuar;
	private Clientela jugadores;
	private JLabel lblExplicacion;
	private JLabel lblID;
	private JLabel lblLogo;
	private JButton btnIngles;
	private JButton btnFrances;
	private JLabel lblAvisoDeID;
	private Cliente cliente;
	private JLabel lblImagenTarjeta;
	private JLabel lblPanelRegalos;
	private JLabel lblde;
	private JLabel lblPanelRegalos_1;
	private JLabel lblde_1;
	private JButton btnEspañol;
	private ResourceBundle textos;
	private Locale localizacion;
	private JButton btnAyuda;
	private JLabel lblFondo;

	/**
	 * Constructor de la VentanaIdentificacion
	 * 
	 * @param listaDeJugadores
	 */
	public VentanaDeIdentificacion(Clientela listaDeJugadores) {
		setResizable(false);
		setTitle("Panel de Regalos\r\n");
		setBackground(new Color(0, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaDeIdentificacion.class.getResource("/imgDecorativas/logo.png")));
		// Crear una lista con los jugadores:
		this.jugadores = listaDeJugadores;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 492);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTextID());
		contentPane.add(getLblBienvenida());
		contentPane.add(getBtnContinuar());
		contentPane.add(getLblExplicacion());
		contentPane.add(getLblID());
		contentPane.add(getLblLogo());
		contentPane.add(getBtnIngles());
		contentPane.add(getBtnFrances());
		contentPane.add(getLblAvisoDeID());
		contentPane.add(getLblImagenTarjeta());
		contentPane.add(getLblPanelRegalos());
		contentPane.add(getLblde());
		contentPane.add(getLblPanelRegalos_1());
		contentPane.add(getLblde_1());
		contentPane.add(getBtnEspañol());
		getRootPane().setDefaultButton(getBtnContinuar());
		contentPane.add(getBtnAyuda());
		contentPane.add(getLblFondo());
		Locale localization = Locale.getDefault(Locale.Category.FORMAT);
		localizar(localization);
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
		hb.enableHelpKey(getRootPane(), "presentacion", hs);
		hb.enableHelpOnButton(getBtnAyuda(), "presentacion", hs);

	}

	/**
	 * Método para la localización.
	 */
	private void localizar(Locale localizacion) { // Traducción
		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", localizacion);
		this.textos = textos;
		this.localizacion = localizacion;
		traductor(textos);
	}

	/**
	 * Método de traducción.
	 * 
	 * @param textos
	 */
	private void traductor(ResourceBundle textos) {
		// ToolTipTexts:
		getBtnContinuar().setToolTipText(textos.getString("tooltipContinuar"));
		getBtnFrances().setToolTipText(textos.getString("tooltipFr"));
		getBtnIngles().setToolTipText(textos.getString("tooltipEn"));
		getBtnEspañol().setToolTipText(textos.getString("tooltipEs"));
		// Etiquetas:
		getLblBienvenida().setText(textos.getString("lblBienvenida"));
		getLblExplicacion().setText(textos.getString("lblExplicacion"));
		// Título:
		getLblPanelRegalos().setText(textos.getString("panelRegalos"));
		getLblde().setText(textos.getString("de"));
		// Sombra:
		getLblPanelRegalos_1().setText(textos.getString("panelRegalos"));
		getLblde_1().setText(textos.getString("de"));
		// Nombre ventana:
		setTitle(textos.getString("tituloVentanaIdentificacion"));
		// Botones
		getBtnContinuar().setText(textos.getString("btnContinuar"));
		getBtnEspañol().setText(textos.getString("btnEspañol"));
		getBtnIngles().setText(textos.getString("btnInglés"));
		getBtnFrances().setText(textos.getString("btnFrances"));
		// Mnemonic
		getBtnEspañol().setMnemonic(textos.getString("mneEspañol").charAt(0));
		getBtnIngles().setMnemonic(textos.getString("mneIngles").charAt(0));

	}

	private JButton getBtnEspañol() {
		if (btnEspañol == null) {
			btnEspañol = new JButton("Castellano");
			btnEspañol.setFocusPainted(false);
			btnEspañol.setMnemonic('s');
			btnEspañol.setFont(new Font("SansSerif", Font.PLAIN, 15));
			btnEspañol.setBorder(new LineBorder(Color.DARK_GRAY, 4, true));
			btnEspañol.setBackground(Color.WHITE);
			btnEspañol.setIcon(
					new ImageIcon(VentanaDeIdentificacion.class.getResource("/imgDecorativas/espa\u00F1a.png")));
			btnEspañol.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					localizar(new Locale("es"));
				}
			});
			btnEspañol.setBounds(100, 312, 155, 61);

		}
		return btnEspañol;
	}

	private JButton getBtnIngles() { // BOTONES
		if (btnIngles == null) {
			btnIngles = new JButton("Ingl\u00E9s");
			btnIngles.setFocusPainted(false);
			btnIngles.setMnemonic('I');
			btnIngles.setFont(new Font("SansSerif", Font.PLAIN, 15));
			btnIngles.setBorder(new LineBorder(Color.DARK_GRAY, 4));
			btnIngles.setIcon(
					new ImageIcon(VentanaDeIdentificacion.class.getResource("/imgDecorativas/inglaterra.png")));
			btnIngles.setBackground(Color.WHITE);
			btnIngles.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					localizar(new Locale("en"));
				}
			});
			btnIngles.setBounds(22, 382, 145, 61);
		}
		return btnIngles;
	}

	private JButton getBtnFrances() {
		if (btnFrances == null) {
			btnFrances = new JButton("Franc\u00E9s");
			btnFrances.setFocusPainted(false);
			btnFrances.setFont(new Font("SansSerif", Font.PLAIN, 15));
			btnFrances.setMnemonic('F');
			btnFrances.setBorder(new LineBorder(Color.DARK_GRAY, 4));
			btnFrances.setIcon(new ImageIcon(VentanaDeIdentificacion.class.getResource("/imgDecorativas/francia.png")));
			btnFrances.setBackground(Color.WHITE);
			btnFrances.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					localizar(new Locale("fr"));
				}
			});
			btnFrances.setBounds(173, 382, 163, 61);
		}
		return btnFrances;
	}

	private JButton getBtnContinuar() {
		if (btnContinuar == null) {
			btnContinuar = new JButton("Continuar");
			btnContinuar.setForeground(new Color(255, 255, 255));
			btnContinuar.setBackground(SystemColor.activeCaptionText);
			btnContinuar.setFont(new Font("Arial", Font.BOLD, 15));
			btnContinuar.setEnabled(false);
			btnContinuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestionarFallo(validarID());
				}
			});
			btnContinuar.setBounds(290, 267, 145, 39);
		}
		return btnContinuar;
	}

	private JButton getBtnAyuda() {
		if (btnAyuda == null) {
			btnAyuda = new JButton("?");
			btnAyuda.setFocusPainted(false);
			btnAyuda.setToolTipText((String) null);
			btnAyuda.setFont(new Font("SansSerif", Font.PLAIN, 30));
			btnAyuda.setBorder(new LineBorder(new Color(204, 204, 204), 2, true));
			btnAyuda.setBackground(Color.WHITE);
			btnAyuda.setBounds(711, 10, 68, 63);
		}
		return btnAyuda;
	}

	public JTextField getTextID() { // CAMPOS DE TEXTO
		if (textID == null) {
			textID = new JTextField();
			textID.setBorder(new LineBorder(new Color(105, 105, 105), 3, true));
			textID.setFont(new Font("Arial", Font.BOLD, 12));
			textID.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					habilitarContinuar();
				}
			});
			textID.setBounds(240, 211, 253, 32);
			textID.setColumns(10);
		}
		return textID;
	}

	private JLabel getLblExplicacion() {
		if (lblExplicacion == null) {
			lblExplicacion = new JLabel(
					"\u00A1Introduzca su identificador y seleccione \"continuar\" para comenzar a jugar!");
			lblExplicacion.setHorizontalAlignment(SwingConstants.CENTER);
			lblExplicacion.setFont(new Font("SansSerif", Font.BOLD, 15));
			lblExplicacion.setBounds(22, 168, 681, 39);
		}
		return lblExplicacion;
	}

	private JLabel getLblID() {
		if (lblID == null) {
			lblID = new JLabel("ID:");
			lblID.setDisplayedMnemonic('D');
			lblID.setLabelFor(getTextID());
			lblID.setFont(new Font("Arial", Font.BOLD, 15));
			lblID.setBounds(212, 214, 35, 23);
		}
		return lblID;
	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setBorder(new LineBorder(new Color(128, 128, 128), 3, true));
			lblLogo.setIcon(new ImageIcon(VentanaDeIdentificacion.class.getResource("/imgDecorativas/logo.png")));
			lblLogo.setBounds(10, 10, 91, 80);
			lblLogo.setIcon(ajustarImagen("/imgDecorativas/logo.png", lblLogo));

		}
		return lblLogo;
	}

	private JLabel getLblImagenTarjeta() {
		if (lblImagenTarjeta == null) {
			lblImagenTarjeta = new JLabel("");
			lblImagenTarjeta.setBounds(411, 222, 320, 221);
			lblImagenTarjeta.setIcon(ajustarImagen("/imgDecorativas/tarjetaAmazon.png", lblImagenTarjeta));
		}
		return lblImagenTarjeta;
	}

	private JLabel getLblBienvenida() {
		if (lblBienvenida == null) {
			lblBienvenida = new JLabel("\u00A1Bienvenido/a!");
			lblBienvenida.setFont(new Font("Arial", Font.PLAIN, 19));
			lblBienvenida.setBounds(303, 10, 264, 25);
		}
		return lblBienvenida;
	}

	private JLabel getLblAvisoDeID() {
		if (lblAvisoDeID == null) {
			lblAvisoDeID = new JLabel("");
			lblAvisoDeID.setBackground(Color.WHITE);
			lblAvisoDeID.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblAvisoDeID.setForeground(new Color(204, 51, 51));
			lblAvisoDeID.setBounds(161, 145, 485, 13);
		}
		return lblAvisoDeID;
	}

	private JLabel getLblPanelRegalos() {
		if (lblPanelRegalos == null) {
			lblPanelRegalos = new JLabel("Panel");
			lblPanelRegalos.setLocation(214, 10);
			lblPanelRegalos.setSize(517, 100);
			lblPanelRegalos.setForeground(Color.BLACK);
			lblPanelRegalos.setFont(new Font("SansSerif", Font.BOLD, 50));
		}
		return lblPanelRegalos;
	}

	private JLabel getLblde() {
		if (lblde == null) {
			lblde = new JLabel("de");
			lblde.setLocation(340, 30);
			lblde.setSize(163, 100);
			lblde.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 30));
			lblde.setForeground(Color.BLACK);
		}
		return lblde;
	}

	private JLabel getLblPanelRegalos_1() {
		if (lblPanelRegalos_1 == null) {
			lblPanelRegalos_1 = new JLabel("Panel");
			lblPanelRegalos_1.setForeground(new Color(204, 204, 204));
			lblPanelRegalos_1.setFont(new Font("SansSerif", Font.BOLD, 50));
			lblPanelRegalos_1.setBounds(205, 10, 526, 100);
		}
		return lblPanelRegalos_1;
	}

	private JLabel getLblde_1() {
		if (lblde_1 == null) {
			lblde_1 = new JLabel("de");
			lblde_1.setForeground(new Color(204, 204, 204));
			lblde_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 30));
			lblde_1.setBounds(335, 30, 270, 100);
		}
		return lblde_1;
	}

	// MÉTODOS:
	private void habilitarContinuar() {
		String id = getTextID().getText();
		if (!id.isEmpty() && !getTextID().getText().isBlank()) {
			getBtnContinuar().setEnabled(true);
		} else {
			getBtnContinuar().setEnabled(false);
		}
	}

	private boolean validarID() {
		for (Cliente cl : jugadores.getClientes()) {
			if (cl.getID().equals(getTextID().getText().toUpperCase()) && cl.esJugador()) {
				System.out.println("Cliente encontrado.\n Continuar habilitado.");
				cl.yaNoEsJugador();
				this.cliente = cl;
				return true;
			}
		}
		return false;
	}

	private void gestionarFallo(boolean esCorrecto) {
		if (esCorrecto) {
			modificarFichero();
			mostrarVentanaDeJuego();
		} else {
			String str = textos.getString("falloID");
			JOptionPane.showMessageDialog(contentPane, str, null, JOptionPane.ERROR_MESSAGE);
			getLblAvisoDeID().setText(textos.getString("lblAvisoID"));
			System.out.println("Error a la hora de introducir el ID.");

		}
	}

	private void modificarFichero() {
		List<Cliente> listaClientes=new ArrayList<Cliente>();
		for(Cliente cliente: jugadores.getClientes()) {
			listaClientes.add(new Cliente(cliente.getID(),cliente.getNombre(),cliente.getPuedeJugar()));
		}
		jugadores.setListaClientes(listaClientes);
		jugadores.grabarClientes();
		
	}

	public void mostrarVentanaDeJuego() {
		VentanaDeJuego frame = new VentanaDeJuego(this);
		frame.setLocationRelativeTo(null); // Hace que se centre en la pantalla.
		frame.setVisible(true);
		dispose();
	}

	public Icon ajustarImagen(String str, JLabel label) {
		ImageIcon imagen = new ImageIcon(VentanaPremios.class.getResource(str));
		ImageIcon icon = new ImageIcon(
				imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		return icon;
	}

	public String getID() {
		return cliente.getID();
	}

	public Locale getLocale() {
		return localizacion;
	}

	public ResourceBundle getTextos() {
		return textos;
	}

	/**
	 * Método público que inicializa toda la pantalla para que el siguiente cliente
	 * pueda utilizarla.
	 */
	public void inicializar() {
		setVisible(true);
		getBtnContinuar().setEnabled(false);
		getTextID().setText("");
		if (!getLblAvisoDeID().getText().isEmpty()) {
			getLblAvisoDeID().setText("");
		}
	}
	private JLabel getLblFondo() {
		if (lblFondo == null) {
			lblFondo = new JLabel("");
			lblFondo.setIcon(new ImageIcon(VentanaDeIdentificacion.class.getResource("/imgDecorativas/fondoID.png")));
			lblFondo.setBounds(0, 0, 803, 455);
		}
		return lblFondo;
	}
}
