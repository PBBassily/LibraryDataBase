package frontend;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;

public class Analysis extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Analysis() {
		
		
		JLabel lblNewLabel = new JLabel("Last Month Sales ");
		lblNewLabel.setBounds(49, 70, 140, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblTopCustomers = new JLabel("Top 10 Customers");
		lblTopCustomers.setBounds(49, 140, 140, 15);
		getContentPane().add(lblTopCustomers);
		
		JLabel lblTopBooks = new JLabel("Top 5 Books ");
		lblTopBooks.setBounds(49, 210, 140, 15);
		getContentPane().add(lblTopBooks);
		
		
		this.setTitle("Analysis");
		this.setSize(500, 360);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
