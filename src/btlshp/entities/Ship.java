package btlshp.entities;

import btlshp.enums.ShipType;
import btlshp.enums.Weapon;

public class Ship extends Construct {
	private boolean hasGun, hasTorpedo, hasMinePlacement;
	private int     maxForwardMove, maxSideMove, maxBackMove, maxGunRange;
	
	/**
	* Constructor for Ship
	* Returns the Ship Constructed
	* @param owner   Player the base belongs to.
	* @param blocks  The blocks to use for the given ship.
	*/
	Ship(Player owner, ConstructBlock inBlocks[], ShipType type) {
		pl = owner;
		this.blocks = inBlocks;
	}
	
	/**
	 * Returns true if this ship can fire guns.
	 * @returns true if the ship has a gun to fire, false otherwise
	 */
	boolean canFireGun() {
		return hasGun;
	}
	
	int getMaxGunRange(){
		return maxGunRange;
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
