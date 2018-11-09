package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import events.AltaCuentaRequestEvent;
import events.AltaCuentaRequestListener;
import events.AltaTarjetaRequestEvent;
import events.AltaTarjetaRequestListener;
import events.BajaRequestEvent;
import events.BajaRequestListener;
import events.CardReadedListener;
import events.ModificacionRequestEvent;
import events.ModificacionRequestListener;
import model.ATM;
import model.Banco;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.awt.event.ActionEvent;

public class AdministracionView extends JFrame implements AdministracionInterface {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCuit;
	private JPasswordField pswPin;
	private JComboBox<Banco> cmbxBanco = new JComboBox<Banco>();
	private ModificacionRequestListener modificacionRequestListener;
	private AltaTarjetaRequestListener altaTarjetaRequestListener;
	private BajaRequestListener bajaRequestListener;
	private AltaCuentaRequestListener altaCuentaRequestListener;
	private ATMSelectorInterface atmSelectorInterface;
	
	@Override
	public void setModificacionRequestListener(ModificacionRequestListener listener) {
		this.modificacionRequestListener = listener;
		
	}
	@Override
	public void setAltaTarjetaRequestListener(AltaTarjetaRequestListener listener) {
		this.altaTarjetaRequestListener = listener;
		
	}
	@Override
	public void setBajaRequestListener(BajaRequestListener listener) {
		this.bajaRequestListener = listener;
		
	}
	@Override
	public void setAltaCuentaRequestListener(AltaCuentaRequestListener listener) {
		this.altaCuentaRequestListener = listener;
		
	}
	
	
	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
	}
	
	@Override
	public void llenarCombobox(ArrayList<Banco> bancos) {
		for (Banco banco : bancos) {
			this.cmbxBanco.addItem(banco);
		}
		
	}


	public AdministracionView() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 548, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("CANCELAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrar(false);
				atmSelectorInterface.mostrar(true);
			}
		});
		btnNewButton.setBounds(10, 359, 512, 23);
		contentPane.add(btnNewButton);
		

		
		
		JSpinner spNroTarjeta = new JSpinner();
		spNroTarjeta.setBounds(244, 25, 278, 20);
		contentPane.add(spNroTarjeta);
		
		JLabel lblNewLabel = new JLabel("Nro de tarjeta");
		lblNewLabel.setBounds(10, 28, 224, 14);
		contentPane.add(lblNewLabel);
		
		cmbxBanco.setBounds(244, 56, 278, 20);
		contentPane.add(cmbxBanco);
		
		JLabel lblBanco = new JLabel("Banco");
		lblBanco.setBounds(10, 59, 224, 14);
		contentPane.add(lblBanco);
		
		JComboBox cmbxTipoCuenta = new JComboBox();
		cmbxTipoCuenta.setModel(new DefaultComboBoxModel(new String[] {"Cuenta Corriente", "Cuenta Sueldo", "Caja de Ahorro"}));
		cmbxTipoCuenta.setBounds(244, 87, 278, 20);
		contentPane.add(cmbxTipoCuenta);
		
		JLabel lblTipoDeCuenta = new JLabel("Tipo de cuenta");
		lblTipoDeCuenta.setBounds(10, 90, 224, 14);
		contentPane.add(lblTipoDeCuenta);
		
		JSpinner spNroCuenta = new JSpinner();
		spNroCuenta.setBounds(244, 118, 278, 20);
		contentPane.add(spNroCuenta);
		
		JLabel lblNroDeCuenta = new JLabel("Nro de cuenta");
		lblNroDeCuenta.setBounds(10, 121, 224, 14);
		contentPane.add(lblNroDeCuenta);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(10, 152, 224, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 183, 224, 14);
		contentPane.add(lblApellido);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(244, 149, 278, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(244, 180, 278, 20);
		contentPane.add(txtApellido);
		
		JLabel lblCuitEmpresa = new JLabel("Cuit Empresa");
		lblCuitEmpresa.setBounds(10, 214, 224, 14);
		contentPane.add(lblCuitEmpresa);
		
		txtCuit = new JTextField();
		txtCuit.setColumns(10);
		txtCuit.setBounds(244, 211, 278, 20);
		contentPane.add(txtCuit);
		
		JLabel lblLimiteDescubierto = new JLabel("Limite descubierto");
		lblLimiteDescubierto.setBounds(10, 245, 224, 14);
		contentPane.add(lblLimiteDescubierto);
		
		JSpinner spnLimiteDesc = new JSpinner();
		spnLimiteDesc.setBounds(244, 242, 278, 20);
		contentPane.add(spnLimiteDesc);
		
		JLabel lblPin = new JLabel("PIN");
		lblPin.setBounds(10, 276, 224, 14);
		contentPane.add(lblPin);
		
		pswPin = new JPasswordField();
		pswPin.setBounds(244, 273, 278, 20);
		contentPane.add(pswPin);
		
		JButton btnModificacion = new JButton("MODIFICACION");
		btnModificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigInteger nroTarjeta = BigInteger.valueOf((int)spNroTarjeta.getValue());
				BigInteger nroCuenta = BigInteger.valueOf((int)spNroCuenta.getValue());
				BigDecimal limiteDesc = BigDecimal.valueOf((int)spnLimiteDesc.getValue());
				Banco banco = ((Banco) cmbxBanco.getSelectedItem());
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String cuit = txtCuit.getText();
				String pwd = new String(pswPin.getPassword());
				modificacionRequestListener.listenModificacionRequestEvent(new ModificacionRequestEvent(nroTarjeta,nroCuenta,limiteDesc,banco,nombre,apellido,cuit,pwd));
			}
		});
		btnModificacion.setBounds(130, 325, 110, 23);
		contentPane.add(btnModificacion);
		
		JButton btnAltaCuenta = new JButton("ALTA CUENTA");
		btnAltaCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigInteger nroTarjeta = BigInteger.valueOf((int)spNroTarjeta.getValue());
				BigInteger nroCuenta = BigInteger.valueOf((int)spNroCuenta.getValue());
				BigDecimal limiteDesc = BigDecimal.valueOf((int)spnLimiteDesc.getValue());
				String tipoCuenta = ((String)cmbxTipoCuenta.getSelectedItem());
				Banco banco = ((Banco) cmbxBanco.getSelectedItem());
				String cuit = txtCuit.getText();
				altaCuentaRequestListener.listenAltaCuentaRequestEvent(new AltaCuentaRequestEvent(nroTarjeta,nroCuenta,limiteDesc,tipoCuenta,banco,cuit));
			}
		});
		btnAltaCuenta.setBounds(297, 325, 110, 23);
		contentPane.add(btnAltaCuenta);
		
		JButton btnBaja = new JButton("BAJA");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigInteger nroTarjeta = BigInteger.valueOf((int)spNroTarjeta.getValue());
				Banco banco = ((Banco) cmbxBanco.getSelectedItem());
				bajaRequestListener.listenBajaRequestEvent(new BajaRequestEvent(nroTarjeta,banco));
			}
		});
		btnBaja.setBounds(10, 325, 110, 23);
		contentPane.add(btnBaja);
		
		JButton btnAltaTarjeta = new JButton("ALTA TARJETA");
		btnAltaTarjeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigInteger nroTarjeta = BigInteger.valueOf((int)spNroTarjeta.getValue());
				BigInteger nroCuenta = BigInteger.valueOf((int)spNroCuenta.getValue());
				BigDecimal limiteDesc = BigDecimal.valueOf((int)spnLimiteDesc.getValue());
				String tipoCuenta = ((String)cmbxTipoCuenta.getSelectedItem());
				Banco banco = ((Banco) cmbxBanco.getSelectedItem());
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String cuit = txtCuit.getText();
				String pwd = new String(pswPin.getPassword());
				altaTarjetaRequestListener.listenAltaTarjetaRequestEvent(new AltaTarjetaRequestEvent(nroTarjeta,nroCuenta,limiteDesc,tipoCuenta,banco,nombre,apellido,cuit,pwd));
			}
		});
		btnAltaTarjeta.setBounds(417, 325, 110, 23);
		contentPane.add(btnAltaTarjeta);
	}
	@Override
	public void setAtmSelectorInterface(ATMSelectorInterface atmInterface) {
		this.atmSelectorInterface = atmInterface;
		
	}
}
