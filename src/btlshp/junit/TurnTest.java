package btlshp.junit;

import java.util.ArrayList;

import org.junit.*;

import ConstructTestStubs.Ship;
import TurnTestStubs.Map;
import btlshp.entities.Base;
import btlshp.entities.ConstructBlock;
import btlshp.entities.Location;
import ConstructTestStubs.Player;
import btlshp.enums.Direction;
import btlshp.turns.Turn;
import btlshp.turns.TurnFactory;
import junit.framework.TestCase;

public class TurnTest extends TestCase {
	private ArrayList<Turn> t = new ArrayList<Turn>();
	@Before
	public void setUp() throws Exception{
		
		Player owner = new Player();
		Map m = new Map();
		Ship s = Ship.buildCruiser(owner);
		Direction dir = Direction.South;
		
		int distance = 0;
		Base b = new Base(null);
		Location loc = new Location(distance, distance);
		ConstructBlock repairBaseBlock = new ConstructBlock(b);
		ConstructTestStubs.ConstructBlock repairBlock = new ConstructTestStubs.ConstructBlock(s);
		
		t.add(TurnFactory.acceptSurrender());
		t.add(TurnFactory.confirmPostponeGame());
		t.add(TurnFactory.launchTorpedo(m, s));
		t.add(TurnFactory.loadGameState());
		t.add(TurnFactory.moveShip(m, s, dir, distance));
		t.add(TurnFactory.pass());
		t.add(TurnFactory.placeMine(m, s, loc));
		t.add(TurnFactory.repairBase(repairBaseBlock, b));
		t.add(TurnFactory.repairShip(repairBlock, s));
		t.add(TurnFactory.requestPostponeGame());
		t.add(TurnFactory.requestSurrender());
		t.add(TurnFactory.shoot(m, s, loc));
		t.add(TurnFactory.takeMine(m, s, loc));

		
	}
	@Test
	public void testExecuteTurn(){
		for(Turn turn: t){
			turn.executeTurn();
			assertTrue(turn.wasSuccessful());
			
		}

	}
}
