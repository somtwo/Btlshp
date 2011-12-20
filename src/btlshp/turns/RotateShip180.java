package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Map;
import btlshp.entities.Ship;

public class RotateShip180 extends Turn implements Serializable {
	private static final long serialVersionUID = 5885764812060992691L;
	private int shipId;
	private boolean leftTurn;
	
	public RotateShip180(Ship s, boolean leftTurn) {
		this.shipId = s.getConstructID();
		this.leftTurn = leftTurn;
	}
	
	@Override
	boolean wasSuccessful() {
		return true;
	}

	@Override
	public void executeTurn(Map map) {
		Ship ship = map.getShip(shipId);
		map.rotateShip180(ship, leftTurn);
	}

}
