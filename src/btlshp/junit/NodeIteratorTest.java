package btlshp.junit;

import org.junit.Before;
import org.junit.Test;

import btlshp.entities.Location;
import btlshp.enums.Direction;
import btlshp.utility.NodeIterator;
import junit.framework.TestCase;

public class NodeIteratorTest extends TestCase {
	NodeIterator testIterator;
	Location     testLocations[];
	
	@Before
	public void setUp() {
		testLocations = new Location[2];
		testLocations[0] = new Location(0, 0);
		testLocations[1] = new Location(15, 3);
		
		testIterator = new NodeIterator(testLocations[0], testLocations);
	}
	
	@Test
	public void testConstructor() {
		assertEquals(testLocations.length, testIterator.size());
		
		for(int i = 0; i < testLocations.length; ++i) {
			assertTrue(testLocations[i].getx() == testIterator.getx(i) &&
					   testLocations[i].gety() == testIterator.gety(i));
		}
	}
	
	@Test
	public void testOffset() {
		int xoff = 4;
		int yoff = -1;
		
		testIterator.offset(xoff, yoff);
		
		for(int i = 0; i < testLocations.length; ++i) {
			assertTrue(testLocations[i].getx() + xoff == testIterator.getx(i) &&
					   testLocations[i].gety() + yoff == testIterator.gety(i));
		}
	}
	
	
	@Test
	public void testRotate90() {
		
		testIterator.rotate(Direction.East);
		
		assertEquals(0, testIterator.getx(0));
		assertEquals(0, testIterator.gety(0));
		assertEquals(-testLocations[1].gety(), testIterator.getx(1));
		assertEquals(testLocations[1].getx(), testIterator.gety(1));
	}
	
	@Test
	public void testRotate180() {
		testIterator.rotate(Direction.South);
		
		assertEquals(0, testIterator.getx(0));
		assertEquals(0, testIterator.gety(0));

		assertEquals(-testLocations[1].getx(), testIterator.getx(1));
		assertEquals(-testLocations[1].gety(), testIterator.gety(1));
	}
	
	
	@Test
	public void testRotate270() {
		testIterator.rotate(Direction.West);
		
		assertEquals(0, testIterator.getx(0));
		assertEquals(0, testIterator.gety(0));
		
		assertEquals(testLocations[1].gety(), testIterator.getx(1));
		assertEquals(-testLocations[1].getx(), testIterator.gety(1));
	}
	
	@Test
	public void testAdd() {
		Location l = new Location(1, 2);
		
		testIterator.add(l);
		
		assertEquals(3, testIterator.size());

		assertEquals(l.getx(), testIterator.getx(2));
		assertEquals(l.gety(), testIterator.gety(2));
	}

}
