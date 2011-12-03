package btlshp;

import javax.swing.SwingUtilities;


public class Btlshp {
	private static BtlshpGame game;
	
	public static void main (String [] args) {
		game = new BtlshpGame();
		
		// This makes sure we're in the even dispatching thread before the gui is created.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				game.buildUI();
			}
		});
	}
	
	
	public static BtlshpGame getGame() {
		return game;
	}
}
