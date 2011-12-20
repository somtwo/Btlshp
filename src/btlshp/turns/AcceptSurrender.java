package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Map;

class AcceptSurrender extends Turn implements Serializable{

	private static final long serialVersionUID = -8163598235191879797L;
	@Override
	/**
	 * Opponent has accepted request to surrender
	 */
	public void executeTurn(Map map) {
		// TODO Implementation UI dependent
		//Generate dialog informing player opponent has accepted surrender
	}
	
	
	@Override
	public boolean wasSuccessful() {
		// TODO Implementation UI dependent
		return true;
	}
	@Override
	public String toString(){
		return "acceptSurrender";
	}
}