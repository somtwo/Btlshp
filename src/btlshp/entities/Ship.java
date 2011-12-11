package btlshp.entities;

import java.io.Serializable;

import btlshp.enums.Direction;
import btlshp.enums.GraphicId;
import btlshp.enums.GraphicPart;
import btlshp.utility.NodeIterator;

public class Ship extends Construct implements Serializable{
	
	private static final long serialVersionUID = -4544034641149715119L;
	
	// Behavior flags.
	private static final int  isArmored = 0x1;
	private static final int  hasGun = 0x2;
	private static final int  hasTorpedo = 0x4;
	private static final int  hasMinePlacement = 0x8;
	private static final int  hasSonar = 0x10;
	
	private int          flags;
	private int          maxForwardMove, maxSideMove, maxBackMove, maxGunRange, maxSonarRange;
	private NodeIterator coreArea, adjacentArea, radarArea, turnLeftArea, turnRightArea, firingArea, mineArea;
	private NodeIterator forwardMoveArea, backMoveArea, leftMoveArea, rightMoveArea;
	
	/**
	* Constructor for Ship
	* Returns the Ship Constructed
	* @param owner   Player the base belongs to.
	* @param blocks  The blocks to use for the given ship.
	*/
	private Ship(Player owner, int shipFlags, int forward, int side, int back, int gunRange, int radarRange, int sonarRange, int numberOfBlocks) {
		pl = owner;
		flags = shipFlags;
		maxForwardMove = forward;
		maxSideMove = side;
		maxBackMove = back;
		maxGunRange = gunRange;
		maxSonarRange = sonarRange;
		maxRadarRange = radarRange;
		myLoc = new Location(0, 0);
		
		if (hasArmor()) {
			blocks = new ArmoredConstructBlock[numberOfBlocks];
			for (int i = 0; i<numberOfBlocks ; i++){
				GraphicPart part = i == 0 ? GraphicPart.Head : 
	                i == numberOfBlocks - 1 ? GraphicPart.Tail : GraphicPart.Middle;
	                
				blocks[i] = new ArmoredConstructBlock(this, GraphicId.Ship, part);
			}	
		}
		else {
			blocks = new ConstructBlock[numberOfBlocks];
			for (int i = 0; i<numberOfBlocks ; i++){
				GraphicPart part = i == 0 ? GraphicPart.Head : 
	                i == numberOfBlocks - 1 ? GraphicPart.Tail : GraphicPart.Middle;
	                
				blocks[i] = new ConstructBlock(this, GraphicId.Ship, part);
			}	
		}
		
		buildLeftRotationIterator();
		buildRightRotationIterator();
		buildCoreIterator();
		buildAdjacentIterator();
		buildMoveIterators();
		
		if(hasSonar())
			buildSonarIterator();
		else
			buildRadarIterator();
		
		if(canFireGun())
			buildFiringArea();
		if(canPlaceMine())
			buildMineArea();
	}
	
	
	//	private NodeIterator coreArea, adjacentArea, radarArea, turnLeftArea, turnRightArea, firingArea;
	
	/**
	 * Returns an iterator that can be used to iterate over the map blocks that will consist of the
	 * ships core area.
	 * 
	 * @return NodeIterator object.
	 */
	public NodeIterator getCoreIterator() {
		return coreArea;
	}
	
	/**
	 * Returns an iterator that can be used to iterate over the map blocks directly adjacent the ships
	 * body.
	 * 
	 * @return NodeIterator object.
	 */
	public NodeIterator getSurroundingIterator() {
		return adjacentArea;
	}
	
	/**
	 * Returns an iterator that can be used to iterate over the map blocks within this ships radar.
	 * 
	 * @return NodeIterator object.
	 */
	public NodeIterator getRadarIterator() {
		return radarArea;
	}
	
	
	/**
	 * Returns an iterator that can be used to iterate over the map blocks within the ships firing range.
	 * 
	 * @return NodeIterator object.
	 */
	public NodeIterator getFiringIterator() {
		return firingArea;
	}
	
	
	
	/**
	 * Returns an iterator that can be used to iterate over the map blocks covered when the ship turns left
	 * 
	 * @return NodeIterator object.
	 */
	public NodeIterator getLeftRotationIterator() {
		return turnLeftArea;
	}
	
	
	/**
	 * Returns an iterator that can be used to iterate over the map blocks covered when the ship turns right
	 * 
	 * @return NodeIterator object.
	 */
	public NodeIterator getRightRotationIterator() {
		return turnRightArea;
	}
	
	
	/**
	 * @return an iterator object that can be used to iterate through all the squares a ship can move forward through.
	 */
	public NodeIterator getForwardMoveArea() {
		return forwardMoveArea;
	}
	
	
	/**
	 * @return an iterator object that can be used to iterate through all the squares a ship can move backward through.
	 */
	public NodeIterator getBackMoveArea() {
		return backMoveArea;
	}
	
