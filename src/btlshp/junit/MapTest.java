package btlshp.junit;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;


import btlshp.entities.*;
import btlshp.enums.Direction;

public class MapTest extends TestCase{
	private static final int MAPWIDTH = 30;
	private static final int MAPHEIGHT = 30;
	Player leftPlayer; 
	Player rightPlayer;
	Location myLoc;
	Map testMap;

	@Before 
	public void setUp() {
		testMap = new Map(leftPlayer, leftPlayer);
		leftPlayer = new Player(); 
		rightPlayer = new Player();
	}

	@Test
	public void testMapConstructor() {

		// pass if map is 30X30 with each block non-null, fail if not
		for (int i = 0; i < MAPWIDTH; i++) {
			for (int j = 0; j < MAPHEIGHT; j++) {
				assertTrue(null != testMap.getMapNode(i, j));
			}
		}
		
		Base leftBase = testMap.getLeftBase();
		Base rightBase = testMap.getRightBase();
		
		// pass if bases are in right location, fail if not
		assertTrue(leftBase.getBlocks().length == 10);
		assertTrue(rightBase.getBlocks().length == 10);
		
		for(int i = 0; i < 10; ++i) {
			assertEquals(testMap.getMapNode(0, 10 + i).block, leftBase.getBlocks()[i]);
			assertEquals(testMap.getMapNode(29, 10 + i).block, rightBase.getBlocks()[i]);
		}
		
		Ship[] ships = testMap.getShips();
		
		assertTrue(ships.length == 18);
		
		// Each player should have 9 ships
		int p1c = 0, p2c = 0;
		
		for(int i = 0; i < ships.length; ++i) {
			if(ships[i].getPlayer() == leftPlayer)
				p1c++;
			if(ships[i].getPlayer() == rightPlayer)
				p2c++;
		}
		
		assertTrue(p1c == 9);
		assertTrue(p2c == 9);
				
		// pass if all reefs are accounted for, and in correct location, fail if not
		// pass if no mines are on playing field, fail if not
	}
	
	// this test case tests both Map() and StoredMap() together...
	@Test
	public void testStoreAndLoadMap() {
		// compare block by block the two representations if they are both the same -> pass, otherwise fail
	}
	
	@Test
	public void testAddShipAndRemoveShip() {
		Ship cruiser = Ship.buildCruiser(leftPlayer);
		testMap.addShip(cruiser);
		Ship [] ships = testMap.getShips();
		
		int i;
		
		for(i = 0; i < ships.length; ++i)
			if(ships[i] == cruiser)
				break;
		
		// Assert that the for loop broke
		assertTrue(i < ships.length);
		
		
		testMap.removeShip(cruiser);
		
		for(i = 0; i < ships.length; ++i)
			if(ships[i] == cruiser)
				break;
		
		// Assert that the for loop did not brake
		assertTrue(i == ships.length);
	}
	
	public void updateFrame() {
		// TODO: This should test functionality that is not existant yet.
		
		//		Pass if ship can see opponent’s ship within radar for all four corners of radar, fail if not
		//		Pass if submarine can see mine within range, fail if not
		//		pass if the visibility range is correct for all ships, fail if not
		//		pass if all ships are accounted for, fail if not
		//		fail if any other ship than the sub can see mines
		//		fail if you can see ships outside of the ships range
	}
	
	public void testGetMapNode() {
		MapNode [][] nodes = testMap.getMapNodes();
		
		assertTrue(nodes[0][0] == testMap.getMapNode(0, 0));
		assertTrue(nodes[10][2] == testMap.getMapNode(2, 10));
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
		Ship mineShip = Ship.buildMineSweeper(leftPlayer);
		
		testMap.addShip(mineShip);
		testMap.placeShip(mineShip, 0, 0, Direction.South);
		
		assertTrue(mineShip.canPlaceMine());
		Location mineLoc = new Location(1, 1);

//		pass if mine can be placed into empty place and is placed, fail if not
		assertTrue(testMap.placeMine(mineShip, mineLoc));
//		fail if mine can be placed into an occupied space, pass if not
		assertFalse(testMap.placeMine(mineShip, mineLoc));
//		fail if anyone other than a mineShip can place a mine
		testMap.pickupMine(mineShip, mineLoc);
		assertFalse(testMap.placeMine(mineShip, mineLoc));
		assertFalse(testMap.placeMine(mineShip, mineLoc));
		assertFalse(testMap.placeMine(mineShip, mineLoc));
	}
	
	public void testPickupMine() {
		Ship mineShip = Ship.buildMineSweeper(leftPlayer);
		Ship otherMineShip = Ship.buildMineSweeper(rightPlayer);
		
		testMap.addShip(mineShip);
		testMap.addShip(otherMineShip);
		
		Location mineLoc = new Location(1, 1);
		Location mineShipLoc = new Location(0,0);
		Direction MineShipDir = Direction.South;
//		fail if the sub can pick up a mine out of range, pass if not
		testMap.placeMine(mineShip, mineLoc);
		assertFalse(testMap.pickupMine(mineShip, mineLoc));		
		assertFalse(testMap.pickupMine(otherMineShip, mineLoc));
		
//		pass if there is a mine, and it is picked up in range, fail if cannot be picked up by either team
		testMap.placeShip(mineShip, mineShipLoc, MineShipDir);		//place ships in range
		testMap.placeShip(mineShip, mineShipLoc, MineShipDir);
		
		testMap.placeMine(mineShip, mineLoc);
		assertTrue(testMap.pickupMine(otherMineShip, mineLoc));			//test left ship
		
		testMap.placeMine(mineShip, mineLoc);
		assertTrue(testMap.pickupMine(otherMineShip, mineLoc));			//test right ship
		
//		fail if a ‘mine’’ can be picked up from a mapblock where there is no mine, pass if not
		testMap.pickupMine(mineShip, mineLoc);
		assertFalse(testMap.pickupMine(mineShip, mineLoc));

//		fail if a ship other than the sub can pick up a mine, pass if only the sub can
		Ship criuser = Ship.buildCruiser(leftPlayer);
		testMap.addShip(criuser);
		assertFalse(testMap.pickupMine(criuser, mineLoc));

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
