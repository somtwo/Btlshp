package btlshp.entities;

import java.io.Serializable;

import btlshp.enums.GraphicId;
import btlshp.enums.Weapon;

public class ReefBlock extends Block implements Serializable{


	private static final long serialVersionUID = -2071545020695045057L;

	ReefBlock() {
		graphicId = GraphicId.Reef;
	}
	
	@Override
	public void takeHit(Weapon fromWeapon, int x, int y) {
	}

	@Override
	public void takeRepair() {
		// Cannot Repair a reef.		
	}

}
