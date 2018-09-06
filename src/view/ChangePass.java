package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import events.ChangePassEvent;
import events.ChangePassListener;

public class ChangePass extends JFrame implements ChangePassInterface {
	
	private JPanel contentPane;
	private JPasswordField claveActual;
	private JPasswordField claveNueva;
	private JPasswordField confirmClaveNueva;
	private JButton btnCancelar;
	private ChangePassListener changePassListener;
	private JLabel lblAdvertencia;
	
	@Override
	public void setChangePassListener(ChangePassListener changePassListener) {
		this.changePassListener = changePassListener;		
	}
	
	@Override
	public void mostrar() {
		this.setVisible(true);	
	}

	@Override
	public void ocultar() {
		this.setVisible(false);	
	}

	/**
	 * Create the frame.
	 */
	public ChangePass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		claveActual = new JPasswordField();
		claveActual.setBounds(142, 55, 136, 20);
		contentPane.add(claveActual);
		
		claveNueva = new JPasswordField();
		claveNueva.setBounds(142, 86, 136, 20);
		contentPane.add(claveNueva);
		
		JLabel lblClaveActual = new JLabel("Clave actual");
		lblClaveActual.setBounds(10, 58, 122, 14);
		contentPane.add(lblClaveActual);
		
		JLabel lblClaveNueva = new JLabel("Clave nueva");
		lblClaveNueva.setBounds(10, 89, 122, 14);
		contentPane.add(lblClaveNueva);
		
		JLabel lblConfirmarClaveNueva = new JLabel("Confirmar clave nueva");
		lblConfirmarClaveNueva.setBounds(10, 120, 122, 14);
		contentPane.add(lblConfirmarClaveNueva);
		
		confirmClaveNueva = new JPasswordField();
		confirmClaveNueva.setBounds(142, 117, 136, 20);
		contentPane.add(confirmClaveNueva);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(10, 169, 95, 23);
		contentPane.add(btnCancelar);
		JButton btnConfirmarr = new JButton("CONFIRMAR");
		btnConfirmarr.setBounds(183, 169, 95, 23);
		contentPane.add(btnConfirmarr);
		btnConfirmarr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pwdActual = new String(claveActual.getPassword());
				String pwdNueva = new String(claveNueva.getPassword());
				String pwdNuevaConfirmar = new String(confirmClaveNueva.getPassword());
				changePassListener.listenChangePassEvent(new ChangePassEvent(pwdActual, pwdNueva, pwdNuevaConfirmar));
			}
		});

		
		lblAdvertencia = new JLabel("Advertencia: datos invalidos.");
		lblAdvertencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdvertencia.setForeground(Color.RED);
		lblAdvertencia.setBounds(10, 21, 268, 14);
		lblAdvertencia.setVisible(false);
		contentPane.add(lblAdvertencia);
		
	}

	@Override
	public void mostrarError() {		
		this.lblAdvertencia.setVisible(true);	
	}

}
