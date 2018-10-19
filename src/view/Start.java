package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSpinner;

import events.CardReadedEvent;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.awt.event.ActionEvent;

public class Start extends Ventana {

	public Start(String title, String subtitle, String message) {
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
}
