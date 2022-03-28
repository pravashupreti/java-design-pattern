package mpp.lib.frontend.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mpp.lib.constants.LibConst;
import mpp.lib.utils.Util;

public class AddBookCopyPanel extends JPanel implements CommonContainer {

	private static final long serialVersionUID = 6259802339157874874L;

	private MainFrame parent;

	private JTextField txtIsbn = new JTextField(15);
	private JTextField txtNumOfCopy = new JTextField();

	private JButton btAddCopy = new JButton("Add Copy");;

	public AddBookCopyPanel(MainFrame mainFrame) {
		this.parent = mainFrame;
		initializeUI();
		addListeners();
	}

	public void initializeUI() {
		setLayout(new BorderLayout());

		// Add Top panel
		JPanel pnTitle = new JPanel();
		pnTitle.setPreferredSize(new Dimension(this.getWidth(), 60));

		pnTitle.setLayout(new BorderLayout());
		JLabel lblLogin = new JLabel("Add Book Copy");
		Util.adjustLabelFont(lblLogin, LibConst.DARK_BLUE, true);
		pnTitle.add(lblLogin, BorderLayout.WEST);

		JPanel pnContent = new JPanel();
		// pnContent.setBackground(Color.CYAN);
		pnContent.setLayout(new BorderLayout(10, 10));

		// Add content
		JPanel pnInput = new JPanel();
		pnInput.setLayout(new GridLayout(3, 2, 10, 10));

		pnInput.add(new JLabel("Book ISBN (*)"));

		pnInput.add(txtIsbn);

		pnInput.add(new JLabel("Number of Copies (*)"));

		pnInput.add(txtNumOfCopy);

		btAddCopy.setActionCommand(LibConst.CMD_ADD_BOOK_COPY);
		pnInput.add(new JLabel());
		pnInput.add(btAddCopy);

		pnContent.add(pnInput, BorderLayout.CENTER);

		JPanel pnLeft = new JPanel();
		pnLeft.setPreferredSize(new Dimension(120, 200));

		JPanel pnRight = new JPanel();
		pnRight.setPreferredSize(new Dimension(120, 200));

		JPanel pnBottom = new JPanel();

		pnBottom.setPreferredSize(new Dimension(120, 320));

		add(pnLeft, BorderLayout.WEST);
		add(pnRight, BorderLayout.EAST);
		add(pnTitle, BorderLayout.NORTH);
		add(pnContent, BorderLayout.CENTER);
		add(pnBottom, BorderLayout.SOUTH);

	}

	public void addListeners() {
		btAddCopy.addActionListener(parent.getActionListener());
	}

	public String getIsbn() {
		return txtIsbn.getText();
	}

	public String getNumOfCopies() {
		return txtNumOfCopy.getText();
	}

	@Override
	public void initializeVariables() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetData() {
		txtIsbn.setText("");
		txtNumOfCopy.setText("");
	}

	@Override
	public void checkRequiredFields() throws Exception {
		if ("".equals(txtIsbn.getText())) {
			throw new Exception("ISBN can't be empty.");
		}
		String numOfCopies = txtNumOfCopy.getText();
		if (!Util.isPossitiveNumber(numOfCopies)) {
			throw new Exception("Number of copies must be numerical value");
		}
	}
}
