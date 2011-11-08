package btlshp.entities;

public class MapNode {
	public Block block;
	boolean p1minemark, p2minemark;
	
	public MapNode() {			//changed to public for MapTest.java Junit test
		
	}
	
	boolean p1SeesMine() {
		return p1minemark;
	}
	
	boolean p2SeesMine() {
		return p2minemark;
	}
}
