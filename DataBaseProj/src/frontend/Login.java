package frontend;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.sql.ResultSet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import database.IMySQLAccess;
import database.MySQLAccess;

import model.*;

public class Login extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final int  LABELS_X = 30 , LABELS_Y=30,LABELS_W=130,LABELS_H=18;
	final int  TEXT_X = 210 , TEXT_Y=30,TEXT_W=250,TEXT_H=18;
	private JTextField textUserName;
	private JPasswordField textPass;
	private JButton btnSignIn ;
	private IMySQLAccess db ;
	
	public Login(IMySQLAccess db) {
		this.db = db;
		getContentPane().setLayout(null);
		
		/////////////////////////////// Labels //////////////////////////////////
		
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setBounds(LABELS_X,LABELS_Y+2*LABELS_H,LABELS_W,LABELS_H);
		getContentPane().add(lblUserName);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setBounds(LABELS_X,LABELS_Y+4*LABELS_H,LABELS_W,LABELS_H);
		getContentPane().add(lblPass);
		
		/////////////////////////////// Text Fields //////////////////////////////////
		
		
		textUserName = new JTextField();
		textUserName.setBounds(TEXT_X, TEXT_Y+2*TEXT_H,TEXT_W, TEXT_H);
		getContentPane().add(textUserName);
		
		textPass = new JPasswordField();
		textPass.setBounds(TEXT_X, TEXT_Y+4*TEXT_H,TEXT_W, TEXT_H);
		getContentPane().add(textPass);
		textPass.setColumns(10);
				
		btnSignIn = new JButton("Login");
		btnSignIn.setBounds(200, 200, 120, 25);
		getContentPane().add(btnSignIn);
		btnSignIn.addActionListener(logUser());
		
		
		this.setTitle("Login");
		this.setSize(500, 300);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	private ActionListener logUser(){
		ActionListener l = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO search for user
				User user = db.logUser(textUserName.getText(), textPass.getText());
				if(user!=null){
					new MainMenu(db,user);
					dispose();
					}
				else {
					JOptionPane.showMessageDialog(rootPane, "Wrong username and/or password");
					textUserName.setText("");
					textPass.setText("");
				}
			}
		};
		return l;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IMySQLAccess db = new MySQLAccess();
		new Login(db);
	}
}
