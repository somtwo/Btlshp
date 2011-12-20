package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Map;
import btlshp.entities.Ship;

public class RotateShip180 extends Turn implements Serializable {
	private static final long serialVersionUID = 5885764812060992691L;
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
		Ship ship = m.getShip(s.getConstructID());
		m.rotateShip180(ship, leftTurn);
	}

}
