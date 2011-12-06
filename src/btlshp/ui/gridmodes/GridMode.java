package btlshp.ui.gridmodes;

import btlshp.Btlshp;
import btlshp.entities.Map;
import btlshp.ui.GameGrid;

public abstract class GridMode {
	protected GameGrid grid;
	protected Map      map;
	
	public GridMode(GameGrid gameGrid, Map gameMap) {
		grid = gameGrid;
		map = gameMap;
	}
	
	/**
	 * Called beofre rendering. This is the pass when each node's radar/sonar flags are updated and any other 
	 * flags that need to be set are set.
	 */
	public void highlightCells() {
		map.updateFrame(Btlshp.getGame().getLocalPlayer());
	}
	
	/**
	 * Called when the mouse clicks on the grid
	 * @param x   x-position of the mouse click
	 * @param y   y-position of the mouse click
	 */
	public abstract void mouseClick(int x, int y);
}
