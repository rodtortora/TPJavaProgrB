package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class PrincipalMenu extends JFrame implements PrincipalMenuInterface {

	private JPanel contentPane;
	private JLabel lblBanco = new JLabel(); 
	
	@Override
	public void mostrar() {
		this.setVisible(true);
	}

	@Override
	public void ocultar() {
		this.setVisible(false);
	}
	
	@Override
	public void setLblBanco(String message) {
		this.lblBanco.setText(message);
	}

	public PrincipalMenu() {
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblBanco.setBounds(10, 11, 414, 14);
		contentPane.add(lblBanco);
		
		JButton btnNewButton = new JButton("Cambiar clave");
		btnNewButton.setBounds(10, 53, 414, 23);
		contentPane.add(btnNewButton);
		
		JButton btnConsultarSaldo = new JButton("Consultar Saldo");
		btnConsultarSaldo.setBounds(10, 87, 414, 23);
		contentPane.add(btnConsultarSaldo);
		
		JButton btnRetirarDinero = new JButton("Retirar dinero");
		btnRetirarDinero.setBounds(10, 121, 414, 23);
		contentPane.add(btnRetirarDinero);
		
		JButton btnDepositarDinero = new JButton("Depositar dinero");
		btnDepositarDinero.setBounds(10, 155, 414, 23);
		contentPane.add(btnDepositarDinero);
		
		JButton btnTransferencia = new JButton("Transferencia");
		btnTransferencia.setBounds(10, 189, 414, 23);
		contentPane.add(btnTransferencia);
		
		JButton btnConsultaDeMovimientos = new JButton("Consulta de movimientos");
		btnConsultaDeMovimientos.setBounds(10, 227, 414, 23);
		contentPane.add(btnConsultaDeMovimientos);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(10, 261, 414, 23);
		contentPane.add(btnSalir);
	}


}
