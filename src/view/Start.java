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

public class LectorTarjetaView extends Ventana implements LectorTarjetaInterface  {
	
	private CardReadedListener cardReadedListener;

	public LectorTarjetaView(String title, String subtitle, String message) {
		super(title, subtitle, message);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigInteger nroTarjeta = BigInteger.valueOf((int)spNroTarjeta.getValue());
				cardReadedListener.listenCardReadedEvent(new CardReadedEvent(nroTarjeta));
			}
		});
		btnConfirmar.setBounds(10, 440, 974, 60);
		getContentPane().add(btnConfirmar);
		
		JSpinner spNroTarjeta = new JSpinner();
		spNroTarjeta.setBounds(370, 267, 260, 20);
		getContentPane().add(spNroTarjeta);

	}
	
	@Override
	public void setCardReadedListener(CardReadedListener listener) {
		this.cardReadedListener = listener;
		
	}

	@Override
	public void editarTexto(String string) {
		this.lblTexto.setText(string);
		
	}
	
	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
	}
}
