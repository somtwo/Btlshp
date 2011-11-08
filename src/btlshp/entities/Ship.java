package btlshp.entities;

import btlshp.enums.Direction;

public class Ship extends Construct {
	private boolean hasGun, hasTorpedo, hasMinePlacement, hasSonar, isArmored;
	private int     maxForwardMove, maxSideMove, maxBackMove, maxGunRange, maxSonarRange;
	
	/**
	* Constructor for Ship
	* Returns the Ship Constructed
	* @param owner   Player the base belongs to.
	* @param blocks  The blocks to use for the given ship.
	*/
	
//had to make ship public for JUnit testing make private again ~Z
	public Ship(Player owner, boolean isArmored, boolean gun, boolean torpedo, boolean mine, boolean Sonar, int forward, int side, int back, int gunRange, int radarRange, int sonarRange, int numberOfBlocks) {
		pl = owner;
		hasGun = gun;
		hasTorpedo = torpedo;
		hasMinePlacement = mine;
		hasSonar = Sonar;
		maxForwardMove = forward;
		maxSideMove = side;
		maxBackMove = back;
		maxGunRange = gunRange;
		maxSonarRange = sonarRange;
		maxRadarRange = radarRange;
		if (isArmored){
			blocks = new ArmoredConstructBlock[numberOfBlocks];
			for (int i = 0; i<numberOfBlocks ; i++){
				blocks[i] = new ArmoredConstructBlock(this);
			}	
		}
		else {
			blocks = new ConstructBlock[numberOfBlocks];
			for (int i = 0; i<numberOfBlocks ; i++){
				blocks[i] = new ConstructBlock(this);
			}	
		}
	}
	
	/**
	 * Returns true if this ship can fire guns.
	 * @returns true if the ship has a gun to fire, false otherwise
	 */
	public boolean canFireGun() {
		return hasGun;
	}
	
	/**
	 * @return the Range of the guns from any point on the ship, 0 if no guns.
	 */
	public int getMaxGunRange(){
		return maxGunRange;
	}

	/**
	 * Returns the maximum number of map blocks a ship can travel forward in a turn
	 * @returns the maximum number of map blocks a ship can travel forward in a turn
	 */
	public int getMaxForwardMove() {
		return maxForwardMove;
	}

	/**
	 * Returns the maximum number of map blocks a ship can travel sideways in a turn
	 * @returns the maximum number of map blocks a ship can travel sideways in a turn
	 */
	public int getMaxSideMove() {
		return maxSideMove;
	}

	/**
	 * Returns the maximum number of map blocks a ship can travel backwards in a turn
	 * @returns the maximum number of map blocks a ship can travel backwards in a turn
	 */
	public int getMaxReverseMove() {
		return maxBackMove;
	}

	/**
	 * Returns true if this ship can fire a torpedo.
	 * @returns true if the ship can fire a torpedo, false otherwise
	 */
	public boolean canFireTorpedo() {
		return hasTorpedo;
	}

	/**
	 * Returns true if this ship can place a mine.
	 * @returns true if the ship can place a mine, false otherwise
	 */
	public boolean canPlaceMine() {
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
	 * @return true if this ship has Sonar capability, false otherwise
	 */
	public boolean hasSonar(){
		return hasSonar;
	}
	
	/**
	 * @return Range of sonar, 0 if this ship cannot use Sonar.
	 */
	public int getSonarRange(){
		return maxSonarRange;
	}
	
	/**
	 * @return true if the ship is constructed with armored blocks, false otherwise.
	 */
	boolean hasArmor(){
		return isArmored;
	}
	/**
	* Factory method for Cruiser
	* @returns the Ship Constructed
	*/
	public static Ship buildCruiser(Player owner) {
		Ship myCruiser = new Ship(owner, false, true, false, false, false, 10, 1, 1, 5, 6, 0, 5);
		return myCruiser;
	}

	/**
	* Factory method for TorpedoBoat
	* @returns the Ship Constructed
	*/
	public static Ship buildTorpedoBoat(Player owner) {
		Ship myTorpedoBoat = new Ship(owner, false, true, true, false, false, 8, 1, 1, 4, 5, 0, 4);
		return myTorpedoBoat;
	}
	// private Ship(Player owner, boolean isArmored, boolean gun, boolean torpedo, boolean mine, boolean Sonar, int forward, int side, int back, int gunRange, int radarRange, int sonarRange, int numberOfBlocks) {
	/**
	* Factory method for Destroyer
	* @returns the Ship Constructed
	*/
	public static Ship buildDestroyer(Player owner) {
		Ship myDestroyer = new Ship(owner, false, false, true, false, false, 6, 1, 1, 0, 4, 0, 3);
		return myDestroyer;
	}

	/**
	* Factory method for MineSweeper
	* @returns the Ship Constructed
	*/
	public static Ship buildMineSweeper(Player owner) {
		Ship myMineSweeper = new Ship(owner, true, false, false, true, true, 4, 1, 1, 0, 2, 2, 2);
		return myMineSweeper;
	}

	public Player getPlayer() {
		return pl;
	}
	
	
	/**
	 * Gets the left-most x-coordinate the ship occupies
	 * @return
	 */
	public int getx1() {
		if(myDir == Direction.West)
			return myLoc.getx() - blocks.length; 
		return myLoc.getx();
	}
	
	/**
	 * Gets the right-most x-coordinate the ship occupies
	 * @return
	 */
	public int getx2() {
		if(myDir == Direction.East)
			return myLoc.getx() + blocks.length; 
		return myLoc.getx();
	}
	
	/**
	 * Gets the top-most y-coordinate the ship occupies
	 * @return
	 */
	public int gety1() {
		if(myDir == Direction.North)
			return myLoc.gety() - blocks.length; 
		return myLoc.gety();
	}
	
	
	/**
	 * Gets the bottom-most y-coordinate the ship occupies
	 * @return
	 */
	public int gety2() {
		if(myDir == Direction.South)
			return myLoc.gety() + blocks.length; 
		return myLoc.gety();
	}
}
