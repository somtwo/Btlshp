package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Location;
import btlshp.entities.Map;
import btlshp.entities.Ship;

class PlaceMine extends Turn implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4336837927576289067L;
	private Map m;
	private Location loc;
	private Ship s;
	private boolean success = false;
	public PlaceMine(Map m, Ship s, Location loc) {
		this.m = m;
		this.loc = loc;
		this.s = s;
	}

	@Override
	public void executeTurn() {
		try{
			m.placeMine(s, loc);
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
		return "placeMine";
	}

	@Override
	public void setMap(Map m) {
		this.m = m;
		
	}
}