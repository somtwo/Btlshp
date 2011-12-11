package btlshp.utility;

import btlshp.entities.Block;
import btlshp.entities.MapNode;

public interface NodeIteratorAction {
	public void visit(MapNode n, Block b);
}
