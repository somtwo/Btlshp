package btlshp.turns;

import btlshp.entities.Base;
import btlshp.entities.Block;
import btlshp.entities.ConstructBlock;
import btlshp.entities.Location;
import btlshp.entities.Map;
import btlshp.entities.Ship;
import btlshp.enums.Direction;
import java.io.*;
public interface Turn {
	/**
	 * @returns true if the move object represents a successful move, false otherwise.
	 */
	boolean wasSuccessful();

	/**
	 * Executes a given move object representing a move from the other player.
	 * @throws IllegalStateException If the turn was not successful.
	 */
	void executeTurn();
}

class Pass implements Turn{
	@Override
	public void executeTurn() {//Does no work
	}
	@Override
	public boolean wasSuccessful() {
		//Always returns true because a pass turn cannot fail
		return true;
	}
}
class RequestPostponeGame implements Turn{
	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
	}
	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
}
class ConfirmPostponeGame implements Turn{


	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
}
class LoadGameState implements Turn{


	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
}
class RequestSurrender implements Turn{


	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
}
class AcceptSurrender implements Turn{
	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
}
class MoveShip implements Turn{
	private Ship s;
	private Direction dir;
	private int distance;
	private Map m;
	private boolean success = false;

	public MoveShip(Map m, Ship s, Direction dir, int distance) {
		this.m = m;
		this.s = s;
		this.dir = dir;
		this.distance = distance;
	}

	@Override
	public void executeTurn() {
		try{
			m.move(s, dir, distance);
			success = true;
		}catch(IllegalStateException e){
			success = false;
		}
		
	}

	@Override
	public boolean wasSuccessful() {
		return success;
	}
}
class PlaceMine implements Turn{
	private Map m;
	private Location loc;
	private Ship s;
	private boolean success = false;
	public PlaceMine(Map m, Ship s, Location loc) {
		this.loc = loc;
		this.s = s;
	}

	@Override
	public void executeTurn() {
		try{
			m.placeMine(s, loc);
			success = true;
		}catch(IllegalStateException e){
			success = false;
		}
	}


	@Override
	public boolean wasSuccessful() {
		return success;
	}
}
class TakeMine implements Turn{
	private Location loc;
	private Ship s;
	private Map m;
	private boolean success = false;

	public TakeMine(Map m, Ship s, Location loc) {
		this.s = s;
		this.loc = loc;
	}

	@Override
	public void executeTurn() {
		try{
			m.pickupMine(s, loc);
			success = true;
		}catch(IllegalStateException e){
			success = false;
		}
	}

	@Override
	public boolean wasSuccessful() {
		return success;
	}
}
class LaunchTorpedo implements Turn{
	private Map m;
	private Ship s;
	private boolean success = false;


	LaunchTorpedo(Map m, Ship s) {
		this.m = m;
		this.s = s;
	}

	@Override
	public void executeTurn() {
		try{
			m.fireTorpedo(s);
			success = true;
		}catch(IllegalStateException e){
			success = false;
		}
	}


	@Override
	public boolean wasSuccessful() {
		return success;
	}
}

class Shoot implements Turn{

	private Map m;
	private Ship s;
	private Location loc;
	private boolean success = false;

	public Shoot(Map m, Ship s, Location loc) {
		this.m = m;
		this.s = s;
		this.loc = loc;
	}

	@Override
	public void executeTurn() {
		try{
			m.fireGuns(s, loc);
			success = true;
		}catch(IllegalStateException e){
			success = false;
		}
	}

	@Override
	public boolean wasSuccessful() {
		return success;
	}
}
class RepairBase implements Turn{

	private ConstructBlock repairBlock;
	private Base b;
	private boolean success = false;

	RepairBase(Base b, ConstructBlock repairBlock) {
		this.b = b;
		this.repairBlock = repairBlock;
	}

	@Override
	public void executeTurn() {
		b.AssesRepair(repairBlock);
		success = true;
	}
	@Override
	public boolean wasSuccessful() {
		return success;
	}
}
class RepairShip implements Turn{
	private Ship s;
	private ConstructBlock repairBlock;
	private boolean success = false;

	RepairShip(Ship s, ConstructBlock repairBlock) {
		this.s = s;
		this.repairBlock = repairBlock;
	}

	@Override
	public void executeTurn() {
		s.AssesRepair(repairBlock);
		success = true;
	}

	

	@Override
	public boolean wasSuccessful() {
		return success;
	}
}
