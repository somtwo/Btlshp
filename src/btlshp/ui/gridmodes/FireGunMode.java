package btlshp.ui.gridmodes;

import btlshp.Btlshp;
import btlshp.entities.Map;
import btlshp.entities.Ship;
import btlshp.ui.GameGrid;

public class FireGunMode extends GridMode {
	private Ship ship;
	
	public FireGunMode(Ship actor, GameGrid gameGrid, Map gameMap) {
		super(gameGrid, gameMap);
		ship = actor;
	}

	@Override
	public void highlightCells() {
		super.highlightCells();
	}
	
	
	@Override
	public void mouseClick(int x, int y) {
		Btlshp.getGame().outputMessage("Fire gun action.");
		grid.cancelAction();
	}

}
