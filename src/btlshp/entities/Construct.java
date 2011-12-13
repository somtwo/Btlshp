package btlshp.entities;

import java.io.Serializable;

import btlshp.Btlshp;
import btlshp.BtlshpGame;
import btlshp.enums.*;

public abstract class Construct implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 207342763352324403L;
	protected Location myLoc;
	protected Direction myDir;
	protected ConstructBlock blocks[];
	protected int maxRadarRange;
	protected Player pl;
	
	/**
	* Computes the damage of the Construct when a block is damaged.
	* Guns Damage only the Box Hit
	* Torpedo's Damage the Box Hit and One more in the direction of the Bow. In the case of the Bow being hit, the next box from the Bow is damaged.
	* Mines Damage the Box that came in contact with it. If that box is damaged, this will damage the next undamaged in the direction of the stern.
	* If all in direction of the stern destroyed then goes in the direction of the bow.
	* @param hitBlock The block damaged
	* @param weaponUsed The Weapon used to do the damaging
	*/
	public void assessDamage(ConstructBlock hitBlock, Weapon weaponUsed) {
	//Determine Weapon Used and Allocate the Damage accordingly
		
	// Gun
		if (weaponUsed == Weapon.Gun){
			hitBlock.takeDamage();
		}
	// Torpedo
		else if (weaponUsed == Weapon.Torpedo){
			// Damage the block hit regardless
			hitBlock.takeDamage();
			
			// Determine the Next block to be Damaged (if another block exists).
			int blockLoc = this.blockLocation(hitBlock);
			// If this is not the Bow, Damage the next in the direction of the Bow
			if (blockLoc > 0){
				blocks[blockLoc-1].takeDamage();
			}
			// If the Bow was hit, Damage the second location of the ship (if it exists)
			else if (blocks.length>1){ 
				blocks[1].takeDamage();
			}
		}
	// Mine
		else if (weaponUsed == Weapon.Mine){
			// If the Block hit is not already destroyed... Damage it.
			if (!hitBlock.isDestroyed()){
				hitBlock.takeDamage();
			}
			// Else, determine next block to be Damaged, if it exists.
			else{
				int blockLoc = this.blockLocation(hitBlock);
				boolean damageAllocated = false;
				// First check Stern Direction
				for (int i = blockLoc+1; i < blocks.length; i++){
					if (!damageAllocated && !blocks[i].isDestroyed()){
						blocks[i].takeDamage();
						damageAllocated = true;
					}
				}
				// Then check Bow Direction
				for (int i = blockLoc-1; i >= 0; i--){
					if (!damageAllocated && !blocks[i].isDestroyed()){
						blocks[i].takeDamage();
						damageAllocated = true;
					}
				}
			}
			// No Special Action if the ship is already totaled when it takes this damage (Covers events of multiple mines)
		}
	// No Other Cases Possible
		else {
			// Error Occurred, Not Checked.
		}
	// Check if Ship has become destroyed, if so, report to player and map.
		if (this.isDestroyed() & this instanceof Ship){
			BtlshpGame currentGame = Btlshp.getGame();
			currentGame.destroyShip(this);
			pl.removeShip((Ship)this);
			
			//TODO MAP CALL to REMOVE - can be done through game method dtestroyShip (just a print statement atm).
		}
	}
	
	/**
	 * @return Player that owns this construct
	 */
	public Player getPlayer() {
		return pl;
	}

	/**
	* Computes the repair to the Construct when a block is repaired.
	* repairs the given block if Applicable, Else Repairs the first block from the bow to the stern.
	* this Repair does give priority to Selected Block, then to destroyed from Bow then partially damaged from the bow. 
	* Does nothing if ship is already fully repaired
	* @param repairBlock The block damaged
	*/
	public void AssesRepair(ConstructBlock repairBlock) {
		boolean repairComplete = false;
		// Check given Block. (Allows user to prioritize repair to a specific location)
		if (repairBlock.isDestroyed()){
			repairBlock.repair();
			repairComplete = true;
		}
		else {
			// Check for Destroyed Blocks from Bow.
			for (int i = 0; i < blocks.length ; i++){
				if (!repairComplete && blocks[i].isDestroyed()){
					blocks[i].repair();
					repairComplete = true;
				}
			}
			// Check for Damaged Blocks from Bow.
			for (int i = 0; i < blocks.length ; i++){
				if (!repairComplete && blocks[i].isDamaged()){
					blocks[i].repair();
					repairComplete = true;
				}
			}
		}
		// No Error Check for repairs on full Health Ship.
	}
	
	public Location getLocation(){
		return myLoc;
	}
	
	public void setLocation(Location loc){
		myLoc = loc;
	}
	
	public Direction getDirection(){
		return myDir;
	}
	
	public void setDirection(Direction dir){
		myDir = dir;
	}
	
	public boolean isDestroyed(){
		// assume it is destroyed
		boolean destroyed = true;
		// check for non-destroyed block
		for (int i = 0; i < blocks.length; i++){
			if (!blocks[i].isDestroyed()){
				destroyed = false;
			}
		}
		// return result
		return destroyed;
	}
	
	public int getRadarRange(){
		return maxRadarRange;
	}
	
	public ConstructBlock[] getBlocks(){
		return blocks;
	}
	
	/**
	 * 
	 * @param myBlock is the block's location to be determined.
	 * @return
	 */
	private int blockLocation(Block myBlock){
		int j = -1; //Start Location for search
		for (int i = 0; i < blocks.length; i++){
			if (myBlock == blocks[i]){
				j = i;
			}
		}
		return j;
	}
	public Location[] getAdjacentLocations(){
		if (myLoc == null) return null;
		Location[] locations = new Location[(this.blocks.length*2) +2];
		// add the head
		// Then add the tail (opposite direction of head and the length of the ship for distance)
		// Finally add the Adjacent blocks, 2 for each block on the construct.
		// The 'j' Variable adjusts for the direction parallel to the Construct.
		switch(myDir){
			case North:{
				int j = myLoc.gety();
				locations[0] = new Location(myLoc.getx(), myLoc.gety()-1);
				locations[1] = new Location(myLoc.getx(), myLoc.gety() + blocks.length);
				// These loops iterate once per block and i keeps track of the relative index to use for each.
				for (int i = 2; i < (blocks.length*2)+2; i = i+2){
					locations[i] = new Location(myLoc.getx()+1, j);
					locations[i+1] = new Location(myLoc.getx()-1, j);
					j++;
				}
			}
			case East:{
				int j = myLoc.getx();
				locations[0] = new Location(myLoc.getx()+1, myLoc.gety());		
				locations[1] = new Location(myLoc.getx() - blocks.length, myLoc.gety());	
				for (int i = 2; i < (blocks.length*2)+2; i = i+2){
					locations[i] = new Location(j, myLoc.gety()+1);
					locations[i+1] = new Location(j, myLoc.gety()-1);
					j--;
				}
			}
			case South:{
				int j = myLoc.gety();
				locations[0] = new Location(myLoc.getx(), myLoc.gety()+1);	
				locations[1] = new Location(myLoc.getx(), myLoc.gety() - blocks.length);
				for (int i = 2; i < (blocks.length*2)+2; i = i+2){
					locations[i] = new Location(myLoc.getx()+1, j);
					locations[i+1] = new Location(myLoc.getx()-1, j);
					j--;
				}
			}
			case West:{
				int j = myLoc.getx();
				locations[0] = new Location(myLoc.getx()-1, myLoc.gety());	
				locations[1] = new Location(myLoc.getx() + blocks.length, myLoc.gety());
				for (int i = 2; i < (blocks.length*2)+2; i = i+2){
					locations[i] = new Location(j, myLoc.gety()+1);
					locations[i+1] = new Location(j, myLoc.gety()-1);
					j++;
				}
			}
		}		
		return locations;
		
	}
}