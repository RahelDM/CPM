package igu;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import log.Regalo;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JCalendar;

public class VentanaObservaciones extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Regalo regalo;
	private JLabel lblObservaciones;
	private JTextArea textObservaciones;
	private JTextField textFecha;
	private JButton btnConfirmarFecha;
	private JButton btnConfirmarTodo;
	private SubVentanaPremios subVentanaPremios;
	@SuppressWarnings("unused")
	private ResourceBundle textos;
	private JLabel lblCalendario;
	private JCalendar calendar;
	private JLabel lblDia;
	private JLabel lblMes;
	private JLabel lblA�o;
	private JButton btnAyuda;

	/**
	 * Constructor de la VentanaObservaciones.
	 * @param regalo
	 * @param subVentanaPremios
	 */
	public VentanaObservaciones(Regalo regalo, SubVentanaPremios subVentanaPremios) {
		setResizable(false);
		this.subVentanaPremios = subVentanaPremios;
		setTitle("Panel de Regalos: Categor\u00EDas: Viajes y Experiencias: Observaciones");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaObservaciones.class.getResource("/imgDecorativas/logo.png")));
		setModal(true);
		this.regalo = regalo;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 684, 518);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		// Calendario --------------
		calendar = new JCalendar();
		calendar.getDayChooser().getDayPanel().setBackground(Color.WHITE);
		calendar.setMinSelectableDate(Calendar.getInstance().getTime());
		calendar.setWeekOfYearVisible(false);
		calendar.setBounds(183, 124, 286, 197);
		contentPanel.add(calendar);
		// ----------------
		contentPanel.add(getLblObservaciones());
		contentPanel.add(getTextObservaciones());
		contentPanel.add(getBtnConfirmarFecha());
		contentPanel.add(getTextFecha());
		contentPanel.add(getBtnConfirmarTodo());
		contentPanel.add(getLblCalendario());
		contentPanel.add(getLblDia());
		contentPanel.add(getLblMes());
		contentPanel.add(getLblA�o());
		contentPanel.add(getBtnAyuda());
		getRootPane().setDefaultButton(getBtnConfirmarTodo());
		localizar();
		cargaAyuda();
	}

	
	/**
	 * M�todo para cargar la ayuda.
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
		hb.enableHelpKey(getRootPane(), "observaciones", hs);
		hb.enableHelpOnButton(getBtnAyuda(), "observaciones", hs);

	}

	/**
	 * M�todo para la localizaci�n.
	 */
	private void localizar() { // Traducci�n
		ResourceBundle textos = subVentanaPremios.getVentanaPremios().getVentanaDeJuego().getVentanaDeIdentificacion()
				.getTextos();
		this.textos = textos;
		this.calendar.setLocale(
				subVentanaPremios.getVentanaPremios().getVentanaDeJuego().getVentanaDeIdentificacion().getLocale());
		traductor(textos);
	}


	/**
	 * M�todo de traducci�n.
	 * 
	 * @param textos
	 */
	private void traductor(ResourceBundle textos) {
		// ToolTipTexts:
		getBtnConfirmarFecha().setToolTipText(textos.getString("toolTipConfirmarFecha"));
		getBtnConfirmarTodo().setToolTipText(textos.getString("toolTipConfirmarTodo"));
		// Etiquetas:
		getLblObservaciones().setText(textos.getString("lblObservaciones"));
		getLblCalendario().setText(textos.getString("lblCalendario"));
		getLblDia().setText(textos.getString("lblDia"));
		getLblA�o().setText(textos.getString("lblA�o"));
		getLblMes().setText(textos.getString("lblMes"));

		// Botones
		getBtnConfirmarFecha().setText(textos.getString("btnConfirmarFecha"));
		getBtnConfirmarTodo().setText(textos.getString("btnConfirmarTodo"));
		// T�tulo
		setTitle(textos.getString("tituloVentanaObservaciones"));
		// Mnemonic
		getLblObservaciones().setDisplayedMnemonic(textos.getString("mneObservaciones").charAt(0));
		getLblDia().setDisplayedMnemonic(textos.getString("mneDia").charAt(0));
		getLblA�o().setDisplayedMnemonic(textos.getString("mneA�o").charAt(0));

	}

	private JLabel getLblObservaciones() {
		if (lblObservaciones == null) {
			lblObservaciones = new JLabel("Introduzca aqu\u00ED sus observaciones:");
			lblObservaciones.setLabelFor(getTextObservaciones());
			lblObservaciones.setForeground(Color.WHITE);
			lblObservaciones.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblObservaciones.setBounds(10, 10, 650, 13);
		}
		return lblObservaciones;
	}

	private JTextArea getTextObservaciones() {
		if (textObservaciones == null) {
			textObservaciones = new JTextArea();
			textObservaciones.setBorder(new LineBorder(new Color(227, 128, 11), 3));
			textObservaciones.setWrapStyleWord(true);
			textObservaciones.setLineWrap(true);
			textObservaciones.setFont(new Font("Arial", Font.PLAIN, 12));
			textObservaciones.setBackground(Color.WHITE);
			textObservaciones.setBounds(10, 33, 650, 58);
		}
		return textObservaciones;
	}

	private JTextField getTextFecha() {
		if (textFecha == null) {
			textFecha = new JTextField();
			textFecha.setFont(new Font("Arial", Font.PLAIN, 15));
			textFecha.setEditable(false);
			textFecha.setBounds(286, 382, 90, 25);
			textFecha.setColumns(10);
		}
		return textFecha;
	}

	private JButton getBtnConfirmarFecha() {
		if (btnConfirmarFecha == null) {
			btnConfirmarFecha = new JButton("Confirmar Fecha");
			btnConfirmarFecha.setMnemonic('o');
			btnConfirmarFecha.setBackground(Color.WHITE);
			btnConfirmarFecha.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					a�adirFecha();
				}
			});
			btnConfirmarFecha.setFont(new Font("Arial", Font.PLAIN, 15));
			btnConfirmarFecha.setBounds(242, 334, 175, 27);
		}
		return btnConfirmarFecha;
	}

	private void a�adirFecha() {
		getTextFecha().setText(calendar.getCalendar().get(Calendar.DAY_OF_MONTH) + "/"
				+ calendar.getCalendar().get(Calendar.MONTH) + 1 + "/" + calendar.getCalendar().get(Calendar.YEAR));
		comprobarBotones();
		//Consola
		System.out.println("Fecha seleccionada: " + getTextFecha().getText());
	}


	private JButton getBtnConfirmarTodo() {
		if (btnConfirmarTodo == null) {
			btnConfirmarTodo = new JButton("Confirmar y volver a la selecci\u00F3n");
			btnConfirmarTodo.setMnemonic('c');
			btnConfirmarTodo.setBackground(new Color(0, 100, 0));
			btnConfirmarTodo.setForeground(Color.WHITE);
			btnConfirmarTodo.setEnabled(false);
			btnConfirmarTodo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarObservaciones();
				}
			});
			btnConfirmarTodo.setFont(new Font("Arial", Font.PLAIN, 15));
			btnConfirmarTodo.setBounds(199, 417, 270, 40);
		}
		return btnConfirmarTodo;
	}


	private void actualizarObservaciones() {
		regalo.setObservaciones(getTextObservaciones().getText());
		regalo.setFecha(getTextFecha().getText());
		dispose();
		System.out.println("Confirmadas las obsrevaciones.");

	}


	private void comprobarBotones() {
		if (getTextFecha().getText().isEmpty()) {
			getBtnConfirmarTodo().setEnabled(false);
		} else {
			getBtnConfirmarTodo().setEnabled(true);
		}

	}
	
	private JLabel getLblCalendario() {
		if (lblCalendario == null) {
			lblCalendario = new JLabel("New label");
			lblCalendario.setDisplayedMnemonic('s');
			lblCalendario.setLabelFor(calendar);
			lblCalendario.setForeground(Color.WHITE);
			lblCalendario.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblCalendario.setBounds(10, 101, 650, 13);
		}
		return lblCalendario;
	}

	private JLabel getLblDia() {
		if (lblDia == null) {
			lblDia = new JLabel("D\u00EDa");
			lblDia.setLabelFor(calendar.getDayChooser());
			lblDia.setForeground(Color.WHITE);
			lblDia.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblDia.setDisplayedMnemonic('D');
			lblDia.setBounds(99, 169, 54, 27);
		}
		return lblDia;
	}

	private JLabel getLblMes() {
		if (lblMes == null) {
			lblMes = new JLabel("Mes");
			lblMes.setLabelFor(calendar.getMonthChooser());
			lblMes.setForeground(Color.WHITE);
			lblMes.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblMes.setDisplayedMnemonic('M');
			lblMes.setBounds(99, 211, 54, 27);
		}
		return lblMes;
	}

	private JLabel getLblA�o() {
		if (lblA�o == null) {
			lblA�o = new JLabel("A\u00F1o");
			lblA�o.setLabelFor(calendar.getYearChooser());
			lblA�o.setForeground(Color.WHITE);
			lblA�o.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblA�o.setDisplayedMnemonic('A');
			lblA�o.setBounds(99, 248, 54, 27);
		}
		return lblA�o;
	}

	private JButton getBtnAyuda() {
		if (btnAyuda == null) {
			btnAyuda = new JButton("?");
			btnAyuda.setToolTipText((String) null);
			btnAyuda.setFont(new Font("SansSerif", Font.PLAIN, 30));
			btnAyuda.setBorder(new LineBorder(new Color(204, 204, 204), 2, true));
			btnAyuda.setBackground(Color.WHITE);
			btnAyuda.setBounds(10, 417, 44, 40);
		}
		return btnAyuda;
	}
}
