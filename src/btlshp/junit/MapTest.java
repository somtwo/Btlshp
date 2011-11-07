package btlshp.junit;

import junit.framework.TestCase;

import org.junit.Test;

import btlshp.entities.Location;
import btlshp.entities.MapNode;
import btlshp.entities.Player;
import btlshp.entities.Map;

public class MapTest extends TestCase{
	MapNode   nodes [][];
	Player leftPlayer = new Player(); 
	Player rightPlayer = new Player();
	private static final int MAPWIDTH = 30;
	private static final int MAPHEIGHT = 30;
	
	@Test
	public void testMap() {
		Map myMap = new Map(leftPlayer, leftPlayer); 
//		pass if map is 30X30, fail if not
		for (int i = 0; i < MAPWIDTH; i++) {
			for (int j = 0; j < MAPHEIGHT; j++) {
				// TODO implement empty node check
				Location myLoc = new Location(i, j);
				assertEquals(null, myMap.getMapNode(myLoc));

			}
		}
//		pass if docks are in right location, fail if not
		
//		pass if all boats are accounted for, fail if not
		
//		pass if all boats are attached to starting dock, fail if not
		
//		pass if all reefs are accounted for, and in correct location, fail if not

//		pass if no mines are on playing field, fail if not
		for (int i = 10; i < 20; i++) {
			// TODO implement the check player1 base
			//assertEquals((leftPlayer's base node), MapNode[i][0]);
		}
		for (int i = 10; i < 20; i++) {
			// TODO implement the check player1 base
			//assertEquals((rightPlayer's base node), MapNode[i][29]);
		}
	}
	
		//this test case tests both Map() and StoredMap() together...
	public void testStoreAndLoadMap() {
		// compare block by block the two representations if they are both the same -> pass, otherwise fail
	}
	
	public void testAddShipAndRemoveShip() {
//		for each type of ship:  
//			add it in a random location, and check with the map block to make sure that the boat is represented.  
//				pass if represented by correct type of block, fail if not
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
//		pass if mine can be placed into empty place and is placed, fail if not
//		fail if mine can be placed into an occupied space, pass if not
//		false if sub can pick up mine placed by opponent, fail if not
	}
	
	public void testPickupMine() {
//		pass if there is a mine, and it is picked up in range, fail if cannot be picked up by either team 
//		fail if a ‘mine’’ can be picked up from a mapblock where there is no mine, pass if not
//		fail if a ship other than the sub can pick up a mine, pass if only the sub can
//		fail if the sub can pick up a mine out of range, pass if not
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
