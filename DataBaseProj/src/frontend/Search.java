package frontend;

import javax.swing.JFrame;
import javax.swing.JTextField;

import database.IMySQLAccess;
import database.MySQLAccess;
import model.Book;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

public class Search extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4762029657255132917L;
	private final int X = 10, Y = 35, W = 90, H = 20, DISP = 90;
	private IMySQLAccess db;
	private ArrayList<JCheckBox> boxesList;
	private JTextField textField;
	private ButtonGroup radioList;
	private JRadioButtonMenuItem isISBN, isPub, isCat, isAuthor, isTitle;
	private JPanel boxPanel;
	private JScrollPane sc;
	private HashMap<JCheckBox,Book> map ;
	private ArrayList<Book> bookList = new ArrayList<Book>();

	public Search(IMySQLAccess db) {
		
		this.db = db;
		radioList = new ButtonGroup();
		boxesList = new ArrayList<JCheckBox>();

		getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 10, 370, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		isISBN = new JRadioButtonMenuItem("ISBN");
		isISBN.setBounds(X, Y, W, H);
		radioList.add(isISBN);
		getContentPane().add(isISBN);

		isAuthor = new JRadioButtonMenuItem("Auther");
		isAuthor.setBounds(X + DISP, Y, W, H);
		radioList.add(isAuthor);
		getContentPane().add(isAuthor);

		isTitle = new JRadioButtonMenuItem("Title");
		isTitle.setBounds(X + 2 * DISP, Y, W, H);
		radioList.add(isTitle);
		getContentPane().add(isTitle);

		isCat = new JRadioButtonMenuItem("Category");
		isCat.setBounds(X + 3 * DISP, Y, W, H);
		radioList.add(isCat);
		getContentPane().add(isCat);

		isPub = new JRadioButtonMenuItem("Publisher");
		isPub.setBounds(X + 4 * DISP, Y, W, H);
		radioList.add(isPub);
		getContentPane().add(isPub);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(390, 10, 100, 20);
		btnNewButton.addActionListener(searchBtn());
		getContentPane().add(btnNewButton);
		
		JButton btn = new JButton("Check Out");
		btn.setBounds(200, 300, 120, 30);
		getContentPane().add(btn);
		btn.addActionListener(checkout());

		this.setTitle("Search For Books");
		this.setSize(500, 350);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	private ActionListener searchBtn() {
		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("search");
				if (isISBN.isSelected())
					bookList = db.searchISBN(Integer.parseInt(textField.getText()));
				else if (isCat.isSelected())
					bookList = db.searchCategory(textField.getText());
				else if (isAuthor.isSelected())
					bookList = db.searchAuthor(textField.getText());
				else if (isTitle.isSelected())
					bookList = db.searchTitle(textField.getText());
				else if (isPub.isSelected())
					bookList = db.searchPublisher(textField.getText());
				else
					JOptionPane.showMessageDialog(rootPane, "Select Ya ...");

				if (bookList == null) {
					JOptionPane.showMessageDialog(rootPane, "No such book !!");
					textField.setText("");
				} else {
					try {
						getContentPane().remove(sc);
					} catch (Exception e2) {
					}
					boxesList=new ArrayList<JCheckBox>();
					map = new HashMap<JCheckBox,Book>();
					boxPanel = new JPanel();
					boxPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					JCheckBox box;
					int i = 1;
					for (Book item : bookList) {
						box = new JCheckBox(item.title + " " + item.price + " $\n");
						box.addActionListener(onClickCheckBox(item));
						boxPanel.add(box);
						boxPanel.setPreferredSize(new Dimension(460, Math.max(220, i++ * 40)));
						map.put(box, item);
						boxesList.add(box);
					}
					boxPanel.setLayout(new GridLayout(0, 1, 30, 10));
					sc = new JScrollPane(boxPanel);
					sc.setSize(new Dimension(490, 230));
					sc.setLocation(7, 60);
					getContentPane().add(sc);
					getContentPane().revalidate();
					getContentPane().repaint();
				}
			}

			private ActionListener onClickCheckBox(Book item) {

				ActionListener l = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String quantity =JOptionPane.showInputDialog("Quantity");
						item.wantedQuantity=Integer.parseInt(quantity);
					}
				};
				return l;
			}
		};
		return l;
	}
	
	private ActionListener checkout(){
		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Book>confiremdBooks = new ArrayList<Book>();
				System.out.println("Selected books");
				for(JCheckBox box : boxesList){
					if(box.isSelected()){
						Book book =map.get(box);
						System.out.println(book.title+" "+book.wantedQuantity);
						confiremdBooks.add(book);
					}
					
				}
				
				// TODO call CHECKOUT class here !!
				// Take confiremed Books as input to your class
				// each book has wantedQuantity attribute , to get wanted copies
			}
		};
		return l;
	}
	public static void main(String[] args) {
		new Search(new MySQLAccess());
	}
}
