package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class DepositarView extends JFrame implements DepositarInterface {

	private JPanel contentPane;

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
		
		JLabel lblNewLabel = new JLabel("Valor del billete");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(10, 11, 203, 14);
		contentPane.add(lblNewLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(10, 36, 199, 20);
		contentPane.add(spinner);
		
		JButton btnNewButton = new JButton("DEPOSITAR");
		btnNewButton.setBounds(10, 151, 203, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad");
		lblNewLabel_1.setBounds(10, 77, 203, 14);
		contentPane.add(lblNewLabel_1);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(10, 102, 199, 20);
		contentPane.add(spinner_1);
	}

	@Override
	public void mostrar(boolean e) {
		// TODO Auto-generated method stub
		
	}
}
