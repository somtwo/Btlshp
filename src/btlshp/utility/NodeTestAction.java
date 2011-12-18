package btlshp.utility;

import btlshp.entities.Block;
import btlshp.entities.MapNode;

public interface NodeTestAction {
	boolean visit(MapNode n, Block b);
}
