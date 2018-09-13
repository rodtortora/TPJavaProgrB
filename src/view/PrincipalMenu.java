package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.MenuController;
import events.ChangePassListener;
import events.MenuEvent;
import events.MenuEventListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrincipalMenu extends JFrame implements PrincipalMenuInterface {

	private JPanel contentPane;
	private JLabel lblBanco = new JLabel();
	private MenuEventListener menuEventListener;
	
	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
	}
	
	@Override
	public void setLblBanco(String message) {
		this.lblBanco.setText(message);
	}

	public PrincipalMenu(ChangePassInterface changePassInterface) {
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblBanco.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblBanco.setBounds(10, 11, 414, 14);
		contentPane.add(lblBanco);
		
		JButton btnCambiarClave = new JButton("Cambiar clave");
		btnCambiarClave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePassInterface.mostrar(true);
			}
		});
		btnCambiarClave.setBounds(10, 53, 414, 23);
		contentPane.add(btnCambiarClave);
		
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

	@Override
	public void setMenuEventListener(MenuEventListener e) {
		this.menuEventListener = e;
		
	}


}
