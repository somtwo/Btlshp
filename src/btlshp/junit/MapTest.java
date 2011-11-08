package btlshp.junit;

import junit.framework.TestCase;
import org.junit.Test;


import btlshp.entities.*;
import btlshp.enums.Direction;

public class MapTest extends TestCase{
	MapNode   nodes [][];
	Player leftPlayer = new Player(); 
	Player rightPlayer = new Player();
	private static final int MAPWIDTH = 30;
	private static final int MAPHEIGHT = 30;
	Location myLoc;
	
	
	Ship LmyCruiser = new Ship(leftPlayer, false, true, false, false, false, 10, 1, 1, 5, 6, 0, 5);
	Ship LmyTorpedoBoat = new Ship(leftPlayer, false, true, true, false, false, 8, 1, 1, 4, 5, 0, 4);
	Ship LmyDestroyer = new Ship(leftPlayer, false, false, true, false, false, 6, 1, 1, 0, 4, 0, 3);
	Ship LmineShip = new Ship(leftPlayer, true, false, false, true, true, 4, 1, 1, 0, 2, 2, 2);
	
	Ship RmyCruiser = new Ship(leftPlayer, false, true, false, false, false, 10, 1, 1, 5, 6, 0, 5);
	Ship RmyTorpedoBoat = new Ship(leftPlayer, false, true, true, false, false, 8, 1, 1, 4, 5, 0, 4);
	Ship RmyDestroyer = new Ship(leftPlayer, false, false, true, false, false, 6, 1, 1, 0, 4, 0, 3);
	Ship RmineShip = new Ship(leftPlayer, true, false, false, true, true, 4, 1, 1, 0, 2, 2, 2);
	
	Map myMap = new Map(leftPlayer, leftPlayer);
	//ConstructBlock blocks = new ConstructBlock(leftPlayer);
	@Test
	public void testMap() {

		//MapNode node = new MapNode();
		Base leftBase = new Base(leftPlayer);
		Base rightBase = new Base(rightPlayer);
		//ConstructBlock cblock = new ConstructBlock(leftPlayer);
		
//		pass if map is 30X30 with each block non-null, fail if not
		for (int i = 0; i < MAPWIDTH; i++) {
			for (int j = 0; j < MAPHEIGHT; j++) {
				myLoc = new Location(i, j);
				assertTrue(null != myMap.getMapNode(myLoc));
			}
		}
		
//		pass if bases are in right location, fail if not
		assertTrue(leftBase.getBlocks().length == 10);
		assertTrue(rightBase.getBlocks().length == 10);
		for (int i = 10; i < 20; i++) {
			myLoc = new Location(i, 0);

			//assertTrue(myMap.getMapNode(myLoc) == );
			
			assertFalse(leftBase.getBlocks() == rightBase.getBlocks());
			assertFalse(rightBase.getBlocks() == leftBase.getBlocks());
		}
//		pass if all boats are accounted for, fail if not
		
//		pass if all boats are attached to starting dock, fail if not
		
//		pass if all reefs are accounted for, and in correct location, fail if not

//		pass if no mines are on playing field, fail if not

	}
	
		//this test case tests both Map() and StoredMap() together...
	public void testStoreAndLoadMap() {
		// compare block by block the two representations if they are both the same -> pass, otherwise fail
		
	}
	
	public void testAddShipAndRemoveShip() {
//		for each type of ship:  
//			add it in a random empty location, and check with the map block to make sure that the boat is represented.  
//				pass if represented by correct type of block, fail if not
		myLoc = new Location(0, 0);
		
		myMap.placeShip(LmyCruiser, myLoc, Direction.North);
		assertTrue(true);
//			then remove the ship
//				pass if the ship is removed from the board, fail if not
	}
	
	public void updateFrame() {
//		Pass if ship can see opponent’s ship within radar for all four corners of radar, fail if not
//		Pass if submarine can see mine within range, fail if not
//		pass if the visibility range is correct for all ships, fail if not
//		pass if all ships are accounted for, fail if not
//		fail if any other ship than the sub can see mines
//		fail if you can see ships outside of the ships range
	}
	
