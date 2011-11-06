package btlshp.junit;

import java.util.ArrayList;

import org.junit.*;

import btlshp.entities.*;
import btlshp.enums.Direction;
import btlshp.enums.ShipType;
import btlshp.turns.Turn;
import btlshp.turns.TurnFactory;
import junit.framework.TestCase;

public class TurnTest extends TestCase {
	private ArrayList<Turn> t = new ArrayList<Turn>();
	
	@Before
	public void testConstructor(){
		Player owner = new Player();
		Map m;
		Ship s;
		Direction dir = Direction.South;
		
		int distance = 0;
		Base b = new Base(null);
		Location loc = new Location(distance, distance);
		ConstructBlock repairBlock = new ConstructBlock(b);
		/**
		t.add(TurnFactory.acceptSurrender());
		t.add(TurnFactory.confirmPostponeGame());
		t.add(TurnFactory.launchTorpedo(m, s));
		t.add(TurnFactory.loadGameState());
		t.add(TurnFactory.moveShip(m, s, dir, distance));
		t.add(TurnFactory.pass());
		t.add(TurnFactory.placeMine(m, s, loc));
		t.add(TurnFactory.repairBase(repairBlock, b));
		t.add(TurnFactory.repairShip(repairBlock, s));
		t.add(TurnFactory.requestPostponeGame());
		t.add(TurnFactory.requestSurrender());
		t.add(TurnFactory.shoot(m, s, loc));
		t.add(TurnFactory.takeMine(m, s, loc));
		**/
	}
	public void testExecuteTurn(){
		for(Turn turn: t){
			turn.executeTurn();
			assertTrue(turn.wasSuccessful());
			
		}
	}
}
