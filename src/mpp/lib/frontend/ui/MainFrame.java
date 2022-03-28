package mpp.lib.frontend.ui;

import static mpp.lib.constants.LibConst.FRAME_HEIGHT;
import static mpp.lib.constants.LibConst.FRAME_WIDTH;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;

import mpp.lib.business.Author;
import mpp.lib.business.Book;
import mpp.lib.business.ControllerInterface;
import mpp.lib.business.LibraryMember;
import mpp.lib.business.OverdueData;
import mpp.lib.business.RecordData;
import mpp.lib.business.SystemController;
import mpp.lib.constants.LibConst;
import mpp.lib.dataaccess.Auth;
import mpp.lib.frontend.handler.LabActionListener;
import mpp.lib.frontend.handler.LabListSelectionListener;
import mpp.lib.frontend.handler.LabWindowListener;

public class MainFrame extends JFrame implements CommonContainer {

	private static final long serialVersionUID = -6246575445960727056L;

	private ActionListener actListener;
	private WindowListener windowListener = new LabWindowListener();
	private ListSelectionListener listSelListener = new LabListSelectionListener(this);

	private DefaultPanel pnDefault = new DefaultPanel();
	private StatusPanel pnStatus = new StatusPanel();
	// private ViewTitlePanel pnViewTitle = new ViewTitlePanel();
	private AddBookCopyPanel pnAddBookCopy;
	private AddMemberPanel pnAddMember;
	private AddBookPanel pnAddBook;
	private CheckoutPanel pnCheckout;
	private CheckoutRecordPanel pnCheckoutRecord;
	private OverdueCheckoutPanel pnOverdueCheckout;

	private JPanel cards = new JPanel(new CardLayout());

	private FunctionPanel pnFunction;

	private LoginDialog loginDialog;

	private ControllerInterface daController = new SystemController();

	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuSystem = new JMenu("System");;
	private JMenuItem menuItemLogout = new JMenuItem("Logout");
	private JMenuItem menuItemExit = new JMenuItem("Exit");

	public MainFrame() {
		initializeVariables();
		initializeUI();
		addListeners();
		displayUI();
	}

	@Override
	public void initializeVariables() {

		actListener = new LabActionListener(this);
		pnAddBook = new AddBookPanel(this);
		pnFunction = new FunctionPanel(this);
		loginDialog = new LoginDialog(this, true);
		pnAddBookCopy = new AddBookCopyPanel(this);
		pnAddMember = new AddMemberPanel(this);
		pnCheckout = new CheckoutPanel(this);
		pnCheckoutRecord = new CheckoutRecordPanel(this);
		pnOverdueCheckout = new OverdueCheckoutPanel(this);
		pnFunction.updateList(LibConst.FUNC_ALL);
	}

	@Override
	public void initializeUI() {

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLayout(new BorderLayout());
		setTitle(LibConst.APP_TITLE);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		this.setLocation(((width - FRAME_WIDTH) / 2), (height - FRAME_HEIGHT) / 3);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add data

		cards.add(pnDefault, LibConst.FUNC_DEFAULT_NM);
		cards.add(pnAddMember, LibConst.FUNC_ADD_MEMBER_NM);
		cards.add(pnAddBook, LibConst.FUNC_ADD_BOOK_NM);
		cards.add(pnAddBookCopy, LibConst.FUNC_ADD_BOOK_COPY_NM);
		cards.add(pnCheckout, LibConst.FUNC_CHECKOUT_NM);
		cards.add(pnCheckoutRecord, LibConst.FUNC_CHECKOUT_RECORD_NM);
		cards.add(pnOverdueCheckout, LibConst.FUNC_LIST_OVERDUE_NM);

		JSplitPane centerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnFunction, cards);
		centerSplitPane.setDividerLocation(140);

