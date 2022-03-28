package mpp.lib.business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
	
	private static final long serialVersionUID = -7165256093033634861L;

	private List<CheckoutEntry> entries = new ArrayList<CheckoutEntry>();
	
	void addCheckoutEntry(BookCopy copy, LocalDate checkoutDate, LocalDate dueDate) {
		this.entries.add(new CheckoutEntry(copy, checkoutDate, dueDate ));
	}
	
	public List<CheckoutEntry> getEntries() {
		return this.entries;
	}

	@Override
	public String toString() {
		return "CheckoutRecord [entries=" + entries + "]";
	}
}
