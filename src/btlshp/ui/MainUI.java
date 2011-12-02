package btlshp.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import btlshp.enums.AppState;


public class MainUI {
	protected JFrame      mainFrame;
	protected JMenuBar    mainMenuBar;
	protected JMenu       fileMenu;
	protected JMenuItem[] fileMenuItems;
	private   Btlshp      game;
	
	MainUI(Btlshp game) {
		this.game = game;
	}
	
	protected void updateMainMenu() {
		if(game.getAppState() == AppState.NoGame) {
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
	
	
	protected void makeMainMenu() {
		mainMenuBar = new JMenuBar();
		mainMenuBar.setOpaque(true);
		mainMenuBar.setPreferredSize(new Dimension(200, 20));
		
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.getAccessibleContext().setAccessibleDescription("Main menu used to starting, saving, or quitting games.");
		
		fileMenuItems = new JMenuItem[6];
		
		JMenuItem newGameItem = fileMenuItems[0] = new JMenuItem("New");
		newGameItem.setMnemonic(KeyEvent.VK_N);
		fileMenu.add(newGameItem);
		fileMenu.addSeparator();
		
		JMenuItem saveGameItem = fileMenuItems[1] = new JMenuItem("Save");
		saveGameItem.setMnemonic(KeyEvent.VK_S);
		fileMenu.add(saveGameItem);
		
		JMenuItem restoreGameItem = fileMenuItems[2] = new JMenuItem("Restore saved game");
		restoreGameItem.setMnemonic(KeyEvent.VK_O);
		fileMenu.add(restoreGameItem);
		fileMenu.addSeparator();
		
		
		JMenuItem forfeitGameItem = fileMenuItems[3] = new JMenuItem("Forfeit current game");
		fileMenu.add(forfeitGameItem);
		fileMenu.addSeparator();
		
		JMenuItem helpItem = fileMenuItems[4] = new JMenuItem("Help");
		helpItem.setMnemonic(KeyEvent.VK_H);
		fileMenu.add(helpItem);
		fileMenu.addSeparator();
		
		JMenuItem quitItem = fileMenuItems[5] = new JMenuItem("Quit");
		quitItem.setMnemonic(KeyEvent.VK_Q);
		fileMenu.add(quitItem);
		
		mainMenuBar.add(fileMenu);
		
		mainFrame.setJMenuBar(mainMenuBar);
		
		updateMainMenu();
	}
	
	
	protected void makeGUI() {
		// Main JFrame
		mainFrame = new JFrame("BtlShp");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dims = new Dimension();
		dims.width = 640;
		dims.height = 580;
		
		mainFrame.setMinimumSize(dims);
		mainFrame.setSize(dims);
		mainFrame.setResizable(false);
		
		// Make the menu
		makeMainMenu();
		
		// Show everything
		mainFrame.setVisible(true);
	}
	
	
	protected DialogResult yesNoCancelDialog(String title, String message) {
		int result = JOptionPane.showConfirmDialog(mainFrame, message, title, JOptionPane.YES_NO_CANCEL_OPTION);
		
		switch(result) {
		case 0:
			return DialogResult.Yes;
		case 1:
			return DialogResult.No;
		case 2:
			return DialogResult.Cancel;
		}
		
		return DialogResult.Cancel;
	}

}
