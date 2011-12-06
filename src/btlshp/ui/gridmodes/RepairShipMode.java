package btlshp.ui.gridmodes;

import btlshp.Btlshp;
import btlshp.entities.Base;
import btlshp.entities.Block;
import btlshp.entities.ConstructBlock;
import btlshp.entities.Map;
import btlshp.entities.MapNode;
import btlshp.ui.GameGrid;
import btlshp.utility.NodeIterator;
import btlshp.utility.NodeIteratorAction;

public class RepairShipMode extends GridMode {
	private Base base;
	
	public RepairShipMode(Base b, GameGrid gameGrid, Map gameMap) {
		super(gameGrid, gameMap);
		base = b;
	}


	@Override
	public void highlightCells() {
		super.highlightCells();
		
		NodeIterator it = base.getRepairArea();
		it.iterate(map, base.getLocation(), base.getDirection(), new NodeIteratorAction() {
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
		
		if(n != null && n.actionArea()) {
			ConstructBlock cb = n.block != null && n.block instanceof ConstructBlock ? (ConstructBlock)n.block : null;
			
			if(cb != null && cb.getPlayer() == base.getPlayer())
				Btlshp.getGame().outputMessage("Repair ship action.");
		}

		grid.cancelAction();
	}

}
