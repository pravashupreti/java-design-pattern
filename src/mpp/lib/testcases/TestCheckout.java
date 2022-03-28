package mpp.lib.testcases;

import java.util.HashMap;
import java.util.List;

import mpp.lib.business.Book;
import mpp.lib.business.BookCopy;
import mpp.lib.business.CheckoutEntry;
import mpp.lib.business.CheckoutRecord;
import mpp.lib.business.LibraryMember;
import mpp.lib.dataaccess.DataAccess;
import mpp.lib.dataaccess.DataAccessFacade;

public class TestCheckout {
	
	public static void printCheckout(String memberId) throws Exception {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> mapMember = da.readMemberMap();
		
		LibraryMember libm = mapMember.get(memberId);
		if (null == libm) {
			throw new Exception(String.format("Member is not existed", libm));
		}
		List<CheckoutRecord> chList = libm.getCheckoutRecords();
		// System.out.println(" chsize: "+ chList.size());
		
		for(CheckoutRecord rec: chList) {
			for(CheckoutEntry entry: rec.getEntries()) {
				BookCopy copy = entry.getCopy();
				Book book = copy.getBook();
				// book name, book isbn , copy number, checkout Date, Due date
				System.out.println("isbn: " + book.getIsbn() + " name: " + book.getTitle() +
						" copy number: " + book.getNumCopies() + " checkout Date: " + entry.getCheckoutDate() + " due date: "+ entry.getDueDate());
			}
		}
		
	}
	
	public static void main(String[] args) {
		try {
			printCheckout("1001");
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}
}