	/**
	 * @return an iterator object that can be used to iterate through all the squares a ship can move left through.
	 */
	public NodeIterator getLeftMoveArea() {
		return leftMoveArea;
	}
	
	/**
	 * @return an iterator object that can be used to iterate through all the squares a ship can move right through.
	 */
	public NodeIterator getRightMoveArea() {
		return rightMoveArea;
	}
	
	
	/**
	 * @return an iterator object that can be used to iterate through all the squares a ship can place/pickup mines through
	 */
	public NodeIterator getMineArea() {
		return mineArea;
	}
	
	/**
	 * Returns true if this ship can fire guns.
	 * @returns true if the ship has a gun to fire, false otherwise
	 */
	public boolean canFireGun() {
		return (flags & hasGun) == hasGun;
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
		return (flags & hasTorpedo) == hasTorpedo;
	}

	/**
	 * Returns true if this ship can place a mine.
	 * @returns true if the ship can place a mine, false otherwise
	 */
	public boolean canPlaceMine() {
		return (flags & hasMinePlacement) == hasMinePlacement;
	}

	/**
	 * Returns true if this ship can pick up a mine.
	 * @returns true if the ship can pick up a mine, false otherwise
	 */
	boolean canPickUpMine() {
		return (flags & hasMinePlacement) == hasMinePlacement;
	}
	
	/**
	 * @return true if this ship has Sonar capability, false otherwise
	 */
	public boolean hasSonar(){
		return (flags & hasSonar) == hasSonar;
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
		return (flags & isArmored) == isArmored;
	}
	/**
	* Factory method for Cruiser
	* @returns the Ship Constructed
	*/
	public static Ship buildCruiser(Player owner) {
		return new Ship(owner, hasGun, 10, 1, 1, 5, 6, 0, 5);
	}

	/**
	* Factory method for TorpedoBoat
	* @returns the Ship Constructed
	*/
	public static Ship buildTorpedoBoat(Player owner) {
		return new Ship(owner, hasGun|hasTorpedo, 8, 1, 1, 4, 5, 0, 4);
	}
	// private Ship(Player owner, int flags, int forward, int side, int back, int gunRange, int radarRange, int sonarRange, int numberOfBlocks) {
	/**
	* Factory method for Destroyer
	* @returns the Ship Constructed
	*/
	public static Ship buildDestroyer(Player owner) {
		return new Ship(owner, hasTorpedo, 6, 1, 1, 0, 4, 0, 3);
	}

	/**
	* Factory method for MineSweeper
	* @returns the Ship Constructed
	*/
	public static Ship buildMineSweeper(Player owner) {
		return new Ship(owner, hasSonar|hasMinePlacement|isArmored, 4, 1, 1, 0, 2, 2, 2);
	}

