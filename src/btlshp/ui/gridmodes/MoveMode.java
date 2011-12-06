package btlshp.ui.gridmodes;

import btlshp.Btlshp;
import btlshp.entities.Block;
import btlshp.entities.Map;
import btlshp.entities.MapNode;
import btlshp.entities.Ship;
import btlshp.ui.GameGrid;
import btlshp.utility.NodeIterator;
import btlshp.utility.NodeIteratorAction;

public class MoveMode extends GridMode {

	Ship ship;
	
	public MoveMode(Ship s, GameGrid gameGrid, Map gameMap) {
		super(gameGrid, gameMap);
		
		ship = s;
	}

	
	
	@Override
	public void highlightCells() {
		super.highlightCells();
		
		// Highlight the move area
		NodeIterator it = ship.getMoveArea();
		it.iterate(map, ship.getLocation(), ship.getDirection(), new NodeIteratorAction() {
			@Override
			public void visit(MapNode n, Block b) {
				if(n != null)
					n.actionArea(true);
			}
		});
		
		// Highlight a potential move.
		it = ship.getCoreIterator();
		it.iterate(map, grid.getHoverx(), grid.getHovery(), ship.getDirection(), new NodeIteratorAction() {
			@Override
			public void visit(MapNode n, Block b) {
				if(n == null)
					return;

				if(!n.actionArea())
					n.badAction(true);
				else
					n.actionSquare(true);
			}
		});
	}

	
	@Override
	public void mouseClick(int x, int y) {
		grid.cancelAction();
	}

}
