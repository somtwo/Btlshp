package btlshp.turns;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import btlshp.entities.Base;
import btlshp.entities.ConstructBlock;
import btlshp.entities.Location;
import btlshp.enums.Direction;
import btlshp.entities.Ship;
import btlshp.entities.Map;
import java.io.*;
public abstract class Turn {
	public long writeOut(String filePath){
		ObjectOutputStream objOut = null;
		FileOutputStream fileOut = null;
		File f = null;
		try {
			fileOut = new FileOutputStream(filePath +"/game.ser");
			objOut = new ObjectOutputStream(fileOut);
			
			objOut.writeObject(this);
			objOut.close();
			f = new File(filePath+"/game.ser");
			
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		
		return f.lastModified();
	}
	public abstract void setMap(Map m);
	/**
	 * @returns true if the move object represents a successful move, false otherwise.
	 */
	abstract boolean wasSuccessful();

	/**
	 * Executes a given move object representing a move from the other player.
	 * @throws IllegalStateException If the turn was not successful.
	 */
	public abstract void executeTurn();
}
class Pass extends Turn implements Serializable{
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
		return "pass";
	}
	@Override
	public void setMap(Map m) {}
}
class JoinGame extends Turn implements Serializable{
	private Map m;
	private static final long serialVersionUID = 6638148998058324549L;
	public JoinGame(Map m){
		this.m = m;
	}
	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setMap(Map m) {
		// TODO Auto-generated method stub
		
	}
	
}
class RequestPostponeGame extends Turn implements Serializable{
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
		return "requestPostponeGame";
	}
	@Override
	public void setMap(Map m) {
		// TODO Auto-generated method stub
		
	}
}
class ConfirmPostponeGame extends Turn implements Serializable{

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
		return "confirmPostponeGame";
	}


	@Override
	public void setMap(Map m) {
		// TODO Auto-generated method stub
		
	}
}
class LoadGameState extends Turn implements Serializable{

	private static final long serialVersionUID = 4870988534077315987L;
	private String filePath;
	/**
	 * 
	 * @param f location of saved game file
	 */
	public LoadGameState(String f){
		filePath = f;
	}
	@Override
	public void executeTurn() {
			FileInputStream fileIn = null;
			ObjectInputStream objIn = null;
			try{
				fileIn = new FileInputStream(filePath);
				objIn = new ObjectInputStream(fileIn);
				fileIn.close();
			}catch(IOException e){
				System.err.println(e.getMessage());
			}
			
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Implementation IO dependent
		return true;
	}
	@Override
	public String toString(){
		return "loadGameState";
	}
	@Override
	public void setMap(Map m) {
		// TODO Auto-generated method stub
		
	}
}
class SaveGameState extends Turn implements Serializable{
	
	private static final long serialVersionUID = 2354631655310067958L;
	private Map saveGame;
	
	public SaveGameState(Map map)
	{
		saveGame = map;
	}
	@Override
	public void executeTurn()  {
		// TODO Auto-generated method stub
		ObjectOutputStream objOut = null;
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("..Dropbox/Btlshp/game.dat");
			objOut = new ObjectOutputStream(fileOut);
			
			objOut.writeObject(saveGame);
			objOut.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String toString(){
		return "saveGameState";
	}
	@Override
	public void setMap(Map m) {
		// TODO Auto-generated method stub
		
	}
	
}
class RequestSurrender extends Turn implements Serializable{


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
		return "requestSurrender";
	}


	@Override
	public void setMap(Map m) {
		// TODO Auto-generated method stub
		
	}
}
class AcceptSurrender extends Turn implements Serializable{

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
		return "acceptSurrender";
	}
	@Override
	public void setMap(Map m) {
		// TODO Auto-generated method stub
		
	}
}
class MoveShip extends Turn implements Serializable{
	
	private static final long serialVersionUID = 4599021282070269467L;
	private Ship s;
	private Direction dir;
	private int distance;
	private Map m;
	private boolean success = false;

	public MoveShip(Map m, Ship s, Direction dir, int distance) {
		this.s = s;
		this.dir = dir;
		this.distance = distance;
	}
	
	@Override
	public void executeTurn() {
		try{
			System.err.println("Moving ship "+s.getId()+" "+ dir.name() +" "+ distance+ " blocks");
			m.move(s, dir,distance);
			success = true;
		}catch(IllegalStateException e){
			e.printStackTrace();
			success = false;
		}
		
	}

	@Override
	public boolean wasSuccessful() {
		return true;
	}
	@Override
	public String toString(){
		return "moveShip";
	}

	@Override
	public void setMap(Map m) {
		this.m = m;
		
	}
}
class PlaceMine extends Turn implements Serializable{
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
		return "placeMine";
	}

	@Override
	public void setMap(Map m) {
		this.m = m;
		
	}
}
class RotateShip extends Turn implements Serializable{
	private Map m;
	private Ship s;
	private Direction dir;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2197899809799342874L;
	RotateShip(Map m, Ship s, Direction dir){
		this.m = m;
		this.s = s;
		this.dir = dir;
	}
	@Override
	public void setMap(Map m) {
		this.m = m;
		
	}

	@Override
	boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void executeTurn() {
		m.rotateShip(s, dir);
		
	}
	
}
class TakeMine extends Turn implements Serializable{
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
		return "takeMine";
	}

	@Override
	public void setMap(Map m) {
		this.m = m;		
	}
}
class LaunchTorpedo extends Turn implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1790493199271040630L;
	private Map m;
	private Ship s;
	private boolean success = false;


	LaunchTorpedo(Map m2, Ship s2) {
		this.m = m2;
		this.s = s2;
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
		return "launchTorpedo";
	}

	@Override
	public void setMap(Map m) {
		// TODO Auto-generated method stub
		
	}
}

class Shoot extends Turn implements Serializable{

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
		return "shoot";
	}

	@Override
	public void setMap(Map m) {
		this.m = m;		
	}
}
class RepairBase extends Turn implements Serializable{

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
		return "repairBase";
	}

	@Override
	public void setMap(Map m) {
		// TODO Auto-generated method stub
		
	}
}
class RepairShip extends Turn implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 89817140305258661L;
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
	
	@Override
	public String toString(){
		return "repairShip";
	}

	@Override
	public void setMap(Map m) {
		// TODO Auto-generated method stub
		
	}
}
