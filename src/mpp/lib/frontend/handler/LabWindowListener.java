package mpp.lib.frontend.handler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LabWindowListener extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	};
}
