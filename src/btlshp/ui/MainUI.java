package btlshp.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import btlshp.Btlshp;
import btlshp.enums.AppState;


public class MainUI {
	private HelpScreen   helpScreen;
	private JFrame       mainFrame;
	private JMenuBar     mainMenuBar;
	private FileMenu     fileMenu;
	private GameGrid     gg;
	private OutputArea   cons;
	private JLabel       status;
	private Font         proFont;
	
	public MainUI() {
		try {
			proFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/ProFontWindows.ttf"));
		} 
		catch (Exception e) {
			proFont = null;
		}
	}
	
	private void makeMainMenu() {
		mainMenuBar = new JMenuBar();
		mainMenuBar.setOpaque(true);
		mainMenuBar.setPreferredSize(new Dimension(200, 20));
		
		fileMenu = new FileMenu();		
		mainMenuBar.add(fileMenu);
		
		mainFrame.setJMenuBar(mainMenuBar);
		
		fileMenu.updateMenuItems();
	}
	
	
	private void makeStatusPane() {
		status = new JLabel();
		status.setPreferredSize(new Dimension(158, 100));
		status.setFont(proFont.deriveFont(Font.PLAIN, 12.0f));
		status.setForeground(new Color(6, 178, 48));
		status.setBackground(new Color(3, 28, 9));
		status.setOpaque(true);
	}
	
	
	public void makeGUI() {
		// Look and feel...
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception e) {
		}
		
		
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
		makeStatusPane();
		mainFrame.add(status, BorderLayout.LINE_END);
		
		// Bottom console pane.
		cons = new OutputArea();
		cons.setFont(status.getFont());
		mainFrame.add(cons, BorderLayout.PAGE_END);
		cons.addText("- Welcome to BtlShp! Please start or restore a game!\n");
		
		// Make help screen
		helpScreen = new HelpScreen();
		helpScreen.buildUi();
		
		// Show everything
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	
	public DialogResult yesNoCancelDialog(String title, String message) {
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
	
	
	public void showAlert(String title, String message) {
		JOptionPane.showMessageDialog(mainFrame, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	public File selectDiretory() {
		JFileChooser fc = new JFileChooser();
		
		fc.setDialogTitle("Choose a directory...");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setAcceptAllFileFilterUsed(false);
		
		if(fc.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		else 
			return null;
	}
	
	
	
	public void showHelp() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				helpScreen.show();
			}
		});
	}
	
	
	public void quit() {
		System.exit(0);
	}
}
