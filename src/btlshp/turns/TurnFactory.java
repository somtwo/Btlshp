package btlshp.turns;

import btlshp.entities.Base;
import btlshp.entities.Location;
import btlshp.entities.Ship;
import btlshp.enums.Direction;

public final class TurnFactory {
	/**
	* Factory method to create a turn representing a request to postpone the game.
	* @return the resulting turn object.
	*/
	static Turn requestPostoneGame() {
		return null;
	}

	/**
	* Factory method to create a turn representing a request to surrender the game.
	* @return the resulting turn object.
	*/
	static Turn requestSurrender() {
		return null;
	}

	/**
	* Factory method to create a turn representing accepting a surrender of the game.
	* @return the resulting turn object.
	*/
	static Turn acceptSurrender() {
		return null;
	}

	/**
	* Factory method to create a turn representing a confirmation to postpone the game.
	* @return the resulting turn object.
	*/
	static Turn confirmPostponeGame() {
		return null;
	}

	/**
	* Factory method to create a turn representing the start of a previously saved game.
	* @return the resulting turn object.
	*/
	static Turn loadGameState() {
		return null;
	}

	/**
	 * Move ship in a certain direction
	 * @param s ship to move
	 * @param dir direction to move the ship
	 * @param distance number of map units to move the ship
	 * @return the resulting turn object.
	 */
	static Turn moveShip(Ship s, Direction dir, int distance) {
		return null;
	}

	

	/**
	* Immerse mine at specified location
	* @param loc location to place mine
	* @return the resulting turn object.
	*/
	static Turn placeMine(Location loc) {
		return null;
	}

	/**
	* Withdraw mine from specified location
	* @param loc location of mine to be removed
	* @return the resulting turn object.
	*/
	static Turn takeMine(Location loc) {
		return null;
	}

	/**
	* Launch a torpedo from a ship
	* @param s ship to launch a torpedo from
	* @return true if torpedo launched
	*/
	static Turn launchTorpedo(Ship s) {
		return null;
	}

	/**
	* Shoot from a ship
	* @param s shooting ship
	* @param loc location to shoot at
	* @return the resulting turn object.
	*/
	static Turn shoot(Ship s, Location loc) {
		return null;
	}

	/**
	* Generates a repair ship turn for player
	* @param s ship to be repaired
	* @return the resulting turn object.
	*/
	static Turn repairShip(Ship s) {
		return null;
	}

	/**
	* Repairs specified base b
	* @param b base to be repaired
	* @return the resulting turn object.
	*/
	static Turn repairBase(Base b) {
		return null;
	}

	/**
	* Generates a pass turn for the player
	*/
	static Turn pass() {
		return null;
	}
}
