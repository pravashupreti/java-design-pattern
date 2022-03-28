package mpp.lib.dataaccess;

import java.util.HashMap;

import mpp.lib.business.Author;
import mpp.lib.business.Book;
import mpp.lib.business.LibraryMember;

public interface DataAccess {
	
	HashMap<String,Book> readBookMap();
	
	HashMap<String,User> readUserMap();
	
	HashMap<String,Author> readAuthorMap();
	
	HashMap<String, LibraryMember> readMemberMap();
	
	void saveMember(LibraryMember member);
	
	void saveBook(Book book);
	
	void addBookCopy(String isbn, int numOfCopy) throws RuntimeException;
	
	void updateCheckout(Book book);
}
