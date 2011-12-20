package btlshp;

import java.applet.*;
import java.net.URL;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import btlshp.entities.Construct;
import btlshp.entities.Map;
import btlshp.entities.Player;
import btlshp.enums.AppState;
import btlshp.turns.Turn;
import btlshp.turns.TurnFactory;
import btlshp.ui.DialogResult;
import btlshp.ui.MainUI;

public class BtlshpGame {
	private AppState appState;
	private static MainUI   mainUi;
	private Player   localPlayer, remotePlayer;
	private File 	 gameDir;
	private static AudioClip audioClip;
	private static URL urlForAudio;
	private static boolean isSongPlaying;
	public BtlshpGame() {
		localPlayer = remotePlayer = null;
		mainUi = new MainUI();
		appState = AppState.NoGame;
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
		if(appState != AppState.NoGame) {
			if(mainUi.yesNoCancelDialog("Start new game?", "You have to forfeit the current game to start a new one, are you sure?") != DialogResult.Yes)
				return;
		}
		
		gameDir = mainUi.selectDiretory("Chose a hosting location");
		
		if(gameDir != null) {
			localPlayer = new Player();
			remotePlayer = new Player();
			Map m = new Map(localPlayer, remotePlayer);
			mainUi.setMap(m);
			
			setAppState(AppState.LocalTurn);
			ObjectOutputStream objOut = null;
			FileOutputStream fileOut = null;
			try {
				fileOut = new FileOutputStream(gameDir.getAbsolutePath() +"/"+ 0+".ser");
				objOut = new ObjectOutputStream(fileOut);
				
				objOut.writeObject(m);
				objOut.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			File f = new File(gameDir.getAbsolutePath() +"/"+ 0+".ser");
			setAppState(AppState.RemoteTurn);
			waitForTurn(f.lastModified());
			mainUi.updateMainMenu();
		}
	}
	public void joinGame(){
		gameDir = mainUi.selectDiretory("Choose a shared directory");
		
		if(gameDir != null){
			outputMessage("Joining game " + gameDir.getPath());
			String filePath = gameDir.getPath()+"/"+0+".ser";
			FileInputStream fileIn = null;
			ObjectInputStream objIn = null;
			try{
				fileIn = new FileInputStream(filePath);
				objIn = new ObjectInputStream(fileIn);
				Map loadMap= null;
				try {
					loadMap =(Map) objIn.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				localPlayer = loadMap.getRightPlayer();
				remotePlayer = loadMap.getLeftPlayer();
				mainUi.setMap(loadMap);
				setAppState(AppState.LocalTurn);
				sendTurn(TurnFactory.pass());
				
				mainUi.updateMainMenu();
			}catch(IOException e){
				System.err.println("IOException: File Path: "+filePath);
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Handles a UI-side help request.
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
	
	
	private void setAppState(AppState newState) {
		appState = newState;
		mainUi.updateStatus();
		mainUi.updateMainMenu();
		
	}
	
	
	/**
	 * Handles a UI-side quit event.
	 */
	public void quitGame() {
		if(appState != AppState.NoGame) {
			mainUi.showAlert("Game in progress.", "Please either forfeit or save a game before quitting.");
		}
		else if (mainUi.yesNoCancelDialog("Do you really want to quit?", "Please don't go, the Socialist Commies will win!") == DialogResult.Yes) {
			System.exit(0);
		}
	}
	
	
	/**
	 * Handles a UI-side forfeit game event.
	 */
	public void forfeitGame() {
		if(appState == AppState.LocalTurn &&
			mainUi.yesNoCancelDialog("Forfeit", "Are you sure you wish to forfeit? This may lead to eternal shame!") == DialogResult.Yes) {
			
			Btlshp.getGame().sendTurn(TurnFactory.requestSurrender());
			
			localPlayer = remotePlayer = null;
			mainUi.setMap(null);
			setAppState(AppState.NoGame);
			mainUi.updateMainMenu();
			return;
		} else {
			mainUi.showNotificationDialog("forfeit Game", "you can only forefit the game when it is you turn.");
			return;
		}
	}
	
	
	/**
	 * Handles game-logic when the other player forfeits.
	 */
	public void otherPlayerForfeit() {
		mainUi.showAlert("Other player has quit!", "It seems you were simply too cunning, fearless, and generally superior for the other player to handle. You have won the game!");
		localPlayer = remotePlayer = null;
		mainUi.setMap(null);
		setAppState(AppState.NoGame);
		mainUi.updateMainMenu();
	}
	
	
	/**
	 * Handles a UI-side save game event.
	 */
	public void saveGame() {
		if(appState == AppState.NoGame) 
			return;
		if(appState == AppState.RemoteTurn) {
			mainUi.showNotificationDialog("Save Game", "you can only save and exit the game when it is you turn.");
			return;
		}
			
		if (localPlayer.getPlayerID() != mainUi.getMap().getLeftPlayer().getPlayerID())
		{
			JOptionPane.showMessageDialog(null, "You can't save because you joined game.");
			return;
		}
		File f = mainUi.selectSaveFile("Select a file to save");
		
		if(f != null) {
			outputMessage("Save file: " + f.getPath());
			ObjectOutputStream objOut = null;
			FileOutputStream fileOut = null;
			try {
				fileOut = new FileOutputStream(f.getPath());
				objOut = new ObjectOutputStream(fileOut);
				
				objOut.writeObject(mainUi.getMap());
				objOut.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			Btlshp.getGame().sendTurn(TurnFactory.requestPostponeGame());
			localPlayer = remotePlayer = null;
			mainUi.setMap(null);
			setAppState(AppState.NoGame);
			mainUi.updateMainMenu();
		}
	}
	
	public void otherPlayerSaved() {
		mainUi.showAlert("Other player has Saved!", "It seems the other player has saved and quit the game.  You can resume the game later.  \n" +
				" Make sure that he enters the game first though otherwise you will be the other player. :)");
		localPlayer = remotePlayer = null;
		mainUi.setMap(null);
		setAppState(AppState.NoGame);
		mainUi.updateMainMenu();
	}
	
	/**
	 * Handles a UI-side restore game event.
	 */
	public void restoreGame() {
	
		File f = mainUi.selectSaveFile("Select a file to restore from");
		
		if(f != null) {
			outputMessage("Restore file: " + f.getPath());
			String filePath = f.getPath();
			FileInputStream fileIn = null;
			ObjectInputStream objIn = null;
			try{
				fileIn = new FileInputStream(filePath);
				objIn = new ObjectInputStream(fileIn);
				Map loadMap= null;
				try {
					loadMap = (Map) objIn.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				localPlayer = loadMap.getLeftPlayer();
				remotePlayer = loadMap.getRightPlayer();
				mainUi.setMap(loadMap);
				setAppState(AppState.LocalTurn);
				mainUi.updateMainMenu();
			}catch(IOException e){
				System.err.println("IOException: File Path: "+filePath);
				e.printStackTrace();
			}
		}
	}
	
	
	
	public Player getLocalPlayer() {
		return localPlayer;
	}
	
	
	public Player getRemotePlayer() {
		return remotePlayer;
	}

	
	/**
	 * Outputs a message to a screen element used to display action messages for the player.
	 * 
	 * @param msg
	 */
	public void outputMessage(String msg) {
		mainUi.outputMessage(msg);
	}


	public void shipDestroyed(Construct destroyedShip) {
	}
	
	
	/**
	 * Used by the game to send a turn and place the game into a waiting-for-turn state.
	 * @param t   Turn object to send to other player.
	 */
	public void sendTurn(Turn t) {
		if(appState != AppState.LocalTurn)
			throw new IllegalStateException("NOT YO TURN!");
		long modified =t.writeOut(gameDir.getAbsolutePath()); 
		if(modified<0){
			throw new IllegalStateException("Error Writing out turn");
		}
	    setAppState(AppState.RemoteTurn);

		waitForTurn(modified);
		// TODO: Send turn
	}
	/**
	 * Read the last modified time of a file every 5 seconds
	 * @param modified time the file was last modified
	 */
	private void waitForTurn(final long modified) {
		Timer t = new Timer();
		
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				String filePath = gameDir.getPath();
				File f = new File(filePath+"/game.ser");
				
				if(f.lastModified()> modified){
					FileInputStream fileIn = null;
					ObjectInputStream objIn = null;
					try{
						fileIn = new FileInputStream(filePath+"/game.ser");
						objIn = new ObjectInputStream(fileIn);
						Turn loadTurn= null;
						try {
							loadTurn = (Turn) objIn.readObject();
							objIn.close();
							fileIn.close();
							mainUi.getMap().clearTurnFlags();
							mainUi.refresh();
							loadTurn.executeTurn(mainUi.getMap());
							setAppState(AppState.LocalTurn);
							cancel();
							
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					
						
					
					}catch(IOException e){
						System.err.println("IOException: File Path: "+filePath);
						e.printStackTrace();
					}
				}	
				
			}
		
		}, 500, 500);
	}


	/**
	 * Called when one player looses all ships.
	 */
	public void gameOver() {
		setAppState(AppState.NoGame);
		
		boolean locallost = localPlayer.getShips().length == 0;
		boolean remotelost = remotePlayer.getShips().length == 0;
		
		if(locallost && remotelost)
			mainUi.showAlert("Draw!", "You and your enemy have reached a draw!\n100 points for both of you!");
		else if(locallost)
			mainUi.showAlert("Defeat!", "You have been defeated by your opponent!\nYour approximate score is 0.\nYour actual score is 0");
		else
			mainUi.showAlert("Victory!", "You have vanquished your foe!");
	}

	
	public void setupMusic() {
		if (!isSongPlaying) {
			urlForAudio = getClass().getResource("audio/BackgroundWebsite.mid");
			//if (audioClip != null)  //--> makes it not play audio
			System.out.println(audioClip);
			audioClip = Applet.newAudioClip(urlForAudio);
			isSongPlaying = true;
		}
		return;
	}

	/**
	 * Handles music
	 */
	public  static void playsMusic() {
		DialogResult result;
		result = mainUi.yesNoCancelDialog("Music", "BONUS FEATURE: Would you like to play music? \n" +
				"Come back and press no to stop the music at any time.");
		
		if(	result == DialogResult.Yes) {
			if(audioClip != null) audioClip.loop();
			return;
		} else if (result == DialogResult.No) {
			audioClip.stop();
			return ;
		} else {
			return ;
		}
	}


}
