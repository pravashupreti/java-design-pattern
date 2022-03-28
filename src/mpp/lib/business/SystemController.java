package mpp.lib.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mpp.lib.dataaccess.Auth;
import mpp.lib.dataaccess.DataAccess;
import mpp.lib.dataaccess.DataAccessFacade;
import mpp.lib.dataaccess.User;

public class SystemController implements ControllerInterface {
	
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	
	@Override
	public String getNextMemberID() {
		DataAccess da = new DataAccessFacade();
		return String.valueOf(da.readMemberMap().size() + 1);
	}
	
	@Override
	public List<User> getUsers() {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> userMap = da.readUserMap();
		return new ArrayList<User>(userMap.values());
	}
	
	@Override
	public List<LibraryMember> getMembers() {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> memberMap = da.readMemberMap();
		return new ArrayList<LibraryMember>(memberMap.values());
	}
	
	@Override
	public List<Author> getAuthors() {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Author> authorMap = da.readAuthorMap();
		return new ArrayList<Author>(authorMap.values());
	}
	
	@Override
	public void checkoutBooks(String memberID, String[] isbns) throws RuntimeException {
		
		DataAccess da = new DataAccessFacade();
		LibraryMember member = da.readMemberMap().get(memberID);
		if (member == null)
			throw new RuntimeException("Library member is not exist");
		HashMap<String, Book> mapBook = da.readBookMap();
		for (String isbn: isbns) {
			if (!mapBook.containsKey(isbn)) {
				throw new RuntimeException(String.format("Book ISBN [%s] is not existed.", isbn));
			} else {
				Book book = mapBook.get(isbn);
				BookCopy bookCopy = book.getNextAvailableCopy();
				if (null == bookCopy) {
					throw new RuntimeException(String.format("Book ISBN [%s] is not available for now", isbn));
				}
			}
		}
		
		CheckoutRecord rc = CheckoutRecordFactory.createCheckoutRecord();
		for (String isbn: isbns) {
			Book book = mapBook.get(isbn);
			BookCopy bookCopy = book.getNextAvailableCopy();
			
			CheckoutRecordFactory.addCheckoutEntry(rc, book, bookCopy);
			bookCopy.changeAvailability();
			book.updateCopies(bookCopy);
			
			da.saveBook(book);
		}
		
		member.addRecord(rc);
		da.saveMember(member);
	}
	
	@Override
	public void addMember(LibraryMember libMember) {
		DataAccess da = new DataAccessFacade();
		da.saveMember(libMember);
	}
	
	@Override
	public void addBook(Book book) throws RuntimeException {
		Book existedBook = getBook(book.getIsbn());
		if (null != existedBook) {
			throw new RuntimeException("This book is already existed");
		}
		DataAccess da = new DataAccessFacade();
		da.saveBook(book);
	}
	
	@Override
	public LibraryMember getLibraryMember(String memberID) {
		DataAccess da = new DataAccessFacade();
		return da.readMemberMap().get(memberID);
	}
	
	@Override
	public Book getBook(String isbn) {
		DataAccess da = new DataAccessFacade();
		return da.readBookMap().get(isbn);
	}
	
	@Override
	public void addBookCopy(String isbn, int numOfCopies) throws RuntimeException {
		DataAccess da = new DataAccessFacade();
		da.addBookCopy(isbn, numOfCopies);
	}
	
	@Override
	public List<RecordData> getCheckoutRecords(String memberID) throws RuntimeException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> mapMember = da.readMemberMap();
		LibraryMember member = mapMember.get(memberID);
		if (null == member) {
			throw new RuntimeException(String.format("Member [%s] is not existed", memberID));
		}
		List<RecordData> result = new ArrayList<>();
		List<CheckoutRecord> checkoutRecords = member.getCheckoutRecords();
		for (CheckoutRecord record: checkoutRecords) {
			List<CheckoutEntry> entries = record.getEntries();
			for (CheckoutEntry entry: entries) {
				Book book = entry.getCopy().getBook();
				String bookTitle = book.getTitle();
				String bookIsbn = book.getIsbn();
				BookCopy bookCopy = entry.getCopy();
				int copyNum = bookCopy.getCopyNum();
				LocalDate checkoutDate = entry.getCheckoutDate();
				LocalDate dueDate = entry.getDueDate();
				
				RecordData data = new RecordData(bookTitle, bookIsbn, copyNum, checkoutDate, dueDate);
				result.add(data);
			}
		}
		return result;
	}
	
	@Override
	public List<OverdueData> getOverdueRecords(String isbn) throws RuntimeException {
		
		List<OverdueData> result = new ArrayList<>();
		
		Book book = getBook(isbn);
		if (null == book) {
			throw new RuntimeException(String.format("Book ISBN [%s] is not existed.", isbn));
		}
		LocalDate today = LocalDate.now();
		List<LibraryMember> members = getMembers();
		for (LibraryMember member: members) {
			List<CheckoutRecord> checkoutRecords = member.getCheckoutRecords();
			for (CheckoutRecord checkoutRecord: checkoutRecords) {
				List<CheckoutEntry> entries = checkoutRecord.getEntries();
				for (CheckoutEntry entry: entries) {
					BookCopy bookCopy = entry.getCopy();
					if (entry.getDueDate().isBefore(today) && !bookCopy.isAvailable() 
							&& bookCopy.getBook().getIsbn().equals(isbn)) {
						String bookTitle = book.getTitle();
						int copyNumber = bookCopy.getCopyNum();
						String memberID = member.getMemberId();
						LocalDate checkoutDate = entry.getCheckoutDate();
						LocalDate dueDate = entry.getDueDate();
						
						OverdueData data = new OverdueData(bookTitle, copyNumber, memberID, checkoutDate, dueDate);
						result.add(data);
					}
				}
			}
		}
		
		return result;
	}
}
