package btlshp.ui.gridmodes;

import btlshp.Btlshp;
import btlshp.entities.Map;
import btlshp.entities.Ship;
import btlshp.ui.GameGrid;

public class PlaceMineMode extends GridMode {
	private Ship ship;
	
	public PlaceMineMode(Ship s, GameGrid gameGrid, Map gameMap) {
		super(gameGrid, gameMap);
		ship = s;
	}

	@Override
	public void highlightCells() {
		super.highlightCells();
	}
	
	
	@Override
	public void mouseClick(int x, int y) {
		Btlshp.getGame().outputMessage("Place mine action.");
		grid.cancelAction();
	}
}
