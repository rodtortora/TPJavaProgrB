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

public class Inicio extends JFrame implements ViewInicioInterface {

	private JPanel contentPane;
	private JLabel lblInserteNroDe;
	private JLabel lblInsertePin;
	private CardReadedListener cardReadedListener;
	private JFormattedTextField pinField;

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
		setBounds(100, 100, 240, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntroduzcaNroDe = new JLabel("Bienvenido al banco");
		lblIntroduzcaNroDe.setBounds(10, 11, 197, 64);
		lblIntroduzcaNroDe.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblIntroduzcaNroDe);
		
		lblInserteNroDe = new JLabel("Inserte nro de tarjeta");
		lblInserteNroDe.setBounds(10, 110, 197, 14);
		contentPane.add(lblInserteNroDe);
		
		lblInsertePin = new JLabel("Inserte PIN");
		lblInsertePin.setBounds(10, 181, 197, 14);
		contentPane.add(lblInsertePin);
		
		pinField = new JFormattedTextField();
		pinField.setText("PIN");
		pinField.setFont(new Font("Tahoma", Font.ITALIC, 10));
		pinField.setBounds(10, 208, 197, 20);
		contentPane.add(pinField);
		
		JFormattedTextField nroTarjetaField = new JFormattedTextField();
		nroTarjetaField.setFont(new Font("Tahoma", Font.ITALIC, 10));
		nroTarjetaField.setText("N\u00FAmero de tarjeta");
		nroTarjetaField.setBounds(10, 135, 197, 20);
		contentPane.add(nroTarjetaField);
		
		JButton btnNewButton = new JButton("CONFIRMAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigInteger nroTarjeta = new BigInteger(nroTarjetaField.getText());
				Integer pin = Integer.parseInt(pinField.getText());
				cardReadedListener.listenCardReadedEvent(new CardReadedEvent(nroTarjeta, pin));								
			}
		});
		btnNewButton.setBounds(10, 280, 197, 23);
		contentPane.add(btnNewButton);
		


		
	}

	@Override
	public void setCardReadedListener(CardReadedListener listener) {
		this.cardReadedListener = listener;
		
	}
}