	public void testGetMapNode() {
//		for each type of MapBlock: 
//			pass if mapblock correctly represents object, fail if object doesn't exist
	}
	
	public void testCanMove() {
//		pass if ship can move forward into empty blocks, fail if not
//		pass if ship can move backward into empty blocks, fail if not
//		pass if ship can move right one space into empty blocks, fail if not
//		pass if ship can move left one space into empty blocks, fail if not
//		pass if ship can move into/near mine, fail if not
//		fail if ship can move into a reef, pass if not
//		fail if ship can move into a ship, pass if not
//		fail if ship can move into base, pass if not
	}
	
	public void testMove() {
//		pass if ship moves to desired location if canMove is true, fail if not
//		pass if ship does not move to desired location if canMove is false, false if so
//		pass if ship correctly stops at mine, fail if not
	}
	
	public void testCanShipRotate() {
//		pass if ship can rotate into empty blocks, fail if not
//		pass if ship can rotate into/near mine, fail if not
//		fail if ship can rotate into a reef, pass if not
//		fail if ship can rotate into a ship, pass if not
//		fail if ship can rotate into base, pass if not
	}
	
	public void testRotateShip  () {
//		pass if ship can rotate into empty blocks, fail if not
//		fail if ship rotates through a reef, pass if not
//		fail if a ship can rotate through a mine, pass if not
//		fail if a ship can rotate through another ship, pass if not
//		fail if ship can rotate through a base, pass if not
	}
	
	public void testPlaceMine() {
		assertTrue(LmineShip.canPlaceMine());
		Location mineLoc = new Location(0, 0);

//		pass if mine can be placed into empty place and is placed, fail if not
		assertTrue(myMap.placeMine(LmineShip, mineLoc));
//		fail if mine can be placed into an occupied space, pass if not
		assertFalse(myMap.placeMine(LmineShip, mineLoc));
//		fail if anyone other than a mineShip can place a mine
		myMap.pickupMine(LmineShip, mineLoc);
		assertFalse(myMap.placeMine(LmyCruiser, mineLoc));
		assertFalse(myMap.placeMine(LmyTorpedoBoat, mineLoc));
		assertFalse(myMap.placeMine(LmyDestroyer, mineLoc));
	}
	
	public void testPickupMine() {
		Location mineLoc = new Location(0, 0);
//		pass if there is a mine, and it is picked up in range, fail if cannot be picked up by either team
							
		//test out of range for both ships
		myMap.placeMine(LmineShip, mineLoc);
		assertFalse(myMap.pickupMine(LmineShip, mineLoc));		
		assertFalse(myMap.pickupMine(RmineShip, mineLoc));
		
		//test in range for both ships
		
		
//		fail if a ‘mine’’ can be picked up from a mapblock where there is no mine, pass if not
		myMap.pickupMine(LmineShip, mineLoc);
		assertFalse(myMap.pickupMine(LmineShip, mineLoc));

//		fail if a ship other than the sub can pick up a mine, pass if only the sub can
		assertFalse(myMap.pickupMine(LmyCruiser, mineLoc));
		assertFalse(myMap.pickupMine(LmyTorpedoBoat, mineLoc));
		assertFalse(myMap.pickupMine(LmyDestroyer, mineLoc));
//		fail if the sub can pick up a mine out of range, pass if not
		
//		true if sub can pick up mine placed by opponent, fail if not
		myMap.placeMine(LmineShip, mineLoc);
		assertTrue(myMap.pickupMine(RmineShip, mineLoc));
	}
	
	public void testFireTorpedo() {
//		true if a torpedo is fired to desired location, false if not
//		fail if a sub can fire a torpedo, pass if not
//		fail if a torpedo can be fired anywhere other than straight, pass if not
	}
	
	public void testFireGun() {
//		pass if ship can fire gun at desired location, fail if not
//		fail if a sub can fire a gun, pass if not
//		fail if ship can fire a gun outside of range, pass if not
	}
	
}
