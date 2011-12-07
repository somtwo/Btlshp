package btlshp.entities;

import java.io.Serializable;
import java.util.ArrayList;

import btlshp.enums.Direction;
import btlshp.enums.Weapon;
import btlshp.utility.NodeIterator;
import btlshp.utility.NodeIteratorAction;

public class Map implements Serializable {
	private static final long serialVersionUID = -6849202817059640136L;
	private static final int MAPWIDTH = 30;
	private static final int MAPHEIGHT = 30;
	ArrayList<Ship> ships;
	MapNode   nodes [][];
	Player    leftPlayer, rightPlayer;
	
	
	/**
	 * Used for unit testing.
	 * @return
	 */
	public Ship[] getShips() {
		return ships.toArray(new Ship[0]);
	}
	
	
	/**
	 * Used for unit testing.
	 * @return
	 */
	public Base getLeftBase() {
		return leftPlayer.getBase();
	}
	
	
	/**
	 * Used for unit testing.
	 * @return
	 */
	public Base getRightBase() {
		return rightPlayer.getBase();
	}
	
	/**
	 * Used for unit testing.
	 * @return
	 */
	public MapNode[][] getMapNodes() {
		return nodes;
	}
	
	
	private void createNodes() {
		nodes = new MapNode[MAPWIDTH][MAPHEIGHT];
		
		for(int y = 0; y < MAPWIDTH; ++y) {
			for(int x = 0; x < MAPHEIGHT; ++x) {
				nodes[y][x] = new MapNode();
			}
		}
	}
	
	
	private void createReefs() {
		int width = 10, height = 24;
		
		int leftx = (MAPWIDTH - width) / 2;
		int topy = (MAPHEIGHT - height) / 2;
		
		for(int i = 0; i < 24; ++i) {
			int x = leftx + (int)(Math.random() * width);
			int y = topy + (int)(Math.random() * height);
			
			MapNode n = getMapNode(x, y);
			n.block = new ReefBlock();
		}
	}

	
	private void placeBlock(MapNode node, Block block) {
		if(node.block != null)
			throw new IllegalStateException();
		
		node.block = block;
	}
	
	
	private void unplaceBlock(MapNode node, Block block) {
		if(node.block != block)
			throw new IllegalStateException();
		
		node.block = null;
	}
	
	private void placeStructures(Player p, boolean isLeft) {
		Direction  shipDir;
		int        basex, shipx;
		Location   baseLoc;
		
		if(isLeft) {
			shipDir = Direction.East;
			basex = 0;
			shipx = 1;
			baseLoc = new Location(0, (MAPHEIGHT - p.getBase().getBlocks().length) / 2);
		}
		else {
			basex = MAPWIDTH - 1;
			shipx = MAPWIDTH - 2;
			shipDir = Direction.West;
			baseLoc = new Location(0, (MAPHEIGHT + p.getBase().getBlocks().length) / 2);
		}
		
		p.getBase().setDirection(shipDir);
		p.getBase().setLocation(baseLoc);
		
		ConstructBlock [] blocks = p.getBase().getBlocks();
		int ytop = (MAPHEIGHT - blocks.length) / 2;
		
		for(int i = 0; i < blocks.length; ++i)
			placeBlock(getMapNode(basex, ytop + i), blocks[i]);
		
		Ship [] playerShips = p.getShips();
		
		for(int i = 0; i < playerShips.length; ++i) {
			// Select a random ship from the array
			/*int startIndex;
			int shipIndex = startIndex = (int)(Math.random() * playerShips.length);
			
			// We might have already placed the ship at shipIndex, so linearly traverse the list
			// until we find one that we haven't placed yet.
			while(playerShips[shipIndex] == null) {
				shipIndex = (shipIndex + 1) % playerShips.length;
				if(shipIndex == startIndex)
					throw new IllegalStateException("Infinite loop detected.");
			}*/
			int shipIndex = i;
			
			// TODO: This is a hack for now. A better solution should be found...
			int offset = 0;
			if(playerShips[shipIndex].getBlocks().length == 3)
				offset = shipDir == Direction.East ? 1 : shipDir == Direction.West ? -1 : 0;
			
			addShip(playerShips[shipIndex]);
			placeShip(playerShips[shipIndex], shipx + offset, ytop + i, shipDir);
			playerShips[shipIndex] = null;
		}
	}
	
