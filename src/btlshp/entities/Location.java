package btlshp.entities;

import java.io.Serializable;

public class Location implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7855842802631651058L;
	private int x, y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Location(Location other) {
		this.x = other.x;
		this.y = other.y;
	}
	
	public int getx() {
		return x;
	}
	
	public int gety() {
		return y;
	}
	
	public void setx(int x) {
		this.x = x;
	}
	
	public void sety(int y) {
		this.y = y;
	}
}
