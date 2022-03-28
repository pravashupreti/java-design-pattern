package mpp.lib.frontend.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mpp.lib.business.Address;
import mpp.lib.business.LibraryMember;
import mpp.lib.constants.LibConst;
import mpp.lib.utils.Util;

public class AddMemberPanel extends JPanel implements CommonContainer {

	private static final long serialVersionUID = 6259802339157874874L;

	private JTextField txtMemberID;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtStreet;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtZip = new JTextField();
	private JTextField txtPhone;
	
	private JButton btSave = new JButton("Save");;
	
	private MainFrame parent;
	
	public AddMemberPanel(MainFrame mainFrame) {
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
		JLabel lblLogin = new JLabel("Add Library Member");
		Util.adjustLabelFont(lblLogin, LibConst.DARK_BLUE, true);
		pnTitle.add(lblLogin, BorderLayout.WEST);
		
		JPanel pnContent = new JPanel();
		
		// Add content
		JPanel pnInput = new JPanel();
		pnInput.setLayout(new GridLayout(9,2, 10, 10));
		
		pnInput.add(new JLabel("Member ID"));
		txtMemberID = new JTextField(15);
		txtMemberID.setEditable(false);
		pnInput.add(txtMemberID);
		
		pnInput.add(new JLabel("First Name (*)"));
		txtFirstName = new JTextField();
		pnInput.add(txtFirstName);
		
		pnInput.add(new JLabel("Last Name (*)"));
		txtLastName = new JTextField();
		pnInput.add(txtLastName);
		
		pnInput.add(new JLabel("Street"));
		txtStreet = new JTextField();
		pnInput.add(txtStreet);
		
		pnInput.add(new JLabel("City"));
		txtCity = new JTextField();
		pnInput.add(txtCity);
		
		pnInput.add(new JLabel("State"));
		txtState = new JTextField();
		pnInput.add(txtState);
		
		pnInput.add(new JLabel("Zip"));
		pnInput.add(txtZip);
		
		pnInput.add(new JLabel("Phone"));
		txtPhone = new JTextField();
		pnInput.add(txtPhone);
		
		btSave.setActionCommand(LibConst.CMD_SAVE_MEMBER);
		pnInput.add(new JLabel());
		pnInput.add(btSave);
			
		pnContent.add(pnInput, BorderLayout.CENTER);
		
		JPanel pnLeft = new JPanel();
		pnLeft.setPreferredSize(new Dimension(120, 200));
		//pnLeft.setBackground(Color.BLUE);
		JPanel pnRight = new JPanel();
		//pnRight.setBackground(Color.cyan);
		pnRight.setPreferredSize(new Dimension(120, 200));
		add(pnTitle, BorderLayout.NORTH);
		add(pnLeft, BorderLayout.EAST);
		add(pnRight, BorderLayout.WEST);
		add(pnContent, BorderLayout.CENTER);
	}
	
	@Override
	public void addListeners() {
		btSave.addActionListener(parent.getActionListener());
	}
	
	public LibraryMember getLibraryMember() {
		String memberID = txtMemberID.getText();
		String firstName = txtFirstName.getText();
		String lastName = txtLastName.getText();
		String phone = txtPhone.getText();
		
		String street = txtStreet.getText();
		String city = txtCity.getText();
		String state = txtState.getText();
		String zip = txtZip.getText();
		
		Address addr = new Address(street, city, state, zip);
		
		LibraryMember member = new LibraryMember(memberID, firstName, lastName, phone, addr);
		return member;
	}
	
	@Override
	public void resetData () {
		txtMemberID.setText(parent.getNextMemberID());
		txtFirstName.setText("");
		txtLastName.setText("");
		txtPhone.setText("");
		txtStreet.setText("");
		txtCity.setText("");
		txtState.setText("");
		txtZip.setText("");
	}
	
	public void setMemberID (String id) {
		txtMemberID.setText(id);
	}
	
	@Override
	public void checkRequiredFields() throws Exception {
		if ("".equals(txtFirstName.getText())) {
			throw new Exception("First Name can't be empty");
		}
		if ("".equals(txtLastName.getText())) {
			throw new Exception("Last Name can't be empty");
		}
	}
}
