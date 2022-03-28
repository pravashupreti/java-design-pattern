package mpp.lib.frontend.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import mpp.lib.business.Author;
import mpp.lib.business.Book;
import mpp.lib.constants.LibConst;
import mpp.lib.utils.Util;

public class AddBookPanel extends JPanel implements CommonContainer {

	private static final long serialVersionUID = 6259802339157874874L;

	private MainFrame parent;

	private JTextField txtISBN;
	private JTextField txtTitle;
	private JTextField txtMaxCheckoutLength;
	private JTextField txtNumberOfCopy;

	@SuppressWarnings("rawtypes")
	private JList listAuthor;
	private Author[] authorListString;
	private JButton btAddBook = new JButton("Save");;

	public AddBookPanel(MainFrame mainFrame) {
		this.parent = mainFrame;
		initializeUI();
		addListeners();
	}

	@Override
	public void initializeVariables() {

	}

	@Override
	public void initializeUI() {
		setLayout(new BorderLayout());

		// Add Top panel
		JPanel pnTitle = new JPanel();
		pnTitle.setPreferredSize(new Dimension(this.getWidth(), 60));

		pnTitle.setLayout(new BorderLayout());
		JLabel lblLogin = new JLabel("  Add Book");
		Util.adjustLabelFont(lblLogin, LibConst.DARK_BLUE, true);
		pnTitle.add(lblLogin, BorderLayout.WEST);

		// Add the book form
		JPanel pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());

		addBookPanel(pnContent);

		// Add margin on both left and right
		JPanel pnEmptyEast = new JPanel();
		pnEmptyEast.setPreferredSize(new Dimension(120, getHeight()));
		JPanel pnEmptyWest = new JPanel();
		pnEmptyWest.setPreferredSize(new Dimension(120, getHeight()));

		add(pnEmptyEast, BorderLayout.EAST);
		add(pnEmptyWest, BorderLayout.WEST);
		add(pnTitle, BorderLayout.NORTH);
		add(pnContent, BorderLayout.CENTER);
	}

	private void addBookPanel(JPanel pnContent) {

		JPanel addBookPanel = new JPanel();
		addBookPanel.setLayout(new BorderLayout());

		JPanel bookPanel = addBookSection(addBookPanel);

		JPanel authorPanel = addAuthorSection(addBookPanel);

		addBookPanel.add(bookPanel, BorderLayout.NORTH);
		addBookPanel.add(authorPanel, BorderLayout.CENTER);

		JPanel addBookButtonPanel = new JPanel();

		addBookButtonPanel.setLayout(new GridLayout(0, 2, 10, 10));

		addBookButtonPanel.add(new JLabel());
		addBookButtonPanel.add(new JLabel());
		addBookButtonPanel.add(new JLabel());
		btAddBook.setActionCommand(LibConst.CMD_ADD_BOOK);
		addBookButtonPanel.add(btAddBook);
		addBookButtonPanel.add(new JLabel());

		addBookPanel.add(addBookButtonPanel, BorderLayout.SOUTH);
		pnContent.add(addBookPanel);

	}

	private JPanel addBookSection(JPanel addBookPanel) {
		JPanel bookPanel = new JPanel();

		bookPanel.setLayout(new GridLayout(0, 2, 10, 10));
		JLabel ISBNLabel = new JLabel("ISBN (*)");
		JLabel titleLabel = new JLabel("Book Title (*)");
		JLabel maxCheckoutLengthLabel = new JLabel("Max Checkout Length (*)");
		JLabel numberOfCopyLabel = new JLabel("Number Of Copy (*)");

		txtISBN = new JTextField(10);
		txtTitle = new JTextField(10);
		txtMaxCheckoutLength = new JTextField(10);
		txtNumberOfCopy = new JTextField("1");

		bookPanel.add(ISBNLabel);
		bookPanel.add(txtISBN);

		bookPanel.add(titleLabel);
		bookPanel.add(txtTitle);

		bookPanel.add(maxCheckoutLengthLabel);
		bookPanel.add(txtMaxCheckoutLength);

		bookPanel.add(numberOfCopyLabel);
		bookPanel.add(txtNumberOfCopy);

		bookPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		bookPanel.add(Box.createRigidArea(new Dimension(0, 12)));

		return bookPanel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JPanel addAuthorSection(JPanel addBookPanel) {

		List<Author> authors = parent.getAuthors();

		authorListString = authors.toArray(new Author[authors.size()]);

		listAuthor = new JList(authorListString);
		listAuthor.setCellRenderer(new CheckboxListCellRenderer());
		listAuthor.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JPanel authorPanel = new JPanel();
		authorPanel.setLayout(new GridLayout(0, 2, 10, 10));

		JLabel authorLabel = new JLabel("Authors");
		authorPanel.add(authorLabel);

		authorPanel.add(new JScrollPane(listAuthor));
		return authorPanel;
	}

	@Override
	public void addListeners() {
		btAddBook.addActionListener(parent.getActionListener());

	}

	@Override
	public void resetData() {
		txtISBN.setText("");
		txtTitle.setText("");
		txtMaxCheckoutLength.setText("");
		txtNumberOfCopy.setText("1");
		listAuthor.setSelectedIndices(new int[0]);

	}

	@Override
	public void checkRequiredFields() throws Exception {

		if ("".equals(txtISBN.getText())) {
			throw new Exception("ISBN can't be empty");
		}
		if ("".equals(txtTitle.getText())) {
			throw new Exception("BookTitle can't be empty");
		}
		if ("".equals(txtMaxCheckoutLength.getText())) {
			throw new Exception("Max checkout length need to be define");
		}

		if (!Util.isPossitiveNumber(txtMaxCheckoutLength.getText())) {
			throw new Exception("Max checkout length must be possitive number");
		}

		if (!Util.isPossitiveNumber(txtNumberOfCopy.getText())) {
			throw new Exception("Max checkout length must be possitive number");
		}

		if (listAuthor.getSelectedIndices().length == 0) {
			throw new Exception("At least 1 author must be selected.");
		}
	}

	public Book getBook() {
		String isbn = txtISBN.getText();
		String title = txtTitle.getText();
		int maxCheckoutLength = Integer.parseInt(txtMaxCheckoutLength.getText());
		int numberOfcopy = Integer.parseInt(txtNumberOfCopy.getText());
		List<Author> authors = new ArrayList<>();

		for (int index : listAuthor.getSelectedIndices()) {
			authors.add(authorListString[index]);
		}

		return new Book(isbn, title, maxCheckoutLength, authors, numberOfcopy);
	}

}

@SuppressWarnings("rawtypes")
class CheckboxListCellRenderer extends JCheckBox implements ListCellRenderer {

	private static final long serialVersionUID = 7674360457029643164L;

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		setComponentOrientation(list.getComponentOrientation());
		setFont(list.getFont());
		setBackground(list.getBackground());
		setForeground(list.getForeground());
		setSelected(isSelected);
		setEnabled(list.isEnabled());

		setText(value == null ? "" : value.toString());

		return this;
	}
}
