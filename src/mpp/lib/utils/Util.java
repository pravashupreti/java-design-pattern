package mpp.lib.utils;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;

public class Util {
	
	public static void adjustLabelFont(JComponent label, Color color, boolean bigger) {
		if (bigger) {
			Font f = new Font(label.getFont().getName(), 
					label.getFont().getStyle(), (label.getFont().getSize()+2));
			label.setFont(f);
		} else {
			Font f = new Font(label.getFont().getName(), 
					label.getFont().getStyle(), (label.getFont().getSize()-2));
			label.setFont(f);
		}
		label.setForeground(color);
		
	}
	
	public static boolean isPossitiveNumber(String value) {
		try {
			int intValue = Integer.parseInt(value);
			if (intValue > 0)
				return true;
			return false;
		} catch (Exception ex) {
			return false;
		}
	}
}
