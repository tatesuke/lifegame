package com.tatesuke.lifegame.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.tatesuke.lifegame.cell.Cell;
import com.tatesuke.lifegame.cell.Cell.State;
import com.tatesuke.lifegame.manager.GameManager;

public class CellPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	private Cell[][] cell;
	private final GameManager manager;
	
	public CellPanel(GameManager manager) {
		this.manager = manager;
		
		setPreferredSize(new Dimension(400, 400));
		addMouseListener(this);
	}

	public void setCell(Cell[][] cell) {
		this.cell = cell;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		double cellSize = Math.min(getHeight() / cell.length, getWidth() / cell[0].length);
		double offsetX = (getWidth() - (cellSize * cell[0].length)) / 2.0;
		double offsetY = (getHeight() - (cellSize * cell.length)) / 2.0;
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int row = 0; row < cell.length; row++) {
			for (int column = 0; column < cell[row].length; column++) {
				int x = (int)((column * cellSize) + offsetX);
				int y = (int)((row * cellSize) + offsetY);
				
				if (cell[row][column].getState() == State.ALIVE) {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, (int)cellSize,  (int)cellSize);
				}
				g.setColor(Color.BLACK);
				g.drawRect(x, y, (int)cellSize,  (int)cellSize);
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		double cellSize = Math.min(getHeight() / cell.length, getWidth() / cell[0].length);
		double offsetX = (getWidth() - (cellSize * cell[0].length)) / 2.0;
		double offsetY = (getHeight() - (cellSize * cell.length)) / 2.0;
		
		double x = e.getX() - offsetX;
		double y = e.getY() - offsetY;
		
		if ((x < 0) || (y < 0) || ((cellSize * cell[0].length) < x) || ((cellSize * cell.length) < y)) {
			return;
		}
		
		int row = (int)((y + 1) / cellSize);
		int column = (int)((x + 1) / cellSize);
		if (manager != null) {
			manager.toggleCell(row, column);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		/* ‰½‚à‚µ‚È‚¢ */
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		/* ‰½‚à‚µ‚È‚¢ */
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		/* ‰½‚à‚µ‚È‚¢ */
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		/* ‰½‚à‚µ‚È‚¢ */
	}

}
