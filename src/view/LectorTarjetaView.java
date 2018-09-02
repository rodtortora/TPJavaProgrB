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

public class LectorTarjetaView extends JFrame implements LectorTarjetaInterface {

	private JPanel contentPane;
	private JLabel lblInserteCard;
	private CardReadedListener cardReadedListener;
	JLabel lblTexto = new JLabel(""); //TODO Sustituir de acuerdo al banco

	/**
	 * Launch the application.
	 */
	public void mostrar() {
		setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public LectorTarjetaView() {
		setTitle("Autenticaci\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblTexto.setBounds(10, 11, 404, 64);
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTexto);
		
		lblInserteCard = new JLabel("Inserte nro de tarjeta");
		lblInserteCard.setBounds(10, 109, 197, 14);
		contentPane.add(lblInserteCard);
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

	@Override
	public void editarTexto(String string) {
		this.lblTexto.setText(string);
		
	}
}
