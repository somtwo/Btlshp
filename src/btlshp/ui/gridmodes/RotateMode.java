package btlshp.ui.gridmodes;

import btlshp.Btlshp;
import btlshp.entities.Block;
import btlshp.entities.Map;
import btlshp.entities.MapNode;
import btlshp.entities.Ship;
import btlshp.ui.GameGrid;
import btlshp.utility.NodeIterator;
import btlshp.utility.NodeIteratorAction;

public class RotateMode extends GridMode {
	private Ship ship;
	
	public RotateMode(Ship targetShip, GameGrid gameGrid, Map gameMap) {
		super(gameGrid, gameMap);
		ship = targetShip;
	}

	@Override
	public void highlightCells() {
		super.highlightCells();
		
		NodeIterator it = ship.getCoreIterator();
		it.iterate(map, ship.getLocation(), ship.getDirection(), new NodeIteratorAction() {
			@Override
			public void visit(MapNode n, Block b) {
				n.actionArea(true);
			}
		});		
		
		
		it = ship.getRightRotationIterator();
		
		final boolean rightAction = it.isPointInside(grid.getHoverx(), grid.getHovery(), ship.getLocation(), ship.getDirection());
		it.iterate(map, ship.getLocation(), ship.getDirection(), new NodeIteratorAction() {
			@Override
			public void visit(MapNode n, Block b) {
				n.actionArea(true);
				
				if(rightAction)
					n.actionSquare(true);
			}
		});
		
		it = ship.getLeftRotationIterator();
		final boolean leftAction = it.isPointInside(grid.getHoverx(), grid.getHovery(), ship.getLocation(), ship.getDirection());
		it.iterate(map, ship.getLocation(), ship.getDirection(), new NodeIteratorAction() {
			@Override
			public void visit(MapNode n, Block b) {
				n.actionArea(true);
				
				if(leftAction)
					n.actionSquare(true);
			}
		});
	}
	

	@Override
	public void mouseClick(int x, int y) {
		MapNode n = map.getMapNode(grid.getHoverx(), grid.getHovery());
		
		if(n.actionArea())
			Btlshp.getGame().outputMessage("Rotate ship action.");
		
		grid.cancelAction();
	}

}
