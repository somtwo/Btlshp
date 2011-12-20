package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.ConstructBlock;
import btlshp.entities.Map;
import btlshp.entities.Ship;

class RepairShip extends Turn implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 89817140305258661L;
	private int shipId;
	private int blockId;
	private boolean success = false;

	RepairShip(Ship s, ConstructBlock repairBlock) {
		this.shipId = s.getConstructID();
		this.blockId = repairBlock.getId();
	}

	@Override
	public void executeTurn(Map map) {
		Ship s = map.getShip(shipId);
		s.AssesRepair(s.getBlockById(blockId));
		success = true;
	}

	

	@Override
	public boolean wasSuccessful() {
		return success;
	}
	
	@Override
	public String toString(){
		return "repairShip";
	}
}