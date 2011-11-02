package btlshp.entities;

public class Base extends Construct {
	
	/**
	* Constructor for Base
	* Returns the Base Constructed
	* @param owner   Player the base belongs to.
	* @param blocks  
	*/
	Base(Player owner, ConstructBlock inBlocks[]) {
		radarRange = 0; // TBD!
		pl = owner;
		this.blocks = inBlocks;
	}
	
	/**
	* Determines if this base is in condition to conduct repairs
	* @return True if destroyed blocks <= (Total Blocks / 2). (In most cases if destroyed is 5 or less) 
	*/
	boolean canRepair() {
		int goodBlocks = 0;
		int destroyedBlocks = 0;
		
		// Count good vs. bad (implimented to accomodate a change in the size of the base)
		for (int i = 0; i < blocks.length; i++){
			if (blocks[i].destroyed()){
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
}
