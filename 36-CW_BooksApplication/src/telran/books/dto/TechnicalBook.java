package telran.books.dto;

import java.time.LocalDate;

public class TechnicalBook extends Book {

	public TechnicalBook() {
	}

	public TechnicalBook(long isbn, String title, String author, CoverType cover, int pages, String publisher,
			LocalDate publishDate) {
		super(isbn, title, author, cover, pages, publisher, publishDate);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	
	String subject;

	@Override
	public String toString() {
		return "TechnicalBook [subject=" + subject + ", isbn=" + isbn + ", title=" + title + ", author=" + author
				+ ", cover=" + cover + ", pages=" + pages + ", publisher=" + publisher + ", publishDate=" + publishDate
				+ "]";
	}
	

}
