package btlshp.ui.gridmodes;

import btlshp.entities.Map;
import btlshp.entities.Ship;
import btlshp.ui.GameGrid;

public class RotateMode extends GridMode {
	private Ship ship;
	
	public RotateMode(Ship targetShip, GameGrid gameGrid, Map gameMap) {
		super(gameGrid, gameMap);
		ship = targetShip;
	}

	@Override
	public void highlightCells() {
		
	}

	@Override
	public void mouseClick(int x, int y) {
		// TODO Auto-generated method stub

	}

}
