package telran.util.controllers;
import java.io.*;
import java.util.List;

import telran.books.dto.Book;
public class BooksRestoreAppl {
	public static void main(String[] args) throws Exception{
		try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("books.data"))){
			
			List<Book> books = (List<Book>) input.readObject();
			
			books.forEach(System.out::println);
			
		}
	}
}
