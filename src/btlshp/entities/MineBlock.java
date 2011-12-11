package btlshp.entities;

import btlshp.Btlshp;
import btlshp.BtlshpGame;
import btlshp.enums.GraphicId;
import btlshp.enums.Weapon;

public class MineBlock extends Block {

	MineBlock() {
		graphicId = GraphicId.Mine;
	}
	
	@Override
	public void takeHit(Weapon fromWeapon) {
		// TODO This Function somehow needs to call the map's explode mine function when it takes damage... No such link exists.
	}

	@Override
	public void takeRepair() {
		//Cannot Repair a mine.
	}
}
