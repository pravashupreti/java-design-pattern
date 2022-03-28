package mpp.lib.frontend.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mpp.lib.constants.LibConst;

public class LoginDialog extends JDialog implements CommonContainer {
	
	private static final long serialVersionUID = -5873151272671865300L;
	
	private static final String DEFAULT_MESSAGE = "Enter user name and password";
	
	private MainFrame parent;
	
	private JTextField txtUserName = new JTextField();
	
	private JPasswordField txtPassword = new JPasswordField();
	
	private JButton btLogin = new JButton("Login");
	
	private JButton btExit = new JButton("Exit");
	
	private static final int WIDTH = 300;
	
	private static final int HEIGHT = 246;
	
	private JLabel lblStatus = new JLabel(DEFAULT_MESSAGE);
	
	public LoginDialog(MainFrame mainFrame, boolean modal) {
		
		super(mainFrame, modal);
		
		this.parent = mainFrame;
		
		initializeUI();
		
		addListeners();
	}
	
	@Override
	public void initializeVariables() {}
	
	@Override
	public void initializeUI() {
		setTitle("Login");
		setLayout(new BorderLayout(10, 10));
		
		addTopPanel();
		
		JPanel pnInput = new JPanel();
		pnInput.setLayout(new GridLayout(3,  2, 10, 10));
		setSize(new Dimension(WIDTH, HEIGHT));
		pnInput.add(new JLabel("User name"));
		pnInput.add(txtUserName);
		pnInput.add(new JLabel("Password"));
		pnInput.add(txtPassword);
		pnInput.add(btLogin);
		pnInput.add(btExit);
		txtPassword.setActionCommand(LibConst.CMD_LOGIN);
		btExit.setActionCommand(LibConst.CMD_EXIT);
		btLogin.setActionCommand(LibConst.CMD_LOGIN);
		
		add(pnInput, BorderLayout.CENTER);
		
		JPanel pnBottom = new JPanel();
		pnBottom.setPreferredSize(new Dimension(WIDTH, 20));
		add(pnBottom, BorderLayout.SOUTH);
		
		JPanel pnLeft = new JPanel();
		pnLeft.setPreferredSize(new Dimension(30, getHeight()));
		add(pnLeft, BorderLayout.WEST);
		
		JPanel pnRight = new JPanel();
		pnRight.setPreferredSize(new Dimension(30, getHeight()));
		add(pnRight, BorderLayout.EAST);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		this.setLocation(((width - WIDTH) / 2), (height - HEIGHT) / 3);
		
		setResizable(false);
	}
	
	private void addTopPanel() {
		
		JPanel pnTop = new JPanel();
		
		pnTop.setBackground(Color.LIGHT_GRAY);
		pnTop.setLayout(new BorderLayout());
		
		JPanel pnLeft = new JPanel();
		JPanel pnRight = new JPanel();
		
		pnLeft.setBackground(Color.LIGHT_GRAY);
		pnRight.setBackground(Color.LIGHT_GRAY);
		
		pnTop.add(pnLeft, BorderLayout.WEST);
		pnTop.add(pnRight, BorderLayout.EAST);
		pnTop.add(lblStatus, BorderLayout.CENTER);
		pnTop.setPreferredSize(new Dimension(WIDTH, 45));
		
		add(pnTop, BorderLayout.NORTH);
	}
	
	public void clearData() {
		txtUserName.setText("");
		txtPassword.setText("");
		txtUserName.requestFocus();
	}
	
	@Override
	public void addListeners() {
		txtPassword.addActionListener(parent.getActionListener());
		btLogin.addActionListener(parent.getActionListener());
		btExit.addActionListener(parent.getActionListener());
		addWindowListener(parent.getWindowListener());
	}
	
	public void showErrorStatus(String errMessage) {
		lblStatus.setForeground(Color.RED.darker());
		lblStatus.setText(errMessage);
	}
	
	public void showDefaultText() {
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setText(DEFAULT_MESSAGE);
	}
	
	public String getUserName() {
		return txtUserName.getText();
	}
	
	public String getPassword() {
		return new String(txtPassword.getPassword());
	}
	
	@Override
	public void resetData () {
		
	}
	
	@Override
	public void checkRequiredFields() throws Exception {
		String userName = txtUserName.getText();
		if (userName.length() < 1) {
			throw new Exception("User name can't be empty");
		}
		
		String pwd = String.valueOf(txtPassword.getPassword());
		if (pwd.length() < 1) {
			throw new Exception("Password can't be empty");
		}
	}
	
}
