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
import btlshp.turns.TurnFactory;
import btlshp.utility.NodeIterator;

/**
 * Facilities for creating and displaying context menus for ships.
 * @author Steve
 */
public class ShipPopupMenu extends JPopupMenu {
	private static final long serialVersionUID = 1500135636069969285L;
	
	private final GameGrid grid;
	private final Ship     target;
	
	public ShipPopupMenu(GameGrid gameGrid, Ship clickedShip, btlshp.entities.Map map2) {
		target = clickedShip;
		grid = gameGrid;
		final Ship ship = clickedShip;
		final btlshp.entities.Map maps = map2;
		
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
					grid.fireTorpedo(target);
					Btlshp.getGame().sendTurn(TurnFactory.launchTorpedo(grid.getMap(), target));
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
