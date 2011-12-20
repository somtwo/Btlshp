package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Location;
import btlshp.entities.Map;
import btlshp.entities.Ship;

class Shoot extends Turn implements Serializable{
	private static final long serialVersionUID = -605750640559980738L;
	private int shipId;
	private Location loc;
	private boolean success = false;

	public Shoot(Ship s, Location loc) {
		this.shipId = s.getConstructID();
		this.loc = loc;
	}

	@Override
	public void executeTurn(Map map) {
		try{
			Ship s = map.getShip(shipId);
			map.fireGuns(s, loc);
			success = true;
		}catch(IllegalStateException e){
			success = false;
		}
	}

	@Override
	public boolean wasSuccessful() {
		return success;
	}
	@Override
	public String toString(){
		return "shoot";
	}

}