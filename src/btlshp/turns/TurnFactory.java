package btlshp.turns;

public final class TurnFactory {
	/**
	* Factory method to create a turn representing a request to postpone the game.
	* @return the resulting turn object.
	*/
	static Turn requestPostoneGame();

	/**
	* Factory method to create a turn representing a confirmation to postpone the game.
	* @return the resulting turn object.
	*/
	static Turn confirmPostponeGame();

	/**
	* Factory method to create a turn representing a request to surrender the game.
	* @return the resulting turn object.
	*/
	static Turn requestSurrender();

	/**
	* Factory method to create a turn representing accepting a surrender of the game.
	* @return the resulting turn object.
	*/
	static Turn acceptSurrender();

	/**
	* Factory method to create a turn representing a confirmation to postpone the game.
	* @return the resulting turn object.
	*/
	static Turn confirmPostponeGame();

	/**
	* Factory method to create a turn representing the start of a previously saved game.
	* @return the resulting turn object.
	*/
	static Turn loadGameState();

	/**
	 * Move ship in a certain direction
	 * @param s ship to move
	 * @param dir direction to move the ship
	 * @param distance number of map units to move the ship
	 * @return the resulting turn object.
	 */
	static Turn moveShip(Ship s, Direction dir, int distance);

	

	/**
	* Immerse mine at specified location
	* @param loc location to place mine
	* @return the resulting turn object.
	*/
	static Turn placeMine(Location loc);

	/**
	* Withdraw mine from specified location
	* @param loc location of mine to be removed
	* @return the resulting turn object.
	*/
	static Turn takeMine(Location loc);

	/**
	* Launch a torpedo from a ship
	* @param s ship to launch a torpedo from
	* @return true if torpedo launched
	*/
	static Turn launchTorpedo(Ship s);

	/**
	* Shoot from a ship
	* @param s shooting ship
	* @param loc location to shoot at
	* @return the resulting turn object.
	*/
	static Turn shoot(Ship s, Location loc);

	/**
	* Generates a repair ship turn for player
	* @param s ship to be repaired
	* @return the resulting turn object.
	*/
	static Turn repairShip(Ship s);

	/**
	* Repairs specified base b
	* @param b base to be repaired
	* @return the resulting turn object.
	*/
	static Turn repairBase(Base b);

	/**
	* Generates a pass turn for the player
	*/
	static Turn pass();
}
