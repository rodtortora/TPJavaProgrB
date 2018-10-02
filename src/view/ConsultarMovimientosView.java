package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;

public class ConsultarMovimientosView extends JFrame implements ConsultarMovimientosInterface {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ConsultarMovimientosView() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 77, 414, 173);
		contentPane.add(textArea);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBounds(335, 11, 89, 42);
		contentPane.add(btnBuscar);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(10, 15, 46, 14);
		contentPane.add(lblMes);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"}));
		comboBox.setBounds(54, 12, 89, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("A\u00F1o");
		lblNewLabel.setBounds(10, 43, 46, 14);
		contentPane.add(lblNewLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(54, 40, 89, 20);
		contentPane.add(spinner);
	}

	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
	}
}
