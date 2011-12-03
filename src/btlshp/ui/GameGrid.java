package btlshp.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import btlshp.entities.Map;

public class GameGrid extends JComponent implements MouseMotionListener {
	/**
	 * It's serializable, y0! 
	 */
	private static final long serialVersionUID = 1038483241713085828L;
	private static final int  rowHeight = 16, colWidth = 16;
	
	private Dimension size;
	private Color  bgColor, gridColor, hoverColor;
	
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
		
		bgColor = new Color(3, 28, 9);
		gridColor = new Color(3, 52, 15);
		hoverColor = new Color(6, 178, 48);
		
		showHover = true;
		hoverx = hovery = 0;
		
		map = null;
	}
	
	
	private void drawDoubleHorizontalLine(Graphics2D g, int y, int x1, int x2) {
		g.drawLine(x1, y,   x2, y);
		g.drawLine(x1, y+1, x2, y+1);
	}
	
	private void drawDoubleVerticalLine(Graphics2D g, int x, int y1, int y2) {
		g.drawLine(x,     y1, x,     y2);
		g.drawLine(x + 1, y1, x + 1, y2);
	}
	
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



	private void updateHover(Point mouseLoc) {
		int x = (mouseLoc.x - 1) / 16;
		int y = (mouseLoc.y - 1) / 16;
		
		x = x < 0 ? 0 : x > 29 ? 29 : x;
		y = y < 0 ? 0 : y > 29 ? 29 : y;
		
		if(x != hoverx || y != hovery)
			repaint();
		
		hoverx = x;
		hovery = y;
	}
	
	
	@Override
	public void mouseDragged(MouseEvent ev) {
		updateHover(ev.getPoint());	
	}



	@Override
	public void mouseMoved(MouseEvent ev) {
		updateHover(ev.getPoint());	
	}
}
