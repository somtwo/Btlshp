package btlshp.enums;

/**
 * Enumation of the four cardinal directions.
 * @author Steve
 */
public enum Direction {
	North (0, "n"),
	East  (1, "e"),
	South (2, "s"),
	West  (3, "w");
	
	private final int    val;
	private final String suffix;
	
	Direction(int val, String suffix) {
		this.val = val;
		this.suffix = suffix;
	}
	
	public int val() { return val; }
	public String suffix() { return suffix; }
}