	/**
	* Constructor to create a map object which will be the map used by two players to play a game.
	* Any random elements will be generated.
	* @param playerOne     Player 1 (left side)   	
	* @param playerTwo     Player 2 (right side)
	*/
	public Map(Player playerOne, Player playerTwo) {
		ships = new ArrayList<Ship>();
		leftPlayer = playerOne;
		rightPlayer = playerTwo;
		
		if(playerOne == playerTwo)
			throw new IllegalArgumentException("Left player and Right player can not be the same player.");
		
		createNodes();
		createReefs();
		
		// Place bases and ships
		placeStructures(leftPlayer, true);
		placeStructures(rightPlayer, false);
	}
	        	
	/**
	* Constructor to create a map object which will be the map used to continue a game between two players.
	* The given StoredMap object will be used to restore the previously saved map state.
	* @param map                               	StoredMap used to restore the map state
	* @param playerOne     Player 1 (left side)   	
	* @param playerTwo     Player 2 (right side)
	*/
	public Map(StoredMap map, Player playerOne, Player playerTwo) {
		throw new IllegalStateException("The source is in such a state that this is illegal...");
	}

	
	
	/**
	* Returns a StoredMap object representing the current state of the map.
	* @return
	*/
	public StoredMap makeStoredMap() {
		// TODO: This
		return null;
	}

	        	
	/**
	* Performs the initial placement of a ship, either at the start of a game or on save load.
	* @param s     The ship to be placed
	*/
	public void addShip(Ship s) {
		for(int i = 0; i < ships.size(); ++i)
			if(s == ships.get(i))
				throw new IllegalStateException();
		
		ships.add(s);
	}
	
	
	/**
	 * Removes a ship from the map.
	 * @param s    The ship to be removed.
	 */
	public void removeShip(Ship s) {
		for(int i = 0; i < ships.size(); ++i) {
			if(s == ships.get(i)) {
				ships.remove(i);
				return;
			}
		}
		
		throw new IllegalStateException();
	}
	
	

	/**
	 * Places the ship in the given location and facing the given direction. Links the map nodes to the ship blocks
	 * @param s     Ship to be placed.
	 * @param tail  The location of the tail of the ship
	 * @param dir   The direction the ship should be facing.
	 */
	public void placeShip(Ship s, Location tail, Direction dir) {
		placeShip(s, tail.getx(), tail.gety(), dir);
	}
	
	
	/**
	 * Places the ship in the given location and facing the given direction. Links the map nodes to the ship blocks
	 * @param s     Ship to be placed.
	 * @param x     The x-coordinate of the tail of the ship
	 * @param y     The y-coordinate of the tail of the ship
	 * @param dir   The direction the ship should be facing.
	 */
	public void placeShip(Ship s, int x, int y, Direction dir) {
		s.getCoreIterator().iterate(this, x, y, dir, new NodeIteratorAction() {
			public void visit(MapNode n, Block b) {
				if(n == null)
					throw new IndexOutOfBoundsException();
				
				placeBlock(n, b);
			}
		});

		s.setLocation(new Location(x, y));
		s.setDirection(dir);
	}
	
	
	/**
	 * Unplaces a ships blocks from the map nodes.
	 * @param s
	 */
	public void unplaceShip(Ship s) {
		s.getCoreIterator().iterate(this, s.getLocation(), s.getDirection(), new NodeIteratorAction() {
			public void visit(MapNode n, Block b) {
				if(n == null)
					throw new IndexOutOfBoundsException();
			
				unplaceBlock(n, b);
			}
		});
	}
	        	
