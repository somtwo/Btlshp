package btlshp.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class OutputArea extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2089727694026951376L;

	public OutputArea() {
		setForeground(new Color(6, 178, 48));
		setBackground(new Color(3, 28, 9));
		setOpaque(true);
		setVerticalAlignment(TOP);
		
		setMinimumSize(new Dimension(200, 80));
		setPreferredSize(new Dimension(640, 80));
	}
	
	
	public void addText(String txt) {
		setText(txt + getText());
	}
}
