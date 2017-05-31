package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import database.IMySQLAccess;
import database.MySQLAccess;

public class AddBook extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final int  LABELS_X = 30 , LABELS_Y=10,LABELS_W=130,LABELS_H=15;
	final int  TEXT_X = 210 , TEXT_Y=10,TEXT_W=250,TEXT_H=15;
	private JTextField text1,text2,text3,text4,text5,text6,text7;
	private IMySQLAccess db ;
	private String[] domain = {"Art","Geography","History","Religion","Science"};
	private Vector<String> publishers ;
	private JComboBox<String> domainList,publishersList;
	
	public AddBook(IMySQLAccess db) {
		this.db = db;
		getContentPane().setLayout(null);
		
		/////////////////////////////// Labels //////////////////////////////////
		
		JLabel lbl1 = new JLabel("ISBN");
		lbl1.setBounds(LABELS_X, LABELS_Y, LABELS_W, LABELS_H);
		getContentPane().add(lbl1);
		
		JLabel lbl2 = new JLabel("Title");
		lbl2.setBounds(LABELS_X,LABELS_Y+2*LABELS_H,LABELS_W,LABELS_H);
		getContentPane().add(lbl2);
		
		JLabel lbl3 = new JLabel("Quantity");
		lbl3.setBounds(LABELS_X,LABELS_Y+4*LABELS_H,LABELS_W,LABELS_H);
		getContentPane().add(lbl3);
		
		JLabel lbl4 = new JLabel("Publication Year");
		lbl4.setBounds(LABELS_X,LABELS_Y+6*LABELS_H,LABELS_W,LABELS_H);
		getContentPane().add(lbl4);
		
		JLabel lbl5 = new JLabel("Price");
		lbl5.setBounds(LABELS_X,LABELS_Y+8*LABELS_H,LABELS_W,LABELS_H);
		getContentPane().add(lbl5);
		
		JLabel lbl6 = new JLabel("Threshold");
		lbl6.setBounds(LABELS_X,LABELS_Y+10*LABELS_H,LABELS_W,LABELS_H);
		getContentPane().add(lbl6);
		
		JLabel lbl7 = new JLabel("Publisher");
		lbl7.setBounds(LABELS_X,LABELS_Y+12*LABELS_H,LABELS_W,LABELS_H);
		getContentPane().add(lbl7);
		
		JLabel lbl8 = new JLabel("Domain");
		lbl8.setBounds(LABELS_X,LABELS_Y+14*LABELS_H,LABELS_W,LABELS_H);
		getContentPane().add(lbl8);
		
		/////////////////////////////// Text Fields //////////////////////////////////
		
		text1 = new JTextField();
		text1.setBounds(TEXT_X, TEXT_Y,TEXT_W, TEXT_H);
		getContentPane().add(text1);
		text1.setColumns(10);
		
		text2 = new JTextField();
		text2.setBounds(TEXT_X, TEXT_Y+2*TEXT_H,TEXT_W, TEXT_H);
		getContentPane().add(text2);
		
		text3 = new JTextField();
		text3.setBounds(TEXT_X, TEXT_Y+4*TEXT_H,TEXT_W, TEXT_H);
		getContentPane().add(text3);
		text3.setColumns(10);
		
		text4 = new JTextField();
		text4.setBounds(TEXT_X, TEXT_Y+6*TEXT_H,TEXT_W, TEXT_H);
		getContentPane().add(text4);
		text4.setColumns(10);
		
		text5 = new JTextField();
		text5.setBounds(TEXT_X, TEXT_Y+8*TEXT_H,TEXT_W, TEXT_H);
		getContentPane().add(text5);
		text5.setColumns(10);
		
		text6 = new JTextField();
		text6.setBounds(TEXT_X, TEXT_Y+10*TEXT_H,TEXT_W, TEXT_H);
		getContentPane().add(text6);
		text6.setColumns(10);
		
		publishersList = new JComboBox<>(db.getPublishers());
		publishersList.setBounds(TEXT_X, TEXT_Y+12*TEXT_H,TEXT_W, 20);
		getContentPane().add(publishersList);
		
		domainList = new JComboBox<>(domain);  
		domainList.setBounds(TEXT_X, TEXT_Y+14*TEXT_H,TEXT_W, 20);
		getContentPane().add(domainList);
		
		JButton btnAdd = new JButton("Add Book");
		btnAdd.setBounds(200, 260, 120, 25);
		getContentPane().add(btnAdd);
		btnAdd.addActionListener(addBook());
		
		this.setTitle("New Book");
		this.setSize(500, 300);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	private ActionListener addBook(){
		ActionListener l = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				db.addBook(Integer.parseInt(text1.getText()), text2.getText(),domainList.getSelectedItem().toString(), 
						Integer.parseInt(text3.getText()),Integer.parseInt(text4.getText()), 
						Float.parseFloat(text5.getText()),Integer.parseInt(text6.getText()),
						publishersList.getSelectedItem().toString());
				 
				dispose();
			}
		};
		return l;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IMySQLAccess db = new MySQLAccess();
		new AddBook(db);
	}
}
