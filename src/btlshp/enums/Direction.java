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
	
	private Direction dirForVal(int value) {
		return value == 0 ? North :
		       value == 1 ? East :
			   value == 2 ? South : West;
	}
	
	public int val() { return val; }
	public String suffix() { return suffix; }
	public Direction backwardsDir() {
		int i = (val + 2) % 4;
		return dirForVal(i);
	}
	public Direction leftDir() {
		int i = (val - 1) % 4;
		return dirForVal(i);
	}
	public Direction rightDir() {
		int i = (val + 1) % 4;
		return dirForVal(i);
	}
}
