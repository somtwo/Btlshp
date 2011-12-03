package btlshp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import btlshp.Btlshp;
import btlshp.enums.AppState;

public class FileMenu extends JMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8339680861310634872L;
	
	protected JMenuItem[] fileMenuItems;
	
	public FileMenu() {
		super("File");
		setMnemonic(KeyEvent.VK_F);
		getAccessibleContext().setAccessibleDescription("Main menu used to starting, saving, or quitting games.");
		
		fileMenuItems = new JMenuItem[6];
		
		JMenuItem newGameItem = fileMenuItems[0] = new JMenuItem("New");
		newGameItem.setMnemonic(KeyEvent.VK_N);
		newGameItem.addActionListener(new FileMenu.NewGameListener());
		add(newGameItem);
		addSeparator();
		
		JMenuItem saveGameItem = fileMenuItems[1] = new JMenuItem("Save");
		saveGameItem.setMnemonic(KeyEvent.VK_S);
		add(saveGameItem);
		
		JMenuItem restoreGameItem = fileMenuItems[2] = new JMenuItem("Restore saved game");
		restoreGameItem.setMnemonic(KeyEvent.VK_O);
		add(restoreGameItem);
		addSeparator();
		
		
		JMenuItem forfeitGameItem = fileMenuItems[3] = new JMenuItem("Forfeit current game");
		add(forfeitGameItem);
		addSeparator();
		
		JMenuItem helpItem = fileMenuItems[4] = new JMenuItem("Help");
		helpItem.setMnemonic(KeyEvent.VK_H);
		helpItem.addActionListener(new FileMenu.HelpListener());
		add(helpItem);
		addSeparator();
		
		JMenuItem quitItem = fileMenuItems[5] = new JMenuItem("Quit");
		quitItem.setMnemonic(KeyEvent.VK_Q);
		quitItem.addActionListener(new FileMenu.QuitListener());
		add(quitItem);
	}
	
	public void updateMenuItems() {
		if(Btlshp.getGame().getAppState() == AppState.NoGame) {
			fileMenuItems[1].setEnabled(false);
			fileMenuItems[2].setEnabled(false);
			fileMenuItems[3].setEnabled(false);
		}
		else {
			fileMenuItems[1].setEnabled(true);
			fileMenuItems[2].setEnabled(true);
			fileMenuItems[3].setEnabled(true);
		}
	}
	
	
	private static class NewGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			Btlshp.getGame().newGame();
		}	
	}
	
	
	private static class HelpListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			Btlshp.getGame().helpScreen();
		}	
	}
	
	private static class QuitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Btlshp.getGame().quitGame();
		}
		
	}
}
