package igu;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Graphics;

import log.Juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;



import java.awt.ComponentOrientation;
import javax.swing.JSeparator;
import java.awt.Toolkit;


public class VentanaDeJuego extends JFrame {

	// ATRIBUTOS
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private VentanaDeIdentificacion ventanaDeIdentificacion;
	private JPanel panelCentro;
	private JLabel lblCasillasRestantes;
	private JLabel lblCasillas;
	private JLabel lblPuntosGanador;
	private JLabel lblPuntos;
	private Juego juego;
	private JButton btnCanjear;
	private JButton btnAyuda;
	private EventActionBotones aE;
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelOeste;
	private JPanel panelEste;
	private JLabel lblPanel;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_1_1;
	private JSeparator separator_2_1;
	private JSeparator separator_2_1_1;

	/**
	 * Constructor de la VentanaDeJuego
	 * 
	 * @param ventanaDeIdentificacion
	 */
	public VentanaDeJuego(VentanaDeIdentificacion ventanaDeIdentificacion) {
		setTitle("Panel de Regalos: Juego");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaDeJuego.class.getResource("/imgDecorativas/logo.png")));
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				asociaImagenBotones();
			}
		});
		aE = new EventActionBotones();
		setMinimumSize(new Dimension(550, 300));
		this.ventanaDeIdentificacion = ventanaDeIdentificacion;
		juego = new Juego(ventanaDeIdentificacion.getID());
		setBounds(100, 100, 1050, 750);
		panelPrincipal = new Fondo();
		panelPrincipal.setBackground(new Color(0, 0, 0));
		panelPrincipal.setBorder(new LineBorder(Color.DARK_GRAY, 4, true));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		panelPrincipal.add(getPanelNorte(), BorderLayout.NORTH);
		panelPrincipal.add(getPanelSur(), BorderLayout.SOUTH);
		panelPrincipal.add(getPanelCentro(), BorderLayout.CENTER);
		panelPrincipal.add(getPanelOeste(), BorderLayout.WEST);
		panelPrincipal.add(getPanelEste(), BorderLayout.EAST);
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
		hb.enableHelpKey(getRootPane(), "juego", hs);
		hb.enableHelpOnButton(getBtnAyuda(), "juego", hs);

	}
	

	/**
	 * Método para la localización.
	 */
	private void localizar() { //Traducción
		ResourceBundle textos = ventanaDeIdentificacion.getTextos();
		traductor(textos);
	}
	
	/**
	 * Método de traducción.
	 * 
	 * @param textos
	 */
	private void traductor(ResourceBundle textos) {
		// ToolTipTexts:
		getBtnCanjear().setToolTipText(textos.getString("tooltipCanjear"));
		getBtnAyuda().setToolTipText((textos.getString("tooltipAyuda")));
		// Etiquetas:
		getLblCasillasRestantes().setText(textos.getString("lblCasillasRestantes"));
		getLblPuntosGanador().setText(textos.getString("lblPuntosGanados"));
		// Título:
		getLblPanelRegalos().setText(textos.getString("panelRegalosJuego"));
		// Nombre ventana:
		setTitle(textos.getString("tituloVentanaDeJuego"));
		//Botones
		getBtnCanjear().setText(textos.getString("btnCanjear"));
		//Mnemonic
		btnCanjear.setMnemonic(textos.getString("mneCanjear").charAt(0));

	}

	// PANELES -------
	private JPanel getPanelCentro() { 
		if (panelCentro == null) {
			panelCentro = new Fondo();
			panelCentro.setBackground(new Color(0, 0, 0));
			panelCentro.setBounds(134, 91, 700, 520);
			panelCentro.setLayout(new GridLayout(5, 5, 0, 0));
			crearBotones();
		}
		return panelCentro;
	}

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new Fondo();
			panelNorte.setBackground(new Color(0, 0, 0));
			FlowLayout flowLayout = (FlowLayout) panelNorte.getLayout();
			flowLayout.setVgap(30);
			panelNorte.add(getLblPanelRegalos());
		}
		return panelNorte;
	}

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new Fondo();
			panelSur.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			panelSur.setBorder(new LineBorder(Color.DARK_GRAY, 3, true));
			panelSur.setBackground(new Color(0, 0, 0));
			panelSur.setLayout(new BoxLayout(panelSur, BoxLayout.X_AXIS));
			panelSur.add(getBtnAyuda());
			panelSur.add(getSeparator());
			panelSur.add(getLblPuntosGanador());
			panelSur.add(getLblPuntos());
			panelSur.add(getSeparator_2());
			panelSur.add(getSeparator_2_1());
			panelSur.add(getBtnCanjear());
			panelSur.add(getSeparator_2_1_1());
			panelSur.add(getSeparator_1());
			panelSur.add(getLblCasillasRestantes());
			panelSur.add(getLblCasillas());
			panelSur.add(getSeparator_1_1());
		}
		return panelSur;
	}

	private JPanel getPanelEste() {
		if (panelEste == null) {
			panelEste = new Fondo();
			panelEste.setBackground(new Color(0, 0, 0));
			FlowLayout flowLayout = (FlowLayout) panelEste.getLayout();
			flowLayout.setHgap(60);
		}
		return panelEste;
	}

	private JPanel getPanelOeste() {
		if (panelOeste == null) {
			panelOeste = new Fondo();
			panelOeste.setBackground(new Color(0, 0, 0));
			FlowLayout flowLayout = (FlowLayout) panelOeste.getLayout();
			flowLayout.setHgap(60);
		}
		return panelOeste;
	}

	// BOTONES -------

	private JButton getBtnCanjear() { // BOTONES
		if (btnCanjear == null) {
			btnCanjear = new JButton("Canjear Puntos");
			btnCanjear.setForeground(Color.BLACK);
			btnCanjear.setBackground(Color.WHITE);
			btnCanjear.setToolTipText("Canjear Puntos");
			btnCanjear.setBorder(new LineBorder(new Color(227,128,11),2,false));
			btnCanjear.setEnabled(false);
			btnCanjear.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e)  {
					comprobacionCanjear();
				}
			});
			btnCanjear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)  {
					comprobacionCanjear();
				}
			});
			btnCanjear.setFont(new Font("SansSerif", Font.BOLD, 20));
			btnCanjear.setBounds(396, 636, 175, 55);
		}
		return btnCanjear;
	}

	private void comprobacionCanjear() {
		String str= "avisoCanjear";
		if(!btnCanjear.isEnabled()) {
			JOptionPane.showMessageDialog(null, ventanaDeIdentificacion.getTextos().getString(str),
					ventanaDeIdentificacion.getTextos().getString("tituloVentanaDeJuego"), JOptionPane.INFORMATION_MESSAGE);
		}else {
			mostrarVentanaPremios();
		}
	}

	private void mostrarVentanaPremios() {
		VentanaPremios frame = new VentanaPremios(this);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); // Hace que se centre en la pantalla.
		dispose();
	}

	private JButton getBtnAyuda() {
		if (btnAyuda == null) {
			btnAyuda = new JButton("  ?  ");
			btnAyuda.setBorder(new LineBorder(new Color(204, 204, 204), 2, true));
			btnAyuda.setFont(new Font("SansSerif", Font.PLAIN, 30));
			btnAyuda.setToolTipText("Ayuda");
			btnAyuda.setBackground(new Color(255, 255, 255));
			btnAyuda.setFont(new Font("SansSerif", Font.PLAIN, 20));
		}
		return btnAyuda;
	}

	// ETIQUETAS -------

	private JLabel getLblCasillasRestantes() { 
		if (lblCasillasRestantes == null) {
			lblCasillasRestantes = new JLabel("    N\u00FAmero de casillas restantes:");
			lblCasillasRestantes.setForeground(new Color(255, 255, 255));
			lblCasillasRestantes.setFont(new Font("SansSerif", Font.BOLD, 20));
		}
		return lblCasillasRestantes;
	}

	private JLabel getLblCasillas() {
		if (lblCasillas == null) {
			lblCasillas = new JLabel("3");
			lblCasillas.setForeground(new Color(255, 255, 255));
			lblCasillas.setFont(new Font("SansSerif", Font.BOLD, 20));
		}
		return lblCasillas;
	}

	private JLabel getLblPuntosGanador() {
		if (lblPuntosGanador == null) {
			lblPuntosGanador = new JLabel("Puntos ganados: ");
			lblPuntosGanador.setForeground(new Color(255, 255, 255));
			lblPuntosGanador.setFont(new Font("SansSerif", Font.BOLD, 20));
			lblPuntosGanador.setBounds(887, 396, 149, 13);
		}
		return lblPuntosGanador;
	}

	private JLabel getLblPuntos() {
		if (lblPuntos == null) {
			lblPuntos = new JLabel("0");
			lblPuntos.setForeground(new Color(255, 255, 255));
			lblPuntos.setFont(new Font("SansSerif", Font.BOLD, 20));
			lblPuntos.setBounds(917, 419, 129, 55);
		}
		return lblPuntos;
	}

	private JLabel getLblPanelRegalos() {
		if (lblPanel == null) {
			lblPanel = new JLabel("Panel  Regalos");
			lblPanel.setForeground(Color.WHITE);
			lblPanel.setFont(new Font("SansSerif", Font.BOLD, 50));
		}
		return lblPanel;
	}

	/**
	 * Método para la creación de los 25 botones necesarios para el panel.
	 * 
	 * @param posición en la que se encuentra el botón.
	 * 
	 * @return el botón creado.
	 */
	private JButton nuevoBoton(Integer posicion) { //MÉTODOS
		JButton boton = new JButton("");
		boton.setBackground(Color.WHITE);
		boton.setActionCommand(String.valueOf(posicion));
		boton.setBorder(new LineBorder(Color.DARK_GRAY, 3, false));
		boton.addActionListener(aE);
		return boton;
	}

	private void crearBotones() {
		getPanelCentro().removeAll();
		for (int i = 0; i < 25; i++) {
			panelCentro.add(nuevoBoton(i));
		}
	}

	/**
	 * Método de selección de casillas.
	 * 
	 * @param i
	 */
	private void seleccionar(int i) {
		juego.selecciona(i); // Actualizar lógica.
		representaJuego(i); // Actualizar interfaz.
	}

	private void representaJuego(int i) {
		actualizarPuntos();
		actualizarIntentos();
		pintarCasilla(i);
		getPanelCentro().getComponents()[i].setEnabled(false);
		mensaje(i);
		comprobarFin(i);
	}

	/**
	 * Método para actualizar los puntos.
	 */
	private void actualizarPuntos() {
		getLblPuntos().setText(Integer.toString(juego.getPuntos()));
	}

	/**
	 * Método para actualizar el número de intentos de selección de casillas.
	 */
	private void actualizarIntentos() {
		getLblCasillas().setText(Integer.toString(juego.getIntentos()));
	}
	
	private void pintarCasilla(int i) {
		ImageIcon imagen = ImagenFactoria.getImagen(juego.getTablero().getCasillas()[i]);
		((JButton) getPanelCentro().getComponent(i)).setIcon(imagen);
		((JButton) getPanelCentro().getComponent(i)).setDisabledIcon(imagen);
		((JButton) getPanelCentro().getComponent(i)).setBorder(new LineBorder(new Color(227,128,11), 4));

	}

	private void mensaje(int i) {
		String str1="bonus"; //¡Has encontrado una casilla que te permite seguir jugando!\n¡Suerte!
		String str2="porDos";// ¡Has encontrado un x2!\nEsta casilla multiplica tus puntos actuales por dos.\n ¡Si tus puntos eran 0 sigues teniendo 0!
		if (juego.getTablero().getCasillas()[i].tieneBonus()) {
			JOptionPane.showMessageDialog(null, ventanaDeIdentificacion.getTextos().getString(str1), ventanaDeIdentificacion.getTextos().getString("tituloVentanaDeJuego"), JOptionPane.INFORMATION_MESSAGE);
			System.out.println("¡Conseguido un bonus!");
		} else if (juego.getTablero().getCasillas()[i].duplicaPuntos()) {
			JOptionPane.showMessageDialog(null, ventanaDeIdentificacion.getTextos().getString(str2), ventanaDeIdentificacion.getTextos().getString("tituloVentanaDeJuego"), JOptionPane.INFORMATION_MESSAGE);
			System.out.println("¡Puntos multiplicados por dos!");
		}
	}

	private void comprobarFin(int i) {
		String str1="finalDePartida"; //¡Partida finalizada!\n Puntos ganados: 
		String str2="noHayPuntos"; //"¡Lo sentimos!\n No has conseguido ningún punto." + "\n¡Muchas gracias por jugar!"
		String str3= "fin"; //Fin
		if (juego.isPartidaFinalizada()) {
			desabilitarPanel(false);
			JOptionPane.showMessageDialog(null, ventanaDeIdentificacion.getTextos().getString(str1) + " "+juego.getPuntos(),
					ventanaDeIdentificacion.getTextos().getString(str3),
					JOptionPane.INFORMATION_MESSAGE);
			pintarTodasLasCasillas();
			getBtnCanjear().setEnabled(true);	
			System.out.println("Juego Finalizado");
			}
		if (juego.getPuntos() == 0 && juego.getIntentos() == 0) {
			JOptionPane.showMessageDialog(null,
					ventanaDeIdentificacion.getTextos().getString(str2), ventanaDeIdentificacion.getTextos().getString(str3),
					JOptionPane.INFORMATION_MESSAGE);
			reiniciar();
		}
	}

	private void pintarTodasLasCasillas() {
		for (int i = 0; i < juego.getDim(); i++) {
			ImageIcon imagen = ImagenFactoria.getImagen(juego.getTablero().getCasillas()[i]);
			((JButton) getPanelCentro().getComponent(i)).setIcon(imagen);
			((JButton) getPanelCentro().getComponent(i)).setDisabledIcon(imagen);
		}
	}

	private void desabilitarPanel(boolean v) {
		for (int i = 0; i < juego.getDim(); i++) {
			getPanelCentro().getComponents()[i].setEnabled(v);
		}
	}

	/**
	 * Método para adaptar las imagenes del panel adecuadamente para su 
	 * redimensión.
	 * @param boton
	 * @param rutaImagen
	 */
	private void setImagenAdaptada(JButton boton, String rutaImagen) {
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_FAST);
		ImageIcon icon = new ImageIcon(imgEscalada);
		boton.setIcon(icon);
	}

	private void asociaImagenBotones() {
		for (int i = 0; i < panelCentro.getComponents().length; i++) {
			JButton boton = (JButton) (panelCentro.getComponents()[i]);
			setImagenAdaptada(boton, "/imgCasillas/" + "sobre" + ".png");
		}
	}

	public void reiniciar() {
		System.out.println("Volviendo a la ventana inicial.");
		setEnabled(false);
		dispose();
		ventanaDeIdentificacion.inicializar();
	}

	public VentanaDeIdentificacion getVentanaDeIdentificacion() {
		return ventanaDeIdentificacion;
	}

	public Juego getJuego() {
		return juego;
	}

	class EventActionBotones implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int posicion = Integer.parseInt(((AbstractButton) e.getSource()).getActionCommand());
			seleccionar(posicion);
		}
	}

	class Fondo extends JPanel {
		private static final long serialVersionUID = 1L;
		@SuppressWarnings("unused")
		private Image imagen;

		@Override
		public void paint(Graphics g) {
			Image imagen = new ImageIcon(getClass().getResource("/imgDecorativas/fondo.png")).getImage();

			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

			setOpaque(false);

			super.paint(g);
		}
	}

	private JSeparator getSeparator() { //SEPARADORES
		if (separator == null) {
			separator = new JSeparator();
			separator.setBackground(Color.BLACK);
			separator.setForeground(Color.BLACK);
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setForeground(Color.BLACK);
			separator_1.setBackground(Color.BLACK);
		}
		return separator_1;
	}
	private JSeparator getSeparator_2() {
		if (separator_2 == null) {
			separator_2 = new JSeparator();
			separator_2.setForeground(Color.BLACK);
			separator_2.setBackground(Color.BLACK);
		}
		return separator_2;
	}
	private JSeparator getSeparator_1_1() {
		if (separator_1_1 == null) {
			separator_1_1 = new JSeparator();
			separator_1_1.setForeground(Color.BLACK);
			separator_1_1.setBackground(Color.BLACK);
		}
		return separator_1_1;
	}
	private JSeparator getSeparator_2_1() {
		if (separator_2_1 == null) {
			separator_2_1 = new JSeparator();
			separator_2_1.setForeground(Color.BLACK);
			separator_2_1.setBackground(Color.BLACK);
		}
		return separator_2_1;
	}
	private JSeparator getSeparator_2_1_1() {
		if (separator_2_1_1 == null) {
			separator_2_1_1 = new JSeparator();
			separator_2_1_1.setForeground(Color.BLACK);
			separator_2_1_1.setBackground(Color.BLACK);
		}
		return separator_2_1_1;
	}
}
