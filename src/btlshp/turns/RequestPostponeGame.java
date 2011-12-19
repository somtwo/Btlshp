package btlshp.turns;

import java.io.Serializable;

import btlshp.Btlshp;
import btlshp.entities.Map;

class RequestPostponeGame extends Turn implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2537600636201002718L;
	@Override
	/**
	 * Opponent would like to PostPone Game
	 */
	public void executeTurn() {
		// TODO Implementation UI dependent
		//Generate a dialog box with player and allow player to accept or reject
		Btlshp.getGame().otherPlayerSaved();
	}
	@Override
	public boolean wasSuccessful() {
		// TODO Implementation UI dependent
		return true;
	}
	@Override
	public String toString(){
		return "requestPostponeGame";
	}
	@Override
	public void setMap(Map m) {
		// TODO Auto-generated method stub
		
	}
}