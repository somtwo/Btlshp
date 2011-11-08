package btlshp.entities;

import java.util.ArrayList;

import btlshp.enums.Direction;
import btlshp.enums.Weapon;

public class Map  {
	private static final int MAPWIDTH = 30;
	private static final int MAPHEIGHT = 30;
	ArrayList<Ship> ships;
	MapNode   nodes [][];
	Player    leftPlayer, rightPlayer;
	Base      leftBase, rightBase;
	
	private void createNodes() {
		nodes = new MapNode[MAPWIDTH][MAPHEIGHT];
		
		for(int y = 0; y < MAPWIDTH; ++y) {
			for(int x = 0; x < MAPHEIGHT; ++x) {
				nodes[y][x] = new MapNode();
			}
		}
	}
	
	private void createReefs() {
		// TODO Auto-generated method stub
		
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
	
	
	
	private void placeBase(Base b, int x) {
		ConstructBlock [] blocks = b.getBlocks();
		int y = (MAPHEIGHT - blocks.length) / 2;
		
		for(int i = 0; i < blocks.length; ++i)
			placeBlock(nodes[x][y + i], blocks[i]);
	}
	
	
	private void createStructures() {
		// Create the bases
		leftBase = new Base(leftPlayer);
		placeBase(leftBase, 0);
		
		rightBase = new Base(rightPlayer);
		placeBase(rightBase, MAPWIDTH - 1);
	}
	
	/**
	* Constructor to create a map object which will be the map used by two players to play a game.
	* Any random elements will be generated.
	* @param playerOne     Player 1 (left side)   	
	* @param playerTwo     Player 2 (right side)
	*/
	public Map(Player playerOne, Player playerTwo) {
		leftPlayer = playerOne;
		rightPlayer = playerTwo;
		
		createNodes();
		createStructures();
		createReefs();
	}
	        	
	/**
	* Constructor to create a map object which will be the map used to continue a game between two players.
	* The given StoredMap object will be used to restore the previously saved map state.
	* @param map                               	StoredMap used to restore the map state
	* @param playerOne     Player 1 (left side)   	
	* @param playerTwo     Player 2 (right side)
	*/
	public Map(StoredMap map, Player playerOne, Player playerTwo) {
		// TODO: Support for loading a stored map.
		leftPlayer = playerOne;
		rightPlayer = playerTwo;
		
		createNodes();
		createStructures();
		createReefs();
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
		ConstructBlock [] blocks = s.getBlocks();
		int x, y, min;
		
		if(dir == Direction.North || dir == Direction.South) {
			x = tail.getx();
			
			for(y = min = s.gety1(); y <= s.gety2(); ++y)
				placeBlock(getMapNode(x, y), blocks[y - min]);
		}
		else {
			y = tail.gety();
			
			for(x = min = s.getx1(); x <= s.getx2(); ++x)
				placeBlock(getMapNode(x, y), blocks[x - min]);
		}
		
		s.setLocation(tail);
		s.setDirection(dir);
	}
	
	
	/**
	 * Unplaces a ships blocks from the map nodes.
	 * @param s
	 */
	public void unplaceShip(Ship s) {
		ConstructBlock [] blocks = s.getBlocks();
		Direction dir = s.getDirection();
		Location  loc = s.getLocation();
		int x, y, min;
		
		if(dir == Direction.North || dir == Direction.South) {
			x = loc.getx();
			
			for(y = min = s.gety1(); y <= s.gety2(); ++y)
				unplaceBlock(getMapNode(x, y), blocks[y - min]);
		}
		else {
			y = loc.gety();
			
			for(x = min = s.getx1(); x <= s.getx2(); ++x)
				unplaceBlock(getMapNode(x, y), blocks[x - min]);
		}
	}
	        	
	/**
	* This method cleans up any visibility data from the previous turn and updates the map with the
	* radar/sonar of the current ship locations.
	*/
	public void updateFrame() {
		// TODO: Determine how to update the blocks for the UI
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
		return nodes[y][x];
	}
	        	
	/**
	* Checks to see if a ship movement can be carried out.
	* @param s          Ship moving
	* @param dir        direction of movement.
	* @param blocks  number of blocks to move.
	* @return true if the ship movement can be carried out.
	*/
	public boolean canMove(Ship s, Direction dir, int blocks) {
		Location   loc = s.getLocation();
		Direction sdir = s.getDirection();
		int x1, x2, y1, y2, x, y;	
		
		x1 = s.getx1(); x2 = s.getx2();
		y1 = s.gety1(); y2 = s.gety2();
		
		for(int i = 0; i < blocks; ++i) {
			if(dir == Direction.North) {
				y1--; y2--;
			}
			else if(dir == Direction.South) {
				y1++; y2++;
			}
			else if(dir == Direction.East) {
				x1--; x2--;
			}
			else if(dir == Direction.West) {
				x1++; x2++;
			}
			
			for(y = y1; y <= y2; ++y) {
				for(x = x1; x <= x2; ++x) {
					Block b = getMapNode(x, y).block;
					
					if(b == null || b instanceof MineBlock)
						continue;
					
					if(b instanceof ConstructBlock) {
						ConstructBlock [] sblocks = s.getBlocks();
						boolean           inship = false;
						
						for(int it = 0; it < sblocks.length; ++it)
							if (b == sblocks[it])
								inship = true;
						
						if(inship)
							continue;
					}
					return false;
				}
			}
		}
		return true;
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
		Location   loc = s.getLocation();
		Direction sdir = s.getDirection();
		int x1, x2, y1, y2, x, y;	
		
		x1 = s.getx1(); x2 = s.getx2();
		y1 = s.gety1(); y2 = s.gety2();
		
		unplaceShip(s);
		
		int i;
		for(i = 0; i < blocks; ++i) {
			if(dir == Direction.North) {
				y1--; y2--;
			}
			else if(dir == Direction.South) {
				y1++; y2++;
			}
			else if(dir == Direction.East) {
				x1--; x2--;
			}
			else if(dir == Direction.West) {
				x1++; x2++;
			}
			
			boolean success = true;
			for(y = y1; y <= y2; ++y) {
				for(x = x1; x <= x2; ++x) {
					Block b = getMapNode(x, y).block;
					
					if(b == null)
						continue;
					
					if(b instanceof MineBlock) {
						unplaceBlock(getMapNode(x, y), b);
						// TODO: EXPLODE MINE
					}

					success = false;
				}
			}
			
			if(!success)
				break;
		}
		
		if(dir == Direction.North) {
			s.setLocation(new Location(loc.getx(), loc.gety() - i));
		}
		else if(dir == Direction.South) {
			s.setLocation(new Location(loc.getx(), loc.gety() + i));
		}
		else if(dir == Direction.East) {
			s.setLocation(new Location(loc.getx() - i, loc.gety()));
		}
		else if(dir == Direction.West) {
			s.setLocation(new Location(loc.getx() + i, loc.gety()));
		}
		return i;
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
		MapNode n = getMapNode(loc);
		if(!s.canPlaceMine() || n.block != null || s.getPlayer().numberOfMines() == 0)
			return false;
		
		n.block = new MineBlock();
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
		
		if(!s.canPickUpMine() || b != null || s.getPlayer().numberOfMines() == 0)
			return false;
		
		n.block = null;
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
