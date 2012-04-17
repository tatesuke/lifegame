package com.tatesuke.lifegame.gui;

import javax.swing.JFrame;

import com.tatesuke.lifegame.cell.Cell;
import com.tatesuke.lifegame.cell.Cell.State;
import com.tatesuke.lifegame.cell.CellBuilder;
import com.tatesuke.lifegame.manager.GameManager;
import com.tatesuke.lifegame.manager.GameObserver;

public class AppFrame extends JFrame implements GameObserver {
	
	public static void main(String[] args) {
		new AppFrame().launch();
	}

	private Cell[][] grid;
	private GameManager manager;
	
	private void launch() {
		CellBuilder gridBuilder = new CellBuilder();
		grid = gridBuilder.buildCellGrid(5, 5);
		grid[0][1].setState(State.ALIVE);
		grid[1][1].setState(State.ALIVE);
		grid[2][1].setState(State.ALIVE);
		
		manager = new GameManager(grid, this);
		manager.start();
	}

	@Override
	public void onGenerationChanged() {
		System.out.println(manager.getGenerationNumber());
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++) {
				if (grid[row][column].getState() == State.ALIVE) {
					System.out.print("¡");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

}
