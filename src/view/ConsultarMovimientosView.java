package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import events.MovementsQueryEvent;
import events.MovementsQueryListener;
import model.TipoTransaccion;
import model.Transaction;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class ConsultarMovimientosView extends JFrame implements ConsultarMovimientosInterface {

	private JPanel contentPane;
	private PrincipalMenuInterface principalMenuInterface;
	private MovementsQueryListener movementsQueryListener;
    private JTable tabla = new JTable();
    private DefaultTableModel tablaModel = new DefaultTableModel();
    private JScrollPane scroll = new JScrollPane(tabla);


	/**
	 * Create the frame.
	 */
	public ConsultarMovimientosView() {
		Calendar fechaActual = Calendar.getInstance();
		int anoActual = fechaActual.get(Calendar.YEAR);
		int mesActual = fechaActual.get(Calendar.MONTH);
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 781, 357);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		tablaModel.addColumn("Fecha");
		tablaModel.addColumn("Dinero");
		tablaModel.addColumn("Tipo transaccion");
		tablaModel.addColumn("Debito / Credito");
		tabla.setModel(tablaModel);
		scroll.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLUE, null, null, null));
		
		scroll.setBounds(10, 77, 745, 173);
		contentPane.add(scroll);
		
		JSpinner spAno = new JSpinner();
		spAno.setBounds(54, 40, 241, 20);
		
		spAno.setValue(anoActual);
		contentPane.add(spAno);
	
		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(10, 15, 46, 14);
		contentPane.add(lblMes);
		
		JComboBox cBoxMes = new JComboBox();
		cBoxMes.setModel(new DefaultComboBoxModel(new String[] {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"}));
		cBoxMes.setSelectedIndex(mesActual);
		cBoxMes.setBounds(54, 12, 241, 20);
		contentPane.add(cBoxMes);
		
		JLabel lblAno = new JLabel("A\u00F1o");
		lblAno.setBounds(10, 43, 46, 14);
		contentPane.add(lblAno);
		
		
		
		JButton btnVolverAlMenu = new JButton("VOLVER AL MENU");
		btnVolverAlMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principalMenuInterface.mostrar(true);
				mostrar(false);
			}
		});
		btnVolverAlMenu.setBounds(10, 272, 745, 35);
		contentPane.add(btnVolverAlMenu);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ano = ((int)spAno.getValue());
				int mes = cBoxMes.getSelectedIndex();
				movementsQueryListener.listenMovementsQueryEvent(new MovementsQueryEvent(ano, mes));
			}
		});
		
		btnBuscar.setBounds(666, 15, 89, 42);
		contentPane.add(btnBuscar);
		
		
	}

	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
	}
	
	@Override
	public void setPrincipalMenuInterface(PrincipalMenuInterface principalMenuInterface) {
		this.principalMenuInterface = principalMenuInterface;
		
	}

	@Override
	public void setMovementsQueryListener(MovementsQueryListener listener) {
		this.movementsQueryListener = listener;
		
	}

	@Override
	public void fillWithMovements(ArrayList<Transaction> transactions) {
		String fecha;
		String tipoMovimiento;
		this.vaciar();
		for(Transaction transaction : transactions) {	
			if (transaction.isDebito()) {
				tipoMovimiento = "Debito";
			} else {
				tipoMovimiento = "Credito";
			}
			fecha = transaction.getFechaTransaccion().get(Calendar.YEAR) + "/" + transaction.getFechaTransaccion().get(Calendar.MONTH) + "/" + transaction.getFechaTransaccion().get(Calendar.DAY_OF_MONTH);
			tablaModel.addRow(new Object[]{fecha,transaction.getMoneyAmount(),TipoTransaccion.getDescription(transaction.getTipoTransaccion()), tipoMovimiento});
			tabla.setModel(tablaModel);
		}
		
	}

	@Override
	public void vaciar() {
		int filas = tablaModel.getRowCount();
		if (filas > 0) {
			for (int i = 1; i <= filas; i++) {
				tablaModel.removeRow(0);
			}
		}
		
	}
}
