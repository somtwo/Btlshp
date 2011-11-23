package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Base;
import btlshp.entities.ConstructBlock;
import btlshp.entities.Location;
import btlshp.enums.Direction;
import btlshp.teststubs.construct.Ship;
import btlshp.teststubs.turn.Map;
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

class Pass implements Turn, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3359745261868508357L;
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
class RequestPostponeGame implements Turn, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2537600636201002718L;
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
class ConfirmPostponeGame implements Turn, Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7601913526703183133L;
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
class LoadGameState implements Turn, Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4870988534077315987L;
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
class RequestSurrender implements Turn, Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4395558489216020334L;
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
class AcceptSurrender implements Turn, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8163598235191879797L;
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
class MoveShip implements Turn, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4599021282070269467L;
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
class PlaceMine implements Turn, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4336837927576289067L;
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
class TakeMine implements Turn, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1991458862311470623L;
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
class LaunchTorpedo implements Turn, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1790493199271040630L;
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

class Shoot implements Turn, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -605750640559980738L;
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
class RepairBase implements Turn, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3486672126675014858L;
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
class RepairShip implements Turn, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 89817140305258661L;
	private Ship s;
	private btlshp.teststubs.construct.ConstructBlock repairBlock;
	private boolean success = false;

	RepairShip(Ship s, btlshp.teststubs.construct.ConstructBlock repairBlock) {
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
