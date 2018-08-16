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

public class AskPin extends JFrame implements AskPinInterface {
	private JPanel contentPane;
	private PinSentListener pinSentListener;
	private JLabel lblAdvertencia;

	/**
	 * Create the frame.
	 */
	public AskPin() {
		setTitle("Login Area");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 351, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblinsertePin = new JLabel("PIN:");
		lblinsertePin.setBounds(10, 69, 172, 29);
		contentPane.add(lblinsertePin);
		
		JSpinner spPin = new JSpinner();
		spPin.setBounds(82, 73, 233, 20);
		contentPane.add(spPin);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setBounds(10, 133, 110, 23);
		contentPane.add(btnCancelar);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.setBounds(205, 133, 110, 23);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinSentListener.listenPinSentEvent(new PinSentEvent((int)spPin.getValue()));
			}
		});
		contentPane.add(btnConfirmar);
		
		lblAdvertencia = new JLabel("Advertencia: datos invalidos. Reintroduzca pin");
		lblAdvertencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdvertencia.setForeground(Color.RED);
		lblAdvertencia.setBounds(10, 11, 315, 47);
		lblAdvertencia.setVisible(false);
		contentPane.add(lblAdvertencia);
	}

	@Override
	public void setPinListener(PinSentListener listener) {
		this.pinSentListener = listener;
		
	}

	@Override
	public void mostrar() {
		this.setVisible(true);
		
	}

	@Override
	public void mostrarError() {		
		this.lblAdvertencia.setVisible(true);
		
		
	}

	@Override
	public void ocultar() {
		this.setVisible(false);
		
	}
}
