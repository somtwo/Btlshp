package btlshp.ui.gridmodes;

import btlshp.Btlshp;
import btlshp.entities.Block;
import btlshp.entities.Map;
import btlshp.entities.MapNode;
import btlshp.entities.Ship;
import btlshp.ui.GameGrid;
import btlshp.utility.NodeIterator;
import btlshp.utility.NodeIteratorAction;

public class PickupMineMode extends GridMode {
	private Ship ship;
	
	public PickupMineMode(Ship s, GameGrid gameGrid, Map gameMap) {
		super(gameGrid, gameMap);
		ship = s;
	}


	@Override
	public void highlightCells() {
		super.highlightCells();
		
		NodeIterator it = ship.getMineArea();
		it.iterate(map, ship.getLocation(), ship.getDirection(), new NodeIteratorAction() {
			@Override
			public void visit(MapNode n, Block b) {
				if(n == null)
					return;
				n.actionArea(true);
			}
		});
		
		MapNode n = map.getMapNode(grid.getHoverx(), grid.getHovery());
		
		if(n != null && n.actionArea())
			n.actionSquare(true);
		else if(n != null && !n.actionArea())
			n.badAction(true);
	}
	
	
	@Override
	public void mouseClick(int x, int y) {
		MapNode n = map.getMapNode(grid.getHoverx(), grid.getHovery());
		
		if(n != null && n.actionSquare())
			Btlshp.getGame().outputMessage("Pickup mine action action.");
		
		grid.cancelAction();
	}

}
