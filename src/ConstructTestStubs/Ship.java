package ConstructTestStubs;

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
	private Ship(Player owner, boolean isArmored, boolean gun, boolean torpedo, boolean mine, boolean Sonar, int forward, int side, int back, int gunRange, int radarRange, int sonarRange, int numberOfBlocks) {
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
	public Location[] getRotateLeftLocations(){
		if (myLoc == null) return null;
		if (blocks.length < 2 || blocks.length >5) return null;
		Location[] locations = new Location[0];
		// Account for Destroyer's odd pivot point
		if (this.blocks.length == 3){
				locations = new Location[4];
				switch(myDir){
					case North:{
						locations[0] = new Location(myLoc.getx()-1, myLoc.gety());
						locations[1] = new Location(myLoc.getx()-1, myLoc.gety()+1);	
						locations[2] = new Location(myLoc.getx()+1, myLoc.gety()+1);
						locations[3] = new Location(myLoc.getx()+1, myLoc.gety()+2);
						break;
					}
					case East:{
						locations[0] = new Location(myLoc.getx(), myLoc.gety()-1);
						locations[1] = new Location(myLoc.getx()-1, myLoc.gety()-1);	
						locations[2] = new Location(myLoc.getx()-1, myLoc.gety()+1);
						locations[3] = new Location(myLoc.getx()-2, myLoc.gety()+1);
						break;
					}
					case South:{
						locations[0] = new Location(myLoc.getx()+1, myLoc.gety());
						locations[1] = new Location(myLoc.getx()+1, myLoc.gety()-1);	
						locations[2] = new Location(myLoc.getx()-1, myLoc.gety()-1);
						locations[3] = new Location(myLoc.getx()-1, myLoc.gety()-2);
						break;
					}
					case West:{
						locations[0] = new Location(myLoc.getx(), myLoc.gety()+1);
						locations[1] = new Location(myLoc.getx()+1, myLoc.gety()+1);	
						locations[2] = new Location(myLoc.getx()+1, myLoc.gety()-1);
						locations[3] = new Location(myLoc.getx()+2, myLoc.gety()-1);
						break;
					}
				}
		}
		// Do all other ships
		else {
			// Counter for array position
			int k = 0; 
			
			if (blocks.length == 2) locations = new Location[2];
			else if (blocks.length == 4) locations = new Location[9];
			else if (blocks.length == 5) locations = new Location[14];
			else locations = new Location[1000];
			
			// Do head through one less then tail. Then do tail at one less then length of ship.
			for (int i = 0; i < blocks.length -1; i++){
				// for each position on the ship, add that number of blocks in the correct direction
				for (int j = 1; j < i+2; j++){
					switch(myDir){
						case North:{
							locations[k] = new Location(myLoc.getx()-j, myLoc.gety()+i);
							k++;
							break;
						}
						case East:{
							locations[k] = new Location(myLoc.getx()-i, myLoc.gety()-j);
							k++;
							break;
						}
						case South:{
							locations[k] = new Location(myLoc.getx()+j, myLoc.gety()-i);
							k++;
							break;
						}
						case West:{
							locations[k] = new Location(myLoc.getx()+i, myLoc.gety()+j);
							k++;
							break;
						}
					}
				}
			}
			// Then Add the Tails row which is one less then the length of the ship.
			for (int i = 1; i < blocks.length; i++){
				switch(myDir){
					case North:{
						locations[k] = new Location(myLoc.getx() - i, myLoc.gety() + (blocks.length-1));
						k++;
						break;
					}
					case East:{
						locations[k] = new Location(myLoc.getx()-(blocks.length-1), myLoc.gety()-i);
						k++;
						break;
					}
					case South:{
						locations[k] = new Location(myLoc.getx()+i, myLoc.gety()-(blocks.length-1));
						k++;
						break;
					}
					case West:{
						locations[k] = new Location(myLoc.getx()+(blocks.length-1), myLoc.gety()+i);
						k++;
						break;
					}
				}
			}
		}
		return locations;
		
	}
	public Location[] getRotateRightLocations(){
		if (myLoc == null) return null;
		if (blocks.length < 2 || blocks.length >10) return null;
		Location[] locations = new Location[0];
		// Account for Destroyer's odd pivot point
		if (this.blocks.length == 3){
				locations = new Location[4];
				switch(myDir){
					case North:{
						locations[0] = new Location(myLoc.getx()+1, myLoc.gety());
						locations[1] = new Location(myLoc.getx()+1, myLoc.gety()+1);	
						locations[2] = new Location(myLoc.getx()-1, myLoc.gety()+1);
						locations[3] = new Location(myLoc.getx()-1, myLoc.gety()+2);
						break;
					}
					case East:{
						locations[0] = new Location(myLoc.getx(), myLoc.gety()+1);
						locations[1] = new Location(myLoc.getx()-1, myLoc.gety()+1);	
						locations[2] = new Location(myLoc.getx()-1, myLoc.gety()-1);
						locations[3] = new Location(myLoc.getx()-2, myLoc.gety()-1);
						break;
					}
					case South:{
						locations[0] = new Location(myLoc.getx()-1, myLoc.gety());
						locations[1] = new Location(myLoc.getx()-1, myLoc.gety()-1);	
						locations[2] = new Location(myLoc.getx()+1, myLoc.gety()-1);
						locations[3] = new Location(myLoc.getx()+1, myLoc.gety()-2);
						break;
					}
					case West:{
						locations[0] = new Location(myLoc.getx(), myLoc.gety()-1);
						locations[1] = new Location(myLoc.getx()+1, myLoc.gety()-1);	
						locations[2] = new Location(myLoc.getx()+1, myLoc.gety()+1);
						locations[3] = new Location(myLoc.getx()+2, myLoc.gety()+1);
						break;
					}
				}
		}
		// Do all other ships
		else {
			// Counter for array position
			int k = 0; 
			int arraySize = 0;
			for (int i = 0; i < blocks.length; i++){
				arraySize = arraySize +i;
			}
			arraySize = arraySize + blocks.length -1;
			locations = new Location[arraySize];
			// Do head through one less then tail. Then do tail at one less then length of ship.
			for (int i = 0; i < blocks.length -1; i++){
				// for each position on the ship, add that number of blocks in the correct direction
				for (int j = 1; j < i+2; j++){
					switch(myDir){
						case North:{
							locations[k] = new Location(myLoc.getx()+j, myLoc.gety()+i);
							k++;
							break;
						}
						case East:{
							locations[k] = new Location(myLoc.getx()-i, myLoc.gety()+j);
							k++;
							break;
						}
						case South:{
							locations[k] = new Location(myLoc.getx()-j, myLoc.gety()-i);
							k++;
							break;
						}
						case West:{
							locations[k] = new Location(myLoc.getx()+i, myLoc.gety()-j);
							k++;
							break;
						}
					}
				}
			}
			// Then Add the Tails row which is one less then the length of the ship.
			for (int i = 1; i < blocks.length; i++){
				switch(myDir){
					case North:{
						locations[k] = new Location(myLoc.getx() + i, myLoc.gety() + (blocks.length-1));
						k++;
						break;
					}
					case East:{
						locations[k] = new Location(myLoc.getx()-(blocks.length-1), myLoc.gety()+i);
						k++;
						break;
					}
					case South:{
						locations[k] = new Location(myLoc.getx()-i, myLoc.gety()-(blocks.length-1));
						k++;
						break;
					}
					case West:{
						locations[k] = new Location(myLoc.getx()+(blocks.length-1), myLoc.gety()-i);
						k++;
						break;
					}
				}
			}
		}
		return locations;
	}
	
	
	/**
	 * Retruns the left-most column occupied by a ship.
	 * @return
	 */
	public int getx1() {
		if(myDir == Direction.West)
			return myLoc.getx() - blocks.length + 1;
		
		return myLoc.getx();
	}
	
	/**
	 * Retruns the right-most column occupied by a ship.
	 * @return
	 */
	public int getx2() {
		if(myDir == Direction.East)
			return myLoc.getx() + blocks.length - 1;
		
		return myLoc.getx();
	}
	
	/**
	 * Retruns the top-most column occupied by a ship.
	 * @return
	 */
	public int gety1() {
		if(myDir == Direction.North)
			return myLoc.gety() - blocks.length + 1;
		
		return myLoc.gety();
	}
	
	/**
	 * Retruns the bottom-most column occupied by a ship.
	 * @return
	 */
	public int gety2() {
		if(myDir == Direction.South)
			return myLoc.gety() + blocks.length - 1;
		
		return myLoc.gety();
	}
}
