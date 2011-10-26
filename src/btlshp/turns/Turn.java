package btlshp.turns;

public abstract class Turn {
	/**
	 * @returns true if the move object represents a successful move, false otherwise.
	 */
	boolean wasSuccessful() = 0;
	  
	/**
	 * Sends the object representing a move this player made, to the other player.
	 * @throws IllegalStateException If the move is sent from a machine that did not create it.
	 */
	void sendTurn() = 0;

	/**
	 * Executes a given move object representing a move from the other player.
	 * @throws IllegalStateException If the turn was not successful.
	 */
	void executeTurn() = 0;
}