	public Player getPlayer() {
		return pl;
	}
	
	
	private void buildLeftRotationIterator() {
		turnLeftArea = new NodeIterator(null);
		
		// Account for Destroyer's odd pivot point
		if(blocks.length == 3) {
			turnLeftArea.add(-1, 0,   blocks[0]);
			turnLeftArea.add(-1, -1,  blocks[0]);	
			turnLeftArea.add( 1, 0,   blocks[2]);
			turnLeftArea.add( 1, 1,   blocks[2]);
		}
		else {
			// Do head through one less then tail. Then do tail at one less then length of ship.
			for (int x = 1; x < blocks.length; x++){
				int colHeight = blocks.length - x;
				
				// for each position on the ship, add that number of blocks in the correct direction
				for (int y = 0; y < colHeight; y++) {
					turnLeftArea.add(-x, y - colHeight, blocks[y]);
				}
			}
			// Then Add the Tails row which is one less then the length of the ship.
			for (int i = 0; i < blocks.length - 1; i++) {
				turnLeftArea.add(1 - blocks.length + i, 0, blocks[i]);
			}
		}
	}

	
	private void buildRightRotationIterator() {
		turnRightArea = new NodeIterator(null);
		
		// Account for Destroyer's odd pivot point
		if (blocks.length == 3) {
			turnRightArea.add(-1, 0,   blocks[0]);
			turnRightArea.add(-1, 1,   blocks[0]);	
			turnRightArea.add( 1, 0,   blocks[2]);
			turnRightArea.add( 1, -1,  blocks[2]);
		}
		// Do all other ships
		else {
			// Do head through one less then tail. Then do tail at one less then length of ship.
			for (int x = 1; x < blocks.length; x++){
				int colHeight = blocks.length - x;
				
				// for each position on the ship, add that number of blocks in the correct direction
				for (int y = 0; y < colHeight; y++) {
					turnRightArea.add(x, y - colHeight, blocks[y]);
				}
			}
			// Then Add the Tails row which is one less then the length of the ship.
			for (int i = 0; i < blocks.length - 1; i++) {
				turnRightArea.add(blocks.length - 1 - i, 0, blocks[i]);
			}
		}
	}
	
	
	private void buildCoreIterator() {
		// Account for Destroyer's odd pivot point
		int offset = (blocks.length == 3) ? 1 : blocks.length - 1;
		
		coreArea = new NodeIterator(null);
		
		for(int i = 0; i < blocks.length; ++i)
			coreArea.add(0, i - offset, blocks[i]);
	}
	
	
	private void buildAdjacentIterator() {
		// Account for Destroyer's odd pivot point
		int offset = (blocks.length == 3) ? 1 : blocks.length - 1;
		
		adjacentArea = new NodeIterator(null);
		
		// Add the area along the sides
		for(int i = 0; i < blocks.length; ++i) {
			adjacentArea.add(-1, i - offset, blocks[i]);
			adjacentArea.add( 1, i - offset, blocks[i]);
		}
		
		// Add the head and tail.
		adjacentArea.add(0, 1,             blocks[blocks.length - 1]);
		adjacentArea.add(0, blocks.length, blocks[0]);
	}
	
	
	private void buildRadarIterator() {
		int x, y, offset;
		
		radarArea = new NodeIterator(null);
		
		// Account for the destroyers weird turn axis
		offset = blocks.length == 3 ? 1 : 0;
		
		// Do in front of the ship
		for(x = -1; x < 2; x++) {
			for(y = 0; y < maxRadarRange; y++) {
				radarArea.add(x, offset - blocks.length - y, null);
			}
		}
		
		// Do along side the ship
		for(y = 1; y < blocks.length; ++y) {
			radarArea.add(-1, offset - y, null);
			radarArea.add(1, offset - y, null);
		}
	}
	
	private void buildSonarIterator() {
		int x, y, ytop, ybot;
		
		radarArea = new NodeIterator(null);
		
		ytop = blocks.length == 3 ? 1 - maxSonarRange : -blocks.length - maxSonarRange + 1;
		ybot = blocks.length == 3 ? 1 + maxSonarRange : maxSonarRange;
		
		for(x = -maxSonarRange; x <= maxSonarRange; ++x) {
			for(y = ytop; y <= ybot; ++y) {
				radarArea.add(x, y, null);
			}
		}
	}
	
	
	private void buildFiringArea() {
		int x, y, ytop, ybot;
		
		firingArea = new NodeIterator(null);
		
		ytop = blocks.length == 3 ? -1 - maxGunRange : -blocks.length - maxGunRange;
		ybot = blocks.length == 3 ? 1 + maxGunRange : maxGunRange;
		
		for(x = -maxGunRange; x <= maxGunRange; ++x) {
			for(y = ytop; y <= ybot; ++y) {
				firingArea.add(x, y, null);
			}
		}
	}
	
	
	private void buildMoveIterators() {
		int x, y;
		
		forwardMoveArea = new NodeIterator(null);
		backMoveArea = new NodeIterator(null);
		leftMoveArea = new NodeIterator(null);
		rightMoveArea = new NodeIterator(null);
		
		int offset = blocks.length == 3 ? 1 : 0;
		
		// Tail
		for(y = 1; y <= maxBackMove; ++y) {
			backMoveArea.add(0, offset + y, null);
		}
		for(y = 0; y < blocks.length; ++y) {
			for(x = 1; x <= maxSideMove; ++x) {
				leftMoveArea.add(-x, offset - y, null);
				rightMoveArea.add(x, offset - y, null);
			}
		}
		for(y = 0; y < maxForwardMove + blocks.length - 1; ++y) {
			forwardMoveArea.add(0, offset - 1 - y, null);
		}
	}
	
	
	private void buildMineArea() {
		int x, y;
		
		mineArea = new NodeIterator(null);
		
		for(x = -1; x < 2; ++x) {
			mineArea.add(x, -blocks.length, null);
			mineArea.add(x, 1, null);
		}
		
		for(y = 0; y < blocks.length; ++y) {
			mineArea.add(-1, -y, null);
			mineArea.add(1, -y, null);
		}
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
