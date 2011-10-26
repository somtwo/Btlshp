package btlshp.entities;

import btlshp.enums.Direction;

public class Map {
	/**
	* Constructor to create a map object which will be the map used by two players to play a game.
	* Any random elements will be generated.
	* @param playerOne     Player 1 (left side)   	
	* @param playerTwo     Player 2 (right side)
	*/
	Map(Player playerOne, Player playerTwo) {
	}
	        	
	/**
	* Constructor to create a map object which will be the map used to continue a game between two players.
	* The given StoredMap object will be used to restore the previously saved map state.
	* @param map                               	StoredMap used to restore the map state
	* @param playerOne     Player 1 (left side)   	
	* @param playerTwo     Player 2 (right side)
	*/
	Map(StoredMap map, Player playerOne, Player playerTwo) {
	}
	        	
	/**
	* Returns a StoredMap object representing the current state of the map.
	* @return
	*/
	StoredMap makeStoredMap() {
	}

	        	
	/**
	* Performs the initial placement of a ship, either at the start of a game or on save load.
	* @param s     The ship to be placed
	* @param tail  The location of the tail of the ship
	* @param dir   The direction the ship should be facing.
	*/
	void addShip(Ship s, Location tail, Direction dir) {
	}
	        	
	/**
	* This method cleans up any visibility data from the previous turn and updates the map with the
	* radar/sonar of the current ship locations.
	*/
	void updateFrame() {
	}

	/**
	* Returns a MapBlock object representing the state of the map at a given location.
	* @param loc   Location to get the map block from
	* @returns The map block at the given location
	*/
	MapBlock getMapBlock(Location loc) {
	}

	/**
	* Returns a MapBlock object representing the state of the map at the given x/y coordinates.
	* @param x      x-map coordinate
	* @param y      y-map coordinate
	* @returns The map block at the given location.
	*/
	MapBlock getMapBlock(int x, int y) {
	}
	        	
	/**
	* Checks to see if a ship movement can be carried out.
	* @param s          Ship moving
	* @param dir        direction of movement.
	* @param blocks  number of blocks to move.
	* @return true if the ship movement can be carried out.
	*/
	boolean canMove(Ship s, Direction dir, int blocks) {
	}
	        	
	/**
	* Moves a ship either entirely or partially the number of blocks in the given direction.
	* @param s             	Ship moving
	* @param dir          	Direction to move the ship
	* @param blocks    	Number of blocks to be moved
	* @returns Number of blocks actually used.
	* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
	*/
	int move(Ship s, Direction dir, int blocks) {
	}
	        	
	/**
	* Checks whether a ship can rotate from it's current direction to a new given direction. This method only
	* considers physical barriers that can be see by radar.
	*
	* @param s 	          Ship to turn
	* @param newDir    New direction
	* @returns True if the ship can rotate in the new direction.
	* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
	*/
	boolean canShipRotate(Ship s, Direction newDir) {
	}
	        	
	/**
	* Attempts to turn a ship from it's current direction to a new direction.
	* @param s       Ship to turn
	* @param newDir  Direction ship should face by the end of the turn.
	* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
	*/
	void rotateShip(Ship s, Direction newDir) {
	}

	/**
	* Attempts to place a mine via the given ship in the given location
	* @param s       Ship that places the mine
	* @param loc     Map location to place the mine
	* @returns true if the mine was placed, or false if it could not be placed.
	* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
	*/
	boolean placeMine(Ship s, Location loc) {
	}

	/**
	* Attempts to pick up a mine via the given ship.
	* @param s       Ship that picks up the mine
	* @param loc     Location of the mine.
	* @return true if the mine was picked up, or false if it was not.
	* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
	*/
	boolean pickupMine(Ship s, Location loc) {
	}
	        	
	/**
	* Fires the torpedo of the given ship. The torpedo will start from the front of the ship and will
	* travel in the direction of the ship.
	* @param s       Ship to fire torpedo from.
	* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
	*/
	void fireTorpedo(Ship s) {
		
	}
	        	
	/**
	* Fires the guns of a ship at a specific location
	* @param s             	Ship to fire guns from
	* @param loc  Map location of the target of the fire
	* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
	*/
	void fireGuns(Ship s, Location loc) {
		
	}
}
