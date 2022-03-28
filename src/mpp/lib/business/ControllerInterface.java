package mpp.lib.business;

import java.util.List;

import mpp.lib.dataaccess.User;

public interface ControllerInterface {
	
	void login(String id, String password) throws LoginException;
	
	List<User> getUsers();
	
	List<LibraryMember> getMembers();
	
	List<Author> getAuthors();
	
	String getNextMemberID();
	
	void addMember(LibraryMember libMember);
	
	void addBook(Book book) throws RuntimeException;
	
	LibraryMember getLibraryMember(String memberID);
	
	Book getBook(String isbn);
	
	void addBookCopy(String isbn, int numOfCopies) throws RuntimeException;
	
	void checkoutBooks(String memberID, String[] isbn) throws RuntimeException;
	
	List<RecordData> getCheckoutRecords(String memberID) throws RuntimeException;
	
	List<OverdueData> getOverdueRecords(String isbn) throws RuntimeException;
}
