package btlshp.junit;

import static org.junit.Assert.*;
import org.junit.Test;

import PlayerTestStubs.Ship;
import btlshp.entities.Player;

public class PlayerTest {
	Ship s = new Ship();
	public void testAddShip() {
		Player myPlayer = new Player();
		// testing addShip
		myPlayer.addShip(s)
		;
		
	}

	public void testRemoveShip() {
		//TODO
	}
	
	public void testShipCount() {
		//TODO
	}
	
	public void testNumberOfMines() {
		Player myPlayer = new Player();
		// starting value of mines
		assertEquals(myPlayer.numberOfMines(), 10);
		assertFalse(9 == myPlayer.numberOfMines());
		assertFalse(11 == myPlayer.numberOfMines());
		
		myPlayer.addMine();
		assertEquals(myPlayer.numberOfMines(), 11);
		
	}
}
