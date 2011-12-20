package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Location;
import btlshp.entities.Map;
import btlshp.entities.Ship;

class TakeMine extends Turn implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1991458862311470623L;
	private Location loc;
	private int shipId;
	private boolean success = false;

	public TakeMine(Ship s, Location loc) {
		this.shipId = s.getConstructID();
		this.loc = loc;
	}

	@Override
	public void executeTurn(Map map) {
		try{
			Ship s = map.getShip(shipId);
			map.pickupMine(s, loc);
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
		return "takeMine";
	}
}