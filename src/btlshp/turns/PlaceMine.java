package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Location;
import btlshp.entities.Map;
import btlshp.entities.Ship;

class PlaceMine extends Turn implements Serializable{
	private static final long serialVersionUID = -4336837927576289067L;
	private Location loc;
	private int shipId;
	private boolean success = false;
	
	public PlaceMine(Ship s, Location loc) {
		this.loc = loc;
		this.shipId = s.getConstructID();
	}

	@Override
	public void executeTurn(Map map) {
		try{
			map.placeMine(map.getShip(shipId), loc);
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
}