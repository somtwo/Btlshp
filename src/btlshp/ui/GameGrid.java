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
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		int        x, y;
		
		// Draw basic grid lines.
		g2.setColor(gridColor);
		for(y = 0; y <= 30; ++y) {
			g2.drawLine(0, y * rowHeight,     getWidth() - 1, y * rowHeight);
			g2.drawLine(0, y * rowHeight + 1, getWidth() - 1, y * rowHeight + 1);
		}

		for(x = 0; x <= 30; ++x) {
			g2.drawLine(x * colWidth,     0, x * colWidth,     getHeight() - 1);				
			g2.drawLine(x * colWidth + 1, 0, x * colWidth + 1, getHeight() - 1);				
		}

		if(showHover) {
			int hx = hoverx * colWidth;
			int hy = hovery * rowHeight;
			
			g2.setColor(hoverColor);
			g2.drawLine(hx, hy,     hx + 17, hy);
			g2.drawLine(hx, hy + 1, hx + 17, hy + 1);
			
			g2.drawLine(hx, hy + 16, hx + 17, hy + 16);
			g2.drawLine(hx, hy + 17, hx + 17, hy + 17);
			
			g2.drawLine(hx,     hy + 2, hx,     hy + 15);
			g2.drawLine(hx + 1, hy + 2, hx + 1, hy + 15);
			
			g2.drawLine(hx + 16, hy + 2, hx + 16, hy + 15);
			g2.drawLine(hx + 17, hy + 2, hx + 17, hy + 15);
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
