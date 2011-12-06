package btlshp.ui.gridmodes;

import btlshp.Btlshp;
import btlshp.entities.Map;
import btlshp.entities.Ship;
import btlshp.ui.GameGrid;

public class PickupMineMode extends GridMode {
	private Ship ship;
	
	public PickupMineMode(Ship s, GameGrid gameGrid, Map gameMap) {
		super(gameGrid, gameMap);
		ship = s;
	}


	@Override
	public void highlightCells() {
		super.highlightCells();
	}
	
	
	@Override
	public void mouseClick(int x, int y) {
		Btlshp.getGame().outputMessage("Pickup mine action action.");
		grid.cancelAction();
	}

}
