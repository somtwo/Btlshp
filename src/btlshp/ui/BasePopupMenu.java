package btlshp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import btlshp.Btlshp;
import btlshp.entities.Base;

public class BasePopupMenu extends JPopupMenu {
	private static final long serialVersionUID = -1149025916444288803L;

	private GameGrid grid;
	private Base     target;
	
	public BasePopupMenu(GameGrid gameGrid, Base targetBase) {
		grid = gameGrid;
		target = targetBase;
		
		// Add menu items based on the capabilities of the base
		JMenuItem item = new JMenuItem("Repair Ship");
		if(target.canRepairOther() || target.canRepairSelf()) {
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					grid.startRepair(target);
				}
			});
		}
		else {
			item.setEnabled(false);
		}
		add(item);
		
		item = new JMenuItem("Pass turn");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// TODO: PAss a turn
				
			}
		});
		add(item);
	}
}