	/**
	 * This method cleans up any visibility data from the previous turn and updates the map with the
	 * radar/sonar of the current ship locations.
	 * 
	 * @param forPlayer    The player to update radar/sonar for (ie, the local player)
	 */
	public void updateFrame(Player forPlayer) {
		if(forPlayer != leftPlayer && forPlayer != rightPlayer)
			throw new IllegalArgumentException("forPlayer must be either player one or player two.");
		
		for(int y = 0; y < MAPHEIGHT; ++y) {
			for(int x = 0; x < MAPWIDTH; ++x) {
				getMapNode(x, y).clearFlags();
			}
		}
		
		for(int i = 0; i < ships.size(); ++i)
		{
			Ship s = ships.get(i);
			if(s.getPlayer() != forPlayer)
				continue;
			
			if(s.hasSonar()) {
				s.getRadarIterator().iterate(this, s.getLocation(), s.getDirection(), new NodeIteratorAction() {
					public void visit(MapNode n, Block b) {
						if(n != null)
							n.hasSonar(true);
					}
				});
			}
			else {
				s.getRadarIterator().iterate(this, s.getLocation(), s.getDirection(), new NodeIteratorAction() {
					public void visit(MapNode n, Block b) {
						if(n != null)
							n.hasRadar(true);
					}
				});
			}
		}
	}

	/**
	* Returns a MapBlock object representing the state of the map at a given location.
	* @param loc   Location to get the map block from
	* @returns The map block at the given location
	*/
	public MapNode getMapNode(Location loc) {
		return getMapNode(loc.getx(), loc.gety());
	}
	
	/**
	* Returns a MapBlock object representing the state of the map at a given location.
	* @param loc   Location to get the map block from
	* @returns The map block at the given location
	*/
	public MapNode getMapNode(int x, int y) {
		return insideMap(x, y) ? nodes[y][x] : null;
	}
	
	
	private boolean insideMap(int x, int y) {
		if(x < 0 || x >= MAPWIDTH || y < 0 || y >= MAPHEIGHT)
			return false;
		return true;
	}
	        	
	/**
	* Checks to see if a ship movement can be carried out.
	* @param s          Ship moving
	* @param dir        direction of movement.
	* @param blocks  number of blocks to move.
	* @return true if the ship movement can be carried out.
	*/
	public boolean canMove(Ship s, Direction dir, int blocks) {
		Location      loc = s.getLocation();
		NodeIterator  it = s.getCoreIterator();
		int           x, y, deltax, deltay;
		
		x = loc.getx();
		y = loc.gety();
		
		deltax = (dir == Direction.West) ? -1 : (dir == Direction.East) ? 1 : 0;
		deltay = (dir == Direction.North) ? -1 : (dir == Direction.South) ? 1 : 0;

		it.rotate(s.getDirection());
		
		int moveCount;
		for(moveCount = 0; moveCount < blocks; ++moveCount)
		{
			it.setOrigin(x, y);
			x += deltax;
			y += deltay;
			
			for(int i = 0; i < it.size(); ++i) {
				if(!insideMap(it.getx(i), it.gety(i)))
					break;
				
				Block b = getMapNode(it.getx(i), it.gety(i)).block;
				
				if(b == null)
					continue;
				
				if(b instanceof ConstructBlock) {
					ConstructBlock cb = (ConstructBlock)b;
					
					if(cb.myConstruct != s)
						break;
				}
			}
		}
		return moveCount > 0;
	}
	        	
