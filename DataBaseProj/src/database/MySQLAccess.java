package database ;
// Adapted from http://www.vogella.com/tutorials/MySQLJava/article.html
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import model.*;

public class MySQLAccess implements IMySQLAccess {

	private final String DATABASE_NAME = "ProjectERD";
	private final int BOOK_ATTR_NUM = 8 ;
	//private final int BOOK_ORDER_SUPPLY = 100 ;
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	final private String host = "localhost/phpmyadmin";
	final private String user = "root";
	final private String passwd = "123456";
	
	public MySQLAccess() {
		// TODO Auto-generated constructor stub
		initDataBase();
	}
	public void initDataBase() {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "?" + "user=" + user + "&password=" + passwd);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
		} catch (Exception e) {
			System.out.println("Cant connect");
		} 
	}

	private void writeMetaData(ResultSet resultSet) throws SQLException {
		// Now get some metadata from the database
		// Result set get the result of the SQL query

		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String user = resultSet.getString("myuser");
			String website = resultSet.getString("webpage");
			String summary = resultSet.getString("summary");
			Date date = resultSet.getDate("datum");
			String comment = resultSet.getString("comments");
			System.out.println("User: " + user);
			System.out.println("Website: " + website);
			System.out.println("Summary: " + summary);
			System.out.println("Date: " + date);
			System.out.println("Comment: " + comment);
		}
	}

	// You need to close the resultSet
	@Override
	public
	void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
	@Override
	public void addBook(int ISBN, String title, String category, int quantity, int publicationYear, float price,
			int threshold, String publisherName) {
		String query = "INSERT INTO "+DATABASE_NAME+".BOOK VALUES (";
		query +=  ISBN+",'"+title+"',"+quantity+","+publicationYear+","+
				price+","+threshold+",'"+publisherName+"','"+category+"');";
		System.out.println(query);
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			///e.printStackTrace();
			System.out.println("Invalid Query");
		}
	}
	@Override
	public void updateBook(int ISBN, int quantity) {
		String query = "UPDATE "+DATABASE_NAME+".BOOK "
				+ "SET QUANTITY = " +quantity+" WHERE ISBN = "+ ISBN ;
		System.out.println(query);
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Inavlid Selling Operation !!");
		}
		
	}
	@Override
	public void issueOrder(int ISBN) {

		String query = "UPDATE "+DATABASE_NAME+".ORDER SET Confirmed = 1 WHERE ORDER.BOOK_ISBN = " + ISBN +" ;" ;
		System.out.println(query);
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Invalid Query");
		}
		
	}
	@Override
	public void confirmOrder(int ISBN) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM "+DATABASE_NAME+".ORDER WHERE ORDER.BOOK_ISBN = "+ISBN+";" ;
		System.out.println(query);
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Invalid Query");
		}
	
		
	}
	@Override
	public ArrayList<Book> searchISBN(int ISBN) {
		
		try {
			resultSet = statement.executeQuery("select * from " + DATABASE_NAME + ".BOOK WHERE ISBN = " + ISBN);
			if(!resultSet.isBeforeFirst()){
				System.out.println("No such ISBN : "+ISBN);
				return null;
			}
			
			return buildBooks();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return null;
	}
	@Override
	public ArrayList<Book> searchTitle(String string) {
		try {
			resultSet = statement.executeQuery("select * from " + DATABASE_NAME + ".BOOK WHERE Title = \'" + string + "\'");
			if(!resultSet.isBeforeFirst()){
				System.out.println("No such Title : "+string);
				return null;
			}
			
			return buildBooks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public ArrayList<Book> searchCategory(String string) {
		try {
			resultSet = statement.executeQuery("select * from " + DATABASE_NAME + ".BOOK WHERE DOMAIN_Category = \'" + string + "\'");
			if(!resultSet.isBeforeFirst()){
				System.out.println("No such Category : "+string);
				return null;
			}
			return buildBooks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public ArrayList<Book> searchAuthor(String string) {
		try {
			resultSet = statement.executeQuery("select * from " + DATABASE_NAME + ".BOOK WHERE Author = \'" + string + "\'");
			if(!resultSet.isBeforeFirst()){
				System.out.println("No such Author : "+string);
				return null;
			}
			
			return buildBooks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public ArrayList<Book> searchPublisher(String string) {
		try {
			resultSet = statement.executeQuery("select * from " + DATABASE_NAME + ".BOOK WHERE Publisher_Publisher_name = \'" + string + "\'");
			if(!resultSet.isBeforeFirst()){
				System.out.println("No such Publisher : " + string );
				return null;
			}
			return buildBooks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return null;
	}
	@Override
	public void addUser(String userName, String email, String pass, String fName, String lName, String shipAddr,
			String tel , boolean isManager) {
		String query = "INSERT INTO "+DATABASE_NAME+".USER VALUES (";
		query += "'"+userName+"','"+email+"','"+pass+"','"+fName+"','"+
				lName+"','"+shipAddr+"','"+tel+"', "+ isManager+"  );";
		System.out.println(query);
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			///e.printStackTrace();
			System.out.println("Invalid Query");
		}		
	}
	@Override
	public void promoteUser(String userName) {
		// TODO Auto-generated method stub
		String query = "UPDATE "+DATABASE_NAME+".USER SET M_state = 1 WHERE USER.Username = '" + userName +"' ;" ;
		System.out.println(query);
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Invalid Query");
		}
		
	}
	@Override
	public User logUser(String username, String pass) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM "+DATABASE_NAME+".USER  WHERE USER.Username = '" + username +"' and USER.Password = '" + pass +"' ;" ;
		System.out.println(query);
		try {
			resultSet = statement.executeQuery(query);
			return resultSet.isBeforeFirst()?buildUser():null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Invalid Query");
		}
		return null;
	}
	@Override
	public User buildUser() {
		// TODO Auto-generated method stub
		User user= null;
		try {
			resultSet.next();
			user = new User(resultSet.getString("FName"),resultSet.getString("Username"),
					resultSet.getBoolean("M_state"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public ArrayList<Book> buildBooks() {
		ArrayList<Book> list = new ArrayList<Book>();
		Book book = null;
		try {
			while (resultSet.next()) {              
			    book = new Book(resultSet.getInt("ISBN"), resultSet.getString("Title"), 
			    		resultSet.getString("Publisher_Publisher_name"), resultSet.getFloat("Price"));
			    list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return list;
	}
	@Override
	public void delUser(String userName) {
		String query = "DELETE FROM "+DATABASE_NAME+".USER WHERE USER.Username = '"+userName+"' ;" ;
		System.out.println(query);
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Invalid Query");
		}
	}
	@Override
	public Vector<String> getPublishers() {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM "+DATABASE_NAME+".PUBLISHER "+" ;" ;
		Vector<String> list = new Vector<String>();
		System.out.println(query);
		try {
			resultSet=statement.executeQuery(query);
			while(resultSet.next())
				list.add(resultSet.getString("Publisher_name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Invalid Query");
		}
		return list;
	}
	

}