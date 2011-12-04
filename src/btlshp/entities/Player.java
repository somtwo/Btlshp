package btlshp.entities;
import java.io.Serializable;
import java.util.ArrayList;
public class Player implements Serializable {
	
	private static final long serialVersionUID = -3335146204678437423L;
	/**
	 * Instance variables
	 */
	private ArrayList<Ship> ships;
	private Base            base;
	private int             numberOfMines;
	private int             points;
	private final int       MAX_NUM_SHIP = 9; // for default we have 9
	
	/**
	 * Default constructor
	 */
	public Player() {
		// Starting value of player
		ships = new ArrayList<Ship>();
		numberOfMines = 10;
		points = 0;
		
		// Create the constructs the player has
		base = new Base(this);
		
		for(int i = 0; i < 2; ++i) {
			addShip(Ship.buildCruiser(this));
			addShip(Ship.buildDestroyer(this));
			addShip(Ship.buildMineSweeper(this));
			addShip(Ship.buildTorpedoBoat(this));
		}
		addShip(Ship.buildTorpedoBoat(this));
	}

	/**
	 * Add ship into the array list of ships
	 * @param s ship to be add
	 * @return true if ship is added to list else return false
	 */
	public boolean addShip(Ship s) {
		boolean shipAdded = false;
		if (ships.size() < MAX_NUM_SHIP)
		{
			ships.add(s);
			shipAdded = true;
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
		if(ships.size() != 0)
		{
			ships.remove(s);
			removedShip = true;
		}
		return removedShip;		
	}
	
	
	public Base getBase() {
		return base;
	}
	
	/**
	 * counts the ships of player
	 * @return number of ships player has in play
	 */
	public int shipCount() {
		return ships.size();
	}

	/**
	* returns the number of mines a player has
	*@return number of mines player has in play
	*/
	public int numberOfMines() {
		return numberOfMines;
	}

	/**
	 * Removes a mine from the player.
	 * @throws IllegalArgumentException if the player has no more mines.
	 */
	public void removeMine() {
		if (numberOfMines == 0)
		{
			throw new IllegalArgumentException("No more mines");
		}
		numberOfMines = numberOfMines - 1;
	}

	/**
	 * Increments the number of mines the player has.
	 */
	public void addMine() {
		numberOfMines = numberOfMines + 1;
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
		return ships.toArray(new Ship[0]);
	}
}
