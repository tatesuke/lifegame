package com.tatesuke.lifegame.manager;

import java.util.Timer;
import java.util.TimerTask;

import com.tatesuke.lifegame.cell.Cell;

public class GameManager {

	private static final long INTERVAL = 1000L;
	
	private Cell[][] grid;
	private Timer gameTimer;
	private GameObserver observer;
	private int generationNumber;
	
	public GameManager(Cell[][] grid, GameObserver observer) {
		if (grid == null) {
			throw new IllegalArgumentException();
		}
		this.grid = grid;
		this.observer = observer;
	}
	
	public void start() {
		gameTimer = new Timer();
		gameTimer.schedule(getTimerTask(), 0L, INTERVAL);
	}

	public void stop() {
		gameTimer.cancel();
	}
	
	public int getGenerationNumber() {
		return generationNumber;
	}
	
	private TimerTask getTimerTask() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				for (int row = 0; row < grid.length; row++) {
					for (int column = 0; column < grid[row].length; column++) {
						grid[row][column].evalNextState();
					}
				}
				for (int row = 0; row < grid.length; row++) {
					for (int column = 0; column < grid[row].length; column++) {
						grid[row][column].updateState();
					}
				}
				generationNumber++;
				if (observer != null) {
					observer.onGenerationChanged();
				}
			}
		};
		return task;
	}
	
}
