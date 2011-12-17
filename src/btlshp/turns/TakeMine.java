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
	private Ship s;
	private Map m;
	private boolean success = false;

	public TakeMine(Map m, Ship s, Location loc) {
		this.s = s;
		this.loc = loc;
		this.m = m;
	}

	@Override
	public void executeTurn() {
		try{
			m.pickupMine(s, loc);
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

	@Override
	public void setMap(Map m) {
		this.m = m;		
	}
}