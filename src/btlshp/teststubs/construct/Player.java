package btlshp.teststubs.construct;

public class Player {
	/**
	 * Default constructor
	 */
	public Player() {
	}

	/**
	 * counts the ships of player
	 * @return number of ships player has in play
	 */
	public int shipCount() {
		return 0;
	}

	/**
	* returns the number of mines a player has
	*@return number of mines player has in play
	*/
	public int numberOfMines() {
		return 0;
	}

	/**
	 * Removes a mine from the player.
	 * @throws IllegalArgumentException if the player has no more mines.
	 */
	public void removeMine() {
	}

	/**
	 * Increments the number of mines the player has.
	 */
	public void addMine() {
	}

	/**
	 * Calculates the current number of points the player has.
	 */
	public int getPoints() {
		return 0;
	}
}
