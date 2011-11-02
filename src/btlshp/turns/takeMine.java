package btlshp.turns;

import btlshp.entities.Location;

public class takeMine extends Turn {
	private Location loc;
	takeMine(Location loc){
		this.loc = loc;
	}
	@Override
	void executeTurn() {
		// TODO Auto-generated method stub

	}

	@Override
	void sendTurn() {
		// TODO Auto-generated method stub

	}

	@Override
	boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}

}
