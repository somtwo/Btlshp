package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Map;

class ConfirmPostponeGame extends Turn implements Serializable{

	private static final long serialVersionUID = -7601913526703183133L;
	@Override
	/**
	 * Opponent accepted postponing game
	 */
	public void executeTurn() {
		// TODO Implementation UI dependent
		
	}


	@Override
	public boolean wasSuccessful() {
		// TODO Implementation UI dependent
		return true;
	}
	@Override
	public String toString(){
		return "confirmPostponeGame";
	}


	@Override
	public void setMap(Map m) {
		// TODO Auto-generated method stub
		
	}
}