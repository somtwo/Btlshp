package btlshp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import btlshp.entities.Ship;

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
				// Rotate the ship
			}
		});
		add(item);
		
		addSeparator();
		
		if(target.canFireGun()) {
			item = new JMenuItem("Fire gun");
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					// fire the gun
				}
			});
			add(item);
		}
		
		if(target.canFireTorpedo()) {
			item = new JMenuItem("Fire torpedo");
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					// fire a torpedo
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
						// place a mine
					}
				});
			}
			add(item);
		}
		
		if(target.canPlaceMine()) {
			item = new JMenuItem("Pick up a mine");
			
			// TODO: Actual conditions for graying out the menu item
			if(target.getPlayer().numberOfMines() != -1) {
				item.setEnabled(false);
			}
			else {
				item.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						// pickup a mine
					}
				});
			}
			add(item);
		}
	}
}
