package model;

public class Book {

	public int ISBN ;
	public String title ;
	public String publisher ;
	public float price;
	public int wantedQuantity;
	
	public Book(int ISBN, String title, String publisher, float price) {
		this.ISBN = ISBN;
		this.title = title;
		this.publisher = publisher;
		this.price = price;
		
	}
	
}
