package btlshp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import btlshp.Btlshp;
import btlshp.BtlshpGame;
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
		
		fileMenuItems = new JMenuItem[8];
		
		JMenuItem newGameItem = fileMenuItems[0] = new JMenuItem("New");
		newGameItem.setMnemonic(KeyEvent.VK_N);
		newGameItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				Btlshp.getGame().newGame();
			}	
		});
		add(newGameItem);
		
		JMenuItem joinGameItem = fileMenuItems[1] = new JMenuItem("Join game");
		joinGameItem.setMnemonic(KeyEvent.VK_J);
		joinGameItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ev){
				Btlshp.getGame().joinGame();
			}
		});
		add(joinGameItem);
		addSeparator();
		JMenuItem saveGameItem = fileMenuItems[2] = new JMenuItem("Save and exit current game");
		saveGameItem.setMnemonic(KeyEvent.VK_S);
		saveGameItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				Btlshp.getGame().saveGame();
			}	
		});
		add(saveGameItem);
		
		JMenuItem restoreGameItem = fileMenuItems[3] = new JMenuItem("Restore saved game");
		restoreGameItem.setMnemonic(KeyEvent.VK_O);
		restoreGameItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				Btlshp.getGame().restoreGame();
			}	
		});
		add(restoreGameItem);
		addSeparator();
		
		
		JMenuItem forfeitGameItem = fileMenuItems[4] = new JMenuItem("Forfeit current game");
		forfeitGameItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Btlshp.getGame().forfeitGame();
				
			}
		});
		add(forfeitGameItem);
		addSeparator();
		
		JMenuItem helpItem = fileMenuItems[5] = new JMenuItem("Help");
		helpItem.setMnemonic(KeyEvent.VK_H);
		helpItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				Btlshp.getGame().helpScreen();
			}	
		});
		add(helpItem);
		addSeparator();
		
		JMenuItem playMusicItem = fileMenuItems[7] = new JMenuItem("Wanna play sweet music?");
		playMusicItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Btlshp.getGame().setupMusic();
				Btlshp.getGame();
				BtlshpGame.playsMusic();
			}
		});
		add(playMusicItem);	
		addSeparator();
		
		JMenuItem quitItem = fileMenuItems[6] = new JMenuItem("Quit");
		quitItem.setMnemonic(KeyEvent.VK_Q);
		quitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Btlshp.getGame().quitGame();
			}
		});
		add(quitItem);
		addSeparator();
		
		
	}
	
	




	public void updateMenuItems() {
		if(Btlshp.getGame().getAppState() == AppState.NoGame) {
			fileMenuItems[0].setEnabled(true);
			fileMenuItems[1].setEnabled(true);
			fileMenuItems[2].setEnabled(false);
			fileMenuItems[3].setEnabled(true);
			fileMenuItems[4].setEnabled(false);
		}
		else if (Btlshp.getGame().getAppState() == AppState.LocalTurn) {
			fileMenuItems[0].setEnabled(false);
			fileMenuItems[1].setEnabled(false);
			fileMenuItems[2].setEnabled(true);
			fileMenuItems[3].setEnabled(false);
			fileMenuItems[4].setEnabled(true);
		
		}else  { 									//AppState.remoteTurn
			fileMenuItems[0].setEnabled(false);
			fileMenuItems[1].setEnabled(false);
			fileMenuItems[2].setEnabled(true);
			fileMenuItems[3].setEnabled(false);
			fileMenuItems[4].setEnabled(true);
		}
	}
	
}
