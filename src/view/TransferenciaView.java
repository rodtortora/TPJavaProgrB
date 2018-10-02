package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class TransferenciaView extends JFrame implements TransferenciaInterface {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public TransferenciaView() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 176);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCbuDestino = new JLabel("CBU cuenta destino");
		lblCbuDestino.setBounds(10, 11, 186, 14);
		contentPane.add(lblCbuDestino);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(207, 8, 217, 20);
		contentPane.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(207, 52, 217, 20);
		contentPane.add(spinner_1);
		
		JLabel lblCantidadDeDinero = new JLabel("Cantidad de dinero");
		lblCantidadDeDinero.setBounds(10, 55, 186, 14);
		contentPane.add(lblCantidadDeDinero);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.setBounds(10, 96, 414, 23);
		contentPane.add(btnConfirmar);
	}
	
	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
	}

}
