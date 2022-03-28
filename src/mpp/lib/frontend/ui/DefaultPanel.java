package mpp.lib.frontend.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DefaultPanel extends JPanel {
	
	private static final long serialVersionUID = 3043642158343192587L;

	public DefaultPanel() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		String currDirectory = System.getProperty("user.dir");
    	String pathToImage = currDirectory+"/src/mpp/lib/resources/library.jpg";
    	
		ImageIcon image = new ImageIcon(pathToImage);
		add(new JLabel(image), BorderLayout.CENTER);
	}
}
