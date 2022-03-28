package mpp.lib.business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntry implements Serializable {

	private static final long serialVersionUID = 7373453283246629629L;

	private BookCopy copy;
	
	private LocalDate checkoutDate, dueDate;
	
	CheckoutEntry(BookCopy copy, LocalDate checkoutDate, LocalDate dueDate) {
		this.copy = copy;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
	}
	
	public BookCopy getCopy() {
		return this.copy;
	}
	
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	@Override
	public String toString() {
		return "CheckoutEntry [copy=" + copy.getCopyNum() + ", checkoutDate=" + checkoutDate + ", dueDate=" + dueDate + "]";
	}
}
