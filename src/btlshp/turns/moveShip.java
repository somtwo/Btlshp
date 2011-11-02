package btlshp.turns;

import btlshp.entities.Ship;
import btlshp.enums.Direction;

public class moveShip extends Turn {
	private Ship s;
	private Direction dir;
	private int distance;
	moveShip(Ship s, Direction dir, int distance){
		this.s = s;
		this.dir = dir;
		this.distance = distance;
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
