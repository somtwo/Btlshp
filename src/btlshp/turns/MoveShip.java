package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Map;
import btlshp.entities.Ship;
import btlshp.enums.Direction;

class MoveShip extends Turn implements Serializable{
	
	private static final long serialVersionUID = 4599021282070269467L;
	private int shipId;
	private Direction dir;
	private int distance;
	private boolean success = false;

	public MoveShip(Ship s, Direction dir, int distance) {
		this.shipId = s.getConstructID();
		this.dir = dir;
		this.distance = distance;
	}
	
	@Override
	public void executeTurn(Map map) {
		try {
			map.move(map.getShip(shipId), dir,distance);
			success = true;
		} catch(IllegalStateException e){
			e.printStackTrace();
			success = false;
		}
		
	}

	@Override
	public boolean wasSuccessful() {
		return true;
	}

	@Override
	public String toString(){
		return "moveShip";
	}
}