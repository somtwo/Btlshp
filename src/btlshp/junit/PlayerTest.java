package btlshp.junit;

import static org.junit.Assert.*;
import org.junit.Test;

import btlshp.entities.Player;

public class PlayerTest {
	public void testPlayer() {
		Player myPlayer = new Player();
		// Assure default of shipCount is 8
		assertTrue(8 == myPlayer.shipCount());
		assertFalse(0 == myPlayer.shipCount());
		
		
		
	}

}
