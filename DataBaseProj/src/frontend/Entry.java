
package frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;

import database.IMySQLAccess;
import database.MySQLAccess;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Entry extends JFrame {
	public Entry() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.setBounds(200, 120, 117, 25);
		panel.add(btnSignIn);
		btnSignIn.addActionListener(signInAction());
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(200, 191, 117, 25);
		panel.add(btnLogin);
		btnLogin.addActionListener(loginAction());
		
		this.setTitle("Library System");
		this.setSize(500,300);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	private ActionListener signInAction(){
		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IMySQLAccess db = new MySQLAccess();
				new SignIn(db,null);
			}
		};
		return l;
	}
	private ActionListener loginAction(){
		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IMySQLAccess db = new MySQLAccess();
				new Login(db);
				dispose();
			}
		};
		return l;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Entry();
	}
	
}
