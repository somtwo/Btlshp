package btlshp.entities;

public class Base extends Construct {

	/**
	* Constructor for Base
	* Returns the Base Constructed
	* @param owner   Player the base belongs to.
	* ! this is assuming all bases are of size 10.
	*/
	public Base(Player owner) {
		blocks = new ConstructBlock[10];
		maxRadarRange = 0; // TBD!
		pl = owner;
		for (int i = 0; i < 10; i++){
			blocks[i] = new ConstructBlock(this);
		}
	}
	
	/**
	* Determines if this base is in condition to conduct repairs
	* @return True if destroyed blocks <= (Total Blocks / 2). (In most cases if destroyed is 5 or less) 
	*/
	public boolean canRepairOther() {
		int goodBlocks = 0;
		int destroyedBlocks = 0; 
		
		// Count good vs. bad (implimented to accomodate a change in the size of the base)
		for (int i = 0; i < blocks.length; i++){
			if (blocks[i].isDestroyed()){
				destroyedBlocks++;
			}
			else {
				goodBlocks++;
			}
		}
		
		if (goodBlocks >= destroyedBlocks) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean canRepairSelf(){
		return !this.isDestroyed();
	}
}
