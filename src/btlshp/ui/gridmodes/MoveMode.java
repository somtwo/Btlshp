package btlshp.ui.gridmodes;

import btlshp.Btlshp;
import btlshp.entities.Block;
import btlshp.entities.Map;
import btlshp.entities.MapNode;
import btlshp.entities.Ship;
import btlshp.enums.Direction;
import btlshp.turns.Turn;
import btlshp.turns.TurnFactory;
import btlshp.ui.GameGrid;
import btlshp.utility.NodeIterator;
import btlshp.utility.NodeIteratorAction;

public class MoveMode extends GridMode {

	private enum Move {
		Forward,
		Backward,
		Left,
		Right,
		None
	}
	
	private Move currentMove;
	private Ship ship;
	
	public MoveMode(Ship s, GameGrid gameGrid, Map gameMap) {
		super(gameGrid, gameMap);
		
		ship = s;
		currentMove = Move.None;
	}

	
	
	@Override
	public void highlightCells() {
		super.highlightCells();
		
		NodeIterator coreIt = ship.getCoreIterator();
		
		currentMove = Move.None;
		
		// Highlight the move area
		NodeIterator it = ship.getForwardMoveArea();
		it.iterate(map, ship.getLocation(), ship.getDirection(), new NodeIteratorAction() {
			@Override
			public void visit(MapNode n, Block b) {
				if(n != null)
					n.actionArea(true);
			}
		});
		if(it.isPointInside(grid.getHoverx(), grid.getHovery(), ship.getLocation(), ship.getDirection())) {
			currentMove = Move.Forward;
			coreIt.iterate(map, grid.getHoverx(), grid.getHovery(), ship.getDirection(), new NodeIteratorAction() {
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

		
		
		it = ship.getBackMoveArea();
		final boolean backHighlight = it.isPointInside(grid.getHoverx(), grid.getHovery(), ship.getLocation(), ship.getDirection());
		if(backHighlight)
			currentMove = Move.Backward;
		it.iterate(map, ship.getLocation(), ship.getDirection(), new NodeIteratorAction() {
			@Override
			public void visit(MapNode n, Block b) {
				if(n == null)
					return;
				if(backHighlight)
					n.actionSquare(true);
				else
					n.actionArea(true);
			}
		});
		
		it = ship.getLeftMoveArea();
		final boolean leftHighlight = it.isPointInside(grid.getHoverx(), grid.getHovery(), ship.getLocation(), ship.getDirection());
		if(leftHighlight)
			currentMove = Move.Left;
		it.iterate(map, ship.getLocation(), ship.getDirection(), new NodeIteratorAction() {
			@Override
			public void visit(MapNode n, Block b) {
				if(n == null)
					return;
				if(leftHighlight)
					n.actionSquare(true);
				else
					n.actionArea(true);
			}
		});
		
		it = ship.getRightMoveArea();
		final boolean rightHighlight = it.isPointInside(grid.getHoverx(), grid.getHovery(), ship.getLocation(), ship.getDirection());
		if(rightHighlight)
			currentMove = Move.Right;
		it.iterate(map, ship.getLocation(), ship.getDirection(), new NodeIteratorAction() {
			@Override
			public void visit(MapNode n, Block b) {
				if(n == null)
					return;
				if(rightHighlight)
					n.actionSquare(true);
				else
					n.actionArea(true);
			}
		});
		
		if(currentMove == Move.None)
		{
			MapNode node = map.getMapNode(grid.getHoverx(), grid.getHovery());
			
			if(node != null)
				node.badAction(true);
		}
	}

	
	@Override
	public void mouseClick(int x, int y) {
		int forwardDistance = Math.abs((grid.getHoverx() - ship.getLocation().getx()) + (grid.getHovery() - ship.getLocation().gety()));
		Direction shipDirection = ship.getDirection();
		
		// Move Forward 
		if(currentMove == Move.Forward && forwardDistance <= ship.getMaxForwardMove() &&
				map.canMove(ship, shipDirection, forwardDistance)) {
			grid.cancelAction();
			map.move(ship, shipDirection, forwardDistance);
			Turn t = TurnFactory.moveShip(ship, shipDirection, forwardDistance);
			Btlshp.getGame().sendTurn(t);
			Btlshp.getGame().outputMessage("Move forward.");
		}
		
		// Move Backward
		if(currentMove == Move.Backward && map.canMove(ship, shipDirection.backwardsDir(), 1)) {
			map.move(ship, shipDirection.backwardsDir(), 1);
			Btlshp.getGame().sendTurn(TurnFactory.moveShip(ship, shipDirection.backwardsDir(), 1));
			Btlshp.getGame().outputMessage("Move Backwards.");
		}
		
		// Move Left
		if(currentMove == Move.Left && map.canMove(ship, shipDirection.leftDir(), 1)) {
			map.move(ship, shipDirection.leftDir(), 1);
			Btlshp.getGame().sendTurn(TurnFactory.moveShip(ship, shipDirection.leftDir(), 1));
			Btlshp.getGame().outputMessage("Move Left.");
		}
		
		// Move Right
		if(currentMove == Move.Right && map.canMove(ship, shipDirection.rightDir(), 1)) {
			map.move(ship, shipDirection.rightDir(), 1);
			Btlshp.getGame().sendTurn(TurnFactory.moveShip(ship, shipDirection.rightDir(), 1));
			Btlshp.getGame().outputMessage("Move Right.");
		}
		
		grid.cancelAction();
	}

}
