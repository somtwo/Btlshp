package btlshp.junit;

import java.util.ArrayList;

import org.junit.*;

import btlshp.entities.Base;
import btlshp.entities.ConstructBlock;
import btlshp.entities.Location;
import btlshp.enums.Direction;
import btlshp.teststubs.construct.Player;
import btlshp.teststubs.construct.Ship;
import btlshp.teststubs.turn.Map;
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
		btlshp.teststubs.construct.ConstructBlock repairBlock = new btlshp.teststubs.construct.ConstructBlock(s);
		
		t.add(TurnFactory.acceptSurrender());
		t.add(TurnFactory.confirmPostponeGame());
		t.add(TurnFactory.launchTorpedo(m, s));
		t.add(TurnFactory.loadGameState());
		t.add(TurnFactory.saveGameState());
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
			assertNotNull(turn);
			turn.executeTurn();
			assertTrue(turn.wasSuccessful());
			
		}
	}
	public void testTurnTypes(){
		assertEquals(t.get(0).toString(),"AcceptSurrender");
		assertEquals(t.get(1).toString(),"ConfirmPostponeGame");
		assertEquals(t.get(2).toString(),"LaunchTorpedo");
		assertEquals(t.get(3).toString(),"LoadGameState");
		assertEquals(t.get(4).toString(),"SaveGameState");
		assertEquals(t.get(5).toString(),"MoveShip");
		assertEquals(t.get(6).toString(),"Pass");
		assertEquals(t.get(7).toString(),"PlaceMine");
		assertEquals(t.get(8).toString(),"RepairBase");
		assertEquals(t.get(9).toString(),"RepairShip");
		assertEquals(t.get(10).toString(),"RequestPostponeGame");
		assertEquals(t.get(11).toString(),"RequestSurrender");
		assertEquals(t.get(12).toString(),"Shoot");
		assertEquals(t.get(13).toString(),"TakeMine");
	}
}
