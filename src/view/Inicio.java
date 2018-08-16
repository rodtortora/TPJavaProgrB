package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import events.CardReadedEvent;
import events.CardReadedListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.JSpinner;

public class Inicio extends JFrame implements ViewInicioInterface {

	private JPanel contentPane;
	private JLabel lblInserteNroDe;
	private CardReadedListener cardReadedListener;

	/**
	 * Launch the application.
	 */
	public void inicializar() {
		setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		setTitle("Autenticaci\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntroduzcaNroDe = new JLabel("Bienvenido"); //TODO Sustituir de acuerdo al banco
		lblIntroduzcaNroDe.setBounds(10, 11, 404, 64);
		lblIntroduzcaNroDe.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblIntroduzcaNroDe);
		
		lblInserteNroDe = new JLabel("Inserte nro de tarjeta");
		lblInserteNroDe.setBounds(10, 109, 197, 14);
		contentPane.add(lblInserteNroDe);
		JSpinner spNroTarjeta = new JSpinner();
		spNroTarjeta.setBounds(10, 142, 404, 20);
		contentPane.add(spNroTarjeta);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigInteger nroTarjeta = BigInteger.valueOf((int)spNroTarjeta.getValue());
				cardReadedListener.listenCardReadedEvent(new CardReadedEvent(nroTarjeta));
				ocultar();
			}
		});
		btnConfirmar.setBounds(117, 212, 202, 23);
		contentPane.add(btnConfirmar);		
	}

	@Override
	public void setCardReadedListener(CardReadedListener listener) {
		this.cardReadedListener = listener;
		
	}

	@Override
	public void ocultar() {
		this.setVisible(false);
		
	}
}
