package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import events.CardReadedEvent;
import events.CardReadedListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class LectorTarjetaView extends JFrame implements LectorTarjetaInterface {

	private JPanel contentPane;
	private JLabel lblInserteCard;
	private CardReadedListener cardReadedListener;
	JLabel lblTexto = new JLabel(""); //TODO Sustituir de acuerdo al banco

	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
	}

	/**
	 * Create the frame.
	 */
	public LectorTarjetaView(ATMSelectorInterface atmInterface) {
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
			}
		});
		btnConfirmar.setBounds(212, 212, 202, 23);
		contentPane.add(btnConfirmar);		
		
		JButton btnCancel = new JButton("CANCELAR");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrar(false);
				atmInterface.mostrar(true);
			}
		});
		btnCancel.setBounds(5, 212, 202, 23);
		contentPane.add(btnCancel);
	}

	@Override
	public void setCardReadedListener(CardReadedListener listener) {
		this.cardReadedListener = listener;
		
	}

	@Override
	public void editarTexto(String string) {
		this.lblTexto.setText(string);
		
	}
}
