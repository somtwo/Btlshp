package btlshp.entities;

import java.io.Serializable;

import btlshp.enums.GraphicAlliance;
import btlshp.enums.GraphicId;
import btlshp.enums.GraphicPart;
import btlshp.enums.Weapon;

public abstract class Block implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5024766800984383104L;
	protected GraphicId       graphicId;
	protected GraphicPart     graphicPart;
	protected GraphicAlliance graphicAlliance;
	
	Block() {
		graphicId = GraphicId.None;
		graphicPart = GraphicPart.None;
		graphicAlliance = GraphicAlliance.Neutral;
	}
	
	/**
	 * Checks if this block is destroyed
	 * @returns True if this block is destroyed
	 */
	public void takeHit(Weapon fromWeapon){
	}
	
	public abstract void takeRepair();

	
	/**
	 * @return A string with the name of the graphic the UI should use to represent this block.
	 */
	public String getGraphicName() {
		return graphicId.getName() + graphicPart.getSuffix() + graphicAlliance.getSuffix();
	}
	
	public GraphicAlliance getAlliance() {
		return graphicAlliance;
	}
}
