package btlshp.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import btlshp.entities.Base;
import btlshp.entities.Block;
import btlshp.entities.ConstructBlock;
import btlshp.entities.Map;
import btlshp.entities.MapNode;
import btlshp.entities.Player;
import btlshp.entities.Ship;

public class GameGrid extends JComponent implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1038483241713085828L;
	private static final int  rowHeight = 16, colWidth = 16;
	
	public enum GameState {
		WaitingForTurn,
		TurnStart,
		MoveShip,
		RotateShip,
		FireGun,
		FireTorpedo,
		PlaceMine,
		PickupMine,
		RepairConstruct
	};
	
	private GameState state;
	
	private Dimension size;
	private Color  bgColor, gridColor, hoverColor;
	
	private boolean showHover;
	private int     hoverx, hovery;
	private int     pressx, pressy;
	
	private int     gridWidth, gridHeight;
	
	private Map     map;
	private Player  thisPlayer;
	
	
	public GameGrid() {
		gridWidth = gridHeight = 30;
		
		size = new Dimension(gridWidth * colWidth + 2, gridHeight * rowHeight + 2);
		
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		
		addMouseMotionListener(this);
		addMouseListener(this);
		
		bgColor = new Color(3, 28, 9);
		gridColor = new Color(3, 52, 15);
		hoverColor = new Color(6, 178, 48);
		
		showHover = true;
		hoverx = hovery = 0;
		
		map = null;
		state = GameState.TurnStart;
	}
	
	
	/**
	 * Used to set the map for the grid to display.
	 * 
	 * @param map           Map to use
	 * @param thisPlayer    The player using this GameGrid.
	 * @param state         The current state of the game, typically this should either be TurnStart or WaitingForTurn
	 */
	public void setMap(Map map, Player thisPlayer, GameState state) {
		this.map = map;
		this.thisPlayer = thisPlayer;
		this.state = state;
	}
	
	
	/**
	 * @return The current state of the game.
	 */
	public GameState getGameState() {
		return state;
	}
	
	
	private void drawDoubleHorizontalLine(Graphics2D g, int y, int x1, int x2) {
		g.drawLine(x1, y,   x2, y);
		g.drawLine(x1, y+1, x2, y+1);
	}
	
	private void drawDoubleVerticalLine(Graphics2D g, int x, int y1, int y2) {
		g.drawLine(x,     y1, x,     y2);
		g.drawLine(x + 1, y1, x + 1, y2);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		int        x, y;
		
		// Draw basic grid lines.
		g2.setColor(gridColor);
		for(y = 0; y <= 30; ++y) {
			drawDoubleHorizontalLine(g2, y * rowHeight, 0, getWidth() - 1);
		}

		for(x = 0; x <= 30; ++x) {
			drawDoubleVerticalLine(g2, x * colWidth, 0, getWidth() - 1);		
		}

		if(showHover) {
			int hx = hoverx * colWidth;
			int hy = hovery * rowHeight;
			
			// Highlight row/column
			g2.setColor(new Color(4, 80, 21));
			drawDoubleVerticalLine(g2, hx,      0, getHeight() - 1);
			drawDoubleVerticalLine(g2, hx + 16, 0, getHeight() - 1);
			
			drawDoubleHorizontalLine(g2, hy,      0, getWidth() - 1);
			drawDoubleHorizontalLine(g2, hy + 16, 0, getWidth() - 1);
			
			g2.setColor(hoverColor);
			drawDoubleHorizontalLine(g2, hy,      hx, hx + 17);
			drawDoubleHorizontalLine(g2, hy + 16, hx, hx + 17);
			
			drawDoubleVerticalLine(g2, hx,      hy + 2, hy + 15);
			drawDoubleVerticalLine(g2, hx + 16, hy + 2, hy + 15);
		}
		
		if(map != null) {
			for(y = 0; y < 30; ++y) {
				for(x = 0; x < 30; ++x) {
					// TODO: Select appropriate color
					g2.setColor(bgColor);
					g2.fillRect(x * colWidth + 2, y * rowHeight + 2, colWidth - 2, rowHeight - 2);
				}
			}
			// TODO: Draw graphics for the ships
		}
		else {
			g2.setColor(bgColor);

			for(y = 0; y < 30; ++y) {
				for(x = 0; x < 30; ++x)
					g2.fillRect(x * colWidth + 2, y * rowHeight + 2, colWidth - 2, rowHeight - 2);
			}
		}
	}
	
	
	private int gridLocX(int x) {
		int res = (x - 1) / colWidth;
		return res < 0 ? 0 : res >= gridWidth ? gridWidth - 1 : res;
	}
	
	
	private int gridLocY(int y) {
		int res = (y - 1) / rowHeight;
		return res < 0 ? 0 : res >= gridHeight ? gridHeight - 1 : res;
	}


	/**
	 * Updates the highlighted square in the grid.
	 * @param mouseLoc    Location the mouse has moved to.
	 */
	private void updateHover(Point mouseLoc, boolean forceRepaint) {
		int x = gridLocX(mouseLoc.x);
		int y = gridLocY(mouseLoc.y);
		
		if(x != hoverx || y != hovery || forceRepaint)
			repaint();
		
		hoverx = x;
		hovery = y;
	}
	
	
	@Override
	public void mouseDragged(MouseEvent ev) {
	}



	@Override
	public void mouseMoved(MouseEvent ev) {
		updateHover(ev.getPoint(), false);	
	}


	@Override
	public void mouseClicked(MouseEvent ev) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent ev) {
		showHover = true;
		updateHover(ev.getPoint(), true);
	}


	@Override
	public void mouseExited(MouseEvent ev) {
		showHover = false;
		repaint();
	}


	@Override
	public void mousePressed(MouseEvent ev) {
		pressx = ev.getX();
		pressy = ev.getY();
	}


	@Override
	public void mouseReleased(MouseEvent ev) {
		int gridx = gridLocX(pressx);
		int gridy = gridLocY(pressy);
		
		if(map == null)
			return;
		
		Block b = map.getMapNode(gridx, gridy).block;
		if(b == null || !(b instanceof ConstructBlock))
			return;
		
		ConstructBlock cb = (ConstructBlock)b;
		if(thisPlayer != cb.getPlayer())
			return;
		
		if((cb.getConstruct()) instanceof Ship) {
			JPopupMenu m = new ShipPopupMenu((Ship)cb.getConstruct());
			m.show(this, pressx, pressy);
		}
		else if(cb.getConstruct() instanceof Base) {
			JPopupMenu m = new BasePopupMenu((Base)cb.getConstruct());
			m.show(this, pressx, pressy);
		}
	}
}
