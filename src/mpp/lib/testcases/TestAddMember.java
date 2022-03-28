package mpp.lib.testcases;

import java.util.HashMap;

import mpp.lib.business.LibraryMember;
import mpp.lib.dataaccess.DataAccess;
import mpp.lib.dataaccess.DataAccessFacade;

public class TestAddMember {
	
	public static void printMembersCount() throws Exception {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> mapMember = da.readMemberMap();
		System.out.println("Number of members: " + mapMember.size());
	}
	public static void printMemberById(String memberId) throws Exception {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> mapMember = da.readMemberMap();
		
		LibraryMember libm = mapMember.get(memberId);
		
		if (null == libm) {
			throw new Exception(String.format("Member [%s] is not existed", libm));
		}
		
		System.out.println("Member data: " + libm);
	}	
	
	public static void main(String[] args) {
		try {
			printMemberById("5");
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}
}
