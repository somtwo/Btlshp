package btlshp.entities;

import java.io.Serializable;

import btlshp.Btlshp;
import btlshp.enums.BlockStatus;
import btlshp.enums.GraphicAlliance;
import btlshp.enums.GraphicId;
import btlshp.enums.GraphicPart;
import btlshp.enums.Weapon;

public class ConstructBlock extends Block implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 475071291959481605L;
	Construct   myConstruct;
	BlockStatus myStatus;
	GraphicId   destroyedId;
	
	/**
	 * Basic Constructor for a Block
	 */
	public ConstructBlock(Construct Owner, GraphicId graphicId, GraphicId destroyedId, GraphicPart graphicPart) {
		this.graphicId = graphicId == null ? GraphicId.None : graphicId;
		this.graphicPart = graphicPart == null ? GraphicPart.None : graphicPart;
		this.destroyedId = destroyedId == null ? GraphicId.None : destroyedId;
		
		myConstruct = Owner;
		myStatus = BlockStatus.untouched;
	}
	
	/**
	 * Passes the Information that this block was hit to it's Construct
	 */
	public void takeHit(Weapon weaponUsed, int x, int y){
		myConstruct.assessDamage(this, weaponUsed);
	}
	public void takeRepair(){
		myConstruct.AssesRepair(this);
	}
	
	
	public Construct getConstruct() {
		return myConstruct;
	}
	
	public Player getPlayer() {
		return myConstruct.pl;
	}
	
	/**
	 * Checks if this block is destroyed
	 * @returns True if this block is destroyed
	 */
	public boolean isDestroyed() {
		if (myStatus == BlockStatus.destroyed){
			return true;
		}
		else return false;
	}
	
	/**
	 * Checks if this block is damaged. Damaged in this case is still destroyed.
	 * @returns True if this block is destroyed
	 */
	public boolean isDamaged(){
		if (myStatus == BlockStatus.destroyed){
			return true;
		}
		else return false;
	}
	
	/**
	 * Checks if this block is untouched
	 * @returns True if this block is untouched
	 */
	public boolean isUntouched(){
		if (myStatus == BlockStatus.untouched){
			return true;
		}
		else return false;
	}

	/**
	 * Causes one tier of Damage to the block.
	 */
	public void takeDamage(){
		myStatus = BlockStatus.destroyed;
	}

	/**
	 * Fully Repairs the given Block.
	 */
	public void repair() {
		myStatus = BlockStatus.untouched;
	}
	
	
	
	@Override
	public String getGraphicName() {
		GraphicId id = myStatus == BlockStatus.untouched ? graphicId : destroyedId;
		
		if(myConstruct != null && Btlshp.getGame() != null) {
			graphicAlliance = myConstruct.pl == Btlshp.getGame().getLocalPlayer() ? GraphicAlliance.Friendly : GraphicAlliance.Unfriendly;
		}
		return id.getName() + graphicPart.getSuffix() + graphicAlliance.getSuffix() + myConstruct.getDirection().suffix();
	}
	
	@Override
	public GraphicAlliance getAlliance() {
		if(myConstruct != null && Btlshp.getGame() != null) {
			graphicAlliance = myConstruct.pl == Btlshp.getGame().getLocalPlayer() ? GraphicAlliance.Friendly : GraphicAlliance.Unfriendly;
		}
		
		return graphicAlliance;
	}
	
}
