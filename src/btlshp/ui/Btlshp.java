package btlshp.ui;

import btlshp.enums.AppState;

public class Btlshp {
	private AppState appState;
	private MainUI   mainUi;
	
		public Btlshp() {
		appState = AppState.NoGame;
		mainUi = new MainUI(this);
	}
	
	
	/**
	 * Creates the main UI for the game
	 */
	public void buildUI()
	{
		mainUi.makeGUI();
	}
	
	
	/**
	 * Handles a UI-side new game request.
	 */
	public void newGame() {
		if(appState == AppState.GameInProgress) {
			if(mainUi.yesNoCancelDialog("Start new game?", "You have to forfeit the current game to start a new one, are you sure?") != DialogResult.Yes)
				return;
		}
		
		// TODO: New game
	}
	
	
	/**
	 * Gets the current state of the app
	 * @return The state of the app
	 */
	public AppState getAppState() {
		return appState;
	}
}
