package btlshp.junit;

import junit.framework.TestCase;
import btlshp.entities.Ship;
import btlshp.entities.Player;

public class PlayerTest extends TestCase {
	public void testAddShip() {
		Player myPlayer = new Player();
		Ship s1 = Ship.buildCruiser(myPlayer);
	
		// testing addShip
		assertTrue(myPlayer.addShip(s1));
		assertTrue(1 == myPlayer.shipCount());
		assertFalse(2 == myPlayer.shipCount());
	
		// add another ship
		Ship s2 = Ship.buildCruiser(myPlayer);
		assertTrue(myPlayer.addShip(s2));
		assertTrue(2 == myPlayer.shipCount());
		assertFalse(1 == myPlayer.shipCount());
		
		// add to fill up ship
		for (int i = 0; i < 6; i ++)
		{
			assertTrue(myPlayer.addShip(s2));
		}
		assertTrue(8 == myPlayer.shipCount());
		assertFalse(7 == myPlayer.shipCount());
		
		// add ship to full list
		assertFalse(myPlayer.addShip(s1));
		assertFalse(9 == myPlayer.shipCount());
		assertTrue(8 == myPlayer.shipCount());
	}

	public void testRemoveShip() {
		Player myPlayer = new Player();
		Ship s = Ship.buildCruiser(myPlayer);
		// remove ship from empty list
		assertFalse(myPlayer.removeShip(s));
		assertEquals(myPlayer.shipCount(), 0);
		assertFalse(-1 == myPlayer.shipCount());
		assertFalse(1 == myPlayer.shipCount());
		
		// add ships and remove
		Ship s1 = Ship.buildCruiser(myPlayer);
		Ship s2 = Ship.buildDestroyer(myPlayer);
		Ship s3 = Ship.buildMineSweeper(myPlayer);
		
		myPlayer.addShip(s1);
		myPlayer.addShip(s2);
		myPlayer.addShip(s3);
		
		assertTrue(myPlayer.removeShip(s2));
		assertEquals(myPlayer.shipCount(), 2);
		assertFalse(1 == myPlayer.shipCount());
		assertFalse(3 == myPlayer.shipCount());
	}
	
	public void testShipCount() {
		Player myPlayer = new Player();
		
		// no ship in list
		assertEquals(myPlayer.shipCount(), 0);
		assertFalse(-1 == myPlayer.shipCount());
		assertFalse(1 == myPlayer.shipCount());
		
		Ship s = Ship.buildCruiser(myPlayer);
		// full list of ship
		for (int i = 0; i < 8; i++)
		{
			myPlayer.addShip(s);
		}
		
		assertEquals(myPlayer.shipCount(), 8);
		assertFalse(0 == myPlayer.shipCount());
		assertFalse(9 == myPlayer.shipCount());
		
		
		// not full list
		Player myPlayer2 = new Player();
		for (int i = 0; i < 4; i++)
		{
			myPlayer2.addShip(s);
		}
		assertEquals(myPlayer2.shipCount(), 4);
		assertFalse(0 == myPlayer2.shipCount());
		assertFalse(8 == myPlayer2.shipCount());
	}
	
	public void testNumberOfMines() {
		Player myPlayer = new Player();
		// starting value of mines
		assertEquals(myPlayer.numberOfMines(), 10);
		assertFalse(9 == myPlayer.numberOfMines());
		assertFalse(11 == myPlayer.numberOfMines());
		
		// more than default mines
		myPlayer.addMine();
		assertEquals(myPlayer.numberOfMines(), 11);
		assertFalse(10 == myPlayer.numberOfMines());
		assertFalse(9 == myPlayer.numberOfMines());
		
		// less than default mines
		Player myPlayer2 = new Player();
		myPlayer2.removeMine();
		assertEquals(myPlayer2.numberOfMines(), 9);
		assertFalse(10 == myPlayer2.numberOfMines());
		assertFalse(11 == myPlayer2.numberOfMines());
	}
	
	public void testRemoveMine() {
		Player myPlayer = new Player();
		myPlayer.removeMine();
		assertEquals(myPlayer.numberOfMines(), 9);
		assertFalse(10 == myPlayer.numberOfMines());
		
		// no mines
		for(int i = 0; i < 9; i++)
		{
			myPlayer.removeMine();
		}
		try
		{
			myPlayer.removeMine();
			fail();
		}
		catch (IllegalArgumentException ill)
		{
			return;
		}
		assertEquals(myPlayer.numberOfMines(), 0);
		assertFalse(10 == myPlayer.numberOfMines());
		
	}
	
	public void testAddMine() {
		Player myPlayer = new Player();
		myPlayer.addMine();
		assertEquals(myPlayer.numberOfMines(), 11);
		assertFalse(10 == myPlayer.numberOfMines());
		
		// add another mine
		myPlayer.addMine();
		assertEquals(myPlayer.numberOfMines(), 12);
		assertFalse(11 == myPlayer.numberOfMines());
	}
}
