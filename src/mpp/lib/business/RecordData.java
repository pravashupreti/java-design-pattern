package mpp.lib.business;

import java.time.LocalDate;

public class RecordData {
	
	private String bookTitle;
	
	private String bookIsbn;
	
	private int copyNum;
	
	private LocalDate checkoutDate;
	
	private LocalDate dueDate;
	
	public RecordData(String bookTitle, String bookIsbn, int copyNum,
			LocalDate checkoutDate, LocalDate dueDate) {
		this.bookTitle = bookTitle;
		this.bookIsbn = bookIsbn;
		this.copyNum = copyNum;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getBookIsbn() {
		return bookIsbn;
	}

	public int getCopyNum() {
		return copyNum;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
}
