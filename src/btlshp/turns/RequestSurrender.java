package btlshp.turns;

import java.io.Serializable;

import btlshp.Btlshp;
import btlshp.entities.Map;

class RequestSurrender extends Turn implements Serializable{


	private static final long serialVersionUID = -4395558489216020334L;
	@Override
	/**
	 * Opponent requests to quit(surrender) game
	 */
	public void executeTurn(Map m) {
		// TODO Implementation UI dependent
		//Generate dialog for the player to accept or reject surrender
		Btlshp.getGame().otherPlayerForfeit();
	}


	@Override
	public boolean wasSuccessful() {
		// TODO Implementation UI dependent
		return true;
	}
	
	@Override
	public String toString(){
		return "requestSurrender";
	}
}