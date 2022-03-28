package mpp.lib.testcases;

import java.util.HashMap;

import mpp.lib.business.Book;
import mpp.lib.dataaccess.DataAccess;
import mpp.lib.dataaccess.DataAccessFacade;

public class TestAddBook {

	public static void printBook(String isbn) throws Exception {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> mapBook = da.readBookMap();

		Book book = mapBook.get(isbn);
		if (null == book) {
			throw new Exception(String.format("Book [%s] is not existed", isbn));
		}

		System.out.println("Number of copies: " + book.getCopies().length);
	}

	public static void main(String[] args) {
		try {
			printBook("4");
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}
}
