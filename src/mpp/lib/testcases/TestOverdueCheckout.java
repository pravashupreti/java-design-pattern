package mpp.lib.testcases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mpp.lib.business.Book;
import mpp.lib.business.BookCopy;
import mpp.lib.business.CheckoutEntry;
import mpp.lib.business.CheckoutRecord;
import mpp.lib.business.ControllerInterface;
import mpp.lib.business.LibraryMember;
import mpp.lib.business.OverdueData;
import mpp.lib.business.SystemController;
import mpp.lib.dataaccess.DataAccess;
import mpp.lib.dataaccess.DataAccessFacade;

public class TestOverdueCheckout {
	
	public static void testOverdueCheckout(String isbn) {
		try {
			DataAccess da = new DataAccessFacade();
			List<OverdueData> result = new ArrayList<>();
			
			Book book = da.readBookMap().get(isbn);
			if (null == book) {
				throw new RuntimeException(String.format("Book ISBN [%s] is not existed.", isbn));
			}
			HashMap<String, LibraryMember> memberMap = da.readMemberMap();
			
			LocalDate today = LocalDate.now();
			List<LibraryMember> members = new ArrayList<LibraryMember>(memberMap.values());
			for (LibraryMember member: members) {
				List<CheckoutRecord> checkoutRecords = member.getCheckoutRecords();
				for (CheckoutRecord checkoutRecord: checkoutRecords) {
					List<CheckoutEntry> entries = checkoutRecord.getEntries();
					for (CheckoutEntry entry: entries) {
						BookCopy bookCopy = entry.getCopy();
						if (entry.getDueDate().isBefore(today) && !bookCopy.isAvailable()) {
							String bookTitle = "";
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
			System.out.println("Overdue size: " + result.size());
			for (OverdueData odata: result) {
				System.out.println("Book Title"+ odata.getBookTitle() + " memberId: " + odata.getMemberID() 
				+ " copy number: " + odata.getCopyNum() + " Checkout date: " + odata.getCheckoutDate() + " Due date: " + odata.getDueDate());
			}
		} catch (Exception ex) {
			
		}
	}
	
	public static void main(String[] args) {
		testOverdueCheckout("1");
	}
}
