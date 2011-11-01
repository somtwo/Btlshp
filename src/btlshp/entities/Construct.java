package btlshp.entities;

import btlshp.enums.Weapon;

public abstract class Construct {
	ConstructBlock blocks[];
	
	/**
	* Computes the damage of the Construct when a block is damaged.
	* @param hitBlock The block damaged
	* @param weaponUsed The Weapon used to do the damaging
	*/
	abstract void assessDamage(Block hitBlock, Weapon weaponUsed);

	/**
	* Computes the damage to the Construct when a block is damaged.
	* @param hitBlock The block damaged
	*/
	abstract void damageBlock(Block damagedBlock);

	/**
	* Computes the repair to the Construct when a block is repaired.
	* @param hitBlock The block damaged
	*/
	abstract void repairBlock(Block repairBlock);
}
