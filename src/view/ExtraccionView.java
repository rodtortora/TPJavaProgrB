package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import events.ExtractionRequestEvent;
import events.ExtractionRequestEventListener;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.awt.event.ActionEvent;

public class ExtraccionView extends JFrame implements ExtraccionInterface {

	private JPanel contentPane;
	private ExtractionRequestEventListener extractionRequestEventListener;

	/**
	 * Create the frame.
	 */
	public ExtraccionView() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCantidadAExtraer = new JLabel("Cantidad a extraer");
		lblCantidadAExtraer.setBounds(10, 38, 110, 14);
		contentPane.add(lblCantidadAExtraer);
		
		JSpinner spnMoneyAmount = new JSpinner();
		spnMoneyAmount.setBounds(130, 35, 294, 20);
		contentPane.add(spnMoneyAmount);
		
		JButton btnConfirmExtraction = new JButton("CONFIRMAR");
		btnConfirmExtraction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigInteger moneyAmount = BigInteger.valueOf((int)spnMoneyAmount.getValue());
				extractionRequestEventListener.listenExtractionRequestEvent(new ExtractionRequestEvent(moneyAmount));
			}
		});
		btnConfirmExtraction.setBounds(10, 115, 414, 23);
		contentPane.add(btnConfirmExtraction);
	}

	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
	}

	@Override
	public void setExtractionRequestEventListener(ExtractionRequestEventListener listener) {
		this.extractionRequestEventListener = listener;
		
	}
}
