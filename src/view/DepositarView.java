package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import events.DepositRequestEvent;
import events.DepositRequestListener;
import events.ExtractionRequestEventListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.awt.event.ActionEvent;

public class DepositarView extends JFrame implements DepositarInterface {

	private JPanel contentPane;
	private DepositRequestListener depositRequestListener;
	

	/**
	 * Create the frame.
	 */
	public DepositarView() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 235, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblValor = new JLabel("Valor del billete");
		lblValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblValor.setBounds(10, 11, 203, 14);
		contentPane.add(lblValor);
		
		JSpinner spValor = new JSpinner();
		spValor.setBounds(10, 36, 199, 20);
		contentPane.add(spValor);
		
		JSpinner spCantidad = new JSpinner();
		spCantidad.setBounds(10, 102, 199, 20);
		contentPane.add(spCantidad);
		
		JButton btnDepositar = new JButton("DEPOSITAR");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigInteger valorBillete = BigInteger.valueOf((int)spValor.getValue());
				BigInteger cantidadBillete = BigInteger.valueOf((int)spCantidad.getValue());
				depositRequestListener.listenDepositRequestEvent(new DepositRequestEvent(valorBillete, cantidadBillete));
			}
		});
		btnDepositar.setBounds(10, 151, 203, 23);
		contentPane.add(btnDepositar);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 77, 203, 14);
		contentPane.add(lblCantidad);
		

	}

	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
	}

	@Override
	public void setDepositRequestListener(DepositRequestListener listener) {
		this.depositRequestListener = listener;
		
	}
}
