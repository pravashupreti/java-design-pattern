package mpp.lib.frontend.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import mpp.lib.constants.LibConst;

public class StatusPanel extends JPanel implements CommonContainer {
	
	private static final long serialVersionUID = 1976856645330654808L;

	private JTextArea textArea = new JTextArea();
	
	public StatusPanel() {
		
		initializeUI();
		addListeners();
	}
	
	@Override
	public void initializeUI() {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		JPanel pnStatus = new JPanel();
		pnStatus.setLayout(new BorderLayout());
		pnStatus.setPreferredSize(new Dimension(600, getHeight()));
		pnStatus.add(textArea, BorderLayout.CENTER);
		textArea.setEditable(false);
		add(pnStatus, BorderLayout.CENTER);
		
	}
	
	public void setMessage(String message) {
		textArea.setForeground(LibConst.DARK_BLUE);
		textArea.setText("   " + message);
	}
	
	public void setErrorMessage(String message) {
		textArea.setForeground(LibConst.ERROR_MESSAGE_COLOR);
		textArea.setText("   Error: " + message);
	}
	
	public void setSuccessfulMessage(String message) {
		textArea.setForeground(LibConst.INFO_MESSAGE_COLOR);
		textArea.setText("   " + message);
	}

	@Override
	public void initializeVariables() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void addListeners() {
	}

	@Override
	public void resetData() {
	}

	@Override
	public void checkRequiredFields() throws Exception {
	}
	
}
