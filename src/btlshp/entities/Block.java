package btlshp.entities;

import btlshp.enums.GraphicAlliance;
import btlshp.enums.GraphicId;
import btlshp.enums.GraphicPart;
import btlshp.enums.Weapon;

public abstract class Block {
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
	public abstract void takeHit(Weapon fromWeapon);

	
	/**
	 * @return A string with the name of the graphic the UI should use to represent this block.
	 */
	public String getGraphicName() {
		return graphicId.getName() + graphicPart.getSuffix() + graphicAlliance.getSuffix();
	}
}
