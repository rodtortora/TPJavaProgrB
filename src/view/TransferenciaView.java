package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import events.ExtractionRequestEvent;
import events.TransferRequestEvent;
import events.TransferRequestListener;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.awt.event.ActionEvent;

public class TransferenciaView extends JFrame implements TransferenciaInterface {

	private JPanel contentPane;
	private TransferRequestListener transferRequestListener;
	private PrincipalMenuInterface principalMenuInterface;

	/**
	 * Create the frame.
	 */
	public TransferenciaView() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 203);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCbuDestino = new JLabel("CBU cuenta destino");
		lblCbuDestino.setBounds(10, 11, 186, 14);
		contentPane.add(lblCbuDestino);
		
		JSpinner spDestinyAcc = new JSpinner();
		spDestinyAcc.setBounds(207, 8, 217, 20);
		contentPane.add(spDestinyAcc);
		
		JSpinner spMoneyAmount = new JSpinner();
		spMoneyAmount.setBounds(207, 52, 217, 20);
		contentPane.add(spMoneyAmount);
		
		JLabel lblCantidadDeDinero = new JLabel("Cantidad de dinero");
		lblCantidadDeDinero.setBounds(10, 55, 186, 14);
		contentPane.add(lblCantidadDeDinero);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal moneyAmount = BigDecimal.valueOf((int)spMoneyAmount.getValue());
				BigInteger cbuDestino = BigInteger.valueOf((int)spDestinyAcc.getValue());
				transferRequestListener.listenTransferRequestEvent(new TransferRequestEvent(moneyAmount, cbuDestino));
			}
		});
		btnConfirmar.setBounds(10, 96, 414, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnVolverAlMenu = new JButton("VOLVER AL MENU");
		btnVolverAlMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principalMenuInterface.mostrar(true);
				mostrar(false);
			}
		});
		btnVolverAlMenu.setBounds(10, 130, 414, 23);
		contentPane.add(btnVolverAlMenu);
	}
	
	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
	}

	@Override
	public void setTransferRequestListener(TransferRequestListener listener) {
		this.transferRequestListener = listener;
		
	}
	
	@Override
	public void setPrincipalMenuInterface(PrincipalMenuInterface principalMenuInterface) {
		this.principalMenuInterface = principalMenuInterface;
		
	}

}
