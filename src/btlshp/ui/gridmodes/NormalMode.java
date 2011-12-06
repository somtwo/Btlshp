package btlshp.ui.gridmodes;

import javax.swing.JPopupMenu;

import btlshp.Btlshp;
import btlshp.entities.Base;
import btlshp.entities.Block;
import btlshp.entities.ConstructBlock;
import btlshp.entities.Map;
import btlshp.entities.Ship;
import btlshp.ui.BasePopupMenu;
import btlshp.ui.GameGrid;
import btlshp.ui.ShipPopupMenu;

public class NormalMode extends GridMode {

	public NormalMode(GameGrid gameGrid, Map gameMap) {
		super(gameGrid, gameMap);
	}


	@Override
	public void mouseClick(int x, int y) {
		int gridx = grid.gridLocX(x);
		int gridy = grid.gridLocY(y);
		
		Block b = map.getMapNode(gridx, gridy).block;
		if(b == null || !(b instanceof ConstructBlock))
			return;
		
		ConstructBlock cb = (ConstructBlock)b;
		if(Btlshp.getGame().getLocalPlayer() != cb.getPlayer())
			return;
		
		if((cb.getConstruct()) instanceof Ship) {
			JPopupMenu m = new ShipPopupMenu(grid, (Ship)cb.getConstruct());
			m.show(grid, x, y);
		}
		else if(cb.getConstruct() instanceof Base) {
			JPopupMenu m = new BasePopupMenu(grid, (Base)cb.getConstruct());
			m.show(grid, x, y);
		}
	}

}
