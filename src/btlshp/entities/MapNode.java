package btlshp.entities;

public class MapNode {
	Block block;
	boolean p1minemark, p2minemark;
	
	MapNode() {
		
	}
	
	boolean p1SeesMine() {
		return p1minemark;
	}
	
	boolean p2SeesMine() {
		return p2minemark;
	}
}
