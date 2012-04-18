package com.tatesuke.lifegame.manager;

import java.util.Timer;
import java.util.TimerTask;

import com.tatesuke.lifegame.cell.Cell;
import com.tatesuke.lifegame.cell.Cell.State;
import com.tatesuke.lifegame.cell.CellBuilder;
import com.tatesuke.lifegame.cell.CellImpl;

public class GameManager {

	private static final long INTERVAL = 1000L;
	
	private CellImpl[][] cell;
	private Timer gameTimer;
	private GameObserver observer;
	private int generationNumber;
	
	public GameManager() {
		cell = new CellBuilder().buildCellGrid(10, 10);
	}

	public void setObserver(GameObserver observer) {
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
				for (int row = 0; row < cell.length; row++) {
					for (int column = 0; column < cell[row].length; column++) {
						cell[row][column].evalNextState();
					}
				}
				for (int row = 0; row < cell.length; row++) {
					for (int column = 0; column < cell[row].length; column++) {
						cell[row][column].updateState();
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

	public void toggleCell(int row, int column) {
		State state = cell[row][column].getState();
		switch (state) {
		case ALIVE:
			cell[row][column].setState(State.DEAD);
			break;
		case DEAD:
			cell[row][column].setState(State.ALIVE);
			break;
		default:
			throw new RuntimeException();
		}
		if (observer != null) {
			observer.onGenerationChanged();
		}
	}

	public Cell[][] getCell() {
		return cell;
	}
	
}
