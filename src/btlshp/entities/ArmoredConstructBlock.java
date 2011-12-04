package btlshp.entities;

import btlshp.enums.BlockStatus;
import btlshp.enums.GraphicAlliance;
import btlshp.enums.GraphicId;
import btlshp.enums.GraphicPart;

public class ArmoredConstructBlock extends ConstructBlock{
	
	public ArmoredConstructBlock(Construct Owner, GraphicId graphicId, GraphicPart graphicPart) {
		super(Owner, graphicId, graphicPart);
	}

	/**
	 * Checks if this block is damaged, Impliments fully the concept of "damaged" for armored blocks
	 * @return True if this block is damaged
	 */
	public boolean damaged(){
		if (myStatus == BlockStatus.damaged){
			return true;
		}
		else return false;
	}
	
	/**
	 * Causes one tier of Damage to the block. 
	 * Untouched to Damaged. Damaged to Destroyed. Destroyed to Destroyed.
	 */
	public void takeDamage(){
		if (myStatus == BlockStatus.untouched){
			myStatus = BlockStatus.damaged;
		}
		else if (myStatus == BlockStatus.damaged){
			myStatus = BlockStatus.destroyed;
		}
		else{ //myStatus == BlockStatus.untouched)
			myStatus = BlockStatus.destroyed;
		}
	}
}
