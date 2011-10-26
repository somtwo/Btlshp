package btlshp.qunit;

import junit.framework.TestCase;

import org.junit.Test;

import btlshp.entities.Location;

public class LocationTest extends TestCase {

	@Test
	public void testConstructor() {
		Location loc = new Location(4, 7);
		assertEquals(loc.getx(), 4);
		assertEquals(loc.gety(), 7);
		
		loc = new Location(1, 20);
		assertEquals(loc.getx(), 1);
		assertEquals(loc.gety(), 20);
	}
}
