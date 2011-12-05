package btlshp.utility;

import java.util.ArrayList;

import btlshp.entities.ConstructBlock;
import btlshp.entities.Location;
import btlshp.entities.Map;
import btlshp.entities.MapNode;
import btlshp.enums.Direction;

public class NodeIterator {
	private static class LocationBlock {
		public LocationBlock(Location l, ConstructBlock b) {
			block = b;
			loc = l;
		}
		
		public Location loc;
		public ConstructBlock block;
	}
	
	private ArrayList<LocationBlock> offsets;
	private Location                 origin;
	private Direction                dir;
	
	
	/**
	 * Creates a NodeIterator with an empty collection.
	 */
	public NodeIterator(Location origin) {
		this.offsets = new ArrayList<LocationBlock>();
		if(origin == null)
			this.origin = new Location(0, 0);
		else
			this.origin = new Location(origin);
		
		this.dir = Direction.North;
	}
	
	/**
	 * Adds a location object to the collection of locations this object will iterate over.
	 * 
	 * @param   l    Node to be added.
	 * @throws  IllegalArgumentException if l is null
	 */
	public void add(Location l, ConstructBlock b) {
		if(l == null)
			throw new IllegalArgumentException("NodeIterator.add given a null argument.");

		add(l.getx(), l.gety(), b);
	}
	
	
	public void add(int x, int y, ConstructBlock b) {
		offsets.add(new LocationBlock(new Location(x - origin.getx(), y - origin.gety()), b));
	}
	
	
	/**
	 * Sets the origin of the iterator.
	 * 
	 * @param origin   new origin for the iterator.
	 */
	public void setOrigin(Location origin) {
		setOrigin(origin.getx(), origin.gety());
	}
	
	/**
	 * Sets the origin of the iterator.
	 * 
	 * @param origin   new origin for the iterator.
	 */
	public void setOrigin(int x, int y) {
		this.origin.setx(x);
		this.origin.sety(y);
	}
	

	/**
	 * Returns the size of the collection.
	 * 
	 * @return size of the collection
	 */
	public int size() {
		return offsets.size();
	}
	
	
	/**
	 * Returns a the x component of one of the locations in the iterator.
	 * 
	 * @param index   index of the Location within the given collection.
	 * @return        x component of the location
	 */
	public int getx(int index) {
		return offsets.get(index).loc.getx() + origin.getx();
	}
	
	
	/**
	 * Returns a the y component of one of the locations in the iterator.
	 * 
	 * @param index   index of the Location within the given collection.
	 * @return        y component of the location
	 */
	public int gety(int index) {
		return offsets.get(index).loc.gety() + origin.gety();
	}
	
	
	/**
	 * Returns the ConstructBlock associated with the location at index in the iterator.
	 * 
	 * @param index   index of the element within the given collection
	 * @return        ConstructBlock associated with the location
	 */
	public ConstructBlock getBlock(int index) {
		return offsets.get(index).block;
	}

	
	/**
	 * Iterates over the given map and performs the given action on all MapNodes. If an iterator goes outside
	 * the bounds of the map, the action object is still called with null in case this should result in an error.
	 * 
	 * @param map     Map to iterate over
	 * @param origin  Origin of the iteration
	 * @param dir     Direction of the rotation of the iteration
	 * @param action  Action to use to visit each node
	 */
	public void iterate(Map map, Location origin, Direction dir, NodeIteratorAction action) {
		iterate(map, origin.getx(), origin.gety(), dir, action);
	}
	
	
	
	/**
	 * Iterates over the given map and performs the given action on all MapNodes. If an iterator goes outside
	 * the bounds of the map, the action object is still called with null in case this should result in an error.
	 * 
	 * @param map     Map to iterate over
	 * @param x	      x origin of the iteration
	 * @param y       y origin of the iteration
	 * @param dir     Direction of the rotation of the iteration
	 * @param action  Action to use to visit each node
	 */
	public void iterate(Map map, int x, int y, Direction dir, NodeIteratorAction action) {
		setOrigin(x, y);
		rotate(dir);
		for(int i = 0; i < offsets.size(); ++i) {
			LocationBlock lb = offsets.get(i);
			
			MapNode n = map.getMapNode(lb.loc.getx() + origin.getx(), lb.loc.gety() + origin.gety());
			
			action.visit(n, lb.block);
		}

	}
	
	
	/**
	 * Offsets all locations in the collection by the given xOffset and yOffset
	 * 
	 * @param xOffset   number of map columns to move right (can be negative for left)
	 * @param yOffset   number of map rows to move down (can be negative for up)
	 */
	public void offset(int xOffset, int yOffset)
	{
		for(int i = 0; i < offsets.size(); ++i) {
			Location l = offsets.get(i).loc;
			
			l.setx(l.getx() + xOffset);
			l.sety(l.gety() + yOffset);
		}
	}
	
	
	/**
	 * Rotates the locations contained in the collection about its origin from its current 
	 * direction to the given one.
	 * 
	 * @param newDir   The new direction the collection will be facing.
	 */
	public void rotate(Direction newDir) {
		int i = newDir.val() - dir.val();
		
		if(i < 0)
			i += 4;
		
		switch(i) {
		case 1: 
			rotate90();
			break;
		case 2:
			rotate180();
			break;
		case 3:
			rotate270();
			break;
		default:
			break;
		}
		
		dir = newDir;
	}
	
	
	private void rotate90() {
		for(int i = 0; i < offsets.size(); ++i) {
			Location l = offsets.get(i).loc;
			int deltax = l.getx();
			int deltay = l.gety();
			
			l.setx(-deltay);
			l.sety(deltax);
		}
	}
	
	private void rotate180() {
		for(int i = 0; i < offsets.size(); ++i) {
			Location l = offsets.get(i).loc;
			int deltax = l.getx();
			int deltay = l.gety();
			
			l.setx(-deltax);
			l.sety(-deltay);
		}
	}
	
	private void rotate270() {
		for(int i = 0; i < offsets.size(); ++i) {
			Location l = offsets.get(i).loc;
			int deltax = l.getx();
			int deltay = l.gety();
			
			l.setx(deltay);
			l.sety(-deltax);
		}
	}
	
}
