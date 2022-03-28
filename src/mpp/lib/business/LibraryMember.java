package mpp.lib.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

final public class LibraryMember extends Person implements Serializable {
	
	private String memberId;
	
	private List<CheckoutRecord> checkoutRecords = new ArrayList<>();
	
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;		
	}
	
	public String getMemberId() {
		return memberId;
	}
	
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}

	public List<CheckoutRecord> getCheckoutRecords() {
		return this.checkoutRecords;
	}
	
	public void addRecord(CheckoutRecord checkoutRecord) {
		checkoutRecords.add(checkoutRecord);
	}
	private static final long serialVersionUID = -2226197306790714013L;
}
