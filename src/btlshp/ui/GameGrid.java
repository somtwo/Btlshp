package btlshp.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;

import btlshp.Btlshp;
import btlshp.entities.Base;
import btlshp.entities.Block;
import btlshp.entities.ConstructBlock;
import btlshp.entities.Map;
import btlshp.entities.MapNode;
import btlshp.entities.Ship;
import btlshp.enums.AppState;
import btlshp.enums.GraphicAlliance;

public class GameGrid extends JComponent implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1038483241713085828L;
	private static final int  rowHeight = 16, colWidth = 16;
	
	public enum GridState {
		TurnStart,
		MoveShip,
		RotateShip,
		FireGun,
		FireTorpedo,
		PlaceMine,
		PickupMine,
		RepairConstruct
	};
	
	private HashMap<String, BufferedImage> imageCache;
	private GridState state;
	
	private Dimension size;
	private Color  bgColor, sonarColor, radarColor;
	private Color  gridColor, hoverColor, hoverLineColor;
	
	private boolean showHover;
	private int     hoverx, hovery;
	
	private int     gridWidth, gridHeight;
	
	private Map     map;
	
	
	public GameGrid() {
		gridWidth = gridHeight = 30;
		
		size = new Dimension(gridWidth * colWidth + 2, gridHeight * rowHeight + 2);
		
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		
		addMouseMotionListener(this);
		addMouseListener(this);
		
		bgColor = new Color(3, 28, 9);
		sonarColor = new Color(16, 79, 59);
		radarColor = new Color(16, 79, 32);

		gridColor = new Color(3, 52, 15);
		hoverColor = new Color(6, 178, 48);
		hoverLineColor = new Color(4, 70, 17);
			
		showHover = true;
		hoverx = hovery = 0;
		
		map = null;
		state = GridState.TurnStart;
		
		imageCache = new HashMap<String, BufferedImage>();
	}
	
	
	/**
	 * Used to set the map for the grid to display.
	 * 
	 * @param map           Map to use
	 */
	public void setMap(Map map) {
		this.map = map;
		this.state = GridState.TurnStart;
		repaint();
	}
	
	
	/**
	 * @return The current state of the game.
	 */
	public GridState getGameState() {
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
	
	
	private boolean displayGraphic(MapNode n) {
		if(n.block == null)
			return false;
		
		if(n.block.getAlliance() != GraphicAlliance.Unfriendly || n.hasRadar() ||
				(n.block instanceof ConstructBlock && ((ConstructBlock)n.block).getConstruct() instanceof Base))
			return true;
		
		return false;
	}
	
	
	
	private BufferedImage getImage(String imageName) {
		BufferedImage img = imageCache.get(imageName);
		if(img == null) {
			try {
				img = ImageIO.read(new File("images/mapgraphics/" + imageName + ".png"));
				imageCache.put(imageName, img);
			} catch (Exception e) { img = null; }
		}
		return img;
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
			g2.setColor(hoverLineColor);
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
			map.updateFrame(Btlshp.getGame().getLocalPlayer());
			
			for(y = 0; y < 30; ++y) {
				for(x = 0; x < 30; ++x) {
					MapNode n = map.getMapNode(x, y);
					
					Color c = n.hasSonar() ? sonarColor : n.hasRadar() ? radarColor : bgColor;
					
					g2.setColor(c);
					g2.fillRect(x * colWidth + 2, y * rowHeight + 2, colWidth - 2, rowHeight - 2);
					
					if(displayGraphic(n)) {
						BufferedImage img = getImage(n.block.getGraphicName());
						
						if(img != null)
							g2.drawImage(img, x * colWidth, y * rowHeight, null);
					}
				}
			}
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
		updateHover(ev.getPoint(), false);	
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
	}


	@Override
	public void mouseReleased(MouseEvent ev) {
		int gridx = gridLocX(ev.getX());
		int gridy = gridLocY(ev.getY());
		
		if(map == null || Btlshp.getGame().getAppState() != AppState.LocalTurn)
			return;
		
		Block b = map.getMapNode(gridx, gridy).block;
		if(b == null || !(b instanceof ConstructBlock))
			return;
		
		ConstructBlock cb = (ConstructBlock)b;
		if(Btlshp.getGame().getLocalPlayer() != cb.getPlayer())
			return;
		
		if((cb.getConstruct()) instanceof Ship) {
			JPopupMenu m = new ShipPopupMenu((Ship)cb.getConstruct());
			m.show(this, ev.getX(), ev.getY());
		}
		else if(cb.getConstruct() instanceof Base) {
			JPopupMenu m = new BasePopupMenu((Base)cb.getConstruct());
			m.show(this, ev.getX(), ev.getY());
		}
	}
}
