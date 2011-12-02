package btlshp;

import javax.swing.SwingUtilities;

import btlshp.ui.Btlshp;

public class Driver {
	private static Btlshp game;
	
	public static void main (String [] args) {
		game = new Btlshp();
		
		// This makes sure we're in the even dispatching thread before the gui is created.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				game.buildUI();
			}
		});
	}
}
