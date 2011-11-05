package btlshp.entities;

import btlshp.enums.Weapon;

public class Block {
	
	/**
	 * Checks if this block is destroyed
	 * @returns True if this block is destroyed
	 */
	public void takeHit(Weapon fromWeapon){
		//Allows for anyBlock to "take a hit"... but many blocks will do nothing by default
	}
}
