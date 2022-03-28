package mpp.lib.business;

import java.time.LocalDate;

public class OverdueData {
	
	private String bookTitle;
	
	private int copyNum;
	
	private String memberID;
	
	private LocalDate checkoutDate;
	
	private LocalDate dueDate;
	
	public OverdueData(String bookTitle, int copyNum, String memberID, 
			LocalDate checkoutDate, LocalDate dueDate) {
		
		this.bookTitle = bookTitle;
		this.copyNum = copyNum;
		this.memberID = memberID;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public int getCopyNum() {
		return copyNum;
	}

	public String getMemberID() {
		return memberID;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
	
}
