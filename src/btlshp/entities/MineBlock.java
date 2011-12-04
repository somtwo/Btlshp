package btlshp.entities;

import btlshp.enums.GraphicId;
import btlshp.enums.Weapon;

public class MineBlock extends Block {

	MineBlock() {
		graphicId = GraphicId.Mine;
	}
	
	@Override
	public void takeHit(Weapon fromWeapon) {
	}
}
