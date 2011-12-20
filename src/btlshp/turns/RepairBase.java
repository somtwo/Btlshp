package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Base;
import btlshp.entities.ConstructBlock;
import btlshp.entities.Map;

class RepairBase extends Turn implements Serializable{
	private static final long serialVersionUID = 3486672126675014858L;
	private int baseId, blockId;
	private boolean success = false;

	RepairBase(Base b, ConstructBlock repairBlock) {
		this.baseId = b.getConstructID();
		this.blockId = repairBlock.getId();
	}

	@Override
	public void executeTurn(Map map) {
		Base b = map.getBase(baseId);
		b.AssesRepair(b.getBlockById(blockId));
		success = true;
	}
	@Override
	public boolean wasSuccessful() {
		return success;
	}
	@Override
	public String toString(){
		return "repairBase";
	}
}