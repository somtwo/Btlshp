package btlshp.teststubs.turn;

import btlshp.entities.Location;
import btlshp.enums.Direction;
import btlshp.teststubs.construct.Ship;

public class Map {

		       	
		    	
		/**
		* Moves a ship either entirely or partially the number of blocks in the given direction.
		* @param s             	Ship moving
		* @param dir          	Direction to move the ship
		* @param blocks    	Number of blocks to be moved
		* @returns Number of blocks actually used.
		* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
		*/
		public  int move(Ship s, Direction dir, int blocks){
			return 0;
		}
		        	

		/**
		* Attempts to place a mine via the given ship in the given location
		* @param s       Ship that places the mine
		* @param loc     Map location to place the mine
		* @returns true if the mine was placed, or false if it could not be placed.
		* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
		*/
		public  boolean placeMine(Ship s, Location loc){
			return false;
		}
		/**
		* Attempts to pick up a mine via the given ship.
		* @param s       Ship that picks up the mine
		* @param loc     Location of the mine.
		* @return true if the mine was picked up, or false if it was not.
		* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
		*/
		public  boolean pickupMine(Ship s, Location loc){
			return false;
		}
		        	
		/**
		* Fires the torpedo of the given ship. The torpedo will start from the front of the ship and will
		* travel in the direction of the ship.
		* @param s       Ship to fire torpedo from.
		* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
		*/
		public  void fireTorpedo(Ship s){

		}
		        	
		/**
		* Fires the guns of a ship at a specific location
		* @param s             	Ship to fire guns from
		* @param loc  Map location of the target of the fire
		* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
		*/
		public void fireGuns(Ship s, Location loc) {
		}
}


