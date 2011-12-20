package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Map;
import btlshp.entities.Ship;
import btlshp.enums.Direction;

class RotateShip extends Turn implements Serializable {
	private int shipId;
	private Direction dir;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2197899809799342874L;
	RotateShip(Ship s, Direction dir){
		this.shipId = s.getConstructID();
		this.dir = dir;
	}
	
	@Override
	boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void executeTurn(Map map) {
		map.rotateShip(map.getShip(shipId), dir);
	}
	
}