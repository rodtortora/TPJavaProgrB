package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import events.AtmSelectedEvent;
import events.AtmSelectedListener;
import model.ATM;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ATMSelectorView extends JFrame implements ATMSelectorInterface {

	private JPanel contentPane;
	private JComboBox<ATM> ATMcomboBox = new JComboBox<ATM>();
	private AtmSelectedListener atmSelectedListener;

	/**
	 * Launch the application.
	 */
	
	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
	}
	
	@Override
	public void llenarCombobox(ArrayList<ATM> atms) {
		for (ATM atm : atms) {
			this.ATMcomboBox.addItem(atm);
		}
		
	}


	/**
	 * Create the frame.
	 */
	public ATMSelectorView(AdministracionInterface administracion) {
		setTitle("Simulador de seleccion de ATM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione ATM de la lista");
		lblNewLabel.setBounds(10, 11, 414, 14);
		contentPane.add(lblNewLabel);
		
		this.ATMcomboBox.setBounds(10, 67, 414, 20);
		contentPane.add(ATMcomboBox);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atmSelectedListener.listenAtmSelectedEvent(new AtmSelectedEvent((ATM) ATMcomboBox.getSelectedItem()));
				setVisible(false);
			}
		});
		btnConfirmar.setBounds(10, 127, 414, 41);
		contentPane.add(btnConfirmar);
		
		JButton btnAdministracion = new JButton("Administracion");
		btnAdministracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrar(false);
				administracion.mostrar(true);
			}
		});
		btnAdministracion.setBounds(10, 172, 414, 41);
		contentPane.add(btnAdministracion);
	}

	@Override
	public void setAtmSelectedListener(AtmSelectedListener atmSelectedListener) {
		this.atmSelectedListener = atmSelectedListener;
		
	}



}