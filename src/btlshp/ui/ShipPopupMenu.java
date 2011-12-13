package btlshp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import btlshp.Btlshp;
import btlshp.entities.Block;
import btlshp.entities.ConstructBlock;
import btlshp.entities.Location;
import btlshp.entities.MapNode;
import btlshp.entities.MineBlock;
import btlshp.entities.Ship;
import btlshp.enums.Direction;
import btlshp.enums.Weapon;
import btlshp.utility.NodeIterator;

/**
 * Facilities for creating and displaying context menus for ships.
 * @author Steve
 */
public class ShipPopupMenu extends JPopupMenu {
	private static final long serialVersionUID = 1500135636069969285L;
	
	private final GameGrid grid;
	private final Ship     target;
	
	public ShipPopupMenu(GameGrid gameGrid, Ship clickedShip) {
		target = clickedShip;
		grid = gameGrid;
		
		// Add menu items based on the capabilities of the ship
		JMenuItem item = new JMenuItem("Move ship");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				grid.startShipMove(target);
			}
		});
		add(item);
		
		item = new JMenuItem("Rotate ship");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				grid.startShipRotate(target);
			}
		});
		add(item);
		
		addSeparator();
		
		if(target.canFireGun()) {
			item = new JMenuItem("Fire gun");
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					grid.startFireGun(target);
				}
			});
			add(item);
		}
		
		if(target.canFireTorpedo()) {
			item = new JMenuItem("Fire torpedo");
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					// TODO: actually do something here...
					Location      loc = target.getLocation();
					int           x, y, deltax, deltay, fireCount;
					boolean		  canContinue = true;
					
					x = loc.getx();
					y = loc.gety();
					
					deltax = (target.getDirection()== Direction.West) ? -1 : (target.getDirection() == Direction.East) ? 1 : 0;
					deltay = (target.getDirection() == Direction.North) ? -1 : (target.getDirection() == Direction.South) ? 1 : 0;
					
					x += (target.getDirection()== Direction.West) ? -(target.getBlocks().length) : (target.getDirection() == Direction.East) ? target.getBlocks().length : 0;
					y += (target.getDirection() == Direction.North) ? -(target.getBlocks().length) : (target.getDirection() == Direction.South) ? target.getBlocks().length : 0;

					for(fireCount = 0; fireCount < 10 && canContinue; fireCount++)
					{				
						// Check each zone one at a time. (similar to move forward method in map)							
							if(!grid.getMap().insideMap(x, y)) {
								canContinue = false; break;
							}
							
							MapNode n = grid.getMap().getMapNode(x, y);
							Block b = n.block;
							if(b != null){
								b.takeHit(Weapon.Torpedo, x , y);
								canContinue = false;
							}
						
						if(!canContinue)
							break;
						x += deltax;
						y += deltay;
					}
					Btlshp.getGame().outputMessage("Fire a torpedo!");
				}
			});
			add(item);
		}
		
		if(target.canPlaceMine()) {
			item = new JMenuItem("Place a mine");
			
			// TODO: More conditions for graying out the menu item
			if(target.getPlayer().numberOfMines() <= 0) {
				item.setEnabled(false);
			}
			else {
				item.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						grid.startPlaceMine(target);
					}
				});
			}
			add(item);
		}
		
		if(target.canPlaceMine()) {
			item = new JMenuItem("Pick up a mine");
			
			// TODO: Actual conditions for graying out the menu item
			if(target.getPlayer().numberOfMines() == -1) {
				item.setEnabled(false);
			}
			else {
				item.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						grid.startPickupMine(target);
					}
				});
			}
			add(item);
		}
	}
}
