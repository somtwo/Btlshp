package btlshp.turns;

import btlshp.entities.Location;
import btlshp.entities.Ship;

public class shoot extends Turn {
	private Ship s;
	private Location loc;
	shoot(Ship s, Location loc){
		this.s = s;
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
