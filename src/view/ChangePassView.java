package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import events.ChangePassEvent;
import events.ChangePassListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePassView extends JFrame implements ChangePassInterface {

	private JPanel contentPane;
	private JPasswordField claveActual;
	private JPasswordField claveNueva;
	private JPasswordField confirmClaveNueva;
	private ChangePassListener changePassListener;
	private PrincipalMenuInterface principalMenuInterface;

	public ChangePassView() {
		this.changePassListener = changePassListener;
		setTitle("Cambio de clave");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 367, 244);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pwdActual = new String(claveActual.getPassword());
				String pwdNueva = new String(claveNueva.getPassword());
				String pwdNuevaConfirmar = new String(confirmClaveNueva.getPassword());
				changePassListener.listenChangePassEvent(new ChangePassEvent(pwdActual, pwdNueva, pwdNuevaConfirmar));
			}
		});
		btnConfirmar.setBounds(10, 139, 331, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("VOLVER AL MENU");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principalMenuInterface.mostrar(true);
				mostrar(false);
			}
		});
		btnCancelar.setBounds(10, 173, 331, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblContraseaActual = new JLabel("Clave actual");
		lblContraseaActual.setBounds(10, 31, 118, 14);
		contentPane.add(lblContraseaActual);
		
		claveActual = new JPasswordField();
		claveActual.setBounds(170, 28, 171, 20);
		contentPane.add(claveActual);
		
		claveNueva = new JPasswordField();
		claveNueva.setBounds(170, 59, 171, 20);
		contentPane.add(claveNueva);
		
		confirmClaveNueva = new JPasswordField();
		confirmClaveNueva.setBounds(170, 90, 171, 20);
		contentPane.add(confirmClaveNueva);
		
		JLabel lblContraseaNueva = new JLabel("Clave nueva");
		lblContraseaNueva.setBounds(10, 62, 118, 14);
		contentPane.add(lblContraseaNueva);
		
		JLabel lblConfirmeContrasea = new JLabel("Confirme clave");
		lblConfirmeContrasea.setBounds(10, 93, 118, 14);
		contentPane.add(lblConfirmeContrasea);
	}

	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
	}

	@Override
	public void setChangePassListener(ChangePassListener listener) {
		this.changePassListener = listener;
		
	}

	@Override
	public void setPrincipalMenuInterface(PrincipalMenuInterface principalMenuInterface) {
		this.principalMenuInterface = principalMenuInterface;
		
	}
}
