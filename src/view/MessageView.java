package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class MessageView extends JFrame implements MessageInterface {

	private JPanel contentPane;
	private JLabel lbl1 = new JLabel();
	private JLabel lbl2 = new JLabel();

	@Override
	public void mostrar(boolean e) {
		this.setVisible(e);
		
	}
	
	public void setMessage(String message, String message1) {		
		this.lbl1.setText(message);
		this.lbl2.setText(message1);
	}

	/**
	 * Create the frame.
	 */
	public MessageView() {
		setTitle("Log");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 133);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.lbl1.setBounds(10, 11, 414, 14);
		contentPane.add(this.lbl1);
		this.lbl2.setBounds(10, 49, 414, 20);
		contentPane.add(this.lbl2);
	}

	@Override
	public void setMessage(String string) {
		this.lbl1.setText(string);
		this.lbl2.setText("");
		
	}
}
