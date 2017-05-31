package frontend;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import database.IMySQLAccess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import model.*;

public class MainMenu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int  X = 180 , Y=30,W=150,H=22;
	private IMySQLAccess db;
	
	public MainMenu(IMySQLAccess db,User user) {
		this.db=db;
		
		getContentPane().setLayout(null);
		
		
		JLabel welcome = new JLabel("Welcome "+user.fName+"...");
		welcome.setBounds(X/8,Y-H,W,H);
		getContentPane().add(welcome);
		
		JButton btnBuyBook = new JButton("Buy Book");
		btnBuyBook.setBounds(X,Y+0*H,W,H);
		getContentPane().add(btnBuyBook);
		btnBuyBook.addActionListener(buyBook());
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.setBounds(X,Y+2*H,W,H);
		btnAddBook.setVisible(user.isManager);
		getContentPane().add(btnAddBook);
		btnAddBook.addActionListener(addBook());

		JButton btnUpProfile = new JButton("Update Profile");
		btnUpProfile.setBounds(X,Y+4*H,W,H);
		getContentPane().add(btnUpProfile);
		btnUpProfile.addActionListener(profile(user));
		
		JButton btnPromUser = new JButton("Promote User");
		btnPromUser.setBounds(X,Y+6*H,W,H);
		btnPromUser.setVisible(user.isManager);
		getContentPane().add(btnPromUser);
		btnPromUser.addActionListener(promote());
		
		JButton btnCheckReq = new JButton("Check Requests");
		btnCheckReq.setBounds(X,Y+8*H,W,H);
		btnCheckReq.setVisible(user.isManager);
		getContentPane().add(btnCheckReq);
		btnCheckReq.addActionListener(checkRequest());
		
		JButton btnAnalysis = new JButton("Get Anaylsis");
		btnAnalysis.setBounds(X,Y+10*H,W,H);
		btnAnalysis.setVisible(user.isManager);
		getContentPane().add(btnAnalysis);
		btnAnalysis.addActionListener(analysis());
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(X,Y+12*H,W,H);
		getContentPane().add(btnLogOut);
		btnLogOut.addActionListener(logout());
		
		this.setTitle("Main Menu");
		this.setSize(500, 360);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
 
	private ActionListener analysis() {
		// TODO Auto-generated method stub
		ActionListener l = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Analysis();
			}
		};
		return l;
	}


	private ActionListener buyBook(){
		ActionListener l = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Search(db);
			}
		};
		return l;
	}
	private ActionListener addBook(){
		ActionListener l = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AddBook(db);
			}
		};
		return l;
	}
	private ActionListener checkRequest(){
		ActionListener l = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new CheckRequests();
			}
		};
		return l;
	}
	private ActionListener profile (User user){
		ActionListener l = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SignIn(db,user);
				dispose();
			}
		};
		return l;
	}
	private ActionListener promote(){
		ActionListener l = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String username =JOptionPane.showInputDialog("User Name of the Candidate");
				db.promoteUser(username);
			}
		};
		return l;
	}
	private ActionListener logout(){
		ActionListener l = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Entry();
				dispose();
			}
		};
		return l;
	}
	public static void main (String[] args){
		
	}
	
}
