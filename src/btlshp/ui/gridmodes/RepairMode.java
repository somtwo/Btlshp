package btlshp.ui.gridmodes;

import btlshp.Btlshp;
import btlshp.entities.Base;
import btlshp.entities.Block;
import btlshp.entities.ConstructBlock;
import btlshp.entities.Map;
import btlshp.entities.MapNode;
import btlshp.entities.MineBlock;
import btlshp.entities.Ship;
import btlshp.turns.TurnFactory;
import btlshp.ui.GameGrid;
import btlshp.utility.NodeIterator;
import btlshp.utility.NodeIteratorAction;

public class RepairMode extends GridMode {
	private Base base;
	
	public RepairMode(Base b, GameGrid gameGrid, Map gameMap) {
		super(gameGrid, gameMap);
		base = b;
	}


	@Override
	public void highlightCells() {
		super.highlightCells();
		
		NodeIterator it = base.getCoreArea();
		it.iterate(map, base.getLocation(), base.getDirection(), new NodeIteratorAction() {
			public void visit(MapNode n, Block b) {
				if(n == null)
					return;
				
				n.actionArea(true);
			}
		});
		
		if(base.canRepairOther()) {
			it = base.getRepairArea();
			it.iterate(map, base.getLocation(), base.getDirection(), new NodeIteratorAction() {
				@Override
				public void visit(MapNode n, Block b) {
					if(n == null || n.block == null || !(n.block instanceof ConstructBlock))
						return;

					ConstructBlock cb = (ConstructBlock)n.block;
					
					if(!(cb.getConstruct() instanceof Ship) || cb.getPlayer() != base.getPlayer())
						return;
					
					Ship s = (Ship)cb.getConstruct();
					NodeIterator shipit = s.getCoreIterator();
					shipit.iterate(map, s.getLocation(), s.getDirection(), new NodeIteratorAction() {
						public void visit(MapNode n, Block b) {
							n.actionArea(true);
						}
					});
				}
			});
		}
		
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
			if(cb != null && cb.getPlayer() == base.getPlayer()) {
				n.block.takeRepair();
				if(cb.getConstruct() instanceof Ship)
					Btlshp.getGame().sendTurn(TurnFactory.repairShip(cb, (Ship)cb.getConstruct()));
				else
					Btlshp.getGame().sendTurn(TurnFactory.repairBase(cb, (Base)cb.getConstruct()));
			}
		}

		grid.cancelAction();
	}

}
