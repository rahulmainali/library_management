package book_storing;

public class Book {
	String Name, PublisherName, PublishedDate, Status;
	int ID, Price;
	
	Book(int ID, String Name, String PublisherName, String PublishedDate, String Status2, int Price){
		this.ID=ID;
		this.Name=Name;
		this.PublisherName=PublisherName;
		this.PublishedDate=PublishedDate;
		this.Status=Status2;
		this.Price=Price;
	}

}
