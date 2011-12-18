package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Location;
import btlshp.entities.Map;
import btlshp.entities.Ship;

class Shoot extends Turn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -605750640559980738L;
	private Map m;
	private Ship s;
	private Location loc;
	private boolean success = false;

	public Shoot(Map m, Ship s, Location loc) {
		this.m = m;
		this.s = s;
		this.loc = loc;
	}

	@Override
	public void executeTurn() {
		try{
			m.fireGuns(s, loc);
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

	@Override
	public void setMap(Map m) {
		this.m = m;		
	}
}