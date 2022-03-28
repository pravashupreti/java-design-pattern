package mpp.lib.business;

import java.time.LocalDate;

public class CheckoutRecordFactory {
	public static CheckoutRecord createCheckoutRecord() {
		return new CheckoutRecord();
	}
	
	public static void addCheckoutEntry(CheckoutRecord checkoutRecord, Book book, BookCopy copy) {
		LocalDate now = LocalDate.now();
		checkoutRecord.addCheckoutEntry(copy, now, now.plusDays(book.getMaxCheckoutLength()));
	}

}
