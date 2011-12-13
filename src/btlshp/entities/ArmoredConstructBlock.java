package btlshp.entities;

import btlshp.Btlshp;
import btlshp.enums.BlockStatus;
import btlshp.enums.GraphicAlliance;
import btlshp.enums.GraphicId;
import btlshp.enums.GraphicPart;

public class ArmoredConstructBlock extends ConstructBlock{
	private static final long serialVersionUID = -8855058436621051905L;
	
	GraphicId damagedId;
	
	public ArmoredConstructBlock(Construct Owner, GraphicId graphicId, GraphicId damagedId, GraphicId destroyedId, GraphicPart graphicPart) {
		super(Owner, graphicId, destroyedId, graphicPart);
		
		this.damagedId = damagedId == null ? GraphicId.None : damagedId;
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
	
	@Override
	public String getGraphicName() {
		GraphicId id = myStatus == BlockStatus.untouched ? graphicId : 
			myStatus == BlockStatus.damaged ? damagedId : destroyedId;
		
		if(myConstruct != null && Btlshp.getGame() != null) {
			graphicAlliance = myConstruct.pl == Btlshp.getGame().getLocalPlayer() ? GraphicAlliance.Friendly : GraphicAlliance.Unfriendly;
		}
		return id.getName() + graphicPart.getSuffix() + graphicAlliance.getSuffix() + myConstruct.getDirection().suffix();
	}
}
