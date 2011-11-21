package btlshp.enums;

/**
 * Enumation of the four cardinal directions.
 * @author Steve
 */
public enum Direction {
	North (0),
	East(1),
	South(2),
	West(3);
	
	private final int val;
	
	Direction(int val) {
		this.val = val;
	}
	
	public int val() { return val; }
}
