package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import events.BalanceCheckEvent;
import events.BalanceCheckListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrincipalMenuView extends JFrame implements PrincipalMenuInterface {

	private JPanel contentPane;
	private JLabel lblBanco = new JLabel();
	private BalanceCheckListener balanceCheckListener;
	
	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
	}
	
	@Override
	public void setLblBanco(String message) {
		this.lblBanco.setText(message);
	}

	public PrincipalMenuView(ChangePassInterface changePassInterface, ExtraccionInterface extraccionInterface, DepositarInterface depositarInterface, TransferenciaInterface transferenciaInterface, ConsultarMovimientosInterface consultarMovimientosInterface,
			ATMSelectorInterface atmSelectorInterface) {
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
				mostrar(false);
				changePassInterface.mostrar(true);
			}
		});
		btnCambiarClave.setBounds(10, 53, 414, 23);
		contentPane.add(btnCambiarClave);
		
		JButton btnConsultarSaldo = new JButton("Consultar Saldo");
		btnConsultarSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				balanceCheckListener.listenBalanceCheckEvent(new BalanceCheckEvent());
			}
		});
		btnConsultarSaldo.setBounds(10, 87, 414, 23);
		contentPane.add(btnConsultarSaldo);
		
		JButton btnRetirarDinero = new JButton("Extracci\u00F3n");
		btnRetirarDinero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar(false);
				extraccionInterface.mostrar(true);
			}
		});
		btnRetirarDinero.setBounds(10, 121, 414, 23);
		contentPane.add(btnRetirarDinero);
		
		JButton btnTransferencia = new JButton("Transferencia");
		btnTransferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar(false);
				transferenciaInterface.mostrar(true);
			}
		});
		btnTransferencia.setBounds(10, 189, 414, 23);
		contentPane.add(btnTransferencia);
		
		JButton btnConsultaDeMovimientos = new JButton("Consulta de movimientos");
		btnConsultaDeMovimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar(false);
				consultarMovimientosInterface.vaciar();
				consultarMovimientosInterface.mostrar(true);
			}
		});
		btnConsultaDeMovimientos.setBounds(10, 227, 414, 23);
		contentPane.add(btnConsultaDeMovimientos);
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar(false);
				atmSelectorInterface.mostrar(true);
			}
		});
		btnSalir.setBounds(10, 261, 414, 23);
		contentPane.add(btnSalir);
		
		JButton btnDepositar = new JButton("Dep\u00F3sito");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar(false);
				depositarInterface.mostrar(true);
			}
		});
		btnDepositar.setBounds(10, 155, 414, 23);
		contentPane.add(btnDepositar);
	}


	public BalanceCheckListener getBalanceCheckListener() {
		return balanceCheckListener;
	}

	public void setBalanceCheckListener(BalanceCheckListener balanceCheckListener) {
		this.balanceCheckListener = balanceCheckListener;
	}


}
