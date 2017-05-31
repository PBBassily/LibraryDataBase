package frontend;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import database.IMySQLAccess;
import database.MySQLAccess;
import model.User;

public class SignIn extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final int  LABELS_X = 30 , LABELS_Y=30,LABELS_W=130,LABELS_H=15;
	final int  TEXT_X = 210 , TEXT_Y=30,TEXT_W=250,TEXT_H=15;
	private JTextField textUserName,textEmail,textPass,textFName,textLName,textTel,textAddrs;
	private JButton btnSignIn ;
	private IMySQLAccess db ;
	
	public SignIn(IMySQLAccess db,User user) {
		this.db = db;
		getContentPane().setLayout(null);
		
		/////////////////////////////// Labels //////////////////////////////////
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(LABELS_X, LABELS_Y, LABELS_W, LABELS_H);
		getContentPane().add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(LABELS_X,LABELS_Y+2*LABELS_H,LABELS_W,LABELS_H);
		getContentPane().add(lblEmail);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setBounds(LABELS_X,LABELS_Y+4*LABELS_H,LABELS_W,LABELS_H);
		getContentPane().add(lblPass);
		
		JLabel lblFName = new JLabel("First Name");
		lblFName.setBounds(LABELS_X,LABELS_Y+6*LABELS_H,LABELS_W,LABELS_H);
		getContentPane().add(lblFName);
		
		JLabel lblLName = new JLabel("Last Name");
		lblLName.setBounds(LABELS_X,LABELS_Y+8*LABELS_H,LABELS_W,LABELS_H);
		getContentPane().add(lblLName);
		
		JLabel lblTel = new JLabel("Telephone");
		lblTel.setBounds(LABELS_X,LABELS_Y+10*LABELS_H,LABELS_W,LABELS_H);
		getContentPane().add(lblTel);
		
		JLabel lblAddrs = new JLabel("Shipping Address");
		lblAddrs.setBounds(LABELS_X,LABELS_Y+12*LABELS_H,LABELS_W,LABELS_H);
		getContentPane().add(lblAddrs);
		
		/////////////////////////////// Text Fields //////////////////////////////////
		
		textUserName = new JTextField();
		textUserName.setText(user!=null?user.userName:"");
		textUserName.setBounds(TEXT_X, TEXT_Y,TEXT_W, TEXT_H);
		getContentPane().add(textUserName);
		textUserName.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(TEXT_X, TEXT_Y+2*TEXT_H,TEXT_W, TEXT_H);
		getContentPane().add(textEmail);
		
		textPass = new JTextField();
		textPass.setBounds(TEXT_X, TEXT_Y+4*TEXT_H,TEXT_W, TEXT_H);
		getContentPane().add(textPass);
		textPass.setColumns(10);
		
		textFName = new JTextField();
		textFName.setText(user!=null?user.fName:"");
		textFName.setBounds(TEXT_X, TEXT_Y+6*TEXT_H,TEXT_W, TEXT_H);
		getContentPane().add(textFName);
		textFName.setColumns(10);
		
		textLName = new JTextField();
		textLName.setBounds(TEXT_X, TEXT_Y+8*TEXT_H,TEXT_W, TEXT_H);
		getContentPane().add(textLName);
		textLName.setColumns(10);
		
		textTel = new JTextField();
		textTel.setBounds(TEXT_X, TEXT_Y+10*TEXT_H,TEXT_W, TEXT_H);
		getContentPane().add(textTel);
		textTel.setColumns(10);
		
		textAddrs = new JTextField();
		textAddrs.setBounds(TEXT_X, TEXT_Y+12*TEXT_H,TEXT_W, TEXT_H);
		getContentPane().add(textAddrs);
		textAddrs.setColumns(10);
		
		btnSignIn = new JButton(user!=null?"Update":"Sign In");
		btnSignIn.setBounds(200, 260, 120, 25);
		getContentPane().add(btnSignIn);
		btnSignIn.addActionListener(addUser(user));
		
		this.setTitle("Sign In");
		this.setSize(500, 300);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	private ActionListener addUser(User user){
		ActionListener l = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(user!=null)
					db.delUser(user.userName);
				db.addUser(textUserName.getText(), textEmail.getText(), textPass.getText(), 
						textLName.getText(), textFName.getText(), textAddrs.getText(), textTel.getText(),user!=null?user.isManager:false);
				if(user!=null)
					new MainMenu(db, user);
				else 
					new Login(db);
				dispose();
			}
		};
		return l;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IMySQLAccess db = new MySQLAccess();
		//new SignIn(db,true);
	}
}
