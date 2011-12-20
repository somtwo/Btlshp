package btlshp.turns;

import btlshp.entities.*;
import btlshp.enums.Direction;


public final class TurnFactory {
	/**
	 * Factory method to create a turn for joing two games
	 */
	public static JoinGame joinGame(){
		return new JoinGame();
	}
	/**
	* Factory method to create a turn representing a request to postpone the game.
	* @return the resulting turn object.
	*/
	public static RequestPostponeGame requestPostponeGame() {
		return new RequestPostponeGame();
	}

	/**
	* Factory method to create a turn representing a request to surrender the game.
	* @return the resulting turn object.
	*/
	public static RequestSurrender requestSurrender() {
		return new RequestSurrender();
	}

	/**
	* Factory method to create a turn representing accepting a surrender of the game.
	* @return the resulting turn object.
	*/
	public static AcceptSurrender acceptSurrender() {
		return new AcceptSurrender();
	}

	/**
	* Factory method to create a turn representing a confirmation to postpone the game.
	* @return the resulting turn object.
	*/
	public static ConfirmPostponeGame confirmPostponeGame() {
		return new ConfirmPostponeGame();
	}

	/**
	* Factory method to create a turn representing the start of a previously saved game.
	* @param f filename of the saved game
	* @return the resulting turn object.
	*/
	public static LoadGameState loadGameState(String f) {
		return new LoadGameState(f);
	}
	/**
	 * Factory method to create a turn representing the saving of game state
	 * @return the resulting turn object
	 */
	public static SaveGameState saveGameState(Map map){
		return new SaveGameState(map);
	}
	/**
	 * Move ship in a certain direction
	 * @param s ship to move
	 * @param dir direction to move the ship
	 * @param distance number of map units to move the ship
	 * @return the resulting turn object.
	 */
	public static MoveShip moveShip(Ship s, Direction dir, int distance) {
		return new MoveShip(s, dir, distance);
	}

	public static RotateShip rotateShip(Ship s, Direction dir){
		return new RotateShip(s, dir);
	}
	/**
	* Immerse mine at specified location
	* @param loc location to place mine
	* @return the resulting turn object.
	*/
	public static PlaceMine placeMine(Ship s, Location loc) {
		return new PlaceMine(s, loc);
	}

	/**
	* Withdraw mine from specified location
	* @param loc location of mine to be removed
	* @return the resulting turn object.
	*/
	public static TakeMine takeMine(Ship s, Location loc) {
		return new TakeMine(s, loc);
	}

	/**
	* Launch a torpedo from a ship
	 * @param m 
	* @param s ship to launch a torpedo from
	* @return true if torpedo launched
	*/
	public static LaunchTorpedo launchTorpedo(Ship s) {
		return new LaunchTorpedo(s);
	}

	/**
	* Shoot from a ship
	* @param s shooting ship
	* @param loc location to shoot at
	* @return the resulting turn object.
	*/
	public static Shoot shoot(Ship s, Location loc) {
		return new Shoot(s, loc);
	}

	/**
	* Generates a repair ship turn for player
	* @param s ship to be repaired
	* @return the resulting turn object.
	*/
	public static RepairShip repairShip(ConstructBlock repairBlock, Ship s) {
		return new RepairShip(s, repairBlock);
	}

	/**
	* Repairs specified base b
	* @param b base to be repaired
	* @return the resulting turn object.
	*/
	public static RepairBase repairBase(ConstructBlock repairBlock, Base b) {
		return new RepairBase(b, repairBlock);
	}

	/**
	* Generates a pass turn for the player
	*/
	public static Pass pass() {
		return new Pass();
	}
	
	public static RotateShip180 rotateShip180(Ship s, boolean leftTurn) {
		return new RotateShip180(s, leftTurn);
	}
}
