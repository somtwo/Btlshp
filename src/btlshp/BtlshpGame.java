package btlshp;

import java.io.File;

import btlshp.enums.AppState;
import btlshp.ui.DialogResult;
import btlshp.ui.MainUI;

public class BtlshpGame {
	private AppState appState;
	private MainUI   mainUi;
	
	public BtlshpGame() {
		appState = AppState.NoGame;
		mainUi = new MainUI();
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
		
		File gameDir = mainUi.selectDiretory();
		
		if(gameDir != null) {
			// TODO: New game
			mainUi.showAlert("Result!", gameDir.getAbsolutePath());
		}
	}
	
	
	/**
	 * HAndles a UI-side help request.
	 */
	public void helpScreen() {
		mainUi.showHelp();
	}
	
	/**
	 * Gets the current state of the app
	 * @return The state of the app
	 */
	public AppState getAppState() {
		return appState;
	}
	
	
	public void quitGame() {
		if(appState == AppState.GameInProgress) {
			// TODO: The user either has to forfeit or save game.
		}
		
		System.exit(0);
	}
}
