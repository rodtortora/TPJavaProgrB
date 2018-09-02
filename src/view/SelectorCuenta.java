package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import events.AccountSelectedEvent;
import events.AccountSelectedListener;
import model.ATM;
import model.Cuenta;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SelectorCuenta extends JFrame implements SelectorCuentaInterface {

	private JPanel contentPane;
	private JComboBox<Cuenta> cuentaComboBox = new JComboBox<Cuenta>();
	private AccountSelectedListener accountSelectedListener;

	/**
	 * Launch the application.
	 */
	
	public void mostrar() {
		this.setVisible(true);
	}
	
	public void ocultar() {
		this.setVisible(false);
	}
	
	@Override
	public void llenarCombobox(ArrayList<Cuenta> cuentas) {
		for (Cuenta cuenta : cuentas) {
			this.cuentaComboBox.addItem(cuenta);
		}
		
	}


	/**
	 * Create the frame.
	 */
	public SelectorCuenta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 248);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneSuCuenta = new JLabel("Seleccione su cuenta");
		lblSeleccioneSuCuenta.setBounds(5, 5, 424, 14);
		contentPane.add(lblSeleccioneSuCuenta);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(5, 181, 424, 23);
		contentPane.add(btnCancelar);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountSelectedListener.ListenAccountSelectedEvent(new AccountSelectedEvent((Cuenta) cuentaComboBox.getSelectedItem()));				
			}
		});
		btnConfirmar.setBounds(5, 152, 424, 23);
		contentPane.add(btnConfirmar);
		
		
		cuentaComboBox.setBounds(10, 66, 414, 20);
		contentPane.add(cuentaComboBox);
	}

	@Override
	public void setAccountSelectedListener(AccountSelectedListener accountSelectedListener) {
		this.accountSelectedListener = accountSelectedListener;
		
	}

}
