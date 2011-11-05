package btlshp.turns;

import btlshp.entities.Base;
import btlshp.entities.Location;
import btlshp.entities.Ship;
import btlshp.enums.Direction;

public interface Turn {
	/**
	 * @returns true if the move object represents a successful move, false otherwise.
	 */
	boolean wasSuccessful();
	  
	/**
	 * Sends the object representing a move this player made, to the other player.
	 * @throws IllegalStateException If the move is sent from a machine that did not create it.
	 */
	void sendTurn();

	/**
	 * Executes a given move object representing a move from the other player.
	 * @throws IllegalStateException If the turn was not successful.
	 */
	void executeTurn();
}
class Pass implements Turn{

	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
}
class RequestPostponeGame implements Turn{

	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendTurn() {
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
	public void sendTurn() {
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
	public void sendTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
}
class MoveShip implements Turn{


	public MoveShip(Ship s, Direction dir, int distance) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
}
class PlaceMine implements Turn{


	public PlaceMine(Location loc) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
}
class TakeMine implements Turn{


	public TakeMine(Location loc) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
}
class LaunchTorpedo implements Turn{


	LaunchTorpedo(Ship s) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendTurn() {
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
	public void sendTurn() {
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
	public void sendTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
}
class Shoot implements Turn{


	public Shoot(Ship s, Location loc) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
}
class RepairBase implements Turn{


	RepairBase(Base b) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
}
class RepairShip implements Turn{


	RepairShip(Ship s) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
}
