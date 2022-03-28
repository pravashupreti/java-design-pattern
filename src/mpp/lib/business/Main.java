package mpp.lib.business;

import java.util.*;

import mpp.lib.dataaccess.DataAccess;
import mpp.lib.dataaccess.DataAccessFacade;

public class Main {

	public static void main(String[] args) {
//		System.out.println(allWhoseZipContains3());
//		System.out.println(allHavingOverdueBook());
//		System.out.println(allHavingMultipleAuthors());
		
		//System.out.println();
		
		printAllAuthors();

	}
	
	public static void printAllAuthors() {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Author> authorMap = da.readAuthorMap();
		for (Author auth: authorMap.values()) {
			System.out.println(auth.toString());
		}
	}
	
	//Returns a list of all ids of LibraryMembers whose zipcode contains the digit 3
	public static List<String> allWhoseZipContains3() {
		DataAccess da = new DataAccessFacade();
		Collection<LibraryMember> members = da.readMemberMap().values();
		List<LibraryMember> mems = new ArrayList<>();
		mems.addAll(members);
		//implement
		return null;
		
	}
	//Returns a list of all ids of  LibraryMembers that have an overdue book
	public static List<String> allHavingOverdueBook() {
		DataAccess da = new DataAccessFacade();
		Collection<LibraryMember> members = da.readMemberMap().values();
		List<LibraryMember> mems = new ArrayList<>();
		mems.addAll(members);
		//implement
		return null;
		
	}
	
	//Returns a list of all isbns of  Books that have multiple authors
	public static List<String> allHavingMultipleAuthors() {
		DataAccess da = new DataAccessFacade();
		Collection<Book> books = da.readBookMap().values();
		List<Book> bs = new ArrayList<>();
		bs.addAll(books);
		//implement
		return null;
		
		}

}
