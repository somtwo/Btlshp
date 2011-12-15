package btlshp.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class OutputArea extends JTextArea {
	private static final long   serialVersionUID = 2089727694026951376L;
	private static final String LE = System.getProperty("line.separator");

	public OutputArea() {
		setForeground(new Color(6, 178, 48));
		setBackground(new Color(0,0,0));
		setOpaque(true);
		setEditable(false);
		
		setMinimumSize(new Dimension(200, 75));
		setPreferredSize(new Dimension(340, 75));
		
		setLineWrap(true);
		setWrapStyleWord(true);
	}
	
	
	public void addLine(String txt) {
		setText("> " + txt + LE + getText());
	}
}
