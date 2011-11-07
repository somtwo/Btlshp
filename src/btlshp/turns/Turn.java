package btlshp.turns;

import ConstructTestStubs.Ship;
import TurnTestStubs.Map;
import btlshp.entities.Base;
import btlshp.entities.ConstructBlock;
import btlshp.entities.Location;
import btlshp.enums.Direction;
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
	@Override
	public String toString(){
		return "Pass";
	}
}
class RequestPostponeGame implements Turn{
	@Override
	/**
	 * Opponent would like to PostPone Game
	 */
	public void executeTurn() {
		// TODO Implementation UI dependent
		//Generate a dialog box with player and allow player to accept or reject
	}
	@Override
	public boolean wasSuccessful() {
		// TODO Implementation UI dependent
		return true;
	}
	@Override
	public String toString(){
		return "RequestPostponeGame";
	}
}
class ConfirmPostponeGame implements Turn{


	@Override
	/**
	 * Opponent accepted postponing game
	 */
	public void executeTurn() {
		// TODO Implementation UI dependent
		
	}


	@Override
	public boolean wasSuccessful() {
		// TODO Implementation UI dependent
		return true;
	}
	@Override
	public String toString(){
		return "ConfirmPostponeGame";
	}
}
class LoadGameState implements Turn{


	@Override
	public void executeTurn() {
		// TODO Implementation IO dependent
		
	}


	@Override
	public boolean wasSuccessful() {
		// TODO Implementation IO dependent
		return true;
	}
	@Override
	public String toString(){
		return "LoadGameState";
	}
}
class SaveGameState implements Turn{

	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String toString(){
		return "SaveGameState";
	}
	
}
class RequestSurrender implements Turn{


	@Override
	/**
	 * Opponent requests to quit(surrender) game
	 */
	public void executeTurn() {
		// TODO Implementation UI dependent
		//Generate dialog for the player to accept or reject surrender
		
	}


	@Override
	public boolean wasSuccessful() {
		// TODO Implementation UI dependent
		return true;
	}
	@Override
	public String toString(){
		return "RequestSurrender";
	}
}
class AcceptSurrender implements Turn{
	@Override
	/**
	 * Opponent has accepted request to surrender
	 */
	public void executeTurn() {
		// TODO Implementation UI dependent
		//Generate dialog informing player opponent has accepted surrender
		
	}
	@Override
	public boolean wasSuccessful() {
		// TODO Implementation UI dependent
		return true;
	}
	@Override
	public String toString(){
		return "AcceptSurrender";
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
	@Override
	public String toString(){
		return "MoveShip";
	}
}
class PlaceMine implements Turn{
	private Map m;
	private Location loc;
	private Ship s;
	private boolean success = false;
	public PlaceMine(Map m, Ship s, Location loc) {
		this.m = m;
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
	@Override
	public String toString(){
		return "PlaceMine";
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
		this.m = m;
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
	@Override
	public String toString(){
		return "TakeMine";
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
	@Override
	public String toString(){
		return "LaunchTorpedo";
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
	@Override
	public String toString(){
		return "Shoot";
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
	@Override
	public String toString(){
		return "RepairBase";
	}
}
class RepairShip implements Turn{
	private Ship s;
	private ConstructTestStubs.ConstructBlock repairBlock;
	private boolean success = false;

	RepairShip(Ship s, ConstructTestStubs.ConstructBlock repairBlock) {
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
	
	@Override
	public String toString(){
		return "RepairShip";
	}
}
