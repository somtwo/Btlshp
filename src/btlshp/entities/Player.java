package btlshp.entities;
import java.util.ArrayList;
public class Player {
	
	/**
	 * Instance variables
	 */
	private ArrayList<Ship> ship;
	private int numberOfMine;
	private int points;
	private final int MAX_NUM_SHIP = 8; // for default we have 8
	
	/**
	 * Default constructor
	 */
	public Player() {
		// Starting value of player
		ship = new ArrayList<Ship>();
		numberOfMine = 10;
		points = 0;
	}

	/**
	 * Add ship into the array list of ships
	 * @param s ship to be add
	 * @return true if ship is added to list else return false
	 */
	public boolean addShip(Ship s) {
		boolean shipAdded = false;
		if (ship.size() < MAX_NUM_SHIP)
		{
			ship.add(s);	
		}
		return shipAdded;
	}
	/**
	 * remove ship from the array list of ships
	 * @param s ship to be added
	 * @return true if ship is remove from list else return false
	 */
	public boolean removeShip(Ship s) {
		boolean removedShip = false;
		if(ship.size() != 0)
		{
			ship.remove(s);
			removedShip = true;
		}
		return removedShip;		
	}
	
	/**
	 * counts the ships of player
	 * @return number of ships player has in play
	 */
	public int shipCount() {
		return ship.size();
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
	
	
	/**
	 * Returns an array with all the ships the player has 
	 * @return
	 */
	public Ship[] getShips() {
		return ship.toArray(null);
	}
}
