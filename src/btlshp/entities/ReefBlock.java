package btlshp.entities;

import btlshp.enums.GraphicId;
import btlshp.enums.Weapon;

public class ReefBlock extends Block {

	ReefBlock() {
		graphicId = GraphicId.Reef;
	}
	
	@Override
	public void takeHit(Weapon fromWeapon) {
	}

}
