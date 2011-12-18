package btlshp.entities;

import java.io.Serializable;

import btlshp.enums.GraphicAlliance;
import btlshp.enums.GraphicId;
import btlshp.enums.GraphicPart;
import btlshp.utility.NodeIterator;

public class Base extends Construct implements Serializable {
	private static final long serialVersionUID = -2521390657646600415L;
	private static final int  BASELENGTH = 10;
	
	private NodeIterator repairArea, coreArea;
	
	/**
	* Constructor for Base
	* Returns the Base Constructed
	* @param owner   Player the base belongs to.
	* ! this is assuming all bases are of size 10.
	*/
	public Base(Player owner) {
		constructID = super.getNextConstructerID();
		blocks = new ConstructBlock[BASELENGTH];
		maxRadarRange = 0; // TBD!
		pl = owner;
		for (int i = 0; i < BASELENGTH; i++) {
			GraphicPart part = i == 0 ? GraphicPart.Head : 
                i == BASELENGTH - 1 ? GraphicPart.Tail : GraphicPart.Middle;
                
			blocks[i] = new ConstructBlock(this, GraphicId.Base, GraphicId.DestroyedBase, part);
		}
		
		buildRepairArea();
		buildCoreArea();
	}
	
	
	/**
	 * @return The length of the base.
	 */
	public int getLength() {
		return BASELENGTH;
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
	
	
	public boolean canRepairSelf() {
		int destroyedBlocks = 0; 
		
		// Count good vs. bad (implimented to accomodate a change in the size of the base)
		for (int i = 0; i < blocks.length; i++){
			if (blocks[i].isDestroyed()){
				destroyedBlocks++;
			}
		}
		
		return destroyedBlocks > 0 && !this.isDestroyed();
	}
	
	
	private void buildRepairArea() {
		repairArea = new NodeIterator(null);
		
		for(int x = 0; x < blocks.length; ++x)
			repairArea.add(x, -1, null);
		
		repairArea.add(-1, 0, null);
		repairArea.add(blocks.length, 0, null);
	}
	
	
	
	public void buildCoreArea() {
		coreArea = new NodeIterator(null);
		
		for(int x = 0; x < blocks.length; ++x)
			coreArea.add(x, 0, blocks[x]);
	}
	
	
	
	public NodeIterator getRepairArea() {
		return repairArea;
	}
	
	
	public NodeIterator getCoreArea() {
		return coreArea;
	}
}
