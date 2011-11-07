package btlshp.turns;

import btlshp.entities.Base;
import btlshp.entities.ConstructBlock;
import btlshp.entities.Location;
import TurnTestStubs.Map;
import ConstructTestStubs.Ship;
import btlshp.enums.Direction;

public final class TurnFactory {
	/**
	* Factory method to create a turn representing a request to postpone the game.
	* @return the resulting turn object.
	*/
	public static Turn requestPostponeGame() {
		return new RequestPostponeGame();
	}

	/**
	* Factory method to create a turn representing a request to surrender the game.
	* @return the resulting turn object.
	*/
	public static Turn requestSurrender() {
		return new RequestSurrender();
	}

	/**
	* Factory method to create a turn representing accepting a surrender of the game.
	* @return the resulting turn object.
	*/
	public static Turn acceptSurrender() {
		return new AcceptSurrender();
	}

	/**
	* Factory method to create a turn representing a confirmation to postpone the game.
	* @return the resulting turn object.
	*/
	public static Turn confirmPostponeGame() {
		return new ConfirmPostponeGame();
	}

	/**
	* Factory method to create a turn representing the start of a previously saved game.
	* @return the resulting turn object.
	*/
	public static Turn loadGameState() {
		return new LoadGameState();
	}

	/**
	 * Move ship in a certain direction
	 * @param s ship to move
	 * @param dir direction to move the ship
	 * @param distance number of map units to move the ship
	 * @return the resulting turn object.
	 */
	public static Turn moveShip(Map m, Ship s, Direction dir, int distance) {
		return new MoveShip(m, s, dir, distance);
	}


	/**
	* Immerse mine at specified location
	* @param loc location to place mine
	* @return the resulting turn object.
	*/
	public static Turn placeMine(Map m, Ship s, Location loc) {
		return new PlaceMine(m, s, loc);
	}

	/**
	* Withdraw mine from specified location
	* @param loc location of mine to be removed
	* @return the resulting turn object.
	*/
	public static Turn takeMine(Map m, Ship s, Location loc) {
		return new TakeMine(m, s, loc);
	}

	/**
	* Launch a torpedo from a ship
	* @param s ship to launch a torpedo from
	* @return true if torpedo launched
	*/
	public static Turn launchTorpedo(Map m, Ship s) {
		return new LaunchTorpedo(m, s);
	}

	/**
	* Shoot from a ship
	* @param s shooting ship
	* @param loc location to shoot at
	* @return the resulting turn object.
	*/
	public static Turn shoot(Map m, Ship s, Location loc) {
		return new Shoot(m, s, loc);
	}

	/**
	* Generates a repair ship turn for player
	* @param s ship to be repaired
	* @return the resulting turn object.
	*/
	public static Turn repairShip(ConstructTestStubs.ConstructBlock repairBlock, Ship s) {
		return new RepairShip(s, repairBlock);
	}

	/**
	* Repairs specified base b
	* @param b base to be repaired
	* @return the resulting turn object.
	*/
	public static Turn repairBase(ConstructBlock repairBlock, Base b) {
		return new RepairBase(b, repairBlock);
	}

	/**
	* Generates a pass turn for the player
	*/
	public static Turn pass() {
		return new Pass();
	}
}
