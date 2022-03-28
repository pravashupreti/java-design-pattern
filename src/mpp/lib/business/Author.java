package mpp.lib.business;

import java.io.Serializable;

final public class Author extends Person implements Serializable {

	private static final long serialVersionUID = 7508481940058530471L;

	private String authorID;

	private String bio;

	public String getBio() {
		return bio;
	}

	public Author(String authorID, String firstNAme, String lastName, String phone, Address addr, String bio) {
		super(firstNAme, lastName, phone, addr);
		this.bio = bio;
		this.authorID = authorID;
	}

	public String getAuthorID() {
		return this.authorID;
	}

	@Override
	public String toString() {
		return authorID + " | " + getFirstName() + " " + getLastName();
	}

}
