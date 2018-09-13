package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import events.PinSentEvent;
import events.PinSentListener;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JPasswordField;

public class AskPin extends JFrame implements AskPinInterface {
	private JPanel contentPane;
	private PinSentListener pinSentListener;
	private JLabel lblAdvertencia;
	private JPasswordField pswPin;

	/**
	 * Create the frame.
	 */
	public AskPin() {
		setTitle("Login Area");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblinsertePin = new JLabel("PIN:");
		lblinsertePin.setBounds(20, 69, 95, 29);
		contentPane.add(lblinsertePin);
		
		pswPin = new JPasswordField();
		pswPin.setBounds(81, 73, 222, 20);
		contentPane.add(pswPin);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setBounds(10, 133, 110, 23);
		contentPane.add(btnCancelar);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.setBounds(193, 133, 110, 23);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pwd = new String(pswPin.getPassword());
				pinSentListener.listenPinSentEvent(new PinSentEvent(pwd));
			}
		});
		contentPane.add(btnConfirmar);
		
		lblAdvertencia = new JLabel("Advertencia: datos invalidos. Reintroduzca pin");
		lblAdvertencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdvertencia.setForeground(Color.RED);
		lblAdvertencia.setBounds(10, 11, 293, 47);
		lblAdvertencia.setVisible(false);
		contentPane.add(lblAdvertencia);
		

	}

	@Override
	public void setPinListener(PinSentListener listener) {
		this.pinSentListener = listener;
		
	}

	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
	}

	@Override
	public void mostrarError() {		
		this.lblAdvertencia.setVisible(true);
		
		
	}
}
