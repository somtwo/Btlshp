package btlshp.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import btlshp.enums.AppState;


public class MainUI {
	protected JFrame      mainFrame;
	protected JMenuBar    mainMenuBar;
	protected JMenu       fileMenu;
	protected JMenuItem[] fileMenuItems;
	private   Btlshp      game;
	private   GameGrid    gg;
	private   OutputArea  cons;
	private   JLabel      status;
	
	private   Font        proFont;
	
	MainUI(Btlshp game) {
		this.game = game;
		
		try {
			proFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/ProFontWindows.ttf"));
		} 
		catch (Exception e) {
			proFont = null;
		}
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
	
	
	protected void makeStatusPane() {
		
	}
	
	
	protected void makeGUI() {
		// Main JFrame
		mainFrame = new JFrame("BtlShp");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dims = new Dimension();
		dims.width = 640;
		dims.height = 580;
		
		mainFrame.setSize(dims);
		mainFrame.setResizable(false);
		if(!(mainFrame.getLayout() instanceof BorderLayout))
			mainFrame.setLayout(new BorderLayout());
		
		// Make the menu
		makeMainMenu();
		
		// Make the game grid
		gg = new GameGrid();
		mainFrame.add(gg, BorderLayout.CENTER);
		
		// Right status pane
		status = new JLabel();
		status.setPreferredSize(new Dimension(158, 100));
		status.setFont(proFont.deriveFont(Font.PLAIN, 12.0f));
		status.setForeground(new Color(6, 178, 48));
		status.setBackground(new Color(3, 28, 9));
		status.setOpaque(true);
		mainFrame.add(status, BorderLayout.LINE_END);
		
		// Bottom console pane.
		cons = new OutputArea();
		cons.setFont(status.getFont());
		mainFrame.add(cons, BorderLayout.PAGE_END);
		cons.addText("- Welcome to BtlShp! Please start or restore a game!\n");
		
		// Show everything
		mainFrame.pack();
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
