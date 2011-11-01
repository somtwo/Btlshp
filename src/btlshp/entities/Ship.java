package btlshp.entities;

import btlshp.enums.Weapon;

public class Ship extends Construct {
	private boolean hasGun, hasTorpedo, hasMinePlacement;
	private int     maxForwardMove, maxSideMove, maxBackMove;
	
	private Ship() {
		
	}
	
	/**
	 * Returns true if this ship can fire guns.
	 * @returns true if the ship has a gun to fire, false otherwise
	 */
	boolean canFireGun() {
		return hasGun;
	}

	/**
	 * Returns the maximum number of map blocks a ship can travel forward in a turn
	 * @returns the maximum number of map blocks a ship can travel forward in a turn
	 */
	int getMaxForwardMove() {
		return maxForwardMove;
	}

	/**
	 * Returns the maximum number of map blocks a ship can travel sideways in a turn
	 * @returns the maximum number of map blocks a ship can travel sideways in a turn
	 */
	int getMaxSideMove() {
		return maxSideMove;
	}

	/**
	 * Returns the maximum number of map blocks a ship can travel backwards in a turn
	 * @returns the maximum number of map blocks a ship can travel backwards in a turn
	 */
	int getMaxReverseMove() {
		return maxBackMove;
	}

	/**
	 * Returns true if this ship can fire a torpedo.
	 * @returns true if the ship can fire a torpedo, false otherwise
	 */
	boolean canFireTorpedo() {
		return hasTorpedo;
	}

	/**
	 * Returns true if this ship can place a mine.
	 * @returns true if the ship can place a mine, false otherwise
	 */
	boolean canPlaceMine() {
		return hasMinePlacement;
	}

	/**
	 * Returns true if this ship can pick up a mine.
	 * @returns true if the ship can pick up a mine, false otherwise
	 */
	boolean canPickUpMine() {
		return hasMinePlacement;
	}

	/**
	* Determains if and how far the ship can fire the given weapon
	* @param weapon Weapon in question
	* @return 0 if not, else gives range of weapon.
	*/
	boolean canFireWeapon(Weapon weapon) {
		return weapon == Weapon.Gun ? hasGun :
			   weapon == Weapon.Torpedo ? hasTorpedo : false;
	}

	/**
	* Factory method for Cruiser
	* @returns the Ship Constructed
	*/
	static Ship buildCruiser() {
		return null;
	}

	/**
	* Factory method for TorpedoBoat
	* @returns the Ship Constructed
	*/
	static Ship buildTorpedoBoat() {
		return null;
	}

	/**
	* Factory method for Destroyer
	* @returns the Ship Constructed
	*/
	static Ship buildDestroyer() {
		return null;
	}

	/**
	* Factory method for MineSweeper
	* @returns the Ship Constructed
	*/
	static Ship buildMineSweeper() {
		return null;
	}
}
