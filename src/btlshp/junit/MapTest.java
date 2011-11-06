package btlshp.junit;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import btlshp.entities.Map;
import MapTestStubs.*;

public class MapTest {
	Player testPlayer1 = new Player();
	Player testPlayer2 = new Player();
	
	@Test
	/*
	Map(Player playerOne, Player playerTwo);
	pass if map is 30X30, fail if not
	pass if all boats are accounted for, fail if not
	pass if all boats are attached to starting dock, fail if not
	pass if all reefs are accounted for, and in correct location, fail if not
	pass if docks are in right location, fail if not
	pass if no mines are on playing field, fail if not
	*/
	public void testMap() {
		//Map myMap =  Map(testPlayer1, testPlayer2);
	}
	
		//this test case tests both Map() and StoredMap() together... 
	public void testStoreAndLoadMap() {
		
	}
	
	public void testAddShip() {
		
	}
	
	public void testRemoveShip()  {
		
	}
	
	public void updateFrame() {
		
	}
	
	public void testGetMapNode() {
		
	}
	
	public void testCanMove() {
		
	}
	
	public void testMove() {
		
	}
	
	public void testCanShipRotate() {
		
	}
	
	public void testRotateShip  () {
		
	}
	
	public void testPlaceMine() {
		
	}
	public void testPickupMine() {
		
	}
	public void testFireTorpedo() {
		
	}
	public void testFireGun() {
		
	}
}
