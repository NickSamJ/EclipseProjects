package telran.util.controllers;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import telran.books.dto.Book;
import telran.books.dto.CoverType;
import telran.books.dto.Genre;
import telran.books.dto.LiteratureBook;
import telran.books.dto.TechnicalBook;

public class RandomCreationAppl {
	private static final long N_BOOKS = 100;
	private static final int PROB_LITERATURE_BOOK = 80;
	private static final int MAX_SUBJECTS = 7;
	private static final int N_AUTHORS = 15;
	private static final int MIN_PAGES = 50;
	private static final int MAX_PAGES = 1500;
	private static final int N_PUBLISHERS = 3;
	private static final int MIN_YEAR = 0;
	private static final int MAX_YEAR = 0;

	public static void main(String[] args) {
		try (ObjectOutputStream output = 
				new ObjectOutputStream(new FileOutputStream("Books.data"))){
			List<Book> books = gerRandomBooks();
			output.writeObject(books);
		} catch (Exception e) {
			
	
		}
	}

	private static List<Book> gerRandomBooks() {
		
		return Stream.generate(RandomCreationAppl::getRandomBook)
					.distinct()
					.limit(N_BOOKS)
					.collect(Collectors.toList());
	}

	public static Book getRandomBook() {

		return getChance() <= PROB_LITERATURE_BOOK ? getLiteratureBook() : getTechnicalBook();  
	}

	private static int getChance() {
		
		return getRandomNumber(0, 100);
	}

	private static int getRandomNumber(int min, int max) {
		
		return (int) (min  + Math.random()*(max - min));
	}

	private static Book getTechnicalBook() {
		Book book = getBook();
		String subject = "subject" + getRandomNumber(1, MAX_SUBJECTS);
		return getTechnicalBook(book, subject);
	}

	private static Book getTechnicalBook(Book book, String subject) {
		
		return new TechnicalBook(book.getIsbn(), 
				book.getTitle(), 
				book.getAuthor(), 
				book.getCover(), 
				book.getPages(), 
				book.getPublisher(), 
				book.getPublishDate());
	}

	private static Book getBook() {
		
		long isbn = getRandomNumber(100000, 10000000);
		String title = "title" + isbn;
		String author = "author" + getRandomNumber(1, N_AUTHORS);
		CoverType cover = getCover();
		int pages = getRanomNumber(MIN_PAGES, MAX_PAGES);
		String publisher = "publisher" + getRandomNumber(1, N_PUBLISHERS);
		LocalDate publishDate = getPublishDate();
		return new Book(isbn, title, author, cover, pages, publisher, publishDate);
	}

	private static LocalDate getPublishDate() {
		int year = getRandomNumber(MIN_YEAR, MAX_YEAR);
		int dayOfYear = getRandomNumber(1, 365);
		
		// TODO
		return LocalDate.ofearDat	;
	}

	private static CoverType getCover() {
		CoverType[] allCovers = CoverType.values();
		int index = getRandomNumber(0, allCovers.length);
		return allCovers[index];
	}

	private static Book getLiteratureBook() {
		
		Genre genre = getGenre();
		return getLiteratureBook(getBook(), genre);
	}

	private static Book getLiteratureBook(Book book, Genre genre) {
		return new LiteratureBook(book.getIsbn(), 
				book.getTitle(), 
				book.getAuthor(), 
				book.getCover(), 
				book.getPages(), 
				book.getPublisher(), 
				book.getPublishDate()
				);
	}

	private static Genre getGenre() {
		Genre[] allGenres = Genre.values();
		int index = getRandomNumber(0, allGenres.length)
		return allGenres[index];
	}
}
