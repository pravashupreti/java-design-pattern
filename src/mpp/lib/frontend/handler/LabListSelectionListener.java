package mpp.lib.frontend.handler;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mpp.lib.frontend.ui.MainFrame;

public class LabListSelectionListener implements ListSelectionListener 
{
	private MainFrame parent;
	
	public LabListSelectionListener(MainFrame mainFrame) {
		this.parent = mainFrame;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			parent.changeFunctions();
		}
	}
}