		pnStatus.setPreferredSize(new Dimension(FRAME_WIDTH, 100));
		pnStatus.setMessage(LibConst.WELCOME_MESSAGE);
		JSplitPane bottomSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, centerSplitPane, pnStatus);

		bottomSplitPane.setDividerLocation(FRAME_HEIGHT - 90);
		bottomSplitPane.setMaximumSize(new Dimension(FRAME_WIDTH, 100));
		bottomSplitPane.setMinimumSize(new Dimension(FRAME_WIDTH, 90));
		add(bottomSplitPane, BorderLayout.CENTER);

		menuSystem.add(menuItemLogout);
		menuSystem.add(menuItemExit);

		menuBar.add(menuSystem);

		menuItemLogout.setActionCommand(LibConst.CMD_LOGOUT);
		menuItemExit.setActionCommand(LibConst.CMD_EXIT);

		setJMenuBar(menuBar);
		setResizable(false);
	}

	@Override
	public void addListeners() {
		menuItemLogout.addActionListener(actListener);
		menuItemExit.addActionListener(actListener);
	}

	public void login() {
		try {
			loginDialog.checkRequiredFields();

			String userName = loginDialog.getUserName().toLowerCase();
			String pwd = loginDialog.getPassword();

			daController.login(userName, pwd);

			if (Auth.ADMIN == SystemController.currentAuth) {
				pnFunction.updateList(LibConst.FUNC_ADMINS);
			} else if (Auth.LIBRARIAN == SystemController.currentAuth) {
				pnFunction.updateList(LibConst.FUNC_LIBRARIANS);
			} else {
				pnFunction.updateList(LibConst.FUNC_ALL);
			}

			loginDialog.setVisible(false);
			this.setVisible(true);
		} catch (Exception ex) {
			loginDialog.showErrorStatus(ex.getMessage());
		}
	}

	public void addMember() {
		try {
			pnAddMember.checkRequiredFields();
			// Save member
			LibraryMember member = pnAddMember.getLibraryMember();
			daController.addMember(member);
			pnAddMember.resetData();
			pnStatus.setSuccessfulMessage("Member is saved");
		} catch (Exception ex) {
			pnStatus.setErrorMessage(ex.getMessage());
		}
	}

	public List<Author> getAuthors() {
		return daController.getAuthors();
	}

	public String getNextMemberID() {
		return daController.getNextMemberID();
	}

	public void logout() {
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, LibConst.FUNC_DEFAULT_NM);
		loginDialog.clearData();

		this.setVisible(false);
		loginDialog.setVisible(true);
	}

	public void addBook() {
		try {
			pnAddBook.checkRequiredFields();

			// Save book
			Book book = pnAddBook.getBook();
			daController.addBook(book);
			pnAddBook.resetData();
			pnStatus.setSuccessfulMessage("Book is saved");
		} catch (Exception ex) {
			pnStatus.setErrorMessage(ex.getMessage());
		}

	}

	public void getCheckoutRecords() {
		try {
			pnCheckoutRecord.checkRequiredFields();

			pnCheckoutRecord.clearTableData();

			List<String[]> checkoutRecordsTable = new ArrayList<>();

			List<RecordData> checkoutRecords = daController.getCheckoutRecords(pnCheckoutRecord.getMemberId());

			for (RecordData data : checkoutRecords) {
				String[] row = new String[] { data.getBookTitle(), data.getBookIsbn(),
						String.valueOf(data.getCopyNum()), data.getCheckoutDate().toString(), data.getDueDate().toString()};
				checkoutRecordsTable.add(row);
			}
			pnCheckoutRecord.updateModel(checkoutRecordsTable);

		} catch (Exception ex) {
			pnStatus.setErrorMessage(ex.getMessage());
		}
	}

	public void getOverdueRecords() {
		try {
			pnOverdueCheckout.checkRequiredFields();
			pnOverdueCheckout.clearTableData();

			List<String[]> overdueDataRecordsTable = new ArrayList<>();

			List<OverdueData> overdueDataRecords = daController.getOverdueRecords(pnOverdueCheckout.getBookISBN());

			for (OverdueData data : overdueDataRecords) {
				String[] row = new String[] { data.getBookTitle(), String.valueOf(data.getCopyNum()), 
						data.getMemberID(),
						data.getCheckoutDate().toString(),
						data.getDueDate().toString()};
				
				overdueDataRecordsTable.add(row);
			}

			pnOverdueCheckout.updateModel(overdueDataRecordsTable);

		} catch (Exception ex) {
			pnStatus.setErrorMessage(ex.getMessage());
		}
	}

	public void addBookCopy() {
		try {
			pnAddBookCopy.checkRequiredFields();
			String isbn = pnAddBookCopy.getIsbn();
			String numOfCopies = pnAddBookCopy.getNumOfCopies();
			daController.addBookCopy(isbn, Integer.parseInt(numOfCopies));
			pnAddBookCopy.resetData();
			pnStatus.setSuccessfulMessage("Book copies are added.");
		} catch (Exception e) {
			String errMessage = e.getMessage();
			pnStatus.setErrorMessage(errMessage);
		}
	}

	public void checkout() {
		try {
			pnCheckout.checkRequiredFields();
			String memberID = pnCheckout.getMemberId();
			String[] isbns = pnCheckout.getIsbns();

			daController.checkoutBooks(memberID, isbns);
			pnCheckout.resetData();
			pnStatus.setSuccessfulMessage("The given book(s) are checked out.");
		} catch (Exception e) {
			String errMessage = e.getMessage();
			pnStatus.setErrorMessage(errMessage);
		}
	}

	public void changeFunctions() {
		String selValue = pnFunction.getSelectedValue();
		if (null != selValue) {
			CardLayout cl = (CardLayout) (cards.getLayout());

			if (selValue.equals(LibConst.FUNC_ADD_BOOK_NM)) {
				// pnViewTitle.updateListData(Data.bookTitles);
			} else if (selValue.equals(LibConst.FUNC_CHECKOUT_RECORD_NM)) {
				pnCheckoutRecord.resetData();
			} else if (selValue.equals(LibConst.FUNC_ADD_MEMBER_NM)) {
				pnAddMember.setMemberID(daController.getNextMemberID());
			}
			cl.show(cards, selValue);
		}
	}

	public ActionListener getActionListener() {
		return this.actListener;
	}

	public ListSelectionListener getListSelectionListener() {
		return this.listSelListener;
	}

	public WindowListener getWindowListener() {
		return this.windowListener;
	}

	private void displayUI() {

		loginDialog.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}
		});
		// new MainFrame();
	}

	@Override
	public void resetData() {
	}

	@Override
	public void checkRequiredFields() throws Exception {
	}
}