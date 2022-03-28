package mpp.lib.frontend.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mpp.lib.constants.LibConst;
import mpp.lib.utils.Util;

public class CheckoutPanel extends JPanel implements CommonContainer {

	private static final long serialVersionUID = 6259802339157874874L;

	private JTextField txtMemberID;
	private JTextField txtIsbn1;
	private JTextField txtIsbn2;
	private JTextField txtIsbn3;
	private JButton btCheckout = new JButton("Checkout");;

	private MainFrame parent;

	public CheckoutPanel(MainFrame mainFrame) {
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
		JLabel lblLogin = new JLabel("Checkout");
		Util.adjustLabelFont(lblLogin, LibConst.DARK_BLUE, true);
		pnTitle.add(lblLogin, BorderLayout.WEST);

		JPanel pnContent = new JPanel();
		// pnContent.setBackground(Color.CYAN);
		pnContent.setLayout(new BorderLayout(10, 10));

		// Add content
		JPanel pnInput = new JPanel();
		pnInput.setLayout(new GridLayout(9, 2, 10, 10));

		pnInput.add(new JLabel("Member ID (*)"));
		txtMemberID = new JTextField(15);
		pnInput.add(txtMemberID);

		pnInput.add(new JLabel("1. Book ISNB (*)"));
		txtIsbn1 = new JTextField();
		pnInput.add(txtIsbn1);

		pnInput.add(new JLabel("2. Book ISBN "));
		txtIsbn2 = new JTextField();
		pnInput.add(txtIsbn2);

		pnInput.add(new JLabel("3. Book ISBN"));
		txtIsbn3 = new JTextField();
		pnInput.add(txtIsbn3);

		btCheckout.setActionCommand(LibConst.CMD_CHECK_OUT);
		pnInput.add(new JLabel());
		pnInput.add(btCheckout);

		pnContent.add(pnInput, BorderLayout.CENTER);

		JPanel pnLeft = new JPanel();
		pnLeft.setPreferredSize(new Dimension(120, 200));

		JPanel pnRight = new JPanel();
		pnRight.setPreferredSize(new Dimension(120, 200));

		JPanel pnBottom = new JPanel();
		pnBottom.setPreferredSize(new Dimension(120, 100));

		add(pnLeft, BorderLayout.WEST);
		add(pnRight, BorderLayout.EAST);
		add(pnTitle, BorderLayout.NORTH);
		add(pnContent, BorderLayout.CENTER);
		add(pnBottom, BorderLayout.SOUTH);

	}

	public String getMemberId() {
		return txtMemberID.getText();
	}

	public String[] getIsbns() {
		String isbn1 = txtIsbn1.getText();
		String isbn2 = txtIsbn2.getText();
		String isbn3 = txtIsbn3.getText();
		List<String> temp = new ArrayList<>();

		if (!isbn1.equals(""))
			temp.add(isbn1);
		if (!isbn2.equals(""))
			temp.add(isbn2);
		if (!isbn3.equals(""))
			temp.add(isbn3);

		String[] isbns = new String[temp.size()];
		return temp.toArray(isbns);
	}

	@Override
	public void addListeners() {
		btCheckout.addActionListener(parent.getActionListener());
	}

	@Override
	public void initializeVariables() {
	}

	@Override
	public void resetData() {
		// TODO Auto-generated method stub
		txtMemberID.setText("");
		txtIsbn1.setText("");
		txtIsbn2.setText("");
		txtIsbn3.setText("");
	}

	@Override
	public void checkRequiredFields() throws Exception {
		String memberID = txtMemberID.getText();
		if ("".equals(memberID)) {
			throw new Exception("Member ID can't be empty.");
		}
		String isbn1 = txtIsbn1.getText();
		String isbn2 = txtIsbn2.getText();
		String isbn3 = txtIsbn3.getText();

		if (isbn1.equals("") && isbn2.equals("") && isbn3.equals("")) {
			throw new Exception("At least 1 ISBN must be entered.");
		}

		List<String> listIsbn = new ArrayList<>();
		if (!"".equals(isbn1))
			listIsbn.add(isbn1);

		if (listIsbn.contains(isbn2)) {
			throw new Exception(String.format("Duplicate ISBN [%s] is not allowed.", isbn2));
		}
		if (!"".equals(isbn2))
			listIsbn.add(isbn2);

		if (listIsbn.contains(isbn3)) {
			throw new Exception(String.format("Duplicate ISBN [%s] is not allowed.", isbn3));
		}
	}

}
