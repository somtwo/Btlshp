package btlshp.ui.gridmodes;

import btlshp.Btlshp;
import btlshp.entities.Block;
import btlshp.entities.Map;
import btlshp.entities.MapNode;
import btlshp.entities.Ship;
import btlshp.enums.Direction;
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
		
		NodeIterator it = ship.getRightRotationIterator();
		
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
		Direction shipDirection = ship.getDirection();
		
		if(n.actionArea()) {
			
			// Rotate Left
			if(isLeftRotate(shipDirection) == true) {
				map.rotateShip(ship,shipDirection.leftDir());
				Btlshp.getGame().outputMessage("Rotate ship action.");
			}
			
			// Rotate Right
			else {
				map.rotateShip(ship,shipDirection.rightDir());
				Btlshp.getGame().outputMessage("Rotate ship action.");
			}
		}
		grid.cancelAction();
	}
	
	private boolean isLeftRotate(Direction dir) {
		int clickx = grid.getHoverx();
		int clicky = grid.getHovery();
		int shipx = ship.getLocation().getx();
		int shipy = ship.getLocation().gety();
		
		if ((dir.val() == 0 && clickx < shipx) |
			(dir.val() == 2 && clickx > shipx) |
			(dir.val() == 1 && clicky < shipy) |
			(dir.val() == 3 && clicky > shipy) )
			return true; //Rotate Left
		else
			return false;//Rotate Right
	}
}
