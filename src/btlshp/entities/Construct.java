package btlshp.entities;

import btlshp.enums.Weapon;
import btlshp.testjavas.Block;

public abstract class Construct {
	/**
	* Computes the damage of the Construct when a block is damaged.
	* @param hitBlock The block damaged
	* @param weaponUsed The Weapon used to do the damaging
	*/
	void assessDamage(Block hitBlock, Weapon weaponUsed) {
	}// = 0;

	/**
	* Computes the damage to the Construct when a block is damaged.
	* @param hitBlock The block damaged
	*/
	void damageBlock(Block damagedBlock) {
	}// = 0;

	/**
	* Computes the repair to the Construct when a block is repaired.
	* @param hitBlock The block damaged
	*/
	void repairBlock(Block repairBlock) {
	}// = 0;
}
