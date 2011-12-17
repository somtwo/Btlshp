package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Map;
import btlshp.entities.Ship;
import btlshp.enums.Direction;

class RotateShip extends Turn implements Serializable{
	private Map m;
	private Ship s;
	private Direction dir;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2197899809799342874L;
	RotateShip(Map m, Ship s, Direction dir){
		this.m = m;
		this.s = s;
		this.dir = dir;
	}
	@Override
	public void setMap(Map m) {
		this.m = m;
		
	}

	@Override
	boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void executeTurn() {
		m.rotateShip(s, dir);
		
	}
	
}