package btlshp.utility;

import java.util.ArrayList;

import btlshp.entities.Location;
import btlshp.enums.Direction;

public class NodeIterator {
	private ArrayList<Location> offsets;
	private Location            origin;
	private Direction			dir;
	
	
	/**
	 * Creates a NodeIterator with an empty collection.
	 */
	public NodeIterator(Location origin) {
		this.offsets = new ArrayList<Location>();
		this.origin = new Location(origin);
		this.dir = Direction.North;
	}

	
	/**
	 * Creates a NodeIterator populated with a list of locations.
	 * 
	 * @param locationArray
	 */
	public NodeIterator(Location origin, Location [] locationArray)
	{
		this(origin);
		
		for (int i = 0; i < locationArray.length; ++i)
			offsets.add(new Location(locationArray[i].getx() - origin.getx(), locationArray[i].gety() - origin.gety()));
	}
	
	/**
	 * Adds a location object to the collection of locations this object will iterate over.
	 * 
	 * @param   l    Node to be added.
	 * @throws  IllegalArgumentException if l is null
	 */
	public void add(Location l) {
		if(l == null)
			throw new IllegalArgumentException("NodeIterator.add given a null argument.");
		
		offsets.add(new Location(l.getx() - origin.getx(), l.gety() - origin.gety()));
	}
	
	
	/**
	 * Sets the origin of the iterator.
	 * 
	 * @param origin   new origin for the iterator.
	 */
	public void setOrigin(Location origin) {
		this.origin.setx(origin.getx());
		this.origin.sety(origin.gety());
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
		return offsets.get(index).getx() + origin.getx();
	}
	
	
	/**
	 * Returns a the y component of one of the locations in the iterator.
	 * 
	 * @param index   index of the Location within the given collection.
	 * @return        y component of the location
	 */
	public int gety(int index) {
		return offsets.get(index).gety() + origin.gety();
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
			Location l = offsets.get(i);
			
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
			Location l = offsets.get(i);
			int deltax = l.getx();
			int deltay = l.gety();
			
			l.setx(-deltay);
			l.sety(deltax);
		}
	}
	
	private void rotate180() {
		for(int i = 0; i < offsets.size(); ++i) {
			Location l = offsets.get(i);
			int deltax = l.getx();
			int deltay = l.gety();
			
			l.setx(-deltax);
			l.sety(-deltay);
		}
	}
	
	private void rotate270() {
		for(int i = 0; i < offsets.size(); ++i) {
			Location l = offsets.get(i);
			int deltax = l.getx();
			int deltay = l.gety();
			
			l.setx(deltay);
			l.sety(-deltax);
		}
	}
	
}
