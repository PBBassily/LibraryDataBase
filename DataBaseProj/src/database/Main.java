package database ;
// Adapted from http://www.vogella.com/tutorials/MySQLJava/article.html
public class Main {
  public static void main(String[] args) throws Exception {
    MySQLAccess db = new MySQLAccess();
    db.initDataBase();
    db.addBook(9, "Opa","Science" , 20, 2017, 200, 50, "Ahmed Helmi");
    //db.issueOrder(2);
    //db.updateBook(1, 10);
//    db.updateBook(11111, 2000);
//    db.confirmOrder(11111);
//    db.searchISBN(0);
//    db.searchISBN(11111);
//    db.searchISBN(-1);
    //db.confirmOrder(1);
    
//    db.promoteUser("PBB12");
    //db.updateBook(1, -1);
    
    
    db.close();
  }

}