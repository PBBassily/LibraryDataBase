package database ;
import java.util.ArrayList;
import java.util.Vector;

import model.*;

public interface IMySQLAccess {
	
	void addBook(int ISBN , String title ,String category, int quantity, int publicationYear, float price,int threshold,String publisherName );
	void updateBook(int ISBN , int quantity);
	void issueOrder(int ISBN);
	void confirmOrder(int ISBN);
	void addUser(String userName , String Email , String pass , String FName , String LName , String shipAddr, String Tel,boolean isManager);
	void delUser(String userName);
	void promoteUser(String userName);
	
	User logUser(String email,String pass);
	User buildUser();
	
	ArrayList<Book> searchISBN(int ISBN);
	ArrayList<Book> searchTitle(String string);
	ArrayList<Book> searchCategory(String string);
	ArrayList<Book> searchAuthor(String string);
	ArrayList<Book> searchPublisher(String string);
	ArrayList<Book> buildBooks();
	
	Vector<String> getPublishers();
	
	// todo ArrayList<Request> getAllRequests();
	void close();

}
