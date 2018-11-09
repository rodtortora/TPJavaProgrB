package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import events.PinSentEvent;
import events.PinSentListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class AskPinView extends JFrame implements AskPinInterface {
	private JPanel contentPane;
	private PinSentListener pinSentListener;
	private JPasswordField pswPin;
	private ATMSelectorInterface atmSelectorInterface;

	/**
	 * Create the frame.
	 */
	public AskPinView() {
		setTitle("Login Area");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 179);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblinsertePin = new JLabel("PIN:");
		lblinsertePin.setBounds(10, 30, 95, 29);
		contentPane.add(lblinsertePin);
		
		pswPin = new JPasswordField();
		pswPin.setBounds(81, 34, 222, 20);
		contentPane.add(pswPin);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pswPin.setText("");
				mostrar(false);
				atmSelectorInterface.mostrar(true);
			}
		});
		btnCancelar.setBounds(10, 103, 110, 23);
		contentPane.add(btnCancelar);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.setBounds(193, 103, 110, 23);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pwd = new String(pswPin.getPassword());
				pinSentListener.listenPinSentEvent(new PinSentEvent(pwd));
				pswPin.setText("");
			}
		});
		contentPane.add(btnConfirmar);
		

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
	public void setAtmSelectorInterface(ATMSelectorInterface atmSelectorInterface) {
		this.atmSelectorInterface = atmSelectorInterface;
	}
}
