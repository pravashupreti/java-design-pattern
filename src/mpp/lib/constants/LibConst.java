package mpp.lib.constants;

import java.awt.Color;

import mpp.lib.frontend.model.ListItem;

public class LibConst {
	
	public static final int FRAME_WIDTH           = 800;
	
	public static final int FRAME_HEIGHT          = 600;
	
	public static final String CMD_LOGIN          = "Login";
	
	public static final String CMD_LOGOUT         = "Logout";
	
	public static final String CMD_SAVE_MEMBER    = "Save_Member";
	
	public static final String CMD_ADD_BOOK       = "AddBook";

	public static final String CMD_ADD_BOOK_COPY  = "AddBookCopy";
	
	public static final String CMD_CHECK_OUT      = "Checkout";
	
	public static final String CMD_CHECK_OUT_RECORD = "CheckoutRecord";
	
	public static final String CMD_OVERDUE_RECORD = "OverdueRecord";
	
	public static final String CMD_EXIT           = "Exit";
	
	public static final String APP_TITLE         = ".:: Library System ::.";
	public static final String WELCOME_MESSAGE   = "Welcome to the Library System !";
	
	public static final String FUNC_DEFAULT_NM         = "Default";
	public static final String FUNC_ADD_MEMBER_NM      = "Add Member";
	public static final String FUNC_ADD_BOOK_NM        = "Add Book";
	public static final String FUNC_ADD_BOOK_COPY_NM   = "Add Book Copy";
	public static final String FUNC_CHECKOUT_NM        = "Checkout";
	public static final String FUNC_CHECKOUT_RECORD_NM = "Checkout Records";
	public static final String FUNC_LIST_OVERDUE_NM    = "Overdue Checkouts";
	
	private static final ListItem FUNC_MEMBER          = new ListItem(FUNC_ADD_MEMBER_NM, true);
	private static final ListItem FUNC_ADD_BOOK        = new ListItem(FUNC_ADD_BOOK_NM, true);
	private static final ListItem FUNC_ADD_BOOK_COPY   = new ListItem(FUNC_ADD_BOOK_COPY_NM, true);
	private static final ListItem FUNC_CHECKOUT        = new ListItem(FUNC_CHECKOUT_NM, true);
	private static final ListItem FUNC_CHECKOUT_RECORD = new ListItem(FUNC_CHECKOUT_RECORD_NM, true);
	private static final ListItem FUNC_LIST_OVER_DUE   = new ListItem(FUNC_LIST_OVERDUE_NM, true);
	
	public static final ListItem[] FUNC_ALL   = {
			FUNC_MEMBER, 
			FUNC_ADD_BOOK,
			FUNC_ADD_BOOK_COPY,
			FUNC_CHECKOUT,
			FUNC_CHECKOUT_RECORD,
			FUNC_LIST_OVER_DUE
	};
	
	public static final ListItem[] FUNC_ADMINS      = { FUNC_MEMBER, FUNC_ADD_BOOK, FUNC_ADD_BOOK_COPY};
	public static final ListItem[] FUNC_LIBRARIANS  = { FUNC_CHECKOUT, FUNC_CHECKOUT_RECORD, FUNC_LIST_OVER_DUE};
	
	public static final Color DARK_BLUE = Color.BLUE.darker();
	public static final Color ERROR_MESSAGE_COLOR = Color.RED.darker(); //dark red
	public static final Color INFO_MESSAGE_COLOR = new Color(24, 98, 19); //dark green
	public static final Color LINK_AVAILABLE = DARK_BLUE;
	public static final Color LINK_NOT_AVAILABLE = Color.gray;
	
}
