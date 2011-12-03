package btlshp.ui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HelpScreen {
	JFrame        mainFrame;
	JButton       closeButton;
	JPanel        buttonPane;
	JEditorPane   editPane;
	
	
	public HelpScreen() {
	}
	
	public void buildUi() {
		mainFrame = new JFrame("Btlshp Help");
		mainFrame.setSize(640, 480);
		mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.PAGE_AXIS));
		
		editPane = new JEditorPane();
		editPane.setEditable(false);
		editPane.setPreferredSize(new Dimension(640, 480));
		editPane.setBackground(mainFrame.getBackground());
		editPane.setContentType("text/html");
		editPane.setText("BTLSHP is a turn-based multiplayer game which pits you against one opponent in a battle of wits and strategy.<br /><br />");
		editPane.setMargin(new Insets(10, 10, 10, 10));
		mainFrame.add(editPane);
		
		closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				mainFrame.setVisible(false);
			}
		});
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(closeButton);
		
		mainFrame.add(buttonPane);
	}
	
	
	public void show() {
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
}
