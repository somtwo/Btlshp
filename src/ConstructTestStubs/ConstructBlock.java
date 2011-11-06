package ConstructTestStubs;

import btlshp.enums.BlockStatus;
import btlshp.enums.Weapon;

public class ConstructBlock extends Block {

	Construct myConstruct;
	BlockStatus myStatus;
	
	/**
	 * Basic Constructor for a Block
	 */
	public ConstructBlock(Construct Owner){
		myConstruct = Owner;
		myStatus = BlockStatus.untouched;
	}
	/**
	 * Passes the Information that this block was hit to it's Construct
	 */
	public void takeHit(Weapon weaponUsed){
		myConstruct.assessDamage(this, weaponUsed);
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
}
