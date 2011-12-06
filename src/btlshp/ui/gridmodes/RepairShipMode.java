package btlshp.ui.gridmodes;

import btlshp.Btlshp;
import btlshp.entities.Base;
import btlshp.entities.Map;
import btlshp.ui.GameGrid;

public class RepairShipMode extends GridMode {
	private Base base;
	
	public RepairShipMode(Base b, GameGrid gameGrid, Map gameMap) {
		super(gameGrid, gameMap);
		base = b;
	}


	@Override
	public void highlightCells() {
		super.highlightCells();
	}
	
	
	@Override
	public void mouseClick(int x, int y) {
		Btlshp.getGame().outputMessage("Repair ship action.");
		grid.cancelAction();
	}

}
