package btlshp.ui.gridmodes;

import btlshp.Btlshp;
import btlshp.entities.Block;
import btlshp.entities.Map;
import btlshp.entities.MapNode;
import btlshp.entities.Ship;
import btlshp.enums.Weapon;
import btlshp.ui.GameGrid;
import btlshp.utility.NodeIterator;
import btlshp.utility.NodeIteratorAction;

public class FireGunMode extends GridMode {
	private Ship ship;
	
	public FireGunMode(Ship actor, GameGrid gameGrid, Map gameMap) {
		super(gameGrid, gameMap);
		ship = actor;
	}

	@Override
	public void highlightCells() {
		super.highlightCells();
		
		NodeIterator it = ship.getFiringIterator();
		it.iterate(map, ship.getLocation(), ship.getDirection(), new NodeIteratorAction() {
			@Override
			public void visit(MapNode n, Block b) {
				if(n == null)
					return;
				n.actionArea(true);
			}
		});
		
		MapNode n = map.getMapNode(grid.getHoverx(), grid.getHovery());
		
		if(n != null) {
			if(n.actionArea())
				n.actionSquare(true);
			else
				n.badAction(true);
		}
	}
	
	
	@Override
	public void mouseClick(int x, int y) {
		MapNode n = map.getMapNode(grid.getHoverx(), grid.getHovery());
		
		if(n.actionSquare()){
			Btlshp.getGame().outputMessage("Fire gun action.");
			if (n.block == null){
				//Do Nothing if the block is null
			}
			else{
				n.block.takeHit(Weapon.Gun, x, y); //TODO need x & y node locations, not mouse click locations
			}
		}
		grid.cancelAction();
	}

}
