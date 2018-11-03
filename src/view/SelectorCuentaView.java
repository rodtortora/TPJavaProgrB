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

public class SelectorCuentaView extends JFrame implements SelectorCuentaInterface {

	private JPanel contentPane;
	private JComboBox<Cuenta> cuentaComboBox = new JComboBox<Cuenta>();
	private AccountSelectedListener accountSelectedListener;
	private ATMSelectorInterface atmSelectorInterface;

	/**
	 * Launch the application.
	 */
	
	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
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
	public SelectorCuentaView() {
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
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar(false);
				vaciarCombobox();
				atmSelectorInterface.mostrar(true);
			}
		});
		btnCancelar.setBounds(5, 181, 424, 23);
		contentPane.add(btnCancelar);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountSelectedListener.ListenAccountSelectedEvent(new AccountSelectedEvent((Cuenta) cuentaComboBox.getSelectedItem()));		
				mostrar(false);
				vaciarCombobox();				
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
	
	@Override
	public void setAtmSelectorInterface(ATMSelectorInterface atmSelectorInterface) {
		this.atmSelectorInterface = atmSelectorInterface;
	}

	public void vaciarCombobox() {
		this.cuentaComboBox.removeAllItems();
		
	}

}
