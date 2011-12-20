package btlshp.turns;

import btlshp.entities.Map;
import btlshp.entities.Ship;

public class RotateShip180 extends Turn {

	private Map  m;
	private Ship s;
	private boolean leftTurn;
	
	public RotateShip180(Map m, Ship s, boolean leftTurn) {
		this.m = m;
		this.s = s;
		this.leftTurn = leftTurn;
	}
	
	@Override
	public void setMap(Map m) {
		this.m = m;
	}

	@Override
	boolean wasSuccessful() {
		return true;
	}

	@Override
	public void executeTurn() {
		m.rotateShip180(s, leftTurn);
	}

}
