package btlshp.entities;
public class Player {
	
	/**
	 * Instance variables
	 */
	private int numberOfShip;
	private int numberOfMine;
	private int points;
	
	/**
	 * Default constructor
	 */
	public Player() {
		// Starting value of player
		numberOfShip = 8;
		numberOfMine = 10;
		points = 0;
	}

	/**
	 * counts the ships of player
	 * @return number of ships player has in play
	 */
	public int shipCount() {
		return numberOfShip;
	}

	/**
	* returns the number of mines a player has
	*@return number of mines player has in play
	*/
	public int numberOfMines() {
		return numberOfMine;
	}

	/**
	 * Removes a mine from the player.
	 * @throws IllegalArgumentException if the player has no more mines.
	 */
	public void removeMine() {
		if (numberOfMine == 0)
		{
			throw new IllegalArgumentException("No more mines");
		}
		numberOfMine = numberOfMine - 1;
	}

	/**
	 * Increments the number of mines the player has.
	 */
	public void addMine() {
		numberOfMine = numberOfMine + 1;
	}
	
	/**
	 * Add points 
	 */
	public void addPoints() {
		points = points + 1;
	}

	/**
	 * Calculates the current number of points the player has.
	 */
	public int getPoints() {
		return points;
	}
}
