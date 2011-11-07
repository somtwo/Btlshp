package btlshp.junit;

import static org.junit.Assert.*;
import org.junit.Test;

import btlshp.entities.Ship;
import btlshp.entities.Player;

public class PlayerTest {
	Player myPlayer = new Player();
	public void testAddShip() {
		Ship s1 = Ship.buildCruiser(myPlayer);
	
		// testing addShip
		myPlayer.addShip(s1);
		assertTrue(1 == myPlayer.shipCount());
		assertFalse(2 == myPlayer.shipCount());
	
		// add another ship
		Ship s2 = Ship.buildCruiser(myPlayer);
		myPlayer.addShip(s2);
		assertTrue(2 == myPlayer.shipCount());
		assertFalse(1 == myPlayer.shipCount());
		
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
