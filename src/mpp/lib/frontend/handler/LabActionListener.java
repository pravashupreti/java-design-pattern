package mpp.lib.frontend.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mpp.lib.constants.LibConst;
import mpp.lib.frontend.ui.MainFrame;

public class LabActionListener implements ActionListener {

	private MainFrame parent;

	public LabActionListener(MainFrame mainFrame) {
		this.parent = mainFrame;
	}

	public void actionPerformed(ActionEvent e) {

		String actCommand = e.getActionCommand();

		if (LibConst.CMD_LOGIN.equals(actCommand)) {
			parent.login();
		} else if (LibConst.CMD_ADD_BOOK.equals(actCommand)) {
			parent.addBook();
		} else if (LibConst.CMD_EXIT.equals(actCommand)) {
			System.exit(0);
		} else if (LibConst.CMD_LOGOUT.equals(actCommand)) {
			parent.logout();
		} else if (LibConst.CMD_SAVE_MEMBER.equals(actCommand)) {
			parent.addMember();
		} else if (LibConst.CMD_CHECK_OUT_RECORD.equals(actCommand)) {
			parent.getCheckoutRecords();
		} else if (LibConst.CMD_OVERDUE_RECORD.equals(actCommand)) {
			parent.getOverdueRecords();
		} else if (LibConst.CMD_ADD_BOOK_COPY.equals(actCommand)) {
			parent.addBookCopy();
		} else if (LibConst.CMD_CHECK_OUT.equals(actCommand)) {
			parent.checkout();
		} 
	}
}
