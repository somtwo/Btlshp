package btlshp.turns;

import java.io.Serializable;

import btlshp.Btlshp;
import btlshp.entities.Map;
import btlshp.entities.Ship;

class LaunchTorpedo extends Turn implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1790493199271040630L;
	private int shipId;
	private boolean success = false;


	LaunchTorpedo(Ship ship) {
		this.shipId = ship.getConstructID();
	}

	@Override
	public void executeTurn(Map map) {
		try {
			map.fireTorpedo(map.getShip(shipId));
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
		return "launchTorpedo";
	}
}