	/**
	* Moves a ship either entirely or partially the number of blocks in the given direction.
	* @param s             	Ship moving
	* @param dir          	Direction to move the ship
	* @param blocks    	Number of blocks to be moved
	* @returns Number of blocks actually used.
	* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
	*/
	public int move(Ship s, Direction dir, int blocks) {
		Location      loc = s.getLocation();
		NodeIterator  it = s.getCoreIterator();
		NodeIterator  adjIt = s.getSurroundingIterator();
		int           x, y, deltax, deltay, moveCount;
		boolean		  canContinue = true;
		
		x = loc.getx();
		y = loc.gety();
		
		deltax = (dir == Direction.West) ? -1 : (dir == Direction.East) ? 1 : 0;
		deltay = (dir == Direction.North) ? -1 : (dir == Direction.South) ? 1 : 0;
		
		it.rotate(s.getDirection());
		adjIt.rotate(s.getDirection());

		for(moveCount = 0; moveCount < blocks; ++moveCount)
		{
			x += deltax;
			y += deltay;
			it.setOrigin(x, y);
			adjIt.setOrigin(x, y);
			
			// Check adjacent squares for mines
			for(int i = 0; i < adjIt.size(); ++i) {
				if(!insideMap(adjIt.getx(i), adjIt.gety(i)))
					continue;
				
				MapNode n = getMapNode(adjIt.getx(i), adjIt.gety(i));
				Block b = n.block;
				
				if(!(b instanceof MineBlock) || s.canPlaceMine())
					continue;
				
				adjIt.getBlock(i).takeHit(Weapon.Mine);
				unplaceBlock(n, b);
				n.hasExplosion(true);
				canContinue = false;
			}
			
			// Now for the body.
			for(int i = 0; i < it.size(); ++i) {
				if(!insideMap(it.getx(i), it.gety(i))) {
					canContinue = false; break;
				}
				
				MapNode n = getMapNode(it.getx(i), it.gety(i));
				Block b = n.block;
				
				if(b instanceof MineBlock) {
					canContinue = false;
					adjIt.getBlock(i).takeHit(Weapon.Mine);
					unplaceBlock(n, b);
					n.hasExplosion(true);
				}
				else if(b instanceof ConstructBlock) {
					ConstructBlock cb = (ConstructBlock)b;
					
					if(cb.myConstruct != s)
						canContinue = false;
				}
				else if(b != null) {
					canContinue = false; break;
				}
			}
			
			if(canContinue) {
				unplaceShip(s);
				placeShip(s, x, y, s.getDirection());
			}
			else
				break;
		}
		
		return moveCount;
	}
	        	
	/**
	* Checks whether a ship can rotate from it's current direction to a new given direction. This method only
	* considers physical barriers that can be see by radar.
	*
	* @param s 	          Ship to turn
	* @param newDir    New direction
	* @returns True if the ship can rotate in the new direction.
	* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
	*/
	public boolean canShipRotate(Ship s, Direction newDir) {
		return false;
		
	}
	        	
	/**
	* Attempts to turn a ship from it's current direction to a new direction.
	* @param s       Ship to turn
	* @param newDir  Direction ship should face by the end of the turn.
	* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
	*/
	public void rotateShip(Ship s, Direction newDir) {
		
	}

	/**
	* Attempts to place a mine via the given ship in the given location
	* @param s       Ship that places the mine
	* @param loc     Map location to place the mine
	* @returns true if the mine was placed, or false if it could not be placed.
	* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
	*/
	public boolean placeMine(Ship s, Location loc) {
		MapNode  n = getMapNode(loc);
		
		if(!s.canPlaceMine() || n.block != null || s.getPlayer().numberOfMines() == 0)
			return false;
		
		if(loc.getx() < s.getx1() - 1 || loc.getx() > s.getx2() + 1 ||
		   loc.gety() < s.gety1() - 1 || loc.gety() > s.gety2() + 1)
			return false;
		
		placeBlock(n, new MineBlock());
		s.getPlayer().removeMine();
		return true;
	}

	/**
	* Attempts to pick up a mine via the given ship.
	* @param s       Ship that picks up the mine
	* @param loc     Location of the mine.
	* @return true if the mine was picked up, or false if it was not.
	* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
	*/
	public boolean pickupMine(Ship s, Location loc) {
		MapNode n = getMapNode(loc);
		MineBlock b = n.block != null && n.block instanceof MineBlock ? (MineBlock)n.block : null;
		
		if(!s.canPickUpMine() || b == null)
			return false;
		
		if(loc.getx() < s.getx1() - 1 || loc.getx() > s.getx2() + 1 ||
		   loc.gety() < s.gety1() - 1 || loc.gety() > s.gety2() + 1)
			return false;
		
		unplaceBlock(n, b);
		s.getPlayer().addMine();
		return true;
	}
	        	
	/**
	* Fires the torpedo of the given ship. The torpedo will start from the front of the ship and will
	* travel in the direction of the ship.
	* @param s       Ship to fire torpedo from.
	* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
	*/
	public void fireTorpedo(Ship s) {
		
	}
	        	
	/**
	* Fires the guns of a ship at a specific location
	* @param s             	Ship to fire guns from
	* @param loc  Map location of the target of the fire
	* @throws IllegalStateException If a move has already been made since the last generateTurn method call.
	*/
	public void fireGuns(Ship s, Location loc) {
		
	}
}
