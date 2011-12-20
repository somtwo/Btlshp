package btlshp.ui.gridmodes;

import btlshp.Btlshp;
import btlshp.entities.Block;
import btlshp.entities.Map;
import btlshp.entities.MapNode;
import btlshp.entities.Ship;
import btlshp.enums.Direction;
import btlshp.turns.TurnFactory;
import btlshp.ui.GameGrid;
import btlshp.utility.NodeIterator;
import btlshp.utility.NodeIteratorAction;

public class Rotate180Mode extends GridMode {
	private Ship target;
	
	public Rotate180Mode(GameGrid gameGrid, Map gameMap, Ship s) {
		super(gameGrid, gameMap);
		target = s;
	}
	
	public void highlightCells() {
		super.highlightCells();
		
		NodeIterator leftIt = target.getLeft180RotationIterator();
		NodeIterator rightIt = target.getRight180RotationIterator();
		
		final boolean action = 
				leftIt.isPointInside(grid.getHoverx(), grid.getHovery(), target.getLocation(), target.getDirection()) ||
				rightIt.isPointInside(grid.getHoverx(), grid.getHovery(), target.getLocation(), target.getDirection());
		
		leftIt.iterate(map, target.getLocation(), target.getDirection(), new NodeIteratorAction() {
			@Override
			public void visit(MapNode n, Block b) {
				if(n == null)
					return;
				
				n.actionArea(true);
				
				if(action)
					n.actionSquare(true);
			}
		});
		
		rightIt.iterate(map, target.getLocation(), target.getDirection(), new NodeIteratorAction() {
			@Override
			public void visit(MapNode n, Block b) {
				if(n == null)
					return;
				
				n.actionArea(true);
				
				if(action)
					n.actionSquare(true);
			}
		});
	}

	@Override
	public void mouseClick(int x, int y) {
		MapNode n = map.getMapNode(grid.getHoverx(), grid.getHovery());
		if(n == null || n.actionSquare() == false) {
			grid.cancelAction();
			return;
		}
		
		if(isLeftRotate(target.getDirection()) &&
		   map.canShipRotate(target, target.getDirection().leftDir()) &&
		   map.canShipRotate(target, target.getDirection().backwardsDir())) {
			map.rotateShip(target, target.getDirection().leftDir());
			map.rotateShip(target, target.getDirection().leftDir());
			
			
		}
	}
	
	private boolean isLeftRotate(Direction dir) {
		int clickx = grid.getHoverx();
		int clicky = grid.getHovery();
		int shipx = target.getLocation().getx();
		int shipy = target.getLocation().gety();
		
		if ((dir.val() == 0 && clickx < shipx) |
			(dir.val() == 2 && clickx > shipx) |
			(dir.val() == 1 && clicky < shipy) |
			(dir.val() == 3 && clicky > shipy) )
			return true; //Rotate Left
		else
			return false;//Rotate Right
	}
}